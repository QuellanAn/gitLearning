package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;

public class DepartmentQueueSNPO implements RowMapper<DepartmentQueueSNPO>  {
	
	private String departmentId;
	
	private String departmentName;
	
	private String departCurSerialNum;
	
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
	
	


	public String getDepartCurSerialNum() {
		return departCurSerialNum;
	}

	public void setDepartCurSerialNum(String departCurSerialNum) {
		this.departCurSerialNum = departCurSerialNum;
	}

	@Override
	public DepartmentQueueSNPO mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		DepartmentQueueSNPO p = new DepartmentQueueSNPO();
		p.setDepartmentId(StringUtils.trim(rs.getString("department_id")));
		p.setDepartmentName(rs.getString("department_name"));
		p.setDepartCurSerialNum(rs.getString("d_cur_serial_num"));
		return p;
	}
	

}
