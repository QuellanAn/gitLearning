package com.catic.mobilehos.dao.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.catic.mobilehos.dao.SpecialistDao;
import com.catic.mobilehos.po.Specialist;
import com.catic.mobilehos.utils.Page;

public class SpecialistDaoImpl extends JdbcDaoSupport implements SpecialistDao {

	@Override
	public boolean save(Specialist specialist) throws Exception {
		String sql = "INSERT INTO specialist(code, name, department, type, photo, adept, depict, status, createTime) VALUES(?, ?, ?, ?, ?, ?, ?, 0, NOW())";
		int count = getJdbcTemplate().update(sql, new Object[]{specialist.getCode(), specialist.getName(), specialist.getDepartment(), 
				specialist.getType(), specialist.getPhoto(), specialist.getAdept(), specialist.getDepict()});
		if(count > 0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delete(String id) throws Exception {
		String sql = "DELETE FROM specialist WHERE id = ?";
		int count = getJdbcTemplate().update(sql, id);
		if(count > 0){
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Specialist> findAll(Specialist specialist, Page page) throws Exception {
		String sql;
		if(page != null){
			sql = "SELECT * FROM specialist WHERE status = 0 ";
		}else{
			sql = "SELECT COUNT(0) AS count FROM specialist WHERE status = 0 ";
		}
		if(StringUtils.isNotBlank(specialist.getName())){
			String name = specialist.getName().replaceAll("\\s*", "");
			sql += " AND name LIKE '%"+ name +"%'";
		}
		if(page != null){
			sql += " ORDER BY createTime DESC LIMIT "+ page.getFirstIndex() + ", " + page.getPageSize();
		}
		
		List<Specialist> list = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Specialist.class));
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Specialist> findByCode(String code) throws Exception {
		String sql = "SELECT * FROM specialist WHERE code = ? AND status = 0";
		List<Specialist> list = getJdbcTemplate().query(sql, new Object[]{code}, new BeanPropertyRowMapper(Specialist.class));
		return list;
	}

	@Override
	public boolean modify(Specialist specialist) throws Exception {
		List<Object> list = new ArrayList<Object>();
		String sql = "UPDATE specialist SET id = " + specialist.getId();
		if(StringUtils.isNotBlank(specialist.getCode())){
			sql += ", code = '" + specialist.getCode() + "'";
		}
		if(StringUtils.isNotBlank(specialist.getName())){
			sql += ", name = '" + specialist.getName() + "'";
		}
		if(StringUtils.isNotBlank(specialist.getDepartment())){
			sql += ", department = '" + specialist.getDepartment() + "'";
		}
		if(StringUtils.isNotBlank(specialist.getType())){
			sql += ", type = '" + specialist.getType() + "'";
		}
		if(specialist.getPhoto() != null){
			sql += ", photo = ?";
			list.add(specialist.getPhoto());
		}
		if(StringUtils.isNotBlank(specialist.getAdept())){
			sql += ", adept = '" + specialist.getAdept() + "'";
		}
		if(StringUtils.isNotBlank(specialist.getDepict())){
			sql += ", depict = '" + specialist.getDepict() + "'";
		}
		sql += ", createTime = NOW()";
		sql += " WHERE id = " + specialist.getId();
		int count = getJdbcTemplate().update(sql, list.toArray());
		if(count == 1){
			return true;
		}
		return false;
	}

}
