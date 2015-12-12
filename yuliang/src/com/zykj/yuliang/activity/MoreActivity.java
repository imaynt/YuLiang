package com.zykj.yuliang.activity;

import com.zykj.yuliang.BaseActivity;
import com.zykj.yuliang.R;
import com.zykj.yuliang.view.MyCommonTitle;
import com.zykj.yuliang.view.RoundImageView;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
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
	private RoundImageView img_avatar;
	private AlertDialog.Builder builder;

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

		user_nick = (TextView) findViewById(R.id.tv_user_nick);// 昵称
		img_avatar = (RoundImageView) findViewById(R.id.img_avatar);// 头像
		user_mobile = (TextView) findViewById(R.id.tv_mobile);// 手机号
		version_code = (TextView) findViewById(R.id.tv_version);// 版本号

		setListener(ll_user_info, ll_weixin, ll_bind_mobile, ll_score_list,
				ll_customer_center, ll_new_notice, ll_business_coopration,
				ll_check_update, ll_change_userId);
	}

	@Override
	public void onClick(View view) {
		super.onClick(view);
		switch (view.getId()) {
		case R.id.ll_user_info:
			/**
			 * 请求数据获得 昵称 和 头像 传 给user_nick和img_avatar
			 */
			startActivityForResult((new Intent(MoreActivity.this, UserInfoActivity.class)),22);
			break;
		case R.id.ll_weixin:
			startActivity(new Intent(MoreActivity.this, WeiXinActivity.class));
			break;
		case R.id.ll_bind_mobile:
			/**
			 * 请求数据获得 手机号 传给 user_mobile
			 */
			String mobile = user_mobile.getText().toString().trim();
			startActivityForResult(new Intent(MoreActivity.this,
					BindMobileActivity.class).putExtra("mobile", mobile), 11);
			break;
		case R.id.ll_score_list:
			startActivity(new Intent(MoreActivity.this, ScoreListActivity.class));
			break;
		case R.id.ll_customer_center:
			startActivity(new Intent(MoreActivity.this,
					CustomerCenterActivity.class));
			break;
		case R.id.ll_news_notice:
			startActivity(new Intent(MoreActivity.this,
					NewsNoticeActivity.class));
			break;
		case R.id.ll_business_coopration:
			startActivity(new Intent(MoreActivity.this,
					BusinessCooperationActivity.class));
			break;
		case R.id.ll_check_version:
			/**
			 * 请求数据获得 版本号 传给 version_code
			 */
			builder = new Builder(MoreActivity.this);
			builder.setTitle("版本更新检查");
			builder.setMessage("您当前已经是最新版本");
			builder.setPositiveButton("确定", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
			builder.create().show();
			break;
		case R.id.ll_change_userId:
			builder = new Builder(MoreActivity.this);
			builder.setTitle("切换账号");
			builder.setMessage("切换账号后，现有的未绑定手机的账号将被清空。确认继续操作？");
			builder.setNegativeButton("取消", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
			builder.setPositiveButton("确定", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {

					startActivity(new Intent(MoreActivity.this,
							BindMobileActivity.class));

				}
			});
			builder.create().show();
			break;
		
		default:
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case 11:
			if(data!=null){
			//	.MoreActivity.....................
			}
			break;
		case 22:
			if(data!=null){
			//	.UserInfoActivity.....................
			}
			break;
		default:
			break;
		}
	}
}
