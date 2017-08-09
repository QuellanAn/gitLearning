package com.catic.mobilehos.pay.biz;

import java.util.List;

import com.catic.mobilehos.pay.entity.CheckRecord;
import com.catic.mobilehos.utils.Page;

public interface ICheckRecordBiz {
	public List<CheckRecord> findAll(Page page, CheckRecord check);
	
	public Boolean save(CheckRecord check)throws Exception;
	
	public CheckRecord find(Integer transType,String transactionNum) throws Exception;
}
