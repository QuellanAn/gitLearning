/**   
 * @Title: OpenFireDoctorDaoImpl.java 
 * @Package com.catic.mobilehos.dao.jdbc 
 * @Description: TODO
 * @author htWang   
 * @date 2014-5-7 上午11:22:27 
 * 
 */ 
package com.catic.mobilehos.dao.jdbc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.catic.mobilehos.dao.OpenFireDoctorDao;

/**
 * @author WANG
 *
 */
public class OpenFireDoctorDaoImpl extends JdbcDaoSupport implements OpenFireDoctorDao{
	
	private Log log = LogFactory.getLog(this.getClass());
	
	/* (non-javadoc) 
	 * <p>Title: saveOfDoctor</p> 
	 * <p>Description: </p> 
	 * @param doctorName 
	 * @see com.catic.mobilehos.dao.OpenFireDoctorDao#saveOfDoctor(java.lang.String) 
	*/ 
	@Override
	public void saveOfDoctor(String doctorName) {
		try {
			String sql = "insert into ofgroupuser(groupName,username,administrator) values ('b4c9d6c7-4e8b-486c-afec-0f3833c21df5',?,0)";
			this.getJdbcTemplate().update(sql, doctorName);
		} catch (DataAccessException ex) {
			log.error(null,ex);
			throw ex;
		}
	}

}
