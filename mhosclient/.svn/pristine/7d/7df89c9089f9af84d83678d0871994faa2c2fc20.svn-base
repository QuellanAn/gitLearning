/**   
 * @Title: IDoctorServiceImpl.java 
 * @Package com.catic.mobilehos.service.synchronize 
 * @Description: TODO
 * @author htWang   
 * @date 2014-5-20 上午10:36:04 
 * 
 */
package com.catic.mobilehos.service.synchronize;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.catic.mobilehos.dao.synchronize.IDoctorDao;
import com.catic.mobilehos.po.DoctorPO;
import com.catic.mobilehos.utils.GetServiceImplPort;

/**
 * @author WANG
 * 
 */
public class IDoctorServiceImpl {

	private Log log = LogFactory.getLog(this.getClass());

	private IDoctorDao iDoctorDao;

	public void addDoctorFromHis() {
		try {
			List<DoctorPO> doctorList = new ArrayList<DoctorPO>();
			String doctors = (new GetServiceImplPort()).getPort().getDoctors();
			JSONArray docArray = JSONArray.fromObject(doctors);
			for (int i = 0; i < docArray.size(); i++) {
				DoctorPO doctor = new DoctorPO();
				doctor.setDoctorId(docArray.getJSONObject(i).getString("doctor_id"));
				doctor.setDoctorName(docArray.getJSONObject(i).getString("doctor_name"));
				doctor.setIntroduction(docArray.getJSONObject(i).getString("introduction"));
				doctor.setJob(docArray.getJSONObject(i).getString("job"));
				doctor.setSpeciality(docArray.getJSONObject(i).getString("speciality"));
				doctorList.add(doctor);
			}
			iDoctorDao.deleteAllDoctor();
			iDoctorDao.addDoctorFromHis(doctorList);
		} catch (Exception e) {
			log.error(null, e);
		}

	}

	/**
	 * @param iDoctorDao
	 *            the iDoctorDao to set
	 */
	public void setiDoctorDao(IDoctorDao iDoctorDao) {
		this.iDoctorDao = iDoctorDao;
	}

}
