package com.zykj.yuliang.view;



import com.zykj.yuliang.R;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

/**
 * һЩui��Dialog�е�ʹ��
 * 
 * @author bin
 * 
 */
public class UIDialog {
	public static AlertDialog dialog;

	/** 3������ťdialog */
	public static void ForThreeBtn(Context context, String[] showtxt,
			OnClickListener lisener) {
		dialog = new AlertDialog.Builder(context).create();
		dialog.show();
		Window window = dialog.getWindow();
		// *** ��Ҫ����������ʵ������Ч����.
		// ���ô��ڵ�����ҳ��,shrew_exit_dialog.xml�ļ��ж���view����
		window.setContentView(R.layout.dialog_picture);

		Button m_btn_1 = (Button) window.findViewById(R.id.dialog_modif_1);
		Button m_btn_2 = (Button) window.findViewById(R.id.dialog_modif_2);
		Button m_btn_3 = (Button) window.findViewById(R.id.dialog_modif_3);

		m_btn_1.setText(showtxt[0]);
		m_btn_2.setText(showtxt[1]);
		m_btn_3.setText(showtxt[2]);

		m_btn_1.setOnClickListener(lisener);
		m_btn_2.setOnClickListener(lisener);
		m_btn_3.setOnClickListener(lisener);
	}
	
	/**
	 * �ر�dialog
	 */
	public static void closeDialog() {
		if (dialog == null || !dialog.isShowing()) {
			return;
		}
		dialog.dismiss();

	}
}
