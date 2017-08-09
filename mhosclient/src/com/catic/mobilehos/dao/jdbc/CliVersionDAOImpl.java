package com.catic.mobilehos.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.catic.mobilehos.dao.CliVersionDAO;
import com.catic.mobilehos.po.CliVersionPO;

/**
 * 版本发布
 * 
 * @author linchunda
 * 
 */
public class CliVersionDAOImpl extends JdbcDaoSupport implements CliVersionDAO {
	private Log log = LogFactory.getLog(this.getClass());

	@Override
	public CliVersionPO checkVersion(String ver_cat, String ver_type) {
		try {
			String sql = "select * from cli_version where ver_cat=? and ver_type=? order by versionNo desc";
			List<CliVersionPO> list = getJdbcTemplate().query(sql,
					new CliVersionPO(), ver_cat,
					"0".equalsIgnoreCase(ver_type.trim()) ? "大众版" : "医护版");
			if (null == list || list.isEmpty()) {
				return null;
			}

			//进行排序
			Collections.sort(list, new Comparator<CliVersionPO>() {
				@Override
				public int compare(CliVersionPO o1, CliVersionPO o2) {
					String versionNo1 = o1.getVersionNo();
					String versionNo2 = o2.getVersionNo();

					String[] no1 = versionNo1.split("\\.");
					String[] no2 = versionNo2.split("\\.");

					if (Integer.parseInt(no1[0]) < Integer.parseInt(no2[0])) {
						return 1;
					} else if (Integer.parseInt(no1[0]) > Integer
							.parseInt(no2[0])) {
						return -1;
					}

					if (Integer.parseInt(no1[1]) < Integer.parseInt(no2[1])) {
						return 1;
					} else if (Integer.parseInt(no1[1]) > Integer
							.parseInt(no2[1])) {
						return -1;
					}

					if (Integer.parseInt(no1[2]) < Integer.parseInt(no2[2])) {
						return 1;
					} else if (Integer.parseInt(no1[2]) > Integer
							.parseInt(no2[2])) {
						return -1;
					}
					return 0;
				}
			});//end sort
			return list.get(0);
		} catch (DataAccessException e) {
			log.error("检查客户端版本发布出错:", e);
			return null;
		}
	}

	@Override
	public CliVersionPO getCliVersionPOById(int ID) {
		return getJdbcTemplate().queryForObject(
				"select * from cli_version where ID=?", new CliVersionPO(), ID);
	}

	@Override
	public List<String> getVersionNo(String ver_cat, String ver_type) {
		String sql = "select versionNo from cli_version where ver_cat=? and ver_type=?";
		List<Map<String, Object>> list = getJdbcTemplate().queryForList(sql,
				ver_cat, ver_type);
		if (null == list || list.isEmpty()) {
			return new ArrayList<String>();
		} else {
			List<String> reseult = new ArrayList<String>();
			for (Map<String, Object> map : list) {
				reseult.add((String) map.get("versionNo"));
			}
			return reseult;
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public int getCountCliVersionPO(String ver_cat) {
		String sql = "";
		if (null == ver_cat || "".equals(ver_cat.trim())) {
			sql = "select count(*) from cli_version";
			return getJdbcTemplate().queryForInt(sql);
		}

		sql = "select count(*) from cli_version where ver_cat=?";
		return getJdbcTemplate().queryForInt(sql, ver_cat);
	}

	@Override
	public List<CliVersionPO> findCliVersionPOByParas(String ver_cat,
			int offset, int length) {
		String sql = "";
		if (null == ver_cat || "".equals(ver_cat.trim())) {
			sql = "select * from cli_version order by ID desc limit ? ,?";
			return getJdbcTemplate().query(sql, new CliVersionPO(), offset,
					length);
		}

		sql = "select * from cli_version where ver_cat=? order by ID desc limit ? ,?";

		return getJdbcTemplate().query(sql, new CliVersionPO(), ver_cat,
				offset, length);
	}

	@Override
	public boolean saveCliVersionPO(final CliVersionPO cliVersionPO) {
		String sql = "insert into cli_version(ver_cat,ver_type,versionNo,createDate"
				+ ",content,update_version,filesize,apkName,apkPath)"
				+ " values(?,?,?,?,?,?,?,?,?)";
		int rows = getJdbcTemplate().update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement preparedStatement)
					throws SQLException {
				preparedStatement.setString(1, cliVersionPO.getVer_cat());
				preparedStatement.setString(2, cliVersionPO.getVer_type());
				preparedStatement.setString(3, cliVersionPO.getVersionNo());
				preparedStatement.setTimestamp(4, cliVersionPO.getCreateDate());
				preparedStatement.setString(5, cliVersionPO.getContent());
				preparedStatement.setString(6, cliVersionPO.getUpdate_version());
				preparedStatement.setInt(7, cliVersionPO.getFilesize());
				preparedStatement.setString(8, cliVersionPO.getApkName());
				preparedStatement.setString(9, cliVersionPO.getApkPath());
			}
		});
		return rows > 0;
	}

	@Override
	public boolean updateCliVersionPO(final CliVersionPO cliVersionPO,
			final int ID) {
		String sql = "update cli_version set ver_cat=?,ver_type=?,versionNo=?,createDate=?"
				+ ",content=?,update_version=? where ID=?";

		int rows = getJdbcTemplate().update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement preparedStatement)
					throws SQLException {
				preparedStatement.setString(1, cliVersionPO.getVer_cat());
				preparedStatement.setString(2, cliVersionPO.getVer_type());
				preparedStatement.setString(3, cliVersionPO.getVersionNo());
				preparedStatement.setTimestamp(4, cliVersionPO.getCreateDate());
				preparedStatement.setString(5, cliVersionPO.getContent());
				preparedStatement.setString(6, cliVersionPO.getUpdate_version());
				preparedStatement.setInt(7, ID);

			}
		});
		return rows > 0;
	}

	@Override
	public boolean deleteCliVersionPO(int ID) {
		int rows = getJdbcTemplate().update(
				"delete from cli_version where ID=?", ID);
		return rows > 0;
	}

}
