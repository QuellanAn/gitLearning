package com.catic.mobilehos.pay.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.catic.mobilehos.pay.dao.BaseDao;
import com.catic.mobilehos.pay.dao.ISystermUserDao;
import com.catic.mobilehos.pay.entity.SystermUser;

@Repository("systermUserDao")
public class SystermUserDaoImpl extends BaseDao implements ISystermUserDao{

	@Override
	public String findPassword(String userId) throws Exception {
		String sql="SELECT password from sysusers WHERE userId=?";
		List<Object> params=new ArrayList<Object>();
		params.add(userId);
		List<SystermUser> list=jdbc.query(sql,params.toArray(),new BeanPropertyRowMapper(SystermUser.class));
		if(list!=null&&list.size()>0){
			return list.get(0).getPassword();
		}
		return null;
	}

}
