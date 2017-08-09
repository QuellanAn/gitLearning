package com.catic.mobilehos.dao.jdbc;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.catic.mobilehos.dao.InfoCatDAO;
import com.catic.mobilehos.po.InfoCatPO;

public class InfoCatDAOImpl extends JdbcDaoSupport implements InfoCatDAO {
	
	private Log log = LogFactory.getLog(this.getClass());

	@Override
	public List<InfoCatPO> findInfoCatByType(int infoType) {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			List<InfoCatPO> lst;
			if (infoType > 0){
				String sql = "select * from info_cat where info_type = ?";
				lst = jt.query(sql, new InfoCatPO(), infoType);
			}else{
				String sql = "select * from info_cat";
				lst = jt.query(sql, new InfoCatPO());
			}
			return lst;
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}
	}
	
	@Override
	public List<InfoCatPO> getInfoCat() {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "select * from info_cat";
			List<InfoCatPO> infoList=jt.query(sql, new InfoCatPO());
			return infoList;
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}
	}

	@Override
	public void deleteInfoCat(String infoCatCode) {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "delete from info_cat where info_cat_code = ?";
			jt.update(sql, infoCatCode);
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}
	}

	@Override
	public void updateInfoCatName(String infoCatCode, String infoCatName) {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "update info_cat set info_cat_name = ? where info_cat_code = ?";
			jt.update(sql, infoCatName, infoCatCode);
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}

		
	}

	@Override
	public void addInfoCat(InfoCatPO infoCat) {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "insert into info_cat(info_cat_name, info_type) values(?,?)";
			jt.update(sql, infoCat.getInfoCatName(), infoCat.getInfoType());
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}
		
	}

	@Override
	public boolean existsInfoCat(String infoCatName) {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "select count(*) from info_cat where info_cat_name = ?";
			int count = jt.queryForObject(sql, Integer.class, infoCatName);
			return count > 0;
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}
	}
	
	
	
	
	
	
	
	
	

}
