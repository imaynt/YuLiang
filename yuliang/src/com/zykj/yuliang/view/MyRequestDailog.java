package com.zykj.yuliang.view;

import java.util.Timer;

import com.zykj.yuliang.R;
import android.app.Dialog;
import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class MyRequestDailog extends Dialog {

	public static MyRequestDailog m_dialog;
	public static Timer m_timer;
	private String msg;

	public MyRequestDailog(Context context) {
		this(context, "");
	}

	public MyRequestDailog(Context context, String msg) {
		super(context, R.style.RequestDialog);
		this.msg = msg;
		this.setContentView(R.layout.dialog_request);
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		if (m_dialog == null) {
			return;
		}
		final ImageView image = (ImageView) findViewById(R.id.dialog_load);
		Animation anim = new RotateAnimation(0, 360,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		anim.setInterpolator(new LinearInterpolator());// ���ò���֮����ͣ��
		anim.setDuration(1500);// ���ò����ٶ�
		anim.setRepeatCount(-1);// ����ѭ������
		image.startAnimation(anim);

		if (msg.length() > 0) {
			final TextView show_msg = (TextView) findViewById(R.id.dialog_text);
			show_msg.setText(msg);
		}

	}

	public static void showDialog(Context context, String msg) {
		if (m_dialog == null) {
			m_dialog = new MyRequestDailog(context, msg);
			m_dialog.setCancelable(false);
			m_dialog.show();
		}else{
			m_dialog.dismiss();
			m_dialog = new MyRequestDailog(context, msg);
			m_dialog.setCancelable(false);
			m_dialog.show();
		}
	}

	public static void closeDialog() {
		if (m_dialog == null) {
			return;
		}
		m_dialog.dismiss();

	}
}
