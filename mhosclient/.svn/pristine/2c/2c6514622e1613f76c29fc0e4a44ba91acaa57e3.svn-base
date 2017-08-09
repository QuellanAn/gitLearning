// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   DateUtil.java

package com.catic.mobilehos.utils;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

public class DateUtil {

	private static final Logger log = Logger.getLogger(DateUtil.class);
	
	private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

	private DateUtil() {
	}

	public static Date str2Date(String str, String format)
	{
		SimpleDateFormat sdf;
		Date date;
		try{
			if (str == null || "".equals(str)){
				return null;
			}
			if (format == null || "".equals(format)){
				format = DEFAULT_FORMAT;
			}
			sdf = new SimpleDateFormat(format);
			date = null;
			date = sdf.parse(str);
			return date;
		}catch(ParseException e){
			log.error((new StringBuilder("Parse string to date error!String : ")).append(str).toString());
		}
		return null;
	}

	public static String date2Str(Date date, String format) {
		if (date == null) {
			return null;
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		}
	}

	public static String timestamp2Str(Timestamp time) {
		Date date = new Date(time.getTime());
		return date2Str(date, DEFAULT_FORMAT);
	}

	public static Timestamp str2Timestamp(String str) {
		Date date = str2Date(str, DEFAULT_FORMAT);
		return new Timestamp(date.getTime());
	}

	public static String timeToStr(Time time) {
		Date date = new Date(time.getTime());
		return date2Str(date, DEFAULT_FORMAT);
	}

	public static Time strToTime(String str) {
		Date date = str2Date(str, DEFAULT_FORMAT);
		return new Time(date.getTime());
	}

	public static java.sql.Date strToDate(String str) {
		Date date = str2Date(str, DEFAULT_FORMAT);
		return new java.sql.Date(date.getTime());
	}

	public static String DateToStr(java.sql.Date time) {
		Date date = new Date(time.getTime());
		return date2Str(date,DEFAULT_FORMAT);
	}
	
	/**
	 * 获取两个日期之间的日期
	 * @param start 开始日期
	 * @param end 结束日期
	 * @param includeFlag 返回结果是否包含开始日期和结束日期，true包含，false不包含
	 * @return 日期集合
	 */
	public static List<Date> getBetweenDates(Date start, Date end, boolean includeFlag) {
	    List<Date> result = new ArrayList<Date>();
	    if(includeFlag){
	    	result.add(start);
	    }
	    Calendar tempStart = Calendar.getInstance();
	    tempStart.setTime(start);
	    tempStart.add(Calendar.DAY_OF_YEAR, 1);
	    
	    Calendar tempEnd = Calendar.getInstance();
	    tempEnd.setTime(end);
	    while (tempStart.before(tempEnd) || (includeFlag && tempStart.equals(tempEnd))) {
	        result.add(tempStart.getTime());
	        tempStart.add(Calendar.DAY_OF_YEAR, 1);
	    }
	    return result;
	}
	
	/**
	 * 获取两个日期之间的年月
	 * @param start 开始年月
	 * @param end 结束年月
	 * @param includeFlag 返回结果是否包含开始年月和结束年月，true包含，false不包含
	 * @return 年月集合
	 */
	public static List<Date> getBetweenMonths(Date start, Date end, boolean includeFlag) {
	    List<Date> result = new ArrayList<Date>();
	    if(includeFlag){
	    	result.add(start);
	    }
	    Calendar tempStart = Calendar.getInstance();
	    tempStart.setTime(start);
	    tempStart.add(Calendar.MONTH, 1);
	    
	    Calendar tempEnd = Calendar.getInstance();
	    tempEnd.setTime(end);
	    while (tempStart.before(tempEnd) || (includeFlag && tempStart.equals(tempEnd))) {
	        result.add(tempStart.getTime());
	        tempStart.add(Calendar.MONTH, 1);
	    }
	    return result;
	}
	
	/**
	 * 获取指定日期的当月第一天的日期
	 * @param date 指定日期
	 * @return 当月第一天的日期，如传入日期为2017-05-17，则返回“2017-05-01”
	 */
	public static String getFirstDayOfMonth(Date date){
		if(date == null){
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return date2Str(calendar.getTime(), "yyyy-MM-dd");
	}
	
	/**
	 * 获取指定日期的当月最后一天的日期
	 * @param date 指定日期
	 * @return 当月最后一天的日期，如传入日期为2017-05-17，则返回“2017-05-31”
	 */
	public static String getLastDayOfMonth(Date date){
		if(date == null){
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		return date2Str(calendar.getTime(), "yyyy-MM-dd");
	}
	
}
