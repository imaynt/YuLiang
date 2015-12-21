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
	
	String w1 = "亲爱的粮友们：<br/><br/>为了让你赚更多的钱，收更多的徒弟，小编煞费苦心，整理出以下攻略，请认真阅读，积极实践！最全最真的攻略就在这里！<br/><br/><font color=\"yellow\">QQ好友、QQ群、微信好友</font><br/><br/>首先肯定是把赚钱这种好事推荐给朋友啦，相信小伙伴们都会喜欢上余粮的，输入你的ID，朋友就能免费领取3元红包，从此踏上赚钱之旅，绝对会对你感激不尽。<br/><br/>";
	String w2 = "<font color=\"yellow\">微信朋友圈、QQ空间、新浪微博、腾讯微博、人人网等社交网络</font><br/><br/>在社交网络，主要是发动态、发微博、发日志，内容可以是余粮使用心得、经验传授等，还可以晒出你的收入截图，重点是带上邀请链接或者ID。微信朋友圈是个非常靠谱的方式，建议每2天就发布一条晒收入的动态，求好友点赞，求转发，吸引更多朋友加入。<br/><br/> ";
	String w3 = "<font color=\"yellow\">百度贴吧、天涯、猫扑、豆瓣、各种论坛</font><br/><br/>贴吧、论坛聚集了大量兴趣相投的用户，你可以针对该用户群体，发布跟兴趣相关的贴子。<br/>实战经验表明，百度贴吧中，在手机游戏、赚钱等相关、人数超过1万的大吧中发布经验贴，能取得非常好的效果。<br/><br/>";
	String w4 = "<font color=\"yellow\">百度知道、新浪爱问、知乎等问答社区</font><br/><br/>你可以回答关于赚钱、游戏币、游戏升级、生活类…等等相关问题，先热心回答他们的问题，然后再介绍余粮，讲解如何赚取现金，记得带上邀请链接或者ID哦。<br/><br/>";
	String w5 = "<font color=\"yellow\">个人博客、百度经验</font><br/><br/>如果你具有网络写作习惯，可以在自己的个人博客发布经验贴，讲解如何通过余粮赚到现金。喜欢发表评论的朋友，也可以在热门博客文章底部发表评论哦。<br/><br/>最后，只要粮友们吃透了攻略，掌握熟人陌生人收徒诀窍，师门壮大也只是时间问题啦<br/><br/>";
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


