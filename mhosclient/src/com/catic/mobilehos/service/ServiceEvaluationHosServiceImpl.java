package com.catic.mobilehos.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.catic.mobilehos.dao.ServiceEvaluationHosDAO;
import com.catic.mobilehos.po.ServiceEvaluationHosPO;
import com.catic.mobilehos.service.vo.Page;
import com.catic.mobilehos.service.vo.ServiceEvaluationHosVO;
import com.catic.mobilehos.service.vo.VOPOConverter;
import com.catic.mobilehos.utils.DateUtil;

public class ServiceEvaluationHosServiceImpl  implements ServiceEvaluationHosService{
	private Log log = LogFactory.getLog(this.getClass());
	
	private ServiceEvaluationHosDAO  serviceEvaluationHosDAO;
	
	
	public ServiceEvaluationHosDAO getServiceEvaluationHosDAO() {
		return serviceEvaluationHosDAO;
	}


	public void setServiceEvaluationHosDAO(
			ServiceEvaluationHosDAO serviceEvaluationHosDAO) {
		this.serviceEvaluationHosDAO = serviceEvaluationHosDAO;
	}


	@Override
	public Page<ServiceEvaluationHosVO> findListByParas(String doctorName,String departmentCode,
			String overallEvaluation, java.sql.Date startDate, java.sql.Date endDate, int page,
			int pageSize,String cardNumber,String patientname, String departmentName, String evaSource) {
		
		
		try {
			
			int totalRecord = this.serviceEvaluationHosDAO.countServiceEvaluationHos(doctorName, departmentCode,
					overallEvaluation, startDate, endDate, cardNumber, patientname, departmentName, evaSource);
			
			
			Page<ServiceEvaluationHosVO> p = new Page<ServiceEvaluationHosVO>(
					totalRecord, pageSize, page);

			List<ServiceEvaluationHosPO> lst = this.serviceEvaluationHosDAO.findListByParas(doctorName, departmentCode, 
					overallEvaluation, startDate, endDate, p.getOffset(), p.getPageSize(), cardNumber, patientname, departmentName, evaSource);

			VOPOConverter<ServiceEvaluationHosVO, ServiceEvaluationHosPO> cvt = new VOPOConverter<ServiceEvaluationHosVO, ServiceEvaluationHosPO>(
					ServiceEvaluationHosVO.class, ServiceEvaluationHosPO.class);
			p.setCurPageData(cvt.fromPOList(lst));
			return p;
		} catch (Exception e) {
			log.error("queryAppRegOrdersByParas出现错误：", e);
			return null;
		}
		
		
	}


	@Override
	public ServiceEvaluationHosVO findServiceEvaluationHosById(
			Integer serviceEvaluationId) {


		ServiceEvaluationHosPO spo= this.serviceEvaluationHosDAO.findServiceEvaluationHosById(serviceEvaluationId);
		if(spo==null){
		return null;
		}else{
			
			VOPOConverter<ServiceEvaluationHosVO, ServiceEvaluationHosPO> converter = new VOPOConverter<ServiceEvaluationHosVO, ServiceEvaluationHosPO>(
					ServiceEvaluationHosVO.class, ServiceEvaluationHosPO.class);
			ServiceEvaluationHosVO svo=converter.poToVO(spo);
			return svo;
			
		}
	}


	@Override
	public ServiceEvaluationHosVO statisticEvaluateHos(String departmentCode,
			Date startDate, Date endDate) {
		ServiceEvaluationHosVO svo=new ServiceEvaluationHosVO();
		int goodNum=this.serviceEvaluationHosDAO.statisticServiceEvaluationHos(departmentCode, "0", startDate, endDate);
		int commonNum=this.serviceEvaluationHosDAO.statisticServiceEvaluationHos(departmentCode, "1", startDate, endDate);
		int badNum=this.serviceEvaluationHosDAO.statisticServiceEvaluationHos(departmentCode, "2", startDate, endDate);
		svo.setGoodNum(goodNum);
		svo.setCommonNum(commonNum);
		svo.setBadNum(badNum);
		
		return svo;
	}


	@Override
	public List<ServiceEvaluationHosPO> getCommentCount(
			ServiceEvaluationHosPO po) {
		return serviceEvaluationHosDAO.getCommentCountByDate(po);
	}


	@Override
	public List<ServiceEvaluationHosPO> getCommentCountByDepartMent(
			ServiceEvaluationHosPO serviceEvaluationHosPO, String type) {
		List<ServiceEvaluationHosPO> resultList = new ArrayList<ServiceEvaluationHosPO>();
		List<ServiceEvaluationHosPO> queryList = serviceEvaluationHosDAO.getCommentCountByDepartMent(serviceEvaluationHosPO);
		if(queryList != null && queryList.size() > 0){
			// 综合好评总数
			int goodSum = 0;
			for (ServiceEvaluationHosPO po : queryList) {
				// 综合好评=好评+中评
				int goodNum = po.getGoodNum() + po.getCommonNum();
				goodSum += goodNum;
				po.setCount(goodNum);
			}
			if(queryList.size() > 10){// 统计结果科室超过10个，则取出前10个+其他合计
				Collections.sort(queryList, new RateComparator());// 按数量排序
				int first10Sum = 0;// 前10合计
				for(int i = 0; i < 10; i++){
					resultList.add(queryList.get(i));
					first10Sum += queryList.get(i).getCount();
				}
				ServiceEvaluationHosPO other = new ServiceEvaluationHosPO();
				other.setDepartmentName("其他");
				other.setCount(goodSum - first10Sum);// 其他总计综合好评数
				resultList.add(other);
			}else{
				resultList.addAll(queryList);
			}
		}
		return resultList;
	}
	
	/**
	 * 综合好评排序比较器
	 * @author 朱伟权
	 */
	class RateComparator implements Comparator<ServiceEvaluationHosPO>{

		@Override
		public int compare(ServiceEvaluationHosPO o1, ServiceEvaluationHosPO o2) {
			if(o1.getCount() > o2.getCount()){
				return -1;
			}else{
				return 1;
			}
		}
		
	}

	@Override
	public List<ServiceEvaluationHosPO> getCommentCountByDate(
			ServiceEvaluationHosPO serviceEvaluationHosPO, String type) {
		// 保存统计的结果，key为统计日期
		Map<String, ServiceEvaluationHosPO> dateCountMap = new HashMap<String, ServiceEvaluationHosPO>();
		
		// 查询时间区间的所有统计
		List<ServiceEvaluationHosPO> evaluationHosPOs = serviceEvaluationHosDAO.getCommentCountByDate(serviceEvaluationHosPO);
		if(evaluationHosPOs != null && evaluationHosPOs.size() > 0){
			for (ServiceEvaluationHosPO po : evaluationHosPOs) {
				int commentSum = po.getGoodNum() + po.getCommonNum() + po.getBadNum();
				if("1".equals(type)){// 好评
					po.setProportion(commentSum == 0 ? 0 : (double)po.getGoodNum() / (double)commentSum);
				}else if("2".equals(type)){// 中评
					po.setProportion(commentSum == 0 ? 0 : (double)po.getCommonNum() / (double)commentSum);
				}else if("3".equals(type)){// 差评
					po.setProportion(commentSum == 0 ? 0 : (double)po.getBadNum() / (double)commentSum);
				}
				dateCountMap.put(po.getCountDate(), po);
			}
		}
		
		java.util.Date sDate = DateUtil.str2Date(serviceEvaluationHosPO.getBeginDate(), "yyyy-MM-dd");
		java.util.Date eDate = DateUtil.str2Date(serviceEvaluationHosPO.getEndDate(), "yyyy-MM-dd");
		// 准备返回的区间统计
		List<ServiceEvaluationHosPO> resultList = new ArrayList<ServiceEvaluationHosPO>();
		List<java.util.Date> dates = DateUtil.getBetweenDates(sDate, eDate, true);
		for (java.util.Date date : dates) {// 循环开始日期和结束日期之间的所有日期，没有统计项的默认为0
			String tmpDate = DateUtil.date2Str(date, "yyyy-MM-dd");
			ServiceEvaluationHosPO po = null;
			if(dateCountMap.get(tmpDate) == null){
				po = new ServiceEvaluationHosPO();
				po.setCountDate(tmpDate);
				po.setPercentage("0");
			}else{
				po = dateCountMap.get(tmpDate);
			}
			if(po != null){
				po.setCountDate(po.getCountDate().substring(5));// 日期只截取月日，即“05-04”
				resultList.add(po);
			}
		}
		return resultList;
	}

	@Override
	public List<ServiceEvaluationHosPO> getCommentCountByDoctor(
			ServiceEvaluationHosPO serviceEvaluationHosPO, String type) {
		List<ServiceEvaluationHosPO> queryList = serviceEvaluationHosDAO.getCommentCountByDoctor(serviceEvaluationHosPO);
		List<ServiceEvaluationHosPO> resultList = new ArrayList<ServiceEvaluationHosPO>();
		if(queryList != null && queryList.size() > 0){
			for (ServiceEvaluationHosPO po : queryList) {
				if("1".equals(type)){
					po.setCount(po.getGoodNum());
				}else if("2".equals(type)){
					po.setCount(po.getCommonNum());
				}else if("3".equals(type)){
					po.setCount(po.getBadNum());
				}
				if(po.getCount() > 0){
					resultList.add(po);// 统计不为0的才添加到结果list
				}
			}
		}
		return resultList;
	}

}
