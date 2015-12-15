package com.zykj.yuliang.activity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
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
			Intent intent = new Intent(CustomerCenterActivity.this,
					JiFenActivity.class);
			startActivity(intent);
			break;
		case R.id.ll_wx_custom:
			AlertDialog.Builder builder=new Builder(CustomerCenterActivity.this);
			builder.setTitle("温馨提示");
			builder.setMessage("由于该软件未申请微信公众号，暂不支持此功能！还请谅解！");
			builder.setPositiveButton("确定", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
			builder.create().show();
//			startActivity(new Intent(CustomerCenterActivity.this,
//					WeiXinActivity.class));
			break;
		case R.id.ll_common_question:
			Intent i = new Intent(CustomerCenterActivity.this,
					CommonQuestionActivity.class);
			startActivity(i);
			break;
		default:
			break;
		}
	}
}
