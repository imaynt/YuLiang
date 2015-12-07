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
	private LinearLayout ll_makemaoney;
	private LinearLayout ll_apprentice;
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
	}

	protected void initViews() {
		btn_detail = (Button) findViewById(R.id.btn_detail);
		ll_makemaoney = (LinearLayout)findViewById(R.id.ll_makemoney);
		ll_apprentice = (LinearLayout)findViewById(R.id.ll_apprentice);
		btn_more = (Button) findViewById(R.id.more);
	}

	protected void initEvents() {

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_detail:
			intent = new Intent(MainActivity.this, DetailActivity.class);
			startActivity(intent);
			break;
		case R.id.ll_makemoney:
			intent = new Intent(MainActivity.this, MakeMoneyActivity.class);
			startActivity(intent);
			break;
		case R.id.ll_apprentice:
			intent = new Intent(MainActivity.this, ApprenticeActivity.class);
			startActivity(intent);
			break;
		case R.id.more:
			intent = new Intent(MainActivity.this, MoreActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
		
	}

}
