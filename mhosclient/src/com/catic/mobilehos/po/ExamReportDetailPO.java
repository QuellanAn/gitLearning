package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;

public class ExamReportDetailPO implements RowMapper<ExamReportDetailPO>{
	
	private int rdId;
	
	private String reportId;
	
	private String itemName;
	
	private String itemNumber;
	
	private String resultValue;
	
	private String resultDesc;
	
	private String unit;
	
	private String refValue;
	
	public int getRdId() {
		return rdId;
	}

	public void setRdId(int rdId) {
		this.rdId = rdId;
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	public String getResultValue() {
		return resultValue;
	}

	public void setResultValue(String resultValue) {
		this.resultValue = resultValue;
	}

	public String getResultDesc() {
		return resultDesc;
	}

	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getRefValue() {
		return refValue;
	}

	public void setRefValue(String refValue) {
		this.refValue = refValue;
	}

	@Override
	public ExamReportDetailPO mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		ExamReportDetailPO rd = new ExamReportDetailPO();
		rd.setRdId(rs.getInt("rd_id"));
		rd.setReportId(StringUtils.trim(rs.getString("report_id")));
		rd.setItemName(StringUtils.trim(rs.getString("item_name")));
		rd.setItemNumber(StringUtils.trim(rs.getString("item_number")));
		rd.setRefValue(StringUtils.trim(rs.getString("ref_value")));
		rd.setResultValue(StringUtils.trim(rs.getString("result_value")));
		rd.setResultDesc(StringUtils.trim(rs.getString("result_desc")));
		rd.setUnit(StringUtils.trim(rs.getString("unit")));
		return rd;
	}
	

}
