package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;

import org.springframework.jdbc.core.RowMapper;

public class ServiceEvaluationHosPO implements RowMapper<ServiceEvaluationHosPO>{
	
	private Integer serviceEvaluationId;
	private Integer environment;
	private Integer efficiency;
	private Integer attitude;
	private String overallEvaluation;
	private String  content;
	private String app_reg_order_id;//对应就诊id
	private String patientName;//病人姓名
	private String doctorName;//主治医生姓名
	private String departmentName;//就诊科室
	private Timestamp lookDate;//就诊时间
	private Timestamp createDate;
	private String  cardNumber;
	
	//统计数量
	private int goodNum;
	private int commonNum;
	private int badNum;

	// 以下为非数据库字段
	private String beginDate;// 开始日期
	private String endDate;// 结束日期
	private double proportion;// 占比，最大为1，非百分比
	private String percentage;// 百分比（不含百分号）
	private String countDate;// 统计日期
	private String departmentId;// 科室id
	private int count;
	private String doctorId;// 医生id
	private String countType;// 统计类型，department表示按科室/doctor表示医生
	private String countDateType;// 日期统计类型，day为按日，month为按月
	private String evaluateSource;// 评价来源
	
	//2017-5-23
	private String regSource;// 开始日期
	private String sourceName;//评价途径 
	private String overallEvaluationName;//总体评价
	
	public Integer getServiceEvaluationId() {
		return serviceEvaluationId;
	}

	public void setServiceEvaluationId(Integer serviceEvaluationId) {
		this.serviceEvaluationId = serviceEvaluationId;
	}

	public Integer getEnvironment() {
		return environment;
	}

	public void setEnvironment(Integer environment) {
		this.environment = environment;
	}

	public Integer getEfficiency() {
		return efficiency;
	}

	public void setEfficiency(Integer efficiency) {
		this.efficiency = efficiency;
	}

	public Integer getAttitude() {
		return attitude;
	}

	public void setAttitude(Integer attitude) {
		this.attitude = attitude;
	}

	public String getOverallEvaluation() {
		return overallEvaluation;
	}

	public void setOverallEvaluation(String overallEvaluation) {
		this.overallEvaluation = overallEvaluation;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getApp_reg_order_id() {
		return app_reg_order_id;
	}

	public void setApp_reg_order_id(String app_reg_order_id) {
		this.app_reg_order_id = app_reg_order_id;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Timestamp getLookDate() {
		return lookDate;
	}

	public void setLookDate(Timestamp lookDate) {
		this.lookDate = lookDate;
	}

	public int getGoodNum() {
		return goodNum;
	}

	public void setGoodNum(int goodNum) {
		this.goodNum = goodNum;
	}

	public int getCommonNum() {
		return commonNum;
	}

	public void setCommonNum(int commonNum) {
		this.commonNum = commonNum;
	}

	public int getBadNum() {
		return badNum;
	}

	public void setBadNum(int badNum) {
		this.badNum = badNum;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
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

	public double getProportion() {
		return proportion;
	}

	public void setProportion(double proportion) {
		this.proportion = proportion;
		DecimalFormat df = new DecimalFormat("0.00");//格式化小数，不足的补0
		this.setPercentage(df.format(proportion * 100));
	}

	public String getPercentage() {
		return percentage;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

	public String getCountDate() {
		return countDate;
	}

	public void setCountDate(String countDate) {
		this.countDate = countDate;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getRegSource() {
		return regSource;
	}

	public void setRegSource(String regSource) {
		this.regSource = regSource;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getCountDateType() {
		return countDateType;
	}

	public void setCountDateType(String countDateType) {
		this.countDateType = countDateType;
	}

	public String getEvaluateSource() {
		return evaluateSource;
	}

	public void setEvaluateSource(String evaluateSource) {
		this.evaluateSource = evaluateSource;
	}

	public String getCountType() {
		return countType;
	}

	public void setCountType(String countType) {
		this.countType = countType;
	}


	public String getOverallEvaluationName() {
		return overallEvaluationName;
	}

	public void setOverallEvaluationName(String overallEvaluationName) {
		this.overallEvaluationName = overallEvaluationName;
	}

	@Override
	public ServiceEvaluationHosPO mapRow(ResultSet rs, int i) throws SQLException{
		
		ServiceEvaluationHosPO s=new ServiceEvaluationHosPO();
		s.setServiceEvaluationId(rs.getInt("serviceEvaluationId"));
		s.setEnvironment(rs.getInt("environment"));
		s.setEfficiency(rs.getInt("efficiency"));
		s.setAttitude(rs.getInt("attitude"));
		s.setOverallEvaluation(rs.getString("overallEvaluation"));
		s.setContent(rs.getString("content"));
		s.setCreateDate(rs.getTimestamp("createDate"));
		
		s.setApp_reg_order_id(rs.getString("app_reg_order_id"));
		s.setPatientName(rs.getString("patientName"));
		s.setDoctorName(rs.getString("doctorName"));
		s.setDepartmentName(rs.getString("departmentName"));
		s.setLookDate(rs.getTimestamp("lookDate"));
		
		s.setCardNumber(rs.getString("cardNumber"));		
		s.setRegSource(rs.getString("regSource"));
		s.setSourceName(rs.getString("sourceName"));
		s.setOverallEvaluationName(rs.getString("overallEvaluationName"));
		return s;
	}
	

}
