package com.catic.mobilehos.action;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.catic.mobilehos.po.EmployeePO;
import com.catic.mobilehos.service.EmployeeService;
import com.catic.mobilehos.service.vo.Page;
import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;

@Controller
@Scope("prototype")
public class EmployeeAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6058407790331319781L;
	private final int DEFAULT_PAGESIZE =5;
	
	private Page<EmployeePO> pageBean;
	private int page;
	private String pageNo;
	private EmployeeService employeeService;
	private String employeesName;
	private List<EmployeePO> employeePOList;
	private List<EmployeePO> employeePOList1;
	private EmployeePO employeePO;
	private String selectOrderNumber;
	private String name;
	private String sex;
	private String department;
	private String msg;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public Page<EmployeePO> getPageBean() {
		return pageBean;
	}
	public void setPageBean(Page<EmployeePO> pageBean) {
		this.pageBean = pageBean;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	

	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getPageNo() {
		return pageNo;
	}
	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}
	public String getSelectOrderNumber() {
		return selectOrderNumber;
	}
	public void setSelectOrderNumber(String selectOrderNumber) {
		this.selectOrderNumber = selectOrderNumber;
	}
	public EmployeeService getEmployeeService() {
		return employeeService;
	}
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public String getEmployeesName() {
		return employeesName;
	}
	public void setEmployeesName(String employeesName) {
		this.employeesName = employeesName;
	}
	public List<EmployeePO> getEmployeePOList() {
		return employeePOList;
	}
	public void setEmployeePOList(List<EmployeePO> employeePOList) {
		this.employeePOList = employeePOList;
	}
	
	public EmployeePO getEmployeePO() {
		return employeePO;
	}
	public void setEmployeePO(EmployeePO employeePO) {
		this.employeePO = employeePO;
	}
	public List<EmployeePO> getEmployeePOList1() {
		return employeePOList1;
	}
	public void setEmployeePOList1(List<EmployeePO> employeePOList1) {
		this.employeePOList1 = employeePOList1;
	}
	public String getAllEmployees(){
		try {
			this.employeePOList=this.employeeService.selectEmployeePOs();
			return "getAllEmployees";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ERROR;		
	}
	public String getEmployeeList(){
		EmployeePO employeePO1=new EmployeePO();
		//page=1;
		
		if(StringUtils.isBlank(pageNo)){
				pageNo="1";
			}
		System.out.println("姓名："+name);
		System.out.println("sex："+sex);
		System.out.println("department："+department);
		employeePO1.setEmployeeName(name);
		employeePO1.setSex(sex);
		employeePO1.setDepartment(department);
		System.out.println(employeePO1.getEmployeeName());
		pageBean=this.employeeService.selectEmployeePOList(employeePO1,page,DEFAULT_PAGESIZE);	
		employeePOList=pageBean.getCurPageData();
		Map<String, String> paras = new TreeMap<String, String>();
		/*paras.put("infotype", infotype == 0 ? null :String.valueOf(infotype));
		paras.put("infocatcode", infocatcode);
		paras.put("startExpDate", startexpdate);
		paras.put("endExpDate", endexpdate);*/
		paras.put("name", name);
		paras.put("sex", sex);
		paras.put("department", department);
		String baseUrl = "employeesAction/getEmployeeList";
		pageBean.setQueryUrl(baseUrl, paras);
		/*JSONArray jarr = JSONArray.fromObject(employeePOList);
		this.writeJSON(jarr);*/
		
		for (EmployeePO po : employeePOList) {
			System.out.println("数据"+po.getEmployeeName()+po.getSex()+po.getDepartment()+po.getSalary());
		}
		return SUCCESS;
		
	}
	public String getEmployeesByName(){
		try {
			System.out.println(employeesName);
			this.employeePOList1=this.employeeService.selectEmployeePOByName(employeesName);
			return "employess";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ERROR;	
	}
	public String addEmployee(){
		try{
			System.out.println(employeePO.getEmployeeName());
			System.out.println(employeePO.getSex());
			System.out.println(employeePO.getSalary());
			boolean b=this.employeeService.addEmployee(employeePO);
			if(b){
				System.out.println("添加成功！");
				//this.employeePOList=this.employeeService.selectEmployeePOs();
				msg="success";
				//request.setAttribute("msg", msg);
				return "addEmployeeSuccess";
				}
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				System.out.println("添加失败");
				}
		return ERROR;
	}
	public String updateEmployee(){
		String selectOrderNumber = request.getParameter("selectOrderNumber");
		System.out.println(selectOrderNumber);
		int number=Integer.parseInt(selectOrderNumber);
		this.employeePOList=this.employeeService.selectEmployeePOs();
		for (EmployeePO po : employeePOList) {
			if (po.getEmployeeId()==number)
				employeePO = po;
		}
		request.setAttribute("employeePO", employeePO);
		request.setAttribute("employeeId", number);
		return "updateEmployeeSuccess";		
	}
	public String updateEmployeeSubmit(){	
		System.out.println(employeePO.getEmployeeId());
		System.out.println(employeePO.getEmployeeName());
		System.out.println(employeePO.getPassword());
		System.out.println(employeePO.getSex());
		System.out.println(employeePO.getDepartment());
		System.out.println(employeePO.getSalary());
		boolean b=this.employeeService.updateEmployee(employeePO);
		if(b){
			System.out.println("修改成功！");
			EmployeePO employeePO2=new EmployeePO();			
			pageBean=this.employeeService.selectEmployeePOList(employeePO2,page,DEFAULT_PAGESIZE);
			return "updateEmployeeSubmitSuccess";
		}
		return ERROR;
		
	}
	public String deleteEmployee(){		
		String selectOrderNumber1 = request.getParameter("selectOrderNumber");
		System.out.println(selectOrderNumber1);
		int number=Integer.parseInt(selectOrderNumber1);
		boolean b=this.employeeService.deleteEmployee(number);
		if(b){
			System.out.println("删除成功！");
			EmployeePO employeePO2=new EmployeePO();
			
			pageBean=this.employeeService.selectEmployeePOList(employeePO2,page,DEFAULT_PAGESIZE);
			//getAllEmployees();
			return "deleteEmployeeSuccess";
		}
		return ERROR;
	}
	
}
