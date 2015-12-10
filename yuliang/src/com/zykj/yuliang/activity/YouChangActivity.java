package com.zykj.yuliang.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.zykj.yuliang.R;

public class YouChangActivity extends Activity implements OnClickListener {

	private ImageButton btn_youchang_back; // ·µ»Ø°´Å¥
	private LinearLayout ll_item;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_youchang);
		initViews();
		initClick();
		initEvents();

	}

	protected void initClick() {
		btn_youchang_back.setOnClickListener(this);
		ll_item.setOnClickListener(this);
	}

	protected void initViews() {
		btn_youchang_back = (ImageButton) findViewById(R.id.btn_youchang_back);
		ll_item = (LinearLayout) findViewById(R.id.ll_yiyuan);
	}

	protected void initEvents() {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_youchang_back:
			this.finish();
			break;
		case R.id.ll_yiyuan:
			startActivity(new Intent(YouChangActivity.this, YouChangDetailsActivity.class));
			break;
		default:
			break;
		}

	}

}
