package com.catic.mobilehos.pay.biz.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.catic.mobilehos.pay.biz.BaseBiz;
import com.catic.mobilehos.pay.biz.IPayChannelBiz;
import com.catic.mobilehos.pay.entity.PayChannel;
import com.catic.mobilehos.utils.Page;
@Service("payChannelBiz")
public class PayChannelBizImpl extends BaseBiz implements IPayChannelBiz{

	@Override
	public Boolean save(PayChannel payChannel) throws Exception {
		
		return payChannelDao.save(payChannel);
	}

	@Override
	public List<PayChannel> findAll(PayChannel payChannel, Page page)
			throws Exception {
		return payChannelDao.findAll(payChannel, page);
	}

	@Override
	public PayChannel findByID(String cID) throws Exception {
		return payChannelDao.findByID(cID);
	}

	@Override
	public boolean modify(PayChannel payChannel) throws Exception {
		
		return payChannelDao.modify(payChannel);
	}

}
