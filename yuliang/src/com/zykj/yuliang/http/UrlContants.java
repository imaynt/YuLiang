package com.zykj.yuliang.http;

/**
 * @author Administrator 服务器路径
 */
public class UrlContants {

	// public static final String SERVERIP = "121.42.194.222";
	// public static final String SERVERIP = "192.168.1.175";
	public static final String SERVERIP = "http://115.28.67.86/yl/";

	// public static final String BASE_URL = "http://121.42.194.222/api.php?";
	// public static final String BASE_URL = "http://192.168.1.175/api.php?";
	public static final String BASE_URL = "http://115.28.67.86/yl/api.php?";

	// public static final String IMAGE_URL = "http://121.42.194.222/Uploads/";
	// public static final String IMAGE_URL = "http://192.168.1.175/Uploads/";
	public static final String IMAGE_URL = "http://115.28.67.86/yl/Uploads/";

	// public static final String ORDERPAY =
	// "http://121.42.194.222/pingxx/api/pay.php";// 支付
	// public static final String ORDERPAY =
	// "http://192.168.1.175/pingxx/api/pay.php";// 支付
	public static final String ORDERPAY = "http://115.28.67.86/yl/pingxx/api/pay.php";// 支付

	public static final String BASEURL = BASE_URL + "%s";

	public static final String jsonData = "datas";

	public static final String ERROR = "{\"code\":400,\"message\":\"请求失败\",\"datas\":null}";

	public static final String ZERODATA = "{\"code\":200,\"message\":\"没有数据\",\"datas\":\"\"}";

	public static final String AUTOREG = "c=user&a=auto_reg";// 自动注册

	public static final String GETLOGINURL = "c=points&a=getLoginUrl";// 优惠券

	public static final String BINDMOBILE = "c=user&a=bindphone";// 绑定手机

	public static final String POSTUSERAVATAR = "c=user&a=postUserAvatar";// 上传头像

	public static final String UPDATEUSERINFO = "c=user&a=resetUsername";// 修改个人资料

	public static final String GETPOINTS = "c=user&a=getPoints";// 获取实时积分

	public static final String INVITEPOINTS = "c=user&a=invi";// 邀请获得积分

	public static final String TODAYINCOME = "c=points&a=todays_money";// 今日收入

	public static final String TODAYCHILDREN = "c=user&a=today_children";// 今日收徒

	public static final String POSTTIXIAN = "c=user&a=posttixian";// 支付宝提现

	public static final String POSTMOBILEFEES = "c=user&a=postfees";// 手机充值

	public static final String NEWANDPERSONAL = "c=user&a=new_and_personal";// 新手教程和个人资料完善

	public static final String CHANGEUSERID = "c=user&a=login_v";// 切换账号
	
	public static final String ALLTASKLIST = "c=points&a=alltask";//所有赚钱明细
	
	public static final String TASKLIST = "c=points&a=alltask_renwu";//任务赚钱明细
	
	public static final String SHOUTUTASKLIST = "c=points&a=alltask_shoutu";//收徒赚钱明细
	
	public static final String DUIHUANTASKLIST = "c=points&a=alltask_duihuan";//兑换赚钱明细
	
	public static final String ZHUANFA = "c=user&a=forword_get_money";//转发
	
	public static final String GETSCORELIST = "c=user&a=children_sum";//成绩单里面的详细
	
	public static final String GETCHILDRENLIST = "c=user&a=children_list";//徒弟列表
	
	public static final String GETCHILDRENINFO = "c=user&a=children_info";//徒弟详情
	
	public static final String GETINCOMEFROMTUDI = "c=user&a=children_com";//师傅获得徒弟收入提成
	
	
	
	public static String getUrl(String token) {
		if (token == null || token.equals("")) {
			return BASE_URL;
		}
		return String.format(BASEURL, token);
	}
}
