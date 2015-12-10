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

	private ImageButton btn_makemoney_back;		//返回按钮
	private LinearLayout ll_lianmeng;  			//联盟任务
	private LinearLayout ll_ziliao;   			//个人资料
	private LinearLayout ll_youchang;			//有偿转发
	private LinearLayout ll_new;			//新手任务
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
		ll_lianmeng.setOnClickListener(this);
		ll_ziliao.setOnClickListener(this);
		ll_youchang.setOnClickListener(this);
		ll_new.setOnClickListener(this);
	}

	protected void initViews() {
		btn_makemoney_back = (ImageButton) findViewById(R.id.btn_makemoney_back);
		ll_lianmeng = (LinearLayout) findViewById(R.id.ll_lianmeng);
		ll_ziliao = (LinearLayout)findViewById(R.id.ll_ziliao);
		ll_youchang = (LinearLayout)findViewById(R.id.ll_youchang);
		ll_new = (LinearLayout)findViewById(R.id.ll_new);
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
		case R.id.ll_lianmeng:
			intent = new Intent(MakeMoneyActivity.this, LianMengActivity.class);
			startActivity(intent);
			break;
		case R.id.ll_ziliao:
			intent = new Intent(MakeMoneyActivity.this, ZiLiaoActivity.class);
			startActivity(intent);
			break;
		case R.id.ll_youchang:
			intent = new Intent(MakeMoneyActivity.this, YouChangActivity.class);
			startActivity(intent);
			break;
		case R.id.ll_new:
			intent = new Intent(MakeMoneyActivity.this, NewActivity.class);
			startActivityForResult(intent, 1000);
			break;

		default:
			break;
		}
		
	}
	
	/**
     * 所有的Activity对象的返回值都是由这个方法来接收
     * requestCode:    表示的是启动一个Activity时传过去的requestCode值
     * resultCode：表示的是启动后的Activity回传值时的resultCode值
     * data：表示的是启动后的Activity回传过来的Intent对象
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == 1000&&resultCode == 1001)
        {
            String result_value = data.getStringExtra("result");
            if(result_value.equals("6"))
            	ll_new.setVisibility(View.GONE);
        }
    }


}
