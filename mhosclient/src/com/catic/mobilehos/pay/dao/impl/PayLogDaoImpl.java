package com.catic.mobilehos.pay.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.catic.mobilehos.pay.dao.BaseDao;
import com.catic.mobilehos.pay.dao.IPayLogDao;
import com.catic.mobilehos.pay.entity.PayLog;

@Repository("payLogDao")
public class PayLogDaoImpl extends BaseDao implements IPayLogDao{

	@SuppressWarnings("unchecked")
	public List<PayLog> findLog(PayLog pl) throws Exception{
		String sql="SELECT * from pay_log WHERE 1=1 ";
		StringBuffer sb=new StringBuffer(sql);
		List<Object> params=new ArrayList<Object>();
		if(pl.getId()!=null){
			sb.append(" AND Id=?");
			params.add(pl.getId());
		}
		if(StringUtils.isNotBlank(pl.getOrderId())){
			sb.append(" AND orderId=?");
			params.add(pl.getOrderId());
		}
		if(StringUtils.isNotBlank(pl.getOut_trade_no())){
			sb.append(" AND out_trade_no=?");
			params.add(pl.getOut_trade_no());
		}

		List<PayLog> pllist=jdbc.query(sb.toString(), params.toArray(),new BeanPropertyRowMapper(PayLog.class));
		return pllist;
		
	}
	
	
	public Boolean saveLog(PayLog pl) throws Exception{
		String sql="INSERT INTO pay_log(itemName,status,createTime,orderId,out_trade_no) VALUES (?,?,NOW(),?,?)";
		StringBuffer sb=new StringBuffer(sql);
		List<Object> params=new ArrayList<Object>();
		params.add(pl.getItemName());
		params.add(pl.getStatus());
		params.add(pl.getOrderId());
		params.add(pl.getOut_trade_no());
		if(StringUtils.isNotBlank(pl.getOrderId())){
			sb.append(" AND orderId=?");
			params.add(pl.getOrderId());
		}
		int count=jdbc.update(sb.toString(), params.toArray());
		if(count>0){
			return true;
		}
		return false;
		
	}
}
