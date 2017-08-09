package com.catic.mobilehos.dao;

import java.util.List;

import com.catic.mobilehos.po.EmployeePO;


public interface EmployeeDao {

	/**
	 * 查找所有员工信息
	 * @return
	 */
	public List<EmployeePO> select_EmployeePOs();
	
	/**
	 * 通过姓名查找指定员工信息
	 * @param name 员工姓名
	 * @return
	 */
	public List<EmployeePO> select_EmployeePOsByName(String name);
	
	/**
	 * 添加新员工信息
	 * @param employeePO 员工信息
	 * @return
	 */
	public boolean addEmployee(EmployeePO employeePO);
	
	/**
	 * 修改指定员工信息
	 * @param employeePO 员工信息
	 * @return
	 */
	public boolean updateEmployee(EmployeePO employeePO);
	
	/**
	 * 删除指定员工信息
	 * @param employeePO 员工信息
	 * @return
	 */
	public boolean deleteEmployee(EmployeePO employeePO);
	
	int countEmployeePOList(EmployeePO employeePO);
	List<EmployeePO>findEmployeePOListByParas(EmployeePO employeePO, int offset, int length);
}

