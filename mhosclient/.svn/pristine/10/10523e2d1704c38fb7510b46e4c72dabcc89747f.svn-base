package com.catic.mobilehos.action;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.catic.mobilehos.po.OpenFireGroupPO;
import com.catic.mobilehos.po.OpenFireUserPO;
import com.catic.mobilehos.service.OpenFireGroupService;
import com.catic.mobilehos.service.OpenFireUserService;
import com.catic.mobilehos.service.vo.Page;
import com.catic.mobilehos.utils.Blowfish;

/**
 * 即时通信OpenFire用户
 * 
 * @author linchunda
 * 
 */
@SuppressWarnings("serial")
public class OpenFireUserAction extends BaseAction {
	private Log log = LogFactory.getLog(this.getClass());

	/**
	 * 添加openfire用户
	 */
	public String addOpenFireUser() {
		try {
			boolean haveGropuName = openFireGroupService.haveGroupName(groupName);
			if (haveGropuName) {
				OpenFireUserPO openFireUserPO = new OpenFireUserPO();
				openFireUserPO.setUsername(username);
				openFireUserPO.setName(name);
				openFireUserPO.setPlainPassword(plainPassword);
				openFireUserPO.setEncryptedPassword(encryptedPassword);
				openFireUserPO.setEmail(email);
				openFireUserPO.setCreationDate(Long.toString(new Date().getTime()));
				openFireUserService.addOpenFireUserPO(openFireUserPO, groupName);
			} else {
				// 先添加组
				OpenFireGroupPO openFireGroupPO = new OpenFireGroupPO();
				openFireGroupPO.setGroupName(groupName);
				openFireGroupPO.setDescription(description);
				openFireGroupService.addGroup(openFireGroupPO);

				// 后添加用户
				OpenFireUserPO openFireUserPO = new OpenFireUserPO();
				openFireUserPO.setUsername(username);
				openFireUserPO.setName(name);
				openFireUserPO.setPlainPassword(plainPassword);
				openFireUserPO.setEncryptedPassword(encryptedPassword);
				openFireUserPO.setEmail(email);
				openFireUserPO.setCreationDate(Long.toString(new Date().getTime()));
				openFireUserService.addOpenFireUserPO(openFireUserPO, groupName);
			}
			return SUCCESS;
		} catch (Exception e) {
			log.error("添加openfire用户出错:", e);
			return ERROR;
		}
	}

	/**
	 * 删除openfire用户
	 */
	public void deleteOpenFireUser() {
		try {
			openFireUserService.deleteOpenFireUserPO(username);
			JSONObject json = new JSONObject();
			json.put("result", "1");
			writeJSON(json);
		} catch (Exception e) {
			log.error("删除openfire用户出错:", e);
			JSONObject json = new JSONObject();
			json.put("result", "0");
			writeJSON(json);
		}
	}

	/**
	 * 根据用户名进行查找
	 */
	public void getOpenFireUser() {
		try {
			OpenFireUserPO openFireUserPO = openFireUserService
					.findByUsername(username);
			
			if(null == openFireUserPO){
				JSONObject json = new JSONObject();
				json.put("result", "0");
				writeJSON(json);
			}else{
				JSONObject json = new JSONObject();
				json.put("result", "1");
				json.put("openFireUserPO", openFireUserPO);
				writeJSON(json);
			}
		} catch (Exception e) {
			log.error("根据用户名查找openfire用户出错:", e);
			JSONObject json = new JSONObject();
			json.put("result", "0");
			writeJSON(json);
		}
	}

	/**
	 * 修改openfire用户
	 */
	public void updateOpenFireUser() {
		try {
			OpenFireUserPO openFireUserPO = openFireUserService
					.findByUsername(username);

			if (null == openFireUserPO) {
				return;
			}

			if (!StringUtils.isEmpty(encryptedPassword)) {
				// 对密码进行加密
				 Blowfish blowfish = new Blowfish(openFireUserService.getPasswordKey());
				 String newPassword = blowfish.encryptString(encryptedPassword);
				 openFireUserPO.setEncryptedPassword(newPassword);
			}
			openFireUserPO.setName(name);
			openFireUserPO.setPlainPassword(plainPassword);
			openFireUserPO.setEmail(email);
			openFireUserPO.setModificationDate(Long.toString(new Date().getTime()));
			openFireUserService.updateOpenFireUserPO(username, openFireUserPO);

			JSONObject json = new JSONObject();
			json.put("result", "1");
			writeJSON(json);
		} catch (Exception e) {
			log.error("编辑openfire用户出错:", e);
			JSONObject json = new JSONObject();
			json.put("result", "0");
			writeJSON(json);
		}
	}

	public String getAllOpenFireUser() {
		try {
			pageBean = openFireUserService.findOpenFireUserPOByByParas(
					groupName, username, page, DEFAULT_PAGESIZE);

			Map<String, String> paras = new TreeMap<String, String>();
			if (!StringUtils.isEmpty(groupName)) {
				paras.put("groupName", groupName);
			}
			if (!StringUtils.isEmpty(username)) {
				paras.put("username", username);
			}

			// String baseUrl = "manager/user/getUsers";
			pageBean.setQueryUrl(baseUrl, paras);
			return SUCCESS;
		} catch (Exception e) {
			log.error("获取医护人员数据出错:", e);
			return ERROR;
		}
	}

	private OpenFireGroupService openFireGroupService;
	private OpenFireUserService openFireUserService;
	/** 组名 */
	private String groupName;
	/** 组名描述信息 */
	private String description;
	/** 用户名 */
	private String username;
	/** 纯文本密码 */
	private String plainPassword;
	/** 加密密码 */
	private String encryptedPassword;
	/** 名字 */
	private String name;
	/** 邮件地址 */
	private String email;
	/** 医院端传过来的路径 */
	private String baseUrl;

	private final int DEFAULT_PAGESIZE = 10;
	private Page<OpenFireUserPO> pageBean;
	private int page=1;

	public OpenFireGroupService getOpenFireGroupService() {
		return openFireGroupService;
	}

	public void setOpenFireGroupService(
			OpenFireGroupService openFireGroupService) {
		this.openFireGroupService = openFireGroupService;
	}

	public OpenFireUserService getOpenFireUserService() {
		return openFireUserService;
	}

	public void setOpenFireUserService(OpenFireUserService openFireUserService) {
		this.openFireUserService = openFireUserService;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		log.info(groupName);
		this.groupName = groupName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPlainPassword() {
		return plainPassword;
	}

	public void setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Page<OpenFireUserPO> getPageBean() {
		return pageBean;
	}

	public void setPageBean(Page<OpenFireUserPO> pageBean) {
		this.pageBean = pageBean;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

}
