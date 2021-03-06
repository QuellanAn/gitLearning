package com.catic.mobilehos.pay.entity;

import java.util.List;

public class Account {

	private Integer aid;
	private String  accountCode;
	private String  accountName;
	private String  outAccount;
	private Integer pattern;
	private String  payType;
	private Integer yq_ac_id;
	private String  yqName;
	private String  createTime;
	private String  operator;
	private String  remark;
	private Integer  status;
	
	private List<String>  payTypes;
	
	private Integer  count;

	
	
	public Integer getAid() {
		return aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getOutAccount() {
		return outAccount;
	}

	public void setOutAccount(String outAccount) {
		this.outAccount = outAccount;
	}

	public Integer getPattern() {
		return pattern;
	}

	public void setPattern(Integer pattern) {
		this.pattern = pattern;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	public Integer getYq_ac_id() {
		return yq_ac_id;
	}

	public void setYq_ac_id(Integer yq_ac_id) {
		this.yq_ac_id = yq_ac_id;
	}

	public String getYqName() {
		return yqName;
	}

	public void setYqName(String yqName) {
		this.yqName = yqName;
	}

	public List<String> getPayTypes() {
		return payTypes;
	}

	public void setPayTypes(List<String> payTypes) {
		this.payTypes = payTypes;
	}
	
	
	
}
