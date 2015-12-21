package com.zykj.yuliang.activity;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.Header;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.telephony.TelephonyManager;

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

	private String userId, coins, points, avatar, nick, sex, birth, profession,
			mobile;// 用户的ID是服务器自动生成返回的
	private boolean regState;// 注册状态

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 在使用SDK各组件之前初始化context信息，传入ApplicationContext
		// 注意该方法要再setContentView方法之前实现
		initView(R.layout.ui_welcome);
		// 获得手机的唯一标识
		TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		final String DEVICE_ID = tm.getDeviceId();

		RequestParams params = new RequestParams();
		params.put("deviceId", DEVICE_ID);
		HttpUtils.autoReg(new HttpErrorHandler() {
			@Override
			public void onRecevieSuccess(JSONObject json) {
				if (json.getString("code").equals("200")) {
					Tools.toast(WelcomeActivity.this, "注册成功");
					regState = true;
					saveUserInfo(json);
				}
			}

			@Override
			public void onRecevieFailed(String status, JSONObject json) {
				super.onRecevieFailed(status, json);
				if (json.getString("code").equals("403")) {
					Tools.toast(WelcomeActivity.this, "此手机已注册");
					regState = false;
					saveUserInfo(json);
				}
			}
			/**
			 * 保存个人信息到本地
			 * 
			 * @param json
			 */
			private void saveUserInfo(JSONObject json) {
				JSONObject jsonObject = json
						.getJSONObject(UrlContants.jsonData);
				BaseApp.getModel().setDeviceId(DEVICE_ID);
				coins = jsonObject.getString("coins");//元宝
				BaseApp.getModel().setMoney(coins);
				points = jsonObject.getString("points");//积分
				BaseApp.getModel().setIntegral(points);
				userId = jsonObject.getString("id");//用户id
				BaseApp.getModel().setUserid(userId);
				avatar = jsonObject.getString("avatar");//用户头像
				BaseApp.getModel().setAvatar(avatar);
				nick = jsonObject.getString("username");//用户昵称
				BaseApp.getModel().setUsername(nick);
				sex = jsonObject.getString("sex");//用户性别
				BaseApp.getModel().setSex(sex);
				birth = jsonObject.getString("birthday").substring(0, 11);//用户生日
				BaseApp.getModel().setBirth(birth);
				profession = jsonObject.getString("profession");//用户职业
				BaseApp.getModel().setPrefession(profession);
				mobile = jsonObject.getString("mobile");//手机号
				BaseApp.getModel().setMobile(mobile);
			}
		}, params);

		// checkLogin();
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
					should_intro = false;// true为第一次安装进入轮播图
				}

				if (should_intro) {// 需要指引为true,跳转轮播图,指引过了为false直接跳转主界面
					Intent intent = new Intent(WelcomeActivity.this,
							IntroActivity.class);
					startActivity(intent);
				} else if (regState) {
					startActivity(new Intent(WelcomeActivity.this,
							FirstLoginActivity.class));
				} else {
					startActivity(new Intent(WelcomeActivity.this,
							MainActivity.class).putExtra("userId", userId));
				}
				finish();
			}
		};
		timer.schedule(task, 2000);

	}

	// private void checkLogin(){
	// if(StringUtil.isEmpty(BaseApp.getModel().getUsername())){
	// return;
	// }
	// RequestParams params = new RequestParams();
	// params.put("mob", BaseApp.getModel().getMobile());
	// params.put("pass", BaseApp.getModel().getPassword());
	// HttpUtils.login(new HttpErrorHandler() {
	// @Override
	// public void onRecevieSuccess(JSONObject json) {
	// JSONObject data = json.getJSONObject(UrlContants.jsonData);
	// String avatar = StringUtil.toStringOfObject(data.getString("avatar"));
	// BaseApp.getModel().setAvatar(avatar.replace("app.do",
	// UrlContants.SERVERIP));//头像
	// BaseApp.getModel().setMobile(StringUtil.toStringOfObject(data.getString("mobile")));//手机号
	// BaseApp.getModel().setMoney(StringUtil.toStringOfObject(data.getString("account")));//我的钱包
	// BaseApp.getModel().setIntegral(StringUtil.toStringOfObject(data.getString("points")));//积分
	// BaseApp.getModel().setPassword(BaseApp.getModel().getPassword());//登录密码
	// BaseApp.getModel().setUserid(StringUtil.toStringOfObject(data.getString("id")));//用户Id
	// BaseApp.getModel().setUsername(StringUtil.toStringOfObject(data.getString("username")));//真实姓名
	// BaseApp.getModel().setSign(StringUtil.toStringOfObject(data.getString("sign")));//是否签到
	// }
	// @Override
	// public void onRecevieFailed(String status, JSONObject json) {
	// BaseApp.getModel().clear();
	// Tools.toast(WelcomeActivity.this, "登录失效!");
	// }
	// }, params);
	// }
}
