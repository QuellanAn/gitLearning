package com.catic.mobilehos.pay.dao;

import java.util.List;

import com.catic.mobilehos.pay.entity.Bill;
import com.catic.mobilehos.utils.Page;

public interface IBillDao {

	public List<Bill> findAll(Bill bill,Page page) throws Exception;
	
	public boolean upStarus(Integer status,String bid) throws Exception;
	
	public Bill findById(String id) throws Exception;
	
	public Boolean modify(Bill bill) throws Exception;

}
