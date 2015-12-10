package com.zykj.yuliang.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.zykj.yuliang.R;

public class ApprenticeActivity extends Activity implements OnClickListener {

	private ImageButton btn_apprentice_back;
	private LinearLayout ll_ac_more;
	private LinearLayout ll_new_app;
	private Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_apprentice);
		initViews();
		initClick();
		initEvents();
		
		
	}

	protected void initClick() {
		btn_apprentice_back.setOnClickListener(this);
		ll_ac_more.setOnClickListener(this);
		ll_new_app.setOnClickListener(this);
	}

	protected void initViews() {
		btn_apprentice_back = (ImageButton) findViewById(R.id.btn_apprentice_back);
		ll_ac_more = (LinearLayout) findViewById(R.id.ll_ac_more);
		ll_new_app = (LinearLayout) findViewById(R.id.ll_new_app);
	}

	protected void initEvents() {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_apprentice_back:
			this.finish();
			break;
		case R.id.ll_ac_more:
		case R.id.ll_new_app:
			intent = new Intent(ApprenticeActivity.this, ApprenticeContentActivity.class);
			startActivity(intent);
			break;
			

		default:
			break;
		}
		
	}

}


