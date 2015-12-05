package com.zykj.yuliang.activity;

import static cn.smssdk.framework.utils.R.getStringRes;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

import com.loopj.android.http.RequestParams;
import com.zykj.yuliang.BaseActivity;
import com.zykj.yuliang.R;
import com.zykj.yuliang.R.id;
import com.zykj.yuliang.utils.StringUtil;
import com.zykj.yuliang.utils.TextUtil;
import com.zykj.yuliang.utils.Tools;
import com.zykj.yuliang.view.MyCommonTitle;

public class BindMobileActivity extends BaseActivity {
	private MyCommonTitle myCommonTitle;
	private LinearLayout ll_bind_1, ll_bind_2, ll_submit;
	private TextView bind_descrip,identify_code, binded_mobile, bind_new_mobile;
	private EditText mobile_number, mobile_code;
	public String mobile, mobileCode;
	private static String APPKEY = "cff7289b85e8";
	private static String APPSECRET = "d21bb73534593769b4ba2c3b45a4b2c6";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mobile = getIntent().getStringExtra("mobile");

		setContentView(R.layout.ui_more_bind_mobile);
		initView();

		// 初始化短信验证
		SMSSDK.initSDK(this, APPKEY, APPSECRET);
		EventHandler eh = new EventHandler() {

			@Override
			public void afterEvent(int event, int result, Object data) {

				Message msg = new Message();
				msg.arg1 = event;
				msg.arg2 = result;
				msg.obj = data;
				handler.sendMessage(msg);
			}
		};
		SMSSDK.registerEventHandler(eh);
	}

	private void initView() {
		myCommonTitle = (MyCommonTitle) findViewById(R.id.aci_mytitle);
		myCommonTitle.setTitle("绑定手机");

		bind_descrip=(TextView)findViewById(R.id.tv_bind_descrip);
		
		ll_bind_1 = (LinearLayout) findViewById(R.id.ll_bind_1);// 已绑定了的,显示页面
		ll_bind_2 = (LinearLayout) findViewById(R.id.ll_bind_2);// 未绑定 显示的页面

		binded_mobile = (TextView) findViewById(R.id.tv_binded_mobile);// 已绑定的手机

		bind_new_mobile = (TextView) findViewById(R.id.tv_bind_new_mobile);// 绑定新手机
		if (StringUtil.isEmpty(mobile)) {//此处是从"更多"--"切换账号"跳转过来的,没有传mobile,所以mobile为空
			ll_bind_1.setVisibility(View.GONE);
			ll_bind_2.setVisibility(View.VISIBLE);
			bind_descrip.setText("曾经绑定过手机的账号可以在此找回30天以内的账号,可以切换两次");
		}else{
			if (!TextUtil.isMobile(mobile)) {//此处是从"更多"--"绑定手机"跳转过来的,传mobile了,判断mobile是否为手机号
				ll_bind_1.setVisibility(View.GONE);
				ll_bind_2.setVisibility(View.VISIBLE);
			} else {
				ll_bind_1.setVisibility(View.VISIBLE);
				ll_bind_2.setVisibility(View.GONE);
				binded_mobile.setText(mobile);
			}
		}
		

		mobile_number = (EditText) findViewById(R.id.ed_mobile_number);// 手机号
		mobile_code = (EditText) findViewById(R.id.ed_mobile_code);// 验证码

		identify_code = (TextView) findViewById(R.id.tv_identify_code);// 获取验证码按钮
		ll_submit = (LinearLayout) findViewById(R.id.ll_submit);// 提交

		setListener(bind_new_mobile, identify_code, ll_submit);
	}

	@Override
	public void onClick(View view) {
		super.onClick(view);
		mobile = mobile_number.getText().toString().trim();
		mobileCode = mobile_code.getText().toString().trim();
		switch (view.getId()) {
		case R.id.tv_bind_new_mobile:// 绑定新手机
			ll_bind_1.setVisibility(View.GONE);
			ll_bind_2.setVisibility(View.VISIBLE);
			break;
		case R.id.tv_identify_code:// 获取验证码
			if (!TextUtil.isMobile(mobile)) {
				Tools.toast(BindMobileActivity.this, "手机号码格式不正确");
			} else {
				/* 发送手机验证码 */
				identify_code.setOnClickListener(null);
				new MyCount(60000, 1000).start();// 一分钟倒计时
				SMSSDK.getVerificationCode("86", mobile);
			}
			break;
		case R.id.ll_submit:// 提交绑定信息
			if (!TextUtil.isCode(mobileCode, 4)) {
				Tools.toast(BindMobileActivity.this, "验证码不正确");
			} else {
				SMSSDK.submitVerificationCode("86", mobile, mobileCode);
			}
			break;
		default:
			break;
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		SMSSDK.unregisterAllEventHandler();
	}

	@SuppressLint("HandlerLeak")
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			int event = msg.arg1;
			int result = msg.arg2;
			Object data = msg.obj;
			Log.e("result", "result=" + result);
			Log.e("event", "event=" + event);
			if (result == SMSSDK.RESULT_COMPLETE) {
				// 短信注册成功后，返回MainActivity,然后提示新好友
				if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {// 提交验证码成功
					// Tools.toast(UserRegisterActivity.this, "提交验证码成功");
					registerNewUser();
					// uu_password.setFocusable(true);
				} else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
					Tools.toast(BindMobileActivity.this, "验证码已经发送");
				}
			} else {
				((Throwable) data).printStackTrace();
				int resId = getStringRes(BindMobileActivity.this,
						"smssdk_network_error");
				Tools.toast(
						BindMobileActivity.this,
						event == SMSSDK.EVENT_GET_VERIFICATION_CODE ? "验证码频繁，请稍后再试！"
								: "验证码错误");
				if (resId > 0) {
					Tools.toast(BindMobileActivity.this, resId + "");
				}
			}
		}

		private void registerNewUser() {
			RequestParams params = new RequestParams();

			params.put("mob", mobile);
			params.put("code", mobileCode);
			// HttpUtils.register(new HttpErrorHandler() {
			// @Override
			// public void onRecevieSuccess(JSONObject json) {
			// MyRequestDailog.closeDialog();
			// Tools.toast(BindMobileActivity.this, json.getString("message"));
			// finish();
			// }
			// @Override
			// public void onRecevieFailed(String status, JSONObject json) {
			// Tools.toast(BindMobileActivity.this, json.getString("message"));
			// }
			// }, params);
			setResult(RESULT_OK);
			finish();
		}
	};

	/* 定义一个倒计时的内部类 */
	class MyCount extends CountDownTimer {
		public MyCount(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onFinish() {
			identify_code.setText("点击获取验证码");
			identify_code.setOnClickListener(BindMobileActivity.this);
		}

		@Override
		public void onTick(long millisUntilFinished) {
			identify_code.setText(millisUntilFinished / 1000 + "秒");
		}
	}
}
