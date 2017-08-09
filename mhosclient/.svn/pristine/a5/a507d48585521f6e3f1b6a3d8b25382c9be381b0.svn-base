package com.catic.mobilehos.pay.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.catic.mobilehos.pay.dao.BaseDao;
import com.catic.mobilehos.pay.dao.IPaySceneDao;
import com.catic.mobilehos.pay.entity.PayScene;
import com.catic.mobilehos.utils.Page;
@Repository("paySceneDao")
public class PaySceneDaoImpl extends BaseDao implements IPaySceneDao {

	public List<PayScene> findAll(Page page) throws Exception {
		String sql;
		if(page==null){
			sql="SELECT COUNT(0) AS count FROM pay_scene ";
		}else{
			sql="SELECT * FROM pay_scene ORDER BY sID ";
		}
		
		StringBuilder sb=new StringBuilder(sql);
		List<Object> param=new ArrayList<Object>();
		if(page!=null){
			sb.append(" LIMIT ?,?");
			param.add(page.getFirstIndex());
			param.add(page.getPageSize());
		}
		List<PayScene> list=jdbc.query(sb.toString(),param.toArray(),new BeanPropertyRowMapper(PayScene.class));
		return list;
	}

	public void modify(PayScene paysene) throws Exception {
		try{
			String sql = "update pay_scene set sName=?,sStatus=? where sCode=?";
			jdbc.update(sql,paysene.getSName(), paysene.getSStatus()
					  	,paysene.getSCode());
		}catch(DataAccessException ex){
			throw ex;
		}
	}

	public PayScene findByCode(String sCode) throws Exception {
		try{
			PayScene payScene=new PayScene();
			String sql = "select * from pay_scene ps where sCode=? ";
			payScene = jdbc.queryForObject(sql, new BeanPropertyRowMapper(PayScene.class),sCode);
			return payScene;
		}catch(DataAccessException ex){
			throw ex;
		}
	}
	
	public List<PayScene> findAll() throws Exception {
		String sql="SELECT * FROM pay_scene ORDER BY sID ";
		List<PayScene> list=jdbc.query(sql,new BeanPropertyRowMapper(PayScene.class));
		return list;
	}


}
