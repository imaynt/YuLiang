package com.zykj.yuliang.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.favorite.WechatFavorite;
import cn.sharesdk.wechat.moments.WechatMoments;

import java.util.HashMap;

import org.apache.http.Header;

import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.RequestParams;
import com.zykj.yuliang.BaseActivity;
import com.zykj.yuliang.BaseApp;
import com.zykj.yuliang.R;
import com.zykj.yuliang.http.HttpErrorHandler;
import com.zykj.yuliang.http.HttpUtils;
import com.zykj.yuliang.http.UrlContants;
import com.zykj.yuliang.utils.CommonUtils;
import com.zykj.yuliang.utils.Tools;
import com.zykj.yuliang.view.MyCommonTitle;

public class YouChangDetailsActivity extends BaseActivity {
	private MyCommonTitle myCommonTitle;
	private LinearLayout ll_zhuanfa;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_youchang_details);
		initView();

	}

	private void initView() {
		myCommonTitle = (MyCommonTitle) findViewById(R.id.aci_mytitle);
		myCommonTitle.setTitle("点击一下，现金到手！");

		ll_zhuanfa = (LinearLayout) findViewById(R.id.ll_zhuanfa);// 转发

		setListener(ll_zhuanfa);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_zhuanfa:
			showShare2(this, "余粮", "余粮是一款通过下载应用广告获得积分的APP", "http://www.pgyer.com/yuliang");
			break;
		default:
			break;
		}
	}
	public static void showShare2(final Context context, String title,String content, String url) {

		ShareSDK.initSDK(context);
		OnekeyShare oks = new OnekeyShare();
		oks.addHiddenPlatform(QQ.NAME);
		oks.addHiddenPlatform(SinaWeibo.NAME);
		oks.addHiddenPlatform(WechatMoments.NAME);
		oks.addHiddenPlatform(WechatFavorite.NAME);
		oks.addHiddenPlatform(QZone.NAME);
		// 关闭sso授权
		oks.disableSSOWhenAuthorize();
		// 分享时Notification的图标和文字 2.5.9以后的版本不调用此方法
		oks.setCallback(new PlatformActionListener() {
			@Override
			public void onError(Platform arg0, int arg1, Throwable arg2) {
				Tools.toast(context, "分享失败");
			}

			@Override
			public void onComplete(Platform arg0, int arg1,
					HashMap<String, Object> arg2) {
				Tools.toast(context, "分享成功");
			}

			@Override
			public void onCancel(Platform arg0, int arg1) {
				Tools.toast(context, "取消分享");
			}
		});
		// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
		oks.setTitle(title);
		// titleUrl是标题的网络链接，仅在人人网和QQ空间使用
		oks.setTitleUrl(url);
		// text是分享文本，所有平台都需要这个字段
		oks.setText(content);
		// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数记得修改哦
		// oks.setImagePath为sdk图片路径
//		oks.setImageUrl("http://dashboard.mob.com/Uploads/db95c30283c2aa827e6831170d70808d.png");// 确保SDcard下面存在此张图片
		// url仅在微信（包括好友和朋友圈）中使用
		oks.setUrl(url);
		// comment是我对这条分享的评论，仅在人人网和QQ空间使用
		oks.setComment("content");
		// site是分享此内容的网站名称，仅在QQ空间使用
		oks.setSite(title);
		// siteUrl是分享此内容的网站地址，仅在QQ空间使用
		oks.setSiteUrl(url);
		// 启动分享GUI
		oks.show(context);
	}

}
