package com.zykj.yuliang.http;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;



/**
 * Created by csh on 15-7-21.
 */
public class HttpUtils {

    private HttpUtils(){

    }

    private static AsyncHttpClient client = new AsyncHttpClient();

    static {
        //ʹ��Ĭ�ϵ� cacheThreadPool
        client.setTimeout(15);
        client.setConnectTimeout(15);
        client.setMaxConnections(20);
        client.setResponseTimeout(20);
    }
    
    /*�ֲ�ͼ*/
    public static void getAdsList(AsyncHttpResponseHandler handler, String type){
        client.get(UrlContants.getUrl(UrlContants.GETADSLIST)+"&id="+type, handler);
    }
 	
 	  /*��������*/
 		public static void getAboutUs(AsyncHttpResponseHandler handler,RequestParams params){
        client.post(UrlContants.getUrl(UrlContants.GETABOUTUS),params,  handler);
    }
    
    /*�㵥�б�*/
    public static void getOrderList(AsyncHttpResponseHandler handler, RequestParams params){
        client.post(UrlContants.getUrl(UrlContants.GETORDERLIST), params, handler);
    }
    
    /*�㵥����*/
    public static void getOrder(AsyncHttpResponseHandler handler, String orderid){
        client.get(UrlContants.getUrl(UrlContants.GETORDER)+"&id="+orderid, handler);
    }
    
    /*�û���¼*/
    public static void login(AsyncHttpResponseHandler handler, RequestParams params){
        client.post(UrlContants.getUrl(UrlContants.LOGIN), params, handler);
    }
    
    /*�û�ע��*/
    public static void register(AsyncHttpResponseHandler handler, RequestParams params){
        client.post(UrlContants.getUrl(UrlContants.REGISTER), params, handler);
    }
    
    /*��������*/
    public static void resetPassword(AsyncHttpResponseHandler handler, RequestParams params){
        client.post(UrlContants.getUrl(UrlContants.RESETPASSWORD), params, handler);
    }
    
    /*�޸��ǳ�*/
    public static void resetUsername(AsyncHttpResponseHandler handler, RequestParams params){
        client.post(UrlContants.getUrl(UrlContants.RESETUSERNAME), params, handler);
    }
    
    /*����ϲ��*/
    public static void getLikeList(AsyncHttpResponseHandler handler, RequestParams params){
        client.post(UrlContants.getUrl(UrlContants.LIKELIST), params, handler);
    }
    
    /*�ϴ�ͷ��*/
    public static void postUserAvatar(AsyncHttpResponseHandler handler, RequestParams params){
        client.post(UrlContants.getUrl(UrlContants.POSIUSERAVATAR), params, handler);
    }
    
    /*��ȡ����*/
    public static void getcategory(AsyncHttpResponseHandler handler, RequestParams params){
        client.post(UrlContants.getUrl(UrlContants.GETCATEGORY), params, handler);
    }
    
    /*��ȡ�������Ƶꡢ�����б�*/
    public static void getRestaurants(AsyncHttpResponseHandler handler, RequestParams params){
        client.post(UrlContants.getUrl(UrlContants.GETRESTAURANTS), params, handler);
    }
    
    /*��ȡ�������Ƶꡢ������Ʒ*/
    public static void getgoodslist(AsyncHttpResponseHandler handler, String tid){
        client.get(UrlContants.getUrl(UrlContants.GETGOODLIST)+"&tid="+tid, handler);
    }
    
    /*��ȡ�������Ƶꡢ���������б�*/
    public static void getCommentsList(AsyncHttpResponseHandler handler, RequestParams params){
        client.post(UrlContants.getUrl(UrlContants.COMMENTLIST), params, handler);
    }
   
    /*��ȡĬ�ϵ�ƴ����Ϣ*/
    public static void getList(AsyncHttpResponseHandler handler, RequestParams params){
    	client.post(UrlContants.getUrl(UrlContants.GETCARLIST), params, handler);
    }
   
//    /*��ȡĬ�ϵ�ƴ��������Ϣ*/
//    public static void getInfo(AsyncHttpResponseHandler handler, RequestParams params){
//    	client.post(UrlContants.getUrl(UrlContants.CARINFO), params, handler);
//    }
    /*��ȡĬ�ϵĳ��� ƴ����Ϣ*/
    public static void offerCar(AsyncHttpResponseHandler handler, RequestParams params){
    	client.post(UrlContants.getUrl(UrlContants.OFFERCAR), params, handler);
    }
    
    /*��ȡĬ�ϵ��û� ƴ����Ϣ*/
    public static void needcar(AsyncHttpResponseHandler handler, RequestParams params){
    	client.post(UrlContants.getUrl(UrlContants.NEEDCAR), params, handler);
    }
    /*��ȡĬ�ϵ��û� ƴ����Ϣ*/
    public static void postcaroder(AsyncHttpResponseHandler handler, RequestParams params){
    	client.post(UrlContants.getUrl(UrlContants.POSTCARORDER), params, handler);
    }

    /*��ȡ�����б�*/
    public static void getArea(AsyncHttpResponseHandler handler){
    	client.post(UrlContants.getUrl(UrlContants.GETAREA), handler);
    }
    
    /*�ύ�������Ƶ�*/
    public static void submit(AsyncHttpResponseHandler handler, RequestParams params){
    	client.post(UrlContants.getUrl(UrlContants.SUBMIT), params, handler);
    }
    
    /*��ȡ��Ʒ����*/
    public static void getGoods(AsyncHttpResponseHandler handler, String goodId){
    	client.get(UrlContants.getUrl(UrlContants.GETGOODS)+"&id="+goodId, handler);
    }
    
    /*��ȡ�����б�*/
    public static void getBianMinList(AsyncHttpResponseHandler handler){
        client.post(UrlContants.getUrl(UrlContants.GETBIANMINLIST), handler);
        }
    /*��ȡ��Ƹ���*/
    public static void getZhaoPinCategory(AsyncHttpResponseHandler handler){
    	client.post(UrlContants.getUrl(UrlContants.GETZHAOPINCATEGORY), handler);
    }
    /*��ȡ��Ƹ�б�*/
    public static void getZhaoPinList(AsyncHttpResponseHandler handler, RequestParams params){
    	client.post(UrlContants.getUrl(UrlContants.GETZHAOPINLIST), params, handler);
    }
    /*������Ƹ��Ϣ*/
    public static void SubmitZhaoPinInfo(AsyncHttpResponseHandler handler, RequestParams params){
    	client.post(UrlContants.getUrl(UrlContants.SUBMITZHAOPININFO), params, handler);
    }
    /*��ȡ��Ƹ����*/
    public static void GetZhaoPinInfo(AsyncHttpResponseHandler handler, RequestParams params){
    	client.post(UrlContants.getUrl(UrlContants.GETZHAOPININFO), params, handler);
    }
    /*��ȡ�����б�*/
    public static void getHouseList(AsyncHttpResponseHandler handler, RequestParams params){
    	client.post(UrlContants.getUrl(UrlContants.GETHOUSELIST), params, handler);
    }
    /*����������Ϣ*/
    public static void submitHouseInfo(AsyncHttpResponseHandler handler, String params){
    	client.get(UrlContants.getUrl(UrlContants.SUBMITHOUSEINFO) + params, handler);
    }
    /*��ȡ��������*/
    public static void getHouseInfo(AsyncHttpResponseHandler handler, RequestParams params){
    	client.post(UrlContants.getUrl(UrlContants.GETHOUSEINFO), params, handler);
    }
    /*��ȡ�����б�*/
    public static void getSupplyDemandList(AsyncHttpResponseHandler handler, RequestParams params){
    	client.post(UrlContants.getUrl(UrlContants.GETSUPPLYDEMANDLIST), params, handler);
    }
    /*����������Ϣ*/
    public static void submitSupplyDemandInfo(AsyncHttpResponseHandler handler, RequestParams params){
    	client.post(UrlContants.getUrl(UrlContants.SUBMITSUPPLYDEMANDINFO), params, handler);
    }
    /*��ȡ��������*/
    public static void getSupplyDemandInfo(AsyncHttpResponseHandler handler, String demandId){
    	client.post(UrlContants.getUrl(UrlContants.GETSUPPLYDEMANDINFO)+"&id="+demandId, handler);
    }
    /* ����ղصĲ������Ƶ�    */
    public static void addCollection(AsyncHttpResponseHandler handler, RequestParams params){
    	client.post(UrlContants.getUrl(UrlContants.ADDCOLLECTION), params, handler);
    }
    
    /*��ȡ�ղ��б�*/
    public static void getCollectionList(AsyncHttpResponseHandler handler, RequestParams params){
    	client.post(UrlContants.getUrl(UrlContants.GETCOLLECTIONLIST), params, handler);
    }
    
    /*ɾ���ղ��б�*/
    public static void deleteCollectionList(AsyncHttpResponseHandler handler, RequestParams params){
    	client.post(UrlContants.getUrl(UrlContants.DELETECOLLECTIONLIST), params, handler);
    }
    
    /*��ȡ�ղ����飿��������������������������������������������������������������������*/
    public static void getCollectionInfo(AsyncHttpResponseHandler handler, String collectionId){
    	client.get(UrlContants.getUrl(UrlContants.GETCOLLECTIONINFO), handler);
    }

    
    /*��ȡ��Ʒ�����б�*/
    public static void getGoodsCommentsList(AsyncHttpResponseHandler handler, RequestParams params){
    	client.post(UrlContants.getUrl(UrlContants.GOODSCOMMENTSLIST), params, handler);
    }

    /*�ύ�Ź�*/
    public static void submitShopOrder(AsyncHttpResponseHandler handler, RequestParams params){
    	client.post(UrlContants.getUrl(UrlContants.SUBMITSHOPORDER), params, handler);
    }
    

    /*�����̳�*/
    public static void getLoginUrl(AsyncHttpResponseHandler handler, RequestParams params){
    	client.post(UrlContants.getUrl(UrlContants.GETLOGINURL), params, handler);
    }

    /*�����̳�*/
    public static void getDrawUrl(AsyncHttpResponseHandler handler, RequestParams params){
    	client.post(UrlContants.getUrl(UrlContants.GETDRAWURL), params, handler);
    }

    /*�Ƿ�ǩ��*/
    public static void usersign(AsyncHttpResponseHandler handler, RequestParams params){
    	client.post(UrlContants.getUrl(UrlContants.USERSIGN), params, handler);
    }

    /*��������*/
    public static void posttixian(AsyncHttpResponseHandler handler, RequestParams params){
    	client.post(UrlContants.getUrl(UrlContants.POSTTIXIANLIST), params, handler);
    }

    /*����״̬*/
    public static void updateorder(AsyncHttpResponseHandler handler, RequestParams params){
    	client.post(UrlContants.getUrl(UrlContants.UPDATEORDER), params, handler);
    }

    /*�ύ����*/
    public static void postComments(AsyncHttpResponseHandler handler, String params){
    	client.get(UrlContants.getUrl(UrlContants.POSTCOMMENTS)+params, handler);
    }

    /*ɾ������*/
    public static void delOrder(AsyncHttpResponseHandler handler, RequestParams params){
    	client.post(UrlContants.getUrl(UrlContants.DELORDER), params, handler);
    }
    
    /*����ͼƬ�ϴ�*/
    public static void uploadone(AsyncHttpResponseHandler handler, RequestParams params){
    	client.post(UrlContants.getUrl(UrlContants.UPLOADONE), params, handler);
    }
    
    /*֧��*/
    public static void orderPay(AsyncHttpResponseHandler handler, RequestParams params){
    	client.post(UrlContants.ORDERPAY, params, handler);
    }
    
    /*��ȡ�Ƶ�����*/
    public static void getShopInfo(AsyncHttpResponseHandler handler, RequestParams params){
    	client.post(UrlContants.getUrl(UrlContants.SHOPDETAIL), params, handler);
    }
    
    /*ɾ���ղ�*/
    public static void delCollectionInfo(AsyncHttpResponseHandler handler, RequestParams params){
    	client.post(UrlContants.getUrl(UrlContants.DELCOLLECTIONINFO), params, handler);
    }
}
