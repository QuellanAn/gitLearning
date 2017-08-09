package com.catic.mobilehos.pay.entity;

public class CheckSource {
	private Integer cId;
	private String checkCode;
	private String accountCode;
	private String checkSource;
	private String acoountName;
	private Integer status;
	private String createTime;
	
	private Integer count;
	
	public Integer getcId() {
		return cId;
	}
	public void setcId(Integer cId) {
		this.cId = cId;
	}
	public String getAccountCode() {
		return accountCode;
	}
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	public String getCheckSource() {
		return checkSource;
	}
	public void setCheckSource(String checkSource) {
		this.checkSource = checkSource;
	}
	public String getAcoountName() {
		return acoountName;
	}
	public void setAcoountName(String acoountName) {
		this.acoountName = acoountName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getCheckCode() {
		return checkCode;
	}
	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
	
}
