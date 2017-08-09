package com.catic.mobilehos.menu_role_authority.biz.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.catic.mobilehos.menu_role_authority.biz.IRoleMenuBiz;
import com.catic.mobilehos.menu_role_authority.entity.RoleMenu;
import com.catic.mobilehos.pay.biz.BaseBiz;

@Service("roleMenuBiz")
public class RoleMenuBizImpl extends BaseBiz implements IRoleMenuBiz {

	@Override
	public boolean save(RoleMenu roleMenu) throws Exception {
		return roleMenuDao.save(roleMenu);
	}
	
	@Override
	public boolean delete(int id) throws Exception {
		return roleMenuDao.delete(id);
	}

	@Override
	public List<RoleMenu> findAll(int[] ids) throws Exception {
		return roleMenuDao.findAll(ids);
	}
	
}
