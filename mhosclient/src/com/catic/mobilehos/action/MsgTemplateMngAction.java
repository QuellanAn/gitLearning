package com.catic.mobilehos.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.catic.mobilehos.service.MsgMngService;
import com.catic.mobilehos.service.ServiceResult;
import com.catic.mobilehos.service.vo.MsgBusMainTypeVO;

/**
 * 消息模版配置管理
 * @author xieweipeng
 *
 */
public class MsgTemplateMngAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	
	private Log log = LogFactory.getLog(this.getClass());
	
	private List<MsgBusMainTypeVO> msgBusMainTypes;
	
	/**
	 * 消息类型编码
	 */
	private String msgBusCode;
	
	/**
	 * 是否支持短信
	 */
	private boolean supportPhoneMsg;
	
	/**
	 * 是否支持网络
	 */
	private boolean supportNetMsg;
	
	/**
	 * 消息模版
	 */
	private String template;
	
	private MsgMngService msgMngService;
	
	
	public List<MsgBusMainTypeVO> getMsgBusMainTypes() {
		return msgBusMainTypes;
	}

	public void setMsgBusMainTypes(List<MsgBusMainTypeVO> msgBusMainTypes) {
		this.msgBusMainTypes = msgBusMainTypes;
	}
	
	public MsgMngService getMsgMngService() {
		return msgMngService;
	}

	public void setMsgMngService(MsgMngService msgMngService) {
		this.msgMngService = msgMngService;
	}
	
	public String getMsgBusCode() {
		return msgBusCode;
	}

	public void setMsgBusCode(String msgBusCode) {
		this.msgBusCode = msgBusCode;
	}

	public boolean isSupportPhoneMsg() {
		return supportPhoneMsg;
	}

	public void setSupportPhoneMsg(boolean supportPhoneMsg) {
		this.supportPhoneMsg = supportPhoneMsg;
	}

	public boolean isSupportNetMsg() {
		return supportNetMsg;
	}

	public void setSupportNetMsg(boolean supportNetMsg) {
		this.supportNetMsg = supportNetMsg;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	/**
	 * 显示消息模版配置页
	 * @return
	 */
	public String showMsgTemplateCfgPage(){
		try{
			this.msgBusMainTypes = msgMngService.getAllMsgBusTypeByMainType();
			return SUCCESS;
		}catch(Exception e){
			log.error(null, e);
			return ERROR;
		}
	}
	
	/**
	 * 载入指定类型的消息模版配置
	 * @return
	 */
	public String loadMsgCfg(){
		try{
			ServiceResult sr = msgMngService.getMsgConfig(msgBusCode);
			return this.setServiceResultToJson(sr);
		}catch(Exception e){
			log.error(null, e);
			return ERROR;
		}
	}
	
	/**
	 * 保存消息模板配置
	 * @return
	 */
	public String saveMsgCfg(){
		try{
			ServiceResult sr = msgMngService.saveMsgConfig(msgBusCode, supportPhoneMsg, supportNetMsg, template);
			return this.setServiceResultToJson(sr);
		}catch(Exception e){
			log.error(null, e);
			return ERROR;
		}
	}

	

}
