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

	private String userId, account, points, avatar, nick, sex, birth, profession, mobile;// �û���ID�Ƿ������Զ����ɷ��ص�
	private boolean regState;// ע��״̬
	private Boolean flag = true;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ��ʹ��SDK�����֮ǰ��ʼ��context��Ϣ������ApplicationContext
		// ע��÷���Ҫ��setContentView����֮ǰʵ��
		initView(R.layout.ui_welcome);
		// �����ѹ�Ӣ
		PgyCrashManager.register(this);
		// ����ֻ���Ψһ��ʶ
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

					Tools.toast(WelcomeActivity.this, "ע��ɹ�");
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
					Tools.toast(WelcomeActivity.this, "���ֻ���ע��");
					SharedPreferenceUtils.init(WelcomeActivity.this).setIsNewFirst("false");
					regState = false;
					saveUserInfo(json);
				}
				if (flag) {
					startInto();
				}
			}

			/**
			 * ���������Ϣ������
			 * 
			 * @param json
			 */
			private void saveUserInfo(JSONObject json) {
				JSONObject jsonObject = json.getJSONObject(UrlContants.jsonData);
				SharedPreferenceUtils.init(WelcomeActivity.this).setIsOver("false");
				BaseApp.getModel().setDeviceId(DEVICE_ID);
				account = jsonObject.getString("account");// Ԫ��
				BaseApp.getModel().setMoney(account);
				points = jsonObject.getString("points");// ����
				BaseApp.getModel().setIntegral(points);
				userId = jsonObject.getString("id");// �û�id
				BaseApp.getModel().setUserid(userId);
				avatar = jsonObject.getString("avatar");// �û�ͷ��
				BaseApp.getModel().setAvatar(avatar);
				nick = jsonObject.getString("username");// �û��ǳ�
				BaseApp.getModel().setUsername(nick);
				sex = jsonObject.getString("sex");// �û��Ա�
				BaseApp.getModel().setSex(sex);
				birth = jsonObject.getString("birthday").substring(0, 11);// �û�����
				BaseApp.getModel().setBirth(birth);
				profession = jsonObject.getString("profession");// �û�ְҵ
				BaseApp.getModel().setPrefession(profession);
				mobile = jsonObject.getString("mobile");// �ֻ���
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

				if (is_intro.length() > 0 && version == save_version_int) {// �Ѿ����й�ָ��,�Ұ汾�ŷ���
					should_intro = false;
				} else {
					should_intro = true;// trueΪ��һ�ΰ�װ�����ֲ�ͼ
				}
				if (should_intro) {// ��Ҫָ��Ϊtrue,��ת�ֲ�ͼ,ָ������Ϊfalseֱ����ת������
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
	 * ������ʾ��Ϣ,���ȷ������˳�
	 */
	private void createDialog() {
		AlertDialog.Builder builder = new Builder(WelcomeActivity.this);
		builder.setTitle("��ܰ��ʾ");
		builder.setMessage("�������������źŲ��ȶ�����ѡ�����ʵ����������������");
		builder.setPositiveButton("ȷ��", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				try {
					// �ж��Ƿ������ʱ�������ļ�
					File temp_file = new File(
							Environment.getExternalStorageDirectory() + File.separator + BaseApp.FILE_DIR);
					Tools.Log("�ļ��Ƿ���ڣ�" + temp_file.exists());
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
