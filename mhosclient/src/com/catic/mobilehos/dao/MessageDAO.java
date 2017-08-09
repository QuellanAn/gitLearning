package com.catic.mobilehos.dao;

import java.sql.Timestamp;
import java.util.List;

import com.catic.mobilehos.po.MessagePO;

public interface MessageDAO {
	
	List<MessagePO> findMsgsByParas(String typeCode, String phone, Integer status
			, Timestamp startDate, Timestamp endDate, int offset, int length);
	
	
	int countMsgsByParas(String typeCode, String phone,
			Integer status, Timestamp startDate, Timestamp endDate);


}
