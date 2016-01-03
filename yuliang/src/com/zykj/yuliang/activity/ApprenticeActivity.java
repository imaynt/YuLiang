package com.zykj.yuliang.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.zykj.yuliang.BaseActivity;
import com.zykj.yuliang.BaseApp;
import com.zykj.yuliang.R;
import com.zykj.yuliang.adapter.CommonAdapter;
import com.zykj.yuliang.adapter.TudiAdapter;
import com.zykj.yuliang.adapter.ViewHolder;
import com.zykj.yuliang.http.EntityHandler;
import com.zykj.yuliang.http.HttpErrorHandler;
import com.zykj.yuliang.http.HttpUtils;
import com.zykj.yuliang.model.Tudi;
import com.zykj.yuliang.utils.CircleImageView;
import com.zykj.yuliang.utils.CommonUtils;
import com.zykj.yuliang.utils.StringUtil;
import com.zykj.yuliang.utils.Tools;
import com.zykj.yuliang.view.MyCommonTitle;

public class ApprenticeActivity extends BaseActivity {

	private int PERPAGE = 10;
	private int nowpage = 1;
	private MyCommonTitle myCommonTitle;
	private LinearLayout ll_more_tudi, ll_share_shoutu;// 更多徒弟 分享好有收徒
	private LinearLayout ll_why_app; // 为什么要收徒
	private LinearLayout ll_more_app; // 如何收取更多徒弟
	private CircleImageView img_tudi_avatar;// 徒弟头像
	private TextView tv_tudi_name;// 徒弟昵称
	private TextView tv_tudi_num, tv_income;// 徒弟个数 师傅从徒弟获得的提成
	private RequestParams params;
	// private SimpleAdapter tudiAdapter=null;
	private GridView gv_tudi;
	private TudiAdapter tudiAdapter;
	// private CommonAdapter<Object> adapter;
	private JSONArray tudi_list;
	private Tudi tudi;
	private List<Tudi> tudier = new ArrayList<Tudi>();

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
		ll_more_tudi = (LinearLayout) findViewById(R.id.ll_tudi_more);// 更多徒弟
		img_tudi_avatar = (CircleImageView) findViewById(R.id.img_tudi_avatar);// 徒弟头像
		tv_tudi_name = (TextView) findViewById(R.id.tv_tudi_name);// 徒弟昵称
		tv_tudi_num = (TextView) findViewById(R.id.tv_tudi_num);// 徒弟个数
		tv_income = (TextView) findViewById(R.id.tv_income);// 师傅从徒弟获得的提成
		ll_share_shoutu = (LinearLayout) findViewById(R.id.ll_share_shoutu);// 分享好有收徒
		setListener(ll_more_tudi, ll_why_app, ll_more_app, ll_share_shoutu);

		gv_tudi = (GridView) findViewById(R.id.gv_tudi);

		// int[]pic={R.drawable.touxiang,R.drawable.touxiang,R.drawable.touxiang};
		// String data[] = { "机器猫" ,"米老鼠" ,"唐老鸭" };
		// List<Map<String, String>> list = new ArrayList<Map<String,
		// String>>();
		// for (int i = 0; i < data.length; i++) {
		// Map<String, String> map = new HashMap<String, String>();
		// map.put("avatar", StringUtil.toString(pic[i]));
		// map.put("nick", data[i]);
		// list.add(map);
		// }
		// tudiAdapter = new SimpleAdapter(this, list, R.layout.ui_item_tudi,
		// new String[] { "avatar", "nick" }, new int[] {
		// R.id.img_tudi_avatar, R.id.tv_tudi_name });
		tudiAdapter = new TudiAdapter(this, tudier);
		gv_tudi.setAdapter(tudiAdapter);
		gv_tudi.setSelector(new ColorDrawable(Color.TRANSPARENT));// 去掉点击产生的背景色
		RequestParams params = new RequestParams();

		params.put("deviceId", BaseApp.getModel().getDeviceId());
		params.put("userid", BaseApp.getModel().getUserid());
		params.put("nowpage", nowpage);
		params.put("perpage", PERPAGE);
		HttpUtils.getChildrenList(res_getChildrenList, params);
	}

	AsyncHttpResponseHandler res_getChildrenList = new EntityHandler<Tudi>(Tudi.class) {

		@Override
		public void onReadSuccess(List<Tudi> list) {
			if (nowpage == 1) {
				tudier.clear();
			}
			tudier.addAll(list);
			tudiAdapter.notifyDataSetChanged();
		}

	};

	/**
	 * 请求服务器数据获得徒弟信息\ 通过徒获得奖励金额/徒弟个数
	 */
	private void requestData() {
		/**
		 * 徒弟个数
		 */
		params = new RequestParams();
		params.put("deviceId", BaseApp.getModel().getDeviceId());
		params.put("type", "3");// 徒弟人数3，
		HttpUtils.getScoreList(new HttpErrorHandler() {

			@Override
			public void onRecevieSuccess(JSONObject json) {
				String data = json.getString("datas");
				tv_tudi_num.setText(data+"个");
			}
		}, params);
		/**
		 * 师傅从徒弟那获得的提成
		 */
		params = new RequestParams();
		params.put("userid", BaseApp.getModel().getUserid());
		HttpUtils.getIncomeFromTudi(new HttpErrorHandler() {

			@Override
			public void onRecevieSuccess(JSONObject json) {
				String data = json.getString("datas");
				tv_income.setText(data+"元");
			}
		}, params);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_tudi_more:
			startActivity(new Intent(ApprenticeActivity.this, ApprenticeContentActivity.class));
			break;
		case R.id.ll_why_app:// 为什么收徒
			startActivity(new Intent(ApprenticeActivity.this, WhyAppActivity.class));
			break;
		case R.id.ll_more_app:// 怎么收更多徒弟
			startActivity(new Intent(ApprenticeActivity.this, MoreAppActivity.class));
			break;
		case R.id.ll_share_shoutu:
			CommonUtils.showShare(this, "余粮", "余粮是一款通过下载应用广告获得积分的APP", "http://fir.im");
		default:
			break;
		}
	}
}
