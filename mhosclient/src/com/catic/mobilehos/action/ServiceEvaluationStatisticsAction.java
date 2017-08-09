package com.catic.mobilehos.action;  

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.catic.mobilehos.po.ServiceEvaluationHosPO;
import com.catic.mobilehos.po.TableRowData;
import com.catic.mobilehos.service.DepartmentsService;
import com.catic.mobilehos.service.ServiceEvaluationHosService;
import com.catic.mobilehos.service.vo.DepartmentVO;
import com.catic.mobilehos.utils.DateUtil;

/**  
 * 类说明:服务评价统计action
 * @author 朱伟权
 * 创建时间: 2017-5-10 下午2:16:39
 */
@Controller
@Scope("prototype")
public class ServiceEvaluationStatisticsAction extends com.catic.mobilehos.pay.action.BaseAction {

	private static final long serialVersionUID = 1L;
	
	@Resource
	private ServiceEvaluationHosService serviceEvaluationHosService;
	@Resource
	private DepartmentsService departmentsService;
	
	private String beginDate;// 开始日期
	private String endDate;// 结束日期
	private String type;// 评价等级，0为综合，1为好评，2为中评，3为差评
	private String departmentId;// 科室id
	private String doctorId;// 医生id
	private String countType;// 统计类型，department表示按科室/doctor表示医生
	private String countDateType;// 日期统计类型，day为按天，month为按月
	private String evaluateSource;// 评价来源
	
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

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getCountDateType() {
		return countDateType;
	}

	public void setCountDateType(String countDateType) {
		this.countDateType = countDateType;
	}

	public String getEvaluateSource() {
		return evaluateSource;
	}

	public void setEvaluateSource(String evaluateSource) {
		this.evaluateSource = evaluateSource;
	}

	public String getCountType() {
		return countType;
	}

	public void setCountType(String countType) {
		this.countType = countType;
	}

	/**
	 * 查询服务评价趋势统计（折线图）
	 */
	public void getCommentCountByDate(){
		try{
			ServiceEvaluationHosPO serviceEvaluationHosPO = new ServiceEvaluationHosPO();
			serviceEvaluationHosPO.setBeginDate(beginDate);
			serviceEvaluationHosPO.setEndDate(endDate);
			serviceEvaluationHosPO.setCountDateType(countDateType);
			serviceEvaluationHosPO.setDepartmentId(departmentId);
			serviceEvaluationHosPO.setEvaluateSource(evaluateSource);
			
			// 日期转换格式
			String formatPattern = "yyyy-MM-dd";// 按日
			if("month".equals(countDateType)){// 按月
				formatPattern = "yyyy-MM";
				// 开始日期设置为开始月第一天
				serviceEvaluationHosPO.setBeginDate(DateUtil.getFirstDayOfMonth(DateUtil.str2Date(beginDate, formatPattern)));
				// 结束日期设置为结束月最后一天
				serviceEvaluationHosPO.setEndDate(DateUtil.getLastDayOfMonth(DateUtil.str2Date(endDate, formatPattern)));
			}
			
			// 保存统计的结果，key为统计日期
			Map<String, ServiceEvaluationHosPO> dateCountMap = new HashMap<String, ServiceEvaluationHosPO>();
			
			int commentSum = 0;// 评价总数
			// 查询时间区间的所有统计
			List<ServiceEvaluationHosPO> evaluationHosPOs = serviceEvaluationHosService.getCommentCount(serviceEvaluationHosPO);
			if(evaluationHosPOs != null && evaluationHosPOs.size() > 0){
				for (ServiceEvaluationHosPO po : evaluationHosPOs) {
					dateCountMap.put(po.getCountDate(), po);
					commentSum += (po.getGoodNum() + po.getCommonNum() + po.getBadNum());
				}
			}
			
			DepartmentVO department = null;// 选择的科室
			if(StringUtils.isNotBlank(departmentId)){
				department = departmentsService.findDepVOByCode(departmentId);
			}
			
			Date sDate = DateUtil.str2Date(beginDate, formatPattern);
			Date eDate = DateUtil.str2Date(endDate, formatPattern);
			// 准备返回的区间统计
			List<ServiceEvaluationHosPO> resultList = new ArrayList<ServiceEvaluationHosPO>();
			List<Date> dates = DateUtil.getBetweenDates(sDate, eDate, true);
			if("month".equals(countDateType)){
				dates = DateUtil.getBetweenMonths(sDate, eDate, true);
			}
			for (Date date : dates) {// 循环开始日期和结束日期之间的所有日期，没有统计项的默认为0
				String tmpDate = DateUtil.date2Str(date, formatPattern);
				ServiceEvaluationHosPO po = null;
				if(dateCountMap.get(tmpDate) == null){
					po = new ServiceEvaluationHosPO();
					po.setCountDate(tmpDate);
					po.setProportion(0);
				}else{
					po = dateCountMap.get(tmpDate);
					// 评价总数
					int sum = po.getGoodNum() + po.getCommonNum() + po.getBadNum();
					// 好评数=好评+中评
					int goodCount = po.getGoodNum() + po.getCommonNum();
					if(sum != 0){
						po.setProportion((double)goodCount / (double)sum);
					}
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
			resultMap.put("sum", commentSum);
			resultMap.put("list", resultList);
			JSONObject json = JSONObject.fromObject(resultMap);
			this.jsonObj(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 综合好评占比统计（饼状图）
	 */
	public void getCommentCountByDepartMent(){
		try{
			ServiceEvaluationHosPO serviceEvaluationHosPO = new ServiceEvaluationHosPO();
			serviceEvaluationHosPO.setBeginDate(beginDate);
			serviceEvaluationHosPO.setEndDate(endDate);
			
			List<ServiceEvaluationHosPO> resultList = serviceEvaluationHosService.getCommentCountByDepartMent(serviceEvaluationHosPO, type);
			int goodSum = 0;
			for (ServiceEvaluationHosPO po : resultList) {
				goodSum += po.getCount();
			}
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("sum", goodSum);
			resultMap.put("list", resultList);
			JSONObject json = JSONObject.fromObject(resultMap);
			this.jsonObj(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取按日/按月 and 按科室分组统计结果（业务量统计，表格）
	 */
	public void getCountTable(){
		try{
			ServiceEvaluationHosPO serviceEvaluationHosPO = new ServiceEvaluationHosPO();
			serviceEvaluationHosPO.setBeginDate(beginDate);
			serviceEvaluationHosPO.setEndDate(endDate);
			serviceEvaluationHosPO.setDepartmentId(departmentId);
			serviceEvaluationHosPO.setEvaluateSource(evaluateSource);
			serviceEvaluationHosPO.setCountType(countType);
			serviceEvaluationHosPO.setCountDateType(countDateType);
			
			// 日期转换格式
			String formatPattern = "yyyy-MM-dd";// 按日
			if("month".equals(countDateType)){// 按月
				formatPattern = "yyyy-MM";
				// 开始日期设置为开始月第一天
				serviceEvaluationHosPO.setBeginDate(DateUtil.getFirstDayOfMonth(DateUtil.str2Date(beginDate, formatPattern)));
				// 结束日期设置为结束月最后一天
				serviceEvaluationHosPO.setEndDate(DateUtil.getLastDayOfMonth(DateUtil.str2Date(endDate, formatPattern)));
			}
			
			// 保存统计的结果，key为统计日期#分组id
			Map<String, ServiceEvaluationHosPO> dateCountMap = new HashMap<String, ServiceEvaluationHosPO>();
			// 查询时间区间的所有统计
			List<ServiceEvaluationHosPO> serviceEvaluationHosPOs = serviceEvaluationHosService.getCommentCount(serviceEvaluationHosPO);
			if(serviceEvaluationHosPOs != null && serviceEvaluationHosPOs.size() > 0){
				for (ServiceEvaluationHosPO po : serviceEvaluationHosPOs) {
					dateCountMap.put(po.getCountDate() + "#" + po.getDepartmentId(), po);
				}
			}
			
			DepartmentVO department = null;// 选择的科室
			if(StringUtils.isNotBlank(departmentId)){
				department = departmentsService.findDepVOByCode(departmentId);
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
			
			// 查询出所有的科室
			List<DepartmentVO> departments = departmentsService.findAllDeptVO();
			if(departments != null && departments.size() > 0){
				for(DepartmentVO vo : departments){
					if(StringUtils.isNotBlank(departmentId) 
							&& !departmentId.equals(vo.getDepartmentId())){
						// 选择了指定的科室，则排除其他科室的统计
						continue;
					}
					TableRowData rowData = new TableRowData();
					rowData.setRowName(vo.getDepartmentName());// 行名称
					List<String> datas = new ArrayList<String>();
					rowData.setRowData(datas);// 行数据
					dataList.add(rowData);
					int dataSum = 0;// 综合好评量总计
					for (Date date : dates) {// 循环开始日期和结束日期之间的所有日期，没有统计项的默认为0
						String tmpDate = DateUtil.date2Str(date, formatPattern);
						ServiceEvaluationHosPO tmpPo = dateCountMap.get(tmpDate + "#" + vo.getDepartmentId());
						if(tmpPo == null){
							datas.add("0");
						}else{
							// 综合好评=好评+中评
							int goodNum = tmpPo.getGoodNum() + tmpPo.getCommonNum();
							datas.add(goodNum + "");
							dataSum += goodNum;
						}
					}
					rowData.setDataSum(dataSum);
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
	
}
 