package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;

public class PatientPO implements RowMapper<PatientPO>{

	private Integer id;
	private String userId;

	private String patientId;

	private String patientName;
	private String cardType;
	private String cardNo;

	private String sexCode;
	private String sex;

	private String identityCard;

	private String phone;

	private String homeAddress;

	private String birthday;

	private String hisId;
	private Integer IDTypeCode;
	private String IDTypeDesc;

	private Date createDate;

	private Date updateDate;
	
	private String orderStatus;
	
	private String canal;
	
	private String type;//0表示建档，1表示绑卡
	private String status;//0表示正常，1表示冻结
	private String patientSource;// 就诊人来源，1表示微信，2表示支付宝服务窗
	private String sourceName;// 就诊人来源，1表示微信，2表示支付宝服务窗
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getSexCode() {
		return sexCode;
	}

	public void setSexCode(String sexCode) {
		this.sexCode = sexCode;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getHisId() {
		return hisId;
	}

	public void setHisId(String hisId) {
		this.hisId = hisId;
	}

	public Integer getIDTypeCode() {
		return IDTypeCode;
	}

	public void setIDTypeCode(Integer iDTypeCode) {
		IDTypeCode = iDTypeCode;
	}

	public String getIDTypeDesc() {
		return IDTypeDesc;
	}

	public void setIDTypeDesc(String iDTypeDesc) {
		IDTypeDesc = iDTypeDesc;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getCanal() {
		return canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPatientSource() {
		return patientSource;
	}

	public void setPatientSource(String patientSource) {
		this.patientSource = patientSource;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	@Override
	public PatientPO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		PatientPO pat = new PatientPO();
		pat.setUserId(StringUtils.trim(rs.getString("userId")));
		pat.setPatientId(rs.getString("patient_id"));
		pat.setPatientName(rs.getString("patient_name"));
		pat.setSexCode(rs.getString("sexCode"));
		pat.setIdentityCard(rs.getString("identitycard"));
		pat.setPhone(rs.getString("phone"));
		pat.setCreateDate(rs.getDate("create_date"));
		pat.setUpdateDate(rs.getDate("update_date"));
		pat.setBirthday(rs.getString("birthday"));
		pat.setHomeAddress(rs.getString("home_address"));
		pat.setCardNo(rs.getString("cardNo"));
		pat.setType(rs.getString("type"));
		pat.setStatus(rs.getString("status"));
		pat.setPatientSource(rs.getString("patient_source"));
		pat.setSourceName(rs.getString("sourceName"));
		return pat;
	}

}
