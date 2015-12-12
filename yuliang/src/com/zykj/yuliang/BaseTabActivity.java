package com.zykj.yuliang;

import java.io.File;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;

import com.zykj.yuliang.utils.Tools;

@SuppressWarnings("deprecation")
public class BaseTabActivity extends TabActivity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {// ���ذ�ť
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("��ʾ")
					.setMessage("��ȷ���˳���ǰӦ��")
					.setNegativeButton("ȡ��", new OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					})
					.setPositiveButton("ȷ��", new OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							try {
								// �ж��Ƿ������ʱ�������ļ�
								File temp_file = new File(Environment
										.getExternalStorageDirectory()
										+ File.separator
										+ BaseApp.FILE_DIR);
								Tools.Log("�ļ��Ƿ���ڣ�" + temp_file.exists());
								if (temp_file.exists()) {
									File[] file_detail = temp_file.listFiles();
									for (File file_del : file_detail) {
										file_del.delete();
									}
									temp_file.delete();
								}

							} catch (Exception e) {

							}
							System.exit(0);
						}
					})
					.setOnCancelListener(
							new DialogInterface.OnCancelListener() {
								public void onCancel(DialogInterface dialog) {
									dialog.dismiss();
								}

							}).show();
		}

		return super.onKeyDown(keyCode, event);
	}

}
