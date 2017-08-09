/**   
 * @Title: SynchroRecord.java 
 * @Package com.catic.mobilehos.utils 
 * @Description: TODO
 * @author htWang   
 * @date 2014-5-27 下午5:27:05 
 * 
 */ 
package com.catic.mobilehos.dao.synchronize;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.catic.mobilehos.po.SynChroRecordPO;

/**
 * @author WANG
 *
 */
public class SynchroRecord{
	
	private Log log = LogFactory.getLog(this.getClass());
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void recordSynchroLog(final SynChroRecordPO synChroRecordPO,JdbcTemplate JdbcTemplate){
		try {
			//JdbcTemplate.getDataSource().getConnection().setAutoCommit(true);
			final String sql = "INSERT INTO synchro_record (synchro_time, synchro_result"
					+ ", record_count, synchro_table,spend_time) VALUES (?,?,?,?,?)";
			JdbcTemplate.update(sql,new PreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement preparedStatement) throws SQLException {
					preparedStatement.setTimestamp(1, synChroRecordPO.getSynchroTime());
					preparedStatement.setString(2, synChroRecordPO.getSynchroResult());
					preparedStatement.setInt(3, synChroRecordPO.getRecordCount());
					preparedStatement.setString(4,synChroRecordPO.getSynchroTable());
					preparedStatement.setLong(5,synChroRecordPO.getSpendTime());
				}
			});
		} catch (Exception e) {
			log.error("插入同步记录失败",e);
		}
	}
}
