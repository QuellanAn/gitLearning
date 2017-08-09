package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

public class TradePO implements RowMapper<TradePO>{
	private int id;
	private String cardNo;
	private String tradeNo;
	private String transactionId;
	private String goodsName;
	private String attach;
	private String tradeType;
	private String tradeState;
	private String bankType;
	private String feeType;
	private String totalFee;
	private String couponFee;
	private String openId;
	private int status;
	private Date endDate;
	private Date createDate;
	private int collateBill;
	private Date collateDate;
	
	private String patientName;
	private String rechargeSource;// 充值来源，1表示微信，2表示支付宝服务窗
	
	// 以下为非数据库字段
	private String startTime;// 开始日期
	private String endTime;// 结束日期
	private String countDate;// 统计日期
	private int count;
	private String countDateType;// 日期统计类型，day为按日，month为按月
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getTradeState() {
		return tradeState;
	}

	public void setTradeState(String tradeState) {
		this.tradeState = tradeState;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	public String getCouponFee() {
		return couponFee;
	}

	public void setCouponFee(String couponFee) {
		this.couponFee = couponFee;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getCollateBill() {
		return collateBill;
	}

	public void setCollateBill(int collateBill) {
		this.collateBill = collateBill;
	}

	public Date getCollateDate() {
		return collateDate;
	}

	public void setCollateDate(Date collateDate) {
		this.collateDate = collateDate;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getCountDate() {
		return countDate;
	}

	public void setCountDate(String countDate) {
		this.countDate = countDate;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getRechargeSource() {
		return rechargeSource;
	}

	public void setRechargeSource(String rechargeSource) {
		this.rechargeSource = rechargeSource;
	}

	public String getCountDateType() {
		return countDateType;
	}

	public void setCountDateType(String countDateType) {
		this.countDateType = countDateType;
	}

	@Override
	public TradePO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		TradePO t = new TradePO();
		try{
			t.setId(rs.getInt("id"));
		}catch(SQLException e){}
		t.setCardNo(rs.getString("cardNo"));
		t.setTradeNo(rs.getString("tradeNo"));
		t.setTransactionId(rs.getString("transactionId"));
		t.setGoodsName(rs.getString("goodsName"));
		t.setAttach(rs.getString("attach"));
		t.setTradeType(rs.getString("tradeType"));
		t.setTradeState(rs.getString("tradeState"));
		t.setBankType(rs.getString("bankType"));
		t.setFeeType(rs.getString("feeType"));
		t.setTotalFee(rs.getString("totalFee"));
		t.setCouponFee(rs.getString("couponFee"));
		t.setOpenId(rs.getString("openId"));
		try{
			t.setStatus(rs.getInt("status"));
		}catch(SQLException e){}
		t.setEndDate(rs.getDate("endDate"));
		t.setCreateDate(rs.getDate("createDate"));
		try{
			t.setCollateBill(rs.getInt("collateBill"));
		}catch(SQLException e){}
		t.setCollateDate(rs.getDate("collateDate"));
		t.setPatientName(rs.getString("patientName"));
		t.setRechargeSource(rs.getString("recharge_source"));
		return t;
	}
}
