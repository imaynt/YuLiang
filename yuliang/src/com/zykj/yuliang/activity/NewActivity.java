package com.zykj.yuliang.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.Header;

import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.RequestParams;
import com.zykj.yuliang.BaseActivity;
import com.zykj.yuliang.BaseApp;
import com.zykj.yuliang.R;
import com.zykj.yuliang.http.AbstractHttpHandler;
import com.zykj.yuliang.http.HttpUtils;
import com.zykj.yuliang.utils.SharedPreferenceUtils;
import com.zykj.yuliang.view.MyCommonTitle;

public class NewActivity extends BaseActivity {

	private MyCommonTitle myCommonTitle;

	// ������ѡ��
	private RelativeLayout ll_answer_o;
	private RelativeLayout ll_answer_t;

	private TextView tv_answer_title;
	private TextView tv_answer_o;
	private TextView tv_answer_t;
	
	private RequestParams params;

	int click = 0;

	int[] answer_num = { 1, 2, 1, 2, 2, 2, 1 };
	String[] answer_title = { "������������ʲô�ģ�", "��������������ʲô���飿", "����������׬����Ǯ��",
			"��ô����ͽ�ܣ�", "�����᲻����ƭ�ӣ�Ϊʲô��׬Ǯ��", "Ϊʲô��ʱ���������������ò���Ǯ��", "�һ���Ҫ�������ɣ�" };
	String[] answer_o = { "����ÿ�������ÿ���ʱ��׬Ǯ����������Ĺ���ֵ��ʹ��Խ������Խ�ߡ�", "�������ţ�������Ϸ��������",
			"������һ���õ�ʱ��Խ�ã���������Խ���Ӧ�á�������ÿ��������ÿ����ͽ�ܣ�����������뼸�٣���ּ�����������������ǧ�����ɡ�",
			"ÿ�춢������������", "ͨ�������ֶλ�ȡ��ѧ��", "��Ϊ��Ʒ����", "���3��Сʱ��һ�㲻����24Сʱ" };
	String[] answer_t = { "һ����Ϸ�����Ҵ򷢿���ʱ�䡣", "ÿ�������������׬Ǯ���һ���ֵ��Ʒ�����ѳ�ֵ��֧�������ֵ�",
			"ֻ��׬׬���ٵ��㻨Ǯ", "������ߵ����ѡ�ͨ��΢�š�΢�����罻���磬����������У����������ҵ�ID������ȡ3Ԫ�ֽ�����",
			"�������й���һ��׬Ǯƽ̨���û���������Ĺ���ֵ�����Ʒ���������������棬��������",
			"�����������ɹ�����˸�����㣬��ʱ��©������㣬�����������ܿ��Ƶģ���������һֱ��Ŭ�������õ����õĽ���", "48Сʱ����ɡ�" };

	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new);
		click = 0;
		initViews();
		initClick();
		initEvents();

	}

	protected void initClick() {
		ll_answer_o.setOnClickListener(this);
		ll_answer_t.setOnClickListener(this);
	}

	protected void initViews() {
		myCommonTitle = (MyCommonTitle) findViewById(R.id.aci_mytitle);
		myCommonTitle.setTitle("��������");
		ll_answer_o = (RelativeLayout) findViewById(R.id.ll_answer_o);
		ll_answer_t = (RelativeLayout) findViewById(R.id.ll_answer_t);
		tv_answer_o = (TextView) findViewById(R.id.tv_answer_o);
		tv_answer_t = (TextView) findViewById(R.id.tv_answer_t);
		tv_answer_title = (TextView) findViewById(R.id.tv_answer_title);
	}

	protected void initEvents() {

	}

	@Override
	public void onClick(View v) {
		if (click == 6&& answer_num[click] == 1) {
			Intent intent = new Intent();
			intent.putExtra("result", "6");
			
			getNew();
			setResult(1001,intent);
			this.finish();
			return;
		}		
		if (v.getId() == R.id.ll_answer_o && answer_num[click] == 1) {
			click++;
			tv_answer_o.setText(answer_o[click]);
			tv_answer_t.setText(answer_t[click]);
			tv_answer_title.setText(answer_title[click]);
			return;
		}//122121
		if (v.getId() == R.id.ll_answer_t && answer_num[click] == 2) {
			click++;
			tv_answer_o.setText(answer_o[click]);
			tv_answer_t.setText(answer_t[click]);
			tv_answer_title.setText(answer_title[click]);
			return;
		}

	}
	
	public void getNew(){
		/**
		 * ��������
		 */
		params = new RequestParams();
		params.put("deviceId", BaseApp.getModel().getDeviceId());// �豸ID
		params.put("part", "1");// 1����2��1�����ֽ̳̣�2�Ǹ������ϵ÷֣�
		HttpUtils.postNewAndPersonal(new AbstractHttpHandler() {

			@Override
			public void onJsonSuccess(JSONObject json) {
				if (json.getString("code").equals("200")) {//�������������
					SharedPreferenceUtils.init(NewActivity.this).setIsOver("true");
					SharedPreferenceUtils.init(NewActivity.this).setIsNew("false");
				}				
			}
			
			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
				
			}
		},params); 
	}

}
