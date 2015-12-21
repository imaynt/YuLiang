package com.zykj.yuliang.activity;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zykj.yuliang.BaseActivity;
import com.zykj.yuliang.BaseApp;
import com.zykj.yuliang.R;
import com.zykj.yuliang.http.UrlContants;
import com.zykj.yuliang.utils.StringUtil;
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
	private TextView user_id, user_nick, user_mobile, version_code;
	private RoundImageView img_avatar;
	private AlertDialog.Builder builder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_more);

		initView();
		requestData();
	}

	private void initView() {
		myCommonTitle = (MyCommonTitle) findViewById(R.id.aci_mytitle);
		myCommonTitle.setTitle("����");

		ll_user_info = (LinearLayout) findViewById(R.id.ll_user_info);// ����
		ll_weixin = (LinearLayout) findViewById(R.id.ll_weixin);// ΢��
		ll_bind_mobile = (LinearLayout) findViewById(R.id.ll_bind_mobile);// ���ֻ�
		ll_score_list = (LinearLayout) findViewById(R.id.ll_score_list);// �ɼ���
		ll_customer_center = (LinearLayout) findViewById(R.id.ll_customer_center);// �ͷ�����
		ll_new_notice = (LinearLayout) findViewById(R.id.ll_news_notice);// ���Ź���
		ll_business_coopration = (LinearLayout) findViewById(R.id.ll_business_coopration);// �������
		ll_check_update = (LinearLayout) findViewById(R.id.ll_check_version);// ���汾
		ll_change_userId = (LinearLayout) findViewById(R.id.ll_change_userId);// �л��˺�

		user_id = (TextView) findViewById(R.id.tv_user_id);// �ǳ�
		user_nick = (TextView) findViewById(R.id.tv_user_nick);// �ǳ�
		img_avatar = (RoundImageView) findViewById(R.id.img_avatar);// ͷ��
		user_mobile = (TextView) findViewById(R.id.tv_mobile);// �ֻ���
		version_code = (TextView) findViewById(R.id.tv_version);// �汾��

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
			 * �������ݻ�� �ǳ� �� ͷ�� �� ��user_nick��img_avatar
			 */
			startActivityForResult((new Intent(MoreActivity.this,
					UserInfoActivity.class)), 22);
			break;
		case R.id.ll_weixin:
			AlertDialog.Builder builder = new Builder(MoreActivity.this);
			builder.setTitle("��ܰ��ʾ");
			builder.setMessage("���ڸ����δ����΢�Ź��ںţ��ݲ�֧�ִ˹��ܣ������½⣡");
			builder.setPositiveButton("ȷ��", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
			builder.create().show();
			// startActivity(new Intent(MoreActivity.this,
			// WeiXinActivity.class));
			break;
		case R.id.ll_bind_mobile:
			/**
			 * �������ݻ�� �ֻ��� ���� user_mobile ����Ѱ�,��mobile
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
					NewsListActivity.class));
			break;
		case R.id.ll_business_coopration:
			startActivity(new Intent(MoreActivity.this,
					BusinessCooperationActivity.class));
			break;
		case R.id.ll_check_version:
			/**
			 * �������ݻ�� �汾�� ���� version_code
			 */
			builder = new Builder(MoreActivity.this);
			builder.setTitle("�汾���¼��");
			builder.setMessage("����ǰ�Ѿ������°汾");
			builder.setPositiveButton("ȷ��", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
			builder.create().show();
			break;
		case R.id.ll_change_userId:
			builder = new Builder(MoreActivity.this);
			builder.setTitle("�л��˺�");
			builder.setMessage("�л��˺ź����е�δ���ֻ����˺Ž�����ա�ȷ�ϼ���������");
			builder.setNegativeButton("ȡ��", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
			builder.setPositiveButton("ȷ��", new OnClickListener() {

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
		switch (requestCode) {
		case 11:
			requestData();
			break;
		case 22:
			requestData();
			break;
		default:
			break;
		}
	}

	private void requestData() {
		user_id.setText(BaseApp.getModel().getUserid());
		String avatar = BaseApp.getModel().getAvatar();
		ImageLoader.getInstance().displayImage(
				StringUtil.toString(UrlContants.IMAGE_URL+avatar, "http://"), img_avatar);
		String nick = BaseApp.getModel().getUsername();
		user_nick.setText(StringUtil.isEmpty(nick) ? "�������ǳ�" : nick);
		String mobile = BaseApp.getModel().getMobile();
		user_mobile.setText(StringUtil.isEmpty(mobile) ? "����ֻ���" : mobile);
	}
}
