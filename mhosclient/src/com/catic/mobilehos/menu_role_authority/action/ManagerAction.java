package com.catic.mobilehos.menu_role_authority.action;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.catic.mobilehos.menu_role_authority.entity.Manager;
import com.catic.mobilehos.menu_role_authority.entity.Menu;
import com.catic.mobilehos.menu_role_authority.entity.Popedom;
import com.catic.mobilehos.menu_role_authority.entity.Role;
import com.catic.mobilehos.utils.Page;

/**
 * 管理员信息
 * @author YUXY
 *
 */
@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class ManagerAction extends BaseAction {
	
	private Manager manager;
	private List<Manager> list;
	private List<Role> roleList;
	private List<String> popedomList;
//	private List<Company> companyList;
	
	/**
	 * 验证组织机构是否存在系统用户
	 * 
	 * from LZW
	 */
	@SuppressWarnings("static-access")
	public void findByCyid(){
		try {
			String cyid = request.getParameter("cyid"); 
			manager = managerBiz.findByCyid(cyid);
			if(manager == null){
				result = resultBiz.getResult(resultBiz.SUCCESS);
			}else{
				jsonObject = JSONObject.fromObject(manager, config);
				result = resultBiz.getResult(resultBiz.FAIL, jsonObject.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = resultBiz.getResult(resultBiz.SYSTEM_BUSY);
		}finally{
			jsonObj(result.toJson());
		}
	} 
	
	/**
	 * 管理员登录
	 */
	@SuppressWarnings("static-access")
	public void login() {
		try {
			String account = request.getParameter("account");
			String password = request.getParameter("password");
			manager = new Manager(account, password);
			Manager manager1 = managerBiz.findManager(manager);
			if (manager1 != null) {
				request.getSession().setAttribute("roleId", manager1.getRoleId()+"");
//				Company company = companyBiz.findById(manager1.getCyid()+"");
				//**********
//				Customer customer=customerBiz.findById(manager1.getCid());
//				if(getSession().getAttribute(COMPANY)!=null||getSession().getAttribute(CUSTOMER)!=null){
//					getSession().removeAttribute(COMPANY);
//					getSession().removeAttribute(CUSTOMER);
//				}//**********
//				if(company != null){
//					manager1.setCompany(company);
//					//*********
//					getSession().setAttribute(COMPANY, company);
//				}
//				//**********
//				if(customer!=null){
//					manager1.setCustomer(customer);
//					getSession().setAttribute(CUSTOMER, customer);
//				}//**********
				roleList = roleBiz.findByMrid(manager1.getUserId());
				if(roleList.size()>0){
				 manager1.setRoleId(roleList.get(0).getRoleId());	
				 manager1.setRoleName(roleList.get(0).getName());
				}
			
				getSession().setAttribute(MANAGER, manager1);
				
				request.getSession().setAttribute("userName", manager1.getUserName());
				request.getSession().setAttribute("userId",  manager1.getUserId());
				request.getSession().setAttribute("result",  "login");
				
				//登录成功后进入首页，从数据库获得下拉菜单的信息。并存放到session中
				List<Menu> list = menuBiz.findMenu(manager1.getUserId());
				getSession().setAttribute(MENU, list);
				
				if(list.size() <= 0){
					result = resultBiz.getResult(resultBiz.MENU_NO,"账号没有权限，请向管理员申请权限！");
				}else{
					result = resultBiz.getResult(resultBiz.SUCCESS);
				}
				//result = resultBiz.getResult(resultBiz.SUCCESS);
			} else {
				result = resultBiz.getResult(resultBiz.PASSWORD_ERRO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = resultBiz.getResult(resultBiz.SYSTEM_BUSY);
		}finally{
			jsonObj(result.toJson());
		}
	}
	
	/**
	 * 查询全部管理员信息
	 */
	public String findAll() {
		try {
			if(StringUtils.isBlank(pageNo)){
		    	pageNo = "1";
		    }
			getSession().setAttribute("keywords", keywords);
			list = managerBiz.findAll(keywords, null);
			page = new Page(Integer.parseInt(pageNo), DEFAULT_PAGE_SIZE, list.size());
			list = managerBiz.findAll(keywords, page);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return NONE;
	}
	
	/**
	 * 翻页
	 */
	public String pageing() {
		try {
			if(StringUtils.isBlank(pageNo)){
		    	pageNo = "1";
		    }
			keywords = (String) getSession().getAttribute("keywords");
			list = managerBiz.findAll(keywords, null);
			page = new Page(Integer.parseInt(pageNo), DEFAULT_PAGE_SIZE, list.size());
			list = managerBiz.findAll(keywords, page);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return NONE;
	}
	
	/**
	 * 进入添加管理员信息界面
	 */
	public String enterSave() {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "save";
	}
	
	/**
	 * 添加管理员信息
	 */
	public String save() {
		try {
			String roleId = request.getParameter("roleId");
			manager.setStatus(0);
//			String category = request.getParameter("category");
//			String company = request.getParameter("company");
//			String customer = request.getParameter("customer");
//			if(category.equals("3")){
//				Company companyOne = companyBiz.findFullName(company);
//				manager.setCyid(companyOne.getCyid());
//			}else if(category.equals("4")){
//				Customer customerOne = customerBiz.findCustomerByFullName(customer);
//				manager.setCid(customerOne.getCid());
//			}
			managerBiz.save(manager, roleId);
			if(StringUtils.isBlank(pageNo)){
		    	pageNo = "1";
		    }
			list = managerBiz.findAll(keywords, null);
			page = new Page(Integer.parseInt(pageNo), DEFAULT_PAGE_SIZE, list.size());
			list = managerBiz.findAll(keywords, page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 进入修改管理员信息界面
	 */
	public String enterModify() {
		try {
			manager = managerBiz.findById(manager.getUserId());
			Popedom popedom = popedomBiz.findById(manager.getUserId()).get(0);
			manager.setRoleId(popedom.getRoleId());
			List<Role> list1=new ArrayList<Role>();
			list1 = roleBiz.findAll(null);
			roleList = list1;
			/*//根据所属类别加载该类类别下的身份角色
			int category=roleBiz.findById(popedom.getRoleId()).getCategory();
			List<Role> list1=new ArrayList<Role>();
			List<Role> list2=new ArrayList<Role>();
			list1 = roleBiz.findAll(null);
			if(list1.size()>0){
				for(int i=0;i<list1.size();i++){
					if(list1.get(i).getCategory()!=null&&list1.get(i).getCategory()==category){
						list2.add(list1.get(i));
					}
				}
			}
			roleList = list2;
			manager.setCategory(category);*/
//			if(manager.getCyid() != null){
//				Company companyOne = companyBiz.findById(manager.getCyid().toString());
//				manager.setCompanyFullName(companyOne.getFullName());
//			}
//			if(manager.getCid() != null){
//				Customer customerOne = customerBiz.findById(manager.getCid());
//				manager.setCustomerFullName(customerOne.getFullName());
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "modify";
	}
	
	/**
	 * 修改管理员信息
	 */
	public String modify() {
		try {
			//******
	//		if(manager.getCategory()==3&&manager.getCompanyFullName()!=null&&manager.getCompanyFullName()!=""){
//			if(manager.getCompanyFullName()!=null){	
//			if(manager.getCategory()!=null&&manager.getCategory()==3){
//						if(manager.getCompanyFullName()!=""){
//							Company companyOne = companyBiz.findFullName(manager.getCompanyFullName());
//							manager.setCyid(companyOne.getCyid());	
//						}
//					}
//					
//				}		
	//		}
//			if(manager.getCustomerFullName()!=null){	
//				if(manager.getCategory()!=null&&manager.getCategory()==4){
//							if(manager.getCustomerFullName()!=""){
//								Customer companyOne = customerBiz.findCustomerByFullName(manager.getCustomerFullName());
//								manager.setCid(companyOne.getCid());
//							}
//						}
//						
//					}	
//			else if(manager.getCategory()==4&&manager.getCompanyFullName()!=null&&manager.getCompanyFullName()!=""){
//				Customer companyOne = customerBiz.findCustomerByFullName(manager.getCustomerFullName());
//				manager.setCid(companyOne.getCid());
//			}
			managerBiz.modify(manager);
			if(StringUtils.isBlank(pageNo)){
		    	pageNo = "1";
		    }
			list = managerBiz.findAll(null, null);
			page = new Page(Integer.parseInt(pageNo), DEFAULT_PAGE_SIZE, list.size());
			list = managerBiz.findAll(null, page);
//			if(manager.getIds() == null){
//				return NONE;
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	@SuppressWarnings("static-access")
	public void findByValid(){
		try {
			manager = managerBiz.findByValid(pageNo, keywords);
			if(manager == null){
				result = resultBiz.getResult(resultBiz.SUCCESS);
			}else{
				jsonObject = JSONObject.fromObject(manager);
				result = resultBiz.getResult(resultBiz.FAIL, jsonObject.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = resultBiz.getResult(resultBiz.SYSTEM_BUSY);
		}finally{
			jsonObj(result.toJson());
		}
	}
	
	/**
	 * 管理员退出
	 */
	public String exit() {
		try {
			if(getSession() != null){
				getSession().invalidate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return LOGIN;
	}
	
	public void saveKey(){
		try {
			getSession().setAttribute(KEY, keywords);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询超级管理员用户是否已存在
	 * @return
	 */
	@SuppressWarnings("static-access")
	public  void adminIsExistingToJson(){
		try {
		String mid=	request.getParameter("mid");
		List<Manager> list=	managerBiz.findAll(null, null);
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
//				if(list.get(i).getCid()==null&&list.get(i).getCyid()==null){
					if(StringUtils.isNotBlank(mid)&&list.get(i).getUserId()!=Integer.parseInt(mid)){
						result = resultBiz.getResult(resultBiz.FAIL);
						break;	
					}else if(StringUtils.isBlank(mid)){
						result = resultBiz.getResult(resultBiz.FAIL);
						break;	
					}else if(StringUtils.isNotBlank(mid)&&list.get(i).getUserId()==Integer.parseInt(mid)){
						result = resultBiz.getResult(resultBiz.SUCCESS);
						break;
					}
					
//				}else{
//					result = resultBiz.getResult(resultBiz.SUCCESS);	
//				}
			}
		}else{
			result = resultBiz.getResult(resultBiz.SUCCESS);
		}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jsonObj(result.toJson());
		}
	}
	
	public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}
	public List<Manager> getList() {
		return list;
	}
	public void setList(List<Manager> list) {
		this.list = list;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public List<String> getPopedomList() {
		return popedomList;
	}

	public void setPopedomList(List<String> popedomList) {
		this.popedomList = popedomList;
	}

//	public List<Company> getCompanyList() {
//		return companyList;
//	}
//
//	public void setCompanyList(List<Company> companyList) {
//		this.companyList = companyList;
//	}
	
}
