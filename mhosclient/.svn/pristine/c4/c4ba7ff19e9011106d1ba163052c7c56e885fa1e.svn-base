package com.catic.mobilehos.menu_role_authority.dao;

import java.util.List;

import com.catic.mobilehos.menu_role_authority.entity.Manager;
import com.catic.mobilehos.utils.Page;


public interface IManagerDao {
	
	public boolean save(Manager manager) throws Exception;
	
	/**
	 * 查询管理员信息
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public Manager findById(Integer id) throws Exception;

	/**
	 * 管理员登录
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public Manager findManager(Manager manager) throws Exception;
	
	public boolean modify(Manager manager) throws Exception;
	
	public boolean findPhoneByType(Manager manager) throws Exception;
	
	public List<Manager> findAll(String id, Page page) throws Exception;
	
	public Integer findMaxId() throws Exception;
	
	public Manager findByValid(String account, String phone) throws Exception;

	public Manager findByCyid(String cyid) throws Exception;

}
