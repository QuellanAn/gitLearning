package com.catic.mobilehos.service.vo;

public class MsgCfgVO {
	
	private String msgBusCode;
	
	private String msgBusName;
	
	private boolean supportPhoneMsg;
	
	private boolean supportNetMsg;
	
	private String template = "";

	public String getMsgBusCode() {
		return msgBusCode;
	}

	public void setMsgBusCode(String msgBusCode) {
		this.msgBusCode = msgBusCode;
	}
	
	public String getMsgBusName() {
		return msgBusName;
	}

	public void setMsgBusName(String msgBusName) {
		this.msgBusName = msgBusName;
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
	

}
