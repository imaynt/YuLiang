package com.zykj.yuliang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zykj.yuliang.BaseActivity;
import com.zykj.yuliang.R;

public class JiFenActivity extends BaseActivity{
	private ImageView iv_jf_back;
	
	private LinearLayout ll_jifenwenti;		//积分问题
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jifen);
		initViews();

	}
	

	private void initViews() {
		iv_jf_back=(ImageView)findViewById(R.id.btn_jf_back);
		ll_jifenwenti=(LinearLayout)findViewById(R.id.ll_jifenwenti);
		setListener(ll_jifenwenti,iv_jf_back);
	}

	@Override
	public void onClick(View view) {
		super.onClick(view);
		switch (view.getId()) {
		case R.id.ll_jifenwenti:
			Intent intent=new Intent(JiFenActivity.this,JiFenProblemActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_jf_back:
			this.finish();
			break;
		default:
			break;
		}
	}
}
