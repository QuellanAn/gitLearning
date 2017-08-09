package com.catic.mobilehos.service;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.catic.mobilehos.service.vo.TitleVO;

public interface StatisticsService {

	public List<Object> getMemberSeries(String start, String end);

	public List<Object> getRegistrationSeries(String start, String end, String type);

	public List<Object> getRegistrationException(String start, String end);

	public Map<String, Object> chartTableView();

	public List<Map<String, Object>> chartTableData(String dep, String doc, String start, String end);

	public List<TitleVO> getTitleJson();

	public JSONObject findQuestionsById(String qnId, String start, String end);
}
