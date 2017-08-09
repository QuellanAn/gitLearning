package com.catic.mobilehos.dao;

import java.util.List;
import java.util.Map;

import com.catic.mobilehos.po.AnswerPO;
import com.catic.mobilehos.po.FeedBackPO;
import com.catic.mobilehos.po.ShowQuesPO;
import com.catic.mobilehos.po.TitlePO;

public interface StatisticsDao {

	public List<Object> getMemberSeries(String start, String end);

	public List<Object> getRegistrationSeries(String start, String end, String type);

	public List<Object> getRegistrationException(String start, String end);

	public Map<String, Object> chartTableView();

	public List<Map<String, Object>> chartTableData(String dep, String doc, String start, String end);

	public List<TitlePO> getTitle();

	public List<ShowQuesPO> findQuestionsById(String qnId);

	public List<AnswerPO> findFeedBackById(String qnId, String start,
			String end);
}
