package com.catic.mobilehos.enterprize.action;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.catic.mobilehos.GlobalConstants;
import com.catic.mobilehos.enterprize.biz.IDeanlecturerService;
import com.catic.mobilehos.enterprize.biz.IHosinfoService;
import com.catic.mobilehos.pay.entity.Result;
import com.catic.mobilehos.utils.Constant;
import com.catic.mobilehos.utils.Page;
import com.opensymphony.xwork2.ActionSupport;


@SuppressWarnings("serial")
public class BaseAction extends ActionSupport implements ServletRequestAware,ServletResponseAware, Constant {
	
	@Resource
	protected IHosinfoService hosinfoBiz;
	
	@Resource
	protected IDeanlecturerService deanlecturerBiz;
	
	protected HttpSession session = null;
	protected HttpServletRequest request = null;
	protected HttpServletResponse response = null;
	protected PrintWriter out = null;
	protected Result result;
	
	protected JSONObject jsonObject = null;
	protected JSONObject jsonObject1 = null;
	protected JSONArray jsonArray = null;
	protected JSONArray jsonArray1 = null;
	protected JsonConfig config = null;
	
	protected List<File> file = null;
	protected List<String> fileFileName = null;
	
	protected String pageNo = null;
	protected Page page = null;
	//盘符
	public static final String DISK="C:";
		//图片总路径
	public static final String PATH=File.separator+"media"+File.separator+"image"+File.separator;
		//微信扫码图片分支
	public static final String WX_PAY_CODE="wxpaycode"+File.separator;

	public List<File> getFile() {
		return file;
	}

	public void setFile(List<File> file) {
		this.file = file;
	}

	public List<String> getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(List<String> fileFileName) {
		this.fileFileName = fileFileName;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	public HttpSession getSession() {
		session = request.getSession();
		return session;
	}

	
	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	
	
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	protected void jsonObj(String jobj) {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setContentType("application/json; charset=UTF-8");
			out = response.getWriter();
			out.write(jobj);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			out.flush();
			out.close();
		}
	}

	protected void jsonArr(JSONArray jarr) {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setContentType("application/json; charset=UTF-8");
			out = response.getWriter();
			out.write(jarr.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			out.flush();
			out.close();
		}
	}

	protected java.sql.Timestamp toTimestamp(String d){
		Date date;
		try {
			date = DateUtils.parseDateStrictly(d, GlobalConstants.DEF_DATETIME_FMT
					, GlobalConstants.DEF_DATEFORMAT);
			return new Timestamp(date.getTime());
		} catch (ParseException e) {
			return null;
		}
	}
}

