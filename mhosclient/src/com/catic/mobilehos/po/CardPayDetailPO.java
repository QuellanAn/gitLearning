package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

/**
 * 此对象用于就诊卡缴费，每项费用的金额
 * @author gds
 *
 */
public class CardPayDetailPO implements RowMapper<CardPayDetailPO>{
	/**
	 * 此字段医嘱单号
	 */
	private int id;
	private String cpId;
	private String totalFee;
	private String name;
	private String totalAmount;
	private String amount;
	private String number;
	private Date creatDate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCpId() {
		return cpId;
	}
	public void setCpId(String cpId) {
		this.cpId = cpId;
	}
	public String getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Date getCreatDate() {
		return creatDate;
	}
	public void setCreatDate(Date creatDate) {
		this.creatDate = creatDate;
	}
	@Override
	public CardPayDetailPO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		CardPayDetailPO cpd = new CardPayDetailPO();
		cpd.setCpId(rs.getString("id"));
		cpd.setCpId(rs.getString("cp_id"));
		cpd.setTotalFee(rs.getString("totalFee"));
		cpd.setName(rs.getString("name"));
		cpd.setTotalAmount(rs.getString("total_amount"));
		cpd.setAmount(rs.getString("amount"));
		cpd.setNumber(rs.getString("number"));
		cpd.setCreatDate(rs.getDate("create_date"));
		return cpd;
	}
}
