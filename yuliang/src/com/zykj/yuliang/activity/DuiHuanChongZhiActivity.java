package com.zykj.yuliang.activity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.RequestParams;
import com.zykj.yuliang.BaseActivity;
import com.zykj.yuliang.BaseApp;
import com.zykj.yuliang.R;
import com.zykj.yuliang.http.HttpErrorHandler;
import com.zykj.yuliang.http.HttpUtils;
import com.zykj.yuliang.utils.CommonUtils;
import com.zykj.yuliang.utils.StringUtil;
import com.zykj.yuliang.utils.TextUtil;
import com.zykj.yuliang.utils.Tools;
import com.zykj.yuliang.view.MyCommonTitle;
import com.zykj.yuliang.view.PickDialog;
import com.zykj.yuliang.view.PickDialog.PickDialogListener;

public class DuiHuanChongZhiActivity extends BaseActivity {
	private MyCommonTitle myCommonTitle;
	private EditText ed_mobile;
	private TextView tv_account;
	private LinearLayout ll_submit;
	private List<String> list;
	private String mobile, actual_money,total_money;//actual_moneyΪʵ�ʿ۳���Ǯ  total_moneyΪ�ҵ����

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_duihuan_chongzhi);

		initView();

	}

	private void initView() {
		myCommonTitle = (MyCommonTitle) findViewById(R.id.aci_mytitle);
		myCommonTitle.setTitle("���ѳ�ֵ");
		ed_mobile = (EditText) findViewById(R.id.ed_mobile);// �ֻ���
		tv_account = (TextView) findViewById(R.id.tv_account);// ���
		ll_submit = (LinearLayout) findViewById(R.id.ll_submit);// �ύ

		setListener(tv_account, ll_submit);
	}

	@Override
	public void onClick(View view) {
		super.onClick(view);
		switch (view.getId()) {
		case R.id.tv_account:
			list = new ArrayList<String>();
			list.add("10Ԫ(�Żݼۣ�10Ԫ)");
			list.add("30Ԫ(�Żݼۣ�29Ԫ)");
			list.add("50Ԫ(�Żݼۣ�48Ԫ)");
			list.add("100Ԫ(�Żݼۣ�95Ԫ)");
			new PickDialog(this, "��ѡ���ֵ���", list, new PickDialogListener() {

				@Override
				public void onListItemClick(int position, String string) {
					tv_account.setText(list.get(position).substring(0, string.indexOf("(") - 1));//�ؼ���ʾ�Ľ��
					actual_money=list.get(position).substring(string.indexOf("��")+1, string.indexOf(")") - 1);//���ҵ����ʵ�ʿ۳��Ľ��
				}
			}).show();
			
			break;
		case R.id.ll_submit:
			mobile = ed_mobile.getText().toString().trim();
			total_money=BaseApp.getModel().getMoney();
			if (!TextUtil.isMobile(mobile)) {
				Tools.toast(DuiHuanChongZhiActivity.this, "�ֻ��Ÿ�ʽ���ԣ����������룡");
				return;
			}
			if (StringUtil.isEmpty(tv_account.getText().toString().trim())) {
				Tools.toast(DuiHuanChongZhiActivity.this, "��ֵ����Ϊ�գ���ѡ���ֵ��");
				return;
			}
			if (Float.valueOf(actual_money) > Float.valueOf(total_money)) {
				Tools.toast(DuiHuanChongZhiActivity.this, "��ܰ��ʾ���������㣡");
				return;
			}
			Float account = Float.valueOf(tv_account.getText().toString().trim());
//			actual_money=;
			postMobileFees(mobile, Float.valueOf(actual_money));
			break;
		default:
			break;
		}
	}

	/**
	 * �ֻ���ֵ�ύ����
	 */
	private void postMobileFees(String mobile, final Float account) {
		RequestParams params = new RequestParams();
		params.put("uid", BaseApp.getModel().getUserid());
		params.put("tel", mobile);
		params.put("money", account);
		HttpUtils.postMobileFees(new HttpErrorHandler() {

			@Override
			public void onRecevieSuccess(JSONObject json) {
				Tools.toast(DuiHuanChongZhiActivity.this, json.getString("message"));
				BaseApp.getModel().setMoney(StringUtil.toString(Float.valueOf(total_money) - account));
				finish();
			}
		}, params);
	}
}
