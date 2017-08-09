package com.catic.mobilehos.enterprize.biz.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.catic.mobilehos.enterprize.biz.BaseBiz;
import com.catic.mobilehos.enterprize.biz.IDeanlecturerService;
import com.catic.mobilehos.enterprize.biz.IHosinfoService;
import com.catic.mobilehos.enterprize.dao.IDeanlecturerDao;
import com.catic.mobilehos.enterprize.entity.Hosinfo;
import com.catic.mobilehos.utils.Page;

@Service("deanlecturerBiz")
public class DeanlecturerServiceImpl extends BaseBiz implements IDeanlecturerService {

	@Override
	public void add(Hosinfo info) {
		deanlecturerDao.add(info);
	}

	@Override
	public void update(Hosinfo info) {
		deanlecturerDao.update(info);				
	}

	@Override
	public void delete(Integer id) {
		deanlecturerDao.delete(id);
	}

	@Override
	public Hosinfo getInfo(Integer id) {
		return deanlecturerDao.getInfo(id);
	}

	@Override
	public List<Hosinfo> findAll(Hosinfo info, Page page) {
		return deanlecturerDao.findAll(info, page);
	}

}
