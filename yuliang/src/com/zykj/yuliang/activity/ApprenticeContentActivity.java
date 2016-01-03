package com.zykj.yuliang.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.zykj.yuliang.BaseActivity;
import com.zykj.yuliang.BaseApp;
import com.zykj.yuliang.R;
import com.zykj.yuliang.adapter.TudiInfoAdapter;
import com.zykj.yuliang.http.EntityHandler;
import com.zykj.yuliang.http.HttpUtils;
import com.zykj.yuliang.model.Tudi;
import com.zykj.yuliang.view.MyCommonTitle;
import com.zykj.yuliang.view.XListView;
import com.zykj.yuliang.view.XListView.IXListViewListener;

public class ApprenticeContentActivity extends BaseActivity implements IXListViewListener {
	private int PERPAGE = 10;
	private int nowpage = 1;
	private MyCommonTitle myCommonTitle;
	private XListView mListView;
	private TudiInfoAdapter tudiInfoAdapter;
	private List<Tudi> tudier = new ArrayList<Tudi>();
	private Tudi tudi;
	private Handler mHandler;
	private RequestParams params;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_apprentice_content);
		initViews();
		mHandler = new Handler();

	}

	protected void initViews() {
		myCommonTitle = (MyCommonTitle) findViewById(R.id.aci_mytitle);
		myCommonTitle.setTitle("最近收徒");
		
		mListView = (XListView) findViewById(R.id.list_tudi);
		
		mListView.setDividerHeight(20);
		mListView.setXListViewListener(this);
		mListView.setPullRefreshEnable(true);
		mListView.setPullLoadEnable(true);
		mListView.setSelector(new ColorDrawable(Color.TRANSPARENT));
		
		tudiInfoAdapter = new TudiInfoAdapter(this, tudier);
		mListView.setAdapter(tudiInfoAdapter);
		requestDate();

	}

	/**
	 * 请求服务器数据
	 */
	private void requestDate() {
		params = new RequestParams();
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
			tudiInfoAdapter.notifyDataSetChanged();
		}
	};

	@Override
	public void onRefresh() {
		mHandler.postDelayed(new Runnable() {

			@Override
			public void run() {
				nowpage = 1;
				requestDate();
				onLoad();
			}

		}, 1000);
	}

	@Override
	public void onLoadMore() {
		mHandler.postDelayed(new Runnable() {

			@Override
			public void run() {
				nowpage += 1;
				requestDate();
				onLoad();
			}
		}, 1000);
	}

	public void onLoad() {
		mListView.stopRefresh();
		mListView.stopLoadMore();
		mListView.setRefreshTime("刚刚");
	}
}
