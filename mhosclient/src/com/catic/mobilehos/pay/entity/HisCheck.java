package com.catic.mobilehos.pay.entity;

public class HisCheck {

	private Integer id;
	private String orderNum;
	private String othersNum	;
	private String hisNum;
	private String body;
	private Integer orderType;
	private Integer orderMoney;
	private Integer orderStatus;
	private Integer refundType;
	private String orderTime;
	private String createTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getOthersNum() {
		return othersNum;
	}
	public void setOthersNum(String othersNum) {
		this.othersNum = othersNum;
	}
	public String getHisNum() {
		return hisNum;
	}
	public void setHisNum(String hisNum) {
		this.hisNum = hisNum;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	public Integer getOrderMoney() {
		return orderMoney;
	}
	public void setOrderMoney(Integer orderMoney) {
		this.orderMoney = orderMoney;
	}
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Integer getRefundType() {
		return refundType;
	}
	public void setRefundType(Integer refundType) {
		this.refundType = refundType;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
}