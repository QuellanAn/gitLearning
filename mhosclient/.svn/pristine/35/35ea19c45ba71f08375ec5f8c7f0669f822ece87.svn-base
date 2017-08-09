package com.catic.mobilehos.service;

import com.catic.mobilehos.po.HelpPO;
import com.catic.mobilehos.service.vo.HelpVO;
import com.catic.mobilehos.service.vo.Page;

public interface HelpAnswerService {
	public Page<HelpVO> getAllHelpList(String question,String createPeople,java.sql.Date startDate,
			java.sql.Date endDate,int page, int pageSize);
	
	HelpPO getHelpAnswer(int helpId);
	
	void updateHelp(HelpPO helpPo);
	
	ServiceResult addHelp(HelpPO helpPo);
	
	void deleteHelp(int helpId);
}
