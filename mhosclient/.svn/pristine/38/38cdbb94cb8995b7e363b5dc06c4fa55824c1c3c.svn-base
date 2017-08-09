package com.catic.mobilehos.service;

import com.catic.mobilehos.dao.OpenFireGroupDAO;
import com.catic.mobilehos.po.OpenFireGroupPO;
import com.catic.mobilehos.po.OpenFireGroupPropPO;

/**
 * 即时通信 openFire 用户组
 * 
 * @author linchunda
 * 
 */
public class OpenFireGroupServiceImpl implements OpenFireGroupService {
	private OpenFireGroupDAO openFireGroupDAO;

	public OpenFireGroupDAO getOpenFireGroupDAO() {
		return openFireGroupDAO;
	}

	public void setOpenFireGroupDAO(OpenFireGroupDAO openFireGroupDAO) {
		this.openFireGroupDAO = openFireGroupDAO;
	}
	
	@Override
	public boolean haveGroupName(String groupName) {
		return openFireGroupDAO.haveGroupName(groupName);
	}

	@Override
	public void addGroup(OpenFireGroupPO openFireGroupPO) {
		openFireGroupDAO.addGroup(openFireGroupPO);

		openFireGroupDAO.addGroupProp(new OpenFireGroupPropPO(openFireGroupPO
				.getGroupName(), "sharedRoster.displayName", ""));
		openFireGroupDAO.addGroupProp(new OpenFireGroupPropPO(openFireGroupPO
				.getGroupName(), "sharedRoster.groupList", ""));
		openFireGroupDAO.addGroupProp(new OpenFireGroupPropPO(openFireGroupPO
				.getGroupName(), "sharedRoster.showInRoster", "nobody"));
	}
}
