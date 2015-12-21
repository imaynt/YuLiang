package com.zykj.yuliang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zykj.yuliang.BaseActivity;
import com.zykj.yuliang.R;
import com.zykj.yuliang.view.MyCommonTitle;

public class CommonQuestionActivity extends BaseActivity{
	
	private MyCommonTitle myCommonTitle;
	private TextView tv_content_0,tv_content_1,tv_content_2,tv_content_3,tv_content_4,tv_content_5,tv_content_6,tv_content_7,tv_content_8,tv_content_9,tv_content_10,tv_content_11,tv_content_12;
	private String []text_all={"��ϵ����","�˻��ⶳ������δ���������ͷ����������ύ����������������ϵ�����ٷ�΢�ſͷ���\n����ʱ�䣺ÿ��9:00 ~ 18:00���ڼ��ղ���Ϣ��\n��ע�����ٷ�΢�ŷ����(Ψһ��ʽ)������","1���������¶�ά�룬�㡰�洢ͼ�񡱣�����ά�뱣�浽�ֻ���᣻\n2����΢�ţ��ҵ���ɨһɨ�����������ᡱ��\n3��ѡ���ά�룬�ֻ����Զ�ʶ��\n4����ע�����������ɣ�","������Ϊ���ж�","1������������ʹ�ù���ID��û��֣��������ҡ����ô����ֻ�����ˮ��ҵ������Ϊ����\n2��ͬһ̨�豸��������ʲô��ʽ������ظ�������ˢ��������ˢͽ����������Ϊ����\n3 �����еĶһ���Ϊ�����ɴ��˹���ˣ��ǳ��ϸ���ͬѧ��������Сʧ��������Ϊһ�����֣��������ᣬ�ҽ������ڽ�⡣���λ����֪����\n������ϧ�Լ���ΨһID������ÿһλ��ʵͽ�������������룬���ǽ�����������20%�Ľ��������������ã�����ÿ��һ���ȥ�Ľ��𳬹���ʮ�򣬾�������������û���������������ͽ�ܽ��ж��ⶳ�ᡣ","������������","�����������Ǹ�⣬Ŀǰ������ʱ�Ƽ�֮�⣬���������޷�ʵ��100%������������ѡ�����ǵ������б������Ǹ���ʵ���û��ķ��������������еġ�������������޶ȵı�֤���������档���������з����������Ȥ�Ĺ��ܡ����ܸ����Ǹ���ľ�ϲ","Ϊʲô��׬Ǯ","�û���ɹ��������ָ�������񣬼�����˹���ƹ���Ϊ�������Ӧ�ı��꣬�˽���Ϊ�Ͷ����ã��ɹ��������֧����Ӧ���ꡣ","û�л�û�����ô��","�����Ƿ�Ҫ������������磺\n1�������״ΰ�װ����ǰ��װ����ɾ���ٰ���Ч\n2��������������Ҫ�󣬱���ע�ᡢ�����\n3����ͬ�����������ͬ����ֻ����һ�Σ��ظ���Ч\n���ȷ��û���⣬��������Ͷ�߸ù�������̣����ǽ����ڸ������ŵĹ�������̡�","�������������ǧ","��������������裺\n1������ÿ�춼��仯��ÿ���Ȱ�ÿ���������ˣ�Ȼ��ȥ��������������ѡ������������������������ʱ����������������࣬����Ҫ����������\n2��ͨ��QQ��΢�š���̳���ƹ��Լ���ID����Ϊʦ��֮����������ͽ�ܵ����뽱����ͽ��Խ��Խ��"};
	private TextView []tv_all={tv_content_0,tv_content_1,tv_content_2,tv_content_3,tv_content_4,tv_content_5,tv_content_6,tv_content_7,tv_content_8,tv_content_9,tv_content_10,tv_content_11,tv_content_12};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_common_question);
		initViews();
		setContent();

	}
	
	private void setContent(){
		for (int i = 0; i < tv_all.length; i++) {
			tv_all[i].setText(text_all[i]);
		}
	}

	private void initViews() {
		myCommonTitle = (MyCommonTitle) findViewById(R.id.aci_mytitle);
		myCommonTitle.setTitle("��������");
	
		tv_content_0=(TextView)findViewById(R.id.tv_content_0);
		tv_all[0]=tv_content_0;
		tv_content_1=(TextView)findViewById(R.id.tv_content_1);
		tv_all[1]=tv_content_1;
		tv_content_2=(TextView)findViewById(R.id.tv_content_2);
		tv_all[2]=tv_content_2;
		tv_content_3=(TextView)findViewById(R.id.tv_content_3);
		tv_all[3]=tv_content_3;
		tv_content_4=(TextView)findViewById(R.id.tv_content_4);
		tv_all[4]=tv_content_4;
		tv_content_5=(TextView)findViewById(R.id.tv_content_5);
		tv_all[5]=tv_content_5;
		tv_content_6=(TextView)findViewById(R.id.tv_content_6);
		tv_all[6]=tv_content_6;
		tv_content_7=(TextView)findViewById(R.id.tv_content_7);
		tv_all[7]=tv_content_7;
		tv_content_8=(TextView)findViewById(R.id.tv_content_8);
		tv_all[8]=tv_content_8;
		tv_content_9=(TextView)findViewById(R.id.tv_content_9);
		tv_all[9]=tv_content_9;
		tv_content_10=(TextView)findViewById(R.id.tv_content_10);
		tv_all[10]=tv_content_10;
		tv_content_11=(TextView)findViewById(R.id.tv_content_11);
		tv_all[11]=tv_content_11;
		tv_content_12=(TextView)findViewById(R.id.tv_content_12);
		tv_all[12]=tv_content_12;
		
		
		
	}

	@Override
	public void onClick(View view) {
		super.onClick(view);
	
	}
}
