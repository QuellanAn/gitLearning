package com.catic.mobilehos.action;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;

import com.catic.mobilehos.GlobalConstants;
import com.catic.mobilehos.po.BasicCntCfgPO;
import com.catic.mobilehos.service.BasicCntCfgService;
import com.catic.mobilehos.service.ServiceResult;
import com.catic.mobilehos.service.vo.BasicCntCfgVO;
import com.catic.mobilehos.service.vo.Page;

/**
 * 基本内容配置
 * 
 * @author linchunda
 * 
 */
@SuppressWarnings("serial")
public class BasicCntCfgAction extends BaseAction {
	private final int DEFAULT_PAGESIZE = 10;
	/** 就医指南html目录 dynamic\html\basicntcfg\ */
	private final String BASIC_CNT_CFG_html_DIR = "dynamic" + File.separator
			+ "html" + File.separator + "basiccntcfg" + File.separator;
	private int cfg_type;
	private int cat;
	private String cat_name;
	
	private File icon;
	
	private String iconValue;
	
	private String iconFileName;
	
	private String content;

	private String htmlFileName;// 生成html文件名

	private BasicCntCfgService basicCntCfgService;
	
	private Page<BasicCntCfgVO> pageBean;
	
	private String update_people;
	
	private java.sql.Date startdate;
	
	private java.sql.Date enddate;
	
	private int page;

	public int getCfg_type() {
		return cfg_type;
	}

	public void setCfg_type(int cfg_type) {
		this.cfg_type = cfg_type;
	}

	public int getCat() {
		return cat;
	}

	public void setCat(int cat) {
		this.cat = cat;
	}

	public String getCat_name() {
		return cat_name;
	}

	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getHtmlFileName() {
		return htmlFileName;
	}

	public void setHtmlFileName(String htmlFileName) {
		this.htmlFileName = htmlFileName;
	}

	public BasicCntCfgService getBasicCntCfgService() {
		return basicCntCfgService;
	}

	public void setBasicCntCfgService(BasicCntCfgService basicCntCfgService) {
		this.basicCntCfgService = basicCntCfgService;
	}

	public Page<BasicCntCfgVO> getPageBean() {
		return pageBean;
	}

	public void setPageBean(Page<BasicCntCfgVO> pageBean) {
		this.pageBean = pageBean;
	}

	public String getUpdate_people() {
		return update_people;
	}

	public void setUpdate_people(String update_people) {
		this.update_people = update_people;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public java.sql.Date getStartdate() {
		return startdate;
	}

	public void setStartdate(java.sql.Date startdate) {
		this.startdate = startdate;
	}

	public java.sql.Date getEnddate() {
		return enddate;
	}

	public void setEnddate(java.sql.Date enddate) {
		this.enddate = enddate;
	}

	public File getIcon() {
		return icon;
	}

	public void setIcon(File icon) {
		this.icon = icon;
	}

	public String getIconFileName() {
		return iconFileName;
	}

	public void setIconFileName(String iconFileName) {
		this.iconFileName = iconFileName;
	}

	public String getIconValue() {
		return iconValue;
	}

	public void setIconValue(String iconValue) {
		this.iconValue = iconValue;
	}

	/**
	 * 获取基本内容配置
	 */
	public void getGuide() {
		BasicCntCfgPO basicCntCfgPO = basicCntCfgService.getBasicCntCfgPO(
				cfg_type, cat);
		this.writeJSON(JSONObject.fromObject(basicCntCfgPO));
	}
	
	public String toUpdateBase(){
		BasicCntCfgPO basicCntCfgPO = basicCntCfgService.getBasicCntCfgPO(1, cat);
		this.cat_name=basicCntCfgPO.getCat_name();
		this.content=basicCntCfgPO.getContent();
		this.iconValue=basicCntCfgPO.getIcon();
		return SUCCESS;
	}
	
	/**
	 * @Title: getDepartments 
	 * @Description: TODO 分页显示就医指南数据
	 * @param @return    
	 * @return String    
	 * @throws
	 */
	public String getBaseContentList(){
		try{
			request.setCharacterEncoding("UTF-8");
			pageBean = this.basicCntCfgService.getAllContentList(cat_name, update_people, startdate,
					enddate, page, DEFAULT_PAGESIZE);
//			Map<String, String> paras = new TreeMap<String, String>();
//			paras.put("cat_name", cat_name);
//			paras.put("update_people", update_people);
//			paras.put("startDate", startdate);
//			paras.put("endDate", enddate);
//			String baseUrl = "config/basic/getBaseContentList";
//			pageBean.setQueryUrl(baseUrl, paras);
			return SUCCESS;
		}catch(Exception ex){
			log.error(null, ex);
			return ERROR;
		}
		
	}

	/**
	 * 保存基本内容配置
	 * @return 
	 */
	public String saveGuide() {
		try {
			String realpath  = ("//C:/media");
			if (icon != null) {
			    File savefile = new File(new File(realpath), iconFileName);
			    if (!savefile.getParentFile().exists())
			        savefile.getParentFile().mkdirs();
			    	FileUtils.copyFile(icon, savefile);
			}
			String updatePeople = (String) request.getSession().getAttribute("userName");
			BasicCntCfgPO basicCntCfgPO = new BasicCntCfgPO();
			basicCntCfgPO.setCfg_type(1);
			basicCntCfgPO.setCat_name(cat_name);
			basicCntCfgPO.setContent(content);
			basicCntCfgPO.setIcon("/media/"+iconFileName);
			basicCntCfgPO.setUpdatePeople(updatePeople);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			basicCntCfgPO.setUpdate_time(this.toTimestamp(format.format(new Date())));
			
			if(cat==0){
				ServiceResult sr = basicCntCfgService.addBaseContent(basicCntCfgPO);
				if (sr.isSuccess()){
					return SUCCESS;
				}else{
					this.addActionError(sr.getFailedInfo().getString(ServiceResult.MSG));
					return ERROR;
				}
			}else{
				basicCntCfgPO.setCat(cat);
				basicCntCfgService.updateBasicCntCfg(basicCntCfgPO);
				return SUCCESS;
			}
		} catch (IOException e) {
			log.error(null, e);
			this.addActionError("系统异常，发布资讯失败！");
			return ERROR;
		}
		
	}

	/**
	 * 修改基本内容配置
	 */
	/*public void updateGuide() {
		BasicCntCfgPO basicCntCfgPO = new BasicCntCfgPO();
		basicCntCfgPO.setCfg_type(cfg_type);
		basicCntCfgPO.setCat(cat);
		basicCntCfgPO.setCat_name(cat_name);
		basicCntCfgPO.setContent(content);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		basicCntCfgPO
				.setUpdate_time(this.toTimestamp(format.format(new Date())));
		boolean result = basicCntCfgService.updateBasicCntCfg(basicCntCfgPO,
				cfg_type, cat);

		if (result) {
			// 生成html
			createHtml(htmlFileName, ServiceUtils.getFullMobileHtml(content));

			JSONObject json = new JSONObject();

			json.put("data", cat_name + "修改成功.");
			this.writeJSON(json);
		} else {
			JSONObject json = new JSONObject();

			json.put("data", cat_name + "修改失败.");
			this.writeJSON(json);
		}
	}*/

	/**
	 * 生成html文件
	 */
	private void createHtml(String htmlFileName, String html) {
		String htmlPath = request.getServletContext().getRealPath("/")
				+ File.separator + BASIC_CNT_CFG_html_DIR;
		htmlPath += htmlFileName;
		File htmlFile = new File(htmlPath);

		try {
			FileUtils.writeByteArrayToFile(htmlFile,
					html.getBytes(GlobalConstants.DEF_ENCODE));
		} catch (UnsupportedEncodingException e) {
			log.error("生成" + htmlFileName + "文件出错:", e);
		} catch (IOException e) {
			log.error("生成" + htmlFileName + "文件出错:", e);
		}

	}
	
	public String deleteBaseContent(){
		try{
			BasicCntCfgPO bccpo=basicCntCfgService.getBasicCntCfgPO(1, cat);
			basicCntCfgService.deleteBaseContent(cat);
			String realpath  = "//D:"+bccpo.getIcon();
			File file = new File(realpath);
			if (file.exists()) {
			    file.delete();
			}
			pageBean = this.basicCntCfgService.getAllContentList(cat_name, update_people, startdate,
					enddate, page, DEFAULT_PAGESIZE);
			return SUCCESS;
		}catch(Exception e){
			log.error(null, e);
			return ERROR;
		}
	}
}
