package com.catic.mobilehos.enterprize.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.catic.mobilehos.enterprize.dao.BaseDao;
import com.catic.mobilehos.enterprize.dao.IDeanlecturerDao;
import com.catic.mobilehos.enterprize.dao.IHosinfoDao;
import com.catic.mobilehos.enterprize.entity.Hosinfo;
import com.catic.mobilehos.utils.Page;

@Repository("deanlecturerDao")
public class DeanlecturerDaoImpl extends BaseDao implements IDeanlecturerDao {

	@Override
	public void add(Hosinfo info) {
		String sql="insert into enterprize_hos_info(title,content,author,type,createtime)" +
				" values(?,?,?,?,?)";
		List<Object> param=new ArrayList<Object>();
		param.add(info.getTitle());
		param.add(info.getContent());
		param.add(info.getAuthor());
		param.add(info.getType());
		param.add(info.getCreatetime());
		jdbc.update(sql, param.toArray());
	}

	@Override
	public void update(Hosinfo info) {
		String sql="update enterprize_hos_info set title=?";
		List<Object> param=new ArrayList<Object>();
		StringBuilder sb=new StringBuilder(sql);
		param.add(info.getTitle());
		if(StringUtils.isNotBlank(info.getContent())){
			sb.append(",content=?");
			param.add(info.getContent());
		}
		if(StringUtils.isNotBlank(info.getAuthor())){
			sb.append(",author=?");
			param.add(info.getAuthor());
		}
		if(StringUtils.isNotBlank(info.getImage())){
			sb.append(",image=?");
			param.add(info.getImage());
		}
		if(info.getType()!=null){
			sb.append(",type=?");
			param.add(info.getType());
		}
		sb.append(",createtime=NOW()");
		sb.append(" where id=?");
		param.add(info.getId());
		jdbc.update(sb.toString(),param.toArray());
	}

	@Override
	public void delete(Integer id) {
		String sql="delete from enterprize_hos_info where id="+id;
		jdbc.update(sql);
		
	}

	@Override
	public List<Hosinfo> findAll(Hosinfo info,Page page) {
		String sql;
		if(page==null){
			sql="SELECT COUNT(0) AS count FROM enterprize_hos_info WHERE type=2";
		}else{
			sql="SELECT * FROM enterprize_hos_info where type=2 ";
		}
		StringBuilder sb=new StringBuilder(sql);
		List<Object> param=new ArrayList<Object>();
		if(info!=null){
			if(StringUtils.isNotBlank(info.getStarttime())){
				sb.append(" and createtime>=?");
				param.add(info.getStarttime());
			}
			if(StringUtils.isNotBlank(info.getEndtime())){
				sb.append(" and createtime<=?");
				param.add(info.getEndtime());
			}
			if(StringUtils.isNotBlank(info.getAuthor())){
				sb.append(" and author like ?");
				param.add("%"+info.getAuthor()+"%");
			}
		}
		sb.append(" ORDER BY createTime DESC ");
		if(page!=null){
			sb.append(" LIMIT ?,?");
			param.add(page.getFirstIndex());
			param.add(page.getPageSize());
		}
		List<Hosinfo> list=jdbc.query(sb.toString(),param.toArray(),new BeanPropertyRowMapper(Hosinfo.class));
		return list;
	}

	@Override
	public Hosinfo getInfo(Integer id) {
		String sql="select * from enterprize_hos_info where 1=1";
		StringBuilder sb=new StringBuilder(sql);
		List<Object> param=new ArrayList<Object>();
		if(id!=null){
			sb.append(" and id=?");
			param.add(id);
		}
		Hosinfo info=jdbc.queryForObject(sb.toString(),param.toArray(),new BeanPropertyRowMapper(Hosinfo.class));
		return info;
	}

}
