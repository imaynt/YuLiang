package com.zykj.yuliang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.LinearLayout;
import org.apache.http.Header;
import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.RequestParams;
import com.zykj.yuliang.BaseActivity;
import com.zykj.yuliang.BaseApp;
import com.zykj.yuliang.R;
import com.zykj.yuliang.http.AbstractHttpHandler;
import com.zykj.yuliang.http.HttpErrorHandler;
import com.zykj.yuliang.http.HttpUtils;
import com.zykj.yuliang.http.UrlContants;
import com.zykj.yuliang.utils.SharedPreferenceUtils;
import com.zykj.yuliang.utils.StringUtil;
import com.zykj.yuliang.view.MyCommonTitle;

public class MakeMoneyActivity extends BaseActivity {

	private MyCommonTitle myCommonTitle;
	private LinearLayout ll_lianmeng; // 联盟任务
	private LinearLayout ll_ziliao; // 个人资料
	private LinearLayout ll_youchang; // 有偿转发
	private LinearLayout ll_new; // 新手任务
	private Intent intent;
	private String part = "1";// 1是新手教程，2是个人资料得分
	private RequestParams params;
	private String state = "1";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_makemoney);

		initViews();
		initClick();
		initEvents();

		requestData();
	}

	protected void initClick() {
		ll_lianmeng.setOnClickListener(this);
		ll_ziliao.setOnClickListener(this);
		ll_youchang.setOnClickListener(this);
		ll_new.setOnClickListener(this);
	}

	protected void initViews() {
		myCommonTitle = (MyCommonTitle) findViewById(R.id.aci_mytitle);
		myCommonTitle.setTitle("赚钱");
		ll_lianmeng = (LinearLayout) findViewById(R.id.ll_lianmeng);
		ll_ziliao = (LinearLayout) findViewById(R.id.ll_ziliao);
		ll_youchang = (LinearLayout) findViewById(R.id.ll_youchang);
		ll_new = (LinearLayout) findViewById(R.id.ll_new);
		String nick = BaseApp.getModel().getUsername();
		if (!StringUtil.isEmpty(nick))
			ll_ziliao.setVisibility(View.VISIBLE);

	}

	protected void initEvents() {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_lianmeng:
			intent = new Intent(MakeMoneyActivity.this, LianMengActivity.class);
			startActivity(intent);
			break;
		case R.id.ll_ziliao:
			startActivity(new Intent(MakeMoneyActivity.this, ZiLiaoActivity.class));
			break;
		case R.id.ll_youchang:
			intent = new Intent(MakeMoneyActivity.this, YouChangActivity.class);
			startActivity(intent);
			break;
		case R.id.ll_new:
			intent = new Intent(MakeMoneyActivity.this, NewActivity.class);
			startActivityForResult(intent, 1000);
			break;

		default:
			break;
		}

	}

	/**
	 * 所有的Activity对象的返回值都是由这个方法来接收 requestCode:
	 * 表示的是启动一个Activity时传过去的requestCode值
	 * resultCode：表示的是启动后的Activity回传值时的resultCode值
	 * data：表示的是启动后的Activity回传过来的Intent对象
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 1000 && resultCode == 1001) {
			String result_value = data.getStringExtra("result");
			if (result_value.equals("6"))
				ll_new.setVisibility(View.GONE);
		}
	}

	/**
	 * 请求服务器数据,查看新手教程和个人资料是否完成,完成后相应的功能不显示
	 * 
	 */
	private void requestData() {

		TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		final String DEVICE_ID = tm.getDeviceId();

		RequestParams params = new RequestParams();
		params.put("deviceId", DEVICE_ID);
		HttpUtils.postNewAndPersonalstate(new HttpErrorHandler() {

			@Override
			public void onRecevieSuccess(JSONObject json) {
				JSONObject jsonObject = json.getJSONObject(UrlContants.jsonData);
				state = jsonObject.getString("new").toString();
				if (state.equals("0")) {
					ll_new.setVisibility(View.VISIBLE);
				}

			}

			@Override
			public void onRecevieFailed(String status, JSONObject json) {
				super.onRecevieFailed(status, json);
			}
		}, params);
		if (state.equals("0")) {
			ll_new.setVisibility(View.VISIBLE);
		}
	}
}