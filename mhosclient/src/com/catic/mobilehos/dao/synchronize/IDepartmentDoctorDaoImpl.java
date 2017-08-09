/**   
 * @Title: DepartmentDoctorDaoImpl.java 
 * @Package com.catic.mobilehos.dao.jdbc 
 * @Description: TODO
 * @author htWang   
 * @date 2014-5-19 下午4:06:40 
 * 
 */
package com.catic.mobilehos.dao.synchronize;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.catic.mobilehos.po.DepartmentDoctorPO;
import com.catic.mobilehos.po.SynChroRecordPO;

/**
 * @author WANG
 * 
 */
public class IDepartmentDoctorDaoImpl extends JdbcDaoSupport implements IDepartmentDoctorDao {

	private Log log = LogFactory.getLog(this.getClass());

	/*
	 * (non-javadoc) <p>Title: delAllDeptDoctor</p> <p>Description: </p>
	 * 
	 * @see com.catic.mobilehos.dao.DepartmentDoctorDao#delAllDeptDoctor()
	 */
	@Override
	public void delAllDeptDoctor() {
		try {
			String sql = "delete from department_doctors";
			this.getJdbcTemplate().update(sql);
		} catch (Exception e) {
			log.error("删除本地科室-医生数据列表出现异常：", e);
		}
	}

	/*
	 * (non-javadoc) <p>Title: addDeptDoctor</p> <p>Description: </p>
	 * 
	 * @param deptDocList
	 * 
	 * @see
	 * com.catic.mobilehos.dao.DepartmentDoctorDao#addDeptDoctor(java.util.List)
	 */
	@Override
	public void addDeptDoctor(final List<DepartmentDoctorPO> deptDocList) {
		SynchroRecord synchroRecord = new SynchroRecord();
		SynChroRecordPO synChroRecordPO = new SynChroRecordPO();
		synChroRecordPO.setSynchroTable("department_doctor");

		try {
			String sql = "INSERT INTO department_doctors (department_id, doctor_id) VALUES (?,?)";
			log.debug("开始同步科室-医生 数据...");
			long sTime = System.currentTimeMillis();
			this.getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement pstmt, int i) throws SQLException {
					pstmt.setString(1, deptDocList.get(i).getDepartmentId());
					pstmt.setString(2, deptDocList.get(i).getDoctorId());
					log.debug("同步第" + (i + 1) + "条科室-医生数据完成..");
				}

				@Override
				public int getBatchSize() {
					return deptDocList.size();
				}
			});
			long eTime = System.currentTimeMillis();
			log.debug("同步完成.......共计：" + deptDocList.size() + "条科室-医生数据,耗时:" + (eTime - sTime) + "毫秒");
			synChroRecordPO.setSpendTime((eTime - sTime));
			synChroRecordPO.setRecordCount(deptDocList.size());
			synChroRecordPO.setSynchroResult("SUCCESS");
		} catch (Exception e) {
			synChroRecordPO.setSynchroResult("FAILED");
			synChroRecordPO.setRecordCount(0);
			log.error("同步科室-医生数据出现异常:", e);
		} finally {
			synchroRecord.recordSynchroLog(synChroRecordPO, this.getJdbcTemplate());
		}
	}

}
