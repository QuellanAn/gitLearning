package com.catic.mobilehos.pay.biz;

import java.util.List;

import com.catic.mobilehos.pay.entity.Bill;
import com.catic.mobilehos.utils.Page;

public interface IBillBiz {

	public List<Bill> findAll(Bill bill,Page page) throws Exception;
	
	public boolean upStarus(Integer status,String bid) throws Exception;
}
