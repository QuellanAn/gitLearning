package com.catic.mobilehos.pay.biz.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.catic.mobilehos.pay.biz.BaseBiz;
import com.catic.mobilehos.pay.biz.IYQAccountBiz;
import com.catic.mobilehos.pay.entity.YQInfo;

@Service("yQAccountBiz")
public class YQAccountBizImpl extends BaseBiz implements IYQAccountBiz {

	@Override
	public List<YQInfo> findYQ(String id) throws Exception {
		
		return yQAccountDao.findYQ(id);
	}

	@Override
	public Boolean save(String yqid, String accountId) throws Exception {
		
		return yQAccountDao.save(yqid, accountId);
	}

}
