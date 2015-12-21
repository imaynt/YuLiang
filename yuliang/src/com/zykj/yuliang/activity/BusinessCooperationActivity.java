package com.zykj.yuliang.activity;

import com.zykj.yuliang.BaseActivity;
import com.zykj.yuliang.R;
import com.zykj.yuliang.view.MyCommonTitle;

import android.app.Activity;
import android.os.Bundle;

public class BusinessCooperationActivity extends BaseActivity{
	private MyCommonTitle myCommonTitle;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.ui_more_business_cooperation);
		
		initView();
	}

	private void initView() {
		myCommonTitle=(MyCommonTitle) findViewById(R.id.aci_mytitle);
		myCommonTitle.setTitle("商务合作");
	}

}
