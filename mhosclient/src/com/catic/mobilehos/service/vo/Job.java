package com.catic.mobilehos.service.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.catic.mobilehos.po.DepartmentNamePO;

public class Job implements RowMapper<Job>{

	private int id;
	private String jobName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	@Override
	public Job mapRow(ResultSet rs, int rowNum)
			throws SQLException {

		Job j=new Job();
		j.setId(rs.getInt("id"));
		j.setJobName(rs.getString("jobName"));
		
		return j;
	}

	
	
}
