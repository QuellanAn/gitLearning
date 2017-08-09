package com.catic.mobilehos.pay.biz.impl;

import org.springframework.stereotype.Service;

import com.catic.mobilehos.pay.biz.BaseBiz;
import com.catic.mobilehos.pay.biz.ISystermUserBiz;

@Service("systermUserBiz")
public class SystermUserBizImpl extends BaseBiz implements ISystermUserBiz{

	@Override
	public String findPassword(String userId) throws Exception {
		// TODO Auto-generated method stub
		return systermUserDao.findPassword(userId);
	}

}
