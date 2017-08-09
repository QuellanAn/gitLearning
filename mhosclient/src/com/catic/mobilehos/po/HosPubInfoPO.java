package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;

public class HosPubInfoPO implements RowMapper<HosPubInfoPO> {
	
	public static final int STATUS_NEW = 0;
	
	public static final int STATUS_UNAPPROVED = 1;
	
	public static final int STATUS_APPROVED = 2;
	
	public static final int STATUS_CANCEL = 3;
	
	private String hosInfoId;
	
	private String subject;
	
	private String concise;
	
	private Timestamp pubDate;
	
	private java.sql.Date expPubDate;
	
	private Timestamp updateDate;
	
	private byte[] image;
	
	private String html;
	
	private String htmlContent;
	
	private Timestamp createDate;
	
	private int browseCount;
	
	private String infoCatCode;
	
	private String infoCatName;
	
	private int infoType;
	
	private int status;
	
	private int isMain;
	
	private String editor;
	
	private String approver;
	
	private Timestamp approveDate;
	
	public java.sql.Date getExpPubDate() {
		return expPubDate;
	}

	public void setExpPubDate(java.sql.Date expPubDate) {
		this.expPubDate = expPubDate;
	}

	

	public String getHosInfoId() {
		return hosInfoId;
	}

	public void setHosInfoId(String hosInfoId) {
		this.hosInfoId = hosInfoId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getConcise() {
		return concise;
	}

	public void setConcise(String concise) {
		this.concise = concise;
	}

	public Timestamp getPubDate() {
		return pubDate;
	}

	public void setPubDate(Timestamp pubDate) {
		this.pubDate = pubDate;
	}
	
	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public int getBrowseCount() {
		return browseCount;
	}

	public void setBrowseCount(int browseCount) {
		this.browseCount = browseCount;
	}

	public String getInfoCatCode() {
		return infoCatCode;
	}

	public void setInfoCatCode(String infoCatCode) {
		this.infoCatCode = infoCatCode;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getIsMain() {
		return isMain;
	}

	public void setIsMain(int isMain) {
		this.isMain = isMain;
	}

	public int getInfoType() {
		return infoType;
	}

	public void setInfoType(int infoType) {
		this.infoType = infoType;
	}
	
	public String getInfoCatName() {
		return infoCatName;
	}

	public void setInfoCatName(String infoCatName) {
		this.infoCatName = infoCatName;
	}
	
	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}
	
	public String getApprover() {
		return approver != null ? approver : "";
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public Timestamp getApproveDate() {
		return approveDate;
	}

	public void setApproveDate(Timestamp approveDate) {
		this.approveDate = approveDate;
	}
	
	public String getHtmlContent() {
		return htmlContent;
	}

	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}

	@Override
	public HosPubInfoPO mapRow(ResultSet rs, int arg1) throws SQLException {
		HosPubInfoPO info = new HosPubInfoPO();
		info.setBrowseCount(rs.getInt("browse_count"));
		info.setConcise(StringUtils.trim(rs.getString("concise")));
		info.setCreateDate(rs.getTimestamp("create_date"));
		info.setHosInfoId(StringUtils.trim(rs.getString("info_id")));
		try{
			info.setHtml(rs.getString("html"));
			info.setHtmlContent(rs.getString("html_content"));
		}catch(SQLException e){
		}
		info.setImage(rs.getBytes("image"));
		info.setInfoCatCode(StringUtils.trim(rs.getString("info_cat_code")));
		info.setInfoCatName(StringUtils.trim(rs.getString("info_cat_name")));
		info.setInfoType(rs.getInt("info_type"));
		info.setPubDate(rs.getTimestamp("pub_date"));
		info.setExpPubDate(rs.getDate("exp_pub_date"));
		info.setStatus(rs.getInt("status"));
		info.setSubject(StringUtils.trim(rs.getString("subject")));
		info.setUpdateDate(rs.getTimestamp("update_date"));
		info.setApproveDate(rs.getTimestamp("approve_date"));
		info.setEditor(rs.getString("editor"));
		info.setApprover(rs.getString("approver"));
		info.setIsMain(Integer.parseInt(rs.getString("is_main")));
		return info;
	}
	
	
	public static class StatByTypePO implements RowMapper<StatByTypePO>{
		
		private int infoType;
		
		private int statCount;
		
		public int getInfoType() {
			return infoType;
		}

		public void setInfoType(int infoType) {
			this.infoType = infoType;
		}

		public int getStatCount() {
			return statCount;
		}

		public void setStatCount(int statCount) {
			this.statCount = statCount;
		}

		@Override
		public StatByTypePO mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			StatByTypePO p = new StatByTypePO();
			p.setInfoType(rs.getInt("info_type"));
			p.setStatCount(rs.getInt("stat_count"));
			return p;
		}
		
	}
	
	
	public static class StatByCatPO implements RowMapper<StatByCatPO>{
		
		private String infoCatCode;
		
		private String infoCatName;
		
		private int infoType;
		
		private int statCount;

		public String getInfoCatCode() {
			return infoCatCode;
		}

		public void setInfoCatCode(String infoCatCode) {
			this.infoCatCode = infoCatCode;
		}

		public String getInfoCatName() {
			return infoCatName;
		}

		public void setInfoCatName(String infoCatName) {
			this.infoCatName = infoCatName;
		}

		public int getInfoType() {
			return infoType;
		}

		public void setInfoType(int infoType) {
			this.infoType = infoType;
		}
		
		public int getStatCount() {
			return statCount;
		}

		public void setStatCount(int statCount) {
			this.statCount = statCount;
		}
		

		@Override
		public StatByCatPO mapRow(ResultSet rs, int rowNum) throws SQLException {
			StatByCatPO stat = new StatByCatPO();
			stat.setInfoCatCode(StringUtils.trim(rs.getString("info_cat_code")));
			stat.setInfoCatName(StringUtils.trim(rs.getString("info_cat_name")));
			stat.setInfoType(rs.getInt("info_type"));
			stat.setStatCount(rs.getInt("stat_count"));
			return stat;
		}
		
	}
	
	

}
