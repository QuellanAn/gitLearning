package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;


public class HelpPO implements RowMapper<HelpPO>{
	
	private int helpId;
	private String question;
	private String answer;
	private String createPeople;
	private Timestamp createTime;
	
	public int getHelpId() {
		return helpId;
	}
	
	public void setHelpId(int helpId) {
		this.helpId = helpId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getCreatePeople() {
		return createPeople;
	}

	public void setCreatePeople(String createPeople) {
		this.createPeople = createPeople;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Override
	public HelpPO mapRow(ResultSet rs, int arg1) throws SQLException {
		HelpPO info = new HelpPO();
		info.setHelpId(rs.getInt("helpId"));
		info.setQuestion(StringUtils.trim(rs.getString("question")));
		info.setAnswer(StringUtils.trim(rs.getString("answer")));
		info.setCreatePeople(StringUtils.trim(rs.getString("createPeople")));
		info.setCreateTime(rs.getTimestamp("createTime"));
		return info;
	}

}
