package com.catic.mobilehos.pay.entity;

public class CheckImport {

	private String iid;
    private String billDate;
	private String name;
	private String fileName;
	private String filePath;
	private Integer submitNum;
	private Integer successNum;
	private String noFileName;
	private String noFilePath;
	private String source;
	private String operator;
	private String createTime;
	
	private String startDate;
	private String endDate;
	private int  count;
	
	public String getIid() {
		return iid;
	}
	public void setIid(String iid) {
		this.iid = iid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Integer getSubmitNum() {
		return submitNum;
	}
	public void setSubmitNum(Integer submitNum) {
		this.submitNum = submitNum;
	}
	public Integer getSuccessNum() {
		return successNum;
	}
	public void setSuccessNum(Integer successNum) {
		this.successNum = successNum;
	}
	public String getNoFileName() {
		return noFileName;
	}
	public void setNoFileName(String noFileName) {
		this.noFileName = noFileName;
	}
	public String getNoFilePath() {
		return noFilePath;
	}
	public void setNoFilePath(String noFilePath) {
		this.noFilePath = noFilePath;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	
 
}
