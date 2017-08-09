package com.catic.mobilehos.menu_role_authority.action;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.catic.mobilehos.menu_role_authority.entity.Role;
import com.catic.mobilehos.utils.Page;

/**
 * 角色信息
 * @author YUXY
 *
 */

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class RoleAction extends BaseAction{

	private Role role;
	private List<Role> list;
	private static final Integer DEFAULT_PAGE_SIZE=10;
	public String save(){
		try {
			roleBiz.save(role);
			if(StringUtils.isBlank(pageNo)){
				pageNo = "1";
			}
			list = roleBiz.findAll(null);
			page = new Page(Integer.parseInt(pageNo), DEFAULT_PAGE_SIZE, list.size());
			list = roleBiz.findAll(page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	public String delete(){
		try {
			roleBiz.delete(role.getRoleId());
			if(StringUtils.isBlank(pageNo)){
				pageNo = "1";
			}
			list = roleBiz.findAll(null);
			page = new Page(Integer.parseInt(pageNo), DEFAULT_PAGE_SIZE, list.size());
			list = roleBiz.findAll(page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String modify(){
		try {
			roleBiz.modify(role);
			if(StringUtils.isBlank(pageNo)){
				pageNo = "1";
			}
			list = roleBiz.findAll(null);
			page = new Page(Integer.parseInt(pageNo), DEFAULT_PAGE_SIZE, list.size());
			list = roleBiz.findAll(page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	public String findById(){
		try {
			role = roleBiz.findById(role.getRoleId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "detail";
	}
	
	public String findAll(){
		try {
			if(StringUtils.isBlank(pageNo)){
				pageNo = "1";
			}
			list = roleBiz.findAll(null);
			page = new Page(Integer.parseInt(pageNo), DEFAULT_PAGE_SIZE, list.size());
			list = roleBiz.findAll(page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public void findAllToJson(){
		try {
			list = roleBiz.findAll(null);
			jsonArray = JSONArray.fromObject(list);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			jsonObj(jsonArray.toString());
		}
	}
	
	/**
	 * 根据类别查找角色
	 */
	public void findAllToJsonByCategory(){
		try {
			String category = request.getParameter("category");
			List<Role> list1=new ArrayList<Role>();
			list = roleBiz.findAll(null);
			/*if(list.size()>0){
				for(int i=0;i<list.size();i++){
					if(list.get(i).getCategory()!=null&&list.get(i).getCategory()==Integer.parseInt(category)){
						list1.add(list.get(i));
					}
				}
			}*/
			jsonArray = JSONArray.fromObject(list);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			jsonObj(jsonArray.toString());
		}
	}
	
	@SuppressWarnings("static-access")
	public void findByName(){
		try {
			role = roleBiz.findByName(pageNo);
			if(role == null){
				result = resultBiz.getResult(resultBiz.SUCCESS);
			}else{
				jsonObject = JSONObject.fromObject(role);
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
	 * 超级管理员只能有一位，验证是否已存在
	 */
    @SuppressWarnings("static-access")
	public void adminIsExistent(){
    	try {
    	String roleId=request.getParameter("roleId");
			list=roleBiz.findAll(null);
			if(list.size()>0){
				for(int i=0;i<list.size();i++){
					if(list.get(i).getCategory()!=null&&list.get(i).getCategory()==1){
						if(StringUtils.isNotBlank(roleId)&&list.get(i).getRoleId()!=Integer.parseInt(roleId)){
				     result = resultBiz.getResult(resultBiz.FAIL);
				     break;
				     }else if(StringUtils.isBlank(roleId)){
				    	 result = resultBiz.getResult(resultBiz.FAIL); 
				    	 break;
				     }
					
					}else{
				    result = resultBiz.getResult(resultBiz.SUCCESS);
					}
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
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public List<Role> getList() {
		return list;
	}
	public void setList(List<Role> list) {
		this.list = list;
	}
	
}
