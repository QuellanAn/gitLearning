package com.catic.mobilehos.dao.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.catic.mobilehos.dao.HelpAnswerDao;
import com.catic.mobilehos.po.HelpPO;

public class HelpAnswerDaoImpl extends JdbcDaoSupport implements HelpAnswerDao {
	
	private Log log = LogFactory.getLog(this.getClass());

	@Override
	public List<HelpPO> findAll(String question,String createPeople,java.sql.Date startDate,
			java.sql.Date endDate,int offset, int length) {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			List<Object> lstArgs = new ArrayList<Object>();
			String sql = "select * from help h where 1=1 ";
			sql = addParasToSQL(sql, question,createPeople,startDate,endDate, lstArgs);
			sql += " order by h.helpId asc limit ?,?";
			lstArgs.add(offset);
			lstArgs.add(length);
			List<HelpPO> lst = jt.query(sql, lstArgs.toArray(), new HelpPO());
			return lst;
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}
	}

	@Override
	public int getTotalHelp(String question,String createPeople,java.sql.Date startDate,java.sql.Date endDate) {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			List<Object> lstArgs = new ArrayList<Object>();
			String sql = "select count(*) from help h where 1=1 ";
			sql = addParasToSQL(sql, question,createPeople, startDate, endDate, lstArgs);
			int count = jt.queryForObject(sql, lstArgs.toArray(), Integer.class);
			return count;
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}
	}
	
	private String addParasToSQL(String sql,String question,String createPeople,
			java.sql.Date startDate,java.sql.Date endDate,List<Object> lstArgs){
		if (StringUtils.isNotBlank(question)){
			sql += " and h.question like ?";
			lstArgs.add("%"+question+"%");
		}
		if (StringUtils.isNotBlank(createPeople)){
			sql += " and h.createPeople like ?";
			lstArgs.add("%"+createPeople+"%");
		}
		if (startDate != null){
			sql += " and h.createTime >= ?";
			lstArgs.add(startDate);
		}
		if (endDate != null){
			sql += " and h.createTime <= ?";
			lstArgs.add(endDate);
		}
		return sql;
	}

	@Override
	public HelpPO getHelpAnswer(int helpId) {
		try{
			HelpPO helpPo=new HelpPO();
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "select * from help h where helpId=? ";
			helpPo = jt.queryForObject(sql, new HelpPO(), helpId);
			return helpPo;
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}
	}

	@Override
	public void updateHelp(HelpPO helpPo) {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "update help set question=?,answer=?,createTime=?,createPeople=? where helpId=?";
			jt.update(sql,helpPo.getQuestion()
					  , helpPo.getAnswer()
					  , helpPo.getCreateTime()
					  , helpPo.getCreatePeople()
					  , helpPo.getHelpId());
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}
	}

	@Override
	public void addHelp(HelpPO helpPo) {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			String sqlId="select max(helpId) from help";
			int helpId=jt.queryForObject(sqlId, null, Integer.class);
			String sql = "INSERT INTO help(helpId, question, answer, createPeople"
						 + ", createTime)values(?,?,?,?,?)";
			jt.update(sql, helpId+1, helpPo.getQuestion(),helpPo.getAnswer()
					,helpPo.getCreatePeople(),helpPo.getCreateTime());
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}
	}

	@Override
	public void deleteHelp(int helpId) {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "delete from help where helpId = ?";
			jt.update(sql, helpId);
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}
	}

}
