package com.catic.mobilehos.pay.dao;

import java.util.List;

import com.catic.mobilehos.pay.entity.PayScene;
import com.catic.mobilehos.utils.Page;

public interface IPaySceneDao {
	public List<PayScene> findAll(Page page) throws Exception ;
	
	public void modify(PayScene paysene) throws Exception;
	
	public PayScene findByCode(String sCode) throws Exception;
	
	public List<PayScene> findAll() throws Exception;
}
