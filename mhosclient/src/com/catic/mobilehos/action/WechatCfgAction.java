package com.catic.mobilehos.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.catic.mobilehos.po.WechatCfgPO;
import com.catic.mobilehos.service.ServiceResult;
import com.catic.mobilehos.service.WechatCfgService;

public class WechatCfgAction extends BaseAction {

	private static final long serialVersionUID = 397078750536070186L;

	private WechatCfgService wechatCfgService;

	private String wechat_msg_subscribe;
	private String wechat_msg_appreg;
	private String wechat_msg_unappreg;

	public String loadWechatCfgAction() {
		List<WechatCfgPO> cfgs = wechatCfgService.getWechatCfgs();
		Map<String, String> data = new HashMap<String, String>();
		for (int i = 0; i < cfgs.size(); i++) {
			data.put(cfgs.get(i).getDataName(), cfgs.get(i).getDataValue());
		}
		request.setAttribute("data", data);
		return SUCCESS;
	}

	public String saveWechatCfgAction() {
		List<WechatCfgPO> cfgs = new ArrayList<WechatCfgPO>();
		WechatCfgPO wcpo1 = new WechatCfgPO();
		wcpo1.setDataName("wechat_msg_subscribe");
		wcpo1.setDataValue(wechat_msg_subscribe);
		cfgs.add(wcpo1);
		WechatCfgPO wcpo2 = new WechatCfgPO();
		wcpo2.setDataName("wechat_msg_appreg");
		wcpo2.setDataValue(wechat_msg_appreg);
		cfgs.add(wcpo2);
		WechatCfgPO wcpo3 = new WechatCfgPO();
		wcpo3.setDataName("wechat_msg_unappreg");
		wcpo3.setDataValue(wechat_msg_unappreg);
		cfgs.add(wcpo3);
		ServiceResult sr = wechatCfgService.saveWechatCfgs(cfgs);
		return this.setServiceResultToJson(sr);
	}

	public WechatCfgService getWechatCfgService() {
		return wechatCfgService;
	}

	public void setWechatCfgService(WechatCfgService wechatCfgService) {
		this.wechatCfgService = wechatCfgService;
	}

	public String getWechat_msg_appreg() {
		return wechat_msg_appreg;
	}

	public void setWechat_msg_appreg(String wechat_msg_appreg) {
		this.wechat_msg_appreg = wechat_msg_appreg;
	}

	public String getWechat_msg_subscribe() {
		return wechat_msg_subscribe;
	}

	public void setWechat_msg_subscribe(String wechat_msg_subscribe) {
		this.wechat_msg_subscribe = wechat_msg_subscribe;
	}

	public String getWechat_msg_unappreg() {
		return wechat_msg_unappreg;
	}

	public void setWechat_msg_unappreg(String wechat_msg_unappreg) {
		this.wechat_msg_unappreg = wechat_msg_unappreg;
	}
}
