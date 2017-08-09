package com.catic.mobilehos.pay.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.catic.mobilehos.pay.dao.BaseDao;
import com.catic.mobilehos.pay.dao.IAccountPayTypeDao;
import com.catic.mobilehos.pay.entity.AccountPayType;

/*
 * 资金账户与支付方式中间表
 */
@Repository("accountPayTypeDao")
public class AccountPayTypeDaoImpl extends BaseDao implements IAccountPayTypeDao {

	/**
	 * 保存
	 * @param ap
	 * @return
	 */
	public boolean save(AccountPayType ap)throws Exception{
		String sql="INSERT INTO pay_account_payType(apID,ptCode,accountCode) VALUES(?,?,?)";
		List<Object> params=new ArrayList<Object>();
		params.add(ap.getApID());
		params.add(ap.getPtCode());
		params.add(ap.getAccountCode());
		int count=jdbc.update(sql,params.toArray());
		if(count>0){
			return true;
		}
		return false;
	}
	
	
	/**
	 * 删除
	 * @param accountCode
	 * @return
	 */
	public boolean delete(String accountCode)throws Exception{
		String sql="DELETE FROM pay_account_payType WHERE accountCode=? ";
		List<Object> params=new ArrayList<Object>();
		params.add(accountCode);
		int count=jdbc.update(sql,params.toArray());
		if(count>0){
			return true;
		}
		return false;
	}
	
	
	/**
	 * 查询
	 * @param accountCode
	 * @return
	 */
	public List<AccountPayType> find(String accountCode)throws Exception{
		String sql="SELECT * FROM pay_account_payType WHERE accountCode=?";
		List<Object> params=new ArrayList<Object>();
		params.add(accountCode);
		 List<AccountPayType>  list=jdbc.query(sql,params.toArray(),new BeanPropertyRowMapper(AccountPayType.class));
		return list;
	}
}
