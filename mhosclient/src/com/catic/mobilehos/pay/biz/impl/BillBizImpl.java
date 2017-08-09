package com.catic.mobilehos.pay.biz.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.catic.mobilehos.pay.biz.BaseBiz;
import com.catic.mobilehos.pay.biz.IBillBiz;
import com.catic.mobilehos.pay.entity.Bill;
import com.catic.mobilehos.utils.Page;
@Service("billBiz")
public class BillBizImpl extends BaseBiz implements IBillBiz{

	@Override
	public List<Bill> findAll(Bill bill, Page page) throws Exception {
		
		return billDao.findAll(bill, page);
	}

	@Override
	public boolean upStarus(Integer status, String bid) throws Exception {
		return billDao.upStarus(status, bid);
	}
}
