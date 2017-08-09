/**   
 * @Title: IDoctorDao.java 
 * @Package com.catic.mobilehos.dao.synchronize 
 * @Description: TODO
 * @author htWang   
 * @date 2014-5-20 上午10:32:22 
 * 
 */ 
package com.catic.mobilehos.dao.synchronize;

import java.util.List;

import com.catic.mobilehos.po.DoctorPO;

/**
 * @author WANG
 *
 */
public interface IDoctorDao {
	
	void addDoctorFromHis(List<DoctorPO> hisDoctorList);
	
	void deleteAllDoctor();
}
