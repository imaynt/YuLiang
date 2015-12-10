package com.zykj.yuliang.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.zykj.yuliang.R;

public class FirstLoginActivity extends Activity implements OnClickListener {
	
	private RelativeLayout rl_youyaoqing,rl_wuyaoqing,rl_yaoqingyes,rl_huodeyes; 	//有邀请id，无邀请id，确定按钮,获得金额
	private LinearLayout ll_yaoqingid,ll_text_t,ll_text_s;		//输入邀请id
	private EditText et_inputid;			//id输入框
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
		initViews();
		initClick();
		initEvents();
		
		
	}

	protected void initViews() {
		rl_youyaoqing=(RelativeLayout)findViewById(R.id.rl_youyaoqing);
		rl_wuyaoqing=(RelativeLayout)findViewById(R.id.rl_wuyaoqing);
		rl_yaoqingyes=(RelativeLayout)findViewById(R.id.rl_yaoqingyes);
		rl_huodeyes=(RelativeLayout)findViewById(R.id.rl_huodeyes);
		ll_yaoqingid=(LinearLayout)findViewById(R.id.ll_yaoqingid);
		ll_text_t=(LinearLayout)findViewById(R.id.ll_text_t);
		ll_text_s=(LinearLayout)findViewById(R.id.ll_text_s);
		et_inputid=(EditText)findViewById(R.id.et_inputid);
	}
	
	protected void initClick() {
		rl_youyaoqing.setOnClickListener(this);
		rl_wuyaoqing.setOnClickListener(this);
		rl_yaoqingyes.setOnClickListener(this);
		rl_huodeyes.setOnClickListener(this);
		
	}

	

	protected void initEvents() {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rl_youyaoqing:
			rl_youyaoqing.setVisibility(View.GONE);
			ll_yaoqingid.setVisibility(View.VISIBLE);
			break;
		case R.id.rl_wuyaoqing:
			rl_youyaoqing.setVisibility(View.GONE);
			rl_wuyaoqing.setVisibility(View.GONE);
			ll_text_t.setVisibility(View.VISIBLE);
			ll_text_s.setVisibility(View.VISIBLE);
			break;
		case R.id.rl_yaoqingyes:
			rl_youyaoqing.setVisibility(View.GONE);
			rl_wuyaoqing.setVisibility(View.GONE);
			ll_text_t.setVisibility(View.VISIBLE);
			ll_text_s.setVisibility(View.VISIBLE);
			break;
		case R.id.rl_huodeyes:
			this.finish();
			break;
			

		default:
			break;
		}
		
	}

}


