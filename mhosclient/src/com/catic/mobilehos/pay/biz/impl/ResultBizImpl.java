package com.catic.mobilehos.pay.biz.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.catic.mobilehos.pay.biz.IResultBiz;
import com.catic.mobilehos.pay.entity.Result;


@Service("resultBiz")
public class ResultBizImpl implements IResultBiz {

	private Map<Integer, Result> results = new ConcurrentHashMap<Integer, Result>();

	public ResultBizImpl() {

		this.results.put(SUCCESS, new Result(SUCCESS, "请求成功"));
		
		this.results.put(FAIL, new Result(FAIL, "请求失败"));
		
		this.results.put(SYSTEM_BUSY, new Result(SYSTEM_BUSY, "系统繁忙"));

		this.results.put(PARAMETER_INCORRENT, new Result(PARAMETER_INCORRENT, "请求参数有误"));
		
		this.results.put(METHOD_POST, new Result(METHOD_POST, "请求方式有误"));
		
		this.results.put(IDCARD_INCORRENT, new Result(IDCARD_INCORRENT, "证件号码有误"));
		
		this.results.put(ALREADY_BIND, new Result(ALREADY_BIND, "就诊人已经绑定"));
		
		this.results.put(INFO_NOT_EXIST, new Result(INFO_NOT_EXIST, "暂无相关记录"));
		
		this.results.put(PASSWORD_ERRO, new Result(PASSWORD_ERRO, "用户不存在或者密码错误"));
		
		this.results.put(USER_NO, new Result(USER_NO, "用户不存在"));
		
		this.results.put(MENU_NO, new Result(MENU_NO, "账号没有权限，请向管理员申请权限！"));
	}

	/**
	 * 根据错误代码取得错误结果
	 * 
	 * @param resultCode
	 * @return
	 */
	public Result getResult(int code) {
		Result result = results.get(code);
		if (result != null) {
			result.setData(null);
			return result;
		}

		// 默认返回系统繁忙
		result = this.results.get(SYSTEM_BUSY);

		return result;
	}
	
	/**
	 * 根据错误代码取得错误结果
	 * 
	 * @param code
	 * @param response
	 * @return
	 */
	public Result getResult(int code, String response) {
		Result result = results.get(code);
		if (result != null) {
			result.setData(response);
			return result;
		}

		// 默认返回系统繁忙
		result = this.results.get(SYSTEM_BUSY);

		return result;
	}

}
