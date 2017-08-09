package com.catic.mobilehos.pay.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.catic.mobilehos.pay.dao.BaseDao;
import com.catic.mobilehos.pay.dao.ICheckSourceDao;
import com.catic.mobilehos.pay.entity.CheckSource;
import com.catic.mobilehos.utils.Page;

@Repository("checkSourceDao")
public class CheckSourceDaoImpl extends BaseDao implements ICheckSourceDao {

	@Override
	public List<CheckSource> findAll(Page page) throws Exception {
		String  sql = null;
		List<Object> params=new ArrayList<Object>();
		if(page==null){
			sql= "SELECT count(0) AS count FROM pay_check_source";
		}else{
			sql= "SELECT * FROM pay_check_source ";
		}
		StringBuilder sb=new StringBuilder(sql);
		if(page!=null){
			sb.append(" LIMIT ?,?");
			params.add(page.getFirstIndex());
			params.add(page.getPageSize());
		}
		List<CheckSource> list = jdbc.query(sb.toString(),params.toArray(), new BeanPropertyRowMapper(CheckSource.class));
		if (list.size() >0) {
			return list;
		}
		return null;
	}

	@Override
	public List<CheckSource> findByJson() throws Exception {
		String  sql = "SELECT * FROM pay_check_source WHERE status=0";
		List<CheckSource> list = jdbc.query(sql, new BeanPropertyRowMapper(CheckSource.class));
		if (list.size() >0) {
			return list;
		}
		return null;
	}

	@Override
	public int modify(CheckSource csource) throws Exception {
		String  sql = "UPDATE pay_check_source SET checkCode=?";
		List<Object> params=new ArrayList<Object>();	
		StringBuilder sb=new StringBuilder(sql);
		params.add(csource.getCheckCode());
		if(StringUtils.isNotBlank(csource.getCheckSource())){
			sb.append(",checkSource=?");
			params.add(csource.getCheckSource());
		}
		if(csource.getStatus()!=null){
			sb.append(",status=?");
			params.add(csource.getStatus());
		}		
	    sb.append(" WHERE checkCode=?");
		params.add(csource.getCheckCode());
		int count = jdbc.update(sb.toString(),params.toArray());
		return count;
	}

	@Override
	public CheckSource findByCode(String code) throws Exception {
		String  sql = "SELECT *  FROM pay_check_source WHERE checkCode=?";
		List<Object> params=new ArrayList<Object>();	
		StringBuilder sb=new StringBuilder(sql);
		params.add(code);
		List<CheckSource> list = jdbc.query(sb.toString(),params.toArray(), new BeanPropertyRowMapper(CheckSource.class));
		if (list!=null&&list.size() >0) {
			return list.get(0);
		}
		return null;
	}

}
