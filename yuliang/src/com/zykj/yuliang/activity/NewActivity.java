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

import com.zykj.yuliang.R;

public class NewActivity extends Activity implements OnClickListener {

	private ImageButton btn_new_back;

	// 两个答案选项
	private RelativeLayout ll_answer_o;
	private RelativeLayout ll_answer_t;

	private TextView tv_answer_title;
	private TextView tv_answer_o;
	private TextView tv_answer_t;

	int click = 0;

	int[] answer_num = { 1, 2, 1, 2, 2, 2, 1 };
	String[] answer_title = { "余粮是用来做什么的？", "我能在余粮里做什么事情？", "余粮能让我赚多少钱？",
			"怎么多收徒弟？", "余粮会不会是骗子？为什么能赚钱？", "为什么有时候做完联盟任务拿不到钱？", "兑换需要多久能完成？" };
	String[] answer_o = { "帮助每个人利用空余时间赚钱，发挥自身的广告价值，使用越久收益越高。", "看看新闻，玩玩游戏，聊聊天",
			"余粮是一款用的时间越久，收益增长越快的应用。如果坚持每天做任务，每天收徒弟，最初可以月入几百，坚持几个月下来，月入数千很轻松。",
			"每天盯着任务，做任务", "通过特殊手段获取奖学金。", "因为人品不好", "最快3个小时，一般不超过24小时" };
	String[] answer_t = { "一款游戏，让我打发空余时间。", "每天来完成新任务赚钱，兑换超值商品，话费充值和支付宝提现等",
			"只能赚赚很少的零花钱", "告诉身边的朋友、通过微信、微博等社交网络，分享给朋友中，朋友输入我的ID可以领取3元现金红包。",
			"余粮是中国第一的赚钱平台，用户发挥自身的广告价值，完成品牌主的任务获得收益，劳有所获。",
			"联盟任务是由广告联盟负责结算，有时会漏算和少算，不是余粮所能控制的，但是余粮一直在努力帮大家拿到该拿的奖金", "48小时内完成。" };

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
		btn_new_back.setOnClickListener(this);
		ll_answer_o.setOnClickListener(this);
		ll_answer_t.setOnClickListener(this);
	}

	protected void initViews() {
		btn_new_back = (ImageButton) findViewById(R.id.btn_new_back);
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
		if (v.getId() == R.id.btn_new_back) {
			this.finish();
		}
		if (click == 6) {
			Intent intent = new Intent();
			intent.putExtra("result", "6");
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
		}
		if (v.getId() == R.id.ll_answer_t && answer_num[click] == 2) {
			click++;
			tv_answer_o.setText(answer_o[click]);
			tv_answer_t.setText(answer_t[click]);
			tv_answer_title.setText(answer_title[click]);
			return;
		}

	}

}
