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

	public static final String ERROR = "{\"code\":\"400\",\"message\":\"请求失败\",\"datas\":null}";

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
	
	
	
	
	
	
	

	public static final String GETADSLIST = "c=public&a=getAdsList&type=slideFocus";// 轮播图

	// http://121.42.194.222/api.php?c=public&a=getArticleInfo&type=about&id=1

	public static final String GETABOUTUS = "c=public&a=getArticleInfo";// 关于我们

	public static final String GETORDERLIST = "c=info&a=getOrderList";// 获取订单

	public static final String GETORDER = "c=info&a=getOrder";// 获取订单详情

	public static final String LOGIN = "c=user&a=login";// 登录

	public static final String REGISTER = "c=user&a=reg";// 注册

	public static final String RESETPASSWORD = "c=user&a=resetPassword";// 重置密码

	public static final String RESETUSERNAME = "c=user&a=resetUsername";// 修改昵称

	public static final String LIKELIST = "c=info&a=getLikeList";// 猜你喜欢

	public static final String POSIUSERAVATAR = "c=user&a=postUserAvatar";// 上传头像

	public static final String GETCATEGORY = "c=info&a=getcategory";// 获取餐饮、酒店、商铺分类

	public static final String GETRESTAURANTS = "c=info&a=getlist";// 获取餐饮、酒店、商铺列表

	public static final String GETGOODLIST = "c=info&a=getgoodslist";// 获取餐饮、酒店、商铺商品

	public static final String COMMENTLIST = "c=info&a=getCommentsList";// 获取评价列表

	// public static final String CARLIST = "c=car&a=getlist";//获取列表

	// public static final String CARINFO = "c=car&a=getinfo";//获取列表

	public static final String NEEDCAR = "c=car&a=needcar";// 我要拼车

	public static final String GETCARLIST = "c=car&a=getlist";// 拼车列表

	public static final String OFFERCAR = "c=car&a=offercar";// 我要拼车

	public static final String POSTCARORDER = "c=car&a=carorder";// 我要拼车

	public static final String GETAREA = "c=public&a=getArea";// 获取城市列表

	public static final String SUBMIT = "c=info&a=postOrder";// 提交餐饮、酒店

	public static final String GETGOODS = "c=info&a=getGoods";// 获取产品详情

	public static final String GETBIANMINLIST = "c=services&a=getList";// 获取便民列表

	public static final String GETZHAOPINCATEGORY = "c=hr&a=getCategory";// 获取招聘类别

	public static final String GETZHAOPINLIST = "c=hr&a=getList";// 获取招聘列表

	public static final String SUBMITZHAOPININFO = "c=hr&a=postInfo";// 发布招聘信息

	public static final String GETZHAOPININFO = "c=hr&a=getInfo";// 获取招聘详情

	public static final String GETHOUSELIST = "c=house&a=getList";// 获取房产列表

	public static final String SUBMITHOUSEINFO = "c=house&a=postInfo";// 发布房产信息

	public static final String GETHOUSEINFO = "c=house&a=getInfo";// 获取房产详情

	public static final String SUBMITSUPPLYDEMANDINFO = "c=offer&a=postInfo";// 发布供求信息

	public static final String GETSUPPLYDEMANDLIST = "c=offer&a=getList";// 获取供求列表

	public static final String GETSUPPLYDEMANDINFO = "c=offer&a=getInfo";// 获取供求详情

	public static final String GOODSCOMMENTSLIST = "c=info&a=getGoodsCommentsList&type=shop";// 获取产品评论列表

	public static final String SUBMITSHOPORDER = "c=info&a=postShopOrder";// 提交团购

	public static final String UPDATEORDER = "c=info&a=updateorder";// 是否签到

	public static final String POSTCOMMENTS = "c=info&a=postComments";// 提交评论

	public static final String DELORDER = "c=info&a=delOrder";// 删除订单

	public static final String ADDCOLLECTION = "c=user&a=addcollection";// 添加收藏

	public static final String GETCOLLECTIONLIST = "c=user&a=getCollectionList";// 获取收藏列表

	public static final String DELETECOLLECTIONLIST = "c=user&a=deleteCollectionList";// 获取收藏列表

	public static final String GETCOLLECTIONINFO = "c=info&a=getCollectionInfo";// 获取收藏详情

	public static final String UPLOADONE = "c=public&a=upload_one";// 图片单张上传

	public static final String SHOPDETAIL = "c=info&a=getinfo";// 获取酒店详情

	public static final String DELCOLLECTIONINFO = "c=user&a=delCollectionInfo";// 获取酒店详情

	public static String getUrl(String token) {
		if (token == null || token.equals("")) {
			return BASE_URL;
		}
		return String.format(BASEURL, token);
	}
}
