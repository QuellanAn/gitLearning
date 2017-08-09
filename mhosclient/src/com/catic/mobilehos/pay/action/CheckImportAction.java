package com.catic.mobilehos.pay.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.catic.mobilehos.pay.entity.CheckImport;
import com.catic.mobilehos.pay.util.Constant;
import com.catic.mobilehos.pay.util.DateUtils;
import com.catic.mobilehos.pay.util.ExcleUtils;
import com.catic.mobilehos.utils.Page;
import com.catic.mobilehos.utils.Upload;

/**
 * 对账单导入查询
 * 
 * @author Administrator
 * 
 */
@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class CheckImportAction extends BaseAction {

	private String iid;
	private String billDate;
	private String operator;
	private String startDate;
	private String endDate;
	private CheckImport checkImport;
	private List<CheckImport> checkImportList;
	private String name;
	private String source;
	private String createTime;
	private File upload;
	private String uploadFileName;
	private String uploadContentType;

	/**
	 * 查询对账单导入列表
	 * 
	 * @return
	 */
	public String findAll() {
		try {
			if (StringUtils.isBlank(pageNo)) {
				pageNo = "1";
			}
			CheckImport checkImport = new CheckImport();
			checkImport.setBillDate(billDate);
			checkImport.setOperator(operator);
			checkImport.setStartDate(startDate);
			checkImport.setEndDate(endDate);
			checkImportList = checkImportBiz.findAll(null, checkImport);
			if (checkImportList != null && checkImportList.size() > 0) {
				page = new Page(Integer.parseInt(pageNo),
						Constant.DEFAULT_PAGE_SIZE, checkImportList.get(0)
								.getCount());
				checkImportList = checkImportBiz.findAll(page, checkImport);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 查看详情
	 * 
	 * @return
	 */
	public String findDetails() {

		try {
			checkImport = checkImportBiz.findDetails(iid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "details";
	}

	public void addCheck() {
		try {
			String SavePath = ServletActionContext.getServletContext()
					.getRealPath("acceptedUpload");
			Upload.uploadCheck(upload, SavePath, "\\" + uploadFileName+createTime);

			Workbook workBook = null;
			InputStream is = new FileInputStream(upload);
			String type = request.getParameter("type");
			if (type.toLowerCase().endsWith("xls")) {
				workBook = new HSSFWorkbook(is);
			}
			if (type.toLowerCase().endsWith("xlsx")) {
				workBook = new XSSFWorkbook(is);
			}
			Map<String, Object> map = hisCheckBiz.readXls(workBook,
					uploadFileName, request, response);

			CheckImport ci = new CheckImport();
			ci.setBillDate(createTime);
			ci.setName(name);
			ci.setSource(source);
			ci.setFileName(uploadFileName);
			ci.setFilePath(SavePath);
			ci.setSubmitNum(Integer.parseInt(map.get("submitNum") + ""));
			ci.setSuccessNum(Integer.parseInt(map.get("successNum") + ""));
			ci.setOperator(request.getSession().getAttribute("userName")
					.toString());
			if (Integer.parseInt(map.get("failNum") + "") > 0) {
				ci.setNoFileName(map.get("noFileName").toString());
				ci.setNoFilePath(map.get("noFilePath").toString());
			}
			checkImportBiz.save(ci);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void exportOtherCheck() throws IOException {
		try {
			StringBuffer sb=new StringBuffer("C:"+File.separator);
			String fileName="";
			if("1".equals(name)){
				sb.append("wechatcheck");
			}else{
				sb.append("zfbcheck");
			}
			createTime=createTime.replace("-", "");
			File file=new File(sb.toString());
			String info[] = file.list();
			for(int i=0;i<info.length;i++)
			{
				if(info[i].contains(createTime)){
					sb.append("/"+info[i]);
					fileName=info[i];
				}
			}
			response.reset();
			fileName = new String(fileName.getBytes(), "ISO-8859-1");
			InputStream is = new FileInputStream(sb.toString());
			OutputStream os = response.getOutputStream();
			// 弹出下载的框filename:提示用户下载的文件名
			response.addHeader("content-disposition", "attachment;filename="
					+ fileName);

			byte[] b = new byte[1024];
			int size = is.read(b);
			while (size > 0) {
				os.write(b, 0, size);
				size = is.read(b);
			}
			is.close();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
			response.setContentType("text/html; charset=UTF-8"); //转码
		    response.getWriter().flush();
		    response.getWriter().println("<script>");
		    response.getWriter().println("alert('导出失败！');");
		    response.getWriter().println("history.back();");
		    response.getWriter().println("</script>");
		}
		
	}
	
	public static void main(String[] args) {
		String dat="2017-05-10";
		dat=dat.replace("-", "");
		File file=new File("E:/workProject/CheckFile/WX");
		String test[] = file.list();
		for(int i=0;i<test.length;i++)
		{
			if(test[i].contains(dat)){
				System.out.println(test[i]);
			}
		}
	}

	public void modeDown() {
		ExcleUtils.modeDown(request, response);
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public CheckImport getCheckImport() {
		return checkImport;
	}

	public void setCheckImport(CheckImport checkImport) {
		this.checkImport = checkImport;
	}

	public List<CheckImport> getCheckImportList() {
		return checkImportList;
	}

	public void setCheckImportList(List<CheckImport> checkImportList) {
		this.checkImportList = checkImportList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	public String getIid() {
		return iid;
	}

	public void setIid(String iid) {
		this.iid = iid;
	}

}
