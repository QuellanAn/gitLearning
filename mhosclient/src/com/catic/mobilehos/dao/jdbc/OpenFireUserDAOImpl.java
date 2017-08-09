package com.catic.mobilehos.dao.jdbc;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.catic.mobilehos.dao.OpenFireUserDAO;
import com.catic.mobilehos.po.OpenFireUserPO;
import com.catic.mobilehos.utils.Blowfish;

/**
 * 及时通信OpenFire用户
 * 
 * @author linchunda
 * 
 */
public class OpenFireUserDAOImpl extends JdbcDaoSupport implements
		OpenFireUserDAO {
	private Log log = LogFactory.getLog(this.getClass());
	
	
	@Override
	public String getPasswordKey() {
		String sql = "select propValue from ofproperty where name='passwordKey'";
		Map<String, Object> map = getJdbcTemplate().queryForMap(sql);
		return (String) map.get("propValue");
	}

	@Override
	public OpenFireUserPO findByUsername(String username) {
		String sql = "select * from ofuser where username=?";
		List<OpenFireUserPO> list = getJdbcTemplate().query(sql,
				new OpenFireUserPO(), username);
		if (null == list || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public boolean addOpenFireUserPO(OpenFireUserPO openFireUserPO) {
		String sql = "insert into ofuser(username,plainPassword,"
				+ "encryptedPassword,name,email,creationDate,modificationDate) "
				+ "values(?,?,?,?,?,?,?)";
		// 对密码进行加密
		Blowfish blowfish = new Blowfish(getPasswordKey());
		String encryptedPassword = blowfish.encryptString(openFireUserPO
				.getEncryptedPassword());

		int rows = getJdbcTemplate().update(sql, openFireUserPO.getUsername(),
				openFireUserPO.getPlainPassword(), encryptedPassword,
				openFireUserPO.getName(), openFireUserPO.getEmail(),
				openFireUserPO.getCreationDate(),
				openFireUserPO.getModificationDate());
		return rows > 0;
	}

	@Override
	public boolean updateOpenFireUserPO(String username,
			OpenFireUserPO openFireUserPO) {
		String sql = "update ofuser set plainPassword=?,"
				+ "encryptedPassword=?,name=?,email=?,creationDate=?,modificationDate=? "
				+ "where username=?";
		int rows = getJdbcTemplate().update(sql,
				openFireUserPO.getPlainPassword(),
				openFireUserPO.getEncryptedPassword(),
				openFireUserPO.getName(), openFireUserPO.getEmail(),
				openFireUserPO.getCreationDate(),
				openFireUserPO.getModificationDate(), username);
		return rows > 0;
	}

	@Override
	public boolean addUserToGroup(String groupName, String username) {
		String sql = "insert into ofgroupuser(groupName,username,administrator) values(?,?,?)";
		int rows = getJdbcTemplate().update(sql, groupName, username, 0);
		return rows > 0;
	}

	@Override
	public boolean deleteOpenFireUserPO(String username) {
		String sql = "delete gu.*,u.* from ofgroupuser gu,ofuser u where gu.username=u.username and u.username=?";
		int rows = getJdbcTemplate().update(sql, username);
		return rows > 0;
	}

	@SuppressWarnings("deprecation")
	@Override
	public int getCountOpenFireUserPO(String groupName, String username) {
		String sql = null;
		int count = 0;
		if (StringUtils.isEmpty(username)) {
			sql = "select count(*) from ofgroupuser gu,ofuser u where gu.username=u.username and gu.groupName=?";
			count = getJdbcTemplate().queryForInt(sql, groupName);
		} else {
			sql = "select count(*) from ofgroupuser gu,ofuser u where gu.username=u.username and gu.groupName=? and u.username like ?";
			count = getJdbcTemplate().queryForInt(sql, groupName,
					"%" + username + "%");
		}
		log.info(sql);
		log.info(count);
		return count;
	}

	@Override
	public List<OpenFireUserPO> findOpenFireUserPOByByParas(String groupName,
			String username, int offset, int length) {
		String sql = "";
		if (StringUtils.isEmpty(username)) {
			log.info(groupName);
			sql = "select u.* from ofgroupuser gu,ofuser u where gu.username=u.username and gu.groupName=? order by u.modificationDate desc limit ?,?";
			return getJdbcTemplate().query(sql, new OpenFireUserPO(),
					groupName, offset, length);
		}

		sql = "select u.* from ofgroupuser gu,ofuser u where gu.username=u.username and gu.groupName=? and u.username like ? order by u.modificationDate desc limit ?,?";

		return getJdbcTemplate().query(sql, new OpenFireUserPO(), groupName,"%" + username + "%", offset, length);
	}

}
