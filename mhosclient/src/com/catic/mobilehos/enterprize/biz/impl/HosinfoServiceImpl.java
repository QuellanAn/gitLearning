package com.catic.mobilehos.enterprize.biz.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.catic.mobilehos.enterprize.biz.BaseBiz;
import com.catic.mobilehos.enterprize.biz.IHosinfoService;
import com.catic.mobilehos.enterprize.entity.Hosinfo;
import com.catic.mobilehos.utils.Page;

@Service("hosinfoBiz")
public class HosinfoServiceImpl extends BaseBiz implements IHosinfoService {

	@Override
	public void add(Hosinfo info) {
		hosinfoDao.add(info);
	}

	@Override
	public void update(Hosinfo info) {
		hosinfoDao.update(info);				
	}

	@Override
	public void delete(Integer id) {
		hosinfoDao.delete(id);
	}

	@Override
	public Hosinfo getInfo(Integer id) {
		return hosinfoDao.getInfo(id);
	}

	@Override
	public List<Hosinfo> findAll(Hosinfo info, Page page) {
		return hosinfoDao.findAll(info, page);
	}

}
