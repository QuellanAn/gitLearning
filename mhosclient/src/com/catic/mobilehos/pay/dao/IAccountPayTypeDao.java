package com.catic.mobilehos.pay.dao;

import java.util.List;

import com.catic.mobilehos.pay.entity.AccountPayType;

public interface IAccountPayTypeDao {

	public boolean save(AccountPayType ap)throws Exception;
	public boolean delete(String accountCode)throws Exception;
	public List<AccountPayType> find(String accountCode)throws Exception;
	
}
