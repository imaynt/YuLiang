package com.zykj.yuliang.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.zykj.yuliang.R;

public class ZiLiaoActivity extends Activity implements OnClickListener {

	private ImageButton btn_ziliao_back;		//·µ»Ø°´Å¥
	
	private LinearLayout ll_weixin, ll_bind_mobile;
	
	private Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_geren);
		initViews();
		initClick();
		initEvents();
		
		
	}

	protected void initClick() {
		btn_ziliao_back.setOnClickListener(this);
		ll_weixin.setOnClickListener(this);
		ll_bind_mobile.setOnClickListener(this);
	}

	protected void initViews() {
		btn_ziliao_back=(ImageButton)findViewById(R.id.btn_ziliao_back);
		ll_weixin=(LinearLayout)findViewById(R.id.ll_weixin_ziliao);
		ll_bind_mobile=(LinearLayout)findViewById(R.id.ll_bind_mobile_ziliao);
	}

	protected void initEvents() {

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_ziliao_back:
			this.finish();
			break;
		case R.id.ll_weixin_ziliao:
			intent = new Intent(ZiLiaoActivity.this, WeiXinActivity.class);
			startActivity(intent);
			break;
		case R.id.ll_bind_mobile_ziliao:
			intent = new Intent(ZiLiaoActivity.this, BindMobileActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
		
	}

}


