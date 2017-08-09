/**   
 * @Title: IDepartmentImpl.java 
 * @Package com.catic.mobilehos.dao.synchronize 
 * @Description: TODO
 * @author htWang   
 * @date 2014-5-20 上午9:49:50 
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

import com.catic.mobilehos.po.DepartmentPO;
import com.catic.mobilehos.po.SynChroRecordPO;

/**
 * @author WANG
 *
 */
public class IDepartmentImpl extends JdbcDaoSupport implements IDepartmentDao {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	/**
	 * 
	 * @Title: addDepartments 
	 * @Description: TODO 
	 * @param deptList  通过webService从his中读取科室数据插入
	 * @throws
	 */
	public void addDepartmentsFromHis(final List<DepartmentPO> deptList){
		
		SynchroRecord synchroRecord = new SynchroRecord();
		SynChroRecordPO synChroRecordPO = new SynChroRecordPO();
		synChroRecordPO.setSynchroTable("departments");
		
		try {
			log.debug("开始同步科室数据");
			String sql = "INSERT INTO departments (department_id, department_name, parent_id, department_addr) VALUES (?,?,?,?)";
			long sTime = System.currentTimeMillis();
			this.getJdbcTemplate().batchUpdate(sql,
					new BatchPreparedStatementSetter() {

						@Override
						public void setValues(PreparedStatement pstmt, int i)
								throws SQLException {
							pstmt.setString(1, deptList.get(i).getDepartmentId());
							pstmt.setString(2, deptList.get(i).getDepartmentName());
							pstmt.setString(3, deptList.get(i).getParentId());
							pstmt.setString(4, deptList.get(i).getDepartmentAddr());
							log.debug("第"+(i+1)+"科室数据同步完成");
						}

						@Override
						public int getBatchSize() {
							return deptList.size();
						}
					});
			long eTime = System.currentTimeMillis();
			log.debug("科室数据同步完成.......共计:"+deptList.size()+"条数据,耗时："+(eTime-sTime)+"毫秒");
			synChroRecordPO.setSpendTime((eTime-sTime));
			synChroRecordPO.setRecordCount(deptList.size());
			synChroRecordPO.setSynchroResult("SUCCESS");
		} catch (Exception e) {
			synChroRecordPO.setSynchroResult("FAILED");
			synChroRecordPO.setRecordCount(0);
			log.info(null,e);
		}finally{
			synchroRecord.recordSynchroLog(synChroRecordPO, this.getJdbcTemplate());
		}
	}
	
	/**
	 * 
	 * @Title: delAllDoctors 
	 * @Description: TODO 
	 * @param 删除本地数据科室数据 
	 * @throws
	 */
	public void delAllDeprtments(){
		try {
			String sql = "delete from departments";
			this.getJdbcTemplate().update(sql);
		} catch (Exception e) {
			log.error("删除本地科室数据异常：",e);
		}	
	}
}
