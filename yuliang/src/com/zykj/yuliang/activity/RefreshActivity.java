package com.zykj.yuliang.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.zykj.yuliang.BaseActivity;
import com.zykj.yuliang.R;

public class RefreshActivity extends BaseActivity {

	private Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_refresh);
		initViews();
		initClick();
		initEvents();
	
		
	}

	protected void initClick() {
	}

	protected void initViews() {
	}

	protected void initEvents() {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
//		case R.id.btn_apprentice_back:
//			this.finish();
//			break;

		default:
			break;
		}
		
	}

}

