package com.catic.mobilehos.pay.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;

public class DateUtils {
	private static String[] parsePatterns = new String[] {
			"yyyy-MM-dd",
			"yyyy-MM-dd HH:mm:ss",
			"yyyyMMddHHmmss",
			"yyyy年MM月dd日",
			"yyyyMMdd" ,
			"yyyy-MM",
			"yyyy"};

	static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	static SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM");

	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 获取需要获取不同格式的日期
	 * 
	 * @param date
	 * @param i
	 * @return
	 */
	public static String formatDate(Date date, int i) {
		return DateFormatUtils.format(date, parsePatterns[i]);
	}

	/**
	 * 获取两个日期之间的时间差(单位：秒)
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 * @throws Exception
	 */
	public static String getTimeDifference(String str1, String str2)
			throws Exception {
		long date1 = sdf.parse(str1).getTime();
		long date2 = sdf.parse(str2).getTime();
		String timeDifference = String.valueOf((date2 - date1) / 1000);
		return timeDifference;

	}

	public static Date getStrToDate(String str) throws ParseException {
		DateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(str);
		return date;

	}
	
	public static Date getStrToMonth(String str) throws ParseException {
		DateFormat sdf =new SimpleDateFormat("yyyy-MM");
		Date date = sdf.parse(str);
		return date;

	}
	
	public static Date getStrToYear(String str) throws ParseException {
		DateFormat sdf =new SimpleDateFormat("yyyy");
		Date date = sdf.parse(str);
		return date;

	}
	

	public static String getFirstDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
		return dateFormat.format(cal.getTime());
	}

	public static String getLastDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));
		return dateFormat.format(cal.getTime());
	}

	public static Date getDate(int i) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());

		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date start = calendar.getTime();

		calendar.add(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.SECOND, -1);
		Date end = calendar.getTime();

		Date[] array = new Date[2];
		array[0] = start;
		array[1] = end;

		return array[i];

	}
	
	//某一天的后几天
	  public static String getDateAfter(int day){
		   Date d = new Date();
		   Calendar now =Calendar.getInstance();
		   now.setTime(d);
		   now.set(Calendar.DATE,now.get(Calendar.DATE)+day);
		   return DateFormatUtils.format(now.getTime(), parsePatterns[0]);
		  }
	  
	//某个月的前几个月
	  public static String getMonthBefore(Date d,int month){
		   Calendar now =Calendar.getInstance();
		   now.setTime(d);
		   now.set(Calendar.MONTH,now.get(Calendar.MONTH)-month);
		   return DateFormatUtils.format(now.getTime(), parsePatterns[5]);
		  }
	  
	//某一年的前几年
	  public static String getYearBefore(Date d,int year){
		   Calendar now =Calendar.getInstance();
		   now.setTime(d);
		   now.set(Calendar.YEAR,now.get(Calendar.YEAR)-year);
		   return DateFormatUtils.format(now.getTime(), parsePatterns[6]);
		  }
	  
	  
	  
	  
	  /**
	   * 查前面第几天
	   * @param days
	   * @return
	   */
	  public static String dateAdd(int days) {
		  SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd");
		  Calendar canlendar = Calendar.getInstance();  
		  canlendar.add(Calendar.DATE, days);
		  return sdfd.format(canlendar.getTime());
	  }
	  
	  /**
	   * 查前面第几天
	   * @param days
	   * @return
	   */
	  public static String monthAdd(int month) {
		  SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM");
		  Calendar canlendar = Calendar.getInstance();  
		  canlendar.add(Calendar.MONTH, month);
		  return sdfd.format(canlendar.getTime());
	  }
	  
	  public static String getDateBefore(int day){
		   Date d = new Date();
		   Calendar now =Calendar.getInstance();
		   now.setTime(d);
		   now.set(Calendar.DATE,now.get(Calendar.DATE)-day);
		   return DateFormatUtils.format(now.getTime(), parsePatterns[0]);
	  }
	  
	  public static String getDateBefores(String endTime, int day){
		try {
			Date d = dateFormat.parse(endTime);
			Calendar now =Calendar.getInstance();
		    now.setTime(d);
		    now.set(Calendar.DATE,now.get(Calendar.DATE)-day);
		    return DateFormatUtils.format(now.getTime(), parsePatterns[0]);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	  }
	  
	  //获取天数差
	  public static int getDayCha(String time1,String time2){
		  	long days=0;
		  	int day=0;
			try {
				Date date1 = dateFormat.parse(time1);
				Date date2 = dateFormat.parse(time2);
			    days= (date1.getTime()-date2.getTime())/(1000*60*60*24);
			    day=(int) (days);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return day;
	  }

	  //获取月份差
	  public static int getMonthCha(String time1,String time2){
		  int month=0;
			try {
				Date date1 = dateFormats.parse(time1);
				Date date2 = dateFormats.parse(time2);
				month=(date1.getYear() - date2.getYear()) * 12 + date1.getMonth() - date2.getMonth();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return month;
	  }
	  
	  public static String getMonthByBefore(String endTime,int month){
		   try {
			   Date date = dateFormats.parse(endTime);
			   Calendar now =Calendar.getInstance();
			   now.setTime(date);
			   now.set(Calendar.MONTH,now.get(Calendar.MONTH)-month);
			   return DateFormatUtils.format(now.getTime(), parsePatterns[5]);
		   } catch (ParseException e) {
			   e.printStackTrace();
			   return null;
		   }
	  }
	  
	  public static void main(String[] args) {
		
		//String month = monthAdd(-1);
		//System.out.println("*qq**"+month);
		/*String date = dateAdd(-0);
        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("：" + sdfd.format(date));
       // SimpleDateFormat sdf = new SimpleDateFormat("E");
        //System.out.println("为：" + sdf.format(date));*/
		
		
		System.out.println(formatDate(new Date(), 2));
		try {
//			System.out.println(getDateAfter(-1).replaceAll("-", ""));
//			System.out.println(dateAdd(5));
			//System.out.println("**1**"+getDateBefore(getStrToDate("2010-07-21"), 10));
//			System.out.println("**1**"+getMonthBefore(getDateBefore("2010-07"), 10));
			//System.out.println("****" + getStrToDate("2010-07-21 21:21:20"));
			//System.out.println(getTimeDifference("2014-07-23 21:27:20", "2014-07-23 21:26:20"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
