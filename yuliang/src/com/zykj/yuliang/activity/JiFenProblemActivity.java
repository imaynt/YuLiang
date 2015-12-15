package com.zykj.yuliang.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zykj.yuliang.BaseActivity;
import com.zykj.yuliang.R;

public class JiFenProblemActivity extends BaseActivity{
	
	private EditText et_jifen_id,et_jifen_name,et_jifen_renwu,et_jifen_data;//余粮id，联盟商名称，任务名称，日期
	private RelativeLayout rl_jifen_click;	//提交按钮
	private ImageView iv_jifen_back;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jifen_problem);
		initViews();

	}
	

	private void initViews() {
		et_jifen_id=(EditText)findViewById(R.id.et_jifen_id);
		et_jifen_name=(EditText)findViewById(R.id.et_jifen_name);
		et_jifen_renwu=(EditText)findViewById(R.id.et_jifen_renwu);
		et_jifen_data=(EditText)findViewById(R.id.et_jifen_data);
		rl_jifen_click=(RelativeLayout)findViewById(R.id.rl_jifen_click);
		iv_jifen_back=(ImageView)findViewById(R.id.btn_jifen_back);
		setListener(rl_jifen_click,iv_jifen_back);
	}

	@Override
	public void onClick(View view) {
		super.onClick(view);
		switch (view.getId()) {
		case R.id.rl_jifen_click:

			break;
		case R.id.btn_jifen_back:
			this.finish();
			break;
		default:
			break;
		}
	}
}