package com.catic.mobilehos.menu_role_authority.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.catic.mobilehos.menu_role_authority.dao.IManagerDao;
import com.catic.mobilehos.menu_role_authority.entity.Manager;
import com.catic.mobilehos.pay.dao.BaseDao;
import com.catic.mobilehos.utils.Page;

@Repository("managerDao")
public class ManagerDaoImpl extends BaseDao implements IManagerDao {
	
	@Override
	public boolean save(Manager manager) throws Exception {
		String sql = "INSERT INTO sysusers(userName, password, realName, sex, tel, department, status, remark, createTime, updateTime) VALUES(?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";
		int count = jdbc.update(sql, new Object[]{manager.getUserName(), manager.getPassword(), manager.getRealName(), 
				manager.getSex(), manager.getTel(), manager.getDepartment(), manager.getStatus(), manager.getRemark()});
		if(count > 0){
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Manager findManager(Manager manager) throws Exception {
		String sql = "SELECT s.*,p.roleId as roleId FROM sysusers s LEFT JOIN popedom p ON s.userId = p.mrid  WHERE s.userName = ? AND s.password = ? AND s.status = 0";
		List<Manager> list = jdbc.query(sql, new Object[]{manager.getUserName(), manager.getPassword()},
					new BeanPropertyRowMapper(Manager.class));
		if(list.size() > 0){
			return list.get(0);
		}
		
		return null;
	}
	
	@Override
	public boolean findPhoneByType(Manager manager) throws Exception {
		String sql = "SELECT COUNT(0) FROM engineer WHERE account = ? AND status = 0";
		int count = jdbc.queryForInt(sql, manager.getUserName());
		if(count == 0){
			return true;
		}
		return false;
	}

	@Override
	public boolean modify(Manager manager) throws Exception {
		if(manager != null){
			Manager manager1 = findById(manager.getUserId());
			StringBuffer sql = new StringBuffer("UPDATE sysusers SET userId = " + manager.getUserId() + "");
			if(StringUtils.isNotBlank(manager.getUserName()) && !manager1.getUserName().equals(manager.getUserName())){
				sql.append(", userName = '" + manager.getUserName() + "'");
			}
			if(StringUtils.isNotBlank(manager.getTel()) && !manager1.getTel().equals(manager.getTel())){
				sql.append(", tel = '" + manager.getTel() + "'");
			}
			if(StringUtils.isNotBlank(manager.getPassword()) && !manager1.getPassword().equals(manager.getPassword())){
				sql.append(", password = '" + manager.getPassword() + "'");
			}
			if(StringUtils.isNotBlank(manager.getDepartment()) && !manager1.getDepartment().equals(manager.getDepartment())){
				sql.append(", department = '" + manager.getDepartment() + "'");
			}
			if(StringUtils.isNotBlank(manager.getRealName()) && !manager1.getRealName().equals(manager.getRealName())){
				sql.append(", realName = '" + manager.getRealName() + "'");
			}
			if(manager.getSex() != null && !manager1.getSex().equals(manager.getSex())){
				sql.append(", sex = " + manager.getSex() + "");
			}
			if(manager.getStatus() != null){
				sql.append(", status = " + manager.getStatus() + "");
			}
			if(manager.getUpdateTime() != null){
				sql.append(", updateTime = NOW()");
			}
			sql.append(" WHERE userId = " + manager.getUserId() + "");
			int count = jdbc.update(sql.toString());
			if(count == 1){
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Manager findById(Integer id) throws Exception {
		String sql = "SELECT s.* FROM sysusers s WHERE s.userId = ?";
		List<Manager> list = jdbc.query(sql, new Object[]{id}, new BeanPropertyRowMapper(Manager.class));
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Manager> findAll(String id, Page page) throws Exception {
		String sql = "SELECT s.*, p.roleId FROM sysusers s LEFT JOIN popedom p ON s.userId = p.mrid ";
		StringBuffer sb = new StringBuffer(sql);
		List<Object> params = new ArrayList<Object>();
		if(StringUtils.isNotBlank(id)){
			sb.append(" WHERE s.status = 0 AND p.roleId = ? ");
			params.add(id);
		}else{
			sb.append(" GROUP BY s.userId HAVING count(s.userId) > 0 AND s.status = 0 ");
		}
		if(page != null){
			sb.append(" ORDER BY s.createTime DESC LIMIT ?, ?");
			params.add(page.getFirstIndex());
			params.add(page.getPageSize());
		}
		List<Manager> list = jdbc.query(sb.toString(), params.toArray(), new BeanPropertyRowMapper(Manager.class));
		return list;
	}
	
	@Override
	public Integer findMaxId() throws Exception {
		String sql = "SELECT max(userId) FROM sysusers";
		Integer maxId = jdbc.queryForInt(sql);
		return maxId;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Manager findByValid(String account, String phone) throws Exception {
		String sql = "SELECT * FROM sysusers WHERE status = 0 ";
		StringBuffer sb = new StringBuffer(sql);
		List<Object> params = new ArrayList<Object>();
		if(StringUtils.isNotBlank(account)){
			account = account.replaceAll("\\s*", "");
			sb.append(" AND userName = ?");
			params.add(account);
		}
		if(StringUtils.isNotBlank(phone)){
			phone = phone.replaceAll("\\s*", "");
			sb.append(" AND tel = ?");
			params.add(phone);
		}
		List<Manager> list = jdbc.query(sb.toString(), params.toArray(), new BeanPropertyRowMapper(Manager.class));
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Manager findByCyid(String cyid) throws Exception {
		String sql = "SELECT * FROM managers WHERE cyid = ?";
		List<Manager> list = jdbc.query(sql, new Object[]{cyid}, new BeanPropertyRowMapper(Manager.class));
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
}
