package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import org.springframework.jdbc.core.RowMapper;

public class AppRegPeriodPO implements RowMapper<AppRegPeriodPO> {

	private int periodId;
	private String periodType;
	private Time startTime;
	private Time endTime;

	public int getPeriodId() {
		return periodId;
	}

	public void setPeriodId(int periodId) {
		this.periodId = periodId;
	}

	public String getPeriodType() {
		return periodType;
	}

	public void setPeriodType(String periodType) {
		this.periodType = periodType;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	@Override
	public AppRegPeriodPO mapRow(ResultSet rs, int i) throws SQLException {
		AppRegPeriodPO arp = new AppRegPeriodPO();
		arp.setPeriodId(rs.getInt("period_id"));
		arp.setPeriodType(rs.getString("period_type"));
		arp.setStartTime(rs.getTime("start_time"));
		arp.setEndTime(rs.getTime("end_time"));
		return arp;
	}
}
