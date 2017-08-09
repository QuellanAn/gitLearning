package com.catic.mobilehos.servlet;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.catic.mobilehos.GlobalConstants;
import com.catic.mobilehos.service.HospitalInfoService;
import com.catic.mobilehos.service.ServiceResult;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class FileUploadServlet
 */
public class ImgFileUpdServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private Log log = LogFactory.getLog(this.getClass());
	
	// 最大文件大小
	private static final int FILE_MAX_SIZE = 1000000;
	
	private HospitalInfoService hospitalInfoService;
	
	      
    public HospitalInfoService getHospitalInfoService() {
		return hospitalInfoService;
	}

	public void setHospitalInfoService(HospitalInfoService hospitalInfoService) {
		this.hospitalInfoService = hospitalInfoService;
	}

	/**
     * @see HttpServlet#HttpServlet()
     */
    public ImgFileUpdServlet() {
        super();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext()); 
		this.hospitalInfoService = (HospitalInfoService) appContext.getBean("hospitalInfoService");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		super.destroy();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("doGet start.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("doPost start.");
		try {
			execute(request, response);
		} catch (Exception e) {
			log.error(null, e);
		}
	}
	
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws FileUploadException{
		
		//定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		response.setContentType("text/html; charset=UTF-8");
		if(!ServletFileUpload.isMultipartContent(request)){
			writeError(response, "请选择文件。");
			return;
		}
		//检查目录
		String dirName = request.getParameter("dir");
		if (dirName == null) {
			dirName = "image";
		}
		if(!extMap.containsKey(dirName)){
			writeError(response, "目录名不正确。");
			return;
		}

		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding(GlobalConstants.DEF_ENCODE);
		List<FileItem> items = upload.parseRequest(request);
		Iterator<FileItem> itr = items.iterator();
		while (itr.hasNext()) {
			FileItem item = (FileItem) itr.next();
			String fileName = item.getName();
			long fileSize = item.getSize();
			if (!item.isFormField()) {
				//检查文件大小
				if(item.getSize() > FILE_MAX_SIZE){
					writeError(response, "上传文件大小超过限制。");
					return;
				}
				//检查扩展名
				String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
				if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){
					writeError(response, "上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。");
					return;
				}

				try{
					byte[] arr = item.get();
					log.debug(arr.length);
					String infoid = request.getParameter("infoid");
					ServiceResult sr = this.hospitalInfoService.saveInfoHtmlImage(getServletContext()
							, infoid, null);
					if (sr.isSuccess()){
						writeUrl(response, (String) sr.getObject());
					}else{
						writeError(response, sr.getFailedInfo().getString(sr.MSG));
					}
				}catch(Exception e){
					log.error(null, e);
					writeError(response, "上传文件失败！");
					return;
				}
			}
		}
		
	}
	
	
	private void writeError(HttpServletResponse response, String errMsg){
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", errMsg);
		try {
			log.debug(obj.toString());
			response.getWriter().println(obj.toString());
		} catch (IOException e) {
			log.error(null, e);
		}
	}
	
	private void writeUrl(HttpServletResponse response, String url){
		JSONObject obj = new JSONObject();
		obj.put("error", 0);
		obj.put("url", url);
		try {
			log.debug(obj.toString());
			response.getWriter().println(obj.toString());
		} catch (IOException e) {
			log.error(null, e);
		}
	}

}
