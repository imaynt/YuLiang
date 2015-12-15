package com.zykj.yuliang.activity;

import java.io.File;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.View;
import android.content.DialogInterface.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.RequestParams;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zykj.yuliang.BaseActivity;
import com.zykj.yuliang.BaseApp;
import com.zykj.yuliang.R;
import com.zykj.yuliang.http.HttpErrorHandler;
import com.zykj.yuliang.http.HttpUtils;
import com.zykj.yuliang.http.UrlContants;
import com.zykj.yuliang.utils.CircleImageView;
import com.zykj.yuliang.utils.StringUtil;
import com.zykj.yuliang.utils.Tools;

public class MainActivity extends BaseActivity implements View.OnClickListener {

	private Button btn_detail;
	private Button btn_more;
	private CircleImageView iv_header;
	private LinearLayout ll_makemaoney;// ׬Ǯ
	private LinearLayout ll_apprentice;// ��ͽ
	private LinearLayout ll_duobao, ll_youhuiquan, ll_shengqian, ll_duihuan;// һԪ�ᱦ,�Ż�ȯ,ʡǮ,�һ�
	private Intent intent;
	private String userId;
	private TextView tv_yue;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);  
//		userId=getIntent().getStringExtra("userId");
		initViews();
		initClick();
		initEvents();

	}

	protected void initClick() {
		btn_detail.setOnClickListener(this);
		btn_more.setOnClickListener(this);
		ll_makemaoney.setOnClickListener(this);
		ll_apprentice.setOnClickListener(this);
		ll_duobao.setOnClickListener(this);
		ll_youhuiquan.setOnClickListener(this);
		ll_shengqian.setOnClickListener(this);
		ll_duihuan.setOnClickListener(this);
	}

	protected void initViews() {
		iv_header=(CircleImageView) findViewById(R.id.iv_header);
		btn_detail = (Button) findViewById(R.id.btn_detail);//��ϸ
		btn_more = (Button) findViewById(R.id.more);//����
		ll_makemaoney = (LinearLayout) findViewById(R.id.ll_zhuanqian);//׬Ǯ
		ll_apprentice = (LinearLayout) findViewById(R.id.ll_shoutu);//��ͽ
		ll_duobao = (LinearLayout) findViewById(R.id.ll_yiyuanduobao);//һԪ�ᱦ
		ll_youhuiquan = (LinearLayout) findViewById(R.id.ll_youhuiquan);//�Ż�ȯ
		ll_shengqian = (LinearLayout) findViewById(R.id.ll_shengqian);//ʡǮ
		ll_duihuan = (LinearLayout) findViewById(R.id.ll_duihuan);//�һ�
		tv_yue=(TextView) findViewById(R.id.tv_yue);
		tv_yue.setText(BaseApp.getModel().getMoney());
		String avatar=BaseApp.getModel().getAvatar();
		ImageLoader.getInstance().displayImage(StringUtil.toString(avatar, "http://"), iv_header);
		
	}

	protected void initEvents() {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_detail:
			intent = new Intent(MainActivity.this, DetailActivity.class);
			startActivity(intent);
			break;
		case R.id.more:
			intent = new Intent(MainActivity.this, MoreActivity.class);
			startActivity(intent);
			break;
		case R.id.ll_zhuanqian:
			intent = new Intent(MainActivity.this, MakeMoneyActivity.class);
			startActivity(intent);
			break;
		case R.id.ll_shoutu:
			intent = new Intent(MainActivity.this, ApprenticeActivity.class);
			startActivity(intent);
			break;
		case R.id.ll_yiyuanduobao:
			intent = new Intent(MainActivity.this, ApprenticeActivity.class);
			startActivity(intent);
			break;
		case R.id.ll_youhuiquan:
			/***
			 * ��ʱ����,����(uid,points)���޸�...................................
			 */
			RequestParams params=new RequestParams();
			params.put("uid", BaseApp.getModel().getUserid());
			params.put("points", BaseApp.getModel().getIntegral());
			HttpUtils.getLoginUrl(new HttpErrorHandler() {
				@Override
				public void onRecevieSuccess(JSONObject json) {
					String url = json.getJSONObject(UrlContants.jsonData).getString("url");
					Intent intent = new Intent().setClass(MainActivity.this,
							CreditActivity.class);
					intent.putExtra("navColor", "#50bf83");// ���õ������ı�����ɫ������#ffffff����ʽ��
					intent.putExtra("titleColor", "#ffffff");// ���õ������������ɫ������#ffffff����ʽ��
					intent.putExtra("url", url);// �����Զ���½��ַ��ÿ�������˶�̬���ɡ�
					startActivity(intent);
				}
			}, params);
			break;
		case R.id.ll_shengqian:
			intent = new Intent(MainActivity.this, ApprenticeActivity.class);
			startActivity(intent);
			break;
		case R.id.ll_duihuan:
			intent = new Intent(MainActivity.this, DuiHuanActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}

	}
	@SuppressWarnings("deprecation")
	@Override
	protected void onDestroy() {
		Tools.Log("��ǰtabActivity�˳�");
		super.onDestroy();
	}
	
	@Override
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
