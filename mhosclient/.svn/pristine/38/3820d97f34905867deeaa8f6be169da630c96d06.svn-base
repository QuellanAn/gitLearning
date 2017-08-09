package com.catic.mobilehos.dao.jdbc;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.catic.mobilehos.dao.WechatMenuDao;
import com.catic.mobilehos.po.DoctorNamePO;
import com.catic.mobilehos.service.vo.WechatMainMenuVO;

public class WechatMenuDAOImpl extends JdbcDaoSupport implements WechatMenuDao {

	private Log log = LogFactory.getLog(this.getClass());

	@Override
	public List<WechatMainMenuVO> getAllMenu() {
		// TODO Auto-generated method stub
		try {
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "SELECT * FROM wx_menu";
			List<WechatMainMenuVO> lst = jt.query(sql,BeanPropertyRowMapper.newInstance(WechatMainMenuVO.class));
			return lst;
		} catch (DataAccessException ex) {
			log.error(null, ex);
			throw ex;
		}
	}

	@Override
	public WechatMainMenuVO loadWechatMenu(String id) {
		// TODO Auto-generated method stub
		try {
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "SELECT id,parentId,`name`,type,`key`,url,holderId,sequence FROM wx_menu WHERE  id = ?";
			WechatMainMenuVO main = jt.queryForObject(sql, new WechatMainMenuVO(), id);
			return main;
		} catch (DataAccessException ex) {
			log.error(null, ex);
			throw ex;
		}
	}

	@Override
	public void updateWechatMenu(String id, String name, String type,
			String key, String view,String submenu) {
		// TODO Auto-generated method stub
		try {
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "update wx_menu set `name` = ?,type=?,`key`=?,url=? WHERE id =?";
			jt.update(sql, name, type, key, view, id);
		} catch (DataAccessException ex) {
			log.error(null, ex);
			throw ex;
		}
	}

	@Override
	public void deleWechatMenu(String id) {
		// TODO Auto-generated method stub
		try {
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "delete from wx_menu where id=?";
			jt.update(sql, id);
		} catch (DataAccessException ex) {
			log.error(null, ex);
			throw ex;
		}
	}

	@Override
	public List<WechatMainMenuVO> getmenuJson() {
		// TODO Auto-generated method stub
		try {
			JdbcTemplate jt = this.getJdbcTemplate();
			List<WechatMainMenuVO> lst;
			String sql = "SELECT id,parentId from wx_menu WHERE parentId ='0'";
			lst = jt.query(sql, new WechatMainMenuVO());
			return lst;
		} catch (DataAccessException ex) {
			log.error(null, ex);
			throw ex;
		}
	}


}
