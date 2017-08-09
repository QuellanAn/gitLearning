package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;

public class ChkReportDetailPO implements RowMapper<ChkReportDetailPO> {
	
	private String reportId;
	
	private String result;
	
	private String chkDoctor;
	
	private String examDoctor;
	

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getChkDoctor() {
		return chkDoctor;
	}

	public void setChkDoctor(String chkDoctor) {
		this.chkDoctor = chkDoctor;
	}

	public String getExamDoctor() {
		return examDoctor;
	}

	public void setExamDoctor(String examDoctor) {
		this.examDoctor = examDoctor;
	}
	

	@Override
	public ChkReportDetailPO mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		ChkReportDetailPO rd = new ChkReportDetailPO();
		rd.setReportId(StringUtils.trim(rs.getString("report_id")));
		rd.setResult(StringUtils.trim(rs.getString("result")));
		rd.setChkDoctor(StringUtils.trim(rs.getString("chk_doctor")));
		rd.setExamDoctor(StringUtils.trim(rs.getString("exam_doctor")));
		return rd;
	}
	
	
	

}
