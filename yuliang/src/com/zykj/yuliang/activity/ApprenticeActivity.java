package com.zykj.yuliang.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.loopj.android.http.RequestParams;
import com.zykj.yuliang.BaseActivity;
import com.zykj.yuliang.R;
import com.zykj.yuliang.adapter.CommonAdapter;
import com.zykj.yuliang.adapter.ViewHolder;
import com.zykj.yuliang.model.Tudi;
import com.zykj.yuliang.utils.CircleImageView;
import com.zykj.yuliang.utils.CommonUtils;
import com.zykj.yuliang.utils.Tools;
import com.zykj.yuliang.view.MyCommonTitle;

public class ApprenticeActivity extends BaseActivity {

	private MyCommonTitle myCommonTitle;
	private LinearLayout ll_more_tudi, ll_share_shoutu;
	private LinearLayout ll_why_app; // 为什么要收徒
	private LinearLayout ll_more_app; // 如何收取更多徒弟
	private CircleImageView img_tudi_avatar;
	private TextView tv_tudi_name;

	private SimpleAdapter tudiAdapter=null;
	private GridView gv_tudi;

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
		ll_share_shoutu = (LinearLayout) findViewById(R.id.ll_share_shoutu);
		setListener(ll_more_tudi, ll_why_app, ll_more_app, ll_share_shoutu);

		gv_tudi = (GridView) findViewById(R.id.gv_tudi);

//		String data[][] = {{R.drawable.touxiang, "机器猫" },
//				{R.drawable.touxiang, "米老鼠" },
//				{R.drawable.touxiang, "唐老鸭" } };
//		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
//		for (int i = 0; i < data.length; i++) {
//			Map<String, String> map = new HashMap<String, String>();
//			map.put("avatar", data[i][0]);
//			map.put("nick", data[i][1]);
//			list.add(map);
//		}
//		tudiAdapter = new SimpleAdapter(this, list, R.layout.ui_item_tudi,
//				new String[] { "avatar", "nick" }, new int[] {
//						R.id.img_tudi_avatar, R.id.tv_tudi_name });
//		gv_tudi.setAdapter(tudiAdapter);
	}

	/**
	 * 请求服务器数据获得徒弟信息\ 通过徒获得奖励金额/徒弟个数
	 */
	private void requestData() {
		RequestParams params = new RequestParams();

	}

	public void initializationDate() {
		tudier = tudi_list.subList(0, tudi_list.size());
		adapter = new CommonAdapter<Object>(ApprenticeActivity.this,
				R.layout.ui_item_tudi, null) {// null改为tudi

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
		case R.id.ll_why_app:// 为什么收徒
			startActivity(new Intent(ApprenticeActivity.this,
					WhyAppActivity.class));
			break;
		case R.id.ll_more_app:// 怎么收更多徒弟
			startActivity(new Intent(ApprenticeActivity.this,
					MoreAppActivity.class));
			break;
		case R.id.ll_share_shoutu:
			CommonUtils.showShare(this, "余粮", "余粮是一款通过下载应用广告获得积分的APP",
					"http://fir.im");
		default:
			break;
		}
	}
}
