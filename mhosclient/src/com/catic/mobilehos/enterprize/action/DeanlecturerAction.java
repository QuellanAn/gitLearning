package com.catic.mobilehos.enterprize.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;

import com.catic.mobilehos.enterprize.entity.Hosinfo;
import com.catic.mobilehos.utils.Page;

@SuppressWarnings("serial")
@Controller
public class DeanlecturerAction extends BaseAction {
	private String id;
	private Hosinfo info;
	private List<Hosinfo> list;
	private String starttime;
	private String endtime;
	
	public String findAll(){
		try {
			if(StringUtils.isBlank(pageNo)){
				pageNo="1";
			}
			if(StringUtils.isNotBlank(starttime)){
				info.setStarttime(starttime);
			}
			if(StringUtils.isNotBlank(endtime)){
				info.setEndtime(endtime);
			}
			list=deanlecturerBiz.findAll(info,page);
			if(list!=null&&list.size()>0){
				page=new Page(Integer.parseInt(pageNo), 10, list.get(0).getCount());
				list=deanlecturerBiz.findAll(info,page);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String toUpdate(){
		try {
			info=deanlecturerBiz.getInfo(Integer.parseInt(id));
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return "toUpdate";
	}
	
	public String addOrUpdate(){
		try {
			info.setType(2);
			info.setCreatetime(starttime);
			if(info.getId()==null){
				deanlecturerBiz.add(info);
			}else{
				deanlecturerBiz.update(info);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "saveUpdate";
	}
	
	public String delete(){
		try {
			deanlecturerBiz.delete(Integer.parseInt(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "saveUpdate";
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Hosinfo getInfo() {
		return info;
	}
	public void setInfo(Hosinfo info) {
		this.info = info;
	}

	public List<Hosinfo> getList() {
		return list;
	}

	public void setList(List<Hosinfo> list) {
		this.list = list;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

}