package com.catic.mobilehos.menu_role_authority.action;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.catic.mobilehos.menu_role_authority.entity.Menu;

/**
 * 菜单信息
 * @author YUXY
 *
 */
@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class MenuAction extends BaseAction {
	
	private Menu menu;
	private List<Menu> list;
	private String json;
	
	public void findAll() {
		try {
			list = menuBiz.findAll();
			jsonArray = new JSONArray();
			for (int i = 0; i < list.size(); i++) {
				menu = list.get(i);
				jsonObject = new JSONObject();
				jsonObject.put("id", menu.getMenuId());
				jsonObject.put("pId", menu.getParentId());
				jsonObject.put("name", menu.getName());
				if(menu.getParentId() == 0 || menu.getParentId() == 1){
					jsonObject.put("open", true);
				}
				jsonArray.add(jsonObject);
				jsonObject.clear();
			}
			json = jsonArray.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			jsonObj(json);
		}
	}
	
	//入口
	public String protal() {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
}
