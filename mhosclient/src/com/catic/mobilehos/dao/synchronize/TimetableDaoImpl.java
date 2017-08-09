/**   
 * @Title: TimetableDaoImpl.java 
 * @Package com.catic.mobilehos.dao.synchronize 
 * @Description: TODO
 * @author htWang   
 * @date 2014-6-3 上午10:07:42 
 * 
 */
package com.catic.mobilehos.dao.synchronize;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.catic.mobilehos.po.SynChroRecordPO;
import com.catic.mobilehos.po.TimetablePO;

/**
 * @author WANG
 * 
 */
public class TimetableDaoImpl extends JdbcDaoSupport implements TimetableDao {

	private Log log = LogFactory.getLog(this.getClass());

	@Override
	public void addTimetable(final List<TimetablePO> timetableList) {

		SynchroRecord synchroRecord = new SynchroRecord();
		SynChroRecordPO synChroRecordPO = new SynChroRecordPO();
		synChroRecordPO.setSynchroTable("timetable");

		try {
			String sql = "INSERT INTO timetable (service_date, remainder, charge, doctor_id, department_id, time_id, total_count, time_state, room_id"
					+ ",period_id, order_type) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
			log.info("开始同步排班表数据...");
			long stime = System.currentTimeMillis();
			this.getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement pstmt, int i) throws SQLException {

					pstmt.setDate(1, timetableList.get(i).getServiceDate());
					pstmt.setInt(2, timetableList.get(i).getRemainder());
					pstmt.setDouble(3, timetableList.get(i).getCharge());
					pstmt.setString(4, timetableList.get(i).getDoctorId());
					pstmt.setString(5, timetableList.get(i).getDepartmentId());
					pstmt.setInt(6, timetableList.get(i).getTimeId());
					pstmt.setInt(7, timetableList.get(i).getTotalCount());
					pstmt.setInt(8, timetableList.get(i).getTimeState());
					pstmt.setString(9, timetableList.get(i).getRoomId());
					pstmt.setInt(10, timetableList.get(i).getPeriodId());
					pstmt.setString(11, timetableList.get(i).getOrderType());
					log.info("第" + (i + 1) + "条排班表数据同步完成......");
				}

				@Override
				public int getBatchSize() {
					return timetableList.size();
				}
			});
			long etime = System.currentTimeMillis();
			log.info("排班表数据同步完成,共完成：" + (timetableList.size() + "条数据" + "  耗时" + (etime - stime) + "毫秒"));

			synChroRecordPO.setSpendTime((etime - stime));
			synChroRecordPO.setRecordCount(timetableList.size());
			synChroRecordPO.setSynchroResult("SUCCESS");
		} catch (Exception e) {
			synChroRecordPO.setSynchroResult("FAILED");
			synChroRecordPO.setRecordCount(0);
			log.error("同步数据出现异常：", e);
		} finally {
			synchroRecord.recordSynchroLog(synChroRecordPO, this.getJdbcTemplate());
		}

	}

	public void deleTimetable() {
		try {
			String sql = "delete from timetable";
			this.getJdbcTemplate().update(sql);
		} catch (Exception e) {
			log.error("排班表数据删除失败:", e);
		}
	}

	@Override
	public long findLastTimetable() {
		try {
			String sql = "SELECT MAX(time_id) FROM timetable";
			return getJdbcTemplate().queryForObject(sql, Long.class);
		} catch (NullPointerException e) {
			return 0;
		}
	}
}
