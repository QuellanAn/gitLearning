package com.catic.mobilehos.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.catic.mobilehos.dao.HospitalIntroInfoDAO;
import com.catic.mobilehos.po.HosIntroInfoPO;
import com.catic.mobilehos.po.HosIntroInfoPO.ContactsPO;

public class HospitalInfoDAOImpl extends JdbcDaoSupport implements HospitalIntroInfoDAO {
	
	private Log log = LogFactory.getLog(this.getClass());

	@Override
	public HosIntroInfoPO getHospitalIntroInfo() {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "select * from hospital_info";
			HosIntroInfoPO info = jt.queryForObject(sql, new HosIntroInfoPO());
			return info;
		}catch(DataAccessException ex){
			log.error(null, ex);
			throw ex;
		}
	}

	@Override
	public List<HosIntroInfoPO.ContactsPO> getHospitalContacts() {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "select * from hospital_contacts order by line_id";
			List<HosIntroInfoPO.ContactsPO> constacts = jt.query(sql, new HosIntroInfoPO.ContactsPO());
			return constacts;
		}catch(DataAccessException ex){
			log.error(null, ex);
			throw ex;
		}
	}

	@Override
	public String[] getHosGPS() {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "select longitude, latitude, gps_place from hospital_info";
			String[] gps = jt.queryForObject(sql, new RowMapper<String[]>(){

				@Override
				public String[] mapRow(ResultSet rs, int rowNum)
						throws SQLException {
					String[] arr = new String[3];
					arr[0] = StringUtils.trim(rs.getString("longitude"));
					arr[1] = StringUtils.trim(rs.getString("latitude"));
					arr[2] = StringUtils.trim(rs.getString("gps_place"));
					return arr;
				}
			});
			return gps;
		}catch(DataAccessException ex){
			log.error(null, ex);
			throw ex;
		}
	}

	@Override
	public void clearHospitalIntroInfo() {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "delete from hospital_info";
			jt.update(sql);
		}catch(DataAccessException ex){
			ex.printStackTrace();
			log.error(null, ex);
			throw ex;
		}
	}
	
	@Override
	public void clearHospitalContacts() {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "delete from hospital_contacts;";
			jt.update(sql);
		}catch(DataAccessException ex){
			log.error(null, ex);
			throw ex;
		}
	}

	@Override
	public void addHospitalIntroInfo(HosIntroInfoPO po) {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "insert into hospital_info(hospital_name, level, address"
					+ ", website, feature_department, introduction"
					+ ", picture, bus_line, longitude"
					+ ", latitude, gps_place,phone) "
					+ " values(?,?,?,?,?,?,?,?,?,?,?,?)";
			
//			String sql = "insert into hospital_info(hospital_name, level, address"
//					+ ", website, feature_department, introduction"
//					+ ", bus_line, longitude"
//					+ ", latitude, gps_place) "
//					+ " values(?,?,?,?,?,?,?,?,?,?)";
			
			//System.out.println("image length is :  " + po.getPicture().length);
			
			int k = jt.update(sql, po.getHospitalName(), po.getLevel(), po.getAddress()
					, po.getWebsite(), po.getFeatureDepartment(), po.getIntroduction()
					, po.getPicture(), po.getBusLine(), po.getLongitude()
					, po.getLatitude(), po.getGpsPlace(),po.getPhone());
			
			//System.out.println("and update is : " + k);
			
//			jt.update(sql, po.getHospitalName(), po.getLevel(), po.getAddress()
//					, po.getWebsite(), po.getFeatureDepartment(), po.getIntroduction()
//					, po.getBusLine(), po.getLongitude()
//					, po.getLatitude(), po.getGpsPlace());
		}catch(DataAccessException ex){
			ex.printStackTrace();
			throw ex;
		}
	}

	@Override
	public void addHospitalContacts(final List<ContactsPO> contacts) {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "insert into hospital_contacts(line_id, name, phone) values(?,?,?)"
					+ " ON DUPLICATE KEY UPDATE name=?, phone=?;";
			jt.batchUpdate(sql, new BatchPreparedStatementSetter(){

				@Override
				public void setValues(PreparedStatement ps, int i)
						throws SQLException {
					ps.setObject(1, contacts.get(i).getLineId());
					ps.setObject(2, contacts.get(i).getName());
					ps.setObject(3, contacts.get(i).getPhone());
					ps.setObject(4, contacts.get(i).getName());
					ps.setObject(5, contacts.get(i).getPhone());
					ps.addBatch();
				}
				
				@Override
				public int getBatchSize() {
					return contacts != null ? contacts.size() : 0;
				}
			});
				
		}catch(DataAccessException ex){
			log.error(null, ex);
			throw ex;
		}
	}
	
	
	
	
	
	
	
	

	
	
	

}
