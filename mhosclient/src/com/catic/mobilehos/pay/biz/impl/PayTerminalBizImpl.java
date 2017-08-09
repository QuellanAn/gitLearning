package com.catic.mobilehos.pay.biz.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.catic.mobilehos.pay.biz.BaseBiz;
import com.catic.mobilehos.pay.biz.IPayTerminalBiz;
import com.catic.mobilehos.pay.entity.PayTerminal;
import com.catic.mobilehos.utils.Page;

@Service("payTerBiz")
public class PayTerminalBizImpl extends BaseBiz implements IPayTerminalBiz {
	private Log log = LogFactory.getLog(this.getClass());

	@Override
	public Boolean insert(PayTerminal payTer) throws Exception {
		return payTemDao.insert(payTer);
	}

	@Override
	public List<PayTerminal> findAll(Page page, PayTerminal payTer)
			throws Exception {
		return payTemDao.findAll(page, payTer);
	}

	@Override
	public Boolean delete(int id) throws Exception {
		return payTemDao.delete(id);
	}

	@Override
	public Boolean modify(PayTerminal payTer) throws Exception {
		return payTemDao.modify(payTer);
	}

	@Override
	public PayTerminal findById(int id) throws Exception {
		return payTemDao.findById(id);
	}
	
	@Override
	public PayTerminal findByCode(String code) throws Exception {
		
		return payTemDao.findByCode(code);
	}

	@Override
	public List<PayTerminal> findFacilityVersion(Page page, PayTerminal payTer)
			throws Exception {
		return payTemDao.findFacilityVersion(page, payTer);
	}

	@Override
	public boolean updateVersioncode(PayTerminal payTer) {
		return payTemDao.updateVersioncode(payTer);
	}

}
