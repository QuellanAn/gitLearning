// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   HisRoom.java

package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class RoomPO implements RowMapper<RoomPO> {

	private String roomId;
	private String roomName;
	private String departmentId;
	private String roomAddr;

	public RoomPO() {
	}

	public RoomPO mapRow(ResultSet rs, int rowCount) throws SQLException {
		RoomPO room = new RoomPO();
		room.setRoomId(rs.getString("room_id"));
		room.setRoomName(rs.getString("room_name"));
		room.setDepartmentId(rs.getString("department_id"));
		room.setRoomAddr(rs.getString("room_addr"));
		return room;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getRoomAddr() {
		return roomAddr;
	}

	public void setRoomAddr(String roomAddr) {
		this.roomAddr = roomAddr;
	}
}
