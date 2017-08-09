package com.catic.mobilehos.pay.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import sun.security.krb5.internal.PAEncTSEnc;

import com.catic.mobilehos.pay.dao.BaseDao;
import com.catic.mobilehos.pay.dao.IBillDao;
import com.catic.mobilehos.pay.entity.Bill;
import com.catic.mobilehos.utils.Page;
@Repository("billDao")
public class BillDaoImpl extends BaseDao implements IBillDao{

	public List<Bill> findAll(Bill bill,Page page) throws Exception{
		String sql="";
		if(page==null){
			sql="SELECT count(0) AS count FROM pay_bill WHERE 1=1";
		}else{
			sql="SELECT * FROM pay_bill WHERE 1=1";
		}
		List<Object> params=new ArrayList<Object>();
		StringBuilder sb=new StringBuilder(sql);
		if(StringUtils.isNotBlank(bill.getAccountCode())){
			sb.append(" AND accountCode=?");
			params.add(bill.getAccountCode());
		}
		if(bill.getCheckStatus()!=null){
			sb.append(" AND checkStatus=?");
			params.add(bill.getCheckStatus());
		}
		if(bill.getStatus()!=null){
			sb.append(" AND status=?");
			params.add(bill.getStatus());
		}
		if(StringUtils.isNotBlank(bill.getStartDate())){
			sb.append(" AND billDate>=?");
			params.add(bill.getStartDate());
		}
		if(StringUtils.isNotBlank(bill.getEndDate())){
			sb.append(" AND billDate<?");
			params.add(bill.getEndDate()+"23:59:59");
		}
		if(page!=null){
			sb.append("  ORDER BY createTime DESC  LIMIT ?,?");
			params.add(page.getFirstIndex());
			params.add(page.getPageSize());
		}
		List<Bill> list=jdbc.query(sb.toString(),params.toArray(),new BeanPropertyRowMapper(Bill.class));
		if(list!=null&&list.size()>0){
			return list;
		}
		return null;
	}
	
	
	public boolean upStarus(Integer status,String bid) throws Exception{
		String sql="UPDATE pay_bill SET `status`=? WHERE bid=?";
		List<Object> params=new ArrayList<Object>();
		params.add(status);
		params.add(bid);
		int count=jdbc.update(sql,params.toArray());
		if(count>0){
			return true;
		}
		return false;
	}
	
	/**
	 * 通过id查询
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Bill findById(String id) throws Exception{
		String sql="SELECT * FROM pay_bill WHERE bid=?";
		List<Object> params=new ArrayList<Object>();
		params.add(id);
		List<Bill> list=jdbc.query(sql,params.toArray(),new BeanPropertyRowMapper(Bill.class));
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 更改对账信息
	 * @param bill
	 * @return
	 * @throws Exception
	 */
	public Boolean modify(Bill bill) throws Exception{
		String sql="UPDATE pay_bill SET bid=?";
		StringBuilder sb=new StringBuilder(sql);
		List<Object> params=new ArrayList<Object>();
		params.add(bill.getBid());
		if(bill.getTotal_his()!=null){
			sb.append(",total_his=?");
			params.add(bill.getTotal_his());
		}
        if(bill.getAmount_his()!=null){
        	sb.append(",amount_his=?");
			params.add(bill.getAmount_his());
		}
        if(bill.getRefund_amount_his()!=null){
        	sb.append(",refund_amount_his=?");
			params.add(bill.getRefund_amount_his());
		}
        if(bill.getCheckStatus()!=null){
        	sb.append(",checkStatus=?");
			params.add(bill.getCheckStatus());
		}
        if(bill.getAbnormalNum()!=null){
        	sb.append(",abnormalNum=abnormalNum+?");
        	params.add(bill.getAbnormalNum());
		}
        sb.append(" WHERE 1=1");
        sb.append(" AND bid=?");
        params.add(bill.getBid());        
		int count =jdbc.update(sb.toString(),params.toArray());
		if(count>0){
			return true;
		}
		return false;
	}
}
