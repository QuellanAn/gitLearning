package com.catic.mobilehos.dao;

import java.util.List;

import com.catic.mobilehos.po.HelpPO;

public interface HelpAnswerDao {
	List<HelpPO> findAll(String question,String createPeople,java.sql.Date startDate,java.sql.Date endDate,int offset, int length);
	
	int getTotalHelp(String question,String createPeople,java.sql.Date startDate,java.sql.Date endDate);

	HelpPO getHelpAnswer(int helpId);
	
	void updateHelp(HelpPO helpPo);
	
	void addHelp(HelpPO helpPo);

	void deleteHelp(int helpId);
}
