package com.catic.mobilehos.service;

import java.util.List;

import com.catic.mobilehos.dao.WechatCfgDao;
import com.catic.mobilehos.po.WechatCfgPO;

public class WechatCfgServiceImpl implements WechatCfgService {

	private WechatCfgDao wechatCfgDao;

	public WechatCfgDao getWechatCfgDao() {
		return wechatCfgDao;
	}

	public void setWechatCfgDao(WechatCfgDao wechatCfgDao) {
		this.wechatCfgDao = wechatCfgDao;
	}

	@Override
	public List<WechatCfgPO> getWechatCfgs() {
		return wechatCfgDao.getWechatCfgs();
	}

	@Override
	public ServiceResult saveWechatCfgs(List<WechatCfgPO> cfgs) {
		wechatCfgDao.saveWechatCfgs(cfgs);
		return ServiceResult.getSucInstance();
	}

}
