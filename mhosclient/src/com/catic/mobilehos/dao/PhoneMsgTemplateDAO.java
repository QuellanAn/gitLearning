package com.catic.mobilehos.dao;

import com.catic.mobilehos.po.PhoneMsgTemplatePO;

public interface PhoneMsgTemplateDAO {
	
	PhoneMsgTemplatePO getPhoneMsgTemplate(String busCode);
	
	void addOrUpdPhoneMsgTemplate(PhoneMsgTemplatePO t);

}
