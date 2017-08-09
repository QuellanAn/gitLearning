/**   
 * @Title: IDepartmentDao.java 
 * @Package com.catic.mobilehos.dao.synchronize 
 * @Description: TODO
 * @author htWang   
 * @date 2014-5-20 上午9:48:52 
 * 
 */ 
package com.catic.mobilehos.dao.synchronize;

import java.util.List;

import com.catic.mobilehos.po.DepartmentPO;

/**
 * @author WANG
 *
 */
public interface IDepartmentDao {
	
	/**
	 * 
	 * @Title: addDepartments 
	 * @Description: TODO 
	 * @param deptList  通过webService从his中读取部门数据插入
	 * @throws
	 */
	void addDepartmentsFromHis(List<DepartmentPO> deptList);
	
	/**
	 * 
	 * @Title: delAllDoctors 
	 * @Description: TODO 
	 * @param 删除本地数据部门数据 
	 * @throws
	 */
	void delAllDeprtments();
}
