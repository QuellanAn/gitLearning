package com.catic.mobilehos.dao.jdbc;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.catic.mobilehos.dao.OpenFireGroupDAO;
import com.catic.mobilehos.po.OpenFireGroupPO;
import com.catic.mobilehos.po.OpenFireGroupPropPO;

/**
 * 即时通信 openfire 用户组
 * 
 * @author linchunda
 * 
 */
public class OpenFireGroupDAOImpl extends JdbcDaoSupport implements
		OpenFireGroupDAO {

	@Override
	public boolean addGroup(OpenFireGroupPO openFireGroupPO) {
		String sql = "insert into ofgroup(groupName,description) values(?,?)";
		int rows = getJdbcTemplate().update(sql,
				openFireGroupPO.getGroupName(),
				openFireGroupPO.getDescription());
		return rows > 0;
	}

	@Override
	public boolean addGroupProp(OpenFireGroupPropPO openFireGroupPropPO) {
		String sql = "insert into ofgroupprop(groupName,name,propValue) values(?,?,?)";
		int rows = getJdbcTemplate().update(sql,
				openFireGroupPropPO.getGroupName(),
				openFireGroupPropPO.getName(),
				openFireGroupPropPO.getPropValue());
		return rows > 0;
	}

	@Override
	public boolean haveGroupName(String groupName) {
		String sql = "select * from ofgroup where groupName=?";
		List<OpenFireGroupPO> list = getJdbcTemplate().query(sql,
				new OpenFireGroupPO(), groupName);
		return null != list && !list.isEmpty();
	}

}
