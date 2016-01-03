package com.zykj.yuliang.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zykj.yuliang.R;
import com.zykj.yuliang.http.UrlContants;
import com.zykj.yuliang.model.Tudi;
import com.zykj.yuliang.utils.CircleImageView;
import com.zykj.yuliang.utils.StringUtil;

public class TudiInfoAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater mLayoutInflater;
	private List<Tudi> list;

	public TudiInfoAdapter(Context context, List<Tudi> list) {
		super();
		mLayoutInflater = LayoutInflater.from(context);
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Viewholder holder = null;
		if (convertView == null) {
			holder = new Viewholder();
			convertView = mLayoutInflater.inflate(R.layout.ui_item_tudi_info, parent, false);
			holder.img_avatar = (CircleImageView) convertView.findViewById(R.id.img_avatar);
			holder.tv_username = (TextView) convertView.findViewById(R.id.tv_username);
			holder.tv_content=(TextView) convertView.findViewById(R.id.tv_content);
			holder.tv_day_used=(TextView) convertView.findViewById(R.id.tv_days);
			convertView.setTag(holder);
		} else {
			holder = (Viewholder) convertView.getTag();
		}
		Tudi tudi = list.get(position);
		holder.tv_username.setText(StringUtil.isEmpty(tudi.getUsername())?tudi.getId():tudi.getUsername());
		ImageLoader.getInstance().displayImage(StringUtil.toString(UrlContants.IMAGE_URL + tudi.getAvatar(), "http://"),
				holder.img_avatar);
		holder.tv_content.setText("总收入："+tudi.getAccount()+"元");
		holder.tv_day_used.setText(StringUtil.isEmpty(tudi.getDay_used())?"活跃天数："+0+"天":"活跃天数："+tudi.getDay_used()+"天");
		return convertView;
	}

	public class Viewholder {
		CircleImageView img_avatar;
		TextView tv_username;
		TextView tv_content;
		TextView tv_day_used;
		
	}
}
