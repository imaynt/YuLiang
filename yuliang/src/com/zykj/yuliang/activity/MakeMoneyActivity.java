package com.zykj.yuliang.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.zykj.yuliang.R;

public class MakeMoneyActivity extends Activity implements OnClickListener{

	private ImageButton btn_makemoney_back;		//返回按钮
	private LinearLayout ll_lianmeng;  			//联盟任务
	private LinearLayout ll_ziliao;   			//个人资料
	private LinearLayout ll_youchang;			//有偿转发
	private Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_makemoney);
		initViews();
		initClick();
		initEvents();
		
		
	}

	protected void initClick() {
		btn_makemoney_back.setOnClickListener(this);
		ll_lianmeng.setOnClickListener(this);
		ll_ziliao.setOnClickListener(this);
		ll_youchang.setOnClickListener(this);
	}

	protected void initViews() {
		btn_makemoney_back = (ImageButton) findViewById(R.id.btn_makemoney_back);
		ll_lianmeng = (LinearLayout) findViewById(R.id.ll_lianmeng);
		ll_ziliao = (LinearLayout)findViewById(R.id.ll_ziliao);
		ll_youchang = (LinearLayout)findViewById(R.id.ll_youchang);
	}

	protected void initEvents() {

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_makemoney_back:
			this.finish();
			break;
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

		default:
			break;
		}
		
	}

}
