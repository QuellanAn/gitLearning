package com.catic.mobilehos.service;

public enum AppRegOrderStatus {
	
	
	
	/**
	 * 初始有效状态
	 */
	valid("011"), 
	
	/**
	 * 已取消
	 */
	cancel("021"),
	
	/**
	 * 已取号
	 */
	taken("041"),
	
	/**
	 * 已停诊
	 */
	stop("031"),
	
	/**
	 * 已付费
	 */
	paid("051");
	
	private String value;
	
	private AppRegOrderStatus(String value){
		this.value = value;
	}
	
	public String getValue(){
		return this.value;
	}
	
	public static AppRegOrderStatus getInstance(String s){
		if (s.equals(valid.value)){
			return valid;
		}else if (s.equals(cancel.value)){
			return cancel;
		}else if (s.equals(taken.value)){
			return taken;
		}else if (s.equals(stop.value)){
			return stop;
		}else if (s.equals(paid)){
			return paid;
		}
		throw new java.lang.IllegalArgumentException("无效参数！");
		
	}
	
	
	

}
