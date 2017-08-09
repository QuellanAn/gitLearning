package com.catic.mobilehos.service;

import java.util.List;

import com.catic.mobilehos.dao.SpecialistDao;
import com.catic.mobilehos.po.Specialist;
import com.catic.mobilehos.utils.Page;

public class SpecialistServiceImpl implements SpecialistService {

	private SpecialistDao specialistDao;
	
	@Override
	public boolean delete(String id) throws Exception {
		return specialistDao.delete(id);
	}

	@Override
	public List<Specialist> findAll(Specialist specialist, Page page) throws Exception {
		return specialistDao.findAll(specialist, page);
	}

	@Override
	public boolean save(Specialist specialist) throws Exception {
		return specialistDao.save(specialist);
	}

	@Override
	public List<Specialist> findByCode(String code) throws Exception {
		return specialistDao.findByCode(code);
	}

	@Override
	public boolean modify(Specialist specialist) throws Exception {
		return specialistDao.modify(specialist);
	}
	
	public SpecialistDao getSpecialistDao() {
		return specialistDao;
	}

	public void setSpecialistDao(SpecialistDao specialistDao) {
		this.specialistDao = specialistDao;
	}
	
}
