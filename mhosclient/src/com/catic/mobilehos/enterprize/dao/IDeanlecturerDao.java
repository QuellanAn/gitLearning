package com.catic.mobilehos.enterprize.dao;

import java.util.List;

import com.catic.mobilehos.enterprize.entity.Hosinfo;
import com.catic.mobilehos.utils.Page;

public interface IDeanlecturerDao {
	void add(Hosinfo info);
	
	void update(Hosinfo info);
	
	void delete(Integer id);
	
	List<Hosinfo> findAll(Hosinfo info,Page page);
	
	Hosinfo getInfo(Integer id);
}
