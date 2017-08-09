package com.catic.mobilehos.pay.biz.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.catic.mobilehos.pay.biz.BaseBiz;
import com.catic.mobilehos.pay.biz.IPaySceneBiz;
import com.catic.mobilehos.pay.entity.PayScene;
import com.catic.mobilehos.utils.Page;
@Service("paySceneBiz")
public class PaySceneBizImpl extends BaseBiz implements IPaySceneBiz {

	@Override
	public List<PayScene> findAll(Page page) throws Exception {
		return paySceneDao.findAll(page);
	}

	@Override
	public void modify(PayScene payScene) throws Exception {
		paySceneDao.modify(payScene);
	}

	@Override
	public PayScene findByCode(String sCode) throws Exception {
		return paySceneDao.findByCode(sCode);
	}

	@Override
	public List<PayScene> findAll() throws Exception {
	
		return paySceneDao.findAll();
	}

}
