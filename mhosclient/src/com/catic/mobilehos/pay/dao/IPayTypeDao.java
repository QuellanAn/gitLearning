package com.catic.mobilehos.pay.dao;

import java.util.List;

import com.catic.mobilehos.pay.entity.PayType;
import com.catic.mobilehos.utils.Page;

public interface IPayTypeDao {

	public List<PayType> findAll() throws Exception;
	
	public List<PayType> find(Page page) throws Exception;
	
	public boolean modify(PayType payType) throws Exception ;
	
	public PayType findByCode(String ptCode) throws Exception;
}
