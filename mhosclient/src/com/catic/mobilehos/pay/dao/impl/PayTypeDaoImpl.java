package com.catic.mobilehos.pay.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.catic.mobilehos.pay.dao.BaseDao;
import com.catic.mobilehos.pay.dao.IPayTypeDao;
import com.catic.mobilehos.pay.entity.PayType;
import com.catic.mobilehos.utils.Page;

@Repository("payTypeDao")
public class PayTypeDaoImpl extends BaseDao implements IPayTypeDao{

	/**
	 * 查询所有
	 */
	@Override
	public List<PayType> findAll() throws Exception {
		String  sql = "SELECT * FROM pay_paytype WHERE ptStatus=0";
		List<PayType> list = jdbc.query(sql, new BeanPropertyRowMapper(PayType.class));
		if (list.size() >0) {
			return list;
		}
		return null;
	}	
	/**
	 * 分页查询
	 */
	@Override
	public List<PayType> find(Page page) throws Exception {
		String  sql = null;
		List<Object> params=new ArrayList<Object>();
		if(page==null){
			sql= "SELECT count(0) AS count FROM pay_paytype";
		}else{
			sql= "SELECT * FROM pay_paytype ";
		}
		StringBuilder sb=new StringBuilder(sql);
		if(page!=null){
			sb.append(" LIMIT ?,?");
			params.add(page.getFirstIndex());
			params.add(page.getPageSize());
		}
		List<PayType> list = jdbc.query(sb.toString(),params.toArray(), new BeanPropertyRowMapper(PayType.class));
		if (list.size() >0) {
			return list;
		}
		return null;
	}	
	
	/**
	 * 修改
	 */
	@Override
	public boolean modify(PayType payType) throws Exception {
		String  sql = "UPDATE pay_paytype SET ptCode=?";
		List<Object> params=new ArrayList<Object>();	
		StringBuilder sb=new StringBuilder(sql);
		params.add(payType.getPtCode());
		if(StringUtils.isNotBlank(payType.getPtName())){
			sb.append(",ptName=?");
			params.add(payType.getPtName());
		}
		if(payType.getPtStatus()!=null){
			sb.append(",ptStatus=?");
			params.add(payType.getPtStatus());
		}		
	    sb.append(" WHERE ptCode=?");
		params.add(payType.getPtCode());
		int count = jdbc.update(sb.toString(),params.toArray());
		if (count>0) {
			return true;
		}
		return false;
	}	
	/**
	 * 通过编号查询
	 */
	@Override
	public PayType findByCode(String ptCode) throws Exception {
		String  sql = "SELECT *  FROM pay_paytype WHERE ptCode=?";
		List<Object> params=new ArrayList<Object>();	
		StringBuilder sb=new StringBuilder(sql);
		params.add(ptCode);
		List<PayType> list = jdbc.query(sb.toString(),params.toArray(), new BeanPropertyRowMapper(PayType.class));
		if (list!=null&&list.size() >0) {
			return list.get(0);
		}
		return null;
	}	
}
