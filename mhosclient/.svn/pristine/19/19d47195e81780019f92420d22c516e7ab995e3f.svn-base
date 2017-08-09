/**   
 * @Title: DepartmentDoctorServiceImpl.java 
 * @Package com.catic.mobilehos.service 
 * @Description: TODO
 * @author htWang   
 * @date 2014-5-19 下午4:26:37 
 * 
 */
package com.catic.mobilehos.service.synchronize;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.catic.mobilehos.dao.synchronize.IDepartmentDoctorDao;
import com.catic.mobilehos.po.DepartmentDoctorPO;
import com.catic.mobilehos.utils.GetServiceImplPort;

/**
 * @author WANG
 * 
 */
public class IDepartmentDoctorServiceImpl implements IDepartmentDoctorService {

	private Log log = LogFactory.getLog(this.getClass());

	private IDepartmentDoctorDao deptDocDao;

	@Override
	public void saveDeptDocFromHIS() {
		try {
			List<DepartmentDoctorPO> deptDocList = new ArrayList<DepartmentDoctorPO>();
			String deptDoc = (new GetServiceImplPort()).getPort().getHisDepartmentDoctor();
			JSONArray deptDocArray = JSONArray.fromObject(deptDoc);
			for (int i = 0; i < deptDocArray.size(); i++) {
				DepartmentDoctorPO deptDocPO = new DepartmentDoctorPO();
				deptDocPO.setDepartmentId(deptDocArray.getJSONObject(i).getString("department_id"));
				deptDocPO.setDoctorId(deptDocArray.getJSONObject(i).getString("doctor_id"));
				deptDocList.add(deptDocPO);
			}
			deptDocDao.delAllDeptDoctor();
			deptDocDao.addDeptDoctor(deptDocList);
		} catch (Exception e) {
			log.error(e);
		}
	}

	/**
	 * @param deptDocDao
	 *            the deptDocDao to set
	 */
	public void setDeptDocDao(IDepartmentDoctorDao deptDocDao) {
		this.deptDocDao = deptDocDao;
	}

}
