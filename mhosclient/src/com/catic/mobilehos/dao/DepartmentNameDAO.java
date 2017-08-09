package com.catic.mobilehos.dao;

import java.util.List;

import com.catic.mobilehos.po.BusTypeDicPO;
import com.catic.mobilehos.po.DepartmentNamePO;
import com.catic.mobilehos.po.DoctorNamePO;

public interface DepartmentNameDAO {
	
	
	List<DepartmentNamePO> getAllDepartmentName();

	List<DoctorNamePO> findDoctorNameByType(String departmentId);
	

}
