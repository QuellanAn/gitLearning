package com.catic.mobilehos.service;

import java.sql.Timestamp;
import java.util.List;

import com.catic.mobilehos.service.vo.BusTypeDicVO;
import com.catic.mobilehos.service.vo.MessageVO;
import com.catic.mobilehos.service.vo.MsgBusMainTypeVO;
import com.catic.mobilehos.service.vo.Page;

public interface MsgMngService {
	
	List<BusTypeDicVO> getAllMsgBusType();
	
	Page<MessageVO> queryMsgByParas(String typeCode, String phone,
			Integer status, Timestamp startDate, Timestamp endDate, int page, int pagesize);
	
	List<MsgBusMainTypeVO> getAllMsgBusTypeByMainType();
	
	ServiceResult saveMsgConfig(String msgBusCode, boolean supportPhoneMsg
			, boolean supportNetMsg, String template);
	
	/**
	 * 获取指定类型消息的配置
	 * @param msgBusCode
	 * @return
	 */
	ServiceResult getMsgConfig(String msgBusCode);
}
