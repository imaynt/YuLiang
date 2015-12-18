package com.zykj.yuliang.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.zykj.yuliang.BaseActivity;
import com.zykj.yuliang.R;
import com.zykj.yuliang.view.MyCommonTitle;

public class YouChangActivity extends BaseActivity {

	private MyCommonTitle myCommonTitle;
	private LinearLayout ll_item;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_youchang);
		initViews();
		initClick();
		initEvents();

	}

	protected void initClick() {
		
		ll_item.setOnClickListener(this);
	}

	protected void initViews() {
		myCommonTitle = (MyCommonTitle) findViewById(R.id.aci_mytitle);
		myCommonTitle.setTitle("ÓÐ³¥×ª·¢");
		ll_item = (LinearLayout) findViewById(R.id.ll_yiyuan);
	}

	protected void initEvents() {

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
