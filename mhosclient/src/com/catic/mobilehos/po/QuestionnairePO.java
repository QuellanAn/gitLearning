package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * @author dengshenghui
 * 
 *         调查问卷管理
 * 
 */
public class QuestionnairePO implements RowMapper<QuestionnairePO> {
	/**调查问卷Id*/
	private int id;
	/**最后一次更改问卷的管理员*/
	private String userName;
	/**标题*/
	private String title;
	/**副标题*/
	private String sub_title;
	/**状态*/
	private int status;
	/**创建时间*/
	private Timestamp createtime;
	/**更新时间*/
	private Timestamp updatetime;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}

	public String getSub_title() {
		return sub_title;
	}

	public void setSub_title(String sub_title) {
		this.sub_title = sub_title;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	
	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public Timestamp getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}

	@Override
	public QuestionnairePO mapRow(ResultSet resultSet, int rows) throws SQLException {
		// TODO Auto-generated method stub
		QuestionnairePO questionnairePO = new QuestionnairePO();
		questionnairePO.setId(resultSet.getInt("id"));
		questionnairePO.setTitle(resultSet.getString("title"));
		questionnairePO.setSub_title(resultSet.getString("sub_title"));
		questionnairePO.setStatus(resultSet.getInt("status"));
		questionnairePO.setCreatetime(resultSet.getTimestamp("createtime"));
		questionnairePO.setUpdatetime(resultSet.getTimestamp("updatetime"));
		questionnairePO.setUserName(resultSet.getString("user_name"));
		return questionnairePO;
	}
	

	
	
	

	
}


