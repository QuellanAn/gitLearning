package com.catic.mobilehos.service;

import java.util.List;

import com.catic.mobilehos.po.WechatCfgPO;

public interface WechatCfgService {

	public List<WechatCfgPO> getWechatCfgs();

	ServiceResult saveWechatCfgs(List<WechatCfgPO> cfgs);
}
