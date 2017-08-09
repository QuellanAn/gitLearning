package com.catic.mobilehos.pay.entity;


public class CheckBill {

	private String id;
	private Integer total;
	private Integer total_w;
	private Integer total_a;
	private Double amount;
	private Double amount_w;
	private Double amount_a;
	private Double refund_amount;
	private Double refund_amount_w;
	private Double refund_amount_a;
	private Double w_amount;
	private Integer w_total;
	private Double w_refund_amount;
	private Double a_amount;
	private Integer a_total;
	private Double a_refund_amount;
//	private Double hisAmount;
	private String billDate;
	private String createTime;
	private Integer billStatus;
	private Integer status;
	
	private Integer count;
	private String startdate;
	private String enddate;
	private Integer pattern;
	
	private Integer total_out;
	private Double amount_out; 
	private Double refund_amount_out;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getBillStatus() {
		return billStatus;
	}
	public void setBillStatus(Integer billStatus) {
		this.billStatus = billStatus;
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
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public Integer getTotal_w() {
		return total_w;
	}
	public void setTotal_w(Integer total_w) {
		this.total_w = total_w;
	}
	public Integer getTotal_a() {
		return total_a;
	}
	public void setTotal_a(Integer total_a) {
		this.total_a = total_a;
	}
	public Double getAmount_w() {
		return amount_w;
	}
	public void setAmount_w(Double amount_w) {
		this.amount_w = amount_w;
	}
	public Double getAmount_a() {
		return amount_a;
	}
	public void setAmount_a(Double amount_a) {
		this.amount_a = amount_a;
	}
	public Double getRefund_amount() {
		return refund_amount;
	}
	public void setRefund_amount(Double refund_amount) {
		this.refund_amount = refund_amount;
	}
	public Double getRefund_amount_w() {
		return refund_amount_w;
	}
	public void setRefund_amount_w(Double refund_amount_w) {
		this.refund_amount_w = refund_amount_w;
	}
	public Double getRefund_amount_a() {
		return refund_amount_a;
	}
	public void setRefund_amount_a(Double refund_amount_a) {
		this.refund_amount_a = refund_amount_a;
	}
	public Double getW_amount() {
		return w_amount;
	}
	public void setW_amount(Double w_amount) {
		this.w_amount = w_amount;
	}
	public Integer getW_total() {
		return w_total;
	}
	public void setW_total(Integer w_total) {
		this.w_total = w_total;
	}
	public Double getW_refund_amount() {
		return w_refund_amount;
	}
	public void setW_refund_amount(Double w_refund_amount) {
		this.w_refund_amount = w_refund_amount;
	}
	public Double getA_amount() {
		return a_amount;
	}
	public void setA_amount(Double a_amount) {
		this.a_amount = a_amount;
	}
	public Integer getA_total() {
		return a_total;
	}
	public void setA_total(Integer a_total) {
		this.a_total = a_total;
	}
	public Double getA_refund_amount() {
		return a_refund_amount;
	}
	public void setA_refund_amount(Double a_refund_amount) {
		this.a_refund_amount = a_refund_amount;
	}
	public Integer getTotal_out() {
		return total_out;
	}
	public void setTotal_out(Integer total_out) {
		this.total_out = total_out;
	}
	public Double getAmount_out() {
		return amount_out;
	}
	public void setAmount_out(Double amount_out) {
		this.amount_out = amount_out;
	}
	public Double getRefund_amount_out() {
		return refund_amount_out;
	}
	public void setRefund_amount_out(Double refund_amount_out) {
		this.refund_amount_out = refund_amount_out;
	}
	public Integer getPattern() {
		return pattern;
	}
	public void setPattern(Integer pattern) {
		this.pattern = pattern;
	}
	
}
