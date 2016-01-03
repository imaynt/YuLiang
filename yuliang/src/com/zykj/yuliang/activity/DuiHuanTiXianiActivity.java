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
	private String bankId, name, total_money,actual_money;//actual_money为实际扣除的钱  total_money为我的余额

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_duihuan_tixian);

		initView();

	}

	private void initView() {
		myCommonTitle = (MyCommonTitle) findViewById(R.id.aci_mytitle);
		myCommonTitle.setTitle("支付宝提现");

		ed_ID = (EditText) findViewById(R.id.ed_ID);// 账号
		ed_name = (EditText) findViewById(R.id.ed_name);// 姓名
		tv_account = (TextView) findViewById(R.id.tv_account);// 提现金额
		ll_account = (LinearLayout) findViewById(R.id.ll_account);
		ll_submit = (LinearLayout) findViewById(R.id.ll_submit);// 提交

		setListener(ll_account, ll_submit);
	}

	@Override
	public void onClick(View view) {
		super.onClick(view);
		bankId = ed_ID.getText().toString().trim();// 支付宝账号
		name = ed_name.getText().toString().trim();// 姓名
		total_money = BaseApp.getModel().getMoney();// 我的余额
		switch (view.getId()) {
		case R.id.ll_account:
			list = new ArrayList<String>();
			list.add("10元(实扣：10.5元)");
			list.add("30元(实扣：30.5元)");
			list.add("50元(实扣：50.5元)");
			list.add("100元(实扣：100.5元)");
			new PickDialog(DuiHuanTiXianiActivity.this, "请选择提现金额", list, new PickDialogListener() {

				@Override
				public void onListItemClick(int position, String string) {
					tv_account.setText(list.get(position).substring(0, string.indexOf("(") - 1));
					actual_money=list.get(position).substring(string.indexOf("：")+1, string.indexOf(")") - 1);
				}
			}).show();
			break;
		case R.id.ll_submit:
			if (StringUtil.isEmpty(bankId)) {
				Tools.toast(DuiHuanTiXianiActivity.this, "支付宝账号不能为空！");
				return;
			}
			// if(StringUtil.isEmpty(name)){
			// Tools.toast(DuiHuanTiXianiActivity.this, "姓名不能为空！");return;
			// }
			if (StringUtil.isEmpty(tv_account.getText().toString().trim())) {
				Tools.toast(DuiHuanTiXianiActivity.this, "提现金额不能为空，请选择提现金额！");
				return;
			}
			if (Float.valueOf(actual_money) > Float.valueOf(total_money)) {
				Tools.toast(DuiHuanTiXianiActivity.this, "余额不足，请重新输入！");
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
	 * 支付宝提现提交数据
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
