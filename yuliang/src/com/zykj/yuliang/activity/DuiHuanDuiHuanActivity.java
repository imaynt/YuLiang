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
		myCommonTitle.setTitle("�һ��Ҷһ�");
		
		tv_account = (TextView) findViewById(R.id.tv_account);//���
		ll_submit = (LinearLayout) findViewById(R.id.ll_submit);//�ύ

		setListener(tv_account, ll_submit);
	}

	@Override
	public void onClick(View view) {
		super.onClick(view);
		switch (view.getId()) {
		case R.id.tv_account:
			list=new ArrayList<String>();
			list.add("10Ԫ(�Żݣ�9.5Ԫ)");
			list.add("30Ԫ(�Żݣ�29Ԫ)");
			list.add("50Ԫ(�Żݣ�46Ԫ)");
			list.add("100Ԫ(�Żݣ�92Ԫ)");
			new PickDialog(DuiHuanDuiHuanActivity.this, "��ѡ��һ����", list, new PickDialogListener() {
				
				@Override
				public void onListItemClick(int position, String string) {
					tv_account.setText(list.get(position).substring(0,string.indexOf("(")-1));
				}
			}).show();
			break;
		case R.id.ll_submit:
			String account = tv_account.getText().toString().trim();
			if(StringUtil.isEmpty(account)){
				Tools.toast(DuiHuanDuiHuanActivity.this, "����Ϊ�գ���ѡ��һ��Ľ�");
			}
			RequestParams params = new RequestParams();
			params.put("", account);
			/**
			 * �ύ����			
			 */
			break;
		default:
			break;
		}
	}
}
