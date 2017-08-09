package com.catic.mobilehos.menu_role_authority.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.catic.mobilehos.menu_role_authority.entity.RoleMenu;

/**
 * 角色菜单信息
 * @author YUXY
 *
 */

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class RoleMenuAction extends BaseAction {

	private RoleMenu roleMenu;
	private List<RoleMenu> list;
	
	@SuppressWarnings("static-access")
	public void findAll(){
		try {
			int[] ids = new int[]{Integer.parseInt(pageNo)};
			list = roleMenuBiz.findAll(ids);
			String menuIds = "";
			for (int i = 0; i < list.size(); i++) {
				roleMenu = list.get(i);
				menuIds += roleMenu.getMenuId() +",";
			}
			result = resultBiz.getResult(resultBiz.SUCCESS, menuIds);
		} catch (Exception e) {
			e.printStackTrace();
			result = resultBiz.getResult(resultBiz.SYSTEM_BUSY);
		}finally{
			jsonObj(result.toJson());
			//System.out.println("menu:"+result.toJson());
		}
	}
}
