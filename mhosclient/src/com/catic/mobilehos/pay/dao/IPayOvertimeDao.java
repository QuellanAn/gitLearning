package com.catic.mobilehos.pay.dao;

import com.catic.mobilehos.pay.entity.PayOvertime;

public interface IPayOvertimeDao {

	public PayOvertime findByOutTradeNo(String outTradeNo,String refundStatus) throws Exception;
	
	public Boolean save(PayOvertime payOvertime) throws Exception;
	
	public Boolean update(PayOvertime payOvertime) throws Exception;
}
