package com.catic.mobilehos.menu_role_authority.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.catic.mobilehos.menu_role_authority.dao.IPopedomDao;
import com.catic.mobilehos.menu_role_authority.entity.Popedom;
import com.catic.mobilehos.pay.dao.BaseDao;

@Repository("popedomDao")
public class PopedomDaoImpl extends BaseDao implements IPopedomDao {

	@Override
	public boolean save(Popedom popedom) throws Exception {
		String sql = "INSERT INTO popedom(mrid, roleId) VALUES(?, ?)";
		int count = jdbc.update(sql, new Object[]{popedom.getMrid(), popedom.getRoleId()});
		if(count > 0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delete(int id) throws Exception {
		String sql = "DELETE FROM popedom WHERE mrid = ?";
		int count = jdbc.update(sql, id);
		if(count > 0){
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> findAll(Integer id) throws Exception {
		String sql = "SELECT roleId FROM popedom WHERE mrid = ?";
		List list = jdbc.queryForList(sql, new Object[]{id});
		List<String> newList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			Map map = (Map) list.get(i);
			newList.add(map.get("roleId").toString());
		}
		return newList;
	}

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * 根据用户编号查询用户与角色关系数据
	 */
	public List<Popedom> findById(Integer id) throws Exception {
		String sql = "SELECT * FROM popedom where mrid = ?";
		List<Popedom> list = jdbc.query(sql, new Object[]{id},new BeanPropertyRowMapper(Popedom.class));
		return list;
	}
}
