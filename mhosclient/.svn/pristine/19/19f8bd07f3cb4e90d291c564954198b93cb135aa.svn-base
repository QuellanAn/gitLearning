package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;

public class TitlePO implements RowMapper<TitlePO> {
	private String questionnaireId;
	private String title;

	@Override
	public TitlePO mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		TitlePO tpo = new TitlePO();
		tpo.setQuestionnaireId(rs.getString("id"));
		tpo.setTitle(rs.getString("title"));
		return tpo;
	}

	public String getQuestionnaireId() {
		return questionnaireId;
	}

	public void setQuestionnaireId(String questionnaireId) {
		this.questionnaireId = questionnaireId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	

}
