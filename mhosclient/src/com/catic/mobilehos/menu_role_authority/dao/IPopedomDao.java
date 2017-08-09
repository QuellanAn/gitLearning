package com.catic.mobilehos.menu_role_authority.dao;

import java.util.List;

import com.catic.mobilehos.menu_role_authority.entity.Popedom;

public interface IPopedomDao {

	public boolean save(Popedom popedom) throws Exception;
	
	public boolean delete(int id) throws Exception;
	
	public List<String> findAll(Integer id) throws Exception;
	
	public List<Popedom> findById(Integer id) throws Exception;
}
