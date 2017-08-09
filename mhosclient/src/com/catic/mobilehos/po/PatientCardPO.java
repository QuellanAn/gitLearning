package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;

public class PatientCardPO implements RowMapper<PatientCardPO>{
	
	private String id;
	private String userId;
	private String cardType;
	private String patientId;
	
	private String cardCode;
	
	private String cardNumber;
	

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	@Override
	public PatientCardPO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		PatientCardPO pcp = new PatientCardPO();
		pcp.setUserId(StringUtils.trim(rs.getString("user_id")));
		pcp.setPatientId(rs.getString("patient_id"));
		pcp.setCardNumber(rs.getString("cardnumber"));
		pcp.setCardCode(rs.getString("card_code"));
		pcp.setCardType(rs.getString("card_type"));
		return pcp;
	}

	
	

}
