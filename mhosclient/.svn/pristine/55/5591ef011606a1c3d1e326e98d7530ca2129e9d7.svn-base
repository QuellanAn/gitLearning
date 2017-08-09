package com.catic.mobilehos.menu_role_authority.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public interface Constant {
	
	/**
	 * 英文和数字共36个
	 */
	public static final String ALL_WORD = "abcdefghijklmnopqrstuvwxyz0123456789";
	
	/**
	 * 产生随机密码的位数
	 */
	public static final Integer COUNT = 6;
	
	/**
	 * 产生随机位数
	 */
	public static final Integer ODIER_COUNT = 10;
	
	/**
	 * 每页的显示数
	 */
	public static final int DEFAULT_PAGE_SIZE = 15;

	/**
	 * 盘符
	 */
	public static final String DISK = "C:";

	/**
	 * 图片上传总路径
	 */
	public static final String PATH = File.separator + "media" + File.separator + "image" + File.separator;
	
	/**
	 * 用户图片分支路径
	 */
	public static final String USER = "user" + File.separator;
	
	/**
	 * 工程师图片分支路径
	 */
	public static final String ENGINEER = "engineer" + File.separator;

	/**
	 * 报修图片分支路径
	 */
	public static final String REPAIR = "repair" + File.separator;
	
	/**
	 * 维修图片分支路径
	 */
	public static final String REPAIRINFO = "repairInfo" + File.separator;
	
	/**
	 * 广告资讯图片分支路径
	 */
	public static final String NEWS = "news" + File.separator;
	
	/**
	 * 二维码图片分支路径
	 */
	public static final String QRCODE = "qrcode" + File.separator;
	
	/**
	 * 巡检报告路径
	 */
	public static final String REPORT = File.separator + "media" + File.separator + "report" + File.separator;
	
	/**
	 * 消息是否被阅读（否）
	 */
	public static final Integer ISREAD = 1;
	public static final Integer MAXDATANUM=5000;

	
	/**
	 * POST提交方式
	 */
	public static final String METHOD_POST = "POST";
	
	/**
	 * session key值
	 */
	public static final String MANAGER = "manager";
	public static final String POPEDOM = "popedom";
	public static final String COMPANY = "company";
	public static final String CUSTOMER = "customer";
	public static final String MENU = "menu";
	public static final String KEY = "key";
	
	/**
	 * 巡检计划状态
	 */
	public static final Map<String, String> PLAN_STATUS = new HashMap<String, String>(){
		   private static final long serialVersionUID = 1L;
	        {
				put("01", "待分解");
				put("02", "已分解");
				put("03", "执行中");
				put("04", "终止");
				put("05", "完成");
			}
	};
	
	public static final Map<String, String> INSPECTION_STATUS = new HashMap<String, String>(){
		   private static final long serialVersionUID = 1L;
	        {
				put("0", "待接收");
				put("1", "已接收");
				put("2", "已拒绝");
				put("3", "已预约");
				put("4", "主动关闭");
				put("5", "巡检中");
				put("6", "待评价");
				put("7", "已评价");
				put("8", "已完成");
				put("9", "关单");
			}
	};
	
	public static final Map<Integer, String> INSPECTIONINFO_STATUS = new HashMap<Integer, String>(){
		   private static final long serialVersionUID = 1L;
	        {
				put(0, "正常");
				put(1, "异常");
			}
	};
	
}
