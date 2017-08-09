package com.catic.mobilehos.service;

import java.util.List;

import com.catic.mobilehos.dao.SysUsersDAO;
import com.catic.mobilehos.po.SysUsersPO;
import com.catic.mobilehos.service.vo.Page;
import com.catic.mobilehos.service.vo.SysUsersVO;
import com.catic.mobilehos.service.vo.VOPOConverter;

/**
 * 系统用户
 * 
 * @author linchunda
 * 
 */
public class SysUsersServiceImpl implements SysUsersService {
	private SysUsersDAO sysUsersDAO;

	public SysUsersDAO getSysUsersDAO() {
		return sysUsersDAO;
	}

	public void setSysUsersDAO(SysUsersDAO sysUsersDAO) {
		this.sysUsersDAO = sysUsersDAO;
	}

	@Override
	public SysUsersPO getUser(int userId) {
		return sysUsersDAO.getUser(userId);
	}

	@Override
	public int createSysUsersPO(SysUsersPO sysUsersPO) {
		return sysUsersDAO.createSysUsersPO(sysUsersPO);
	}

	@Override
	public void updateSysUsersPO(SysUsersPO sysUsersPO, int userId) {
		sysUsersDAO.updateSysUsersPO(sysUsersPO, userId);
	}

	@Override
	public boolean existsUsername(String username) {
		return sysUsersDAO.existsUsername(username);
	}
	
	@Override
	public boolean deleteSysUser(int userId) {
		return sysUsersDAO.deleteSysUser(userId);
	}
	
	@Override
	public void addRoles(int userId, int[] role_ids) {
		sysUsersDAO.addRoles(userId, role_ids);
	}
	
	@Override
	public boolean removeRoles(int userId) {
		return sysUsersDAO.removeRoles(userId);
	}
	
	@Override
	public List<Integer> getRoles(int userId) {
		return sysUsersDAO.getRoles(userId);
	}

	@Override
	public Page<SysUsersVO> getSysUsersPOsbyParas(String realName, int status,
			int roleId, int curPage, int pageSize) {
		int totalRecord = sysUsersDAO.getCountSysUsersPO(realName, status,
				roleId);
		Page<SysUsersVO> page = new Page<SysUsersVO>(totalRecord, pageSize,
				curPage);
		List<SysUsersPO> list = sysUsersDAO.getSysUsersPOsbyParas(realName,
				status, roleId, page.getOffset(), page.getPageSize());
		VOPOConverter<SysUsersVO, SysUsersPO> converter = new VOPOConverter<SysUsersVO, SysUsersPO>(
				SysUsersVO.class, SysUsersPO.class);
		List<SysUsersVO> liUsersVOs = converter.fromPOList(list);
		page.setCurPageData(liUsersVOs);
		return page;
	}
	
	@Override
	public SysUsersPO findSysUsersPOByParas(String userName) {
		return sysUsersDAO.findSysUsersPOByParas(userName);
	}
	
	
	@Override
	public List<String> getAuthorityByUserId(int userId) {
		return sysUsersDAO.getAuthorityByUserId(userId);
	}

}
