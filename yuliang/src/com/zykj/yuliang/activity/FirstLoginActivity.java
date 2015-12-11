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
			rl_huodeyes; // ������id��������id��ȷ����ť,��ý��
	private LinearLayout ll_yaoqingid, ll_text_t, ll_text_s; // ��������id
	private EditText et_inputid; // id�����
	private String userId;//�û���ID�Ƿ������Զ����ɷ��ص�

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
		initViews();
		initClick();
		initEvents();

		// ����ֻ���Ψһ��ʶ
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
				Tools.toast(FirstLoginActivity.this, "ע��ɹ�");
			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					byte[] responseBody, Throwable throwable) {
				super.onFailure(statusCode, headers, responseBody, throwable);
				Tools.toast(FirstLoginActivity.this, "���ֻ���ע��");
			}
		}, params);
	}

	protected void initViews() {
		rl_youyaoqing = (RelativeLayout) findViewById(R.id.rl_youyaoqing);// ������ID
		rl_wuyaoqing = (RelativeLayout) findViewById(R.id.rl_wuyaoqing);// ������ID
		rl_yaoqingyes = (RelativeLayout) findViewById(R.id.rl_yaoqingyes);// ��������ID���ȷ��
		rl_huodeyes = (RelativeLayout) findViewById(R.id.rl_huodeyes);// ������û����һ��ҳ���ȷ��
		ll_yaoqingid = (LinearLayout) findViewById(R.id.ll_yaoqingid);// ID������ȷ��
		ll_text_t = (LinearLayout) findViewById(R.id.ll_text_t);// ˵������
		ll_text_s = (LinearLayout) findViewById(R.id.ll_text_s);// ˵������
		et_inputid = (EditText) findViewById(R.id.et_inputid);// �����ID
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
				Tools.toast(FirstLoginActivity.this, "����ID����Ϊ�գ���û����ѡ�������룡");
				return;
			}
			rl_youyaoqing.setVisibility(View.GONE);
			rl_wuyaoqing.setVisibility(View.GONE);
			ll_yaoqingid.setVisibility(View.GONE);
			ll_text_t.setVisibility(View.VISIBLE);
			ll_text_s.setVisibility(View.VISIBLE);

			RequestParams params = new RequestParams();
			// params.put("", BaseApp.getModel().getDeviceId());//deviceIdû�л��,
			params.put("", shifuID);
			/**
			 * �ύ����
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
