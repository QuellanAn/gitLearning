package com.catic.mobilehos.pay.dao;

import java.util.List;

import com.catic.mobilehos.pay.entity.Account;
import com.catic.mobilehos.utils.Page;

public interface IAccountConfigDao {

    public Boolean save(Account account) throws Exception;
	
	public List<Account> findAll(Page page) throws Exception ;
	
	public Boolean delete(String accountCode) throws Exception ;
	
	public Boolean modify(Account account) throws Exception ;
	
	public Account  findByCode(String code) throws Exception ;
	
	public List<Account> findAll() throws Exception;
}
