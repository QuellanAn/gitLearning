package com.catic.mobilehos.service;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.catic.mobilehos.dao.DepartmentNameDAO;
import com.catic.mobilehos.dao.ServiceEvaluationDAO;
import com.catic.mobilehos.po.DepartmentNamePO;
import com.catic.mobilehos.po.DoctorNamePO;
import com.catic.mobilehos.po.QuestionPO;
import com.catic.mobilehos.po.ServiceEvaluationPO;
import com.catic.mobilehos.po.ShowQuesPO;
import com.catic.mobilehos.service.vo.DepartmentNameVO;
import com.catic.mobilehos.service.vo.DoctorNameVO;
import com.catic.mobilehos.service.vo.Page;
import com.catic.mobilehos.service.vo.ServiceEvaluationVO;
import com.catic.mobilehos.service.vo.VOPOConverter;

public class ServiceEvaluationServiceImpl implements ServiceEvaluationService {

	private Log log = LogFactory.getLog(this.getClass());

	private ServiceEvaluationDAO serviceEvaluationDAO;
	private DepartmentNameDAO departmentNameDAO;
	

	public ServiceEvaluationDAO getServiceEvaluationDAO() {
		return serviceEvaluationDAO;
	}

	public void setServiceEvaluationDAO(
			ServiceEvaluationDAO serviceEvaluationDAO) {
		this.serviceEvaluationDAO = serviceEvaluationDAO;
	}

	public DepartmentNameDAO getDepartmentNameDAO() {
		return departmentNameDAO;
	}

	public void setDepartmentNameDAO(DepartmentNameDAO departmentNameDAO) {
		this.departmentNameDAO = departmentNameDAO;
	}

	@Override
	public Page<ServiceEvaluationVO> queryServiceEvaluationByParas(
			String departmentId, String doctorId,
			java.sql.Date startDate, java.sql.Date endDate, int page,
			int pageSize, String title) {
		try {
			int totalRecord = this.serviceEvaluationDAO
					.countServiceEvaluationByparas(departmentId,doctorId,startDate, endDate, title);
			Page<ServiceEvaluationVO> p = new Page<ServiceEvaluationVO>(
					totalRecord, pageSize, page);

			List<ServiceEvaluationPO> lst = this.serviceEvaluationDAO
					.findServiceEvaluationByParas(departmentId,doctorId,startDate, endDate,
							p.getOffset(), p.getPageSize(), title);

			VOPOConverter<ServiceEvaluationVO, ServiceEvaluationPO> cvt = new VOPOConverter<ServiceEvaluationVO, ServiceEvaluationPO>(
					ServiceEvaluationVO.class, ServiceEvaluationPO.class);
			p.setCurPageData(cvt.fromPOList(lst));
			return p;
		} catch (Exception e) {
			log.error("queryAppRegOrdersByParas出现错误：", e);
			return null;
		}

	}

	@Override
	public List<DepartmentNameVO> getAllDepartmentName() {
		List<DepartmentNamePO> lst = this.departmentNameDAO.getAllDepartmentName();
		VOPOConverter<DepartmentNameVO, DepartmentNamePO> cvt 
		= new VOPOConverter<DepartmentNameVO, DepartmentNamePO>(DepartmentNameVO.class, DepartmentNamePO.class);
	List<DepartmentNameVO> lstVO = cvt.fromPOList(lst);
	return lstVO;
	}

	@Override
	public List<DoctorNameVO> getDoctorName(String departmentId) {
		List<DoctorNamePO> lst = this.departmentNameDAO.findDoctorNameByType(departmentId);
		List<DoctorNameVO> lst2 = new ArrayList<DoctorNameVO>();
		for (DoctorNamePO i: lst){
			DoctorNameVO v = new DoctorNameVO();
			v.setDepartmentId(i.getDepartmentId());
			v.setDoctorId(i.getDoctorId());
			v.setDoctorName(i.getDoctorName());
			
			lst2.add(v);
		}
		return lst2;
	}

	@Override
	public JSONObject findQuestionsById(
			String questionnaireId, String appRegOrderId) {
		List<ShowQuesPO> show=serviceEvaluationDAO.findQuestionsById(questionnaireId,appRegOrderId);
		JSONObject q_json = new JSONObject();
		JSONArray questions_array = new JSONArray();
		ShowQuesPO shpo;
		
		int lastId = -1;
		int thisId = -1;
		
		for (int i = 0; i < show.size(); i++) {
			shpo = show.get(i);
			thisId = shpo.getId();
			if(thisId == lastId && i != 0) {
				JSONObject shpo_json = questions_array.getJSONObject(questions_array.size() - 1);
				String usggest = shpo_json.getString("suggest");
				usggest =  usggest + "," + shpo.getSuggest();
				shpo_json.put("suggest", usggest);
			} else {
				JSONObject shpo_json = new JSONObject();
				/*shpo_json.put("title", shpo.getTitle());*/
				shpo_json.put("id",shpo.getId());
				shpo_json.put("subject", shpo.getSubject());
				shpo_json.put("type", shpo.getType());
				shpo_json.put("suggest", shpo.getSuggest());
				JSONArray options_array = new JSONArray();
				if(shpo.getType() == 0 || shpo.getType() == 1){
					if(shpo.getOption0() != null){
						options_array.add(shpo.getOption0());
					}
					if(shpo.getOption1() != null){
						options_array.add(shpo.getOption1());
					}
					if(shpo.getOption2() != null){
						options_array.add(shpo.getOption2());
					}
					if(shpo.getOption3() != null){
						options_array.add(shpo.getOption3());
					}
					if(shpo.getOption4() != null){
						options_array.add(shpo.getOption4());
					}
					if(shpo.getOption5() != null){
						options_array.add(shpo.getOption5());
					}
					if(shpo.getOption6() != null){
						options_array.add(shpo.getOption6());
					}
					if(shpo.getOption7() != null){
						options_array.add(shpo.getOption7());
					}
					if(shpo.getOption8() != null){
						options_array.add(shpo.getOption8());
					}
					if(shpo.getOption9() != null){
						options_array.add(shpo.getOption9());
					}
				}
				shpo_json.put("options", options_array);
				questions_array.add(shpo_json);
			}
			lastId = thisId;
		}
		q_json.put("questions", questions_array);
		//System.out.println("**"+q_json.toString());
		return q_json;
	}
}