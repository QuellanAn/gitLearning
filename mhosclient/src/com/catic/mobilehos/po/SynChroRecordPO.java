/**   
 * @Title: SynChroRecordPO.java 
 * @Package com.catic.mobilehos.po 
 * @Description: TODO
 * @author htWang   
 * @date 2014-5-27 下午5:07:44 
 * 
 */ 
package com.catic.mobilehos.po;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

/**
 * @author WANG
 *
 */
public class SynChroRecordPO implements RowMapper<SynChroRecordPO>{
	
	private int recordId;				//同步记录标识
	
	private String synchroResult;		//同步结果
	
	private Timestamp synchroTime;		//执行同步操作的时间
	
	private int recordCount;			//同步的记录数
	
	private String synchroTable;		//每次进行同步的表名
	
	private long spendTime;

	public SynChroRecordPO(){
		this.synchroResult="SUCCESS";
		this.recordCount=0;
		this.spendTime=0;
		this.synchroTime =new Timestamp(new Date().getTime());
	}
	
	
	
	@Override
	public SynChroRecordPO mapRow(ResultSet rs, int rowCount) throws SQLException {
		SynChroRecordPO synChroRecordPO = new SynChroRecordPO();
		synChroRecordPO.setRecordId(rs.getInt("record_id"));
		synChroRecordPO.setSynchroResult(rs.getString("synchro_result"));
		synChroRecordPO.setSynchroTime(rs.getTimestamp("synchro_time"));
		synChroRecordPO.setRecordCount(rs.getInt("record_count"));
		synChroRecordPO.setSynchroTable(rs.getString("synchro_table"));
		synChroRecordPO.setSpendTime(rs.getLong("spend_time"));
		return synChroRecordPO;
	}

	/**
	 * @return the recordId
	 */
	public int getRecordId() {
		return recordId;
	}

	/**
	 * @param recordId the recordId to set
	 */
	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	/**
	 * @return the synchroResult
	 */
	public String getSynchroResult() {
		return synchroResult;
	}

	/**
	 * @param synchroResult the synchroResult to set
	 */
	public void setSynchroResult(String synchroResult) {
		this.synchroResult = synchroResult;
	}

	/**
	 * @return the synchroTime
	 */
	public Timestamp getSynchroTime() {
		return synchroTime;
	}

	/**
	 * @param synchroTime the synchroTime to set
	 */
	public void setSynchroTime(Timestamp synchroTime) {
		this.synchroTime = synchroTime;
	}

	/**
	 * @return the recordCount
	 */
	public int getRecordCount() {
		return recordCount;
	}

	/**
	 * @param recordCount the recordCount to set
	 */
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	/**
	 * @return the synchroTable
	 */
	public String getSynchroTable() {
		return synchroTable;
	}

	/**
	 * @param synchroTable the synchroTable to set
	 */
	public void setSynchroTable(String synchroTable) {
		this.synchroTable = synchroTable;
	}



	/**
	 * @return the spendTime
	 */
	public long getSpendTime() {
		return spendTime;
	}



	/**
	 * @param spendTime the spendTime to set
	 */
	public void setSpendTime(long spendTime) {
		this.spendTime = spendTime;
	}

}
