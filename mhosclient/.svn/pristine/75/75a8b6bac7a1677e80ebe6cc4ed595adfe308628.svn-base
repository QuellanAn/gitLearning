package com.catic.mobilehos.menu_role_authority.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.catic.mobilehos.menu_role_authority.biz.IMenuBiz;
import com.catic.mobilehos.menu_role_authority.entity.Menu;
import com.catic.mobilehos.pay.biz.BaseBiz;

@Service("menuBiz")
public class MenuBizImpl extends BaseBiz implements IMenuBiz {

	@Override
	public List<Menu> findAll() throws Exception {
		return menuDao.findAll();
	}

	@Override
	public List<Menu> findMenu(int id) throws Exception {
		List<Menu> list = menuDao.findParentMenu(id);
		List<Menu> sublist = menuDao.findMenu(id);
		for (int i = 0; i < list.size(); i++) {
			Menu menu = list.get(i);
			List<Menu> submenus = new ArrayList<Menu>();
			for (int j = 0; j < sublist.size(); j++) {
				Menu submenu = sublist.get(j);
				if(menu.getMenuId() == submenu.getParentId()){
					submenus.add(submenu);
				}
			}
			menu.setSubmenus(submenus);
		}
		return list;
	}
}
