package com.catic.mobilehos.pay.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.catic.mobilehos.pay.biz.BaseBiz;
import com.catic.mobilehos.pay.biz.IAccountConfigBiz;
import com.catic.mobilehos.pay.entity.Account;
import com.catic.mobilehos.pay.entity.AccountPayType;
import com.catic.mobilehos.utils.Page;

@Service("accountConfigBiz")
public class AccountConfigBizImpl extends BaseBiz implements IAccountConfigBiz{

	@Override
	public Boolean save(Account account) throws Exception {
		
		return accountConfigDao.save(account);
	}

	@Override
	public List<Account> findAll(Page page) throws Exception {
		return accountConfigDao.findAll(page);
	}

	@Override
	public Boolean delete(String accountCode) throws Exception {
		
		return accountConfigDao.delete(accountCode);
	}

	@Override
	public Boolean modify(Account account) throws Exception {
		accountPayTypeDao.delete(account.getAccountCode())	;
		for(int i=0;i<account.getPayTypes().size();i++){
			AccountPayType ap=new AccountPayType();
			ap.setPtCode(account.getPayTypes().get(i));
			ap.setAccountCode(account.getAccountCode());
			ap.setApID(com.catic.mobilehos.utils.Common.getUUID());
			accountPayTypeDao.save(ap);
		}
		return accountConfigDao.modify(account);
	}

	@Override
	public Account findByCode(String code) throws Exception {
		
		Account ac=accountConfigDao.findByCode(code);
		if(ac!=null){
			List<AccountPayType> list=accountPayTypeDao.find(ac.getAccountCode());
			List<String> list1=new ArrayList<String>();
			if(list!=null&&list.size()>0){
				for(int i=0;i<list.size();i++){
					list1.add(list.get(i).getPtCode());
				}
			}
		/*	final int size =  list1.size();
			String[] arr = (String[])list1.toArray(new String[size]);*/
		//	ac.setPayTypes(arr);
			ac.setPayTypes(list1);
		}
		return ac;
	}

	@Override
	public List<Account> findAll() throws Exception {
		
		return accountConfigDao.findAll();
	}

}
