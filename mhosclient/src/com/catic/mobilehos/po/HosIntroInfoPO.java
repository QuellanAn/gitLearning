package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;

public class HosIntroInfoPO implements RowMapper<HosIntroInfoPO> {
	
	private String hospitalName;
	
	private String level;
	
	private String address;
	
	private String website;
	
	private String featureDepartment;
	
	private String introduction;
	
	private byte[] picture;
	
	private String busLine;
	
	private String longitude;
	
	private String latitude;
	
	private String gpsPlace;
	
	private String phone;
	

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getFeatureDepartment() {
		return featureDepartment;
	}

	public void setFeatureDepartment(String featureDepartment) {
		this.featureDepartment = featureDepartment;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public String getBusLine() {
		return busLine;
	}

	public void setBusLine(String busLine) {
		this.busLine = busLine;
	}
	
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	public String getGpsPlace() {
		return gpsPlace;
	}

	public void setGpsPlace(String gpsPlace) {
		this.gpsPlace = gpsPlace;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public HosIntroInfoPO mapRow(ResultSet rs, int rowNum) throws SQLException {
		HosIntroInfoPO info = new HosIntroInfoPO();
		info.setAddress(StringUtils.trim(rs.getString("address")));
		info.setBusLine(StringUtils.trim(rs.getString("bus_line")));
		info.setFeatureDepartment(StringUtils.trim(rs.getString("feature_department")));
		info.setHospitalName(StringUtils.trim(rs.getString("hospital_name")));
		info.setIntroduction(StringUtils.trim(rs.getString("introduction")));
		info.setLevel(StringUtils.trim(rs.getString("level")));
		info.setWebsite(StringUtils.trim(rs.getString("website")));
		info.setPicture(rs.getBytes("picture"));
		info.setLongitude(StringUtils.trim(rs.getString("longitude")));
		info.setLatitude(StringUtils.trim(rs.getString("latitude")));
		info.setGpsPlace(StringUtils.trim(rs.getString("gps_place")));
		info.setPhone(StringUtils.trim(rs.getString("phone")));
		return info;
	}
	
	public static class ContactsPO implements RowMapper<ContactsPO>{
		
		private int lineId;
		
		private String name;
		
		private String phone;
		
		

		public int getLineId() {
			return lineId;
		}

		public void setLineId(int lineId) {
			this.lineId = lineId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		@Override
		public ContactsPO mapRow(ResultSet rs, int rowNum) throws SQLException {
			ContactsPO c = new ContactsPO();
			c.setLineId(rs.getInt("line_id"));
			c.setName(rs.getString("name"));
			c.setPhone(rs.getString("phone"));
			return c;
		}
		
	}
	
	
	
	
	
}
