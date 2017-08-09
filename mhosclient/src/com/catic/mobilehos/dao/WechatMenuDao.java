package com.catic.mobilehos.dao;

import java.util.List;

import com.catic.mobilehos.service.vo.WechatMainMenuVO;


public interface WechatMenuDao {

	//获取全部菜单
	List<WechatMainMenuVO> getAllMenu();

	WechatMainMenuVO loadWechatMenu(String id);

	//保存菜单内容
	void updateWechatMenu(String id, String name, String type, String key,
			String view,String submenu);
	//删除菜单
	void deleWechatMenu(String id);
	//加载一级菜单
	List<WechatMainMenuVO> getmenuJson();
	


}
