package com.zykj.yuliang.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

import com.zykj.yuliang.R;

public class LianMengActivity extends Activity implements OnClickListener {

	private ImageButton btn_lianmeng_back;		//·µ»Ø°´Å¥
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lianmeng);
		initViews();
		initClick();
		initEvents();
		
		
	}

	protected void initClick() {
		btn_lianmeng_back.setOnClickListener(this);
	}

	protected void initViews() {
		btn_lianmeng_back=(ImageButton)findViewById(R.id.btn_lianmeng_back);
	}

	protected void initEvents() {

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_lianmeng_back:
			this.finish();
			break;

		default:
			break;
		}
		
	}

}


