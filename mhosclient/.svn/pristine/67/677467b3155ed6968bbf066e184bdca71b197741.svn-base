package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;

public class TimetablePO implements RowMapper<TimetablePO> {

	private String orderType;

	private Integer totalCount;

	private String roomId;

	private Integer timeState;

	private Integer periodId;

	private Integer timeId;

	private java.sql.Date serviceDate;

	private String timeType;

	private java.sql.Time startTime;

	private java.sql.Time endTime;

	private Integer remainder;

	private Double charge;

	private String doctorId;

	private String departmentId;

	@Override
	public TimetablePO mapRow(ResultSet rs, int rowNum) throws SQLException {
		TimetablePO t = new TimetablePO();
		t.setTimeId(rs.getInt("time_id"));
		t.setServiceDate(rs.getDate("service_date"));
		t.setTimeType(StringUtils.trim(rs.getString("time_type")));
		t.setStartTime(rs.getTime("start_time"));
		t.setEndTime(rs.getTime("end_time"));
		t.setRemainder(rs.getInt("remainder"));
		t.setCharge(rs.getDouble("charge"));
		t.setDoctorId(StringUtils.trim(rs.getString("doctor_id")));
		t.setDepartmentId(StringUtils.trim(rs.getString("department_id")));
		t.setOrderType(rs.getString("order_type"));
		t.setPeriodId(rs.getInt("period_id"));
		t.setTimeState(rs.getInt("time_state"));
		t.setRoomId(rs.getString("room_id"));
		return t;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public Integer getTimeState() {
		return timeState;
	}

	public void setTimeState(Integer timeState) {
		this.timeState = timeState;
	}

	public Integer getPeriodId() {
		return periodId;
	}

	public void setPeriodId(Integer periodId) {
		this.periodId = periodId;
	}

	public Integer getTimeId() {
		return timeId;
	}

	public void setTimeId(Integer timeId) {
		this.timeId = timeId;
	}

	public java.sql.Date getServiceDate() {
		return serviceDate;
	}

	public void setServiceDate(java.sql.Date serviceDate) {
		this.serviceDate = serviceDate;
	}

	public String getTimeType() {
		return timeType;
	}

	public void setTimeType(String timeType) {
		this.timeType = timeType;
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

	public Integer getRemainder() {
		return remainder;
	}

	public void setRemainder(Integer remainder) {
		this.remainder = remainder;
	}

	public Double getCharge() {
		return charge;
	}

	public void setCharge(Double charge) {
		this.charge = charge;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
}
