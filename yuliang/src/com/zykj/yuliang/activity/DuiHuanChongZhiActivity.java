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

public class DuiHuanChongZhiActivity extends BaseActivity {
	private MyCommonTitle myCommonTitle;
	private EditText ed_mobile;
	private TextView tv_account;
	private LinearLayout ll_submit;
	private List<String> list;

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
			list.add("10Ԫ");
			list.add("30Ԫ(�Żݼۣ�29.5Ԫ)");
			list.add("50Ԫ(�Żݼۣ�29.15Ԫ)");
			list.add("100Ԫ(�Żݼۣ�28.5Ԫ)");
			new PickDialog(this, "��ѡ���ֵ���", list, new PickDialogListener() {

				@Override
				public void onListItemClick(int position, String string) {
					tv_account.setText(list.get(position).substring(0, string.indexOf("(")-1));
				}
			}).show();
			break;
		case R.id.ll_submit:
			String mobile=ed_mobile.getText().toString().trim();
			String account=tv_account.getText().toString().trim();
			if(!TextUtil.isMobile(mobile)){
				Tools.toast(DuiHuanChongZhiActivity.this, "�ֻ��Ÿ�ʽ���ԣ����������룡");return;
			}if(StringUtil.isEmpty(account)){
				Tools.toast(DuiHuanChongZhiActivity.this, "��ֵ����Ϊ�գ���ѡ���ֵ��");return;
			}
			RequestParams params = new RequestParams();
			params.put("", mobile);
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
