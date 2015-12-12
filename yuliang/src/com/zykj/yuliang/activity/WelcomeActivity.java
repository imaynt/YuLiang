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
		//��ʹ��SDK�����֮ǰ��ʼ��context��Ϣ������ApplicationContext
        //ע��÷���Ҫ��setContentView����֮ǰʵ��
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

				if (is_intro.length() > 0 && version == save_version_int) {// �Ѿ����й�ָ��,�Ұ汾�ŷ���
					should_intro = false;
				} else {
					should_intro = false;//trueΪ��һ�ΰ�װ�����ֲ�ͼ
				}

				if (should_intro) {//��Ҫָ��Ϊtrue,��ת�ֲ�ͼ,ָ������Ϊfalseֱ����ת������
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
//				BaseApp.getModel().setAvatar(avatar.replace("app.do", UrlContants.SERVERIP));//ͷ��
//				BaseApp.getModel().setMobile(StringUtil.toStringOfObject(data.getString("mobile")));//�ֻ���
//				BaseApp.getModel().setMoney(StringUtil.toStringOfObject(data.getString("account")));//�ҵ�Ǯ��
//				BaseApp.getModel().setIntegral(StringUtil.toStringOfObject(data.getString("points")));//����
//				BaseApp.getModel().setPassword(BaseApp.getModel().getPassword());//��¼����
//				BaseApp.getModel().setUserid(StringUtil.toStringOfObject(data.getString("id")));//�û�Id
//				BaseApp.getModel().setUsername(StringUtil.toStringOfObject(data.getString("username")));//��ʵ����
//				BaseApp.getModel().setSign(StringUtil.toStringOfObject(data.getString("sign")));//�Ƿ�ǩ��
//			}
//			@Override
//			public void onRecevieFailed(String status, JSONObject json) {
//				BaseApp.getModel().clear();
//				Tools.toast(WelcomeActivity.this, "��¼ʧЧ!");
//			}
//		}, params);
//	}	
}
