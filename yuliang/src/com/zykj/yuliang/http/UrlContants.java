package com.zykj.yuliang.http;

/**
 * @author Administrator ������·��
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
	// "http://121.42.194.222/pingxx/api/pay.php";// ֧��
	// public static final String ORDERPAY =
	// "http://192.168.1.175/pingxx/api/pay.php";// ֧��
	public static final String ORDERPAY = "http://115.28.67.86/yl/pingxx/api/pay.php";// ֧��

	public static final String BASEURL = BASE_URL + "%s";

	public static final String jsonData = "datas";

	public static final String ERROR = "{\"code\":400,\"message\":\"����ʧ��\",\"datas\":null}";

	public static final String ZERODATA = "{\"code\":200,\"message\":\"û������\",\"datas\":\"\"}";

	public static final String AUTOREG = "c=user&a=auto_reg";// �Զ�ע��

	public static final String GETLOGINURL = "c=points&a=getLoginUrl";// �Ż�ȯ

	public static final String BINDMOBILE = "c=user&a=bindphone";// ���ֻ�

	public static final String POSTUSERAVATAR = "c=user&a=postUserAvatar";// �ϴ�ͷ��

	public static final String UPDATEUSERINFO = "c=user&a=resetUsername";// �޸ĸ�������

	public static final String GETPOINTS = "c=user&a=getPoints";// ��ȡʵʱ����

	public static final String INVITEPOINTS = "c=user&a=invi";// �����û���

	public static final String TODAYINCOME = "c=points&a=todays_money";// ��������

	public static final String TODAYCHILDREN = "c=user&a=today_children";// ������ͽ

	public static final String POSTTIXIAN = "c=user&a=posttixian";// ֧��������

	public static final String POSTMOBILEFEES = "c=user&a=postfees";// �ֻ���ֵ

	public static final String NEWANDPERSONAL = "c=user&a=new_and_personal";// ���ֽ̳̺͸�����������

	public static final String CHANGEUSERID = "c=user&a=login_v";// �л��˺�
	
	public static final String ALLTASKLIST = "c=points&a=alltask";//����׬Ǯ��ϸ
	
	public static final String TASKLIST = "c=points&a=alltask_renwu";//����׬Ǯ��ϸ
	
	public static final String SHOUTUTASKLIST = "c=points&a=alltask_shoutu";//��ͽ׬Ǯ��ϸ
	
	public static final String DUIHUANTASKLIST = "c=points&a=alltask_duihuan";//�һ�׬Ǯ��ϸ
	
	public static final String ZHUANFA = "c=user&a=forword_get_money";//ת��
	
	public static final String GETSCORELIST = "c=user&a=children_sum";//�ɼ����������ϸ
	
	public static final String GETCHILDRENLIST = "c=user&a=children_list";//ͽ���б�
	
	public static final String GETCHILDRENINFO = "c=user&a=children_info";//ͽ������
	
	public static final String GETINCOMEFROMTUDI = "c=user&a=children_com";//ʦ�����ͽ���������
	
	
	
	public static String getUrl(String token) {
		if (token == null || token.equals("")) {
			return BASE_URL;
		}
		return String.format(BASEURL, token);
	}
}
