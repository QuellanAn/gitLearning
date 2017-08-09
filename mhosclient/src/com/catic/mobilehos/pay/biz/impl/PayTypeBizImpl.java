package com.catic.mobilehos.pay.biz.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.catic.mobilehos.pay.biz.BaseBiz;
import com.catic.mobilehos.pay.biz.IPayTypeBiz;
import com.catic.mobilehos.pay.entity.PayType;
import com.catic.mobilehos.utils.Page;

@Service("payTypeBiz")
public class PayTypeBizImpl extends BaseBiz implements IPayTypeBiz{

	@Override
	public List<PayType> findAll() throws Exception {
	   
		return payTypeDao.findAll();
	}

	@Override
	public List<PayType> find(Page page) throws Exception {
		
		return payTypeDao.find(page);
	}

	@Override
	public boolean modify(PayType payType) throws Exception {
		return payTypeDao.modify(payType);
	}

	@Override
	public PayType findByCode(String ptCode) throws Exception {
		
		return payTypeDao.findByCode(ptCode);
	}

}
