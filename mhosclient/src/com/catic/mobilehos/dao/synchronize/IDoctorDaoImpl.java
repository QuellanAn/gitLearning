/**   
 * @Title: IDoctorDaoImpl.java 
 * @Package com.catic.mobilehos.dao.synchronize 
 * @Description: TODO
 * @author htWang   
 * @date 2014-5-20 上午10:32:45 
 * 
 */
package com.catic.mobilehos.dao.synchronize;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.catic.mobilehos.po.DoctorPO;
import com.catic.mobilehos.po.SynChroRecordPO;

/**
 * @author WANG
 * 
 */
public class IDoctorDaoImpl extends JdbcDaoSupport implements IDoctorDao {

	private Log log = LogFactory.getLog(this.getClass());

	public void addDoctorFromHis(final List<DoctorPO> hisDoctorList) {
		SynchroRecord synchroRecord = new SynchroRecord();
		SynChroRecordPO synChroRecordPO = new SynChroRecordPO();
		synChroRecordPO.setSynchroTable("doctors");
		try {
			log.debug("开始同步医生数据...");
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "INSERT INTO doctors (doctor_id, doctor_name, job" + ", speciality, introduction) VALUES (?,?,?,?,?)";
			long sTime = System.currentTimeMillis();
			jt.batchUpdate(sql, new BatchPreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement pstmt, int i) throws SQLException {
					log.debug("正在同步第" + (i + 1) + "条医生数据");
					pstmt.setString(1, hisDoctorList.get(i).getDoctorId());
					pstmt.setString(2, hisDoctorList.get(i).getDoctorName());
					pstmt.setString(3, hisDoctorList.get(i).getJob());
					pstmt.setString(4, hisDoctorList.get(i).getSpeciality());
					pstmt.setString(5, hisDoctorList.get(i).getIntroduction());
				}

				@Override
				public int getBatchSize() {
					return hisDoctorList.size();
				}
			});
			long eTime = System.currentTimeMillis();
			log.debug("医生信息同步完成.......本次共同步" + hisDoctorList.size() + "条数据,耗时：" + (eTime - sTime) + "毫秒");
			synChroRecordPO.setSpendTime((eTime - sTime));
			synChroRecordPO.setRecordCount(hisDoctorList.size());
			synChroRecordPO.setSynchroResult("SUCCESS");
		} catch (Exception e) {
			synChroRecordPO.setSynchroResult("FAILED");
			synChroRecordPO.setRecordCount(0);
			log.error("医生信息数据同步失败", e);
		} finally {
			synchroRecord.recordSynchroLog(synChroRecordPO, this.getJdbcTemplate());
		}
	}

	public void deleteAllDoctor() {
		try {
			String sql = "delete from doctors";
			this.getJdbcTemplate().update(sql);
		} catch (Exception e) {
			log.error(null, e);
		}
	}
}
