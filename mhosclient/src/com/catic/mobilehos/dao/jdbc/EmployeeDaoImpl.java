package com.catic.mobilehos.dao.jdbc;




import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.catic.mobilehos.dao.EmployeeDao;
import com.catic.mobilehos.po.EmployeePO;
import com.catic.mobilehos.po.HosPubInfoPO;


public class EmployeeDaoImpl extends JdbcDaoSupport implements EmployeeDao{

	private Log log = LogFactory.getLog(this.getClass());
	@Override
	public List<EmployeePO> select_EmployeePOs() {
		// TODO Auto-generated method stub
		try {
			System.out.println("进入daoImpl层");
			JdbcTemplate jt=this.getJdbcTemplate();
			String sql="select employee_id,employee_name,employee_password,employee_sex,department,salary from _employee";
			System.out.println(sql);
			List<EmployeePO> list=jt.query(sql, new EmployeePO());
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<EmployeePO> select_EmployeePOsByName(String name) {
		// TODO Auto-generated method stub
		try {
			JdbcTemplate jt=this.getJdbcTemplate();
			String sql="select employee_id,employee_name,employee_password,employee_sex,department,salary from _employee where employee_name=?";
			List<EmployeePO> list=jt.query(sql, new EmployeePO(), name);
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}

	@Override
	public boolean addEmployee(final EmployeePO employeePO) {
		// TODO Auto-generated method stub
			JdbcTemplate jt=this.getJdbcTemplate();
			String sql="INSERT INTO _EMPLOYEE(EMPLOYEE_NAME,EMPLOYEE_PASSWORD,EMPLOYEE_SEX,DEPARTMENT,SALARY) VALUES(?,?,?,?,?)";
			System.out.println("daoImpl层"+sql);
			System.out.println(employeePO.getEmployeeName()+employeePO.getDepartment());
			//int b=jt.update(sql, employeePO);
			int rows = jt.update(sql, new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement preparedStatement)
						throws SQLException {
					preparedStatement.setString(1, employeePO.getEmployeeName());
					preparedStatement.setString(2, employeePO.getPassword());
					preparedStatement.setString(3,employeePO.getSex());
					preparedStatement.setString(4, employeePO.getDepartment());
					preparedStatement.setDouble(5, employeePO.getSalary());
				}
			});
			return rows>0;
	}

	@Override
	public boolean updateEmployee(final EmployeePO employeePO) {
		// TODO Auto-generated method stub
		JdbcTemplate jt=this.getJdbcTemplate();
		String sql="UPDATE _EMPLOYEE SET EMPLOYEE_NAME=?,EMPLOYEE_PASSWORD=?,EMPLOYEE_SEX=?,DEPARTMENT=?,SALARY=? where EMPLOYEE_ID=?";		
		System.out.println(employeePO.getEmployeeName()+employeePO.getDepartment());
		//int b=jt.update(sql, employeePO);
		int rows = jt.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement preparedStatement)
					throws SQLException {
				preparedStatement.setString(1, employeePO.getEmployeeName());
				preparedStatement.setString(2, employeePO.getPassword());
				preparedStatement.setString(3,employeePO.getSex());
				preparedStatement.setString(4, employeePO.getDepartment());
				preparedStatement.setDouble(5, employeePO.getSalary());
				preparedStatement.setInt(6, employeePO.getEmployeeId());
			}
		});
		return rows>0;
	}

	@Override
	public boolean deleteEmployee(EmployeePO employeePO) {
		// TODO Auto-generated method stub	
			JdbcTemplate jt=this.getJdbcTemplate();
			String sql="delete from _employee where employee_id=?";
			int rows=jt.update(sql, employeePO.getEmployeeId());
		return rows>0;
	}

	@Override
	public int countEmployeePOList(EmployeePO employeePO){
		// TODO Auto-generated method stub
		try {
			JdbcTemplate jt = this.getJdbcTemplate();
			List<Object> lstArgs = new ArrayList<Object>();
			
			//StringBuilder sql = new StringBuilder("SELECT COUNT(*) AS C FROM _EMPLOYEE where 1=1");
			//lstArgs.add(EmployeePO.STATUS_APPROVED);
			//lstArgs.add(EmployeePO.STATUS_CANCEL);
			 StringBuffer sql = new StringBuffer();
			 String name=employeePO.getEmployeeName();
			 String department=employeePO.getDepartment();
			 String sex=employeePO.getSex();
		        sql.append("SELECT COUNT(*) AS C FROM _EMPLOYEE where 1=1 ");
			//连接查询条件，姓名
			if (employeePO.getEmployeeName()!= null && !employeePO.getEmployeeName().equals("")) {
				sql.append(" AND EMPLOYEE_NAME LIKE ?");
				String s1="%"+employeePO.getEmployeeName()+"%";
				employeePO.setEmployeeName(s1);
				//lstArgs.add(employeePO.getEmployeeName());
				lstArgs.add(employeePO.getEmployeeName());
			}
			//性别
			if (employeePO.getSex()!= null && !employeePO.getSex().equals("")) {
				sql.append(" AND EMPLOYEE_SEX= ?");
				//lstArgs.add(employeePO.getSex());
				lstArgs.add(sex);
			}
			//部门
			if (employeePO.getDepartment()!= null && !employeePO.getDepartment().equals("")) {
				sql.append(" AND DEPARTMENT LIKE ?");
				//lstArgs.add(employeePO.getDepartment());
				String s2="%"+employeePO.getDepartment()+"%";
				employeePO.setDepartment(s2);
				lstArgs.add(employeePO.getDepartment());
			}
			System.out.println("daoImpl1"+sql);
			int count = jt.queryForObject(sql.toString(), lstArgs.toArray(), Integer.class);
			return count;
		} catch (DataAccessException ex){
			log.error(null,ex);
			throw ex;	
		}
		
	}

	@Override
	public List<EmployeePO> findEmployeePOListByParas(EmployeePO employeePO,
			int offset, int length) {
		// TODO Auto-generated method stub
		//SELECT EMPLOYEE_ID,EMPLOYEE_NAME,EMPLOYEE_PASSWORD,EMPLOYEE_SEX,DEPARTMENT,SALARY FROM _EMPLOYEE where 1=1
		try {
			JdbcTemplate jt = this.getJdbcTemplate();
			List<Object> lstArgs = new ArrayList<Object>();
			StringBuilder sql = new StringBuilder("SELECT EMPLOYEE_ID,EMPLOYEE_NAME,EMPLOYEE_PASSWORD,EMPLOYEE_SEX,DEPARTMENT,SALARY FROM _EMPLOYEE where 1=1 ");
			System.out.println(employeePO.getEmployeeName());
			//连接查询条件，姓名
			if(employeePO.getEmployeeName()!= null && !employeePO.getEmployeeName().trim().equals("")) {
				sql.append(" AND EMPLOYEE_NAME LIKE ?");
				String s1="%"+employeePO.getEmployeeName()+"%";
				employeePO.setEmployeeName(s1);
				lstArgs.add(employeePO.getEmployeeName());
			}
			//性别
			if (employeePO.getSex()!= null && !employeePO.getSex().equals("")) {
				sql.append(" AND EMPLOYEE_SEX=?");
				lstArgs.add(employeePO.getSex());
			}
			//部门
			if (employeePO.getDepartment()!= null && !employeePO.getDepartment().equals("")) {
				sql.append(" AND DEPARTMENT LIKE ?");
				String s2="%"+employeePO.getDepartment()+"%";
				employeePO.setEmployeeName(s2);
				lstArgs.add(employeePO.getDepartment());
			}
			sql.append(" order by EMPLOYEE_ID desc limit ?,?");
			//sql.insert(0, "select R, EMPLOYEE_ID,EMPLOYEE_NAME,EMPLOYEE_PASSWORD,EMPLOYEE_SEX,DEPARTMENT,SALARY FROM (");
			//int outset=offset+length-1;
			//ql.append(") WHERE R BETWEEN "+offset+" AND "+outset);
			System.out.println("daoImpl2"+sql);
			lstArgs.add(offset);
			lstArgs.add(length);
			//lstArgs.add(outset);
			List<EmployeePO> list = jt.query(sql.toString(), lstArgs.toArray(), new EmployeePO());
			return list;
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}
	}




}
