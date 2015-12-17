package com.zykj.yuliang.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.zykj.yuliang.R;

public class WhyAppActivity extends Activity implements OnClickListener{
	
//	String w1 = "��׬�����Ǯ���Ǿ�ȥ��ͽ�ɣ���<br/><br/>�����������ͽ�ܺ���ͽ�ܵ�ͽ�ܣ�ͽ������������������㶼���Եõ����";
//	String y1 = "20%";
//	String w2 = "�����棨�������ٷ�������������֮�����ѧͽ�����񣬲���ѧͽȫ�����棬��Ҳ��õ������ٷ��������Ľ�������ô������������һ�£�<br/><br/>1��ͽ��ÿ��׬10Ԫ����ͻ��2Ԫ������ 10��ͽ��ÿ��׬10Ԫ����ͻ��20Ԫ������ 100��ͽ��ÿ��׬10Ԫ����ͻ��200Ԫ������1����6000Ԫ���� ����Ҫ���ǣ�ѧͽ������";
//	String y2 = "������Ч ";
//	String w3 = "��Ŷ���Ǿ���ζ�ţ�ÿ���¶�����ô�ཱ������ȫ��ѧͽ����������Ҫ�Լ����κ����飡����������ô���ף�˵��������<br/><br/>���⣬���100��ͽ��ÿ������10��ͽ�ܣ�������������1000��ͽ�ͬ�����ͽ������Ľ��������������֪�������жྪ�ˣ�<br/><br/>�����ǣ��ⲻ�ǰ����Σ�������ʵ��׬Ǯ���ԣ�Ҳ����Щ�����ǵ�";
//	String y3 = "����";
//	String w4 = "����������Ѿ���Խ��Խ���������Ϊ��ͽ�������Ǯ�����ѣ�<br/><br/>���ԣ���λ��׬Ǯ�������ǣ��벻Ҫ����������������ɵ���ͽ�ɣ�ֻҪ��ġ�ʦ�š���׳�����ͽ���������࣬������֤��һ���ӱ��������ʹ����������۷壬������Ǯ��";

	
	String w1 = "��׬�����Ǯ���Ǿ�ȥ��ͽ�ɣ���<br/><br/>�����������ͽ�ܺ���ͽ�ܵ�ͽ�ܣ�ͽ������������������㶼���Եõ����<font color=\"yellow\">20%</font>�����棨�������ٷ�������������֮�����ѧͽ�����񣬲���ѧͽȫ�����棬��Ҳ��õ������ٷ��������Ľ�������ô������������һ�£�<br/><br/>";
	String w2 = "1��ͽ��ÿ��׬10Ԫ����ͻ��2Ԫ������ 10��ͽ��ÿ��׬10Ԫ����ͻ��20Ԫ������ 100��ͽ��ÿ��׬10Ԫ����ͻ��200Ԫ������1����6000Ԫ���� ����Ҫ���ǣ�ѧͽ������<font color=\"yellow\">������Ч</font>��Ŷ���Ǿ���ζ�ţ�ÿ���¶�����ô�ཱ������ȫ��ѧͽ����������Ҫ�Լ����κ����飡����������ô���ף�˵��������<br/><br/> ";
	String w3 = "���⣬���100��ͽ��ÿ������10��ͽ�ܣ�������������1000��ͽ�ͬ�����ͽ������Ľ��������������֪�������жྪ�ˣ�<br/><br/>�����ǣ��ⲻ�ǰ����Σ�������ʵ��׬Ǯ���ԣ�Ҳ����Щ�����ǵ�<font color=\"yellow\">����</font>����������Ѿ���Խ��Խ���������Ϊ��ͽ�������Ǯ�����ѣ�<br/><br/>";
	String w4 = "���ԣ���λ��׬Ǯ�������ǣ��벻Ҫ����������������ɵ���ͽ�ɣ�ֻҪ��ġ�ʦ�š���׳�����ͽ���������࣬������֤��һ���ӱ��������ʹ����������۷壬������Ǯ��<br/><br/>";

	private ImageButton ib_btn_back;
	private TextView tv_why_app_1;
	private TextView tv_why_app_2;
	private TextView tv_why_app_3;
	private TextView tv_why_app_4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_why_app);
		initViews();
		initClick();
		initEvents();
		tv_why_app_1.setText(Html.fromHtml(w1));  
		tv_why_app_2.setText(Html.fromHtml(w2)); 
		tv_why_app_3.setText(Html.fromHtml(w3)); 
		tv_why_app_4.setText(Html.fromHtml(w4)); 
	}

	protected void initClick() {
		ib_btn_back.setOnClickListener(this);
	}

	protected void initViews() {
		ib_btn_back = (ImageButton) findViewById(R.id.ib_btn_back);
		tv_why_app_1 = (TextView) findViewById(R.id.tv_why_app_1);
		tv_why_app_2 = (TextView) findViewById(R.id.tv_why_app_2);
		tv_why_app_3 = (TextView) findViewById(R.id.tv_why_app_3);
		tv_why_app_4 = (TextView) findViewById(R.id.tv_why_app_4);
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


