package com.zykj.yuliang.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.zykj.yuliang.R;

public class ApprenticeContentActivity extends Activity implements OnClickListener {

	private ImageButton btn_ac_back;
	private Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_apprentice_content);
		initViews();
		initClick();
		initEvents();
		
		
	}

	protected void initClick() {
		btn_ac_back.setOnClickListener(this);
	}

	protected void initViews() {
		btn_ac_back = (ImageButton) findViewById(R.id.btn_ac_back);
	}

	protected void initEvents() {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_ac_back:
			this.finish();
			break;

		default:
			break;
		}
		
	}

}


