package com.zykj.yuliang.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.zykj.yuliang.R;
import com.zykj.yuliang.view.MyCommonTitle;

public class YouChangDetailsActivity extends Activity implements
		OnClickListener {
	private MyCommonTitle myCommonTitle;
	private LinearLayout ll_zhuanfa;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_youchang_details);
		initView();

	}



	private void initView() {
		myCommonTitle=(MyCommonTitle) findViewById(R.id.aci_mytitle);
		myCommonTitle.setTitle("点击一下，现金到手！");
		
		ll_zhuanfa=(LinearLayout) findViewById(R.id.ll_yiyuan);//转发
	}



	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_yiyuan:

			break;
		default:
			break;
		}

	}

}
