/**   
 * @Title: IDepartmentServiceImpl.java 
 * @Package com.catic.mobilehos.service.synchronize 
 * @Description: TODO
 * @author htWang   
 * @date 2014-5-20 上午9:55:28 
 * 
 */ 
package com.catic.mobilehos.service.synchronize;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import com.catic.mobilehos.dao.synchronize.IDepartmentDao;
import com.catic.mobilehos.po.DepartmentPO;
import com.catic.mobilehos.utils.GetServiceImplPort;

/**
 * @author WANG
 *
 */
public class IDepartmentServiceImpl implements IDepartmentService{
	
	private IDepartmentDao iDepartmentDao;
	
	public void saveDepartmentFromHis(){
		String depts = (new GetServiceImplPort()).getPort().getDepartments();
		JSONArray deptArray = JSONArray.fromObject(depts);
		List<DepartmentPO> deptList = new ArrayList<DepartmentPO>();
		for (int i = 0; i < deptArray.size(); i++) {
			DepartmentPO department = new DepartmentPO();
			department.setDepartmentId(deptArray.getJSONObject(i).getString("department_id"));
			department.setDepartmentName(deptArray.getJSONObject(i).getString("department_name"));
			if(deptArray.getJSONObject(i).getString("parent_id").equals("")){
				department.setParentId(null);
			} else {
				department.setParentId(deptArray.getJSONObject(i).getString("parent_id"));
			}
			if(deptArray.getJSONObject(i).getString("department_addr").equals("")){
				department.setDepartmentAddr(null);
			} else {
				department.setDepartmentAddr(deptArray.getJSONObject(i).getString("department_addr"));
			}
			deptList.add(department);
		}
		iDepartmentDao.delAllDeprtments();
		iDepartmentDao.addDepartmentsFromHis(deptList);
	}

	/**
	 * @param iDepartmentDao the iDepartmentDao to set
	 */
	public void setiDepartmentDao(IDepartmentDao iDepartmentDao) {
		this.iDepartmentDao = iDepartmentDao;
	}
}	
