package com.zykj.yuliang.activity;

import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.RequestParams;
import com.zykj.yuliang.BaseActivity;
import com.zykj.yuliang.BaseApp;
import com.zykj.yuliang.R;
import com.zykj.yuliang.http.HttpErrorHandler;
import com.zykj.yuliang.http.HttpUtils;
import com.zykj.yuliang.view.MyCommonTitle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class YouChangActivity extends BaseActivity {

	private MyCommonTitle myCommonTitle;
	private LinearLayout ll_item;
	private TextView tv_my_money;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_youchang);
		initViews();

	}

	protected void initClick() {
		
		ll_item.setOnClickListener(this);
	}

	protected void initViews() {
		myCommonTitle = (MyCommonTitle) findViewById(R.id.aci_mytitle);
		myCommonTitle.setTitle("ÓÐ³¥×ª·¢");
		ll_item = (LinearLayout) findViewById(R.id.ll_yiyuan);
		tv_my_money=(TextView) findViewById(R.id.tv_my_money);
		setListener(ll_item);
		requestData();
	}

	private void requestData() {
		RequestParams params=new RequestParams();
		params.put("deviceId", BaseApp.getModel().getDeviceId());
		params.put("type", "4");//Í½µÜ½±Àø4
		HttpUtils.getScoreList(new HttpErrorHandler() {
			
			@Override
			public void onRecevieSuccess(JSONObject json) {
				String data=json.getString("datas");
				tv_my_money.setText(data);
			}
		}, params);		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_yiyuan:
			startActivity(new Intent(YouChangActivity.this, YouChangDetailsActivity.class));
			break;
		default:
			break;
		}

	}

}
