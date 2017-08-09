// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   HisRoomQueueSn.java

package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class RoomQueueSnPO implements RowMapper<RoomQueueSnPO> {

	private String departmentId;
	private String roomId;
	private String doctorId;
	private String patientOrderId;
	private int queueSn;

	public RoomQueueSnPO() {
	}

	public RoomQueueSnPO mapRow(ResultSet rs, int rowCount)
			throws SQLException {
		RoomQueueSnPO roomQueueSn = new RoomQueueSnPO();
		roomQueueSn.setDepartmentId(rs.getString("department_id"));
		roomQueueSn.setDoctorId(rs.getString("doctor_id"));
		roomQueueSn.setRoomId(rs.getString("room_id"));
		roomQueueSn.setPatientOrderId(rs.getString("patient_order_id"));
		roomQueueSn.setQueueSn(rs.getInt("queue_sn"));
		return roomQueueSn;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getPatientOrderId() {
		return patientOrderId;
	}

	public void setPatientOrderId(String patientOrderId) {
		this.patientOrderId = patientOrderId;
	}

	public int getQueueSn() {
		return queueSn;
	}

	public void setQueueSn(int queueSn) {
		this.queueSn = queueSn;
	}

}
