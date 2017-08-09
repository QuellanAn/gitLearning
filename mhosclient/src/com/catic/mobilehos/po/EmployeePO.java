package com.catic.mobilehos.po;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.jdbc.core.RowMapper;

import com.catic.mobilehos.service.vo.HosPubInfoVO;

public class EmployeePO implements RowMapper<EmployeePO>{

	public static final int STATUS_NEW = 0;
	
	public static final int STATUS_UNAPPROVED = 1;
	
	public static final int STATUS_APPROVED = 2;
	
	public static final int STATUS_CANCEL = 3;
	
	private int employeeId;
	private String employeeName;
	private String password;
	private String sex;
	private String department;
	private double salary;
	public int getEmployeeId() {
		return employeeId;
	}
	
	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	@Override
	public EmployeePO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		EmployeePO employee=new EmployeePO();
		employee.setEmployeeId(rs.getInt("employee_id"));
		employee.setEmployeeName(rs.getString("employee_name"));
		employee.setPassword(rs.getString("employee_password"));
		employee.setSex(rs.getString("employee_sex"));
		employee.setDepartment(rs.getString("department"));
		employee.setSalary(rs.getDouble("salary"));
		return employee;
	}

	public static EmployeePO fromPO(EmployeePO po){
		EmployeePO vo = new EmployeePO();
		try {
			PropertyUtils.copyProperties(vo, po);
			return vo;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public static List<EmployeePO> fromList(List<EmployeePO> oriList){
		List<EmployeePO> destList = new ArrayList<EmployeePO>();
		for (EmployeePO p : oriList){
			destList.add(fromPO(p));
		}
		return destList;
	}
}
