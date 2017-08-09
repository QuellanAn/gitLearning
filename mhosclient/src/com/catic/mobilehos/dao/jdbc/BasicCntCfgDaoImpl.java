package com.catic.mobilehos.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.catic.mobilehos.dao.BasicCntCfgDao;
import com.catic.mobilehos.po.BasicCntCfgPO;
import com.catic.mobilehos.po.HosPubInfoPO;

/**
 * 基础内容配置
 * 
 * @author linchunda
 * 
 */
public class BasicCntCfgDaoImpl extends JdbcDaoSupport implements
		BasicCntCfgDao {
	private Log log = LogFactory.getLog(this.getClass());

	@Override
	public BasicCntCfgPO getBasicCntCfgPO(int cfg_type, int cat) {
		try {
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "select * from basic_cnt_cfg where cfg_type=? and cat=?";

			List<BasicCntCfgPO> basicCntCfgPOs = jt.query(sql,
					new BasicCntCfgPO(), cfg_type, cat);
			if (null != basicCntCfgPOs && basicCntCfgPOs.size() > 0) {
				return basicCntCfgPOs.get(0);
			} else {
				return null;
			}
		} catch (DataAccessException e) {
			log.error("基本内容配置cfg_type=" + cfg_type + ",cat=" + cat + "不存在记录", e);
			return null;
		}
	}

	@Override
	public int saveBasicCntCfg(final BasicCntCfgPO basicCntCfgPO) {
		String sql = "insert into basic_cnt_cfg(cfg_type,cat,cat_name,content,update_time) values(?,?,?,?,?)";
		return getJdbcTemplate().update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement preparedStatement)
					throws SQLException {
				preparedStatement.setInt(1, basicCntCfgPO.getCfg_type());
				preparedStatement.setInt(2, basicCntCfgPO.getCat());
				preparedStatement.setString(3, basicCntCfgPO.getCat_name());
				preparedStatement.setString(4, basicCntCfgPO.getContent());
				preparedStatement.setTimestamp(5,
						basicCntCfgPO.getUpdate_time());
			}
		});
	}

	@Override
	public void updateBasicCntCfg(BasicCntCfgPO basicCntCfgPO) {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "update basic_cnt_cfg set cat_name=?,icon=?,content=?,update_time=?,update_people=? where cfg_type=1 and cat=?";
			jt.update(sql,basicCntCfgPO.getCat_name()
					  , basicCntCfgPO.getIcon()
					  , basicCntCfgPO.getContent()
					  , basicCntCfgPO.getUpdate_time()
					  , basicCntCfgPO.getUpdatePeople()
					  , basicCntCfgPO.getCat());
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}
	}

	public int getTotalContent(String catName, String updatePeople,
			java.sql.Date startDate, java.sql.Date endDate) {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			List<Object> lstArgs = new ArrayList<Object>();
			String sql = "select count(*) from basic_cnt_cfg bcc where bcc.cfg_type=1 ";
			sql = addParasToSQL(sql, catName, updatePeople, startDate, endDate, lstArgs);
			int count = jt.queryForObject(sql, lstArgs.toArray(), Integer.class);
			return count;
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}
	}

	public List<BasicCntCfgPO> getAllContent(String catName,
			String updatePeople, java.sql.Date startDate, java.sql.Date endDate,int offset, int length) {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			List<Object> lstArgs = new ArrayList<Object>();
			String sql = "select * from basic_cnt_cfg bcc where bcc.cfg_type=1 ";
			sql = addParasToSQL(sql, catName, updatePeople, startDate, endDate, lstArgs);
			sql += " order by bcc.cat asc limit ?,?";
			lstArgs.add(offset);
			lstArgs.add(length);
			List<BasicCntCfgPO> lst = jt.query(sql, lstArgs.toArray(), new BasicCntCfgPO());
			return lst;
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}
	}
	
	private String addParasToSQL(String sql,String catName,String updatePeople, 
			java.sql.Date startDate,java.sql.Date endDate,List<Object> lstArgs){
		if (StringUtils.isNotBlank(catName)){
			sql += " and bcc.cat_name like ?";
			lstArgs.add("%"+catName+"%");
		}
		if (StringUtils.isNotBlank(updatePeople)){
			sql += " and bcc.update_people like ?";
			lstArgs.add("%"+updatePeople+"%");
		}
		if (startDate != null){
			sql += " and bcc.update_time >= ?";
			lstArgs.add(startDate);
		}
		if (endDate != null){
			sql += " and bcc.update_time <= ?";
			lstArgs.add(endDate);
		}
		return sql;
	}

	public void addBaseContent(BasicCntCfgPO basicCntCfgPO) {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			String select_sql="select max(cat) from basic_cnt_cfg where cfg_type=1 ";
			int cat=jt.queryForObject(select_sql, null, Integer.class);
			String sql = "INSERT INTO basic_cnt_cfg(cfg_type, cat, cat_name"
						 + ", content, icon, update_people, update_time)"
						 + "values(?,?,?,?,?,?,?)";
			jt.update(sql, basicCntCfgPO.getCfg_type(),cat+1,basicCntCfgPO.getCat_name()
					,basicCntCfgPO.getContent(),basicCntCfgPO.getIcon()
					,basicCntCfgPO.getUpdatePeople(),basicCntCfgPO.getUpdate_time());
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}
	}

	@Override
	public void deleteBaseContent(int cat) {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "delete from basic_cnt_cfg where cfg_type =1 and cat = ?";
			jt.update(sql, cat);
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}
	}
}
