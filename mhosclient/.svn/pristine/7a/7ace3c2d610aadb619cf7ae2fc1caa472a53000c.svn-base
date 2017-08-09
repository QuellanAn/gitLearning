package com.catic.mobilehos.service.message;

/**
 * 该类定义百度消息格式，一个实例代表一条百度消息
 * @author xieweipeng
 *
 */
public class BaiduMessage {
	
	public static String MSG_KEY_TITLE = "title";
	
	public static String MSG_KEY_DESC = "description";
	
	public static String MSG_KEY_BUSCODE = "msgbuscode";
	
	/**
	 * 消息标题
	 */
	private String title = "";
	
	/**
	 * 消息内容
	 */
	private String description = "";
	
	/**
	 * 消息类型码
	 */
	private String msgBusCode = "";

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

	public String getMsgBusCode() {
		return msgBusCode;
	}

	public void setMsgBusCode(String msgBusCode) {
		this.msgBusCode = msgBusCode;
	}

	
	@Override
	public String toString() {
		// 转换成百度消息格式
		return "{\"title\":\"" + title + "\",\"description\":\"" 
				+ description + "\"" + ", \"msgbuscode\":\"" + msgBusCode + "\"" + "}";
	}
	
	

}
