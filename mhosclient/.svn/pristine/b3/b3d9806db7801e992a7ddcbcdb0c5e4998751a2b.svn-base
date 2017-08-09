package com.catic.mobilehos.action;

import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.catic.mobilehos.service.ServiceResult;
import com.catic.mobilehos.service.WechatMenuService;
import com.catic.mobilehos.service.vo.WechatMainMenuVO;

/**
 * 微信菜单配置管理
 * 
 * @author dsh
 * 
 */
public class WechatMenuAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private Log log = LogFactory.getLog(this.getClass());

	private WechatMenuService wechatMenuService;

	private List<WechatMainMenuVO> wechatVO;
	private String id;
	private String name;
	private boolean view;
	private boolean click;
	private boolean key;
	private String url;
	private String type;
	private String submenu;
	private String level;

	/**
	 * 显示微信菜单模板配置页
	 */
	public String showWechatMenu() {
		try {
			this.wechatVO = wechatMenuService.getAllMenu();
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			log.error(null, e);
			return ERROR;
		}
	}

	/**
	 * 加载菜单内容
	 * 
	 * @return
	 */
	public String loadWechatMenu() {
		try {
			ServiceResult sr = wechatMenuService.loadWechatMenu(id);
			return this.setServiceResultToJson(sr);
		} catch (Exception e) {
			// TODO: handle exception
			return ERROR;
		}
	}

	/**
	 * 新增菜单内容
	 * 
	 * @return
	 */
	public String addMenuContent() {
		try {
			ServiceResult sr = wechatMenuService.saveMenuContent(id, name,
					view, click, key, url,submenu);
		return this.setServiceResultToJson(sr);
		} catch (Exception e) {
			log.error(null, e);
			return ERROR;
		}

	}
	/**
	 * 修改菜单内容
	 * 
	 * @return
	 */
	public String saveMenuContent() {
		try {
			ServiceResult sr = wechatMenuService.saveMenuContent(id, name,
					view, click, key, url,submenu);
			return this.setServiceResultToJson(sr);
		} catch (Exception e) {
			log.error(null, e);
			return ERROR;
		}

	}

	/**
	 * 删除菜单内容
	 * 
	 * @return
	 */
	public String deleMenuContent() {
		try {
			ServiceResult sr = wechatMenuService.deleMenuContent(id);
			return this.setServiceResultToJson(sr);
		} catch (Exception e) {
			log.error(null, e);
			return ERROR;
		}
	}

	/**
	 * 发布菜单
	 * 
	 * @return
	 */
	public String pubMenuContent() {
		try {
			ServiceResult sr = wechatMenuService.pubMenuContent();
			return this.setServiceResultToJson(sr);
		} catch (Exception e) {
			log.error(null, e);
			return ERROR;
		}
	}

	/**
	 * 加载一级菜单
	 * 
	 * @return
	 */
	public void getmenuJson() {
		try {
			if (type.equals("1")) {
				List<WechatMainMenuVO> menu = wechatMenuService.getmenuJson();
				JSONArray jarr = JSONArray.fromObject(menu);
				this.writeJSON(jarr);
			} else {
			}
		} catch (Exception e) {
			log.error(null, e);
		}
	}

	public WechatMenuService getWechatMenuService() {
		return wechatMenuService;
	}

	public void setWechatMenuService(WechatMenuService wechatMenuService) {
		this.wechatMenuService = wechatMenuService;
	}

	public List<WechatMainMenuVO> getWechatVO() {
		return wechatVO;
	}

	public void setWechatVO(List<WechatMainMenuVO> wechatVO) {
		this.wechatVO = wechatVO;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isView() {
		return view;
	}

	public void setView(boolean view) {
		this.view = view;
	}

	public boolean isClick() {
		return click;
	}

	public void setClick(boolean click) {
		this.click = click;
	}

	public boolean isKey() {
		return key;
	}

	public void setKey(boolean key) {
		this.key = key;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSubmenu() {
		return submenu;
	}

	public void setSubmenu(String submenu) {
		this.submenu = submenu;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

}
