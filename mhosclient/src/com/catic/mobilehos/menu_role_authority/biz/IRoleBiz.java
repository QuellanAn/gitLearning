package com.catic.mobilehos.menu_role_authority.biz;

import java.util.List;

import com.catic.mobilehos.menu_role_authority.entity.Role;
import com.catic.mobilehos.utils.Page;

public interface IRoleBiz {

	public boolean save(Role role) throws Exception;
	
	public boolean delete(int id) throws Exception;
	
	public boolean modify(Role role) throws Exception;
	
	public Role findById(int id) throws Exception;
	
	public List<Role> findAll(Page page) throws Exception;
	
	public Role findByName(String name) throws Exception;
	
	public List<Role> findByMrid(Integer id) throws Exception;
}
