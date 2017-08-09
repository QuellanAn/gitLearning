package com.catic.mobilehos.pay.biz.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.catic.mobilehos.pay.biz.BaseBiz;
import com.catic.mobilehos.pay.biz.ICheckSourceBiz;
import com.catic.mobilehos.pay.entity.CheckSource;
import com.catic.mobilehos.utils.Page;

@Service("checkSourceBiz")
public class CheckSourceZipImpl extends BaseBiz implements ICheckSourceBiz {

	@Override
	public List<CheckSource> findAll(Page page) throws Exception {
		return checkSourceDao.findAll(page);
	}

	@Override
	public List<CheckSource> findByJson() throws Exception {
		return checkSourceDao.findByJson();
	}

	@Override
	public int modify(CheckSource csource) throws Exception {
		return checkSourceDao.modify(csource);
	}

	@Override
	public CheckSource findByCode(String code) throws Exception {
		return checkSourceDao.findByCode(code);
	}

}
