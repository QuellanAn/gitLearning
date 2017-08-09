package com.catic.mobilehos.service;

import java.sql.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;

import com.catic.mobilehos.dao.HelpAnswerDao;
import com.catic.mobilehos.po.HelpPO;
import com.catic.mobilehos.service.vo.HelpVO;
import com.catic.mobilehos.service.vo.Page;
import com.catic.mobilehos.service.vo.VOPOConverter;

public class HelpAnswerServiceImpl implements HelpAnswerService{
	private Log log = LogFactory.getLog(this.getClass());
	private HelpAnswerDao haDao;
	
	public HelpAnswerDao getHaDao() {
		return haDao;
	}

	public void setHaDao(HelpAnswerDao haDao) {
		this.haDao = haDao;
	}

	@Override
	public Page<HelpVO> getAllHelpList(String question,String createPeople, Date startDate,
			Date endDate, int page, int pageSize) {
		int totalRecord = this.haDao.getTotalHelp(question,createPeople, startDate, endDate);
		Page<HelpVO> pageCont = new Page<HelpVO>(totalRecord, pageSize, page);
		List<HelpPO> list = this.haDao.findAll(question,createPeople, startDate, 
				endDate,pageCont.getOffset(),pageCont.getPageSize());
		VOPOConverter<HelpVO, HelpPO> cvt 
		= new VOPOConverter<HelpVO, HelpPO>(HelpVO.class, HelpPO.class);
		List<HelpVO> vlist = cvt.fromPOList(list);
		pageCont.setCurPageData(vlist);
		return pageCont;
	}

	@Override
	public HelpPO getHelpAnswer(int helpId) {
		try {
			return this.haDao.getHelpAnswer(helpId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void updateHelp(HelpPO helpPo) {
		try {
			this.haDao.updateHelp(helpPo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ServiceResult addHelp(HelpPO helpPo) {
		try{
			this.haDao.addHelp(helpPo);
			return new ServiceResult(){
				public boolean isSuccess() {
					return true;
				}
			};
		}catch(DataAccessException dacEx){
			ServiceResult sr = ServiceResult.getFailedInstance("", "系统异常，添加就医指南失败！");
			return sr;
		}catch(Exception ex){
			log.error(null, ex);
			ServiceResult sr = ServiceResult.getFailedInstance("", "系统异常，添加就医指南失败！");
			return sr;
		}
	}

	@Override
	public void deleteHelp(int helpId) {
		this.haDao.deleteHelp(helpId);
	}

}
