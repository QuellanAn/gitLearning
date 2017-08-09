package com.catic.mobilehos.pay.biz;

import java.util.List;

import com.catic.mobilehos.pay.entity.PayLog;

public interface IPayLogBiz {
	public List<PayLog> findPayLog(PayLog pl) throws Exception;
	
	public Boolean saveLog(PayLog pl) throws Exception;
}