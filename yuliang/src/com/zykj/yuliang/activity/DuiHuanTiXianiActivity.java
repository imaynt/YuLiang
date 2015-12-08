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
		myCommonTitle.setTitle("支付宝提现");
		
		ed_ID = (EditText) findViewById(R.id.ed_ID);//账号
		ed_name = (EditText) findViewById(R.id.ed_name);//姓名
		tv_account = (TextView) findViewById(R.id.tv_account);//提现金额
		ll_submit = (LinearLayout) findViewById(R.id.ll_submit);//提交

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
			list.add("10元(实扣：10.5元)");
			list.add("30元(实扣：30.5元)");
			list.add("50元(实扣：50.5元)");
			list.add("100元(实扣：101元)");
			new PickDialog(DuiHuanTiXianiActivity.this, "请选择提现金额", list, new PickDialogListener() {
				
				@Override
				public void onListItemClick(int position, String string) {
					tv_account.setText(list.get(position).substring(0, string.indexOf("(")-1));
				}
			}).show();
			break;
		case R.id.ll_submit:
			String account=tv_account.getText().toString().trim();
			if(!TextUtil.checkBankCard(bankId)){
				Tools.toast(DuiHuanTiXianiActivity.this, "银行卡卡号不正确，请核验正确输入！");return;
			}if(StringUtil.isEmpty(name)){
				Tools.toast(DuiHuanTiXianiActivity.this, "姓名不能为空！");return;
			}if(StringUtil.isEmpty(account)){
				Tools.toast(DuiHuanTiXianiActivity.this, "提现金额不能为空，请选择提现金额！");return;
			}
			RequestParams params=new RequestParams();
			params.put("", bankId);
			params.put("", name);
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
