package com.catic.mobilehos.service;

import java.util.List;

import net.sf.json.JSONObject;

import com.catic.mobilehos.service.vo.DepartmentNameVO;
import com.catic.mobilehos.service.vo.DoctorNameVO;
import com.catic.mobilehos.service.vo.Page;
import com.catic.mobilehos.service.vo.ServiceEvaluationVO;


/**
 * 服务评价详情
 * @author dsh
 *
 */
public interface ServiceEvaluationService {

	Page<ServiceEvaluationVO> queryServiceEvaluationByParas(String departmentId, String doctorId, java.sql.Date startSQLDate,
			java.sql.Date endSQLDate, int page, int pageSize, String title);

	List<DepartmentNameVO> getAllDepartmentName();

	List<DoctorNameVO> getDoctorName(String departmentId);

	JSONObject findQuestionsById(String questionnaireId,
			String appRegOrderId);
	


	

}
