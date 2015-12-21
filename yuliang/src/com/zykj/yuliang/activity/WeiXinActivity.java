package com.zykj.yuliang.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zykj.yuliang.BaseActivity;
import com.zykj.yuliang.R;
import com.zykj.yuliang.utils.StringUtil;
import com.zykj.yuliang.utils.Tools;
import com.zykj.yuliang.view.MyCommonTitle;
import com.zykj.yuliang.view.PickDialog;
import com.zykj.yuliang.view.PickDialog.PickDialogListener;

public class WeiXinActivity extends BaseActivity {
	private MyCommonTitle myCommonTitle;
	private ImageView img_weixin;
	private EditText tv_code;
	private Button btn_submit;
	private List<String> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ui_more_weixin);

		initiew();
	}

	private void initiew() {
		myCommonTitle = (MyCommonTitle) findViewById(R.id.aci_mytitle);
		myCommonTitle.setTitle("微信");

		img_weixin=(ImageView) findViewById(R.id.img_weixin);//微信公众号二维码
		tv_code = (EditText) findViewById(R.id.ed_code);//验证码
		btn_submit = (Button) findViewById(R.id.btn_submit);//提交
		
		setListener(img_weixin,btn_submit);
		
		/**
		 * 长按保存图片
		 */
		img_weixin.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				list=new ArrayList<String>();
				list.add("保存到手机");
				new PickDialog(WeiXinActivity.this, "图片操作", list, new PickDialogListener() {
					
					@Override
					public void onListItemClick(int position, String string) {
						/**
						 * 代码实现保存图片到手机
						 */
					}
				}).show();
				return false;
			}
		});
	}
	@Override
	public void onClick(View view) {
		super.onClick(view);
		switch (view.getId()) {
		case R.id.img_weixin:
			break;
		case R.id.btn_submit:
			AlertDialog.Builder builder=new Builder(WeiXinActivity.this);
			builder.setTitle("温馨提示");
			builder.setMessage("由于该软件未申请微信公众号，暂不支持此功能！还请谅解！");
			builder.setPositiveButton("确定", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
			builder.create().show();
			// if(StringUtil.isEmpty(tv_code.getText().toString().trim())){
			// Tools.toast(WeiXinActivity.this, "验证码不能为空");
			// }
			break;
		default:
			break;
		}
	}

}
