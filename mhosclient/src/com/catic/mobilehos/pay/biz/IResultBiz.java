package com.catic.mobilehos.pay.biz;

import com.catic.mobilehos.pay.entity.Result;



public interface IResultBiz {
	
	/**
	 * 请求成功
	 */
	public static final int SUCCESS = 0;
	
	/**
	 * 请求失败
	 */
	public static final int FAIL  = 1;

	/**
	 * 系统繁忙
	 */
	public static final int SYSTEM_BUSY = -1;
	
	/**
	 * 请求参数有误
	 */
	public static final int PARAMETER_INCORRENT = 2;
	
	/**
	 * 请求方式有误
	 */
	public static final int METHOD_POST = 3;

	/**
	 * 证件号码有误
	 */
	public static final int IDCARD_INCORRENT = 4;
	
	/**
	 * 就诊人已经绑定
	 */
	public static final int ALREADY_BIND  = 5;
	
	/**
	 * 暂无相关记录
	 */
	public static final int INFO_NOT_EXIST = 6;
	
	
	/**
	 * 密码错误
	 */
	public static final int PASSWORD_ERRO = 7;
	
	
	/**
	 * 用户不存在
	 */
	public static final int USER_NO = 8;
	/**
	 * 用户没有权限，可能被删除权限
	 */
	public static final int MENU_NO = 9;
	
	public Result getResult(int code);
	
	public Result getResult(int code, String response);
}
