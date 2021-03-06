package com.catic.mobilehos.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.catic.mobilehos.service.DepartmentsService;
import com.catic.mobilehos.service.ServiceResult;
import com.catic.mobilehos.service.vo.DepartmentVO;
import com.catic.mobilehos.service.vo.Page;
import com.catic.mobilehos.utils.ExportExcel;

/**
 * 后台科室配置管理
 * @author xieweipeng
 *
 */
public class DepartmentCfgMngAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	
	private final int DEFAULT_PAGESIZE = 10;
	
	private Page<DepartmentVO> pageBean;
	
	private DepartmentsService departmentsService;
	private DepartmentVO departmentVO;
	private List<DepartmentVO> departmentVOList;
	private File excleFile;
	
	/**
	 * 科室ID
	 */
	private String id;
	
	private String department_id;
	
	/**
	 * 科室名
	 */
	private String name;
	
	/**
	 * 科室位置
	 */
	private String location;
	
	/**
	 * 当前页
	 */
	private int page;
	
	/**
	 * @Title: getDepartments 
	 * @Description: TODO 分页显示科室数据
	 * @param @return    
	 * @return String    
	 * @throws
	 */
	public String getDepartments(){
		try{
			String status=request.getParameter("status");
//			if(name!=null){
//				this.name = new String(this.name.getBytes("ISO-8859-1"),"utf-8");
//			}
//			if(location!=null){
//				this.location =  new String(this.location.getBytes("ISO-8859-1"),"utf-8");
//			}
			if(StringUtils.isBlank(status)){
				status="2";
			}
			pageBean = this.departmentsService.queryDepartmentsListByParas(name
					, location, Integer.parseInt(status), department_id, page, DEFAULT_PAGESIZE);
			Map<String, String> paras = new TreeMap<String, String>();
			paras.put("name",this.name);
			paras.put("location",this.location);
			String baseUrl = "config/department/getDepartments";
			pageBean.setQueryUrl(baseUrl, paras);
			return SUCCESS;
		}catch(Exception ex){
			log.error(null, ex);
			return ERROR;
		}
		
	}
	public String toUpdate(){
		//添加
		if(id==null){
			
			departmentVO=new DepartmentVO();
		}else{
			//编辑
		departmentVO=this.departmentsService.findDepVOById(id);
		}
		//查找一级科室
		departmentVOList= departmentsService.findOneGradeDeptVO();
	   
		return "toUpdate";
	}
	
	/**
	 * 
	 * 描述：添加或者更新科室
	 * @author yjj
	 * @date 2016-11-24
	 */
	public String saveDepartment(){
		ServiceResult sr=null;
		try{
			/*departmentVO=new DepartmentVO();*/
			/*if(departmentVO.getId()==0){
				departmentVO.setId(0);
			}else{
				departmentVO.setId(Integer.parseInt(id));
			}*/
		/*	String departmentName=request.getParameter("departmentName");
			String departmentId=request.getParameter("departmentId");
			String parentId=request.getParameter("parentId");
			String departmentAddr=request.getParameter("departmentAddr");
			String introduction=request.getParameter("introduction");
			String phone=request.getParameter("phone");
			departmentVO.setDepartmentName(departmentName);
			departmentVO.setDepartmentId(departmentId);
			departmentVO.setParentId(parentId);
			departmentVO.setDepartmentAddr(departmentAddr);
			departmentVO.setIntroduction(introduction);
			departmentVO.setPhone(phone);*/	
			sr = this.departmentsService.saveOrUpdaeDepartment(departmentVO);
			
			//插入
			/*if(departmentVO.getId()==0 &&d==null){
				 sr = this.departmentsService.saveOrUpdaeDepartment(departmentVO);
			}else if(departmentVO.getId()!=0&&(d==null||departmentVO.getId()==d.getId())){
				//修改 
				
				sr = this.departmentsService.saveOrUpdaeDepartment(departmentVO);
			}else{
				
				 sr = ServiceResult.getFailedInstance("","科室编号已存在");
			}
		*/
			if (!sr.isSuccess()){				
				this.addActionError(sr.getErrMsg());
				writeServiceReuslt(sr);
				return "error";
			}
			
			return "saveDepartment";
			
		}catch(Exception ex){
			log.error(null, ex);
			writeServiceReuslt(sr);
			return "error";
		}
		
	}
	
	/**
	 * 
	 * 描述：验证科室编号是否存在
	 * @author yjj
	 * @date 2016-11-29
	 */
	public void validateCode(){
		String departmentId=request.getParameter("departmentId");
		ServiceResult sr=null;
		DepartmentVO validate=departmentsService.findDepVOByCode(departmentId);
		if(validate==null){
			sr=ServiceResult.getSucInstance();
		}else if(StringUtils.isNotBlank(id)&&id.equals(validate.getId()+"")){
			sr=ServiceResult.getSucInstance();
		}else{
			sr= ServiceResult.getFailedInstance("","科室编号已存在");
		}
		
		if (!sr.isSuccess()){
			this.addActionError(sr.getErrMsg());
			writeServiceReuslt(sr);
		}
		
	}
	
	//获取所有科室信息 用于下拉框
	public void findAllDeptment(){
		List<DepartmentVO> list = departmentsService.findAllDeptVO();
		if (null == list || list.isEmpty()) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("result", "0");
			writeJSON(jsonObject);
		} else {
			JSONArray array = JSONArray.fromObject(list);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("result", "1");
			jsonObject.put("depts", array);
			writeJSON(jsonObject);
		}
	}
	
	//获取所有科室信息 Ztree
	public void findAllDeptmentTree() throws IOException{
		List<DepartmentVO> list = departmentsService.findAllDeptVO();
		List<Map<String,Object>> mapList=new ArrayList<Map<String,Object>>();
		Map<String,Object> items = new HashMap<String,Object>();
		items.put("id", "0");//id属性  ，数据传递  
		items.put("name", "全部科室");
		items.put("value", "");
		items.put("isParent", "true");
		items.put("open","true");
		mapList.add(items);
		for (DepartmentVO depart : list) {
			 Map<String,Object> item = new HashMap<String,Object>();
			 item.put("id", depart.getDepartmentId());//id属性  ，数据传递  
			 item.put("pId", depart.getParentId());//id属性  ，数据传递  
			 item.put("name", depart.getDepartmentName());
			 item.put("value", depart.getDepartmentId());
			 mapList.add(item);
		}
		JSONArray json = JSONArray.fromObject(mapList);//转成json格式  
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(json.toString());  
		response.getWriter().flush(); 
		response.getWriter().close();  
//		writeJSON(json);
	}
	
	public void delDepartment(){
		boolean result =departmentsService.deleteDepartment(id);
		if(result){
			JSONObject json=new JSONObject();
			json.put("result",0);
			writeJSON(json);
		}else{
			JSONObject json=new JSONObject();
			json.put("result",1);
			writeJSON(json);
		}
		
	}
	
	//导入科室信息 
	public String readXls() throws IOException{
		try {
			Workbook workBook=null;
			InputStream is = new FileInputStream(excleFile);
			String type = request.getParameter("type");
			if(type.toLowerCase().endsWith("xls")){  
				workBook=new HSSFWorkbook(is);  
	        }  
	        if(type.toLowerCase().endsWith("xlsx")){  
	        	workBook= new XSSFWorkbook(is);  
	        }  
			departmentsService.readXls(workBook);
			return SUCCESS;
		} catch (FileNotFoundException e) {
			log.error(e);
			return ERROR;
		}
	}
	
	//导出科室信息 
	public String exporDepartInfo(){
		List<DepartmentVO> lists = departmentsService.findAllDeptVO();
		List<List<Object>> list = new ArrayList<List<Object>>();
		for (DepartmentVO departVO : lists) {  
            List<Object> dataList = new ArrayList<Object>();  
            dataList.add(departVO.getDepartmentName() == null ? "":departVO.getDepartmentName());  
            dataList.add(departVO.getDepartmentId()==null ? "":departVO.getDepartmentId()+"");
            if(departVO.getParentId().equals("0")){
            	departVO.setParentId("无");
            }
            dataList.add(departVO.getParentId()==null ? "":departVO.getParentId()+"");
            if(departVO.getDepartment_status()==1){
            	dataList.add("正常");
            }else{
            	dataList.add("冻结");
            }
            dataList.add(departVO.getPhone()==null ? "":departVO.getPhone()+"");
            dataList.add(departVO.getDepartmentAddr()==null ? "":departVO.getDepartmentAddr()+"");
            dataList.add(departVO.getDisplay_no()==null ? "":departVO.getDisplay_no()+"");
            dataList.add(departVO.getIntroduction()==null ? "":departVO.getIntroduction()+"");
            dataList.add(departVO.getApp_reg_status()+"");
            list.add(dataList);  
        }  
		String[] headers={"科室名称","科室编号","上级科室编号","科室状态","联系方式","科室位置","科室排序","科室介绍","手机预约"};
		String fileName = "科室信息";
		ExportExcel.excelOut(headers, list,fileName,request,response);  
        return SUCCESS;
	}
	
	public Page<DepartmentVO> getPageBean() {
		return pageBean;
	}


	public void setPageBean(Page<DepartmentVO> pageBean) {
		this.pageBean = pageBean;
	}


	public int getPage() {
		return page;
	}


	public void setPage(int page) {
		this.page = page;
	}
	
	

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}

	public String getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(String department_id) {
		this.department_id = department_id;
	}
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}

	public DepartmentsService getDepartmentsService() {
		return departmentsService;
	}


	public void setDepartmentsService(DepartmentsService departmentsService) {
		this.departmentsService = departmentsService;
	}
	public DepartmentVO getDepartmentVO() {
		return departmentVO;
	}
	public void setDepartmentVO(DepartmentVO departmentVO) {
		this.departmentVO = departmentVO;
	}
	public List<DepartmentVO> getDepartmentVOList() {
		return departmentVOList;
	}
	public void setDepartmentVOList(List<DepartmentVO> departmentVOList) {
		this.departmentVOList = departmentVOList;
	}
	public File getExcleFile() {
		return excleFile;
	}
	public void setExcleFile(File excleFile) {
		this.excleFile = excleFile;
	}

}
