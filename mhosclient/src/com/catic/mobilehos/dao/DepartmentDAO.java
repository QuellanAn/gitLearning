package com.catic.mobilehos.dao;

import java.util.List;

import com.catic.mobilehos.po.DepartmentPO;
import com.catic.mobilehos.service.vo.DepartmentVO;

public interface DepartmentDAO {
	
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
	List<DepartmentPO> findDepartmentsByParas(String name, String location, Integer status, String department_id, int offset, int length);
	
	/**
	 * 
	 * @Title: countDepartments 
	 * @Description: TODO 获取科室记录总数
	 * @param @param name 科室名称
	 * @param @param localtion 科室位置
	 * @return  科室记录总数
	 * @throws
	 */
	int countDepartments(String name, String localtion, Integer status, String department_id);
	
	/**
	 * 
	 * @Title: updateDepartmentAddr 
	 * @Description: TODO 修改科室信息
	 * @param @param did  科室ID
	 * @param @param addr 科室位置
	 * @return void 无返回值
	 * @throws
	 */
	void updateDepartmentAddr(String did, String addr);
	
	List<DepartmentPO> findAllDeptPO();
	/**
	 * 
	 * 描述：根据id查找科室
	 * @author yjj
	 * @date 2016-11-22
	 */
	DepartmentPO findDepVOById(String id);
	/**
	 * 
	 * 描述：根据科室编码查找科室
	 * @author yjj
	 * @date 2016-11-22
	 */
	DepartmentPO findDepVOByCode(String department_id);
	/**
	 * 
	 * 描述：查找一级科室
	 * @author yjj
	 * @date 2016-11-22
	 */
	List<DepartmentPO> findOneGradeDeptVO();
	/**
	 * 
	 * 描述：更新或者添加科室
	 * @author yjj
	 * @date 2016-11-22
	 */
	void saveOrUpdaeDepartment(DepartmentVO d);
	boolean deleteDepartment(String id);
}
