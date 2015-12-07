package com.zykj.yuliang.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.zykj.yuliang.R;

public class MakeMoneyActivity extends Activity implements OnClickListener{

	private ImageButton btn_makemoney_back;
	private Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_makemoney);
		initViews();
		initClick();
		initEvents();
		
		
	}

	protected void initClick() {
		btn_makemoney_back.setOnClickListener(this);
	}

	protected void initViews() {
		btn_makemoney_back = (ImageButton) findViewById(R.id.btn_makemoney_back);
	}

	protected void initEvents() {

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_makemoney_back:
			this.finish();
			break;

		default:
			break;
		}
		
	}

}
