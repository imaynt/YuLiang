package com.zykj.yuliang.activity;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.loopj.android.http.RequestParams;
import com.zykj.yuliang.BaseActivity;
import com.zykj.yuliang.R;
import com.zykj.yuliang.adapter.CommonAdapter;
import com.zykj.yuliang.adapter.ViewHolder;
import com.zykj.yuliang.model.Tudi;
import com.zykj.yuliang.utils.CircleImageView;
import com.zykj.yuliang.utils.Tools;
import com.zykj.yuliang.view.MyCommonTitle;

public class ApprenticeActivity extends BaseActivity {

	private MyCommonTitle myCommonTitle;
	private LinearLayout ll_more_tudi;
	private LinearLayout ll_why_app;	//为什么要收徒
	private LinearLayout ll_more_app;  	//如何收取更多徒弟
	private CircleImageView img_tudi_avatar;
	private TextView tv_tudi_name;
	private CommonAdapter<Object> adapter;
	private JSONArray tudi_list;
	private Tudi tudi;
	private List<Object> tudier;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_apprentice);

		initView();
		requestData();
	}



	private void initView() {
		myCommonTitle = (MyCommonTitle) findViewById(R.id.aci_mytitle);
		myCommonTitle.setTitle("收徒");

		
		ll_why_app = (LinearLayout) findViewById(R.id.ll_why_app);
		ll_more_app = (LinearLayout) findViewById(R.id.ll_more_app);
		ll_more_tudi = (LinearLayout) findViewById(R.id.ll_tudi_more);
		img_tudi_avatar = (CircleImageView) findViewById(R.id.img_tudi_avatar);
		tv_tudi_name = (TextView) findViewById(R.id.tv_tudi_name);
		setListener(ll_more_tudi,ll_why_app,ll_more_app);
	}
	/**
	 * 请求服务器数据获得徒弟信息\
	 * 通过徒获得奖励金额/徒弟个数
	 */
	private void requestData() {
		RequestParams params=new RequestParams();
		

	}
	public void initializationDate(){
		tudier = tudi_list.subList(0, tudi_list.size());
		adapter=new CommonAdapter<Object>(ApprenticeActivity.this, R.layout.ui_item_tudi, null) {//null改为tudi
			
			@Override
			public void convert(ViewHolder holder, Object t) {
				final LinearLayout mLinearLayout = holder
						.getView(R.id.ly_item_tudi);
				if (Tools.M_SCREEN_WIDTH < 800) {
					LayoutParams checkboxParms = mLinearLayout
							.getLayoutParams();
					checkboxParms.width = Tools.M_SCREEN_WIDTH * 3 / 10;
					checkboxParms.height = Tools.M_SCREEN_WIDTH * 3 / 10;
				}
			}
		};
	}
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_tudi_more:
			startActivity(new Intent(ApprenticeActivity.this,
					ApprenticeContentActivity.class));
			break;
		case R.id.ll_why_app://为什么收徒
			startActivity(new Intent(ApprenticeActivity.this,
					WhyAppActivity.class));	
			break;
		case R.id.ll_more_app://怎么收更多徒弟
			startActivity(new Intent(ApprenticeActivity.this,
					MoreAppActivity.class));
			break;
		default:
			break;
		}
	}
}
