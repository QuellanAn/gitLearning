package com.catic.mobilehos.pay.entity;

/**
 * 对账单
 * @author Administrator
 *
 */
public class Bill {

	private Integer bid;
	private String accountName;
	private String accountCode;
	private String billDate;
	private Integer total;
	private Integer total_out;
	private Integer total_his;
	private Double amount;
	private Double amount_out;
	private Double amount_his;
	private Double refund_amount;
	private Double refund_amount_out;
	private Double refund_amount_his;
	private Integer checkStatus;
	private Integer status;
	private Integer abnormalNum;
	private String createTime;
	
	private String startDate;
	private String endDate;
	private int count;
	
	
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountCode() {
		return accountCode;
	}
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getTotal_out() {
		return total_out;
	}
	public void setTotal_out(Integer total_out) {
		this.total_out = total_out;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getAmount_out() {
		return amount_out;
	}
	public void setAmount_out(Double amount_out) {
		this.amount_out = amount_out;
	}
	public Double getRefund_amount() {
		return refund_amount;
	}
	public void setRefund_amount(Double refund_amount) {
		this.refund_amount = refund_amount;
	}
	public Double getRefund_amount_out() {
		return refund_amount_out;
	}
	public void setRefund_amount_out(Double refund_amount_out) {
		this.refund_amount_out = refund_amount_out;
	}
	public Integer getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
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
	public Integer getAbnormalNum() {
		return abnormalNum;
	}
	public void setAbnormalNum(Integer abnormalNum) {
		this.abnormalNum = abnormalNum;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Integer getTotal_his() {
		return total_his;
	}
	public void setTotal_his(Integer total_his) {
		this.total_his = total_his;
	}
	public Double getAmount_his() {
		return amount_his;
	}
	public void setAmount_his(Double amount_his) {
		this.amount_his = amount_his;
	}
	public Double getRefund_amount_his() {
		return refund_amount_his;
	}
	public void setRefund_amount_his(Double refund_amount_his) {
		this.refund_amount_his = refund_amount_his;
	}
	
	
	
}
