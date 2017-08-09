package com.catic.mobilehos.pay.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.catic.mobilehos.pay.dao.BaseDao;
import com.catic.mobilehos.pay.dao.IPayOvertimeDao;
import com.catic.mobilehos.pay.entity.PayOvertime;

@Repository("payOvertimeDao")
public class PayOvertimeDaoImpl extends BaseDao implements IPayOvertimeDao{

	/*
	 * 通过商户单号查询
	 */
	@Override
	public PayOvertime findByOutTradeNo(String outTradeNo,String refundStatus) throws Exception{
		String sql="SELECT * FROM pay_overtime WHERE outTradeNo='"+outTradeNo+"'";
		StringBuilder sb=new StringBuilder(sql);
		List<Object> params=new ArrayList<Object>();
		if(StringUtils.isNotBlank(refundStatus)){
			sb.append(" AND refundStatus=?");
			params.add(refundStatus);
			
		}
		List<PayOvertime>  list=jdbc.query(sb.toString(),params.toArray(),new BeanPropertyRowMapper(PayOvertime.class));
		
		if(list!=null&&list.size()>0){
		
			return list.get(0);
		}
		return null;
		
	}
	
	/*
	 * 插入数据
	 */
	@Override
	public Boolean save(PayOvertime payOvertime) throws Exception{
		String sql="INSERT INTO pay_overtime(overTimeId,outTradeNo,transactionId,amount,refundStatus,createTime) VALUES (?,?,?,?,?,NOW())";
	
		List<Object> params=new ArrayList<Object>();
		params.add(payOvertime.getOverTimeId());
		params.add(payOvertime.getOutTradeNo());
		params.add(payOvertime.getTransactionId());
		params.add(payOvertime.getAmount());
		params.add(payOvertime.getRefundStatus());
		int  count=jdbc.update(sql,params.toArray());
		
		if(count>0){
			return true; 
		}
		return false;
		
	}
	
	
	/*
	 * 更新数据
	 */
	@Override
	public Boolean update(PayOvertime payOvertime) throws Exception{
		String sql="UPDATE pay_overtime SET ";
		StringBuilder sb=new StringBuilder(sql);
		List<Object> params=new ArrayList<Object>();
		
		if(payOvertime.getRefundStatus()!=null){
			sb.append(" refundStatus=?");
		    params.add(payOvertime.getRefundStatus());
		}
		
		if(payOvertime.getPayStatus()!=null){
			sb.append(" ,payStatus=?");
		    params.add(payOvertime.getTransactionId());
		}
		
		if(StringUtils.isNotBlank(payOvertime.getTransactionId())){
			sb.append(" ,transactionId=?");
		    params.add(payOvertime.getTransactionId());
		}
		
		if(StringUtils.isNotBlank(payOvertime.getOutTradeNo())){
			sb.append(" WHERE outTradeNo=?");
		    params.add(payOvertime.getOutTradeNo());
		}
		int  count=jdbc.update(sb.toString(),params.toArray());
		
		if(count>0){
			return true; 
		}
		return false;
		
	}
	
	
}
