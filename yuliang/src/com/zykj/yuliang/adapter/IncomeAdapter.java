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

	private static final int TYPE_COUNT = 2;// item���͵�����
	private static final int TYPE_TASK = 0;// ��������
	private static final int TYPE_XUETU = 1;// ��ͽ����
	private LayoutInflater inflater;
	private List<Income> incomes;
	private int mType;

	public IncomeAdapter(Context ctx, List<Income> incomes, int type) {
		inflater = LayoutInflater.from(ctx);
		this.mType = type;// 1ȫ�� 2����3ѧͽ4�һ�
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
		// holder.aci_incomerId.setText("��");
		// }
		holder.aci_incomerId.setText(income.getParentid().equals(BaseApp.getModel().getUserid())?income.getUid():"��");// ������ʾ�ҵ�ID,ѧͽ��ʾͽ�ܵ�ID
//		holder.aci_incomerId.setText(income.getUid());// ������ʾ�ҵ�ID,ѧͽ��ʾͽ�ܵ�ID
		holder.aci_incomeDate.setText(income.getTimestamp());
		// �����������ʾ��ѧͽ����ʾ��ͬ
		holder.aci_incomeCotent.setText("��ɣ�" + income.getDescription() + "��" + "׬��"
				+ Html.fromHtml("<font color=#FF0000>" + income.getMoney() + "</font>") + "Ԫ");
		
		holder.aci_xuetuCotent.setText(income.getUid()+"��Ϊ����ͽ��");//���Ϊ��ͽ������....................................
		
		holder.aci_xuetuCotent.setVisibility(mType == 3 || mType == 1 ? View.VISIBLE : View.GONE);// ���ѧͽ,��ʾ������
		holder.aci_incomeCotent.setVisibility(mType == 2 || mType == 1 || mType == 4 ? View.VISIBLE : View.GONE);// ���������ʾ������

		

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
