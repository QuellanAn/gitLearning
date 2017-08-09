package com.catic.mobilehos.pay.biz;

import java.util.List;

import com.catic.mobilehos.pay.entity.TransFlow;

public interface ITransFlowBiz {

	public List<TransFlow> find(String outTradeNo,Integer transType) throws Exception;
}
