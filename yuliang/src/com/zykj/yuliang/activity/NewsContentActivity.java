package com.zykj.yuliang.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.zykj.yuliang.R;

public class NewsContentActivity extends Activity implements OnClickListener {

	private ImageButton btn_nc_back;
	private Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_content);
		initViews();
		initClick();
		initEvents();
		
		
	}

	protected void initClick() {
		btn_nc_back.setOnClickListener(this);
	}

	protected void initViews() {
		btn_nc_back = (ImageButton) findViewById(R.id.btn_nc_back);
	}

	protected void initEvents() {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_nc_back:
			this.finish();
			break;
			

		default:
			break;
		}
		
	}

}


