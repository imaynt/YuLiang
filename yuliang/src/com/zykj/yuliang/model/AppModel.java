package com.zykj.yuliang.model;

import com.zykj.yuliang.utils.SharedPreferenceUtils;

import android.content.Context;

public class AppModel {
	
	 /**
     * ��ǰ�ʺ��Ƿ��Ѿ���¼�ı�ʶ
     */
    public static boolean is_login = false;
    public static String NAME = "name";
    public static String PHONE = "phone";
	
	
	
    private String username;//��¼�˺�
    private String password;//��¼����
    private String userid;//�û�Id
    private String avatar;//ͷ��
    private String mobile;//�ֻ�
    private String money;//�ҵ�Ǯ��
    private String integral;//����
    private String latitude;//����
    private String longitude;//γ��
    private String sign;

    private static SharedPreferenceUtils utils;
    
    public static AppModel init(Context context){
        AppModel model =new AppModel();
        utils = SharedPreferenceUtils.init(context);

        if(utils.getUsername()!=null){
            model.username = utils.getUsername();
        }

        if(utils.getPassword() != null){
            model.password= utils.getPassword();
        }

        if(utils.getUserid() != null){
            model.userid= utils.getUserid();
        }

        if(utils.getAvatar() != null){
            model.avatar= utils.getAvatar();
        }

        if(utils.getMobile() != null){
            model.mobile= utils.getMobile();
        }

        if(utils.getMoney() != null){
            model.money= utils.getMoney();
        }

        if(utils.getIntegral() != null){
            model.integral= utils.getIntegral();
        }

        if(utils.getLatitude() != null){
            model.latitude= utils.getLatitude();
        }

        if(utils.getLongitude() != null){
            model.longitude= utils.getLongitude();
        }

        if(utils.getSign() != null){
            model.sign= utils.getSign();
        }

        return model;
    }
    
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
        utils.setUsername(username);
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
        utils.setPassword(password);
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
        utils.setUserid(userid);
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
        utils.setAvatar(avatar);
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
        utils.setMobile(mobile);
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
        utils.setMoney(money);
	}
	public String getIntegral() {
		return integral;
	}
	public void setIntegral(String integral) {
		this.integral = integral;
        utils.setIntegral(integral);
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public void clear(){
		this.setUsername("");
		this.setPassword("");
		this.setUserid("");
		this.setAvatar("");
		this.setMobile("");
		this.setMoney("");
		this.setIntegral("");
		utils.clear();
	}
}
