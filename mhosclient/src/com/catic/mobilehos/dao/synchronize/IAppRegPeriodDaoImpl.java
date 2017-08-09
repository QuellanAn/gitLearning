package com.catic.mobilehos.dao.synchronize;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.catic.mobilehos.po.AppRegPeriodPO;
import com.catic.mobilehos.po.SynChroRecordPO;

public class IAppRegPeriodDaoImpl extends JdbcDaoSupport implements IAppRegPeriodDao {

	private Log log = LogFactory.getLog(this.getClass());

	@Override
	public void addAppRegPeriodFromHis(final List<AppRegPeriodPO> periodList) {
		SynchroRecord synchroRecord = new SynchroRecord();
		SynChroRecordPO synChroRecordPO = new SynChroRecordPO();
		synChroRecordPO.setSynchroTable("app_reg_period");
		try {
			log.debug("开始同步挂号时段数据...");
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "INSERT INTO app_reg_period (period_id, period_type, start_time, end_time) VALUES (?,?,?,?)";
			long sTime = System.currentTimeMillis();
			jt.batchUpdate(sql, new BatchPreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement pstmt, int i) throws SQLException {
					log.debug("正在同步第" + (i + 1) + "条挂号时段数据");
					pstmt.setInt(1, periodList.get(i).getPeriodId());
					pstmt.setString(2, periodList.get(i).getPeriodType());
					pstmt.setTime(3, periodList.get(i).getStartTime());
					pstmt.setTime(4, periodList.get(i).getEndTime());
				}

				@Override
				public int getBatchSize() {
					return periodList.size();
				}
			});
			long eTime = System.currentTimeMillis();
			log.debug("挂号时段数据同步完成.......本次共同步" + periodList.size() + "条数据,耗时：" + (eTime - sTime) + "毫秒");
			synChroRecordPO.setSpendTime((eTime - sTime));
			synChroRecordPO.setRecordCount(periodList.size());
			synChroRecordPO.setSynchroResult("SUCCESS");
		} catch (Exception e) {
			synChroRecordPO.setSynchroResult("FAILED");
			synChroRecordPO.setRecordCount(0);
			log.error("挂号时段数据同步失败", e);
		} finally {
			synchroRecord.recordSynchroLog(synChroRecordPO, this.getJdbcTemplate());
		}
	}

	@Override
	public int delAllAppRegPeriod() {
		try {
			String sql = "delete from app_reg_period";
			return this.getJdbcTemplate().update(sql);
		} catch (Exception e) {
			log.error(null, e);
			return -1;
		}
	}

}
