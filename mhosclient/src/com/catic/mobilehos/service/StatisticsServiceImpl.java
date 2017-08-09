package com.catic.mobilehos.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.catic.mobilehos.dao.StatisticsDao;
import com.catic.mobilehos.po.AnswerPO;
import com.catic.mobilehos.po.FeedBackPO;
import com.catic.mobilehos.po.ShowQuesPO;
import com.catic.mobilehos.po.TitlePO;
import com.catic.mobilehos.service.vo.TitleVO;
import com.catic.mobilehos.service.vo.VOPOConverter;

public class StatisticsServiceImpl implements StatisticsService {

	private StatisticsDao statisticsDao;

	public List<Object> getMemberSeries(String start, String end) {

		return statisticsDao.getMemberSeries(start, end);
	}

	@Override
	public List<Object> getRegistrationSeries(String start, String end, String type) {

		return statisticsDao.getRegistrationSeries(start, end, type);
	}

	@Override
	public List<Object> getRegistrationException(String start, String end) {

		return statisticsDao.getRegistrationException(start, end);
	}

	@Override
	public Map<String, Object> chartTableView() {
		return statisticsDao.chartTableView();
	}

	@Override
	public List<Map<String, Object>> chartTableData(String dep, String doc, String start, String end) {
		List<Map<String, Object>> data = statisticsDao.chartTableData(dep, doc, start, end);
		Map<String, Object> row = null;
		String lastDepId = null;
		String lastDepName = null;
		String thisDepId = null;
		String thisDepName = null;
		boolean flag = false;
		int dep_011 = 0, dep_021 = 0, dep_031 = 0, dep_041 = 0, dep_051 = 0, dep_061 = 0, dep_count = 0;
		for (int i = 0; i < data.size(); i++) {
			row = data.get(i);
			thisDepId = (String) row.get("department_id");
			thisDepName = (String) row.get("department_name");
			if (!thisDepId.equals(lastDepId) && i != 0) {
				Map<String, Object> newRow = new HashMap<String, Object>();
				newRow.put("department_id", lastDepId);
				newRow.put("department_name", lastDepName);
				newRow.put("doctor_id", "countRow");
				newRow.put("doctor_name", "[" + lastDepName + "] 小结");
				newRow.put("011", dep_011);
				newRow.put("021", dep_021);
				newRow.put("031", dep_031);
				newRow.put("041", dep_041);
				newRow.put("051", dep_051);
				newRow.put("061", dep_061);
				newRow.put("count", dep_count);
				data.add(i, newRow);
				flag = false;
				dep_011 = 0;
				dep_021 = 0;
				dep_031 = 0;
				dep_041 = 0;
				dep_051 = 0;
				dep_061 = 0;
				dep_count = 0;
			}
			dep_011 += Integer.parseInt(row.get("011").toString());
			dep_021 += Integer.parseInt(row.get("021").toString());
			dep_031 += Integer.parseInt(row.get("031").toString());
			dep_041 += Integer.parseInt(row.get("041").toString());
			dep_051 += Integer.parseInt(row.get("051").toString());
			dep_061 += Integer.parseInt(row.get("061").toString());
			dep_count += Integer.parseInt(row.get("count").toString());
			flag = true;
			lastDepId = thisDepId;
			lastDepName = thisDepName;
		}
		if (flag) {
			Map<String, Object> newRow = new HashMap<String, Object>();
			newRow.put("department_id", lastDepId);
			newRow.put("department_name", lastDepName);
			newRow.put("doctor_id", "countRow");
			newRow.put("doctor_name", "[" + lastDepName + "] 小结");
			newRow.put("011", dep_011);
			newRow.put("021", dep_021);
			newRow.put("031", dep_031);
			newRow.put("041", dep_041);
			newRow.put("051", dep_051);
			newRow.put("061", dep_061);
			newRow.put("count", dep_count);
			data.add(newRow);
		}
		return data;
	}

	public StatisticsDao getStatisticsDao() {
		return statisticsDao;
	}

	public void setStatisticsDao(StatisticsDao statisticsDao) {
		this.statisticsDao = statisticsDao;
	}

	@Override
	public List<TitleVO> getTitleJson() {
		List<TitlePO> lst = this.statisticsDao.getTitle();
		VOPOConverter<TitleVO, TitlePO> cvt 
		= new VOPOConverter<TitleVO, TitlePO>(TitleVO.class, TitlePO.class);
	List<TitleVO> lstVO = cvt.fromPOList(lst);
	return lstVO;
	}



	@Override
	public JSONObject findQuestionsById(String qnId, String start, String end) {
		try {
			List<ShowQuesPO> show=this.statisticsDao.findQuestionsById(qnId);
			List<AnswerPO> answer = statisticsDao.findFeedBackById(qnId,start,end);
		
			JSONObject q_json = new JSONObject();
			JSONArray questions_array = new JSONArray();
			ShowQuesPO shpo;
			AnswerPO anPo; 
			for(int i = 0; i< show.size(); i++){
				if (answer.size() > 0 ) {
					shpo = show.get(i);
					anPo = answer.get(i);
					JSONObject shpo_json = new JSONObject();
					shpo_json.put("id", shpo.getId());
					shpo_json.put("type", shpo.getType());
					shpo_json.put("subject", shpo.getSubject());
					
			
					JSONArray options_array = new JSONArray();
					JSONArray answers_array = new JSONArray();
					if(shpo.getType() == 0 || shpo.getType() == 1){
						if(shpo.getOption0() != null){
							options_array.add(shpo.getOption0());
							answers_array.add(anPo.getSuggest0());
						}
						if(shpo.getOption1() != null){
							options_array.add(shpo.getOption1());
							answers_array.add(anPo.getSuggest1());
						}
						if(shpo.getOption2() != null){
							options_array.add(shpo.getOption2());
							answers_array.add(anPo.getSuggest2());
						}
						if(shpo.getOption3() != null){
							options_array.add(shpo.getOption3());
							answers_array.add(anPo.getSuggest3());
						}
						if(shpo.getOption4() != null){
							options_array.add(shpo.getOption4());
							answers_array.add(anPo.getSuggest4());
						}
						if(shpo.getOption5() != null){
							options_array.add(shpo.getOption5());
							answers_array.add(anPo.getSuggest5());
						}
						if(shpo.getOption6() != null){
							options_array.add(shpo.getOption6());
							answers_array.add(anPo.getSuggest6());
						}
						if(shpo.getOption7() != null){
							options_array.add(shpo.getOption7());
							answers_array.add(anPo.getSuggest7());
						}
						if(shpo.getOption8() != null){
							options_array.add(shpo.getOption8());
							answers_array.add(anPo.getSuggest8());
						}
						if(shpo.getOption9() != null){
							options_array.add(shpo.getOption9());
							answers_array.add(anPo.getSuggest9());
						}
					}
					shpo_json.put("options", options_array);
					shpo_json.put("answers", answers_array);
					questions_array.add(shpo_json);
				}else{
					shpo = show.get(i);
//					anPo = answer.get(i);
					JSONObject shpo_json = new JSONObject();
					shpo_json.put("id", shpo.getId());
					shpo_json.put("type", shpo.getType());
					shpo_json.put("subject", shpo.getSubject());
					
			
					JSONArray options_array = new JSONArray();
					JSONArray answers_array = new JSONArray();
					if(shpo.getType() == 0 || shpo.getType() == 1){
						if(shpo.getOption0() != null){
							options_array.add(shpo.getOption0());
							answers_array.add(0);
						}
						if(shpo.getOption1() != null){
							options_array.add(shpo.getOption1());
							answers_array.add(0);
						}
						if(shpo.getOption2() != null){
							options_array.add(shpo.getOption2());
							answers_array.add(0);
						}
						if(shpo.getOption3() != null){
							options_array.add(shpo.getOption3());
							answers_array.add(0);
						}
						if(shpo.getOption4() != null){
							options_array.add(shpo.getOption4());
							answers_array.add(0);
						}
						if(shpo.getOption5() != null){
							options_array.add(shpo.getOption5());
							answers_array.add(0);
						}
						if(shpo.getOption6() != null){
							options_array.add(shpo.getOption6());
							answers_array.add(0);
						}
						if(shpo.getOption7() != null){
							options_array.add(shpo.getOption7());
							answers_array.add(0);
						}
						if(shpo.getOption8() != null){
							options_array.add(shpo.getOption8());
							answers_array.add(0);
						}
						if(shpo.getOption9() != null){
							options_array.add(shpo.getOption9());
							answers_array.add(0);
						}
					}
					shpo_json.put("options", options_array);
					shpo_json.put("answers", answers_array);
					questions_array.add(shpo_json);
				}
				
				
			}
			q_json.put("questions", questions_array);
			return q_json;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

}
