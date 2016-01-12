package com.zykj.yuliang.model;

import java.io.Serializable;

public class Income implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;//任务ID
	private String uid;//徒弟的ID
	private String parentid;//师傅ID
	private String description;//任务名称
	private String money;//收入金额
	private String deviceid;
	private String timestamp;//收入日期
	private String incomeAvatar;//头像
	private String alipay;
	private String state;
    private String addtime;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getIncomeAvatar() {
		return incomeAvatar;
	}
	public void setIncomeAvatar(String incomeAvatar) {
		this.incomeAvatar = incomeAvatar;
	}
	public String getAlipay() {
		return alipay;
	}
	public void setAlipay(String alipay) {
		this.alipay = alipay;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	
	
	
}
