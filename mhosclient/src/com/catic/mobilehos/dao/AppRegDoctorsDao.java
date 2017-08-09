/**   
 * @Title: AppRegDoctors.java 
 * @Package com.catic.mobilehos.dao 
 * @Description: TODO
 * @author htWang   
 * @date 2014-5-7 上午8:41:09 
 * 
 */ 
package com.catic.mobilehos.dao;

/**
 * @author WANG
 *
 */
public interface AppRegDoctorsDao {
	/**
	 * 
	 * @Title: deleteAppRegDoctors 
	 * @Description: TODO 根据医生ID 删除已预约医生记录
	 * @param @param doctorId 医生ID
	 * @return boolean 标识是否删除成功   
	 * @throws
	 */
	boolean deleteAppRegDoctors(String doctorId);
}
