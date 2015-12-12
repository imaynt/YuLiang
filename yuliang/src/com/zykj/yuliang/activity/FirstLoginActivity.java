package com.zykj.yuliang.activity;

import org.apache.http.Header;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.zykj.yuliang.R;
import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.RequestParams;
import com.zykj.yuliang.BaseActivity;
import com.zykj.yuliang.BaseApp;
import com.zykj.yuliang.http.HttpErrorHandler;
import com.zykj.yuliang.http.HttpUtils;
import com.zykj.yuliang.http.UrlContants;
import com.zykj.yuliang.utils.StringUtil;
import com.zykj.yuliang.utils.Tools;

public class FirstLoginActivity extends BaseActivity implements OnClickListener {

	private RelativeLayout rl_youyaoqing, rl_wuyaoqing, rl_yaoqingyes,
			rl_huodeyes; // 有邀请id，无邀请id，确定按钮,获得金额
	private LinearLayout ll_yaoqingid, ll_text_t, ll_text_s; // 输入邀请id
	private EditText et_inputid; // id输入框
	private String userId;//用户的ID是服务器自动生成返回的

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
		initViews();
		initClick();
		initEvents();

		// 获得手机的唯一标识
		TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		final String DEVICE_ID = tm.getDeviceId();

		RequestParams params = new RequestParams();
		params.put("deviceId", DEVICE_ID);
		HttpUtils.autoReg(new HttpErrorHandler() {

			@Override
			public void onRecevieSuccess(JSONObject json) {
				JSONObject jsonObject = json
						.getJSONObject(UrlContants.jsonData);
				userId = jsonObject.getString("id");
				BaseApp.getModel().setUserid(userId);
				BaseApp.getModel().setDeviceId(DEVICE_ID);
				Tools.toast(FirstLoginActivity.this, "注册成功");
			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					byte[] responseBody, Throwable throwable) {
				super.onFailure(statusCode, headers, responseBody, throwable);
				Tools.toast(FirstLoginActivity.this, "此手机已注册");
			}
		}, params);
	}

	protected void initViews() {
		rl_youyaoqing = (RelativeLayout) findViewById(R.id.rl_youyaoqing);// 有邀请ID
		rl_wuyaoqing = (RelativeLayout) findViewById(R.id.rl_wuyaoqing);// 无邀请ID
		rl_yaoqingyes = (RelativeLayout) findViewById(R.id.rl_yaoqingyes);// 输入邀请ID后的确定
		rl_huodeyes = (RelativeLayout) findViewById(R.id.rl_huodeyes);// 不管有没有下一个页面的确定
		ll_yaoqingid = (LinearLayout) findViewById(R.id.ll_yaoqingid);// ID输入框和确定
		ll_text_t = (LinearLayout) findViewById(R.id.ll_text_t);// 说明文字
		ll_text_s = (LinearLayout) findViewById(R.id.ll_text_s);// 说明文字
		et_inputid = (EditText) findViewById(R.id.et_inputid);// 邀请的ID
	}

	protected void initClick() {
		rl_youyaoqing.setOnClickListener(this);
		rl_wuyaoqing.setOnClickListener(this);
		rl_yaoqingyes.setOnClickListener(this);
		rl_huodeyes.setOnClickListener(this);

	}

	protected void initEvents() {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rl_youyaoqing:
			rl_youyaoqing.setVisibility(View.GONE);
			ll_yaoqingid.setVisibility(View.VISIBLE);
			break;
		case R.id.rl_wuyaoqing:
			rl_youyaoqing.setVisibility(View.GONE);
			ll_yaoqingid.setVisibility(View.GONE);
			rl_wuyaoqing.setVisibility(View.GONE);
			ll_text_t.setVisibility(View.VISIBLE);
			ll_text_s.setVisibility(View.VISIBLE);
			break;
		case R.id.rl_yaoqingyes:
			String shifuID = et_inputid.getText().toString().trim();
			if (StringUtil.isEmpty(shifuID)) {
				Tools.toast(FirstLoginActivity.this, "邀请ID不能为空，如没有请选择无邀请！");
				return;
			}
			rl_youyaoqing.setVisibility(View.GONE);
			rl_wuyaoqing.setVisibility(View.GONE);
			ll_yaoqingid.setVisibility(View.GONE);
			ll_text_t.setVisibility(View.VISIBLE);
			ll_text_s.setVisibility(View.VISIBLE);

			RequestParams params = new RequestParams();
			// params.put("", BaseApp.getModel().getDeviceId());//deviceId没有获得,
			params.put("", shifuID);
			/**
			 * 提交数据
			 */
			break;
		case R.id.rl_huodeyes:
			startActivity(new Intent(FirstLoginActivity.this,
					MainActivity.class).putExtra("userId", userId));
			finish();
			break;
		default:
			break;
		}

	}

}
