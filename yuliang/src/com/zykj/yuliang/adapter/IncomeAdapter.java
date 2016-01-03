package com.zykj.yuliang.adapter;

import java.util.List;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zykj.yuliang.BaseApp;
import com.zykj.yuliang.R;
import com.zykj.yuliang.http.UrlContants;
import com.zykj.yuliang.model.AppModel;
import com.zykj.yuliang.model.Income;
import com.zykj.yuliang.utils.CircleImageView;
import com.zykj.yuliang.utils.StringUtil;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class IncomeAdapter extends BaseAdapter {

	private static final int TYPE_COUNT = 2;// item类型的总数
	private static final int TYPE_TASK = 0;// 任务收入
	private static final int TYPE_XUETU = 1;// 收徒收入
	private LayoutInflater inflater;
	private List<Income> incomes;
	private int mType;

	public IncomeAdapter(Context ctx, List<Income> incomes, int type) {
		inflater = LayoutInflater.from(ctx);
		this.mType = type;// 1全部 2任务3学徒4兑换
		this.incomes = incomes;
	}

	@Override
	public int getCount() {
		return incomes.size();
	}

	@Override
	public Object getItem(int position) {
		return incomes.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	// @Override
	// public int getItemViewType(int position) {
	// if (!StringUtil.isEmpty(incomes.get(position).getIncomeNum())) {
	// return TYPE_TASK;
	// } else {
	// return TYPE_XUETU;
	// }
	// }
	//
	// @Override
	// public int getViewTypeCount() {
	// return TYPE_COUNT;
	// }

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder1 holder = null;
		if (convertView == null) {
			holder = new ViewHolder1();
			convertView = inflater.inflate(R.layout.ui_item_income, parent,
					false);
			holder.rv_avatar = (CircleImageView) convertView
					.findViewById(R.id.cimg_avatar);
			holder.aci_incomerId = (TextView) convertView
					.findViewById(R.id.tv_income_userId);
			holder.aci_incomeDate = (TextView) convertView
					.findViewById(R.id.tv_income_date);
			holder.aci_incomeCotent = (TextView) convertView
					.findViewById(R.id.tv_income_content);
			holder.aci_xuetuCotent = (TextView) convertView
					.findViewById(R.id.tv_xuetu_content);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder1) convertView.getTag();
		}
		Income income = incomes.get(position);
		ImageLoader.getInstance().displayImage(
				StringUtil.toString(UrlContants.IMAGE_URL + BaseApp.getModel().getAvatar(), "http://"),
				holder.rv_avatar);
		// if(mType==2){
		// holder.aci_incomerId.setText("我");
		// }
		holder.aci_incomerId.setText(income.getParentid().equals(BaseApp.getModel().getUserid())?income.getUid():"我");// 任务显示我的ID,学徒显示徒弟的ID
//		holder.aci_incomerId.setText(income.getUid());// 任务显示我的ID,学徒显示徒弟的ID
		holder.aci_incomeDate.setText(income.getTimestamp());
		// 任务收入的显示和学徒的显示不同
		holder.aci_incomeCotent.setText("完成：" + income.getDescription() + "，" + "赚了"
				+ Html.fromHtml("<font color=#FF0000>" + income.getMoney() + "</font>") + "元");
		
		holder.aci_xuetuCotent.setText(income.getUid()+"成为您的徒弟");//这个为收徒的内容....................................
		
		holder.aci_xuetuCotent.setVisibility(mType == 3 || mType == 1 ? View.VISIBLE : View.GONE);// 点击学徒,显示的内容
		holder.aci_incomeCotent.setVisibility(mType == 2 || mType == 1 || mType == 4 ? View.VISIBLE : View.GONE);// 点击任务显示的内容

		

		return convertView;
	}

	class ViewHolder1 {
		CircleImageView rv_avatar;
		TextView aci_incomerId;
		TextView aci_incomeDate;
		TextView aci_incomeCotent;
		TextView aci_incomeNum;
		TextView aci_xuetuCotent;
		
	}

}
