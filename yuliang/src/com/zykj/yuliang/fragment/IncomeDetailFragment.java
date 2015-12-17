package com.zykj.yuliang.fragment;

import java.util.ArrayList;
import java.util.List;

import com.zykj.yuliang.R;
import com.zykj.yuliang.adapter.CommonAdapter;
import com.zykj.yuliang.adapter.IncomeAdapter;
import com.zykj.yuliang.adapter.ViewHolder;
import com.zykj.yuliang.model.Income;
import com.zykj.yuliang.utils.StringUtil;
import com.zykj.yuliang.view.XListView;
import com.zykj.yuliang.view.XListView.IXListViewListener;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class IncomeDetailFragment extends Fragment implements
		IXListViewListener {

	private static int PERPAGE = 10;// perpage默认每页显示10条信息
	private int nowpage = 1;// 当前显示的页面
	private int mType = 1;// 1全部 2任务3学徒4兑换
	private IncomeAdapter incomeAdapter;
	private List<Income> incomes = new ArrayList<Income>();
	private XListView mListView;
	private Handler mHandler;

	/**
	 * @param type
	 *            1全部 2任务3学徒4兑换
	 * @return 实例化收入列表
	 */
	public static IncomeDetailFragment getInstance(int type) {
		IncomeDetailFragment fragment = new IncomeDetailFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("type", type);
		fragment.setArguments(bundle);
		return fragment;
	}

	/**
	 * 初始化页面
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT);
		mListView = new XListView(getActivity(), null);
		mListView.setDividerHeight(0);
		mListView.setLayoutParams(params);
		mListView.setPullRefreshEnable(true);
		mListView.setPullLoadEnable(true);
		mListView.setXListViewListener(this);
		return mListView;

	}

	/**
	 * 配置页面参数
	 */
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		mType = getArguments().getInt("type");
		mHandler = new Handler();
		incomeAdapter = new IncomeAdapter(getActivity(), incomes, mType);

		mListView.setAdapter(incomeAdapter);
		requestData();

	}

	/**
	 * 请求服务器数据
	 */
	private void requestData() {
		if (mType == 1) {//全部

		} else if (mType == 2) {//任务

		} else if (mType == 3) {//学徒

		} else if (mType == 4) {//兑换

		}
	}

	/**
	 * 下拉刷新
	 */
	@Override
	public void onRefresh() {
		mHandler.postDelayed(new Runnable() {

			@Override
			public void run() {
				nowpage = 1;
				// requestData();
				onLoad();
			}
		}, 1000);
	}

	/**
	 * 上拉加载
	 */
	@Override
	public void onLoadMore() {
		mHandler.postDelayed(new Runnable() {

			@Override
			public void run() {
				nowpage += 1;
				// requestData();
				onLoad();
			}
		}, 1000);
	}

	/**
	 * 结束加载/刷新
	 */
	public void onLoad() {
		mListView.stopRefresh();
		mListView.stopLoadMore();
		mListView.setRefreshTime("刚刚");
	}

}
