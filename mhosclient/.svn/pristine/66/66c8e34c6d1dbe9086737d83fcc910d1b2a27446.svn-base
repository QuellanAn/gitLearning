package com.catic.mobilehos.service.message;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息类，可以为网络消息，也可以为短信
 * @author xieweipeng
 *
 */
public class Message {
	
	/**
	 * 消息的业务代码
	 */
	private String busCode;
	
	/**
	 * 接收消息的用户ID
	 */
	private String userId;
	
	/**
	 * 接收消息的用户手机号
	 */
	private String phone;
	
	/**
	 * 是否广播消息
	 */
	private boolean broadcast;
		
	/**
	 * 消息内容中的动态参数，用于替换消息模板
	 */
	private Map<String, String> paras;
	

	public String getBusCode() {
		return busCode;
	}

	public void setBusCode(String busCode) {
		this.busCode = busCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Map<String, String> getParas() {
		return paras;
	}

	public void setParas(Map<String, String> paras) {
		this.paras = paras;
	}
	
	
	public void addPara(String key, String value){
		if (this.paras == null){
			this.paras = new HashMap<String, String>();
		}
		this.paras.put(key, value);
	}

	/**
	 * 是否广播消息
	 * @return
	 */
	public boolean isBroadcast() {
		return broadcast;
	}

	public void setBroadcast(boolean broadcast) {
		this.broadcast = broadcast;
	}
	
	


}
