package com.zykj.yuliang.view;

import com.zykj.yuliang.R;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MyCommonTitle extends RelativeLayout {
	private LinearLayout ll_back_btn;
	private ImageView img_back;
	private TextView backEdit;
	private TextView titleText;
	private TextView subtitleText;
	private TextView title_edit;
	private ImageView share_btn;

	public MyCommonTitle(Context context, AttributeSet attrs) {
		super(context, attrs);

		LayoutInflater.from(context).inflate(R.layout.ui_mycommontitle, this);
		ll_back_btn = (LinearLayout) findViewById(R.id.ll_back_btn);// ����
		img_back = (ImageView) findViewById(R.id.aci_back_btn);// ����
		title_edit = (TextView) findViewById(R.id.aci_edit_textview);// �༭
		share_btn = (ImageView) findViewById(R.id.aci_share_btn);// ����
		/**
		 * ������ص�ǰ�ر�
		 */
		ll_back_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				((Activity) getContext()).finish();
			}
		});
	}

	/**
	 * �����¼���Ϊ������Ӧ�Ŀؼ���ʾ
	 * 
	 * @param backEditListener
	 * @param editListener
	 * @param shareListener
	 */
	public void setListener(OnClickListener backEditListener, OnClickListener editListener,
			OnClickListener shareListener) {
		if (backEditListener != null) {
			backEdit.setVisibility(View.VISIBLE);
			backEdit.setOnClickListener(backEditListener);
		}
		if (editListener != null) {
			title_edit.setVisibility(View.VISIBLE);
			title_edit.setOnClickListener(editListener);
		}
		if (shareListener != null) {
			share_btn.setVisibility(View.VISIBLE);
			share_btn.setOnClickListener(shareListener);
		}
	}

	public void setBackTitle(String backtitle) {
		backEdit = (TextView) findViewById(R.id.aci_back_textview);// ����������ʾ
		backEdit.setText(backtitle);
	}

	public void setTitle(String title) {
		titleText = (TextView) findViewById(R.id.aci_title_textview);// ����
		titleText.setText(title);
	}

	public TextView getTitle() {
		return titleText;
	}

	public void setSubTitle(String subtitle) {
		subtitleText = (TextView) findViewById(R.id.aci_subtitle_textview);// ������
		subtitleText.setVisibility(View.VISIBLE);
		subtitleText.setText(subtitle);
	}

	/**
	 * INVISIBLE�ǿؼ����ɼ�,����λ�ò���ռ��,�����ؼ�λ�ò��� GONE ���ǿؼ����ɼ�,����λ�ûᱻ�����ؼ�λ�øı��ռ��
	 * 
	 * @param flag
	 */
	public void setBackVisible(Boolean flag) {
		if (flag) {
			ll_back_btn.setVisibility(View.VISIBLE);
		} else {
			ll_back_btn.setVisibility(View.INVISIBLE);
		}
	}
}
