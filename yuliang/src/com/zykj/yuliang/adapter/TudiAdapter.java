package com.zykj.yuliang.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zykj.yuliang.R;
import com.zykj.yuliang.model.Tudi;
import com.zykj.yuliang.utils.CircleImageView;

public class TudiAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater mLayoutInflater;
	private List<Tudi> list;

	public TudiAdapter(Context context, LayoutInflater mLayoutInflater,
			List<Tudi> list) {
		super();
		this.context = context;
		this.mLayoutInflater = mLayoutInflater;
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
			convertView = mLayoutInflater.inflate(R.layout.ui_item_tudi,
					parent, false);
			holder.img_avatar = (CircleImageView) convertView
					.findViewById(R.id.img_tudi_avatar);
			holder.tv_nick = (TextView) convertView
					.findViewById(R.id.tv_tudi_name);
			convertView.setTag(holder);
		} else {
			holder = (Viewholder) convertView.getTag();
		}
		holder.tv_nick.setText(list.get(position).getNick());
		ImageLoader.getInstance().displayImage(list.get(position).getAvatar(),
				holder.img_avatar);
		return convertView;
	}

	public class Viewholder {
		CircleImageView img_avatar;
		TextView tv_nick;
	}
}
