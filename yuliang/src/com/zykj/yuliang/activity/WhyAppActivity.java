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
	
//	String w1 = "想赚更多的钱吗？那就去收徒吧！！<br/><br/>在余粮，你的徒弟和你徒弟的徒弟（徒孙），他们所做的任务，你都可以得到最高";
//	String y1 = "20%";
//	String w2 = "的收益（由余粮官方补贴）。换言之，你的学徒做任务，不仅学徒全额收益，你也会得到余粮官方额外给予的奖励！那么让我们来分析一下：<br/><br/>1个徒弟每天赚10元，你就获得2元奖励； 10个徒弟每天赚10元，你就获得20元奖励； 100个徒弟每天赚10元，你就获得200元奖励（1个月6000元）； 更重要的是，学徒奖励是";
//	String y2 = "永久有效 ";
//	String w3 = "的哦，那就意味着，每个月都有这么多奖励，完全由学徒带来，不需要自己做任何事情！余粮就是这么靠谱！说到做到！<br/><br/>另外，你的100个徒弟每人再收10个徒弟，那你又增加了1000个徒孙，同样获得徒孙带来的奖励……（想想就知道收益有多惊人）<br/><br/>粮友们，这不是白日梦，这是真实的赚钱攻略，也是那些粮友们的";
//	String y3 = "秘密";
//	String w4 = "！在余粮里，已经有越来越多的粮友因为收徒变成了有钱的粮友！<br/><br/>所以，各位想赚钱的粮友们，请不要独来独往，拉帮结派的收徒吧！只要你的“师门”够壮大，你的徒弟数量够多，余粮保证你一秒钟变土豪，就此走向人生巅峰，天天有钱花";

	
	String w1 = "想赚更多的钱吗？那就去收徒吧！！<br/><br/>在余粮，你的徒弟和你徒弟的徒弟（徒孙），他们所做的任务，你都可以得到最高<font color=\"yellow\">20%</font>的收益（由余粮官方补贴）。换言之，你的学徒做任务，不仅学徒全额收益，你也会得到余粮官方额外给予的奖励！那么让我们来分析一下：<br/><br/>";
	String w2 = "1个徒弟每天赚10元，你就获得2元奖励； 10个徒弟每天赚10元，你就获得20元奖励； 100个徒弟每天赚10元，你就获得200元奖励（1个月6000元）； 更重要的是，学徒奖励是<font color=\"yellow\">永久有效</font>的哦，那就意味着，每个月都有这么多奖励，完全由学徒带来，不需要自己做任何事情！余粮就是这么靠谱！说到做到！<br/><br/> ";
	String w3 = "另外，你的100个徒弟每人再收10个徒弟，那你又增加了1000个徒孙，同样获得徒孙带来的奖励……（想想就知道收益有多惊人）<br/><br/>粮友们，这不是白日梦，这是真实的赚钱攻略，也是那些粮友们的<font color=\"yellow\">秘密</font>！在余粮里，已经有越来越多的粮友因为收徒变成了有钱的粮友！<br/><br/>";
	String w4 = "所以，各位想赚钱的粮友们，请不要独来独往，拉帮结派的收徒吧！只要你的“师门”够壮大，你的徒弟数量够多，余粮保证你一秒钟变土豪，就此走向人生巅峰，天天有钱花<br/><br/>";

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


