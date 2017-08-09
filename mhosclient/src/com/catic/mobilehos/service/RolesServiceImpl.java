package com.catic.mobilehos.service;

import java.util.ArrayList;
import java.util.List;

import com.catic.mobilehos.dao.RolesDAO;
import com.catic.mobilehos.po.RolesPO;
import com.catic.mobilehos.service.vo.Page;
import com.catic.mobilehos.service.vo.RolesVO;
import com.catic.mobilehos.service.vo.VOPOConverter;

/**
 * 角色管理
 * 
 * @author linchunda
 * 
 */
public class RolesServiceImpl implements RolesService {
	private RolesDAO rolesDAO;

	public RolesDAO getRolesDAO() {
		return rolesDAO;
	}

	public void setRolesDAO(RolesDAO rolesDAO) {
		this.rolesDAO = rolesDAO;
	}

	@Override
	public RolesPO getRolesPOByroleId(int roleId) {
		return rolesDAO.getRolesPOByroleId(roleId);
	}

	@Override
	public int createRolesPO(RolesPO rolesPO) {
		try {
			return rolesDAO.createRolesPO(rolesPO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void updateRolesPO(RolesPO rolesPO, int roleId) {
		rolesDAO.updateRolesPO(rolesPO, roleId);

	}

	@Override
	public boolean deleteRolesPO(int roleId) {
		return rolesDAO.deleteRolesPO(roleId);
	}

	@Override
	public void addAuthority(int roleId, int[] au_ids) {
		rolesDAO.addAuthority(roleId, au_ids);
	}

	@Override
	public boolean removeAuthority(int roleId) {
		return rolesDAO.removeAuthority(roleId);
	}
	
	@Override
	public List<Integer> getAuthority(int roleId) {
		return rolesDAO.getAuthority(roleId);
	}

	@Override
	public Page<RolesVO> getRolesPOByParas(int curPage, int pageSize) {
		int totalRecord = rolesDAO.getCountRolesPO();
		Page<RolesVO> page = new Page<RolesVO>(totalRecord, pageSize, curPage);
		List<RolesPO> list = rolesDAO.findRolesPOByParas(page.getOffset(),
				page.getPageSize());

		VOPOConverter<RolesVO, RolesPO> converter = new VOPOConverter<RolesVO, RolesPO>(
				RolesVO.class, RolesPO.class);
		List<RolesVO> rolesVOs = converter.fromPOList(list);
		page.setCurPageData(rolesVOs);
		return page;
	}
	
	@Override
	public List<RolesVO> findAllRolesVO() {
		List<RolesPO> list =rolesDAO.findAllRolesPO();
		
		if(null == list || list.isEmpty()){
			return new ArrayList<RolesVO>();
		}
		
		VOPOConverter<RolesVO, RolesPO> converter = new VOPOConverter<RolesVO, RolesPO>(
				RolesVO.class, RolesPO.class);
		List<RolesVO> rolesVOs = converter.fromPOList(list);
		return rolesVOs;
	}

}
