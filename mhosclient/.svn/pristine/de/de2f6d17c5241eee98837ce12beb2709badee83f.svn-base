package com.catic.mobilehos.pay.biz.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.catic.mobilehos.pay.biz.BaseBiz;
import com.catic.mobilehos.pay.biz.IPayLogBiz;
import com.catic.mobilehos.pay.entity.PayLog;

@Service("payLogBiz")
public class PayLogBizImpl extends BaseBiz implements IPayLogBiz{

	public List<PayLog> findPayLog(PayLog pl) throws Exception{
		return payLogDao.findLog(pl); 
	
	}
	
	public Boolean saveLog(PayLog pl) throws Exception{
		return payLogDao.saveLog(pl);
	}
}