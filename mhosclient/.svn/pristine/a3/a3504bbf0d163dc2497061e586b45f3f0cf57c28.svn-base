package com.catic.mobilehos.menu_role_authority.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.catic.mobilehos.menu_role_authority.dao.IRoleDao;
import com.catic.mobilehos.menu_role_authority.entity.Role;
import com.catic.mobilehos.pay.dao.BaseDao;
import com.catic.mobilehos.utils.Page;

@Repository("roleDao")
public class RoleDaoImpl extends BaseDao implements IRoleDao {

	@Override
	public boolean save(Role role) throws Exception {
		String sql = "INSERT INTO role(name, depict, createDate) VALUES(?, ?, NOW())";
		int count = jdbc.update(sql, new Object[] { role.getName(),
				role.getDepict()});
		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(int id) throws Exception {
		String sql = "DELETE FROM role WHERE roleId = ?";
		int count = jdbc.update(sql, id);
		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean modify(Role role) throws Exception {
		if (role != null) {
			Role role1 = findById(role.getRoleId());
			StringBuffer sql = new StringBuffer("UPDATE role SET roleId = "
					+ role.getRoleId() + "");
			/*if (role.getCategory()!=null && !role1.getCategory().equals(role.getCategory())) {
				sql.append(", category = '" + role.getCategory() + "'");
			}*/
			if (StringUtils.isNotBlank(role.getName())
					&& !role1.getName().equals(role.getName())) {
				sql.append(", name = '" + role.getName() + "'");
			}
			if (StringUtils.isNotBlank(role.getDepict())
					&& !role1.getDepict().equals(role.getDepict())) {
				sql.append(", depict = '" + role.getDepict() + "'");
			}
			sql.append(", createDate = NOW()");
			sql.append(" WHERE roleId = " + role.getRoleId() + "");
			int count = jdbc.update(sql.toString());
			if (count == 1) {
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Role findById(int id) throws Exception {
		String sql = "SELECT * FROM role WHERE roleId = ?";
		List<Role> list = jdbc.query(sql, new Object[] { id },
				new BeanPropertyRowMapper(Role.class));
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> findAll(Page page) throws Exception {
		String sql = "SELECT * FROM role ORDER BY createDate DESC";
		StringBuffer sb = new StringBuffer(sql);
		List<Object> params = new ArrayList<Object>();
		if (page != null) {
			sb.append(" LIMIT ?, ?");
			params.add(page.getFirstIndex());
			params.add(page.getPageSize());
		}
		List<Role> list = jdbc.query(sb.toString(), params.toArray(),
				new BeanPropertyRowMapper(Role.class));
		return list;
	}

	@Override
	public Integer findMaxId() throws Exception {
		String sql = "SELECT max(roleId) FROM role";
		Integer maxId = jdbc.queryForInt(sql);
		return maxId;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Role findByName(String name) throws Exception {
		String sql = "SELECT * FROM role WHERE name = ?";
		name = name.replaceAll("\\s*", "");
		List<Role> list = jdbc.query(sql, new Object[] { name },
				new BeanPropertyRowMapper(Role.class));
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> findByMrid(Integer id) throws Exception {
		String sql = "SELECT r.* FROM role r INNER JOIN popedom p ON r.roleId = p.roleId WHERE p.mrid = ?";
		List<Role> list = jdbc.query(sql, new Object[] { id },
				new BeanPropertyRowMapper(Role.class));
		return list;
	}
}
