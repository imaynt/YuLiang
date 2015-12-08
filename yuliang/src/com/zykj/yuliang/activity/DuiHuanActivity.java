package com.zykj.yuliang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.zykj.yuliang.BaseActivity;
import com.zykj.yuliang.R;
import com.zykj.yuliang.utils.CommonUtils;
import com.zykj.yuliang.view.MyCommonTitle;

public class DuiHuanActivity extends BaseActivity {
	private MyCommonTitle myCommonTitle;
	private LinearLayout ll_chongzhi, ll_tixian, ll_duihuan;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_duihuan);

		initView();

	}

	private void initView() {
		myCommonTitle = (MyCommonTitle) findViewById(R.id.aci_mytitle);
		myCommonTitle.setTitle("¶Ò»»");
		ll_chongzhi = (LinearLayout) findViewById(R.id.ll_chongzhi);
		ll_tixian = (LinearLayout) findViewById(R.id.ll_tixian);
		ll_duihuan = (LinearLayout) findViewById(R.id.ll_duihuan);

		setListener(ll_chongzhi, ll_tixian, ll_duihuan);
	}

	@Override
	public void onClick(View view) {
		super.onClick(view);
		switch (view.getId()) {
		case R.id.ll_chongzhi:
			startActivity(new Intent(DuiHuanActivity.this, DuiHuanChongZhiActivity.class));
			break;
		case R.id.ll_tixian:
			startActivity(new Intent(DuiHuanActivity.this, DuiHuanTiXianiActivity.class));
			break;
		case R.id.ll_duihuan:
			startActivity(new Intent(DuiHuanActivity.this, DuiHuanDuiHuanActivity.class));
			break;
		default:
			break;
		}
	}
}
