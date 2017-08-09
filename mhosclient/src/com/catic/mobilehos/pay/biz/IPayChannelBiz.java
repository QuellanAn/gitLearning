package com.catic.mobilehos.pay.biz;

import java.util.List;

import com.catic.mobilehos.pay.entity.PayChannel;
import com.catic.mobilehos.utils.Page;

public interface IPayChannelBiz {

	public Boolean save(PayChannel payChannel) throws Exception;

	public List<PayChannel> findAll(PayChannel payChannel, Page page)
			throws Exception;

	public PayChannel findByID(String cID) throws Exception;

	public boolean modify(PayChannel payChannel) throws Exception;

}
