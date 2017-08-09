package com.catic.mobilehos.action;  

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.catic.mobilehos.pay.entity.PayDictionary;
import com.catic.mobilehos.po.ItemsPO;
import com.catic.mobilehos.service.ItemsService;
import com.catic.mobilehos.utils.DateUtil;

/**  
 * 类说明:缴费统计action
 * @author 朱伟权
 * 创建时间: 2017-5-10 上午10:11:52
 */
@Controller
@Scope("prototype")
public class PaymentStatisticsAction extends com.catic.mobilehos.pay.action.BaseAction {

	private static final long serialVersionUID = 1L;
	
	@Resource
	private ItemsService itemsService;
	
	private String beginDate;// 开始日期
	private String endDate;// 结束日期
	private String countDateType;// 日期统计类型，day为按天，month为按月
	private String paySource;// 缴费来源
	
	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getCountDateType() {
		return countDateType;
	}

	public void setCountDateType(String countDateType) {
		this.countDateType = countDateType;
	}

	public String getPaySource() {
		return paySource;
	}

	public void setPaySource(String paySource) {
		this.paySource = paySource;
	}

	/**
	 * 获取按日期统计缴费量结果
	 */
	public void getCountByDate(){
		try{
			ItemsPO itemsPO = new ItemsPO();
			itemsPO.setBeginDate(beginDate);
			itemsPO.setEndDate(endDate);
			itemsPO.setPaySource(paySource);
			itemsPO.setCountDateType(countDateType);
			
			// 日期转换格式
			String formatPattern = "yyyy-MM-dd";// 按日
			if("month".equals(countDateType)){// 按月
				formatPattern = "yyyy-MM";
				// 开始日期设置为开始月第一天
				itemsPO.setBeginDate(DateUtil.getFirstDayOfMonth(DateUtil.str2Date(beginDate, "yyyy-MM")));
				// 结束日期设置为结束月最后一天
				itemsPO.setEndDate(DateUtil.getLastDayOfMonth(DateUtil.str2Date(endDate, "yyyy-MM")));
			}
			
			// 保存统计的结果，key为统计日期，如“2017-05-10”
			Map<String, ItemsPO> dateCountMap = new HashMap<String, ItemsPO>();
			
			// 查询时间区间的所有统计
			List<ItemsPO> itemsPOs = itemsService.getCountByDate(itemsPO);
			if(itemsPOs != null && itemsPOs.size() > 0){
				for (ItemsPO po : itemsPOs) {
					dateCountMap.put(po.getCountDate(), po);
				}
			}
			
			Date sDate = DateUtil.str2Date(beginDate, formatPattern);
			Date eDate = DateUtil.str2Date(endDate, formatPattern);
			// 准备返回的区间统计
			List<ItemsPO> resultList = new ArrayList<ItemsPO>();
			List<Date> dates = DateUtil.getBetweenDates(sDate, eDate, true);
			if("month".equals(countDateType)){
				dates = DateUtil.getBetweenMonths(sDate, eDate, true);
			}
			for (Date date : dates) {// 循环开始日期和结束日期之间的所有日期，没有统计项的默认为0
				String tmpDate = DateUtil.date2Str(date, formatPattern);
				ItemsPO po = dateCountMap.get(tmpDate);// 当前循环日期缴费统计
				if(dateCountMap.get(tmpDate) == null){
					po = new ItemsPO();
					po.setCountDate(tmpDate);
					po.setCount(0);
				}else{
					po = dateCountMap.get(tmpDate);
				}
				if(po != null){
					if("day".equals(countDateType)){
						po.setCountDate(po.getCountDate().substring(5));// 日期只截取月日，即“05-04”
					}
					resultList.add(po);
				}
			}
			
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("list", resultList);
			JSONObject json = JSONObject.fromObject(resultMap);
			this.jsonObj(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取来源为分组的统计（占比分析，饼状图）
	 */
	public void getCountBySource(){
		try{
			ItemsPO itemsPO = new ItemsPO();
			itemsPO.setBeginDate(beginDate);
			itemsPO.setEndDate(endDate);
			
			// key为分组名称，value为对应的统计结果
			Map<String, ItemsPO> queryResultMap = new HashMap<String, ItemsPO>();
			// 按来源统计结果
			List<ItemsPO> queryList = itemsService.getCountBySource(itemsPO);
			List<ItemsPO> resultList = new ArrayList<ItemsPO>();
			// 记录时间区间内的缴费累计数量
			int periodCount = 0;
			if(queryList != null && queryList.size() > 0){
				for (ItemsPO po : queryList) {
					periodCount += po.getCount();
					queryResultMap.put(po.getPaySource(), po);
				}
			}
			
			// 查询出数据字典所有的业务来源
			List<PayDictionary> list = payDictionaryBiz.findAll("business_source");
			if(list != null && list.size() > 0){
				for (PayDictionary payDictionary : list) {
					ItemsPO tmpPo = queryResultMap.get(payDictionary.getCodeNo());
					if(tmpPo == null){
						tmpPo = new ItemsPO();
						tmpPo.setCount(0);
					}
					tmpPo.setPaySource(payDictionary.getCodeName());
					resultList.add(tmpPo);
				}
			}
			
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("num", periodCount);
			resultMap.put("list", resultList);
			JSONObject json = JSONObject.fromObject(resultMap);
			this.jsonObj(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
 