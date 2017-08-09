package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;

public class AppRegOrdersPO implements RowMapper<AppRegOrdersPO>{
	
	public static final String STATUS_SUBMITED = "011";
	
	public static final String STATUS_CANCELED = "021";
	
	private String orderDate;
	
	private String userId;
	
	private String IMEI;
	
	private String userName;
	
	private String appRegOrderId;
	
	private String appRegOrderNumber;
	private String appROrderNumber;
	
	private String patientId;
	
	private String patientName;
	
	private String sex;
	
	private String phone;
	
	private String identitycard;
	
	private String cardCode;
	
	private String cardName;
	
	private String cardNumber;
	
	private String departmentId;
	
	private String departmentName;
	
	private String doctorId;
	
	private String doctorName;
	
	private String timeId;
	
	private java.sql.Date doctorDate;
	
	private java.sql.Time startTime;
	
	private java.sql.Time endTime;
	
	private String status;
	
	private java.sql.Date createDate;
	
	private double payableFees;
	
	private String paidFees;
	
	private String payMode;
	
	private Timestamp payTime;
	
	private String payOrderId;
	
	private Timestamp updateDate;
	
	private int paid;
	
	private String orderStatus;
	
	private String canal;
	
	private String patientOrderId;
	
	private String type;
	
	private String regSource;// 挂号来源，1为微信，2为支付宝
	
	// 以下为非数据库字段
	private String beginDate;
	private String endDate;
	private String countDate;
	private int count;
	private String countType;// 统计类型，按科室/医生/来源
	private String countDateType;// 日期统计类型，day为按日，month为按月
	private String groupName;// 分组名称
	
	private String regSourceName;// 预约途径
	private String statusName;//挂号状态
	private String paidName;//缴费状态
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getIMEI() {
		return IMEI;
	}

	public void setIMEI(String IMEI) {
		this.IMEI = IMEI;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAppRegOrderId() {
		return appRegOrderId;
	}

	public void setAppRegOrderId(String appRegOrderId) {
		this.appRegOrderId = appRegOrderId;
	}
	
	public String getAppRegOrderNumber() {
		return appRegOrderNumber;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public void setAppRegOrderNumber(String appRegOrderNumber) {
		this.appRegOrderNumber = appRegOrderNumber;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	
	public String getAppROrderNumber() {
		return appROrderNumber;
	}

	public void setAppROrderNumber(String appROrderNumber) {
		this.appROrderNumber = appROrderNumber;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIdentitycard() {
		return identitycard;
	}

	public void setIdentitycard(String identitycard) {
		this.identitycard = identitycard;
	}

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getTimeId() {
		return timeId;
	}

	public void setTimeId(String timeId) {
		this.timeId = timeId;
	}

	public java.sql.Date getDoctorDate() {
		return doctorDate;
	}

	public void setDoctorDate(java.sql.Date doctorDate) {
		this.doctorDate = doctorDate;
	}

	public java.sql.Time getStartTime() {
		return startTime;
	}

	public void setStartTime(java.sql.Time startTime) {
		this.startTime = startTime;
	}

	public java.sql.Time getEndTime() {
		return endTime;
	}

	public void setEndTime(java.sql.Time endTime) {
		this.endTime = endTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	


	public java.sql.Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.sql.Date createDate) {
		this.createDate = createDate;
	}

	public double getPayableFees() {
		return payableFees;
	}

	public void setPayableFees(double payableFees) {
		this.payableFees = payableFees;
	}

	

	public String getPaidFees() {
		return paidFees;
	}

	public void setPaidFees(String paidFees) {
		this.paidFees = paidFees;
	}

	public String getPayMode() {
		return payMode;
	}

	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}

	public Timestamp getPayTime() {
		return payTime;
	}

	public void setPayTime(Timestamp payTime) {
		this.payTime = payTime;
	}

	public String getPayOrderId() {
		return payOrderId;
	}

	public void setPayOrderId(String payOrderId) {
		this.payOrderId = payOrderId;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}
	
	public int getPaid() {
		return paid;
	}

	public void setPaid(int paid) {
		this.paid = paid;
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

	public String getPatientOrderId() {
		return patientOrderId;
	}

	public void setPatientOrderId(String patientOrderId) {
		this.patientOrderId = patientOrderId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
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

	public String getRegSource() {
		return regSource;
	}

	public void setRegSource(String regSource) {
		this.regSource = regSource;
	}

	public String getCountType() {
		return countType;
	}

	public void setCountType(String countType) {
		this.countType = countType;
	}

	public String getCountDateType() {
		return countDateType;
	}

	public void setCountDateType(String countDateType) {
		this.countDateType = countDateType;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getRegSourceName() {
		return regSourceName;
	}

	public void setRegSourceName(String regSourceName) {
		this.regSourceName = regSourceName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getPaidName() {
		return paidName;
	}

	public void setPaidName(String paidName) {
		this.paidName = paidName;
	}

	@Override
	public AppRegOrdersPO mapRow(ResultSet rs, int rowNum) throws SQLException {
		AppRegOrdersPO aro = new AppRegOrdersPO();
		aro.setUserId(StringUtils.trim(rs.getString("user_id")));
		aro.setIMEI(IMEI);
		try{
			aro.setUserName(StringUtils.trim(rs.getString("user_name")));
		}catch(Exception e){
		}
		aro.setAppRegOrderId(StringUtils.trim(rs.getString("app_reg_order_id")));
		aro.setAppRegOrderNumber(StringUtils.trim(rs.getString("app_r_order_number")));
		aro.setPatientId(StringUtils.trim(rs.getString("patient_id")));
		aro.setPatientName(rs.getString("patient_name"));
		aro.setSex(rs.getString("sex"));
		aro.setPhone(rs.getString("phone"));
		aro.setIdentitycard(StringUtils.trim(rs.getString("identitycard")));
		aro.setCardCode(rs.getString("card_code"));
		aro.setCardName(rs.getString("card_name"));
		aro.setCardNumber(rs.getString("card_number"));
		aro.setDepartmentId(rs.getString("department_id"));
		aro.setDepartmentName(rs.getString("department_name"));
		aro.setDoctorId(rs.getString("doctor_id"));
		aro.setDoctorName(rs.getString("doctor_name"));
		aro.setTimeId(rs.getString("time_id"));
		aro.setDoctorDate(rs.getDate("doctor_date"));
		aro.setStartTime(rs.getTime("starttime"));
		aro.setEndTime(rs.getTime("endtime"));
		aro.setStatus(rs.getString("status"));
		aro.setCreateDate(rs.getDate("create_date"));
		aro.setPayableFees(rs.getDouble("payable_fees"));
		aro.setPaidFees(rs.getString("paid_fees"));
		aro.setPayMode(rs.getString("pay_mode"));
		aro.setPayTime(rs.getTimestamp("pay_time"));
		aro.setPayOrderId(rs.getString("pay_orderid"));
		aro.setUpdateDate(rs.getTimestamp("update_date"));
		aro.setPaid(rs.getInt("paid"));
		aro.setCanal(rs.getString("canal"));
		aro.setOrderStatus(rs.getString("orderStatus"));
		aro.setPatientOrderId(rs.getString("patient_order_id"));
		aro.setRegSource(rs.getString("reg_source"));
		aro.setRegSourceName(rs.getString("reg_source_name"));
		aro.setStatusName(rs.getString("statusName"));
		aro.setPaidName(rs.getString("paidName"));
		return aro;
		
	}
	
	
	
}

