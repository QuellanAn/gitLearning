package com.catic.mobilehos.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.UUID;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.catic.mobilehos.po.DoctorPO;
import com.catic.mobilehos.service.DepartmentsService;
import com.catic.mobilehos.service.DoctorsService;
import com.catic.mobilehos.service.JobService;
import com.catic.mobilehos.service.ServiceResult;
import com.catic.mobilehos.service.vo.DepartmentVO;
import com.catic.mobilehos.service.vo.DoctorVO;
import com.catic.mobilehos.service.vo.Job;
import com.catic.mobilehos.service.vo.Page;
import com.catic.mobilehos.service.vo.VOPOConverter;
import com.catic.mobilehos.utils.DeleteFile;
import com.catic.mobilehos.utils.ExportExcel;
import com.catic.mobilehos.utils.ImageUtil;
import com.catic.mobilehos.utils.Upload;

/**
 * 后台医护人员管理的Action
 * @author WANG
 *
 */
public class DoctorMngAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	
	private DoctorsService doctorsService;
	private JobService jobService;
	
	private final String DEFAULT_HOSPITAL="巩义市人民医院";
	
	private final int DEFAULT_PAGESIZE = 10;
	
	private Page<DoctorVO> pageBean;
	
	private DepartmentsService departmentsService;
	
	private List<DepartmentVO> deptList = new ArrayList<DepartmentVO>();
	private List<Job>jobList;
	
	//医生ID
	private String doctorId;
	
	//医生对象
	private DoctorPO doctorPO;
	
	private DoctorVO doctorVO;
	/**
	 * 当前页
	 */
	private int page;
	/**
	 * 医生名
	 */
	private String name;
	
	protected List<File> file = null;
	protected List<String> fileFileName = null;
	
	public List<File> getFile() {
		return file;
	}

	public void setFile(List<File> file) {
		this.file = file;
	}

	public List<String> getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(List<String> fileFileName) {
		this.fileFileName = fileFileName;
	}
	/**
	 * 根据用户名搜索医生
	 */
	public String searchDoctors(){
		try {
		/*if(name!=null){
				this.name = new String(this.name.getBytes("ISO-8859-1"),"utf-8");
			}*/
			String departmentId=request.getParameter("departmentId");
			String job=request.getParameter("job");
			String status=request.getParameter("status");
			String integrity=request.getParameter("integrity");
			if(StringUtils.isNotBlank(job)){
				job=new String(job.getBytes("ISO-8859-1"),"utf-8");
			}
			String isExpert=request.getParameter("isExpert");
			
			doctorVO=new DoctorVO();
			if(StringUtils.isBlank(status)){
				doctorVO.setStatus(0);
			}else{
				doctorVO.setStatus(Integer.parseInt(status));
			}
			doctorVO.setIntegrity(integrity);
			doctorVO.setDoctorName(name);
			doctorVO.setDepartmentId(departmentId);
			doctorVO.setJob(job);
            if(StringUtils.isBlank(isExpert)){
            	doctorVO.setIsExpert(2);
			}else{
				doctorVO.setIsExpert(Integer.parseInt(isExpert));	
			}
				
			pageBean = doctorsService.getDoctorsList(doctorVO, page,DEFAULT_PAGESIZE);
			
			Map<String, String> paras = new TreeMap<String, String>();
			paras.put("name", this.name);
			paras.put("departmentId",departmentId);
			paras.put("job",job);
			String baseUrl = "config/doctor/searchDoctors";
			pageBean.setQueryUrl(baseUrl, paras);
			return SUCCESS;
		} catch (Exception e) {
			log.error(null, e);
			return ERROR;
		}
	}
	
	
	/**
	 * 
	 * 描述：/跳转至添加、更新界面
	 * @author yjj
	 * @date 2016-11-23
	 */
	public String toSaveOrUpdate(){
		if(StringUtils.isBlank(doctorId)){
			doctorVO=new DoctorVO();
		}else{
			doctorVO = doctorsService.getDoctorDetail(doctorId);
		}
		
		deptList = departmentsService.findAllDeptVO();
		jobList=jobService.getJobList();
		return "toSaveOrUpdate";
	}
	
	public void saveOrUpdateDoctor(){
		
		ServiceResult sr=null;
		DoctorVO doc=doctorsService.getDoctorDetailByCode(doctorVO.getCode());
		if(doc!=null&&!doc.getDoctorId().equals(doctorVO.getDoctorId())){
			
			sr = ServiceResult.getFailedInstance("","医生工号已存在");
			this.addActionError(sr.getErrMsg());
			writeServiceReuslt(sr);	
			return;
		}
		
		
		if(file != null){	
			
				DeleteFile.deleteFile(ROOTPATH+doctorVO.getPhoto());
				String uploadPath = Upload.upload(file, fileFileName,DOCTOR, 1);
				doctorVO.setPhoto(uploadPath.replaceAll("\\\\", "/"));
				File imgFile=new File(ROOTPATH+File.separator+doctorVO.getPhoto());	
				
				new ImageUtil().thumbnailImage(ROOTPATH+File.separator+doctorVO.getPhoto(),162,208,true);
				String imgName="thumb_"+doctorVO.getPhoto().substring(doctorVO.getPhoto().lastIndexOf("/")+1,
						doctorVO.getPhoto().length());
				uploadPath=doctorVO.getPhoto().substring(0,
						doctorVO.getPhoto().lastIndexOf("/")+1)+imgName;
				
				DeleteFile.deleteFile(ROOTPATH+doctorVO.getPhoto());
				doctorVO.setPhoto(uploadPath);
			
		}

		if(StringUtils.isBlank(doctorVO.getSpeciality())||
				StringUtils.isBlank(doctorVO.getPhoto())||
				StringUtils.isBlank(doctorVO.getIntroduction())){
			doctorVO.setIntegrity("缺失");
		}else{
			doctorVO.setIntegrity("完整");
		}
		
		//添加
		if(StringUtils.isBlank(doctorVO.getDoctorId())){
			doctorId=UUID.randomUUID().toString();
			doctorVO.setDoctorId(doctorId);
			sr=doctorsService.saveOrUpdateDoctor(doctorVO,0);
			
		}else{
		//修改
			//doctorVO.setDoctorId(doctorId);
			sr=doctorsService.saveOrUpdateDoctor(doctorVO,1);
		}
		
		if (!sr.isSuccess()){				
			this.addActionError(sr.getErrMsg());
			writeServiceReuslt(sr);	
		}
	}
	
	/**
	 * To updateDoctor's Page
	 */
	public String toUpdateDoctor() {
		doctorPO = doctorsService.getDoctorDetail(doctorId);
		deptList = departmentsService.findAllDeptVO();
		VOPOConverter<DoctorVO, DoctorPO> converter = new VOPOConverter<DoctorVO
				, DoctorPO>(DoctorVO.class, DoctorPO.class);
		doctorVO = converter.poToVO(doctorPO);
		return SUCCESS;
	}
	/**
	 *
	 * @Title: updateDoctor 
	 * @Description: TODO 更新
	 * @return String  success
	 * @throws
	 */
	public String updateDoctor(){
		doctorPO =  doctorsService.getDoctorDetail(doctorId);
		doctorPO.setDoctorName(doctorVO.getDoctorName());
		doctorPO.setIntroduction(doctorVO.getIntroduction());
		doctorPO.setDepartmentId(doctorVO.getDepartmentId());
		doctorPO.setJob(doctorVO.getJob());
		doctorPO.setSpeciality(doctorVO.getSpeciality());
		doctorsService.updateDoctorPO(doctorPO, doctorId);
		return SUCCESS;
	}
	
	public void delDoctor(){
		
		boolean result = doctorsService.deleteDoctor(doctorId);
		if (result) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("result", "0");
			writeJSON(jsonObject);
		} else {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("result", "1");
			writeJSON(jsonObject);
		}
	}
	
	public String addDoctor() {
		try {
			log.info("doctorVO.getDoctorName()"+doctorVO.getDoctorName());
			log.info("doctorVO.getDepartmentId():"+doctorVO.getDepartmentId());
			doctorPO = doctorVO;
			Random rand = new Random();
			doctorPO.setHospital(DEFAULT_HOSPITAL);
			doctorPO.setDoctorId(rand.nextInt(10)+"000D"+rand.nextInt(10));
			ServiceResult sr = doctorsService.saveDoctor(doctorPO);
			if(sr.isSuccess()){
				return SUCCESS;
			}
			this.addActionError(sr.getErrMsg());
			return ERROR;
		} catch (Exception e) {
			log.error(null,e);
			return ERROR;
		}
	}
	//生成职称的下拉列表
	public void findJobList(){
		
		jobList=jobService.getJobList();
		if (null == jobList || jobList.isEmpty()) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("result", "0");
			writeJSON(jsonObject);
		} else {
			JSONArray array = JSONArray.fromObject(jobList);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("result", "1");
			jsonObject.put("jobList", array);
			writeJSON(jsonObject);
		}
		
	}
	
	public void moveByOperate(){
		doctorVO=new DoctorVO();
		String tempId=null;
		String downId=null;
		pageBean = doctorsService.getDoctorsList(doctorVO, page,DEFAULT_PAGESIZE);
		String num=request.getParameter("num");
		String operate=request.getParameter("operate");
		String sortNum=request.getParameter("sortNum");
		if(num.equals("0")||num.equals(pageBean.getTotalRecord())){
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("result", "1");
			writeJSON(jsonObject);
		}else{
			DoctorPO doctorPO=doctorsService.findByUpDown(sortNum, operate);
			downId=doctorPO.getSortNumber();
			tempId=doctorPO.getSortNumber()+123;
			doctorsService.updateIdByOperate(tempId, downId);
			doctorsService.updateIdByOperate(downId, sortNum);
			doctorsService.updateIdByOperate(sortNum, tempId);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("result", "0");
			writeJSON(jsonObject);
		}
	}
	
	public String exportDoctorByExcle(){
		List<List<Object>> list = new ArrayList<List<Object>>();
		String xzsj = request.getParameter("xzsj");
		String sjArray[]=xzsj.split(",");
		for(int i=0;i<sjArray.length;i++){
			List<Object> dataList = new ArrayList<Object>();  
			String doctor=sjArray[i];
			doctorPO =  doctorsService.getDoctorDetail(doctor);
			dataList.add(doctorPO.getDoctorName() == null ? "":doctorPO.getDoctorName());  
            if(doctorPO.getSex()==1){
            	dataList.add("男");
            }else{
            	dataList.add("女");
            }
            dataList.add(doctorPO.getJobName()==null ? "":doctorPO.getJobName()+"");
            dataList.add(doctorPO.getDepartmentId()+""==null ? "":doctorPO.getDepartmentId()+"");
            dataList.add(doctorPO.getDeptName()==null ? "":doctorPO.getDeptName()+"");
            if(doctorPO.getIsExpert()==1){
            	dataList.add("是");
            }else{
            	dataList.add("否");
            }
            dataList.add(doctorPO.getCode()==null ? "":doctorPO.getCode()+"");
            dataList.add(doctorPO.getSortNumber()==null ? "":doctorPO.getSortNumber()+"");
            if(doctorPO.getStatus()==1){
            	dataList.add("正常");
            }else{
            	dataList.add("冻结");
            }
            dataList.add(doctorPO.getSpeciality()+""==null ? "":doctorPO.getSpeciality()+"");
            dataList.add(doctorPO.getIntroduction()+""==null ? "":doctorPO.getIntroduction()+"");
            list.add(dataList);  
		}
		String[] headers={"医生姓名","性别","职位","科室编号","科室名称","专家","工号","排序号","状态","专长","介绍"};
		String fileName = "医生信息";
		ExportExcel.excelOut(headers, list,fileName,request,response); 
		return SUCCESS;
	}
	
	public DoctorsService getDoctorsService() {
		return doctorsService;
	}

	public void setDoctorsService(DoctorsService doctorsService) {
		this.doctorsService = doctorsService;
	}
	
	
	public DepartmentsService getDepartmentsService() {
		return departmentsService;
	}

	public void setDepartmentsService(DepartmentsService departmentsService) {
		this.departmentsService = departmentsService;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the pageBean
	 */
	public Page<DoctorVO> getPageBean() {
		return pageBean;
	}

	/**
	 * @param pageBean the pageBean to set
	 */
	public void setPageBean(Page<DoctorVO> pageBean) {
		this.pageBean = pageBean;
	}

	/**
	 * @return the doctorVO
	 */
	public DoctorVO getDoctorVO() {
		return doctorVO;
	}

	/**
	 * @param doctorVO the doctorVO to set
	 */
	public void setDoctorVO(DoctorVO doctorVO) {
		this.doctorVO = doctorVO;
	}

	/**
	 * @return the doctorId
	 */
	public String getDoctorId() {
		return doctorId;
	}

	/**
	 * @return the doctorPO
	 */
	public DoctorPO getDoctorPO() {
		return doctorPO;
	}

	/**
	 * @param doctorPO the doctorPO to set
	 */
	public void setDoctorPO(DoctorPO doctorPO) {
		this.doctorPO = doctorPO;
	}
	
	/**
	 * @param doctorId the doctorId to set
	 */
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}

	

	
	/**
	 * @return the deptList
	 */
	public List<DepartmentVO> getDeptList() {
		return deptList;
	}

	/**
	 * @param deptList the deptList to set
	 */
	public void setDeptList(List<DepartmentVO> deptList) {
		this.deptList = deptList;
	}


	public JobService getJobService() {
		return jobService;
	}


	public void setJobService(JobService jobService) {
		this.jobService = jobService;
	}


	public List<Job> getJobList() {
		return jobList;
	}


	public void setJobList(List<Job> jobList) {
		this.jobList = jobList;
	}

	
}
