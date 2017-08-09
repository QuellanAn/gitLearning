/**   
 * @Title: IRoomQueueSnDaoImpl.java 
 * @Package com.catic.mobilehos.dao.synchronize 
 * @Description: TODO
 * @author htWang   
 * @date 2014-5-23 下午2:18:12 
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

import com.catic.mobilehos.po.RoomQueueSnPO;
import com.catic.mobilehos.po.SynChroRecordPO;

/**
 * @author WANG
 *
 */
public class IRoomQueueSnDaoImpl extends JdbcDaoSupport implements IRoomQueueSnDao{
	
	private Log log = LogFactory.getLog(this.getClass());
	
	@Override
	public void deleteRoomQueueSn() {
		try {
			String sql = "delete from room_queue_sn";
			this.getJdbcTemplate().update(sql);
		} catch (Exception e) {
			log.error("删除诊室排队信息失败..",e);
		}
	}

	@Override
	public void addRoomQueueSnFromHIS(final List<RoomQueueSnPO> roomQueueSnList) {
		
		SynchroRecord synchroRecord = new SynchroRecord();
		SynChroRecordPO synChroRecordPO = new SynChroRecordPO();
		synChroRecordPO.setSynchroTable("room_queue_sn");
		
		try {
			String sql = "INSERT INTO room_queue_sn (room_id, department_id, doctor_id"
					+ ", patien_order_id,queue_sn) VALUES (?,?,?,?,?)";
			log.debug("开始从HIS同步诊室排队信息");
			long startTime = System.currentTimeMillis();
			this.getJdbcTemplate().batchUpdate(sql,
			new BatchPreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement pstmt, int i) throws SQLException {
					pstmt.setString(1, roomQueueSnList.get(i).getRoomId());
					pstmt.setString(2, roomQueueSnList.get(i).getDepartmentId());
					pstmt.setString(3, roomQueueSnList.get(i).getDoctorId());
					pstmt.setString(4, roomQueueSnList.get(i).getPatientOrderId());
					pstmt.setInt(5, roomQueueSnList.get(i).getQueueSn());
					log.debug("第"+(i+1)+"条诊室排队信息同步完成");
				}

				@Override
				public int getBatchSize() {
					return roomQueueSnList.size();
				}
			});
			long endTime = System.currentTimeMillis();
			log.debug("诊室排队信息数据同步完成,共:"+roomQueueSnList.size()+"条数据,耗时"+(endTime-startTime)+"毫秒");
			synChroRecordPO.setSpendTime((endTime-startTime));
			synChroRecordPO.setRecordCount(roomQueueSnList.size());
			synChroRecordPO.setSynchroResult("SUCCESS");
		}catch (Exception e) {
			synChroRecordPO.setSynchroResult("FAILED");
			synChroRecordPO.setRecordCount(0);
			log.error("同步诊室排队数据失败",e);
		}finally{
			synchroRecord.recordSynchroLog(synChroRecordPO, this.getJdbcTemplate());
		}
		
	}

}
