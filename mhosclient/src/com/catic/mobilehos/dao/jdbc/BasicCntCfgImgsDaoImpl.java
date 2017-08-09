package com.catic.mobilehos.dao.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobCreator;

import com.catic.mobilehos.dao.BasicCntCfgImgsDao;
import com.catic.mobilehos.po.BasicCntCfgImgsPO;

/**
 * 基础内容网页图片
 * 
 * @author linchunda
 * 
 */
public class BasicCntCfgImgsDaoImpl extends JdbcDaoSupport implements
		BasicCntCfgImgsDao {
	private Log log = LogFactory.getLog(this.getClass());

	@Override
	public List<BasicCntCfgImgsPO> getBasicCntCfgImgs(int cfg_type, int cat) {
		List<BasicCntCfgImgsPO> list = null;
		try {
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "select * from basic_cnt_cfg_imgs where cfg_type=? and cat=?";
			list = jt.query(sql, new BasicCntCfgImgsPO(), cfg_type, cat);
			if (null == list) {
				list = new ArrayList<BasicCntCfgImgsPO>();
			}
			return list;
		} catch (DataAccessException ex) {
			log.error(null, ex);
			throw ex;
		}
	}

	@Override
	public void saveBasiCntCfgImgs(final BasicCntCfgImgsPO cntCfgImgsPO,final File file) {
		try {
			String sql = "insert into  basic_cnt_cfg_imgs(cfg_type,cat,image_content,url,filename) values(?,?,?,?,?)";
			JdbcTemplate jt = this.getJdbcTemplate();
			
			jt.execute(sql, new AbstractLobCreatingPreparedStatementCallback(new DefaultLobHandler()) {
				@Override
				protected void setValues(PreparedStatement preparedStatement, LobCreator lobCreator)
						throws SQLException, DataAccessException {
					preparedStatement.setInt(1, cntCfgImgsPO.getCfg_type());
					preparedStatement.setInt(2, cntCfgImgsPO.getCat());
					if(null != file){
						try {
							lobCreator.setBlobAsBinaryStream(preparedStatement, 3, new FileInputStream(file), (int)file.length());
						} catch (FileNotFoundException ex) {
							log.error("保存图片文件出错:", ex);
						}
					}
					preparedStatement.setString(4, cntCfgImgsPO.getUrl());
					preparedStatement.setString(5, cntCfgImgsPO.getFilename());
				}
			});
		} catch (DataAccessException ex) {
			log.error(null, ex);
			throw ex;
		}
	}

	@Override
	public boolean UpdateBasiCntCfgImgs(final BasicCntCfgImgsPO cntCfgImgsPO,
			final String filename) {
		try {
			JdbcTemplate jt = this.getJdbcTemplate();

			String sql = "update basic_cnt_cfg_imgs set cfg_type=?,cat=?,url=?,filename=? where filename=?";
			int row = jt.update(sql,new PreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement preparedStatement)
						throws SQLException {
					preparedStatement.setInt(1, cntCfgImgsPO.getCfg_type());
					preparedStatement.setInt(2, cntCfgImgsPO.getCat());
					preparedStatement.setString(3, cntCfgImgsPO.getUrl());
					preparedStatement.setString(4, cntCfgImgsPO.getFilename());
					preparedStatement.setString(5, filename);
				}
			});
			return row > 0;
		} catch (DataAccessException ex) {
			log.error(null, ex);
			throw ex;
		}
	}
}
