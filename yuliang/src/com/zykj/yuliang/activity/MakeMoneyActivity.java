package com.zykj.yuliang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import org.apache.http.Header;
import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.RequestParams;
import com.zykj.yuliang.BaseActivity;
import com.zykj.yuliang.BaseApp;
import com.zykj.yuliang.R;
import com.zykj.yuliang.http.AbstractHttpHandler;
import com.zykj.yuliang.http.HttpUtils;
import com.zykj.yuliang.utils.StringUtil;
import com.zykj.yuliang.view.MyCommonTitle;

public class MakeMoneyActivity extends BaseActivity {

	private MyCommonTitle myCommonTitle;
	private LinearLayout ll_lianmeng; // ��������
	private LinearLayout ll_ziliao; // ��������
	private LinearLayout ll_youchang; // �г�ת��
	private LinearLayout ll_new; // ��������
	private Intent intent;
	private String part = "1";// 1�����ֽ̳̣�2�Ǹ������ϵ÷�
	private RequestParams params;

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
		myCommonTitle.setTitle("׬Ǯ");
		ll_lianmeng = (LinearLayout) findViewById(R.id.ll_lianmeng);
		ll_ziliao = (LinearLayout) findViewById(R.id.ll_ziliao);
		ll_youchang = (LinearLayout) findViewById(R.id.ll_youchang);
		ll_new = (LinearLayout) findViewById(R.id.ll_new);
		String nick=BaseApp.getModel().getUsername();
		if(StringUtil.isEmpty(nick))
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
			intent = new Intent(MakeMoneyActivity.this, ZiLiaoActivity.class);
			startActivity(intent);
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
	 * ���е�Activity����ķ���ֵ������������������� requestCode:
	 * ��ʾ��������һ��Activityʱ����ȥ��requestCodeֵ
	 * resultCode����ʾ�����������Activity�ش�ֵʱ��resultCodeֵ
	 * data����ʾ�����������Activity�ش�������Intent����
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
	 * �������������,�鿴���ֽ̳̺͸��������Ƿ����,��ɺ���Ӧ�Ĺ��ܲ���ʾ
	 * 
	 */
	private void requestData() {
		/**
		 * ���ֽ̳�
		 */
		String id = BaseApp.getModel().getDeviceId();
		params = new RequestParams();
		params.put("deviceId", BaseApp.getModel().getDeviceId());// �豸ID
		params.put("part", "1");// 1����2��1�����ֽ̳̣�2�Ǹ������ϵ÷֣�
		HttpUtils.postNewAndPersonal(new AbstractHttpHandler() {

			@Override
			public void onJsonSuccess(JSONObject json) {
				if (json.getString("code").equals("200")) {// ��������,�������������
					ll_new.setVisibility(View.VISIBLE);
				}
			}

			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {

			}

		}, params);

		/**
		 * ��������
		 */
		params = new RequestParams();
		params.put("deviceId", BaseApp.getModel().getDeviceId());// �豸ID
		params.put("part", "2");// 1����2��1�����ֽ̳̣�2�Ǹ������ϵ÷֣�
		HttpUtils.postNewAndPersonal(new AbstractHttpHandler() {

			@Override
			public void onJsonSuccess(JSONObject json) {
				if (json.getString("code").equals("200")) {//�������������
				
				}				
			}
			
			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
				
			}
		},params); 
	}

}
