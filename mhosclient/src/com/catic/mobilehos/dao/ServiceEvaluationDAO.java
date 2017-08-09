package com.catic.mobilehos.dao;

import java.sql.Date;
import java.util.List;

import com.catic.mobilehos.po.ServiceEvaluationPO;
import com.catic.mobilehos.po.ShowQuesPO;

/**
 * 服务评价dao层接口
 * 
 * @author dsh
 * 
 */
public interface ServiceEvaluationDAO {

	int countServiceEvaluationByparas(String departmentId, String doctorId,
			java.sql.Date startDate, java.sql.Date endDate, String title);

	List<ServiceEvaluationPO> findServiceEvaluationByParas(String departmentId,
			String doctorId, java.sql.Date startDate, java.sql.Date endDate,
			int offset, int length, String title);

	List<ShowQuesPO> findQuestionsById(String questionnaireId,
			String appRegOrderId);

	
}
