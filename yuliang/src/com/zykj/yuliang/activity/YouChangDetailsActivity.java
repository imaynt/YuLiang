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
import com.zykj.yuliang.utils.CommonUtils;
import com.zykj.yuliang.view.MyCommonTitle;

public class YouChangDetailsActivity extends BaseActivity {
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
		
		ll_zhuanfa=(LinearLayout) findViewById(R.id.ll_zhuanfa);//转发
		
		setListener(ll_zhuanfa);
	}



	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_zhuanfa:
			CommonUtils.showShare(this, "余粮赚钱", "余粮赚钱余粮赚钱", "http://fir.im");
			break;
		default:
			break;
		}

	}

}
