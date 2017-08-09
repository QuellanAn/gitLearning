package com.catic.mobilehos.action;  

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.catic.mobilehos.pay.entity.PayDictionary;
import com.catic.mobilehos.po.AppRegOrdersPO;
import com.catic.mobilehos.po.DoctorPO;
import com.catic.mobilehos.po.TableRowData;
import com.catic.mobilehos.service.AppRegOrderService;
import com.catic.mobilehos.service.DepartmentsService;
import com.catic.mobilehos.service.DoctorsService;
import com.catic.mobilehos.service.vo.DepartmentVO;
import com.catic.mobilehos.utils.DateUtil;

/**  
 * 类说明:预约挂号统计（当日挂号统计）action
 * @author 朱伟权
 * 创建时间: 2017-5-8 上午11:23:31
 */
@Controller
@Scope("prototype")
public class AppointmentStatisticsAction extends com.catic.mobilehos.pay.action.BaseAction {

	private static final long serialVersionUID = 1L;
	
	@Resource
	private AppRegOrderService appRegOrderService;
	@Resource
	private DepartmentsService departmentsService;
	@Resource
	private DoctorsService doctorsService;
	
	private String beginDate;// 开始时间
	private String endDate;// 结束时间
	private String type;// 0为预约，1为挂号
	private String departmentCode;// 科室id
	private String regSource;// 挂号来源，1表示微信，2表示支付宝
	private String countType;// 统计类型，day为按天，month为按月
	private String countDateType;// 日期统计类型，day为按天，month为按月
	
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getRegSource() {
		return regSource;
	}
	public void setRegSource(String regSource) {
		this.regSource = regSource;
	}
	public String getCountType() {
		return countType;
	}
	public void setCountType(String countType) {
		this.countType = countType;
	}
	public String getCountDateType() {
		return countDateType;
	}
	public void setCountDateType(String countDateType) {
		this.countDateType = countDateType;
	}
	
	/**
	 * 获取按日/按月分组统计结果（趋势分析，折线图）
	 */
	public void getCountByDate(){
		try{
			AppRegOrdersPO appRegOrdersPO = new AppRegOrdersPO();
			appRegOrdersPO.setType(type);
			appRegOrdersPO.setBeginDate(beginDate);
			appRegOrdersPO.setEndDate(endDate);
			appRegOrdersPO.setDepartmentId(departmentCode);
			appRegOrdersPO.setRegSource(regSource);
			appRegOrdersPO.setCountDateType(countDateType);
			
			// 日期转换格式
			String formatPattern = "yyyy-MM-dd";// 按日
			if("month".equals(countDateType)){// 按月
				formatPattern = "yyyy-MM";
				// 开始日期设置为开始月第一天
				appRegOrdersPO.setBeginDate(DateUtil.getFirstDayOfMonth(DateUtil.str2Date(beginDate, "yyyy-MM")));
				// 结束日期设置为结束月最后一天
				appRegOrdersPO.setEndDate(DateUtil.getLastDayOfMonth(DateUtil.str2Date(endDate, "yyyy-MM")));
			}
			
			// 保存统计的结果，key为统计日期
			Map<String, AppRegOrdersPO> dateCountMap = new HashMap<String, AppRegOrdersPO>();
			// 统计区间总人数
			int periodSum = 0;
			
			// 查询时间区间的所有统计
			List<AppRegOrdersPO> appRegOrdersPOs = appRegOrderService.getCountByDate(appRegOrdersPO);
			if(appRegOrdersPOs != null && appRegOrdersPOs.size() > 0){
				for (AppRegOrdersPO po : appRegOrdersPOs) {
					dateCountMap.put(po.getCountDate(), po);
					periodSum += po.getCount();
				}
			}
			
			DepartmentVO department = null;// 选择的科室
			if(StringUtils.isNotBlank(departmentCode)){
				department = departmentsService.findDepVOByCode(departmentCode);
			}
			
			Date sDate = DateUtil.str2Date(beginDate, formatPattern);
			Date eDate = DateUtil.str2Date(endDate, formatPattern);
			// 准备返回的区间统计
			List<AppRegOrdersPO> resultList = new ArrayList<AppRegOrdersPO>();
			List<Date> dates = DateUtil.getBetweenDates(sDate, eDate, true);
			if("month".equals(countDateType)){
				dates = DateUtil.getBetweenMonths(sDate, eDate, true);
			}
			for (Date date : dates) {// 循环开始日期和结束日期之间的所有日期，没有统计项的默认为0
				String tmpDate = DateUtil.date2Str(date, formatPattern);
				AppRegOrdersPO po = null;
				if(dateCountMap.get(tmpDate) == null){
					po = new AppRegOrdersPO();
					po.setCountDate(tmpDate);
					po.setCount(0);// 统计默认为0
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
			resultMap.put("department", department == null ? "" : department.getDepartmentName());
			resultMap.put("sum", periodSum);
			resultMap.put("list", resultList);
			JSONObject json = JSONObject.fromObject(resultMap);
			this.jsonObj(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取以科室/来源为分组的统计（占比分析，饼状图）
	 */
	public void getCountByDepartment(){
		try{
			AppRegOrdersPO appRegOrdersPO = new AppRegOrdersPO();
			appRegOrdersPO.setType(type);
			appRegOrdersPO.setBeginDate(beginDate);
			appRegOrdersPO.setEndDate(endDate);
			appRegOrdersPO.setRegSource(regSource);
			appRegOrdersPO.setCountType(countType);
			
			// key为分组名称，value为对应的统计结果
			Map<String, AppRegOrdersPO> queryResultMap = new HashMap<String, AppRegOrdersPO>();
			// 按科室/来源统计结果
			List<AppRegOrdersPO> queryList = appRegOrderService.getCountByDepartment(appRegOrdersPO);
			List<AppRegOrdersPO> resultList = new ArrayList<AppRegOrdersPO>();
			// 记录时间区间内的预约累计数量
			int periodCount = 0;
			if(queryList != null && queryList.size() > 0){
				for (AppRegOrdersPO po : queryList) {
					periodCount += po.getCount();
					queryResultMap.put(po.getGroupName(), po);
				}
				
				if(queryList.size() > 10){// 统计结果科室超过10个，则取出前10个+其他合计
					Collections.sort(queryList, new RateComparator());// 按数量排序
					int first10Sum = 0;// 前10合计
					for(int i = 0; i < 10; i++){
						resultList.add(queryList.get(i));
						first10Sum += queryList.get(i).getCount();
					}
					AppRegOrdersPO other = new AppRegOrdersPO();
					other.setGroupName("其他");
					other.setCount(periodCount - first10Sum);// 其他总计挂号数
					resultList.add(other);
				}else{
					resultList.addAll(queryList);
				}
			}
			
			if("regSource".equals(countType)){// 按来源统计
				resultList.clear();
				// 查询出数据字典所有的业务来源
				List<PayDictionary> list = payDictionaryBiz.findAll("business_source");
				if(list != null && list.size() > 0){
					for (PayDictionary payDictionary : list) {
						AppRegOrdersPO tmpPo = queryResultMap.get(payDictionary.getCodeNo());
						if(tmpPo == null){
							tmpPo = new AppRegOrdersPO();
							tmpPo.setCount(0);
						}
						tmpPo.setGroupName(payDictionary.getCodeName());
						resultList.add(tmpPo);
					}
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
	
	/**
	 * 占比排序比较器
	 * @author 朱伟权
	 */
	class RateComparator implements Comparator<AppRegOrdersPO>{

		@Override
		public int compare(AppRegOrdersPO o1, AppRegOrdersPO o2) {
			if(o1.getCount() > o2.getCount()){
				return -1;
			}else{
				return 1;
			}
		}
		
	}
	
	/**
	 * 获取按日/按月 and 按科室/医生分组统计结果（业务量统计，表格）
	 */
	public void getCountTable(){
		try{
			AppRegOrdersPO appRegOrdersPO = new AppRegOrdersPO();
			appRegOrdersPO.setType(type);
			appRegOrdersPO.setBeginDate(beginDate);
			appRegOrdersPO.setEndDate(endDate);
			appRegOrdersPO.setDepartmentId(departmentCode);
			appRegOrdersPO.setRegSource(regSource);
			appRegOrdersPO.setCountType(countType);
			appRegOrdersPO.setCountDateType(countDateType);
			
			// 日期转换格式
			String formatPattern = "yyyy-MM-dd";// 按日
			if("month".equals(countDateType)){// 按月
				formatPattern = "yyyy-MM";
				// 开始日期设置为开始月第一天
				appRegOrdersPO.setBeginDate(DateUtil.getFirstDayOfMonth(DateUtil.str2Date(beginDate, "yyyy-MM")));
				// 结束日期设置为结束月最后一天
				appRegOrdersPO.setEndDate(DateUtil.getLastDayOfMonth(DateUtil.str2Date(endDate, "yyyy-MM")));
			}
			
			// 保存统计的结果，key为统计日期#分组id
			Map<String, AppRegOrdersPO> dateCountMap = new HashMap<String, AppRegOrdersPO>();
			Set<String> doctorIds = new HashSet<String>();
			
			// 查询时间区间的所有统计
			List<AppRegOrdersPO> appRegOrdersPOs = appRegOrderService.getCountByDate(appRegOrdersPO);
			if(appRegOrdersPOs != null && appRegOrdersPOs.size() > 0){
				for (AppRegOrdersPO po : appRegOrdersPOs) {
					dateCountMap.put(po.getCountDate() + "#" + po.getGroupName(), po);
					if("doctor".equals(countType)){
						doctorIds.add(po.getGroupName());
					}
				}
			}
			
			DepartmentVO department = null;// 选择的科室
			if(StringUtils.isNotBlank(departmentCode)){
				department = departmentsService.findDepVOByCode(departmentCode);
			}
			
			// 表头数据
			List<String> headers = new ArrayList<String>();
			List<TableRowData> dataList = new ArrayList<TableRowData>();
			
			Date sDate = DateUtil.str2Date(beginDate, formatPattern);
			Date eDate = DateUtil.str2Date(endDate, formatPattern);
			List<Date> dates = DateUtil.getBetweenDates(sDate, eDate, true);
			if("month".equals(countDateType)){
				dates = DateUtil.getBetweenMonths(sDate, eDate, true);
			}
			
			// 循环开始日期和结束日期之间的所有日期，得出表头数据
			for (Date date : dates) {
				String tmpDate = DateUtil.date2Str(date, formatPattern);
				if("day".equals(countDateType)){
					headers.add(tmpDate.substring(5));// 只截取日期的年月
				}else{
					headers.add(tmpDate);
				}
			}
			
			if("department".equals(countType)){
				// 查询出所有的科室
				List<DepartmentVO> departments = departmentsService.findAllDeptVO();
				if(departments != null && departments.size() > 0){
					for(DepartmentVO vo : departments){
						if(StringUtils.isNotBlank(departmentCode) 
								&& !departmentCode.equals(vo.getDepartmentId())){
							// 选择了指定的科室，则排除其他科室的统计
							continue;
						}
						TableRowData rowData = new TableRowData();
						rowData.setRowName(vo.getDepartmentName());// 行名称
						List<String> datas = new ArrayList<String>();
						rowData.setRowData(datas);// 行数据
						dataList.add(rowData);
						int dataSum = 0;// 挂号量总计
						for (Date date : dates) {// 循环开始日期和结束日期之间的所有日期，没有统计项的默认为0
							String tmpDate = DateUtil.date2Str(date, formatPattern);
							AppRegOrdersPO tmpPo = dateCountMap.get(tmpDate + "#" + vo.getDepartmentId());
							if(tmpPo == null){
								datas.add("0");
							}else{
								datas.add(tmpPo.getCount() + "");
								dataSum += tmpPo.getCount();
							}
						}
						rowData.setDataSum(dataSum);
					}
				}
			}else if("doctor".equals(countType)){
				if(StringUtils.isBlank(departmentCode)){// 没有选择科室，则查询全部医生
					for(String doctId : doctorIds){
						TableRowData rowData = new TableRowData();
						List<String> datas = new ArrayList<String>();
						rowData.setRowData(datas);// 行数据
						dataList.add(rowData);
						int dataSum = 0;// 挂号量总计
						for (Date date : dates) {// 循环开始日期和结束日期之间的所有日期，没有统计项的默认为0
							String tmpDate = DateUtil.date2Str(date, formatPattern);
							AppRegOrdersPO tmpPo = dateCountMap.get(tmpDate + "#" + doctId);
							if(tmpPo == null){
								datas.add("0");
							}else{
								rowData.setRowName(tmpPo.getDoctorName());// 行名称
								datas.add(tmpPo.getCount() + "");
								dataSum += tmpPo.getCount();
							}
						}
						rowData.setDataSum(dataSum);
					}
				}else{// 有选择科室，则返回该科室医生全部结果
					DoctorPO po = new DoctorPO();
					po.setDepartmentId(departmentCode);
					List<DoctorPO> doctorList = doctorsService.getDoctorByDepartmentId(po);
					if(doctorList != null && doctorList.size() > 0){
						for (DoctorPO doctorPO : doctorList) {
							TableRowData rowData = new TableRowData();
							List<String> datas = new ArrayList<String>();
							rowData.setRowName(doctorPO.getDoctorName());// 行名称
							rowData.setRowData(datas);// 行数据
							dataList.add(rowData);
							int dataSum = 0;// 挂号量总计
							for (Date date : dates) {// 循环开始日期和结束日期之间的所有日期，没有统计项的默认为0
								String tmpDate = DateUtil.date2Str(date, formatPattern);
								AppRegOrdersPO tmpPo = dateCountMap.get(tmpDate + "#" + doctorPO.getCode());
								if(tmpPo == null){
									datas.add("0");
								}else{
									datas.add(tmpPo.getCount() + "");
									dataSum += tmpPo.getCount();
								}
							}
							rowData.setDataSum(dataSum);
						}
					}
				}
			}
			
			if(dataList.size() == 0){// 没有统计结果数据
				TableRowData rowData = new TableRowData();
				rowData.setRowName("全部");// 行名称
				List<String> datas = new ArrayList<String>();
				rowData.setRowData(datas);// 行数据
				dataList.add(rowData);
				for(int i = 0; i < headers.size(); i++){
					datas.add("0");
				}
			}
			
			// 按挂号量总计排序
			Collections.sort(dataList, new DataSumComparator());
			if(dataList.size() > 50){// 如果结果超过50，截取前50
				dataList = dataList.subList(0, 50);
			}
			
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("department", department == null ? "" : department.getDepartmentName());
			resultMap.put("headers", headers);
			resultMap.put("list", dataList);
			JSONObject json = JSONObject.fromObject(resultMap);
			this.jsonObj(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 数量排序比较器
	 * @author 朱伟权
	 */
	class DataSumComparator implements Comparator<TableRowData>{

		@Override
		public int compare(TableRowData o1, TableRowData o2) {
			if(o1.getDataSum() > o2.getDataSum()){
				return -1;
			}else{
				return 1;
			}
		}
		
	}
	
	/**
	 * 获取以医生为分组的统计
	 */
	public void getCountByDoctor(){
		try{
			AppRegOrdersPO appRegOrdersPO = new AppRegOrdersPO();
			appRegOrdersPO.setType(type);
			appRegOrdersPO.setBeginDate(beginDate);
			appRegOrdersPO.setEndDate(endDate);
			appRegOrdersPO.setDepartmentId(departmentCode);
			List<AppRegOrdersPO> countResultList = appRegOrderService.getCountByDoctor(appRegOrdersPO);
			// 记录时间区间内的预约累计数量
			int periodCount = 0;
			if(countResultList != null && countResultList.size() > 0){
				for (AppRegOrdersPO po : countResultList) {
					periodCount += po.getCount();
				}
			}
			
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("num", periodCount);
			resultMap.put("list", countResultList);
			JSONObject json = JSONObject.fromObject(resultMap);
			this.jsonObj(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据科室id获取对应的所有医生信息
	 */
	public void getDoctorsByDepartmentId(){
		try{
			DoctorPO po = new DoctorPO();
			po.setDepartmentId(departmentCode);
			List<DoctorPO> doctorList = doctorsService.getDoctorByDepartmentId(po);
			JSONArray array = JSONArray.fromObject(doctorList);
			this.jsonObj(array.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
 