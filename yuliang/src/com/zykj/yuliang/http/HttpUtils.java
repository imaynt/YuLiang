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
		// 使用默认的 cacheThreadPool
		client.setTimeout(15);
		client.setConnectTimeout(15);
		client.setMaxConnections(20);
		client.setResponseTimeout(20);
	}

	/* 自动注册 */
	public static void autoReg(AsyncHttpResponseHandler handler,
			RequestParams params) {
		client.get(UrlContants.getUrl(UrlContants.AUTOREG), params, handler);
	}

	/* 优惠券 */
	public static void getLoginUrl(AsyncHttpResponseHandler handler,
			RequestParams params) {
		client.get(UrlContants.getUrl(UrlContants.GETLOGINURL), params, handler);
	}

	/* 修改个人资料 */
	public static void updateUserInfo(AsyncHttpResponseHandler handler,
			RequestParams params) {
		client.get(UrlContants.getUrl(UrlContants.UPDATEUSERINFO), params,
				handler);
	}

	/* 绑定手机 */
	public static void bindMobile(AsyncHttpResponseHandler handler,
			RequestParams params) {
		client.post(UrlContants.getUrl(UrlContants.BINDMOBILE), params, handler);
	}

	/* 上传头像 */
	public static void postUserAvatar(AsyncHttpResponseHandler handler,
			RequestParams params) {
		client.post(UrlContants.getUrl(UrlContants.POSTUSERAVATAR), params,
				handler);
	}
	/* 获取积分 */
	public static void getPoints(AsyncHttpResponseHandler handler,
			RequestParams params) {
		client.post(UrlContants.getUrl(UrlContants.GETPOINTS), params,
				handler);
	}
	
}
