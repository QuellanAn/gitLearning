
package com.catic.mobilehos.dao.jdbc;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.catic.mobilehos.dao.AppRegDoctorsDao;

/**   
 * @Title: AppRegDoctorsDaoImpl.java 
 * @Package com.catic.mobilehos.dao.jdbc 
 * @Description: TODO
 * @author htWang   
 * @date 2014-5-7 上午8:42:44 
 * 
 */ 
public class AppRegDoctorsDaoImpl extends JdbcDaoSupport implements AppRegDoctorsDao{

	/* (non-javadoc) 
	 * <p>Title: deleteAppRegDoctors</p> 
	 * <p>Description: </p> 
	 * @param doctorId
	 * @return 
	 * @see com.catic.mobilehos.dao.AppRegDoctorsDao#deleteAppRegDoctors(java.lang.String) 
	*/ 
	@Override
	public boolean deleteAppRegDoctors(String doctorId) {
		String sql = "delete from app_reg_doctors where doctor_id = ?";
		int rouCount = this.getJdbcTemplate().update(sql,doctorId);
		return rouCount>0;
	}

}
