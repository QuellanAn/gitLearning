package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;

public class PatientQueueSNPO implements RowMapper<PatientQueueSNPO>{
	
	private String curDocSN;
	
	private String roomId;
	
	private String roomName;
	
	private String patientId;
	
	private String patientHosId;
	
	private String patientName;
	
	private String departmentId;
	
	private String departmentName;
	
	private String doctorId;
	
	private String doctorName;
	
	private java.sql.Date docDate;
	
	private java.sql.Time docStartTime;
	
	private java.sql.Time docEndTime;
	
	private String location;
	
	private String patientNum;
	
	private String departCurSerialNum;

	private int queueTotalCount;
	private String curQueueNum;
	private int leftQueueCount;

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getPatientHosId() {
		return patientHosId;
	}

	public void setPatientHosId(String patientHosId) {
		this.patientHosId = patientHosId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
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
	
	public java.sql.Date getDocDate() {
		return docDate;
	}

	public void setDocDate(java.sql.Date docDate) {
		this.docDate = docDate;
	}

	public java.sql.Time getDocStartTime() {
		return docStartTime;
	}

	public void setDocStartTime(java.sql.Time docStartTime) {
		this.docStartTime = docStartTime;
	}

	public java.sql.Time getDocEndTime() {
		return docEndTime;
	}

	public void setDocEndTime(java.sql.Time docEndTime) {
		this.docEndTime = docEndTime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPatientNum() {
		return patientNum;
	}

	public void setPatientNum(String patientNum) {
		this.patientNum = patientNum;
	}
	
	
	public String getCurDocSN() {
		return curDocSN;
	}

	public void setCurDocSN(String curDocSN) {
		this.curDocSN = curDocSN;
	}
	
	public String getDepartCurSerialNum() {
		return departCurSerialNum;
	}

	public void setDepartCurSerialNum(String departCurSerialNum) {
		this.departCurSerialNum = departCurSerialNum;
	}

	@Override
	public PatientQueueSNPO mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		PatientQueueSNPO p = new PatientQueueSNPO();
		p.setRoomId(rs.getString("room_id"));
		p.setRoomName(rs.getString("room_name"));
		p.setCurDocSN(StringUtils.trim(rs.getString("cur_doc_sn")));
		p.setDepartmentId(StringUtils.trim(rs.getString("department_id")));
		p.setDepartmentName(rs.getString("department_name"));
		p.setDocDate(rs.getDate("doc_date"));
		p.setDocEndTime(rs.getTime("doc_end_time"));
		p.setDocStartTime(rs.getTime("doc_start_time"));
		p.setDoctorId(StringUtils.trim(rs.getString("doctor_id")));
		p.setDoctorName(rs.getString("doctor_name"));
		p.setLocation(rs.getString("location")); 
		p.setPatientHosId(StringUtils.trim(rs.getString("patient_hos_id")));
		p.setPatientId(StringUtils.trim(rs.getString("patient_id")));
		p.setPatientName(StringUtils.trim(rs.getString("patient_name")));
		p.setCurQueueNum(rs.getString("cur_queue_num"));
		p.setQueueTotalCount(rs.getInt("queue_total_count"));
		p.setLeftQueueCount(rs.getInt("left_queue_count"));
		try{
			p.setDepartCurSerialNum(rs.getString("d_cur_serial_num"));
		}catch(Exception e){
		}
		p.setPatientNum(rs.getString("patient_num"));
		return p;
	}

	/**
	 * @return the roomId
	 */
	public String getRoomId() {
		return roomId;
	}

	/**
	 * @param roomId the roomId to set
	 */
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	/**
	 * @return the queueTotalCount
	 */
	public int getQueueTotalCount() {
		return queueTotalCount;
	}

	/**
	 * @param queueTotalCount the queueTotalCount to set
	 */
	public void setQueueTotalCount(int queueTotalCount) {
		this.queueTotalCount = queueTotalCount;
	}

	/**
	 * @return the curQueueNum
	 */
	public String getCurQueueNum() {
		return curQueueNum;
	}

	/**
	 * @param curQueueNum the curQueueNum to set
	 */
	public void setCurQueueNum(String curQueueNum) {
		this.curQueueNum = curQueueNum;
	}

	/**
	 * @return the leftQueueCount
	 */
	public int getLeftQueueCount() {
		return leftQueueCount;
	}

	/**
	 * @param leftQueueCount the leftQueueCount to set
	 */
	public void setLeftQueueCount(int leftQueueCount) {
		this.leftQueueCount = leftQueueCount;
	}

	/**
	 * @return the roomName
	 */
	public String getRoomName() {
		return roomName;
	}

	/**
	 * @param roomName the roomName to set
	 */
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	
	
	
	
	

}
