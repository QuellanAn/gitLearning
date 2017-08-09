package com.catic.mobilehos.pay.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.catic.mobilehos.pay.dao.BaseDao;
import com.catic.mobilehos.pay.dao.IYQConfigDao;
import com.catic.mobilehos.pay.entity.YQInfo;
import com.catic.mobilehos.utils.Page;

@Repository("yQConfigDao")
public class YQConfigDaoImpl extends BaseDao implements IYQConfigDao{

	/**
	 * 插入院区信息
	 * @param yq
	 * @return
	 */
	@Override
	public Boolean insert(YQInfo yq) throws Exception {
		String sql="INSERT INTO pay_yqinfo (yqCode,name,linkManName,linkPhone,address,creator,createTime ) VALUES(?,?,?,?,?,?,NOW())";
		List<Object> param=new ArrayList<Object>();				
		param.add(yq.getYqCode());
		param.add(yq.getName());
		param.add(yq.getLinkManName());
		param.add(yq.getLinkPhone());
		param.add(yq.getAddress());
		param.add(yq.getCreator());
		int count=jdbc.update(sql,param.toArray());
		if(count>0){
			return true;
		}
		return false;
		
	}
	
	/**
	 * 查询所有院区
	 * @return
	 */
	@Override
	public List<YQInfo> findAll(Page page) throws Exception {
		String sql;
		if(page==null){
			sql="SELECT COUNT(0) AS count FROM pay_yqinfo WHERE status=0";
		}else{
			sql="SELECT * FROM pay_yqinfo WHERE status=0 ORDER BY createTime DESC ";
		}
		
		StringBuilder sb=new StringBuilder(sql);
		List<Object> param=new ArrayList<Object>();
		if(page!=null){
			sb.append(" LIMIT ?,?");
			param.add(page.getFirstIndex());
			param.add(page.getPageSize());
		}
		List<YQInfo> list=jdbc.query(sb.toString(),param.toArray(),new BeanPropertyRowMapper(YQInfo.class));
		return list;
		
	}
	
	/**
	 * 删除院区
	 * @param yqCode
	 * @return
	 */
	@Override
	public Boolean delete(String yqCode) throws Exception {
		String sql="UPDATE pay_yqinfo SET `status`=1 WHERE yqCode=?";
		List<Object> param=new ArrayList<Object>();				
		param.add(yqCode);
	    int count=jdbc.update(sql,param.toArray());
	    if(count>0){
	    	return true;
	    }
		return false;
		
	}
/**
 * 修改
 */
	@Override
	public Boolean modify(YQInfo yq) throws Exception {
		
		String sql="UPDATE pay_yqinfo SET yqCode=?";
		List<Object> param=new ArrayList<Object>();	
		StringBuilder sb=new StringBuilder(sql);
		param.add(yq.getYqCode());
		if(StringUtils.isNotBlank(yq.getName())){
			sb.append(",`name`=?");
			param.add(yq.getName());
		}
		if(StringUtils.isNotBlank(yq.getLinkManName())){
			sb.append(",linkManName=?");
			param.add(yq.getLinkManName());
		}
		if(StringUtils.isNotBlank(yq.getLinkPhone())){
			sb.append(",linkPhone=?");
			param.add(yq.getLinkPhone());
		}
		if(StringUtils.isNotBlank(yq.getAddress())){
			sb.append(",address=?");
			param.add(yq.getAddress());
		}
		if(StringUtils.isNotBlank(yq.getCreator())){
			sb.append(",creator=?");
			param.add(yq.getCreator());
		}
		sb.append(" WHERE yqId=?");
		param.add(yq.getYqId());
	    int count=jdbc.update(sb.toString(),param.toArray());
	    if(count>0){
	    	return true;
	    }
		return false;
	}
/**
 * 根据编号查询
 */
@Override
public YQInfo findByCode(String code) throws Exception {
	String sql="SELECT * FROM pay_yqinfo WHERE yqCode=?";
	List<Object> param=new ArrayList<Object>();	
	param.add(code);
	List<YQInfo> list=jdbc.query(sql,param.toArray(),new BeanPropertyRowMapper(YQInfo.class));
	if(list!=null&&list.size()>0){
		return list.get(0);
	}
	return null;
}

@Override
public YQInfo findById(String id) throws Exception {
	String sql="SELECT * FROM pay_yqinfo WHERE yqId=?";
	List<Object> param=new ArrayList<Object>();	
	param.add(id);
	List<YQInfo> list=jdbc.query(sql,param.toArray(),new BeanPropertyRowMapper(YQInfo.class));
	if(list!=null&&list.size()>0){
		return list.get(0);
	}
	return null;
}

public List<YQInfo> findAllyq() throws Exception {
	String sql="SELECT * FROM pay_yqinfo";
	List<YQInfo> list=jdbc.query(sql,new BeanPropertyRowMapper(YQInfo.class));
	return list;
}
}
