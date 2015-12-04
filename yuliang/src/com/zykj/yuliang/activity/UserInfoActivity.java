package com.zykj.yuliang.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zykj.yuliang.BaseActivity;
import com.zykj.yuliang.R;
import com.zykj.yuliang.utils.CommonUtils;
import com.zykj.yuliang.view.MyCommonTitle;

public class UserInfoActivity extends BaseActivity {
	private MyCommonTitle myCommonTitle;
	private LinearLayout ll_nick, ll_avatar, ll_sex, ll_birth, ll_prefession,
			ll_submit;
	private TextView tv_birthday;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ui_user_info);

		initView();
	}

	private void initView() {
		myCommonTitle = (MyCommonTitle) findViewById(R.id.aci_mytitle);
		myCommonTitle.setTitle("资料");

		tv_birthday = (TextView) findViewById(R.id.tv_birthday);

		ll_nick = (LinearLayout) findViewById(R.id.ll_nick);// 昵称
		ll_avatar = (LinearLayout) findViewById(R.id.ll_avatar);// 头像
		ll_sex = (LinearLayout) findViewById(R.id.ll_sex);// 性别
		ll_birth = (LinearLayout) findViewById(R.id.ll_birthday);// 生日
		ll_prefession = (LinearLayout) findViewById(R.id.ll_profession);// 职业
		ll_submit = (LinearLayout) findViewById(R.id.ll_submit);// 提交

		setListener(ll_avatar, ll_sex, ll_birth, ll_prefession, ll_submit);
	}

	@Override
	public void onClick(View view) {
		super.onClick(view);
		switch (view.getId()) {
		case R.id.ll_avatar:

			break;
		case R.id.ll_sex:

			break;
		case R.id.ll_birthday:
//			View startView=CommonUtils.showDateTimePicker(this, tv_birthday);
//			startView.findViewById(R.id.hour).setVisibility(View.GONE);
//			startView.findViewById(R.id.mins).setVisibility(View.GONE);
			break;
		case R.id.ll_profession:

			break;
		default:
			break;
		}
	}
}
