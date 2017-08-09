package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;

public class PayCheckPO implements RowMapper<PayCheckPO> {

	
	private String orderid;
	private String patientid;
	private String patientname;
	private String payid; 
	private String wechatid;
	private String tradetype;
	private String status;
	private float totalmoney;
	private java.sql.Date create;

	@Override
	public PayCheckPO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		PayCheckPO pcpo =new PayCheckPO();
		pcpo.setPayid(StringUtils.trim(rs.getString("pay_id")));
		pcpo.setWechatid(StringUtils.trim(rs.getString("wechat_id")));
		pcpo.setOrderid(StringUtils.trim(rs.getString("app_reg_order_id")));
		pcpo.setPatientid(StringUtils.trim(rs.getString("patient_id")));
		pcpo.setPatientname(rs.getString("patient_name"));
		pcpo.setStatus(rs.getString("status"));
		pcpo.setTradetype(rs.getString("trade_type"));
		pcpo.setCreate(rs.getDate("create"));
		pcpo.setTotalmoney(rs.getFloat("money"));
		return pcpo;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getPatientname() {
		return patientname;
	}

	public void setPatientname(String patientname) {
		this.patientname = patientname;
	}

	public String getPayid() {
		return payid;
	}

	public void setPayid(String payid) {
		this.payid = payid;
	}

	public String getWechatid() {
		return wechatid;
	}

	public void setWechatid(String wechatid) {
		this.wechatid = wechatid;
	}

	public String getTradetype() {
		return tradetype;
	}

	public void setTradetype(String tradetype) {
		this.tradetype = tradetype;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public float getTotalmoney() {
		return totalmoney;
	}

	public void setTotalmoney(float totalmoney) {
		this.totalmoney = totalmoney;
	}

	public java.sql.Date getCreate() {
		return create;
	}

	public void setCreate(java.sql.Date create) {
		this.create = create;
	}

	public String getPatientid() {
		return patientid;
	}

	public void setPatientid(String patientid) {
		this.patientid = patientid;
	}


}
