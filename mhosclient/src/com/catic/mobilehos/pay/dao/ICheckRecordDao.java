package com.catic.mobilehos.pay.dao;

import java.util.List;

import com.catic.mobilehos.pay.entity.CheckRecord;
import com.catic.mobilehos.utils.Page;

public interface ICheckRecordDao {
	public List<CheckRecord> findAll(Page page,CheckRecord check) throws Exception ;
	
	public Boolean save(CheckRecord check)throws Exception;
	
	public CheckRecord find(Integer transType,String transactionNum) throws Exception;
}
