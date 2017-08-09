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

import com.catic.mobilehos.po.UserPO;
import com.catic.mobilehos.service.UserAmountStatisticsService;
import com.catic.mobilehos.utils.DateUtil;

/**  
 * 类说明:用户量统计action
 * @author 朱伟权 
 * 创建时间: 2017-5-4 16:40:08
 */
@Controller
@Scope("prototype")
public class UserAmountStatisticsAction extends com.catic.mobilehos.pay.action.BaseAction {

	private static final long serialVersionUID = 1L;
	
	@Resource
	private UserAmountStatisticsService userAmountStatisticsService;
	
	private static final String INCREMENT = "increment";// 增量
	private static final String ALLCOUNT = "allCount";// 总人数
	
	private String userType;// 用户类型
	private String beginDate;// 开始时间
	private String endDate;// 结束时间
	private String countType;// increment表示增量，allCount表示总人数
	private String userSource;// 用户来源，1表示微信，2表示支付宝服务窗
	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

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

	public String getCountType() {
		return countType;
	}

	public void setCountType(String countType) {
		this.countType = countType;
	}

	public String getUserSource() {
		return userSource;
	}

	public void setUserSource(String userSource) {
		this.userSource = userSource;
	}

	/**
	 * 获取用户量统计
	 */
	public void getUserAmountCount(){
		try{
			UserPO userPO = new UserPO();
			userPO.setUserType(userType);
			userPO.setBeginDate(beginDate);
			userPO.setEndDate(endDate);
			userPO.setUserSource(userSource);
			
			// 保存统计的结果，key为统计日期
			Map<String, UserPO> dateCountMap = new HashMap<String, UserPO>();
			
			int periodUserIncrement = 0;// 记录指定时间段内的用户增量总人数
			// 开始日期的用户数量
			int beginDateUserCount = 0;
			if(ALLCOUNT.equals(countType)){// 总人数
	//			beginDateUserCount = userAmountStatisticsService.getCountUser(null, null, null, new Timestamp(DateUtil.str2Date(beginDate, "yyyy-MM-dd").getTime()));
				beginDateUserCount = userAmountStatisticsService.getCountUser(userPO);
			}
			int tmpCount = beginDateUserCount;
			// 查询时间区间的所有统计
			List<UserPO> userPOs = userAmountStatisticsService.getUserDayCountByPeriod(userPO);
			if(userPOs != null && userPOs.size() > 0){
				for (UserPO po : userPOs) {
					dateCountMap.put(po.getCountDate(), po);
					periodUserIncrement += po.getCount();// 累加用户增量
				}
			}
			
			Date sDate = DateUtil.str2Date(beginDate, "yyyy-MM-dd");
			Date eDate = DateUtil.str2Date(endDate, "yyyy-MM-dd");
			// 准备返回的区间统计
			List<UserPO> resultList = new ArrayList<UserPO>();
			List<Date> dates = DateUtil.getBetweenDates(sDate, eDate, true);
			for (Date date : dates) {// 循环开始日期和结束日期之间的所有日期，没有统计项的默认为0
				String tmpDate = DateUtil.date2Str(date, "yyyy-MM-dd");
				UserPO po = null;
				if(INCREMENT.equals(countType)){// 增量
					if(dateCountMap.get(tmpDate) == null){
						po = new UserPO();
						po.setCountDate(tmpDate);
						po.setCount(0);// 统计默认为0
					}else{
						po = dateCountMap.get(tmpDate);
					}
				}else if(ALLCOUNT.equals(countType)){
					if(dateCountMap.get(tmpDate) != null){
						tmpCount += dateCountMap.get(tmpDate).getCount();// 累加当前循环日期的用户总人数
					}
					po = new UserPO();
					po.setCountDate(tmpDate);
					po.setCount(tmpCount);// 当前循环日期用户总人数
				}
				if(po != null){
					po.setCountDate(po.getCountDate().substring(5));// 日期只截取月日，即“05-04”
					resultList.add(po);
				}
			}
			
			if(ALLCOUNT.equals(countType)){// 总人数
				userPO.setBeginDate(null);
				// 查询当前总人数
				periodUserIncrement = userAmountStatisticsService.getCountUser(userPO);
			}
			
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("num", periodUserIncrement);
			resultMap.put("list", resultList);
			JSONObject json = JSONObject.fromObject(resultMap);
			this.jsonObj(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
 