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

	private RelativeLayout rl_youyaoqing, rl_wuyaoqing, rl_yaoqingyes, rl_huodeyes; // 有邀请id，无邀请id，确定按钮,获得金额
	private LinearLayout ll_yaoqingid, ll_text_t, ll_text_s; // 输入邀请id
	private EditText et_inputid; // id输入框
	private String userId, shifuID;// 用户的ID是服务器自动生成返回的
	private RequestParams params;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
		initViews();
		initClick();
		initEvents();

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
		case R.id.rl_youyaoqing:// 有邀请ID
			rl_youyaoqing.setVisibility(View.GONE);
			ll_yaoqingid.setVisibility(View.VISIBLE);
			break;
		case R.id.rl_wuyaoqing:// 无邀请ID、
			rl_youyaoqing.setVisibility(View.GONE);
			ll_yaoqingid.setVisibility(View.GONE);
			rl_wuyaoqing.setVisibility(View.GONE);
			ll_text_t.setVisibility(View.VISIBLE);
			ll_text_s.setVisibility(View.VISIBLE);
			params = new RequestParams();
			params.put("deviceid", BaseApp.getModel().getDeviceId());// deviceId没有获得,
			HttpUtils.getPointsFromInvite(new HttpErrorHandler() {

				@Override
				public void onRecevieSuccess(JSONObject json) {
					Tools.toast(FirstLoginActivity.this, "无邀请ID获得20积分");
				}
			}, params);
			/**
			 * 提交数据
			 */
			break;
		case R.id.rl_yaoqingyes:// 有邀请ID,并输入ID后的确定
			shifuID = et_inputid.getText().toString().trim();
			if (StringUtil.isEmpty(shifuID)) {
				Tools.toast(FirstLoginActivity.this, "邀请ID不能为空，如没有请选择无邀请！");
				return;
			}
			rl_youyaoqing.setVisibility(View.GONE);
			rl_wuyaoqing.setVisibility(View.GONE);
			ll_yaoqingid.setVisibility(View.GONE);
			ll_text_t.setVisibility(View.VISIBLE);
			ll_text_s.setVisibility(View.VISIBLE);
			params = new RequestParams();
			params.put("deviceid", BaseApp.getModel().getDeviceId());// deviceId没有获得,
			params.put("parentid", shifuID);
			HttpUtils.getPointsFromInvite(new HttpErrorHandler() {

				@Override
				public void onRecevieSuccess(JSONObject json) {
					Tools.toast(FirstLoginActivity.this, "有邀请ID获得30积分");
				}
			}, params);
			/**
			 * 提交数据
			 */
			break;
		case R.id.rl_huodeyes:
			startActivity(new Intent(FirstLoginActivity.this, MainActivity.class).putExtra("userId", userId));

			finish();
			break;
		default:
			break;
		}

	}

}
