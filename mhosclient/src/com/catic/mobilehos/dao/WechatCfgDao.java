package com.catic.mobilehos.dao;

import java.util.List;

import com.catic.mobilehos.po.WechatCfgPO;

public interface WechatCfgDao {

	public List<WechatCfgPO> getWechatCfgs();

	void saveWechatCfgs(List<WechatCfgPO> cfgs);
}
