package com.catic.mobilehos.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.catic.mobilehos.po.CliVersionPO;
import com.catic.mobilehos.service.CliVersionService;
import com.catic.mobilehos.service.ServiceResult;
import com.catic.mobilehos.service.vo.CliVersionVO;
import com.catic.mobilehos.service.vo.Page;

/**
 * 版本发布
 * 
 * @author linchunda
 * 
 */
@SuppressWarnings("serial")
public class CliVersionAction extends BaseAction {
	private Log log = LogFactory.getLog(this.getClass());
	private CliVersionService cliVersionService;
	private CliVersionPO cliVersionPO;
	private CliVersionVO cliVersionVO;

	private final int DEFAULT_PAGESIZE = 10;

	private int page;

	private File apkPath;
	private String apkPathContentType;;
	private String apkPathFileName;

	private Page<CliVersionVO> pageBean;
	private String ver_cat;
	private String ver_type;
	private String apkName;
	private long fileLength;

	/**
	 * 客户端版本发布检查
	 */
	public void checkVersion() {
		ServiceResult result = cliVersionService
				.checkVersion(ver_cat, ver_type);

		writeJSON(result.getJSON());
	}

	public InputStream getDownloadCliVersion() {
		String path = request.getServletContext().getRealPath("/")
				+ File.separator + "dynamic" + File.separator + "apk"
				+ File.separator + apkName;
		try {
			File file = new File(path);
			setFileLength(file.length());
			return new FileInputStream(file);
		} catch (FileNotFoundException e) {
			log.error("下载文件出错:" + path, e);
			return null;
		}
	}
	/**
	 * 获取版本号
	 */
	public void getVersionNo() {
		try {
			List<String> list = cliVersionService.getVersionNo(
					cliVersionPO.getVer_cat(), cliVersionPO.getVer_type());

			JSONArray array = JSONArray.fromObject(list);
			JSONObject json = new JSONObject();
			json.put("data", array);
			writeJSON(json);
		} catch (Exception e) {
			log.error("获取版本号出错:", e);
		}
	}

	/**
	 * 版本发布管理页面,根据分页获取版本发布
	 * 
	 * @return
	 */
	public String getCliVersions() {
		try {
			pageBean = cliVersionService.findCliVersionPOByByParas(
					null == cliVersionPO ? null : cliVersionPO.getVer_cat(),
					page, DEFAULT_PAGESIZE);

			Map<String, String> paras = new TreeMap<String, String>();
			if (null != cliVersionPO) {
				paras.put("ver_cat", cliVersionPO.getVer_cat());
			}

			String baseUrl = "config/version/getCliVersions";
			pageBean.setQueryUrl(baseUrl, paras);
			return SUCCESS;
		} catch (Exception e) {
			log.error("获取版本发布数据出错:", e);
			return ERROR;
		}
	}

	/**
	 * 添加版本发布
	 * 
	 * @return
	 */
	public String saveCliVersion() {
		cliVersionPO.setVersionNo(cliVersionPO.getVersionNo().trim());
		cliVersionPO.setCreateDate(new Timestamp(new Date().getTime()));
		cliVersionPO.setApkName(apkPathFileName);
		cliVersionPO.setFilesize((int) apkPath.length() / 1024);

		String path = request.getServletContext().getRealPath("/")
				+ File.separator + "dynamic" + File.separator + "apk"
				+ File.separator;

		cliVersionPO.setApkPath(path + apkPathFileName);
		try {
			FileUtils.copyFile(apkPath, new File(path + apkPathFileName));
		} catch (IOException e) {
			log.error("版本文件上传失败", e);
		}

		cliVersionService.saveCliVersionPO(cliVersionPO);
		return SUCCESS;
	}

	/**
	 * 修改版本发布
	 */
	public String updateCliVersion() {
		cliVersionPO = cliVersionService.getCliVersionPOById(cliVersionPO
				.getID());
		return SUCCESS;

	}

	/**
	 * 修改版本发布
	 */
	public String updateCliVersionAction() {
		CliVersionPO po = cliVersionService.getCliVersionPOById(cliVersionPO
				.getID());
		po.setContent(cliVersionPO.getContent());
		po.setUpdate_version(cliVersionPO.getUpdate_version());
		po.setVer_cat(cliVersionPO.getVer_cat());
		po.setVer_type(cliVersionPO.getVer_type());
		po.setVersionNo(cliVersionPO.getVersionNo().trim());
		cliVersionService.updateCliVersionPO(po, cliVersionPO.getID());

		return SUCCESS;
	}

	/**
	 * 删除版本发布
	 */
	public void delCliVersion() {
		boolean result = cliVersionService.deleteCliVersionPO(cliVersionPO
				.getID());

		if (result) {
			JSONObject json = new JSONObject();
			json.put("result", "1");
			json.put("data", "已经成功删除该版本发布!");
			writeJSON(json);
		} else {
			JSONObject json = new JSONObject();
			json.put("result", "0");
			json.put("data", "删除该版本发布失败!");
			writeJSON(json);
		}
	}

	public CliVersionService getCliVersionService() {
		return cliVersionService;
	}

	public void setCliVersionService(CliVersionService cliVersionService) {
		this.cliVersionService = cliVersionService;
	}

	public CliVersionPO getCliVersionPO() {
		return cliVersionPO;
	}

	public void setCliVersionPO(CliVersionPO cliVersionPO) {
		this.cliVersionPO = cliVersionPO;
	}

	public File getApkPath() {
		return apkPath;
	}

	public void setApkPath(File apkPath) {
		this.apkPath = apkPath;
	}

	public String getApkPathContentType() {
		return apkPathContentType;
	}

	public void setApkPathContentType(String apkPathContentType) {
		this.apkPathContentType = apkPathContentType;
	}

	public String getApkPathFileName() {
		return apkPathFileName;
	}

	public void setApkPathFileName(String apkPathFileName) {
		this.apkPathFileName = apkPathFileName;
	}

	public Page<CliVersionVO> getPageBean() {
		return pageBean;
	}

	public void setPageBean(Page<CliVersionVO> pageBean) {
		this.pageBean = pageBean;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public CliVersionVO getCliVersionVO() {
		return cliVersionVO;
	}

	public void setCliVersionVO(CliVersionVO cliVersionVO) {
		this.cliVersionVO = cliVersionVO;
	}

	public String getVer_cat() {
		return ver_cat;
	}

	public void setVer_cat(String ver_cat) {
		this.ver_cat = ver_cat;
	}

	public String getVer_type() {
		return ver_type;
	}

	public void setVer_type(String ver_type) {
		this.ver_type = ver_type;
	}

	public String getApkName() {
		return apkName;
	}

	public void setApkName(String apkName) {
		this.apkName = apkName;
	}

	public long getFileLength() {
		return fileLength;
	}

	public void setFileLength(long fileLength) {
		this.fileLength = fileLength;
	}

}
