package com.catic.mobilehos.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.catic.mobilehos.dao.HtmlImagesDAO;
import com.catic.mobilehos.po.HtmlImagesPO;

public class HtmlImagesDAOImpl extends JdbcDaoSupport implements HtmlImagesDAO {
	
	private Log log = LogFactory.getLog(this.getClass());

	@Override
	public void addHtmlImages(final HtmlImagesPO htmlImages) {
		
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "INSERT INTO html_images(info_id, image, filename"
						 + ", url, create_date) values(?,?,?,?,?)";
			jt.update(sql, new PreparedStatementSetter(){
				@Override
				public void setValues(PreparedStatement arg0)
						throws SQLException {
					arg0.setString(1, htmlImages.getInfoId());
					arg0.setBytes(2, htmlImages.getImage());
					arg0.setString(3, htmlImages.getFilename());
					arg0.setString(4, htmlImages.getUrl());
					arg0.setTimestamp(5, htmlImages.getCreateDate());
				}
			});
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}

	}
	
	
	@Override
	public void deleteHtmlImages(String infoid) {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "delete from html_images where info_id = ?";
			jt.update(sql, infoid);
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}
	}


	public List<HtmlImagesPO> findHtmlImagesByInfoId(String id){
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "select * from html_images where info_id=?";
			List<HtmlImagesPO> imgs = jt.query(sql, new HtmlImagesPO(), id);
			return imgs;
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}

		
	}

}
