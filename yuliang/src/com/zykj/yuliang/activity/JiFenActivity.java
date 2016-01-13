package com.zykj.yuliang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zykj.yuliang.BaseActivity;
import com.zykj.yuliang.R;
import com.zykj.yuliang.view.MyCommonTitle;

public class JiFenActivity extends BaseActivity{
	
	private MyCommonTitle myCommonTitle;
	
	private LinearLayout ll_jifenwenti;		//积分问题
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jifen);
		initViews();

	}
	

	private void initViews() {
		myCommonTitle = (MyCommonTitle) findViewById(R.id.aci_mytitle);
		myCommonTitle.setTitle("积分问题");
		ll_jifenwenti=(LinearLayout)findViewById(R.id.ll_jifenwenti);
		setListener(ll_jifenwenti);
	}

	@Override
	public void onClick(View view) {
		super.onClick(view);
		switch (view.getId()) {
		case R.id.ll_jifenwenti:
//			Intent intent=new Intent(JiFenActivity.this,JiFenProblemActivity.class);
//			startActivity(intent);
			break;
		default:
			break;
		}
	}
}
