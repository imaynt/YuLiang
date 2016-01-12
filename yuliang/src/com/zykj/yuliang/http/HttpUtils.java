package com.zykj.yuliang.http;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by csh on 15-7-21.
 */
public class HttpUtils {

	private HttpUtils() {

	}

	private static AsyncHttpClient client = new AsyncHttpClient();

	static {
		// ʹ��Ĭ�ϵ� cacheThreadPool
		client.setTimeout(15);
		client.setConnectTimeout(15);
		client.setMaxConnections(20);
		client.setResponseTimeout(20);
	}

	/* �Զ�ע�� */
	public static void autoReg(AsyncHttpResponseHandler handler,
			RequestParams params) {
		client.get(UrlContants.getUrl(UrlContants.AUTOREG), params, handler);
	}

	/* �Ż�ȯ */
	public static void getLoginUrl(AsyncHttpResponseHandler handler,
			RequestParams params) {
		client.get(UrlContants.getUrl(UrlContants.GETLOGINURL), params, handler);
	}

	/* �޸ĸ������� */
	public static void updateUserInfo(AsyncHttpResponseHandler handler,
			RequestParams params) {
		client.get(UrlContants.getUrl(UrlContants.UPDATEUSERINFO), params,
				handler);
	}

	/* ���ֻ� */
	public static void bindMobile(AsyncHttpResponseHandler handler,
			RequestParams params) {
		client.post(UrlContants.getUrl(UrlContants.BINDMOBILE), params, handler);
	}

	/* �ϴ�ͷ�� */
	public static void postUserAvatar(AsyncHttpResponseHandler handler,
			RequestParams params) {
		client.post(UrlContants.getUrl(UrlContants.POSTUSERAVATAR), params,
				handler);
	}
	/* ��ȡ���� */
	public static void getPoints(AsyncHttpResponseHandler handler,
			RequestParams params) {
		client.post(UrlContants.getUrl(UrlContants.GETPOINTS), params,
				handler);
	}
	
	/* ��һ��ͨ������ID��ȡ���� */
	public static void getPointsFromInvite(AsyncHttpResponseHandler handler,
			RequestParams params) {
		client.post(UrlContants.getUrl(UrlContants.INVITEPOINTS), params,
				handler);
	}
	/* �������� */
	public static void getTodayIncome(AsyncHttpResponseHandler handler,
			RequestParams params) {
		client.post(UrlContants.getUrl(UrlContants.TODAYINCOME), params,
				handler);
	}
	/* ������ͽ */
	public static void getTodayChildren(AsyncHttpResponseHandler handler,
			RequestParams params) {
		client.post(UrlContants.getUrl(UrlContants.TODAYCHILDREN), params,
				handler);
	}
	/*֧��������*/
	public static void postTiXian(AsyncHttpResponseHandler handler,
			RequestParams params) {
		client.post(UrlContants.getUrl(UrlContants.POSTTIXIAN), params,
				handler);
	}
	/*�ֻ���ֵ*/
	public static void postMobileFees(AsyncHttpResponseHandler handler,
			RequestParams params) {
		client.post(UrlContants.getUrl(UrlContants.POSTMOBILEFEES), params,
				handler);
	}
	
	/*���ֽ̳̺͸�����������*/
	public static void postNewAndPersonal(AsyncHttpResponseHandler handler,
			RequestParams params) {
		client.post(UrlContants.getUrl(UrlContants.NEWANDPERSONAL), params,
				handler);
	}
	
	/*���ֽ̳̺͸�����������״̬*/
	public static void postNewAndPersonalstate(AsyncHttpResponseHandler handler,
			RequestParams params) {
		client.post(UrlContants.getUrl(UrlContants.NEWANDPERSONALSTATE), params,
				handler);
	}
	
	/*�л��˺�*/
	public static void changeUserId(AsyncHttpResponseHandler handler,
			RequestParams params) {
		client.post(UrlContants.getUrl(UrlContants.CHANGEUSERID), params,
				handler);
	}
	/*����׬Ǯ��ϸ*/
	public static void allTaskList(AsyncHttpResponseHandler handler,
			RequestParams params) {
		client.post(UrlContants.getUrl(UrlContants.ALLTASKLIST), params,
				handler);
	}
	/*����׬Ǯ��ϸ*/
	public static void taskList(AsyncHttpResponseHandler handler,
			RequestParams params) {
		client.post(UrlContants.getUrl(UrlContants.TASKLIST), params,
				handler);
	}
	/*��ͽ׬Ǯ��ϸ*/
	public static void shouTuTaskList(AsyncHttpResponseHandler handler,
			RequestParams params) {
		client.post(UrlContants.getUrl(UrlContants.SHOUTUTASKLIST), params,
				handler);
	}
	/*�һ�׬Ǯ��ϸ*/
	public static void duiHuanTaskList(AsyncHttpResponseHandler handler,
			RequestParams params) {
		client.post(UrlContants.getUrl(UrlContants.DUIHUANTASKLIST), params,
				handler);
	}
	/*ת��׬Ǯ*/
	public static void zhuanFa(AsyncHttpResponseHandler handler,
			RequestParams params) {
		client.post(UrlContants.getUrl(UrlContants.ZHUANFA), params,
				handler);
	}
	/*�ɼ�����������*/
	public static void getScoreList(AsyncHttpResponseHandler handler,
			RequestParams params) {
		client.post(UrlContants.getUrl(UrlContants.GETSCORELIST), params,
				handler);
	}
	/*ͽ���б�*/
	public static void getChildrenList(AsyncHttpResponseHandler handler,
			RequestParams params) {
		client.post(UrlContants.getUrl(UrlContants.GETCHILDRENLIST), params,
				handler);
	}
	/*ͽ������*/
	public static void getChildrenInfo(AsyncHttpResponseHandler handler,
			RequestParams params) {
		client.post(UrlContants.getUrl(UrlContants.GETCHILDRENINFO), params,
				handler);
	}
	/*ʦ����ͽ�������õ����*/
	public static void getIncomeFromTudi(AsyncHttpResponseHandler handler,
			RequestParams params) {
		client.post(UrlContants.getUrl(UrlContants.GETINCOMEFROMTUDI), params,
				handler);
	}
	
}
