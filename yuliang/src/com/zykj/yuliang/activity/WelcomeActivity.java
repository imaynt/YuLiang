package com.zykj.yuliang.activity;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;

import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.RequestParams;
import com.zykj.yuliang.BaseActivity;
import com.zykj.yuliang.BaseApp;
import com.zykj.yuliang.R;
import com.zykj.yuliang.http.HttpErrorHandler;
import com.zykj.yuliang.http.HttpUtils;
import com.zykj.yuliang.http.UrlContants;
import com.zykj.yuliang.utils.StringUtil;
import com.zykj.yuliang.utils.Tools;

public class WelcomeActivity extends BaseActivity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
		initView(R.layout.ui_welcome);
		
//		checkLogin();
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			public void run() {
				String is_intro = getSharedPreferenceValue(BaseApp.IS_INTRO);
				boolean should_intro = false;
				int version = Tools.getAppVersion(WelcomeActivity.this);
				String save_version = getSharedPreferenceValue(BaseApp.VERSION);
				int save_version_int = save_version.equals("") ? -1 : Integer
						.parseInt(save_version);

				if (is_intro.length() > 0 && version == save_version_int) {// 已经进行过指引,且版本号符合
					should_intro = false;
				} else {
					should_intro = false;//true为第一次安装进入轮播图
				}

				if (should_intro) {//需要指引为true,跳转轮播图,指引过了为false直接跳转主界面
					Intent intent = new Intent(WelcomeActivity.this, IntroActivity.class);
					startActivity(intent);
				} else {
					Intent intent = new Intent(WelcomeActivity.this, FirstLoginActivity.class);
					startActivity(intent);
				}
				finish();

			}
		};
		timer.schedule(task, 2000);
	}
	
//	private void checkLogin(){
//		if(StringUtil.isEmpty(BaseApp.getModel().getUsername())){
//			return;
//		}
//        RequestParams params = new RequestParams();
//        params.put("mob", BaseApp.getModel().getMobile());
//        params.put("pass", BaseApp.getModel().getPassword());
//        HttpUtils.login(new HttpErrorHandler() {
//			@Override
//			public void onRecevieSuccess(JSONObject json) {
//				JSONObject data = json.getJSONObject(UrlContants.jsonData);
//				String avatar = StringUtil.toStringOfObject(data.getString("avatar"));
//				BaseApp.getModel().setAvatar(avatar.replace("app.do", UrlContants.SERVERIP));//头像
//				BaseApp.getModel().setMobile(StringUtil.toStringOfObject(data.getString("mobile")));//手机号
//				BaseApp.getModel().setMoney(StringUtil.toStringOfObject(data.getString("account")));//我的钱包
//				BaseApp.getModel().setIntegral(StringUtil.toStringOfObject(data.getString("points")));//积分
//				BaseApp.getModel().setPassword(BaseApp.getModel().getPassword());//登录密码
//				BaseApp.getModel().setUserid(StringUtil.toStringOfObject(data.getString("id")));//用户Id
//				BaseApp.getModel().setUsername(StringUtil.toStringOfObject(data.getString("username")));//真实姓名
//				BaseApp.getModel().setSign(StringUtil.toStringOfObject(data.getString("sign")));//是否签到
//			}
//			@Override
//			public void onRecevieFailed(String status, JSONObject json) {
//				BaseApp.getModel().clear();
//				Tools.toast(WelcomeActivity.this, "登录失效!");
//			}
//		}, params);
//	}	
}
