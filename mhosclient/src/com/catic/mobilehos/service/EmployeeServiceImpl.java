package com.catic.mobilehos.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



import com.catic.mobilehos.dao.EmployeeDao;
import com.catic.mobilehos.dao.jdbc.EmployeeDaoImpl;
import com.catic.mobilehos.po.EmployeePO;
import com.catic.mobilehos.service.vo.Page;

public class EmployeeServiceImpl implements EmployeeService{

	private Log log = LogFactory.getLog(this.getClass());
	private EmployeeDao employeeDao;
	
	public EmployeeDao getEmployeeDao() {
		return employeeDao;
	}

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	@Override
	public List<EmployeePO> selectEmployeePOs() {
		// TODO Auto-generated method stub
		try {
			System.out.println("进入serviceImpl层");
			
//			List<EmployeePO> employee=employeeDao.select_EmployeePOs();
			EmployeeDaoImpl employeeDaoImpl=new EmployeeDaoImpl();
			System.out.println(employeeDao);
			List<EmployeePO> employee=employeeDao.select_EmployeePOs();
					
//			JSONArray jarr=JSONArray.fromObject(employee);
//			return jarr;
			return employee;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}

	@Override
	public List<EmployeePO> selectEmployeePOByName(String name) {
		// TODO Auto-generated method stub
		try {
			//List<EmployeePO> employee=employeeDao.select_EmployeePOsByName(name);
			return this.employeeDao.select_EmployeePOsByName(name);
		}finally{
			System.out.println("service 层结束");
		}
		
	}

	@Override
	public boolean addEmployee(EmployeePO employeePO) {
		// TODO Auto-generated method stub
		try {
			System.out.println("service层add");
			return this.employeeDao.addEmployee(employeePO);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public boolean updateEmployee(EmployeePO employeePO) {
		// TODO Auto-generated method stub
		return this.employeeDao.updateEmployee(employeePO);
	
	}

	@Override
	public boolean deleteEmployee(int number) {
		// TODO Auto-generated method stub
		EmployeePO employeePO=new EmployeePO();
		employeePO.setEmployeeId(number);
		return this.employeeDao.deleteEmployee(employeePO);
	}

	@Override
	public Page<EmployeePO> selectEmployeePOList(EmployeePO employeePO,
			int page, int pageSize) {
		// TODO Auto-generated method stub
		int totalRecord=this.employeeDao.countEmployeePOList(employeePO);
		System.out.println("service"+totalRecord);
		Page<EmployeePO> p=new Page<EmployeePO>(totalRecord, pageSize,page);
		System.out.println("service1"+employeePO.getEmployeeName());
		List<EmployeePO> orig=this.employeeDao.findEmployeePOListByParas(employeePO, p.getOffset(), p.getPageSize());
		System.out.println("service2");
		List<EmployeePO> dest=EmployeePO.fromList(orig);
		
		p.setCurPageData(dest);
		
		return p;
	}

}
