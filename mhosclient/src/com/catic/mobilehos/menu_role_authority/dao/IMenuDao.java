package com.catic.mobilehos.menu_role_authority.dao;

import java.util.List;

import com.catic.mobilehos.menu_role_authority.entity.Menu;

public interface IMenuDao {

	public List<Menu> findAll() throws Exception;
	
	public List<Menu> findParentMenu(int id) throws Exception;
	
	public List<Menu> findMenu(int id) throws Exception;
}
