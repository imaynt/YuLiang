package com.zykj.yuliang.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zykj.yuliang.BaseActivity;
import com.zykj.yuliang.R;
import com.zykj.yuliang.view.MyCommonTitle;
import com.zykj.yuliang.view.PickDialog;
import com.zykj.yuliang.view.PickDialog.PickDialogListener;

public class WeiXinActivity extends BaseActivity {
	private MyCommonTitle myCommonTitle;
	private ImageView img_weixin;
	private EditText tv_code;
	private Button btn_submit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ui_more_weixin);

		initiew();
	}

	private void initiew() {
		myCommonTitle = (MyCommonTitle) findViewById(R.id.aci_mytitle);
		myCommonTitle.setTitle("΢��");

		img_weixin=(ImageView) findViewById(R.id.img_weixin);//΢�Ź��ںŶ�ά��
		tv_code = (EditText) findViewById(R.id.ed_code);//��֤��
		btn_submit = (Button) findViewById(R.id.btn_submit);//�ύ

		img_weixin.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				List<String> list=new ArrayList<String>();
				list.add("���浽�ֻ�");
				new PickDialog(WeiXinActivity.this, "ͼƬ����", list, new PickDialogListener() {
					
					@Override
					public void onListItemClick(int position, String string) {
						/**
						 * ����ʵ�ֱ���ͼƬ���ֻ�
						 */
					}
				}).show();
				
				return false;
			}
		});
	}

}
