package com.catic.mobilehos.pay.entity;

public class CheckBillDetails {


	private String id;
	private String orderId;
	private String orderCode;
	private String failReason;
	private String  creatDate;
	private String  out_trade_no;
	private String  billDate;
	private String  accountCode;
	private String  accountName;
	private String  ptTransType;
	private String  transType;
	private String  transTime;
	
	private Integer pattern;
	private Integer payType;
	private Integer outStatus;
	private Integer hisStatus;
	private Integer billStatus;
	private Integer isCheck;
	private Integer handle;
	
	private Integer payStatus;
	
	private Integer count;
	
	private Integer actualPay;
	private Integer outAmount;
	private Integer hisAmount;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getFailReason() {
		return failReason;
	}
	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}
	public Integer getPattern() {
		return pattern;
	}
	public void setPattern(Integer pattern) {
		this.pattern = pattern;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public Integer getOutStatus() {
		return outStatus;
	}
	public void setOutStatus(Integer outStatus) {
		this.outStatus = outStatus;
	}
	public Integer getHisStatus() {
		return hisStatus;
	}
	public void setHisStatus(Integer hisStatus) {
		this.hisStatus = hisStatus;
	}
	public Integer getBillStatus() {
		return billStatus;
	}
	public void setBillStatus(Integer billStatus) {
		this.billStatus = billStatus;
	}
	public Integer getIsCheck() {
		return isCheck;
	}
	public void setIsCheck(Integer isCheck) {
		this.isCheck = isCheck;
	}
	
	public String getCreatDate() {
		return creatDate;
	}
	public void setCreatDate(String creatDate) {
		this.creatDate = creatDate;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public Integer getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	public Integer getActualPay() {
		return actualPay;
	}
	public void setActualPay(Integer actualPay) {
		this.actualPay = actualPay;
	}
	public Integer getOutAmount() {
		return outAmount;
	}
	public void setOutAmount(Integer outAmount) {
		this.outAmount = outAmount;
	}
	public Integer getHisAmount() {
		return hisAmount;
	}
	public void setHisAmount(Integer hisAmount) {
		this.hisAmount = hisAmount;
	}
	public Integer getHandle() {
		return handle;
	}
	public void setHandle(Integer handle) {
		this.handle = handle;
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
	public String getPtTransType() {
		return ptTransType;
	}
	public void setPtTransType(String ptTransType) {
		this.ptTransType = ptTransType;
	}
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public String getTransTime() {
		return transTime;
	}
	public void setTransTime(String transTime) {
		this.transTime = transTime;
	}
	
}
