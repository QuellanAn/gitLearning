/**   
 * @Title: AppRegExceptionPO.java 
 * @Package com.catic.mobilehos.po 
 * @Description: TODO
 * @author htWang   
 * @date 2014-6-19 下午3:07:54 
 * 
 */ 
package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;



/**
 * @author WANG
 *
 */
public class AppRegExceptionPO implements RowMapper<AppRegExceptionPO>{
	private int exceptId;
	private String orderId;
	private String exceptionType;
	private Timestamp exceptionTime;


	public AppRegExceptionPO mapRow(ResultSet rs, int rowCount)
			throws SQLException {
		AppRegExceptionPO hisAppRegOrderException = new AppRegExceptionPO();
		hisAppRegOrderException.setOrderId(rs.getString("order_id"));
		hisAppRegOrderException
				.setExceptionType(rs.getString("exception_type"));
		hisAppRegOrderException.setExceptionTime(rs
				.getTimestamp("exception_time"));
		hisAppRegOrderException.setExceptId(rs.getInt("excep_id"));
		return hisAppRegOrderException;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getExceptionType() {
		return exceptionType;
	}

	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}

	public Timestamp getExceptionTime() {
		return exceptionTime;
	}

	public void setExceptionTime(Timestamp exceptionTime) {
		this.exceptionTime = exceptionTime;
	}

	public int getExceptId() {
		return exceptId;
	}

	public void setExceptId(int exceptId) {
		this.exceptId = exceptId;
	}
}
