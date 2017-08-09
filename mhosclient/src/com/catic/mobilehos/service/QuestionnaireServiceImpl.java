package com.catic.mobilehos.service;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.catic.mobilehos.dao.QuestionnaireDAO;
import com.catic.mobilehos.po.QuestionPO;
import com.catic.mobilehos.po.QuestionnairePO;
import com.catic.mobilehos.service.vo.Page;
import com.catic.mobilehos.service.vo.QuestionnaireVO;
import com.catic.mobilehos.service.vo.VOPOConverter;

/**
 * 
 * dengshenghui
 * 
 * @author
 * 
 */
public class QuestionnaireServiceImpl implements QuestionnaireService {
	// private Log log = LogFactory.getLog(this.getClass());

	private QuestionnaireDAO questionnaireDAO;

	public QuestionnaireDAO getQuestionnaireDAO() {
		return questionnaireDAO;
	}

	public void setQuestionnaireDAO(QuestionnaireDAO questionnaireDAO) {
		this.questionnaireDAO = questionnaireDAO;
	}

	/**
	 * 获取分页列表
	 */
	@Override
	public Page<QuestionnaireVO> getQuestionnairePObyParas(int id, int status, int curPage, int pageSize, String title, String startdate, String enddate) {
		// TODO Auto-generated method stub
		int totalRecord = questionnaireDAO.getCountQuestionnairePO(id, status, title, startdate, enddate);

		Page<QuestionnaireVO> page = new Page<QuestionnaireVO>(totalRecord, pageSize, curPage);
		List<QuestionnairePO> list = questionnaireDAO.getQuestionnairePOsbyParas(id, status, page.getOffset(), page.getPageSize(), title, startdate, enddate);
		VOPOConverter<QuestionnaireVO, QuestionnairePO> converter = new VOPOConverter<QuestionnaireVO, QuestionnairePO>(QuestionnaireVO.class,
				QuestionnairePO.class);
		List<QuestionnaireVO> liUsersVOs = converter.fromPOList(list);
		page.setCurPageData(liUsersVOs);
		return page;
	}

	@Override
	public int createQuestionnairePO(QuestionnairePO questionnairePO) {
		// TODO Auto-generated method stub
		return questionnaireDAO.createQuestionnairePO(questionnairePO);
	}

	@Override
	public Map<String, Object> submitQuestions(int id, JSONArray json_ques) {
		// TODO Auto-generated method stub
		//System.out.println(json_ques.toString());
		//System.out.println("service si id   " + id);

		QuestionPO quesPO = new QuestionPO();

		for (int i = 0; i < json_ques.size(); i++) {
			JSONObject j = json_ques.getJSONObject(i);
			if (j.getInt("type") == 2) {
				quesPO.setId(id);
				quesPO.setSubject(j.getString("subject"));
				quesPO.setId(j.getInt("type"));
			} else {
				quesPO.setId(id);
				quesPO.setSubject(j.getString("subject"));
				quesPO.setId(j.getInt("type"));
				String option = j.getString("options");
				String[] questions = option.split(",");
				for (String q : questions) {
					if (quesPO.getOption0() == null) {
						quesPO.setOption0(q);
					} else if (quesPO.getOption1() == null) {
						quesPO.setOption1(q);
					} else if (quesPO.getOption2() == null) {
						quesPO.setOption3(q);
					} else if (quesPO.getOption3() == null) {
						quesPO.setOption3(q);
					} else if (quesPO.getOption4() == null) {
						quesPO.setOption4(q);
					} else if (quesPO.getOption5() == null) {
						quesPO.setOption5(q);
					} else if (quesPO.getOption6() == null) {
						quesPO.setOption6(q);
					} else if (quesPO.getOption7() == null) {
						quesPO.setOption7(q);
					} else if (quesPO.getOption8() == null) {
						quesPO.setOption8(q);
					} else if (quesPO.getOption9() == null) {
						quesPO.setOption9(q);
					}
				}
			}
			questionnaireDAO.submitQuestions(quesPO);
		}
		return null;
	}

	@Override
	public void deleteQuestionnaire(int id) {
		// TODO Auto-generated method stub
		questionnaireDAO.deleteQuestionnaire(id);
	}

	@Override
	public JSONObject getQuestionnaireDetail(int qnId) {
		// TODO Auto-generated method stub
		QuestionnairePO questionnaire = questionnaireDAO.getQuestionnaireDetail(qnId);

		if (questionnaire != null) {
			List<QuestionPO> questions = questionnaireDAO.getQuestionsByQuestionnaireId(qnId);
			if (questions.size() > 0) {
				JSONObject q_json = new JSONObject();
				q_json.put("questionnaire_id", questionnaire.getId());
				q_json.put("title", questionnaire.getTitle());
				q_json.put("sub_title", questionnaire.getSub_title());
				JSONArray questions_array = new JSONArray();
				QuestionPO qpo;
				for (int i = 0; i < questions.size(); i++) {
					qpo = questions.get(i);
					JSONObject qpo_json = new JSONObject();
					qpo_json.put("question_id", qpo.getId());
					qpo_json.put("subject", qpo.getSubject());
					qpo_json.put("type", qpo.getType());
					JSONArray options_array = new JSONArray();
					if (qpo.getType() == 0 || qpo.getType() == 1) {
						if (qpo.getOption0() != null) {
							options_array.add(qpo.getOption0());
						}
						if (qpo.getOption1() != null) {
							options_array.add(qpo.getOption1());
						}
						if (qpo.getOption2() != null) {
							options_array.add(qpo.getOption2());
						}
						if (qpo.getOption3() != null) {
							options_array.add(qpo.getOption3());
						}
						if (qpo.getOption4() != null) {
							options_array.add(qpo.getOption4());
						}
						if (qpo.getOption5() != null) {
							options_array.add(qpo.getOption5());
						}
						if (qpo.getOption6() != null) {
							options_array.add(qpo.getOption7());
						}
						if (qpo.getOption8() != null) {
							options_array.add(qpo.getOption8());
						}
						if (qpo.getOption9() != null) {
							options_array.add(qpo.getOption9());
						}
					}
					qpo_json.put("options", options_array);
					questions_array.add(qpo_json);
				}
				q_json.put("questions", questions_array);
				// System.out.println(q_json.toString());
				return q_json;

			}
		}
		return null;
	}

	/**
	 * 增加调查问卷和问题
	 */
	@Override
	public void saveQuestionnaire(String adminUserName, JSONObject json_qn) {
		// TODO Auto-generated method stub
		// 获取问卷id
		String title = json_qn.getString("title");
		String sub_title = json_qn.getString("sub_title");
		int status = json_qn.getInt("status");

		QuestionnairePO questionnaire = new QuestionnairePO();
		questionnaire.setUserName(adminUserName);
		questionnaire.setTitle(title);
		questionnaire.setSub_title(sub_title);
		questionnaire.setStatus(status);

		int naireid = questionnaireDAO.createQuestionnaire(questionnaire);

		// 增加问题
		String questions = json_qn.getString("questions");
		JSONArray json_ques = JSONArray.fromObject(questions);

		if (json_ques != null && json_ques.isArray() && !json_ques.isEmpty()) {
			for (int i = 0; i < json_ques.size(); i++) {
				QuestionPO question = new QuestionPO();
				question.setQuestionnaireId(naireid);
				JSONObject j = json_ques.getJSONObject(i);
				if (j.getInt("type") == 2) {
					question.setSubject(j.getString("subject"));
					question.setType(j.getInt("type"));
				} else {
					question.setSubject(j.getString("subject"));
					question.setType(j.getInt("type"));
					String option = j.getString("options");
					JSONArray options = JSONArray.fromObject(option);
					String op;
					for (int k = 0; k < options.size(); k++) {
						op = options.getString(k);
						if (question.getOption0() == null) {
							question.setOption0(op);
						} else if (question.getOption1() == null) {
							question.setOption1(op);
						} else if (question.getOption2() == null) {
							question.setOption2(op);
						} else if (question.getOption3() == null) {
							question.setOption3(op);
						} else if (question.getOption4() == null) {
							question.setOption4(op);
						} else if (question.getOption5() == null) {
							question.setOption5(op);
						} else if (question.getOption6() == null) {
							question.setOption6(op);
						} else if (question.getOption7() == null) {
							question.setOption7(op);
						} else if (question.getOption8() == null) {
							question.setOption8(op);
						} else if (question.getOption9() == null) {
							question.setOption9(op);
						}
					}
				}
				questionnaireDAO.addQuestions(question);
			}
		}

	}

	// 获取修改调查界面的ID信息
	/*
	 * @Override public QuestionnairePO getQuestionnaire(int id) { // TODO
	 * Auto-generated method stub return questionnaireDAO.getQuestionnaire(id);
	 * }
	 */

	@Override
	public JSONObject getQuestionnaire(int id) {
		// TODO Auto-generated method stub
		QuestionnairePO questionnaire = questionnaireDAO.getQuestionnaireById(id);
		if (questionnaire != null) {
			List<QuestionPO> questions = questionnaireDAO.getQuestionsByQuestionnaireId(questionnaire.getId());
			// if (questions.size()>0){
			JSONObject q_json = new JSONObject();
			q_json.put("id", questionnaire.getId());
			q_json.put("title", questionnaire.getTitle());
			q_json.put("sub_title", questionnaire.getSub_title());
			q_json.put("status", questionnaire.getStatus());
			JSONArray questions_array = new JSONArray();
			QuestionPO qpo;
			for (int i = 0; i < questions.size(); i++) {
				qpo = questions.get(i);
				JSONObject qpo_json = new JSONObject();
				qpo_json.put("subject", qpo.getSubject());
				qpo_json.put("type", qpo.getType());
				JSONArray options_array = new JSONArray();
				if (qpo.getType() == 0 || qpo.getType() == 1) {
					if (qpo.getOption0() != null) {
						options_array.add(qpo.getOption0());
					}
					if (qpo.getOption1() != null) {
						options_array.add(qpo.getOption1());
					}
					if (qpo.getOption2() != null) {
						options_array.add(qpo.getOption2());
					}
					if (qpo.getOption3() != null) {
						options_array.add(qpo.getOption3());
					}
					if (qpo.getOption4() != null) {
						options_array.add(qpo.getOption4());
					}
					if (qpo.getOption5() != null) {
						options_array.add(qpo.getOption5());
					}
					if (qpo.getOption6() != null) {
						options_array.add(qpo.getOption7());
					}
					if (qpo.getOption8() != null) {
						options_array.add(qpo.getOption8());
					}
					if (qpo.getOption9() != null) {
						options_array.add(qpo.getOption9());
					}
				}
				qpo_json.put("options", options_array);
				questions_array.add(qpo_json);
			}
			q_json.put("questions", questions_array);
			// System.out.println(q_json.toString());
			return q_json;
		}

		return null;
	}

	/**
	 * 更新调查问卷和 问题
	 */
	@Override
	public void updateQuestionnaire(String adminUserName, JSONObject json_qn) {
		// TODO Auto-generated method stub
		int id = json_qn.getInt("id");
		String title = json_qn.getString("title");
		String sub_title = json_qn.getString("sub_title");
		int status = json_qn.getInt("status");

		QuestionnairePO questionnaire = new QuestionnairePO();
		questionnaire.setUserName(adminUserName);
		questionnaire.setId(id);
		questionnaire.setTitle(title);
		questionnaire.setSub_title(sub_title);
		questionnaire.setStatus(status);
		questionnaireDAO.updataQuestionnaire(questionnaire);
		questionnaireDAO.deleteQuestions(questionnaire);

		// 更新问题
		String questions = json_qn.getString("questions");
		//System.out.println(questions);
		JSONArray json_ques = JSONArray.fromObject(questions);

		for (int i = 0; i < json_ques.size(); i++) {
			QuestionPO question = new QuestionPO();
			question.setQuestionnaireId(id);
			//System.out.println(id);
			JSONObject j = json_ques.getJSONObject(i);
			if (j.getInt("type") == 2) {
				question.setSubject(j.getString("subject"));
				question.setType(j.getInt("type"));
			} else {
				question.setQuestionnaireId(id);
				question.setSubject(j.getString("subject"));
				question.setType(j.getInt("type"));
				String option = j.getString("options");
				JSONArray options = JSONArray.fromObject(option);
				String op;
				for (int k = 0; k < options.size(); k++) {
					op = options.getString(k);
					if (question.getOption0() == null) {
						question.setOption0(op);
					} else if (question.getOption1() == null) {
						question.setOption1(op);
					} else if (question.getOption2() == null) {
						question.setOption2(op);
					} else if (question.getOption3() == null) {
						question.setOption3(op);
					} else if (question.getOption4() == null) {
						question.setOption4(op);
					} else if (question.getOption5() == null) {
						question.setOption5(op);
					} else if (question.getOption6() == null) {
						question.setOption6(op);
					} else if (question.getOption7() == null) {
						question.setOption7(op);
					} else if (question.getOption8() == null) {
						question.setOption8(op);
					} else if (question.getOption9() == null) {
						question.setOption9(op);
					}
					
				}
			}questionnaireDAO.updataQuestions(question);
			
		}
	}
}
