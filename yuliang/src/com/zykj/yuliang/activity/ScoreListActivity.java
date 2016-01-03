package com.zykj.yuliang.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.Header;

import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.RequestParams;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zykj.yuliang.BaseActivity;
import com.zykj.yuliang.BaseApp;
import com.zykj.yuliang.R;
import com.zykj.yuliang.http.HttpErrorHandler;
import com.zykj.yuliang.http.HttpUtils;
import com.zykj.yuliang.http.UrlContants;
import com.zykj.yuliang.utils.CommonUtils;
import com.zykj.yuliang.utils.StringUtil;
import com.zykj.yuliang.view.MyCommonTitle;
import com.zykj.yuliang.view.RoundImageView;

public class ScoreListActivity extends BaseActivity {
	private MyCommonTitle myCommonTitle;
	private TextView tv_income, tv_id, used_days, task_income, xuetu_jl,
			tudi_number, tudi_jl, tusun_jl;
	private RoundImageView img_avatar;
	private LinearLayout btn_submit;
	private String data;
	private RequestParams params;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_more_score_list);

		initView();
		requestData();
	}

	private void initView() {
		myCommonTitle = (MyCommonTitle) findViewById(R.id.aci_mytitle);
		myCommonTitle.setTitle("成绩单");

		tv_income = (TextView) findViewById(R.id.tv_score_income);
		img_avatar = (RoundImageView) findViewById(R.id.img_score_avatar);
		tv_id = (TextView) findViewById(R.id.tv_score_id);

		used_days = (TextView) findViewById(R.id.tv_used_days);// 使用天数
		task_income = (TextView) findViewById(R.id.tv_task_shouru);// 任务收入
		xuetu_jl = (TextView) findViewById(R.id.tv_xuetu_jl);// 学徒奖励
		tudi_number = (TextView) findViewById(R.id.tv_tudi_number);// 徒弟人数
		tudi_jl = (TextView) findViewById(R.id.tv_tudi_jl);// 徒弟奖励
		tusun_jl = (TextView) findViewById(R.id.tv_tusun_jl);// 徒孙奖励

		btn_submit = (LinearLayout) findViewById(R.id.ll_shaiyishai);

		setListener(btn_submit);
	}

	private void requestData() {
		tv_income.setText(BaseApp.getModel().getMoney());
		String avatar=BaseApp.getModel().getAvatar();
		ImageLoader.getInstance().displayImage(StringUtil.toString(UrlContants.IMAGE_URL+avatar, "http://"), img_avatar);
		tv_id.setText(BaseApp.getModel().getUserid());
			params=new RequestParams();
			params.put("deviceId", BaseApp.getModel().getDeviceId());
			params.put("type", "1");//使用天数1，
			HttpUtils.getScoreList(new HttpErrorHandler() {
				
				@Override
				public void onRecevieSuccess(JSONObject json) {
					data=json.getString("datas");
						used_days.setText(data);
				}
			}, params);
			params=new RequestParams();
			params.put("deviceId", BaseApp.getModel().getDeviceId());
			params.put("type", "2");//任务收入2，
			HttpUtils.getScoreList(new HttpErrorHandler() {
				
				@Override
				public void onRecevieSuccess(JSONObject json) {
					data=json.getString("datas");
						task_income.setText(data);
				}
			}, params);
			params=new RequestParams();
			params.put("deviceId", BaseApp.getModel().getDeviceId());
			params.put("type", "3");//徒弟人数3，
			HttpUtils.getScoreList(new HttpErrorHandler() {
				
				@Override
				public void onRecevieSuccess(JSONObject json) {
					data=json.getString("datas");
						tudi_number.setText(data);
				}
			}, params);
			params=new RequestParams();
			params.put("deviceId", BaseApp.getModel().getDeviceId());
			params.put("type", "4");//徒弟奖励4
			HttpUtils.getScoreList(new HttpErrorHandler() {
				
				@Override
				public void onRecevieSuccess(JSONObject json) {
					data=json.getString("datas");
						tudi_jl.setText(data);
				}
			}, params);
	}

	@Override
	public void onClick(View view) {
		super.onClick(view);
		switch (view.getId()) {
		case R.id.ll_shaiyishai:
			CommonUtils.showShare(this, "余粮", "余粮是一款通过下载应用广告获得积分的APP", "http://fir.im");
			break;
		default:
			break;
		}
	}
}
