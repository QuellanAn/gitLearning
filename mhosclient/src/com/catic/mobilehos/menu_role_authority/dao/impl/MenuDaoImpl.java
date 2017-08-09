package com.catic.mobilehos.menu_role_authority.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.catic.mobilehos.menu_role_authority.dao.IMenuDao;
import com.catic.mobilehos.menu_role_authority.entity.Menu;
import com.catic.mobilehos.pay.dao.BaseDao;

@Repository("menuDao")
public class MenuDaoImpl extends BaseDao implements IMenuDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> findAll() throws Exception {
		String sql = "SELECT * FROM menu WHERE status = 0 order by code";
		List<Menu> list = jdbc.query(sql, new BeanPropertyRowMapper(Menu.class));
		return list;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> findMenu(int id) throws Exception {
		String sql = "SELECT mu.* from popedom p INNER JOIN role r ON p.roleId = r.roleId INNER JOIN rolemenu rm ON r.roleId = rm.roleId INNER JOIN menu mu ON rm.menuId = mu.menuId  WHERE p.mrid = ? and status = 0  GROUP BY mu.menuId order by mu.code";
		List<Menu> list = jdbc.query(sql, new Object[]{id}, new BeanPropertyRowMapper(Menu.class));
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> findParentMenu(int id) throws Exception {
		String sql = "SELECT mu2.* from popedom p INNER JOIN role r ON p.roleId = r.roleId INNER JOIN rolemenu rm ON r.roleId = rm.roleId INNER JOIN menu mu1 ON rm.menuId = mu1.menuId INNER JOIN menu mu2 ON mu1.parentId = mu2.menuId  WHERE p.mrid = ?  GROUP BY mu1.parentId order by mu2.code";
		List<Menu> list = jdbc.query(sql, new Object[]{id}, new BeanPropertyRowMapper(Menu.class));
		return list;
	}


}
