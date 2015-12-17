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
	
}
