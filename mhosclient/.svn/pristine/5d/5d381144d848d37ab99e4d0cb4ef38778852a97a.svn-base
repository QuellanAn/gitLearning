package com.catic.mobilehos.menu_role_authority.biz.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.catic.mobilehos.menu_role_authority.biz.IRoleBiz;
import com.catic.mobilehos.menu_role_authority.entity.Role;
import com.catic.mobilehos.menu_role_authority.entity.RoleMenu;
import com.catic.mobilehos.pay.biz.BaseBiz;
import com.catic.mobilehos.utils.Page;

@Service("roleBiz")
public class RoleBizImpl extends BaseBiz implements IRoleBiz {

	@Override
	public boolean save(Role role) throws Exception {
		boolean flag = roleDao.save(role);
		if(flag){
			Integer roleId = roleDao.findMaxId();
			String[] arr =  role.getIds().split(",");
			for (int i = 0; i < arr.length; i++) {
				RoleMenu roleMenu = new RoleMenu(roleId, Integer.parseInt(arr[i]));
				roleMenuDao.save(roleMenu);
			}
		}
		return flag;
	}
	
	@Override
	public boolean delete(int id) throws Exception {
		boolean flag = roleMenuDao.delete(id);
		if(flag){
			flag = roleDao.delete(id);
		}
		return flag;
	}

	@Override
	public boolean modify(Role role) throws Exception {
		boolean flag = roleDao.modify(role);
		flag = roleMenuDao.delete(role.getRoleId());
		if(flag){
			String[] arr =  role.getIds().split(",");
			for (int i = 0; i < arr.length; i++) {
				RoleMenu roleMenu = new RoleMenu(role.getRoleId(), Integer.parseInt(arr[i]));
				roleMenuDao.save(roleMenu);
			}
		}
		return flag;
	}
	
	@Override
	public Role findById(int id) throws Exception {
		return roleDao.findById(id);
	}
	
	@Override
	public List<Role> findAll(Page page) throws Exception {
		return roleDao.findAll(page);
	}

	@Override
	public Role findByName(String name) throws Exception {
		return roleDao.findByName(name);
	}

	@Override
	public List<Role> findByMrid(Integer id) throws Exception {
		return roleDao.findByMrid(id);
	}

}
