package com.catic.mobilehos.po;  

import java.util.List;

/**  
 * 类说明:用于封装业务量统计数据的bean
 * @author 朱伟权
 * 创建时间: 2017-5-18 下午7:49:44
 */
public class TableRowData {
	
	private String rowName;// 这一行数据的名称
	private List<String> rowData;// 具体数据
	private int dataSum;// 数量合计，主要用于排序
	
	public String getRowName() {
		return rowName;
	}
	public void setRowName(String rowName) {
		this.rowName = rowName;
	}
	public List<String> getRowData() {
		return rowData;
	}
	public void setRowData(List<String> rowData) {
		this.rowData = rowData;
	}
	public int getDataSum() {
		return dataSum;
	}
	public void setDataSum(int dataSum) {
		this.dataSum = dataSum;
	}
	
}
 