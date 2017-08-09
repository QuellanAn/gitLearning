package com.catic.mobilehos.dao;

import java.util.List;

import com.catic.mobilehos.po.ServiceEvaluationHosPO;
public interface ServiceEvaluationHosDAO {

	/**
	 * 根据时间，总体评价，查询服务评价
	 * @param overallEvaluation
	 * @param startDate
	 * @param endDate
	 * @param offset
	 * @param length
	 * @return
	 */
	List<ServiceEvaluationHosPO> findListByParas (String doctorName,String departmentCode,String overallEvaluation, 
			java.sql.Date startDate, java.sql.Date endDate, int offset, int length,String cardNumber,
			String patientname, String departmentName, String evaSource);
	/**
	 * 根据时间，总体评价，查询记录数量
	 * @param overallEvaluation
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	int countServiceEvaluationHos (String doctorName,String departmentCode,String overallEvaluation, 
			java.sql.Date startDate, java.sql.Date endDate,String cardNumber,String patientname, String departmentName, String evaSource);
	
	
	
	ServiceEvaluationHosPO findServiceEvaluationHosById(Integer serviceEvaluationId);
	
	int statisticServiceEvaluationHos(String departmentCode,String overallEvaluation,java.sql.Date startDate, java.sql.Date endDate);
	
	/**
	 * 根据条件统计服务评价
	 * @param po 查询条件
	 * @return 统计结果
	 */
	public List<ServiceEvaluationHosPO> getCommentCount(ServiceEvaluationHosPO po);
	
	/**
	 * 根据科室统计服务评价
	 * @param po 查询条件
	 * @return 统计结果
	 */
	public List<ServiceEvaluationHosPO> getCommentCountByDepartMent(ServiceEvaluationHosPO po);
	
	/**
	 * 根据日期统计服务评价
	 * @param po 查询条件
	 * @return 按日期统计服务评价统计结果
	 */
	public List<ServiceEvaluationHosPO> getCommentCountByDate(ServiceEvaluationHosPO po);
	
	/**
	 * 按医生分组统计服务评价
	 * @param po 查询条件
	 * @return 按医生分组统计服务评价统计结果
	 */
	public List<ServiceEvaluationHosPO> getCommentCountByDoctor(ServiceEvaluationHosPO po);
	
}
