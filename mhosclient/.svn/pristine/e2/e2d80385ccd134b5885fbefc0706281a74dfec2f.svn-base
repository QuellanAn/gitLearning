/**   
 * @Title: RoomDaoImpl.java 
 * @Package com.catic.mobilehos.dao.jdbc 
 * @Description: TODO
 * @author htWang   
 * @date 2014-5-19 下午4:50:04 
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

import com.catic.mobilehos.po.RoomPO;
import com.catic.mobilehos.po.SynChroRecordPO;

/**
 * @author WANG
 *
 */
public class IRoomDaoImpl extends JdbcDaoSupport implements IRoomDao{
	
	private Log log = LogFactory.getLog(this.getClass());
	/* (non-javadoc) 
	 * <p>Title: delAllRooms</p> 
	 * <p>Description: </p>  
	 * @see com.catic.mobilehos.dao.RoomDao#delAllRooms() 
	*/ 
	@Override
	public void delAllRooms() {
		try {
			log.info("开始删除本地数据库诊室数据...");
			String sql = "delete from room";
			this.getJdbcTemplate().update(sql);
			log.info("删除本地数据库诊室数据成功...");
		} catch (Exception e) {
			log.error("删除本地数据库诊室数据失败",e);
		}
	}

	/* (non-javadoc) 
	 * <p>Title: addRoomsFromHIS</p> 
	 * <p>Description: </p> 
	 * @param roomsList 
	 * @see com.catic.mobilehos.dao.RoomDao#addRoomsFromHIS(java.util.List) 
	*/ 
	@Override
	public void addRoomsFromHIS(final List<RoomPO> roomsList) {
		
		SynchroRecord synchroRecord = new SynchroRecord();
		SynChroRecordPO synChroRecordPO = new SynChroRecordPO();
		synChroRecordPO.setSynchroTable("room");
		
		try {
			log.debug("开始同步诊室数据...");
			long startTime = System.currentTimeMillis();
			String sql = "INSERT INTO room (room_id, room_name, department_id, room_addr) VALUES (?,?,?,?)";
			this.getJdbcTemplate().batchUpdate(sql,
					new BatchPreparedStatementSetter() {
						@Override
						public void setValues(PreparedStatement pstmt, int i)
								throws SQLException {
							pstmt.setString(1, roomsList.get(i).getRoomId());
							pstmt.setString(2, roomsList.get(i).getRoomName());
							pstmt.setString(3, roomsList.get(i).getDepartmentId());
							pstmt.setString(4, roomsList.get(i).getRoomAddr());
							log.debug("第"+(i+1)+"条诊室数据同步成功....");
						}
						
						@Override
						public int getBatchSize() {
							return roomsList.size();
						}
					});
			long endTime = System.currentTimeMillis();
			log.debug("诊室数据同步完成,共:"+roomsList.size()+"条数据,耗时"+(endTime-startTime)+"毫秒");
			synChroRecordPO.setSpendTime((endTime-startTime));
			synChroRecordPO.setRecordCount(roomsList.size());
			synChroRecordPO.setSynchroResult("SUCCESS");
		}catch (Exception e) {
			synChroRecordPO.setSynchroResult("FAILED");
			synChroRecordPO.setRecordCount(0);
			log.error("同步诊室数据失败",e);
		}finally{
			synchroRecord.recordSynchroLog(synChroRecordPO, this.getJdbcTemplate());
		}
	}

}
