package com.catic.mobilehos.menu_role_authority.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.catic.mobilehos.menu_role_authority.dao.IRoleMenuDao;
import com.catic.mobilehos.menu_role_authority.entity.RoleMenu;
import com.catic.mobilehos.pay.dao.BaseDao;

@Repository("roleMenuDao")
public class RoleMenuDaoImpl extends BaseDao implements IRoleMenuDao {

	@Override
	public boolean save(RoleMenu roleMenu) throws Exception {
		String sql = "INSERT INTO roleMenu(roleId, menuId) VALUES(?, ?)";
		int count = jdbc.update(sql, new Object[]{roleMenu.getRoleId(), roleMenu.getMenuId()});
		if(count > 0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delete(int id) throws Exception {
		String sql = "DELETE FROM rolemenu WHERE roleId = ?";
		int count = jdbc.update(sql, id);
		if(count > 0){
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoleMenu> findAll(int[] ids) throws Exception {
		String sql = "SELECT rm.* FROM roleMenu rm INNER JOIN menu m ON rm.menuId = m.menuId ";
		StringBuffer sb = new StringBuffer(sql);
		List<Object> params = new ArrayList<Object>();
		if(ids.length > 0){
			sb.append(" WHERE roleId IN (");
			for (int i = 0; i < ids.length; i++) {
				sb.append("?,");
				params.add(ids[i]);
			}
			sb = new StringBuffer(sb.substring(0,sb.length()-1));
			sb.append(")");
		}
		List<RoleMenu> list = jdbc.query(sb.toString(), params.toArray(), new BeanPropertyRowMapper(RoleMenu.class));
		return list;
	}

}
