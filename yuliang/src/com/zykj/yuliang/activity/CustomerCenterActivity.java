package com.zykj.yuliang.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.zykj.yuliang.BaseActivity;
import com.zykj.yuliang.R;
import com.zykj.yuliang.view.MyCommonTitle;

public class CustomerCenterActivity extends BaseActivity {
	private MyCommonTitle myCommonTitle;
	private LinearLayout ll_score_question, ll_wx_question, ll_common_question;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_more_customer_center);

		initView();
	}

	private void initView() {
		myCommonTitle = (MyCommonTitle) findViewById(R.id.aci_mytitle);
		myCommonTitle.setTitle("客服中心");

		ll_score_question = (LinearLayout) findViewById(R.id.ll_score_question);// 积分问题
		ll_wx_question = (LinearLayout) findViewById(R.id.ll_wx_custom);// 微信客服
		ll_common_question = (LinearLayout) findViewById(R.id.ll_common_question);// 常见问题

		setListener(ll_score_question, ll_wx_question, ll_common_question);
	}

	@Override
	public void onClick(View view) {
		super.onClick(view);

		switch (view.getId()) {
		case R.id.ll_score_question:

			break;
		case R.id.ll_wx_custom:

			break;
		case R.id.ll_common_question:

			break;
		default:
			break;
		}
	}
}
