package com.catic.mobilehos.pay.biz.impl;

import org.springframework.stereotype.Service;

import com.catic.mobilehos.pay.biz.BaseBiz;
import com.catic.mobilehos.pay.biz.IPayOverTimeBiz;
import com.catic.mobilehos.pay.entity.PayOvertime;

@Service("payOverTimeBiz")
public class PayOverTimeBizImpl  extends BaseBiz implements IPayOverTimeBiz {

	@Override
	public PayOvertime findByOutTradeNo(String outTradeNo,String refundStatus) throws Exception {
		// TODO Auto-generated method stub
		return payOvertimeDao.findByOutTradeNo(outTradeNo,refundStatus);
	}

	@Override
	public Boolean save(PayOvertime payOvertime) throws Exception {
		// TODO Auto-generated method stub
		return payOvertimeDao.save(payOvertime);
	}

	@Override
	public Boolean update(PayOvertime payOvertime) throws Exception {
		// TODO Auto-generated method stub
		return payOvertimeDao.update(payOvertime);
	}

}
