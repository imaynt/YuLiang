package com.zykj.yuliang.model;

import java.io.Serializable;

public class Income implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String incomeNum;//������
	private String taskName;//��������
	private String icomedate;//��������
	private String incomeAvatar;//ͷ��
	private String incomerId;//������Դ�ߵ�ͷ��
	
	public String getIncomeNum() {
		return incomeNum;
	}
	public void setIncomeNum(String incomeNum) {
		this.incomeNum = incomeNum;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getIcomedate() {
		return icomedate;
	}
	public void setIcomedate(String icomedate) {
		this.icomedate = icomedate;
	}
	public String getIncomeAvatar() {
		return incomeAvatar;
	}
	public void setIncomeAvatar(String incomeAvatar) {
		this.incomeAvatar = incomeAvatar;
	}
	public String getIncomerId() {
		return incomerId;
	}
	public void setIncomerId(String incomerId) {
		this.incomerId = incomerId;
	}
	
	
	
	
}
