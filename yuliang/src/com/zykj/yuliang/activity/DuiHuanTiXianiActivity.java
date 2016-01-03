package com.zykj.yuliang.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.RequestParams;
import com.zykj.yuliang.BaseActivity;
import com.zykj.yuliang.BaseApp;
import com.zykj.yuliang.R;
import com.zykj.yuliang.http.HttpErrorHandler;
import com.zykj.yuliang.http.HttpUtils;
import com.zykj.yuliang.utils.StringUtil;
import com.zykj.yuliang.utils.Tools;
import com.zykj.yuliang.view.MyCommonTitle;
import com.zykj.yuliang.view.PickDialog;
import com.zykj.yuliang.view.PickDialog.PickDialogListener;

public class DuiHuanTiXianiActivity extends BaseActivity {
	private MyCommonTitle myCommonTitle;
	private EditText ed_ID, ed_name;
	private TextView tv_account;
	private LinearLayout ll_account, ll_submit;
	private List<String> list;
	private String bankId, name, total_money,actual_money;//actual_moneyΪʵ�ʿ۳���Ǯ  total_moneyΪ�ҵ����

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_duihuan_tixian);

		initView();

	}

	private void initView() {
		myCommonTitle = (MyCommonTitle) findViewById(R.id.aci_mytitle);
		myCommonTitle.setTitle("֧��������");

		ed_ID = (EditText) findViewById(R.id.ed_ID);// �˺�
		ed_name = (EditText) findViewById(R.id.ed_name);// ����
		tv_account = (TextView) findViewById(R.id.tv_account);// ���ֽ��
		ll_account = (LinearLayout) findViewById(R.id.ll_account);
		ll_submit = (LinearLayout) findViewById(R.id.ll_submit);// �ύ

		setListener(ll_account, ll_submit);
	}

	@Override
	public void onClick(View view) {
		super.onClick(view);
		bankId = ed_ID.getText().toString().trim();// ֧�����˺�
		name = ed_name.getText().toString().trim();// ����
		total_money = BaseApp.getModel().getMoney();// �ҵ����
		switch (view.getId()) {
		case R.id.ll_account:
			list = new ArrayList<String>();
			list.add("10Ԫ(ʵ�ۣ�10.5Ԫ)");
			list.add("30Ԫ(ʵ�ۣ�30.5Ԫ)");
			list.add("50Ԫ(ʵ�ۣ�50.5Ԫ)");
			list.add("100Ԫ(ʵ�ۣ�100.5Ԫ)");
			new PickDialog(DuiHuanTiXianiActivity.this, "��ѡ�����ֽ��", list, new PickDialogListener() {

				@Override
				public void onListItemClick(int position, String string) {
					tv_account.setText(list.get(position).substring(0, string.indexOf("(") - 1));
					actual_money=list.get(position).substring(string.indexOf("��")+1, string.indexOf(")") - 1);
				}
			}).show();
			break;
		case R.id.ll_submit:
			if (StringUtil.isEmpty(bankId)) {
				Tools.toast(DuiHuanTiXianiActivity.this, "֧�����˺Ų���Ϊ�գ�");
				return;
			}
			// if(StringUtil.isEmpty(name)){
			// Tools.toast(DuiHuanTiXianiActivity.this, "��������Ϊ�գ�");return;
			// }
			if (StringUtil.isEmpty(tv_account.getText().toString().trim())) {
				Tools.toast(DuiHuanTiXianiActivity.this, "���ֽ���Ϊ�գ���ѡ�����ֽ�");
				return;
			}
			if (Float.valueOf(actual_money) > Float.valueOf(total_money)) {
				Tools.toast(DuiHuanTiXianiActivity.this, "���㣬���������룡");
				return;
			}
			Float account = Float.valueOf(actual_money);
			postTiXian(account, bankId);

			break;
		default:
			break;
		}
	}

	/**
	 * ֧���������ύ����
	 */
	private void postTiXian(final Float account, String bankId) {
		RequestParams params = new RequestParams();
		params.put("uid", BaseApp.getModel().getUserid());
		params.put("alipay", bankId);
		// params.put("", name);
		params.put("money", account);
		HttpUtils.postTiXian(new HttpErrorHandler() {

			@Override
			public void onRecevieSuccess(JSONObject json) {
				Tools.toast(DuiHuanTiXianiActivity.this, json.getString("message"));
				BaseApp.getModel().setMoney(StringUtil.toString(Float.valueOf(total_money) - account));
				finish();
			}
		}, params);
	}
}
