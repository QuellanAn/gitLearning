package com.catic.mobilehos.service;
import java.util.List;

import com.catic.mobilehos.po.ServiceEvaluationHosPO;
import com.catic.mobilehos.service.vo.Page;
import com.catic.mobilehos.service.vo.ServiceEvaluationHosVO;
public interface ServiceEvaluationHosService {
	
	Page<ServiceEvaluationHosVO> findListByParas(String doctorName,String departmentCode,String overallEvaluation,java.sql.Date startDate,
			java.sql.Date endDate,int page, int pageSize,String cardNumber,String patientname, String departmentName, String evaSource);
	
		
	ServiceEvaluationHosVO  findServiceEvaluationHosById(Integer serviceEvaluationId);
	
	ServiceEvaluationHosVO  statisticEvaluateHos(String departmentCode,java.sql.Date startDate,
			java.sql.Date endDate);
	
	/**
	 * 根据条件统计服务评价
	 * @param po 查询条件
	 * @return 统计结果
	 */
	public List<ServiceEvaluationHosPO> getCommentCount(ServiceEvaluationHosPO po);
	
	/**
	 * 根据科室统计服务评价
	 * @param po 查询条件
	 * @param type 评价等级，0为综合，1为好评，2为中评，3为差评
	 * @return 统计结果
	 */
	public List<ServiceEvaluationHosPO> getCommentCountByDepartMent(ServiceEvaluationHosPO po, String type);
	
	/**
	 * 根据日期统计服务评价
	 * @param po 查询条件
	 * @param type 评价等级，0为综合，1为好评，2为中评，3为差评
	 * @return 按日期统计服务评价统计结果
	 */
	public List<ServiceEvaluationHosPO> getCommentCountByDate(ServiceEvaluationHosPO po, String type);
	
	/**
	 * 按医生分组统计服务评价
	 * @param po 查询条件
	 * @param type 评价等级，0为综合，1为好评，2为中评，3为差评
	 * @return 按医生分组统计服务评价统计结果
	 */
	public List<ServiceEvaluationHosPO> getCommentCountByDoctor(ServiceEvaluationHosPO po, String type);
}
