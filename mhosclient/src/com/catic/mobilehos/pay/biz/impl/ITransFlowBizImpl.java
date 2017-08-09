package com.catic.mobilehos.pay.biz.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.catic.mobilehos.pay.biz.BaseBiz;
import com.catic.mobilehos.pay.biz.ITransFlowBiz;
import com.catic.mobilehos.pay.entity.TransFlow;
@Service("transFlowBiz")
public class ITransFlowBizImpl extends BaseBiz implements ITransFlowBiz {

	/* 
	 * 根据商户号、交易类型查询
	 * @see com.catic.mobilehos.pay.biz.ITransFlowBiz#find(java.lang.String, java.lang.Integer)
	 */
	@Override
	public List<TransFlow> find(String outTradeNo, Integer transType)
			throws Exception {
		
		return transFlowDao.find(outTradeNo, transType);
	}

}
