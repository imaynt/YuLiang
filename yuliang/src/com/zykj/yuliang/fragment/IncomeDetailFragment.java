package com.zykj.yuliang.fragment;

import java.util.ArrayList;
import java.util.List;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.zykj.yuliang.BaseApp;
import com.zykj.yuliang.adapter.IncomeAdapter;
import com.zykj.yuliang.http.EntityHandler;
import com.zykj.yuliang.http.HttpUtils;
import com.zykj.yuliang.model.Income;
import com.zykj.yuliang.view.XListView;
import com.zykj.yuliang.view.XListView.IXListViewListener;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class IncomeDetailFragment extends Fragment implements IXListViewListener {

	private static int PERPAGE = 10;// perpageĬ��ÿҳ��ʾ10����Ϣ
	private int nowpage = 1;// ��ǰ��ʾ��ҳ��
	private int mType = 1;// 1ȫ�� 2����3ѧͽ4�һ�
	private IncomeAdapter incomeAdapter;
	private List<Income> incomes = new ArrayList<Income>();
	private XListView mListView;
	private Handler mHandler;
	private RequestParams params;

	/**
	 * @param type
	 *            1ȫ�� 2����3ѧͽ4�һ�
	 * @return ʵ���������б�
	 */
	public static IncomeDetailFragment getInstance(int type) {
		IncomeDetailFragment fragment = new IncomeDetailFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("type", type);
		fragment.setArguments(bundle);
		return fragment;
		
	}

	/**
	 * ��ʼ��ҳ��
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
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
	 * ����ҳ�����
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
	 * �������������
	 */
	private void requestData() {
		if (mType == 1) {// ȫ��
			params = new RequestParams();
			params.put("userid", BaseApp.getModel().getUserid());
			params.put("nowpage", nowpage);
			params.put("perpage", PERPAGE);
			HttpUtils.allTaskList(res_getAllTaskList, params);
		} else if (mType == 2) {// ����
			params = new RequestParams();
			params.put("userid", BaseApp.getModel().getUserid());
			params.put("nowpage", nowpage);
			params.put("perpage", PERPAGE);
			HttpUtils.taskList(res_getTaskList, params);
		} else if (mType == 3) {// ѧͽ
			params = new RequestParams();
			params.put("userid", BaseApp.getModel().getUserid());
			params.put("nowpage", nowpage);
			params.put("perpage", PERPAGE);
			HttpUtils.shouTuTaskList(res_getShouTuTaskList, params);
		} else if (mType == 4) {// �һ�
			params = new RequestParams();
			params.put("userid", BaseApp.getModel().getUserid());
			params.put("nowpage", nowpage);
			params.put("perpage", PERPAGE);
			HttpUtils.duiHuanTaskList(res_getDuiHuanTaskList, params);
		}
	}

	// ȫ��
	private AsyncHttpResponseHandler res_getAllTaskList = new EntityHandler<Income>(Income.class) {

		@Override
		public void onReadSuccess(List<Income> list) {
			if (nowpage == 1) {
				incomes.clear();
			}
			incomes.addAll(list);
			incomeAdapter.notifyDataSetChanged();
		}
	};
	// ����
	private AsyncHttpResponseHandler res_getTaskList = new EntityHandler<Income>(Income.class) {

		@Override
		public void onReadSuccess(List<Income> list) {
			if (nowpage == 1) {
				incomes.clear();
			}
			incomes.addAll(list);
			incomeAdapter.notifyDataSetChanged();
		}
	};
	// ѧͽ
	private AsyncHttpResponseHandler res_getShouTuTaskList = new EntityHandler<Income>(Income.class) {

		@Override
		public void onReadSuccess(List<Income> list) {
			if (nowpage == 1) {
				incomes.clear();
			}
			incomes.addAll(list);
			incomeAdapter.notifyDataSetChanged();
		}
	};
	// �һ�
	private AsyncHttpResponseHandler res_getDuiHuanTaskList = new EntityHandler<Income>(Income.class) {

		@Override
		public void onReadSuccess(List<Income> list) {
			if (nowpage == 1) {
				incomes.clear();
			}
			incomes.addAll(list);
			incomeAdapter.notifyDataSetChanged();
		}
	};

	/**
	 * ����ˢ��
	 */
	@Override
	public void onRefresh() {
		mHandler.postDelayed(new Runnable() {

			@Override
			public void run() {
				nowpage = 1;
				requestData();
				onLoad();
			}
		}, 1000);
	}

	/**
	 * ��������
	 */
	@Override
	public void onLoadMore() {
		mHandler.postDelayed(new Runnable() {

			@Override
			public void run() {
				nowpage += 1;
				requestData();
				onLoad();
			}
		}, 1000);
	}

	/**
	 * ��������/ˢ��
	 */
	public void onLoad() {
		mListView.stopRefresh();
		mListView.stopLoadMore();
		mListView.setRefreshTime("�ո�");
	}

}
