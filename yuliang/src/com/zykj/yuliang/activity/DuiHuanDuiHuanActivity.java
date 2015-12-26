package com.zykj.yuliang.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;
import com.zykj.yuliang.BaseActivity;
import com.zykj.yuliang.R;
import com.zykj.yuliang.utils.CommonUtils;
import com.zykj.yuliang.utils.StringUtil;
import com.zykj.yuliang.utils.Tools;
import com.zykj.yuliang.view.MyCommonTitle;
import com.zykj.yuliang.view.PickDialog;
import com.zykj.yuliang.view.PickDialog.PickDialogListener;

public class DuiHuanDuiHuanActivity extends BaseActivity {
	private MyCommonTitle myCommonTitle;
	private LinearLayout ll_submit;
	private TextView tv_account;
	private List<String> list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_duihuan_duihuan);

		initView();

	}

	private void initView() {
		myCommonTitle = (MyCommonTitle) findViewById(R.id.aci_mytitle);
		myCommonTitle.setTitle("兑换币兑换");
		
		tv_account = (TextView) findViewById(R.id.tv_account);//金额
		ll_submit = (LinearLayout) findViewById(R.id.ll_submit);//提交

		setListener(tv_account, ll_submit);
	}

	@Override
	public void onClick(View view) {
		super.onClick(view);
		switch (view.getId()) {
		case R.id.tv_account:
			list=new ArrayList<String>();
			list.add("10元(优惠：9.5元)");
			list.add("30元(优惠：29元)");
			list.add("50元(优惠：46元)");
			list.add("100元(优惠：92元)");
			new PickDialog(DuiHuanDuiHuanActivity.this, "请选择兑换金额", list, new PickDialogListener() {
				
				@Override
				public void onListItemClick(int position, String string) {
					tv_account.setText(list.get(position).substring(0,string.indexOf("(")-1));
				}
			}).show();
			break;
		case R.id.ll_submit:
			String account = tv_account.getText().toString().trim();
			if(StringUtil.isEmpty(account)){
				Tools.toast(DuiHuanDuiHuanActivity.this, "金额不能为空，请选择兑换的金额！");
			}
			RequestParams params = new RequestParams();
			params.put("", account);
			/**
			 * 提交数据			
			 */
			break;
		default:
			break;
		}
	}
}
