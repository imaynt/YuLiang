package com.zykj.yuliang.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.zykj.yuliang.BaseActivity;
import com.zykj.yuliang.R;
import com.zykj.yuliang.utils.CommonUtils;
import com.zykj.yuliang.view.MyCommonTitle;

public class DuiHuanDuiHuanActivity extends BaseActivity {
	private MyCommonTitle myCommonTitle;
	private LinearLayout ll_submit;
	private TextView tv_account;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_duihuan_duihuan);

		initView();

	}

	private void initView() {
		myCommonTitle = (MyCommonTitle) findViewById(R.id.aci_mytitle);
		myCommonTitle.setTitle("¶Ò»»±Ò¶Ò»»");
		
		tv_account = (TextView) findViewById(R.id.tv_account);//½ð¶î
		ll_submit = (LinearLayout) findViewById(R.id.ll_submit);//Ìá½»

		setListener(tv_account, ll_submit);
	}

	@Override
	public void onClick(View view) {
		super.onClick(view);
		switch (view.getId()) {
		case R.id.tv_account:
			
			break;
		case R.id.ll_submit:
			
			break;
		default:
			break;
		}
	}
}
