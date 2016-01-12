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
	
	/* 第一次通过邀请ID获取积分 */
	public static void getPointsFromInvite(AsyncHttpResponseHandler handler,
			RequestParams params) {
		client.post(UrlContants.getUrl(UrlContants.INVITEPOINTS), params,
				handler);
	}
	/* 今日收入 */
	public static void getTodayIncome(AsyncHttpResponseHandler handler,
			RequestParams params) {
		client.post(UrlContants.getUrl(UrlContants.TODAYINCOME), params,
				handler);
	}
	/* 今日收徒 */
	public static void getTodayChildren(AsyncHttpResponseHandler handler,
			RequestParams params) {
		client.post(UrlContants.getUrl(UrlContants.TODAYCHILDREN), params,
				handler);
	}
	/*支付宝提现*/
	public static void postTiXian(AsyncHttpResponseHandler handler,
			RequestParams params) {
		client.post(UrlContants.getUrl(UrlContants.POSTTIXIAN), params,
				handler);
	}
	/*手机充值*/
	public static void postMobileFees(AsyncHttpResponseHandler handler,
			RequestParams params) {
		client.post(UrlContants.getUrl(UrlContants.POSTMOBILEFEES), params,
				handler);
	}
	
	/*新手教程和个人资料完善*/
	public static void postNewAndPersonal(AsyncHttpResponseHandler handler,
			RequestParams params) {
		client.post(UrlContants.getUrl(UrlContants.NEWANDPERSONAL), params,
				handler);
	}
	
	/*新手教程和个人资料完善状态*/
	public static void postNewAndPersonalstate(AsyncHttpResponseHandler handler,
			RequestParams params) {
		client.post(UrlContants.getUrl(UrlContants.NEWANDPERSONALSTATE), params,
				handler);
	}
	
	/*切换账号*/
	public static void changeUserId(AsyncHttpResponseHandler handler,
			RequestParams params) {
		client.post(UrlContants.getUrl(UrlContants.CHANGEUSERID), params,
				handler);
	}
	/*所有赚钱明细*/
	public static void allTaskList(AsyncHttpResponseHandler handler,
			RequestParams params) {
		client.post(UrlContants.getUrl(UrlContants.ALLTASKLIST), params,
				handler);
	}
	/*任务赚钱明细*/
	public static void taskList(AsyncHttpResponseHandler handler,
			RequestParams params) {
		client.post(UrlContants.getUrl(UrlContants.TASKLIST), params,
				handler);
	}
	/*收徒赚钱明细*/
	public static void shouTuTaskList(AsyncHttpResponseHandler handler,
			RequestParams params) {
		client.post(UrlContants.getUrl(UrlContants.SHOUTUTASKLIST), params,
				handler);
	}
	/*兑换赚钱明细*/
	public static void duiHuanTaskList(AsyncHttpResponseHandler handler,
			RequestParams params) {
		client.post(UrlContants.getUrl(UrlContants.DUIHUANTASKLIST), params,
				handler);
	}
	/*转发赚钱*/
	public static void zhuanFa(AsyncHttpResponseHandler handler,
			RequestParams params) {
		client.post(UrlContants.getUrl(UrlContants.ZHUANFA), params,
				handler);
	}
	/*成绩单内容数据*/
	public static void getScoreList(AsyncHttpResponseHandler handler,
			RequestParams params) {
		client.post(UrlContants.getUrl(UrlContants.GETSCORELIST), params,
				handler);
	}
	/*徒弟列表*/
	public static void getChildrenList(AsyncHttpResponseHandler handler,
			RequestParams params) {
		client.post(UrlContants.getUrl(UrlContants.GETCHILDRENLIST), params,
				handler);
	}
	/*徒弟详情*/
	public static void getChildrenInfo(AsyncHttpResponseHandler handler,
			RequestParams params) {
		client.post(UrlContants.getUrl(UrlContants.GETCHILDRENINFO), params,
				handler);
	}
	/*师傅从徒弟收入获得的提成*/
	public static void getIncomeFromTudi(AsyncHttpResponseHandler handler,
			RequestParams params) {
		client.post(UrlContants.getUrl(UrlContants.GETINCOMEFROMTUDI), params,
				handler);
	}
	
}
