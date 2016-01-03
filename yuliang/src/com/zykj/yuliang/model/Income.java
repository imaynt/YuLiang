package com.zykj.yuliang.model;

import java.io.Serializable;

public class Income implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;//����ID
	private String uid;//ͽ�ܵ�ID
	private String parentid;//ʦ��ID
	private String description;//��������
	private String money;//������
	private String deviceid;
	private String timestamp;//��������
	private String incomeAvatar;//ͷ��
	
	
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
	
	
	
}
