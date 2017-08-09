package com.catic.mobilehos.dao;

import java.sql.Timestamp;
import java.util.List;

import com.catic.mobilehos.po.AppRegOrdersPO;
import com.catic.mobilehos.po.PatientPO;
/**
 * 预约挂号dao层接口
 * @author WANG
 *
 */
public interface AppRegOrdersDAO {
	
	/**
	 * 
	* @Title: findAppRegOrdersByParas  
	* @Description: TODO 带条件分页查询预约挂号记录
	* @param @param status 预约状态
	* @param @param startDocDate 查询的开始就诊日期
	* @param @param endDocDate 查询的结束就诊日期
	* @param @param startCreateTime 查询的创建预约开始日期
	* @param @param endCreateTime 查询的创建预约结束日期
	* @param @param offset 
	* @param @param length
	* @return List<AppRegOrdersPO>  预约挂号记录列表
	* @throws
	 */
	List<AppRegOrdersPO> findAppRegOrdersByParas(String orderid,
			String patientname,String status,
			java.sql.Date startDocDate, java.sql.Date endDocDate,
			Timestamp startCreateTime, Timestamp endCreateTime, int offset,
			int length,String type, String cardNumber, String doctorName, 
			String departmentCode, String paid, String departmentName, String regSource);
	/**
	 * 
	 * @Title: countAppRegOrdersByParas 
	 * @Description: TODO 条件查询记录总数
	 * @param @param status 预约状态
	 * @param @param startDocDate 查询的开始就诊日期
	 * @param @param endDocDate 查询的结束就诊日期
	 * @param @param startCreateTime 查询的创建预约开始日期
	 * @param @param endCreateTime 查询的创建预约结束日期
	 * @return int    记录总数
	 * @throws
	 */
	int countAppRegOrdersByParas(String orderid,
			String patientname,String status, java.sql.Date startDocDate,
			java.sql.Date endDocDate, Timestamp startCreateTime,
			Timestamp endCreateTime,String type, String cardNumber, String doctorName,
			String departmentCode, String paid, String departmentName, String regSource);
	
	/**
	 * 
	 * @Title: deleteAppRegOrdersByDoctor 
	 * @Description: TODO 删除某个医生时 将与该医生有关的预约挂号记录删除
	 * @param @return    
	 * @return boolean    
	 * @throws
	 */
	boolean deleteAppRegOrdersByDoctor(String doctorId);
	/**
	 * 通过就诊人的id来查询就诊人的信息
	 * @param patientId
	 * @return
	 */
	
	List<PatientPO> findPatientByPatentId(String patientId);
	/**
	 * 通过预约号查询预约信息
	 * @param orderid
	 * @return
	 */
	List<AppRegOrdersPO> findRegInfoByOrderId(String orderidNum);
	
	List<AppRegOrdersPO> findAppRegOrdersPOByAppRegOrderId(String appRegOrderId);
	
	/**
	 * 按科室分组统计查询结果
	 * @param po 查询条件
	 * @return 分组统计查询结果 
	 */
	List<AppRegOrdersPO> getCountByDepartment(AppRegOrdersPO po);
	
	/**
	 * 按日期分组统计结果
	 * @param po 查询条件
	 * @return 按日期分组统计结果 
	 */
	List<AppRegOrdersPO> getCountByDate(AppRegOrdersPO po);
	
	/**
	 * 按医生分组统计查询结果
	 * @param po 查询条件
	 * @return 分组统计查询结果 
	 */
	List<AppRegOrdersPO> getCountByDoctor(AppRegOrdersPO po);

}
