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
import com.catic.mobilehos.po.TradePO;
import com.catic.mobilehos.service.TradeService;
import com.catic.mobilehos.utils.DateUtil;

/**  
 * 类说明:充值统计action
 * @author 朱伟权
 * 创建时间: 2017-5-9 下午5:22:26
 */
@Controller
@Scope("prototype")
public class RechargeStatisticsAction extends com.catic.mobilehos.pay.action.BaseAction {

	private static final long serialVersionUID = 1L;
	
	@Resource
	private TradeService tradeService;
	
	private String startTime;// 开始日期
	private String endTime;// 结束日期
	private String rechargeSource;// 充值来源，1表示微信，2表示支付宝
	private String countDateType;// 日期统计类型，day为按天，month为按月
	
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getRechargeSource() {
		return rechargeSource;
	}

	public void setRechargeSource(String rechargeSource) {
		this.rechargeSource = rechargeSource;
	}

	public String getCountDateType() {
		return countDateType;
	}

	public void setCountDateType(String countDateType) {
		this.countDateType = countDateType;
	}

	/**
	 * 获取按日期统计充值量结果
	 */
	public void getSumByDate(){
		try{
			TradePO tradePO = new TradePO();
			tradePO.setStartTime(startTime);
			tradePO.setEndTime(endTime);
			tradePO.setCountDateType(countDateType);
			tradePO.setRechargeSource(rechargeSource);
			
			// 日期转换格式
			String formatPattern = "yyyy-MM-dd";// 按日
			if("month".equals(countDateType)){// 按月
				formatPattern = "yyyy-MM";
				// 开始日期设置为开始月第一天
				tradePO.setStartTime(DateUtil.getFirstDayOfMonth(DateUtil.str2Date(startTime, "yyyy-MM")));
				// 结束日期设置为结束月最后一天
				tradePO.setEndTime(DateUtil.getLastDayOfMonth(DateUtil.str2Date(endTime, "yyyy-MM")));
			}
			
			// 保存统计的结果，key为统计日期
			Map<String, TradePO> dateCountMap = new HashMap<String, TradePO>();
			
			// 查询时间区间的所有统计
			List<TradePO> tradePOs = tradeService.getCountByDate(tradePO);
			if(tradePOs != null && tradePOs.size() > 0){
				for (TradePO po : tradePOs) {
					dateCountMap.put(po.getCountDate(), po);
				}
			}
			
			Date sDate = DateUtil.str2Date(startTime, formatPattern);
			Date eDate = DateUtil.str2Date(endTime, formatPattern);
			// 准备返回的区间统计
			List<TradePO> resultList = new ArrayList<TradePO>();
			List<Date> dates = DateUtil.getBetweenDates(sDate, eDate, true);
			if("month".equals(countDateType)){
				dates = DateUtil.getBetweenMonths(sDate, eDate, true);
			}
			for (Date date : dates) {// 循环开始日期和结束日期之间的所有日期，没有统计项的默认为0
				String tmpDate = DateUtil.date2Str(date, formatPattern);
				TradePO po = null;
				if(dateCountMap.get(tmpDate) == null){
					po = new TradePO();
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
			TradePO tradePO = new TradePO();
			tradePO.setStartTime(startTime);
			tradePO.setEndTime(endTime);
			
			// key为分组名称，value为对应的统计结果
			Map<String, TradePO> queryResultMap = new HashMap<String, TradePO>();
			// 按来源统计结果
			List<TradePO> queryList = tradeService.getCountBySource(tradePO);
			List<TradePO> resultList = new ArrayList<TradePO>();
			// 记录时间区间内的充值累计数量
			int periodCount = 0;
			if(queryList != null && queryList.size() > 0){
				for (TradePO po : queryList) {
					periodCount += po.getCount();
					queryResultMap.put(po.getRechargeSource(), po);
				}
			}
			
			// 查询出数据字典所有的业务来源
			List<PayDictionary> list = payDictionaryBiz.findAll("business_source");
			if(list != null && list.size() > 0){
				for (PayDictionary payDictionary : list) {
					TradePO tmpPo = queryResultMap.get(payDictionary.getCodeNo());
					if(tmpPo == null){
						tmpPo = new TradePO();
						tmpPo.setCount(0);
					}
					tmpPo.setRechargeSource(payDictionary.getCodeName());
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
 