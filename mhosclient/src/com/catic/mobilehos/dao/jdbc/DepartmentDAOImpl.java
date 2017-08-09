package com.catic.mobilehos.dao.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import com.catic.mobilehos.dao.DepartmentDAO;
import com.catic.mobilehos.po.DepartmentPO;
import com.catic.mobilehos.service.vo.DepartmentVO;
public class DepartmentDAOImpl extends JdbcDaoSupport implements DepartmentDAO {
	
	private Log log = LogFactory.getLog(this.getClass());

	/**
	 * 
	 * @Title: findDepartmentsByParas 
	 * @Description: TODO 根据名称位置分页查询科室记录
	 * @param @param name 科室名称 
	 * @param @param location 科室位置
	 * @param @param offset
	 * @param @param length
	 * @return List<DepartmentPO>  科室记录列表
	 * @throws
	 */
	@Override
	public List<DepartmentPO> findDepartmentsByParas(String name,
			String location, Integer status, String department_id, int offset, int length) {
		
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			SimpleDynamicSQL.SelectSQL sql = new SimpleDynamicSQL.SelectSQL();
			sql.setBaseSQL("select * from departments where (not parent_id is null)", false);
			if (StringUtils.isNotEmpty(name)){
				sql.addPara("department_name", "like", "%" + name + "%");
			}
			if (StringUtils.isNotEmpty(location)){
				sql.addPara("department_addr", "like", "%" + location + "%");
			}
			if (status==0||status==1){
				sql.addSQLPart(" and department_status="+status+"");
			}
			if (StringUtils.isNotEmpty(department_id)){
				sql.addSQLPart(" and parent_id="+department_id+" || department_id="+department_id+"");
			}
			sql.addSQLPart("order by display_no asc ");
			sql.setLimit(offset, length);
			log.debug("findDepartmentsByParas sql: " + sql.getFullSQL());
			List<DepartmentPO> lst = jt.query(sql.getFullSQL().toString()
					, sql.getParas().toArray(), new DepartmentPO());
			return lst;
		}catch(DataAccessException ex){
			log.error(null, ex);
			throw ex;
		}

	}
	
	/**
	 * 
	 * @Title: countDepartments 
	 * @Description: TODO 获取科室记录总数
	 * @param @param name 科室名称
	 * @param @param localtion 科室位置
	 * @return  科室记录总数
	 * @throws
	 */
	@Override
	public int countDepartments(String name, String location, Integer status, String department_id) {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			SimpleDynamicSQL.SelectSQL sql = new SimpleDynamicSQL.SelectSQL();
			sql.setBaseSQL("select count(*) from departments where (not parent_id is null)", false);
			if (StringUtils.isNotEmpty(name)){
				sql.addPara("department_name", "like", "%" + name + "%");
			}
			if (StringUtils.isNotEmpty(location)){
				sql.addPara("department_addr", "like", "%" + location + "%");
			}
			if (status==0||status==1){
				sql.addSQLPart(" and department_status="+status+"");
			}
			if (StringUtils.isNotEmpty(department_id)){
				sql.addSQLPart(" and parent_id="+department_id+" || department_id="+department_id+"");
			}
			log.debug("countDepartments sql: " + sql.getFullSQL());
			int count = jt.queryForObject(sql.getFullSQL().toString()
					, sql.getParas().toArray(), Integer.class);
			return count;
		}catch(DataAccessException ex){
			log.error(null, ex);
			throw ex;
		}
	}
	
	/**
	 * 
	 * @Title: updateDepartmentAddr 
	 * @Description: TODO 修改科室信息
	 * @param @param did  科室ID
	 * @param @param addr 科室位置
	 * @return void 无返回值
	 * @throws
	 */
	@Override
	public void updateDepartmentAddr(String did, String addr) {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "update departments set department_addr=? where department_id=?";
			jt.update(sql, addr, did);
		}catch(DataAccessException ex){
			log.error(null, ex);
			throw ex;
		}

		
	}

	/* (non-javadoc) 
	 * <p>Title: findAllDeptPO</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.catic.mobilehos.dao.DepartmentDAO#findAllDeptPO() 
	*/ 
	@Override
	public List<DepartmentPO> findAllDeptPO() {
		/*String sql = "select * from "
				     + "departments where parent_id!=0 ";*/
		String sql = "select * from "
			     + "departments ";
		List<DepartmentPO> departmentPO = getJdbcTemplate().query(sql, new DepartmentPO());
		if (null == departmentPO) {
			return new ArrayList<DepartmentPO>();
		}
		return departmentPO;
	}

	@Override
	public DepartmentPO findDepVOById(String id) {
		JdbcTemplate jt = this.getJdbcTemplate();
		String sql="SELECT * FROM departments WHERE id=?";
		List<DepartmentPO> list = getJdbcTemplate().query(sql,new Object[]{id}, new DepartmentPO());
		if(list==null||list.size()==0){
			return null;
		}
		return list.get(0);
	}

	@Override
	public DepartmentPO findDepVOByCode(String department_id) {
		JdbcTemplate jt = this.getJdbcTemplate();
		String sql="SELECT * FROM departments WHERE department_id=?";
		List<DepartmentPO> list = getJdbcTemplate().query(sql,new Object[]{department_id}, new DepartmentPO());
		if(list==null||list.size()==0){
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<DepartmentPO> findOneGradeDeptVO() {
		String sql = "select * from "
			     + "departments where parent_id=0 ";
	List<DepartmentPO> departmentPO = getJdbcTemplate().query(sql, new DepartmentPO());
	if (null == departmentPO) {
		return new ArrayList<DepartmentPO>();
	}
	return departmentPO;
	}

	@Override
	public void saveOrUpdaeDepartment(DepartmentVO d) {
		try{
		
			JdbcTemplate jt = this.getJdbcTemplate();
			if(d.getId()==0){
				String sql = "INSERT INTO departments (department_id,department_name,parent_id,introduction,department_addr,department_status,phone,display_no) VALUES(?,?,?,?,?,?,?,?) ";
				jt.update(sql,d.getDepartmentId(),d.getDepartmentName(),d.getParentId(),d.getIntroduction(),d.getDepartmentAddr(),d.getDepartment_status(),d.getPhone(),d.getDisplay_no());	
			}
			else{
			String sql = "UPDATE departments SET department_id=? ,department_name=?,parent_id=?,introduction=?,department_addr=?,department_status=?,phone=?,display_no=? WHERE id=? ";
			jt.update(sql,d.getDepartmentId(),d.getDepartmentName(),d.getParentId(),d.getIntroduction(),d.getDepartmentAddr(),d.getDepartment_status(),d.getPhone(),d.getDisplay_no(),d.getId());
			}
		}catch(DataAccessException ex){
			log.error(null, ex);
			throw ex;
		}
		
	}

	@Override
	public boolean deleteDepartment(String id) {
		StringBuffer sql= new StringBuffer("DELETE FROM departments WHERE id=? ");
		int rowCount =this.getJdbcTemplate().update(sql.toString(),id);
		return rowCount>0;
	}

}
