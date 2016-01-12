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
		myCommonTitle.setTitle("���һ�£��ֽ��֣�");

		ll_zhuanfa = (LinearLayout) findViewById(R.id.ll_zhuanfa);// ת��

		setListener(ll_zhuanfa);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_zhuanfa:
			showShare2(this, "����", "������һ��ͨ������Ӧ�ù���û��ֵ�APP", "http://www.pgyer.com/yuliang");
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
		// �ر�sso��Ȩ
		oks.disableSSOWhenAuthorize();
		// ����ʱNotification��ͼ������� 2.5.9�Ժ�İ汾�����ô˷���
		oks.setCallback(new PlatformActionListener() {
			@Override
			public void onError(Platform arg0, int arg1, Throwable arg2) {
				Tools.toast(context, "����ʧ��");
			}

			@Override
			public void onComplete(Platform arg0, int arg1,
					HashMap<String, Object> arg2) {
				Tools.toast(context, "����ɹ�");
			}

			@Override
			public void onCancel(Platform arg0, int arg1) {
				Tools.toast(context, "ȡ������");
			}
		});
		// title���⣬ӡ��ʼǡ����䡢��Ϣ��΢�š���������QQ�ռ�ʹ��
		oks.setTitle(title);
		// titleUrl�Ǳ�����������ӣ�������������QQ�ռ�ʹ��
		oks.setTitleUrl(url);
		// text�Ƿ����ı�������ƽ̨����Ҫ����ֶ�
		oks.setText(content);
		// imagePath��ͼƬ�ı���·����Linked-In�����ƽ̨��֧�ִ˲����ǵ��޸�Ŷ
		// oks.setImagePathΪsdkͼƬ·��
//		oks.setImageUrl("http://dashboard.mob.com/Uploads/db95c30283c2aa827e6831170d70808d.png");// ȷ��SDcard������ڴ���ͼƬ
		// url����΢�ţ��������Ѻ�����Ȧ����ʹ��
		oks.setUrl(url);
		// comment���Ҷ�������������ۣ�������������QQ�ռ�ʹ��
		oks.setComment("content");
		// site�Ƿ�������ݵ���վ���ƣ�����QQ�ռ�ʹ��
		oks.setSite(title);
		// siteUrl�Ƿ�������ݵ���վ��ַ������QQ�ռ�ʹ��
		oks.setSiteUrl(url);
		// ��������GUI
		oks.show(context);
	}

}
