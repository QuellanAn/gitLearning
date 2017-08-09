package com.catic.mobilehos.pay.biz.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.catic.mobilehos.pay.biz.BaseBiz;
import com.catic.mobilehos.pay.biz.IYQConfigBiz;
import com.catic.mobilehos.pay.entity.YQInfo;
import com.catic.mobilehos.utils.Page;

@Service("yQConfigBiz")
public class YQConfigBizImpl extends BaseBiz implements IYQConfigBiz{

	@Override
	public Boolean insert(YQInfo yq) throws Exception {
	    if(yq!=null){
	    	SimpleDateFormat df=new SimpleDateFormat("yyMMddHHmmss");
	    	yq.setYqCode(df.format(new Date()));
	    }
	    
		return yQConfigDao.insert(yq);
	}

	@Override
	public List<YQInfo> findAll(Page page) throws Exception {
	
		return yQConfigDao.findAll( page);
	}

	@Override
	public Boolean delete(String yqCode) throws Exception {
	
		return yQConfigDao.delete(yqCode);
	}

	@Override
	public Boolean modify(YQInfo yq) throws Exception {
		
		return yQConfigDao.modify(yq);
	}

	@Override
	public YQInfo findByCode(String code) throws Exception {
		
		return yQConfigDao.findByCode(code);
	}

	@Override
	public YQInfo findById(String id) throws Exception {
	
		return yQConfigDao.findById(id);
	}

	@Override
	public List<YQInfo> findAllyq() throws Exception {
		return yQConfigDao.findAllyq();
	}

}
