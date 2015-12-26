package com.zykj.yuliang.activity;

import static cn.smssdk.framework.utils.R.getStringRes;

import org.apache.http.Header;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.RequestParams;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zykj.yuliang.BaseActivity;
import com.zykj.yuliang.BaseApp;
import com.zykj.yuliang.R;
import com.zykj.yuliang.R.id;
import com.zykj.yuliang.R.string;
import com.zykj.yuliang.http.HttpErrorHandler;
import com.zykj.yuliang.http.HttpUtils;
import com.zykj.yuliang.http.UrlContants;
import com.zykj.yuliang.utils.StringUtil;
import com.zykj.yuliang.utils.TextUtil;
import com.zykj.yuliang.utils.Tools;
import com.zykj.yuliang.view.MyCommonTitle;

public class BindMobileActivity extends BaseActivity {
	private MyCommonTitle myCommonTitle;
	private LinearLayout ll_bind_1, ll_bind_2, ll_submit;
	private TextView bind_descrip, identify_code, binded_mobile, bind_new_mobile;
	private EditText mobile_number, mobile_code;
	public String mobile, mobileCode;
	private static String APPKEY = "d1ff3bafe382";
	private static String APPSECRET = "6cb8331f1f929ec5de0c3b79e484a21a";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mobile = getIntent().getStringExtra("mobile");

		setContentView(R.layout.ui_more_bind_mobile);
		initView();

		// ��ʼ��������֤
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
		myCommonTitle.setTitle(StringUtil.isEmpty(mobile) ? "�л��˺�" : "���ֻ�");

		bind_descrip = (TextView) findViewById(R.id.tv_bind_descrip);

		ll_bind_1 = (LinearLayout) findViewById(R.id.ll_bind_1);// �Ѱ��˵�,��ʾҳ��
		ll_bind_2 = (LinearLayout) findViewById(R.id.ll_bind_2);// δ�� ��ʾ��ҳ��

		binded_mobile = (TextView) findViewById(R.id.tv_binded_mobile);// �Ѱ󶨵��ֻ�

		bind_new_mobile = (TextView) findViewById(R.id.tv_bind_new_mobile);// �����ֻ�
		if (StringUtil.isEmpty(mobile)) {// �˴��Ǵ�"����"--"�л��˺�"��ת������,û�д�mobile,����mobileΪ��
			ll_bind_1.setVisibility(View.GONE);
			ll_bind_2.setVisibility(View.VISIBLE);
			bind_descrip.setText("�����󶨹��ֻ����˺ſ����ڴ��һ�30�����ڵ��˺�,�����л�����");
		} else {
			if (!TextUtil.isMobile(mobile)) {// �˴��Ǵ�"����"--"���ֻ�"��ת������,��mobile��,�ж�mobile�Ƿ�Ϊ�ֻ���
				ll_bind_1.setVisibility(View.GONE);
				ll_bind_2.setVisibility(View.VISIBLE);
			} else {
				ll_bind_1.setVisibility(View.VISIBLE);
				ll_bind_2.setVisibility(View.GONE);
				binded_mobile.setText(mobile);
			}
		}

		mobile_number = (EditText) findViewById(R.id.ed_mobile_number);// �ֻ���
		mobile_code = (EditText) findViewById(R.id.ed_mobile_code);// ��֤��

		identify_code = (TextView) findViewById(R.id.tv_identify_code);// ��ȡ��֤�밴ť
		ll_submit = (LinearLayout) findViewById(R.id.ll_submit);// �ύ

		setListener(bind_new_mobile, identify_code, ll_submit);
	}

	@Override
	public void onClick(View view) {
		super.onClick(view);
		mobile = mobile_number.getText().toString().trim();
		mobileCode = mobile_code.getText().toString().trim();
		switch (view.getId()) {
		case R.id.tv_bind_new_mobile:// �����ֻ�
			ll_bind_1.setVisibility(View.GONE);
			ll_bind_2.setVisibility(View.VISIBLE);
			break;
		case R.id.tv_identify_code:// ��ȡ��֤��
			if (!TextUtil.isMobile(mobile)) {
				Tools.toast(BindMobileActivity.this, "�ֻ������ʽ����ȷ");
			} else {
				/* �����ֻ���֤�� */
				identify_code.setOnClickListener(null);
				new MyCount(60000, 1000).start();// һ���ӵ���ʱ
				SMSSDK.getVerificationCode("86", mobile);
			}
			break;
		case R.id.ll_submit:// �ύ����Ϣ
			if (!TextUtil.isCode(mobileCode, 4)) {
				Tools.toast(BindMobileActivity.this, "��֤�벻��ȷ");
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
				// ����ע��ɹ��󣬷���MainActivity,Ȼ����ʾ�º���
				if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {// �ύ��֤��ɹ�
					// Tools.toast(UserRegisterActivity.this, "�ύ��֤��ɹ�");
					if (myCommonTitle.getTitle().getText().toString().equals("���ֻ�")) {
						registerNewUser();
					} else if (myCommonTitle.getTitle().getText().toString().equals("�л��˺�")) {
						changeUserId();
					}
					// uu_password.setFocusable(true);
				} else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
					Tools.toast(BindMobileActivity.this, "��֤���Ѿ�����");
				}
			} else {
				((Throwable) data).printStackTrace();
				int resId = getStringRes(BindMobileActivity.this, "smssdk_network_error");
				Tools.toast(BindMobileActivity.this,
						event == SMSSDK.EVENT_GET_VERIFICATION_CODE ? "��֤��Ƶ�������Ժ����ԣ�" : "��֤�����");
				if (resId > 0) {
					Tools.toast(BindMobileActivity.this, resId + "");
				}
			}
		}

		/**
		 * �����ֻ�
		 */
		private void registerNewUser() {
			RequestParams params = new RequestParams();
			// deviceIdû�л�ȡ�������ύ���ݲ��ɹ�
			params.put("deviceId", BaseApp.getModel().getDeviceId());// deviceId�豸id
			params.put("mobile", mobile);// mobileҪ�󶨵��ֻ���
			HttpUtils.bindMobile(new HttpErrorHandler() {

				@Override
				public void onRecevieSuccess(JSONObject json) {
					Tools.toast(BindMobileActivity.this, "�ֻ��󶨳ɹ�");
					BaseApp.getModel().setMobile(mobile);
					setResult(RESULT_OK);
					finish();
				}
			}, params);
		}
	};

	/**
	 * �л��˺�
	 */
	private void changeUserId() {
		RequestParams params = new RequestParams();
		params.put("mob", mobile);//mob�û���¼���Ƽ��ֻ�����
		HttpUtils.changeUserId(new HttpErrorHandler() {
			
			@Override
			public void onRecevieSuccess(JSONObject json) {
//				{
//				    "code": 200,    "message": "��¼�ɹ�",	    
//				    "datas": {
//				        "id": "11",   "mobile": "18660992783",    "username": "����",    "password": "96e79218965eb72c92a549dd5a330112",
//				        "avatar": "http://115.28.67.86/yl/Uploads/20151016/562080da2c901.jpg",     "type": "0",       "group": "0",
//				        "isadmin": "0",      "addtime": "2015-10-15 17:30:46",      "lastlog": "2015-12-08 09:37:19",
//				        "ischeck": "1",      "istop": null,      "content": null,     "account": "-33.00",    "coins": "33",
//				        "points": "9999999",    "name": null,     "address": null,     "email": null,   "qq": null,
//				        "fax": null,     "deviceid": "11",   "referrerid": "0",    "birthday": "0000-00-00 00:00:00",
//				        "sex": "��",   "profession": null,      "parentid": "0",     "new_and_personal": "00",    "sign": "no"
//				    }			}
				JSONObject jsonObject=json.getJSONObject(UrlContants.jsonData);
				String id=jsonObject.getString("id");//�û�id
				BaseApp.getModel().setUserid(id);
				String mobile=jsonObject.getString("mobile");//�û��ֻ�
				BaseApp.getModel().setMobile(mobile);
				String username=jsonObject.getString("username");//�û��ǳ�
				BaseApp.getModel().setUsername(username);
				String avatar=jsonObject.getString("avatar");//�û�ͷ��
				BaseApp.getModel().setAvatar(avatar);
				String account=jsonObject.getString("account");//�û����
				BaseApp.getModel().setMoney(account);
				String birthday=jsonObject.getString("birthday").substring(0, 11);//�û�����
				BaseApp.getModel().setBirth(birthday);
				String sex=jsonObject.getString("sex");//�û��Ա�
				BaseApp.getModel().setSex(sex);
				String profession=jsonObject.getString("profession");//�û�ְҵ
				BaseApp.getModel().setPrefession(profession);
				setResult(RESULT_OK);
				finish();
			}
		}, params);
		
	}

	/* ����һ������ʱ���ڲ��� */
	class MyCount extends CountDownTimer {
		public MyCount(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onFinish() {
			identify_code.setText("�����ȡ��֤��");
			identify_code.setOnClickListener(BindMobileActivity.this);
		}

		@Override
		public void onTick(long millisUntilFinished) {
			identify_code.setText(millisUntilFinished / 1000 + "��");
		}
	}
}
