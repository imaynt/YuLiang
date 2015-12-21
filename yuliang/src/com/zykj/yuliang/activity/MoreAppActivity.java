package com.zykj.yuliang.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.zykj.yuliang.BaseActivity;
import com.zykj.yuliang.R;
import com.zykj.yuliang.view.MyCommonTitle;

public class MoreAppActivity extends BaseActivity{
	
	String w1 = "�װ��������ǣ�<br/><br/>Ϊ������׬�����Ǯ���ո����ͽ�ܣ�С��ɷ�ѿ��ģ���������¹��ԣ��������Ķ�������ʵ������ȫ����Ĺ��Ծ������<br/><br/><font color=\"yellow\">QQ���ѡ�QQȺ��΢�ź���</font><br/><br/>���ȿ϶��ǰ�׬Ǯ���ֺ����Ƽ���������������С����Ƕ���ϲ���������ģ��������ID�����Ѿ��������ȡ3Ԫ������Ӵ�̤��׬Ǯ֮�ã����Ի����м�������<br/><br/>";
	String w2 = "<font color=\"yellow\">΢������Ȧ��QQ�ռ䡢����΢������Ѷ΢�������������罻����</font><br/><br/>���罻���磬��Ҫ�Ƿ���̬����΢��������־�����ݿ���������ʹ���ĵá����鴫�ڵȣ�������ɹ����������ͼ���ص��Ǵ����������ӻ���ID��΢������Ȧ�Ǹ��ǳ����׵ķ�ʽ������ÿ2��ͷ���һ��ɹ����Ķ�̬������ѵ��ޣ���ת���������������Ѽ��롣<br/><br/> ";
	String w3 = "<font color=\"yellow\">�ٶ����ɡ����ġ�è�ˡ����ꡢ������̳</font><br/><br/>���ɡ���̳�ۼ��˴�����Ȥ��Ͷ���û����������Ը��û�Ⱥ�壬��������Ȥ��ص����ӡ�<br/>ʵս����������ٶ������У����ֻ���Ϸ��׬Ǯ����ء���������1��Ĵ���з�������������ȡ�÷ǳ��õ�Ч����<br/><br/>";
	String w4 = "<font color=\"yellow\">�ٶ�֪�������˰��ʡ�֪�����ʴ�����</font><br/><br/>����Իش����׬Ǯ����Ϸ�ҡ���Ϸ�����������࡭�ȵ�������⣬�����Ļش����ǵ����⣬Ȼ���ٽ����������������׬ȡ�ֽ𣬼ǵô����������ӻ���IDŶ��<br/><br/>";
	String w5 = "<font color=\"yellow\">���˲��͡��ٶȾ���</font><br/><br/>������������д��ϰ�ߣ��������Լ��ĸ��˲��ͷ������������������ͨ������׬���ֽ�ϲ���������۵����ѣ�Ҳ���������Ų������µײ���������Ŷ��<br/><br/>���ֻҪ�����ǳ�͸�˹��ԣ���������İ������ͽ���ϣ�ʦ��׳��Ҳֻ��ʱ��������<br/><br/>";
	private ImageButton ib_btn_back;
	private TextView tv_more_app_1;
	private TextView tv_more_app_2;
	private TextView tv_more_app_3;
	private TextView tv_more_app_4;
	private TextView tv_more_app_5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_more_app);
		initViews();
		initClick();
		initEvents();
		tv_more_app_1.setText(Html.fromHtml(w1));  
		tv_more_app_2.setText(Html.fromHtml(w2)); 
		tv_more_app_3.setText(Html.fromHtml(w3)); 
		tv_more_app_4.setText(Html.fromHtml(w4)); 
		tv_more_app_5.setText(Html.fromHtml(w5)); 
	}

	protected void initClick() {
		ib_btn_back.setOnClickListener(this);
	}

	protected void initViews() {
		ib_btn_back = (ImageButton) findViewById(R.id.ib_btn_back);
		tv_more_app_1 = (TextView) findViewById(R.id.tv_more_app_1);
		tv_more_app_2 = (TextView) findViewById(R.id.tv_more_app_2);
		tv_more_app_3 = (TextView) findViewById(R.id.tv_more_app_3);
		tv_more_app_4 = (TextView) findViewById(R.id.tv_more_app_4);
		tv_more_app_5 = (TextView) findViewById(R.id.tv_more_app_5);
	}

	protected void initEvents() {
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ib_btn_back:
			this.finish();
			break;

		default:
			break;
		}
		
	}

}


