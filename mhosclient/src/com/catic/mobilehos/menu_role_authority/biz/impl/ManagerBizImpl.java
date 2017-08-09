package com.catic.mobilehos.menu_role_authority.biz.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.catic.mobilehos.menu_role_authority.biz.IManagerBiz;
import com.catic.mobilehos.menu_role_authority.entity.Manager;
import com.catic.mobilehos.menu_role_authority.entity.Popedom;
import com.catic.mobilehos.pay.biz.BaseBiz;
import com.catic.mobilehos.utils.Page;

@Service("managerBiz")
public class ManagerBizImpl extends BaseBiz implements IManagerBiz {

	@Override
	public Manager findById(Integer id) throws Exception {
		return managerDao.findById(id);
	}

	@Override
	public Manager findManager(Manager manager) throws Exception {
		manager = managerDao.findManager(manager);
//		if(manager != null){
//			manager.setLoginDate(new Date());
//			managerDao.modify(manager);
//		}
		return manager;
	}

	@Override
	public boolean findPhoneByType(Manager manager) throws Exception {
		return managerDao.findPhoneByType(manager);
	}

	@Override
	public boolean modify(Manager manager) throws Exception {
		boolean flag = managerDao.modify(manager);
		flag = popedomDao.delete(manager.getUserId());
		if(manager.getIds() != null){
			for (int i = 0; i < manager.getIds().length; i++) {
				Popedom popedom = new Popedom(manager.getUserId(), manager.getIds()[i]);
				popedomDao.save(popedom);
			}
		}
		if(flag){
		    Popedom popedom=new Popedom(manager.getUserId(), manager.getRoleId());
			popedomDao.save(popedom);
			}
		
		return flag;
	}

	@Override
	public List<Manager> findAll(String id, Page page) throws Exception {
		return managerDao.findAll(id, page);
	}

	@Override
	public boolean save(Manager manager, String roleId) throws Exception {
		boolean flag = managerDao.save(manager);
		if(flag){
			Integer mrid = managerDao.findMaxId();
			Popedom popedom = new Popedom();
			popedom.setMrid(mrid);
			popedom.setRoleId(Integer.parseInt(roleId));
			popedomDao.save(popedom);
		}
		return flag;
	}

	@Override
	public Manager findByValid(String account, String phone) throws Exception {
		return managerDao.findByValid(account, phone);
	}

	@Override
	public Manager findByCyid(String cyid) throws Exception {
		return managerDao.findByCyid(cyid);
	}

	
}
