package com.catic.mobilehos.utils;

import java.text.NumberFormat;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

public abstract class CommonUtils {
	
	public static Date addTime(Date date, int hours, int minutes, int seconds){
		date = DateUtils.addHours(date, hours);
		date = DateUtils.addMinutes(date, minutes);
		date = DateUtils.addSeconds(date, seconds);
		return date;
	}
	
	/**
	 * 把字符串值为null的转化为空字符串，如果不是null则返回原字符串
	 * @return 如果为null返回""，否则的返回原字符串
	 */
	public static String nullStringToEmpty(Object str){
		return str == null ? "" : str.toString();
	}
	
	/**
	 * num1除以num2并转成保留2位小数的百分比
	 * @param num1 被除数
	 * @param num2 除数
	 * @return 保留2位小数的百分比
	 */
	public static String getDividePercentage(double num1, double num2) {
		double percent = (double) num1 / (double) num2;
		return getPercentage(percent);
	}
	
	/**
	 * double转为保留2位小数的百分比
	 * @param num 准备转换的数字
	 * @return 百分比
	 */
	public static String getPercentage(double num) {
		// 获取格式化对象
		NumberFormat nt = NumberFormat.getPercentInstance();
		// 设置百分数精确度2即保留两位小数
		nt.setMinimumFractionDigits(2);
		// 最后格式化并输出
		return nt.format(num);
	}
	
}
