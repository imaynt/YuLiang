package com.zykj.yuliang.activity;

import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.RequestParams;
import com.zykj.yuliang.R;
import com.zykj.yuliang.R.layout;
import com.zykj.yuliang.R.menu;
import com.zykj.yuliang.http.HttpErrorHandler;
import com.zykj.yuliang.http.HttpUtils;
import com.zykj.yuliang.http.UrlContants;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;

public class MainActivity extends Activity implements OnClickListener {

	private Button btn_detail;
	private Button btn_more;
	private LinearLayout ll_makemaoney;// ׬Ǯ
	private LinearLayout ll_apprentice;// ��ͽ
	private LinearLayout ll_duobao, ll_youhuiquan, ll_shengqian, ll_duihuan;// һԪ�ᱦ,�Ż�ȯ,ʡǮ,�һ�
	private Intent intent;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);  
		
		Intent i=new Intent(MainActivity.this,FirstLoginActivity.class);
		startActivity(i);
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
		btn_detail = (Button) findViewById(R.id.btn_detail);//��ϸ
		btn_more = (Button) findViewById(R.id.more);//����
		ll_makemaoney = (LinearLayout) findViewById(R.id.ll_zhuanqian);//׬Ǯ
		ll_apprentice = (LinearLayout) findViewById(R.id.ll_shoutu);//��ͽ
		ll_duobao = (LinearLayout) findViewById(R.id.ll_yiyuanduobao);//һԪ�ᱦ
		ll_youhuiquan = (LinearLayout) findViewById(R.id.ll_youhuiquan);//�Ż�ȯ
		ll_shengqian = (LinearLayout) findViewById(R.id.ll_shengqian);//ʡǮ
		ll_duihuan = (LinearLayout) findViewById(R.id.ll_duihuan);//�һ�
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
			 * ��ʱ����,���޸�...................................
			 */
			RequestParams params=new RequestParams();
			params.put("uid", "1");
			params.put("points", "2");
			HttpUtils.getLoginUrl(new HttpErrorHandler() {
				@Override
				public void onRecevieSuccess(JSONObject json) {
					String url = json.getJSONObject(UrlContants.jsonData)
							.getString("url");
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

}
