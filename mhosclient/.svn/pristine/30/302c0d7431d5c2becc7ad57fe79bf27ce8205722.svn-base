package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
/**
 * 缴费列表
 * @author gds
 *
 */
public class ItemsPO implements RowMapper<ItemsPO>{
	private String id;
	private String patientId;
	private String patientName;
	private String cardNo;
	private String admRowid;//医嘱id
	private String admLoc;//科室
	private String admDoctor;//医生
	private String fee;
	private String admDate;
	private String status;//缴费状态，0为成功，1为失败
	
	private String tarOCCateDesc;
	private String paySource;// 缴费来源，1表示微信，2表示支付宝
	
	// 以下为非数据库字段
	private String beginDate;// 开始日期
	private String endDate;// 结束日期
	private String countDate;// 统计日期
	private int count;
	private String countDateType;// 日期统计类型，day为按日，month为按月
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPatientId() {
		return patientId;
	}


	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}


	public String getPatientName() {
		return patientName;
	}


	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}


	public String getCardNo() {
		return cardNo;
	}


	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}


	public String getAdmRowid() {
		return admRowid;
	}


	public void setAdmRowid(String admRowid) {
		this.admRowid = admRowid;
	}


	public String getAdmLoc() {
		return admLoc;
	}


	public void setAdmLoc(String admLoc) {
		this.admLoc = admLoc;
	}


	public String getAdmDoctor() {
		return admDoctor;
	}


	public void setAdmDoctor(String admDoctor) {
		this.admDoctor = admDoctor;
	}


	public String getFee() {
		return fee;
	}


	public void setFee(String fee) {
		this.fee = fee;
	}


	public String getAdmDate() {
		return admDate;
	}


	public void setAdmDate(String admDate) {
		this.admDate = admDate;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getTarOCCateDesc() {
		return tarOCCateDesc;
	}


	public void setTarOCCateDesc(String tarOCCateDesc) {
		this.tarOCCateDesc = tarOCCateDesc;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getCountDate() {
		return countDate;
	}


	public void setCountDate(String countDate) {
		this.countDate = countDate;
	}


	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getCountDateType() {
		return countDateType;
	}


	public void setCountDateType(String countDateType) {
		this.countDateType = countDateType;
	}


	public String getPaySource() {
		return paySource;
	}


	public void setPaySource(String paySource) {
		this.paySource = paySource;
	}


	@Override
	public ItemsPO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		ItemsPO items = new ItemsPO();
		items.setId(rs.getString("id"));
		items.setPatientId(rs.getString("patient_id"));
		items.setPatientName(rs.getString("patient_Name"));
		items.setCardNo(rs.getString("cardNo"));
		items.setAdmRowid(rs.getString("admRowid"));
		items.setAdmLoc(rs.getString("admLoc"));
		items.setAdmDoctor(rs.getNString("admDoctor"));
		items.setFee(rs.getString("fee"));
		items.setAdmDate(rs.getString("admDate"));
		items.setStatus(rs.getString("status"));
		items.setTarOCCateDesc(rs.getString("tarOCCateDesc"));
		items.setPaySource(rs.getString("pay_source"));
		return items;
	}
	
}
