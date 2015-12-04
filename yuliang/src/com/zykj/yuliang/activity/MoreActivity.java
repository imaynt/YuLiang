package com.zykj.yuliang.activity;

import com.zykj.yuliang.BaseActivity;
import com.zykj.yuliang.R;
import com.zykj.yuliang.view.MyCommonTitle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MoreActivity extends BaseActivity {
	private MyCommonTitle myCommonTitle;
	private LinearLayout ll_user_info, ll_weixin, ll_bind_mobile,
			ll_score_list, ll_customer_center, ll_new_notice,
			ll_business_coopration, ll_check_update, ll_change_userId;
	private TextView user_nick, user_mobile, version_code;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_more);

		initView();
	}

	private void initView() {
		myCommonTitle = (MyCommonTitle) findViewById(R.id.aci_mytitle);
		myCommonTitle.setTitle("更多");

		ll_user_info = (LinearLayout) findViewById(R.id.ll_user_info);// 资料
		ll_weixin = (LinearLayout) findViewById(R.id.ll_weixin);// 微信
		ll_bind_mobile = (LinearLayout) findViewById(R.id.ll_bind_mobile);// 绑定手机
		ll_score_list = (LinearLayout) findViewById(R.id.ll_score_list);// 成绩单
		ll_customer_center = (LinearLayout) findViewById(R.id.ll_customer_center);// 客服中心
		ll_new_notice = (LinearLayout) findViewById(R.id.ll_news_notice);// 新闻公告
		ll_business_coopration = (LinearLayout) findViewById(R.id.ll_business_coopration);// 商务合作
		ll_check_update = (LinearLayout) findViewById(R.id.ll_check_version);// 检查版本
		ll_change_userId = (LinearLayout) findViewById(R.id.ll_change_userId);// 切换账号

		user_nick = (TextView) findViewById(R.id.tv_user_nick);
		user_mobile = (TextView) findViewById(R.id.tv_mobile);
		version_code = (TextView) findViewById(R.id.tv_version);

		setListener(ll_user_info, ll_weixin, ll_bind_mobile, ll_score_list,
				ll_customer_center, ll_new_notice, ll_business_coopration,
				ll_check_update, ll_change_userId);
	}

	@Override
	public void onClick(View view) {
		super.onClick(view);
		switch (view.getId()) {
		case R.id.ll_user_info:
			startActivity(new Intent(MoreActivity.this, UserInfoActivity.class));
			break;
		case R.id.ll_weixin:
			startActivity(new Intent(MoreActivity.this, WeiXinActivity.class));
			break;
		case R.id.ll_bind_mobile:
			startActivity(new Intent(MoreActivity.this, BindMobileActivity.class));
			break;
		case R.id.ll_score_list:
			startActivity(new Intent(MoreActivity.this, ScoreListActivity.class));
			break;
		case R.id.ll_customer_center:
			startActivity(new Intent(MoreActivity.this, CustomerCenterActivity.class));
			break;
		case R.id.ll_news_notice:
			startActivity(new Intent(MoreActivity.this, NewsNoticeActivity.class));
			break;
		case R.id.ll_business_coopration:
			startActivity(new Intent(MoreActivity.this, BusinessCooperationActivity.class));
			break;
		case R.id.ll_change_userId:
			startActivity(new Intent(MoreActivity.this, UserInfoActivity.class));
			break;
		default:
			break;
		}
	}

}
