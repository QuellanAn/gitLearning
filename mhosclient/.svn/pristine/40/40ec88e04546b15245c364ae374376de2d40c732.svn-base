package com.catic.mobilehos.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import com.catic.mobilehos.po.Specialist;
import com.catic.mobilehos.service.SpecialistService;
import com.catic.mobilehos.utils.Page;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

@SuppressWarnings("serial")
public class SpecialistAction extends BaseAction {
 
	private SpecialistService specialistService;
	
	private Specialist specialist;
	private List<Specialist> list;
	
	private String pageNo;
	private Page page;
	private File file;
	private JSONObject jsonObject;
	
	public String name;
	
	/**
	 * 进入保存界面
	 */
	public String toSave() {
		return "toSave";
	}
	
	/**
	 * 保存信息
	 */
	public String save() {
		try {
			if(file != null){
				InputStream is = new FileInputStream(file);
				byte[] photo = IOUtils.toByteArray(is);
				specialist.setPhoto(photo);
			}
			boolean flag = specialistService.save(specialist);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return "all";
	}
	
	/**
	 * 删除信息
	 */
	public void delete() {
		jsonObject = new JSONObject();
		try {
			String id = request.getParameter("id");
			boolean flag = specialistService.delete(id);
			if(flag){
				jsonObject.put("code", 0);
			}else{
				jsonObject.put("code", 1);
			}
		} catch (Exception e) {
			jsonObject.put("code", -1);
			e.printStackTrace();
		} finally{
			writeObject(jsonObject);
		}
	}
	
	/**
	 * 查询全部信息
	 */
	public String findAll() {
		try {
			if(StringUtils.isBlank(pageNo)){
				pageNo = "1";
			}
			specialist = new Specialist();
			specialist.setName(name);
			list = specialistService.findAll(specialist, null);
			page = new Page(Integer.parseInt(pageNo), 15, list.get(0).getCount());
			list = specialistService.findAll(specialist, page);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return SUCCESS;
	}
	
	/**
	 * 验证医生编号是否存在
	 */
	public void findByCode() {
		jsonObject = new JSONObject();
		try {
			String code = request.getParameter("code");
			list = specialistService.findByCode(code);
			if(list.size() == 0){
				jsonObject.put("code", 0);
			}else{
				jsonObject.put("code", 1);
			}
		} catch (Exception e) {
			jsonObject.put("code", -1);
			e.printStackTrace();
		} finally{
			writeObject(jsonObject);
		}
	}
	
	/**
	 * 进入修改界面
	 */
	public String toModify() {
		jsonObject = new JSONObject();
		try {
			String code = request.getParameter("code");
			list = specialistService.findByCode(code);
			if(list != null && list.size() > 0){
				specialist = list.get(0);
				specialist.setImage(Base64.encode(specialist.getPhoto()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return "toModify";
	}
	
	/**
	 * 保存信息
	 */
	public String modify() {
		try {
			if(file != null){
				InputStream is = new FileInputStream(file);
				byte[] photo = IOUtils.toByteArray(is);
				specialist.setPhoto(photo);
			}
			boolean flag = specialistService.modify(specialist);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return "all";
	}
	
	public SpecialistService getSpecialistService() {
		return specialistService;
	}

	public void setSpecialistService(SpecialistService specialistService) {
		this.specialistService = specialistService;
	}

	public Specialist getSpecialist() {
		return specialist;
	}
	public void setSpecialist(Specialist specialist) {
		this.specialist = specialist;
	}
	public List<Specialist> getList() {
		return list;
	}
	public void setList(List<Specialist> list) {
		this.list = list;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
}
