package com.zykj.yuliang.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;
import com.zykj.yuliang.BaseActivity;
import com.zykj.yuliang.R;
import com.zykj.yuliang.utils.CommonUtils;
import com.zykj.yuliang.utils.StringUtil;
import com.zykj.yuliang.utils.TextUtil;
import com.zykj.yuliang.utils.Tools;
import com.zykj.yuliang.view.MyCommonTitle;
import com.zykj.yuliang.view.PickDialog;
import com.zykj.yuliang.view.PickDialog.PickDialogListener;

public class DuiHuanTiXianiActivity extends BaseActivity {
	private MyCommonTitle myCommonTitle;
	private EditText ed_ID,ed_name;
	private TextView tv_account;
	private LinearLayout ll_submit;
	private List<String> list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_duihuan_tixian);

		initView();

	}

	private void initView() {
		myCommonTitle = (MyCommonTitle) findViewById(R.id.aci_mytitle);
		myCommonTitle.setTitle("֧��������");
		
		ed_ID = (EditText) findViewById(R.id.ed_ID);//�˺�
		ed_name = (EditText) findViewById(R.id.ed_name);//����
		tv_account = (TextView) findViewById(R.id.tv_account);//���ֽ��
		ll_submit = (LinearLayout) findViewById(R.id.ll_submit);//�ύ

		setListener(tv_account, ll_submit);
	}

	@Override
	public void onClick(View view) {
		super.onClick(view);
		String bankId=ed_ID.getText().toString().trim();
		String name=ed_name.getText().toString().trim();
		switch (view.getId()) {
		case R.id.tv_account:
			list=new ArrayList<String>();
			list.add("10Ԫ(ʵ�ۣ�10.5Ԫ)");
			list.add("30Ԫ(ʵ�ۣ�30.5Ԫ)");
			list.add("50Ԫ(ʵ�ۣ�50.5Ԫ)");
			list.add("100Ԫ(ʵ�ۣ�101Ԫ)");
			new PickDialog(DuiHuanTiXianiActivity.this, "��ѡ�����ֽ��", list, new PickDialogListener() {
				
				@Override
				public void onListItemClick(int position, String string) {
					tv_account.setText(list.get(position).substring(0, string.indexOf("(")-1));
				}
			}).show();
			break;
		case R.id.ll_submit:
			String account=tv_account.getText().toString().trim();
			if(!TextUtil.checkBankCard(bankId)){
				Tools.toast(DuiHuanTiXianiActivity.this, "���п����Ų���ȷ���������ȷ���룡");return;
			}if(StringUtil.isEmpty(name)){
				Tools.toast(DuiHuanTiXianiActivity.this, "��������Ϊ�գ�");return;
			}if(StringUtil.isEmpty(account)){
				Tools.toast(DuiHuanTiXianiActivity.this, "���ֽ���Ϊ�գ���ѡ�����ֽ�");return;
			}
			RequestParams params=new RequestParams();
			params.put("", bankId);
			params.put("", name);
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
