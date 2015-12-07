package com.zykj.yuliang.activity;

import com.zykj.yuliang.R;
import com.zykj.yuliang.R.layout;
import com.zykj.yuliang.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;

public class MainActivity extends Activity implements OnClickListener {

	private Button btn_detail;
	private Button btn_more;
	private LinearLayout ll_makemaoney;// ×¬Ç®
	private LinearLayout ll_apprentice;// ÊÕÍ½
	private LinearLayout ll_duobao, ll_youhuiquan, ll_shengqian, ll_duihuan;// Ò»Ôª¶á±¦,ÓÅ»ÝÈ¯,Ê¡Ç®,¶Ò»»
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initViews();
		initClick();
		initEvents();

	}

	protected void initClick() {
		btn_detail.setOnClickListener(this);
		btn_more.setOnClickListener(this);
		ll_makemaoney.setOnClickListener(this);
		ll_apprentice.setOnClickListener(this);
		ll_duobao.setOnClickListener(this);
		ll_youhuiquan.setOnClickListener(this);
		ll_shengqian.setOnClickListener(this);
		ll_duihuan.setOnClickListener(this);
	}

	protected void initViews() {
		btn_detail = (Button) findViewById(R.id.btn_detail);//Ã÷Ï¸
		btn_more = (Button) findViewById(R.id.more);//¸ü¶à
		ll_makemaoney = (LinearLayout) findViewById(R.id.ll_zhuanqian);//×¬Ç®
		ll_apprentice = (LinearLayout) findViewById(R.id.ll_shoutu);//ÊÕÍ½
		ll_duobao = (LinearLayout) findViewById(R.id.ll_yiyuanduobao);//Ò»Ôª¶á±¦
		ll_youhuiquan = (LinearLayout) findViewById(R.id.ll_youhuiquan);//ÓÅ»ÝÈ¯
		ll_shengqian = (LinearLayout) findViewById(R.id.ll_shengqian);//Ê¡Ç®
		ll_duihuan = (LinearLayout) findViewById(R.id.ll_duihuan);//¶Ò»»
	}

	protected void initEvents() {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_detail:
			intent = new Intent(MainActivity.this, DetailActivity.class);
			startActivity(intent);
			break;
		case R.id.more:
			intent = new Intent(MainActivity.this, MoreActivity.class);
			startActivity(intent);
			break;
		case R.id.ll_zhuanqian:
			intent = new Intent(MainActivity.this, MakeMoneyActivity.class);
			startActivity(intent);
			break;
		case R.id.ll_shoutu:
			intent = new Intent(MainActivity.this, ApprenticeActivity.class);
			startActivity(intent);
			break;
		case R.id.ll_yiyuanduobao:
			intent = new Intent(MainActivity.this, ApprenticeActivity.class);
			startActivity(intent);
			break;
		case R.id.ll_youhuiquan:
			intent = new Intent(MainActivity.this, ApprenticeActivity.class);
			startActivity(intent);
			break;
		case R.id.ll_shengqian:
			intent = new Intent(MainActivity.this, ApprenticeActivity.class);
			startActivity(intent);
			break;
		case R.id.ll_duihuan:
			intent = new Intent(MainActivity.this, DuiHuanActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}

	}

}
