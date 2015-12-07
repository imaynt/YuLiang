package com.zykj.yuliang.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.zykj.yuliang.BaseActivity;
import com.zykj.yuliang.R;
import com.zykj.yuliang.utils.CommonUtils;
import com.zykj.yuliang.view.MyCommonTitle;

public class DuiHuanTiXianiActivity extends BaseActivity {
	private MyCommonTitle myCommonTitle;
	private EditText ed_ID,ed_name;
	private TextView tv_account;
	private LinearLayout ll_submit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_duihuan_tixian);

		initView();

	}

	private void initView() {
		myCommonTitle = (MyCommonTitle) findViewById(R.id.aci_mytitle);
		myCommonTitle.setTitle("支付宝提现");
		
		ed_ID = (EditText) findViewById(R.id.ed_ID);//账号
		ed_name = (EditText) findViewById(R.id.ed_name);//姓名
		tv_account = (TextView) findViewById(R.id.tv_account);//提现金额
		ll_submit = (LinearLayout) findViewById(R.id.ll_submit);//提交

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
