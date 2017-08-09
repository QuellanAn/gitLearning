package com.catic.mobilehos.service;

import java.util.List;

import com.catic.mobilehos.service.vo.WechatMainMenuVO;

public interface WechatMenuService {

	//获取所有菜单
	List<WechatMainMenuVO> getAllMenu();

	//加载菜单内容
	ServiceResult loadWechatMenu(String id);
	//保存菜单内容
	ServiceResult saveMenuContent(String id, String name, boolean view,
			boolean click, boolean key, String url,String submenu);

	ServiceResult deleMenuContent(String id);

	ServiceResult pubMenuContent();
	//一级菜单
	List<WechatMainMenuVO> getmenuJson();

	
}
