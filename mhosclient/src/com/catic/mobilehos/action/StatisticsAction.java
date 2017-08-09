package com.catic.mobilehos.action;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.catic.mobilehos.service.StatisticsService;
import com.catic.mobilehos.service.vo.TitleVO;

public class StatisticsAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private StatisticsService statisticsService;

	private String dep;
	private String doc;
	private String start;
	private String end;
	private List<TitleVO> title;
	private String qnId;

	public void getMemberSeries() {
		try {
			List<Object> series = statisticsService.getMemberSeries(start, end);
			String result = series.toString().replaceAll("=", ":");
			writeString(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getRegistrationSeries() {
		String type = request.getParameter("type");
		List<Object> series = statisticsService.getRegistrationSeries(start,
				end,type);
		String result = series.toString().replaceAll("=", ":");
		writeString(result);
	}

	public void getRegistrationException() {
		List<Object> series = statisticsService.getRegistrationException(start,
				end);
		String result = series.toString().replaceAll("=", ":");
		writeString(result);
	}

	public String getChartTableView() {
		Map<String, Object> data = statisticsService.chartTableView();
		request.setAttribute("data", data);
		return SUCCESS;
	}

	public String getChartTableData() {
		List<Map<String, Object>> data = statisticsService.chartTableData(dep,
				doc, start, end);
		request.setAttribute("data", data);
		return SUCCESS;
	}

	public void getTitleJson() {
		try {
			List<TitleVO> title = this.statisticsService.getTitleJson();
			JSONArray jarr = JSONArray.fromObject(title);
			this.writeJSON(jarr);
		} catch (Exception e) {
			log.error(null, e);
		}

	}

	public String getEvaluateStatistics() {
		JSONObject json = statisticsService.findQuestionsById(qnId,start,end);
		request.setAttribute("data", json);
		return SUCCESS;

	}

	public StatisticsService getStatisticsService() {
		return statisticsService;
	}

	public void setStatisticsService(StatisticsService statisticsService) {
		this.statisticsService = statisticsService;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getDep() {
		return dep;
	}

	public void setDep(String dep) {
		this.dep = dep;
	}

	public String getDoc() {
		return doc;
	}

	public void setDoc(String doc) {
		this.doc = doc;
	}

	public List<TitleVO> getTitle() {
		return title;
	}

	public void setTitle(List<TitleVO> title) {
		this.title = title;
	}

	public String getQnId() {
		return qnId;
	}

	public void setQnId(String qnId) {
		this.qnId = qnId;
	}

}
