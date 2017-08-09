package com.catic.mobilehos.service;

import java.util.List;

import com.catic.mobilehos.po.EmployeePO;
import com.catic.mobilehos.service.vo.Page;

import net.sf.json.JSONArray;

public interface EmployeeService {

	/**
	 * 查询所有用户信息
	 * @return
	 */
	List<EmployeePO> selectEmployeePOs();
	
	List<EmployeePO> selectEmployeePOByName(String name);
	/**
	 * 查找指定条件的员工信息，并进行分页
	 * @param employeePO
	 * @param page
	 * @param pageSize
	 * @return
	 */
	Page<EmployeePO> selectEmployeePOList(EmployeePO employeePO,int page, int pageSize);
	/**
	 * 添加新员工信息
	 * @param employeePO 员工信息
	 */
	 boolean addEmployee(EmployeePO employeePO);
	
	/**
	 * 修改指定员工信息
	 * @param employeePO 员工信息
	 */
	 boolean updateEmployee(EmployeePO employeePO);
	
	/**
	 * 删除指定员工信息
	 * @param employeePO 员工信息
	 */
	 boolean deleteEmployee(int number);
}
