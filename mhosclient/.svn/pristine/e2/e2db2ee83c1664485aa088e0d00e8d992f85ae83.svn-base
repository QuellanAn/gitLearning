package com.catic.mobilehos.pay.biz.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.catic.mobilehos.pay.biz.BaseBiz;
import com.catic.mobilehos.pay.biz.IPayDictionaryBiz;
import com.catic.mobilehos.pay.entity.PayDictionary;

@Service("payDictionaryBiz")
public class PayDictionaryBizImpl extends BaseBiz implements IPayDictionaryBiz{

	@Override
	public List<PayDictionary> findAll(String dictionaryCode) throws Exception {
	   
		return payDictionaryDao.findAll(dictionaryCode);
	}

}
