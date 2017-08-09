/**   
 * @Title: DepartmentDoctorDao.java 
 * @Package com.catic.mobilehos.dao 
 * @Description: TODO
 * @author htWang   
 * @date 2014-5-19 下午4:02:01 
 * 
 */ 
package com.catic.mobilehos.dao.synchronize;

import java.util.List;

import com.catic.mobilehos.po.DepartmentDoctorPO;

/**
 * @author WANG
 *
 */
public interface IDepartmentDoctorDao {
	void delAllDeptDoctor();
	void addDeptDoctor(List<DepartmentDoctorPO> deptDocList);
}
