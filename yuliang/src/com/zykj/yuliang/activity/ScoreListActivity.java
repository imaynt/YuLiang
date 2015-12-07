package com.zykj.yuliang.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.zykj.yuliang.BaseActivity;
import com.zykj.yuliang.R;
import com.zykj.yuliang.utils.CommonUtils;
import com.zykj.yuliang.view.MyCommonTitle;

public class ScoreListActivity extends BaseActivity {
	private MyCommonTitle myCommonTitle;
	private TextView used_days, task_income, xuetu_jl, tudi_number, tudi_jl,
			tusun_jl;
	private LinearLayout btn_submit;
	private ListView score_list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_more_score_list);

		initView();

	}

	private void initView() {
		myCommonTitle = (MyCommonTitle) findViewById(R.id.aci_mytitle);
		myCommonTitle.setTitle("�ɼ���");
		
		used_days = (TextView) findViewById(R.id.tv_used_days);//ʹ������
		task_income = (TextView) findViewById(R.id.tv_task_shouru);//��������
		xuetu_jl = (TextView) findViewById(R.id.tv_xuetu_jl);//ѧͽ����
		tudi_number = (TextView) findViewById(R.id.tv_tudi_number);//ͽ������
		tudi_jl = (TextView) findViewById(R.id.tv_tudi_jl);//ͽ�ܽ���
		tusun_jl = (TextView) findViewById(R.id.tv_tusun_jl);//ͽ�ｱ��
		
		btn_submit=(LinearLayout) findViewById(R.id.ll_submit);
		
		setListener(btn_submit);
	}
	
	@Override
	public void onClick(View view) {
		super.onClick(view);
		switch (view.getId()) {
		case R.id.ll_submit:
			CommonUtils.showShare(this, "����׬Ǯ", "����׬Ǯ����׬Ǯ", "http://fir.im");
			break;
		default:
			break;
		}
	}
}
