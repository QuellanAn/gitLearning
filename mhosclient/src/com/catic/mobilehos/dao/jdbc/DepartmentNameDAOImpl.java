package com.catic.mobilehos.dao.jdbc;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.catic.mobilehos.dao.DepartmentNameDAO;
import com.catic.mobilehos.po.DepartmentNamePO;
import com.catic.mobilehos.po.DoctorNamePO;

public class DepartmentNameDAOImpl extends JdbcDaoSupport implements DepartmentNameDAO {
	
	private Log log = LogFactory.getLog(this.getClass());

	@Override
	public List<DepartmentNamePO> getAllDepartmentName() {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "SELECT department_id,department_name,doctor_id,doctor_name FROM app_reg_orders GROUP BY department_name";
			List<DepartmentNamePO> lst = jt.query(sql, new DepartmentNamePO());
			return lst;
		}catch(DataAccessException ex){
			log.error(null, ex);
			throw ex;
		}
	}

	@Override
	public List<DoctorNamePO> findDoctorNameByType(String departmentId) {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			List<DoctorNamePO> lst;
			//.....
//			departmentId ="10003";
			String b = "0";
			if (departmentId.equals(b)){
				String sql = "SELECT doctor_id,doctor_name,department_id FROM app_reg_orders GROUP BY doctor_name";
				lst = jt.query(sql, new DoctorNamePO());
				
			}else{
				String sql = "SELECT doctor_id,doctor_name,department_id FROM app_reg_orders where department_id= ? GROUP BY doctor_name";
				lst = jt.query(sql, new DoctorNamePO(), departmentId);
			}
			return lst;
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}
	}

	
	
	
	

}
