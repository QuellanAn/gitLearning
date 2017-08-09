package com.catic.mobilehos.pay.entity;

public class PayType {

	private Integer ptID;
	private Integer ptCode;
	private String ptName;
	private Integer ptStatus;
	private int count;

	public Integer getPtID() {
		return ptID;
	}

	public void setPtID(Integer ptID) {
		this.ptID = ptID;
	}

	public Integer getPtCode() {
		return ptCode;
	}

	public void setPtCode(Integer ptCode) {
		this.ptCode = ptCode;
	}

	public String getPtName() {
		return ptName;
	}

	public void setPtName(String ptName) {
		this.ptName = ptName;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Integer getPtStatus() {
		return ptStatus;
	}

	public void setPtStatus(Integer ptStatus) {
		this.ptStatus = ptStatus;
	}

}
