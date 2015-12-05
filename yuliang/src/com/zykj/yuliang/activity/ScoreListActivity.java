package com.zykj.yuliang.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.zykj.yuliang.BaseActivity;
import com.zykj.yuliang.R;
import com.zykj.yuliang.view.MyCommonTitle;

public class ScoreListActivity extends BaseActivity{
	private MyCommonTitle myCommonTitle;
	private ListView score_list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_more_score_list);
		
		initView();
		
	}
	private void initView() {
		
	}

}
