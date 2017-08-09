package com.catic.mobilehos.service;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.catic.mobilehos.po.QuestionnairePO;
import com.catic.mobilehos.service.vo.Page;
import com.catic.mobilehos.service.vo.QuestionnaireVO;

/**
 * 调查问卷
 * 
 * @author dengshenghui
 * 
 */
public interface QuestionnaireService {

	/**
	 * 根据分页获取会员
	 * 
	 * @param questionnaireid
	 * @param status
	 * @param offset
	 * @param length
	 * @return
	 */
	Page<QuestionnaireVO> getQuestionnairePObyParas(int id, int status, int offset, 
			int length, String title, String startdate, String enddate);

	
	int createQuestionnairePO(QuestionnairePO questionnairePO);

	
	Map<String, Object> submitQuestions(int id, JSONArray json_ques);


	void deleteQuestionnaire(int id);


	JSONObject getQuestionnaireDetail(int qnId);
/**
 * 增加调查问卷和问题
 * @param adminUserName
 * @param json_qn
 */
	void saveQuestionnaire(String adminUserName, JSONObject json_qn);

/**
 * 获取修改调查界面
 * @param id
 * @return
 */

	JSONObject getQuestionnaire(int id);

/**
 * 修改调查问卷和问题
 * @param adminUserName
 * @param json_qn
 */
void updateQuestionnaire(String adminUserName, JSONObject json_qn);


}
