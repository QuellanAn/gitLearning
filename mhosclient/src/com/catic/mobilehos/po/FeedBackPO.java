package com.catic.mobilehos.po;

import java.sql.Timestamp;

/**
 * 
 * @author dengshenghui
 * 
 *         调查问卷管理
 * 
 */
public class FeedBackPO {
	private int questionnaireId;
	private int questionId;
	private String suggest;

	public int getQuestionnaireId() {
		return questionnaireId;
	}

	public void setQuestionnaireId(int questionnaireId) {
		this.questionnaireId = questionnaireId;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}


	public String getSuggest() {
		return suggest;
	}

	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}


}
