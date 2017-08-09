package com.catic.mobilehos.enterprize.biz;

import javax.annotation.Resource;

import com.catic.mobilehos.enterprize.dao.IDeanlecturerDao;
import com.catic.mobilehos.enterprize.dao.IHosinfoDao;


public class BaseBiz {
	@Resource
	protected IHosinfoDao hosinfoDao;
	
	@Resource
	protected IDeanlecturerDao deanlecturerDao;
}
