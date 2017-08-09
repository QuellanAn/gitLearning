package com.catic.mobilehos.service;

import java.util.List;

import com.catic.mobilehos.dao.OpenFireUserDAO;
import com.catic.mobilehos.po.OpenFireUserPO;
import com.catic.mobilehos.service.vo.Page;

/**
 * 即时通信OpenFire用户
 * 
 * @author linchunda
 * 
 */
public class OpenFireUserServiceImpl implements OpenFireUserService {

	private OpenFireUserDAO openFireUserDAO;

	public OpenFireUserDAO getOpenFireUserDAO() {
		return openFireUserDAO;
	}

	public void setOpenFireUserDAO(OpenFireUserDAO openFireUserDAO) {
		this.openFireUserDAO = openFireUserDAO;
	}

	@Override
	public String getPasswordKey() {
		return openFireUserDAO.getPasswordKey();
	}

	@Override
	public OpenFireUserPO findByUsername(String username) {
		return openFireUserDAO.findByUsername(username);
	}

	@Override
	public void addOpenFireUserPO(OpenFireUserPO openFireUserPO,
			String groupName) {
		openFireUserDAO.addOpenFireUserPO(openFireUserPO);
		openFireUserDAO.addUserToGroup(groupName, openFireUserPO.getUsername());
	}

	@Override
	public boolean updateOpenFireUserPO(String username,
			OpenFireUserPO openFireUserPO) {
		return openFireUserDAO.updateOpenFireUserPO(username, openFireUserPO);
	}

	@Override
	public boolean deleteOpenFireUserPO(String username) {
		return openFireUserDAO.deleteOpenFireUserPO(username);
	}

	@Override
	public Page<OpenFireUserPO> findOpenFireUserPOByByParas(String groupName,
			String username, int curPage, int pageSize) {
		int totalRecord = openFireUserDAO.getCountOpenFireUserPO(groupName,
				username);
		Page<OpenFireUserPO> page = new Page<OpenFireUserPO>(totalRecord,
				pageSize, curPage);
		List<OpenFireUserPO> list = openFireUserDAO.findOpenFireUserPOByByParas(
				groupName, username, page.getOffset(), page.getPageSize());
		page.setCurPageData(list);
		return page;
	}

}
