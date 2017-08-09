package com.catic.mobilehos.service;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.catic.mobilehos.service.vo.DepartmentVO;
import com.catic.mobilehos.service.vo.Page;

/**
 * 科室相关的服务
 * @author xieweipeng
 *
 */
public interface DepartmentsService {
		
	Page<DepartmentVO> queryDepartmentsListByParas(String name, String location, Integer status, String department_id, int page, int pageSize);

	ServiceResult saveDepartmentAddr(String did, String addr);
	
	List<DepartmentVO> findAllDeptVO();
	DepartmentVO findDepVOById(String id);
	DepartmentVO findDepVOByCode(String department_id);
	/**
	 * 
	 * 描述：查找一级科室
	 * @author yjj
	 * @date 2016-11-22
	 */
	List<DepartmentVO> findOneGradeDeptVO();
	ServiceResult saveOrUpdaeDepartment(DepartmentVO d);
	boolean deleteDepartment (String id);
	
	List<DepartmentVO> readXls(Workbook workBook);
}
