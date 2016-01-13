package com.zykj.yuliang.activity;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.Header;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.location.Location;
import android.os.Bundle;
import android.os.Environment;
import android.telephony.TelephonyManager;

import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.RequestParams;
import com.pgyersdk.crash.PgyCrashManager;
import com.zykj.yuliang.BaseActivity;
import com.zykj.yuliang.BaseApp;
import com.zykj.yuliang.R;
import com.zykj.yuliang.http.HttpErrorHandler;
import com.zykj.yuliang.http.HttpUtils;
import com.zykj.yuliang.http.UrlContants;
import com.zykj.yuliang.utils.SharedPreferenceUtils;
import com.zykj.yuliang.utils.StringUtil;
import com.zykj.yuliang.utils.Tools;

public class WelcomeActivity extends BaseActivity {

	private String userId, account, points, avatar, nick, sex, birth, profession, mobile;// 用户的ID是服务器自动生成返回的
	private boolean regState;// 注册状态
	private Boolean flag = true;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 在使用SDK各组件之前初始化context信息，传入ApplicationContext
		// 注意该方法要再setContentView方法之前实现
		initView(R.layout.ui_welcome);
		// 集成蒲公英
		PgyCrashManager.register(this);
		// 获得手机的唯一标识
		TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		final String DEVICE_ID = tm.getDeviceId();
		// final String DEVICE_ID = "830043457186";

		RequestParams params = new RequestParams();
		params.put("deviceId", DEVICE_ID);
		HttpUtils.autoReg(new HttpErrorHandler() {
			@Override
			public void onRecevieSuccess(JSONObject json) {
				if (json.getString("code").equals("400")) {
					flag = false;
					createDialog();
					
				} else if (json.getString("code").equals("200")) {

					Tools.toast(WelcomeActivity.this, "注册成功");
					regState = true;
					SharedPreferenceUtils.init(WelcomeActivity.this).setIsNew("true");
					SharedPreferenceUtils.init(WelcomeActivity.this).setIsNewFirst("true");
					saveUserInfo(json);
				}
				if (flag) {
					startInto();
				}
			}


			@Override
			public void onRecevieFailed(String status, JSONObject json) {
				super.onRecevieFailed(status, json);
				if (json.getString("code").equals("400")) {
					flag = false;
					
					createDialog();
				
				} else if (json.getString("code").equals("403")) {
					Tools.toast(WelcomeActivity.this, "此手机已注册");
					SharedPreferenceUtils.init(WelcomeActivity.this).setIsNewFirst("false");
					regState = false;
					saveUserInfo(json);
				}
				if (flag) {
					startInto();
				}
			}

			/**
			 * 保存个人信息到本地
			 * 
			 * @param json
			 */
			private void saveUserInfo(JSONObject json) {
				JSONObject jsonObject = json.getJSONObject(UrlContants.jsonData);
				SharedPreferenceUtils.init(WelcomeActivity.this).setIsOver("false");
				BaseApp.getModel().setDeviceId(DEVICE_ID);
				account = jsonObject.getString("account");// 元宝
				BaseApp.getModel().setMoney(account);
				points = jsonObject.getString("points");// 积分
				BaseApp.getModel().setIntegral(points);
				userId = jsonObject.getString("id");// 用户id
				BaseApp.getModel().setUserid(userId);
				avatar = jsonObject.getString("avatar");// 用户头像
				BaseApp.getModel().setAvatar(avatar);
				nick = jsonObject.getString("username");// 用户昵称
				BaseApp.getModel().setUsername(nick);
				sex = jsonObject.getString("sex");// 用户性别
				BaseApp.getModel().setSex(sex);
				birth = jsonObject.getString("birthday").substring(0, 11);// 用户生日
				BaseApp.getModel().setBirth(birth);
				profession = jsonObject.getString("profession");// 用户职业
				BaseApp.getModel().setPrefession(profession);
				mobile = jsonObject.getString("mobile");// 手机号
				BaseApp.getModel().setMobile(mobile);
			}
		}, params);
		
	}

	private void startInto() {
		// checkLogin();
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {

			public void run() {
				String is_intro = getSharedPreferenceValue(BaseApp.IS_INTRO);
				boolean should_intro = false;
				int version = Tools.getAppVersion(WelcomeActivity.this);
				String save_version = getSharedPreferenceValue(BaseApp.VERSION);
				int save_version_int = save_version.equals("") ? -1 : Integer.parseInt(save_version);

				if (is_intro.length() > 0 && version == save_version_int) {// 已经进行过指引,且版本号符合
					should_intro = false;
				} else {
					should_intro = true;// true为第一次安装进入轮播图
				}
				if (should_intro) {// 需要指引为true,跳转轮播图,指引过了为false直接跳转主界面
					Intent intent = new Intent(WelcomeActivity.this, IntroActivity.class);
					startActivity(intent);
				} else if (regState) {
					startActivity(new Intent(WelcomeActivity.this, FirstLoginActivity.class));
				} else {
					startActivity(new Intent(WelcomeActivity.this, MainActivity.class).putExtra("userId", userId));
				}
				finish();
			}
		};

		timer.schedule(task, 2000);

	}
	/**
	 * 弹框提示信息,点击确定软件退出
	 */
	private void createDialog() {
		AlertDialog.Builder builder = new Builder(WelcomeActivity.this);
		builder.setTitle("温馨提示");
		builder.setMessage("由于您的网络信号不稳定，请选择优质的网络再运行软件！");
		builder.setPositiveButton("确定", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				try {
					// 判断是否存在临时创建的文件
					File temp_file = new File(
							Environment.getExternalStorageDirectory() + File.separator + BaseApp.FILE_DIR);
					Tools.Log("文件是否存在：" + temp_file.exists());
					if (temp_file.exists()) {
						File[] file_detail = temp_file.listFiles();
						for (File file_del : file_detail) {
							file_del.delete();
						}
						temp_file.delete();
					}
				} catch (Exception e) {
				}
				System.exit(0);
			}
		});
		builder.create().show();
		
	}
}
