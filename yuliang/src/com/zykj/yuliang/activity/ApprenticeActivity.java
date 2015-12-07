package com.zykj.yuliang.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.zykj.yuliang.R;

public class ApprenticeActivity extends Activity implements OnClickListener {

	private ImageButton btn_apprentice_back;
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
	}

	protected void initViews() {
		btn_apprentice_back = (ImageButton) findViewById(R.id.btn_apprentice_back);
	}

	protected void initEvents() {

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_apprentice_back:
			this.finish();
			break;

		default:
			break;
		}
		
	}

}


