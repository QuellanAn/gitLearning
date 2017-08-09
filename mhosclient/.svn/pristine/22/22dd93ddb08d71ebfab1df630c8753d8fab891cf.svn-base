package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
/**
 * 医嘱列表
 * @author gds
 *
 */
public class ItemsList implements RowMapper<ItemsList>{
	private String id;
	private String admRowid;//医嘱id
	private String tarOCCateAmt;//总费用
	private String tarOCCateDesc;//列表明细名称
	
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getAdmRowid() {
		return admRowid;
	}


	public void setAdmRowid(String admRowid) {
		this.admRowid = admRowid;
	}


	public String getTarOCCateAmt() {
		return tarOCCateAmt;
	}


	public void setTarOCCateAmt(String tarOCCateAmt) {
		this.tarOCCateAmt = tarOCCateAmt;
	}


	public String getTarOCCateDesc() {
		return tarOCCateDesc;
	}


	public void setTarOCCateDesc(String tarOCCateDesc) {
		this.tarOCCateDesc = tarOCCateDesc;
	}


	@Override
	public ItemsList mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		ItemsList il = new ItemsList();
		il.setId(rs.getString("id"));
		il.setAdmRowid(rs.getString("admRowid"));
		il.setTarOCCateAmt(rs.getString("tarOCCateAmt"));
		il.setTarOCCateDesc(rs.getString("tarOCCateDesc"));
		return il;
	}

}
