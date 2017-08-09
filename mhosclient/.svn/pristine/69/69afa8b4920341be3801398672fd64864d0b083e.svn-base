/**   
 * @Title: IAppRegOrderTypeDaoImpl.java 
 * @Package com.catic.mobilehos.wsClient.dao.impl 
 * @Description: TODO
 * @author htWang   
 * @date 2014-5-20 上午9:33:38 
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

import com.catic.mobilehos.po.RegOrderTypePO;
import com.catic.mobilehos.po.SynChroRecordPO;

/**
 * @author WANG
 * 
 */
public class IAppRegOrderTypeDaoImpl extends JdbcDaoSupport implements IAppRegOrderTypeDao {
	private Log log = LogFactory.getLog(this.getClass());

	/*
	 * (non-javadoc) <p>Title: addOrderTypeFromHis</p> <p>Description: </p>
	 * 
	 * @param typeList
	 * 
	 * @see
	 * com.catic.mobilehos.dao.AppRegOrderTypeDao#addOrderTypeFromHis(java.util
	 * .List)
	 */
	@Override
	public void addOrderTypeFromHis(final List<RegOrderTypePO> typeList) {

		SynchroRecord synchroRecord = new SynchroRecord();
		SynChroRecordPO synChroRecordPO = new SynChroRecordPO();
		synChroRecordPO.setSynchroTable("app_reg_order_type");

		try {
			String sql = "INSERT INTO app_reg_order_type (type_code, type_name) VALUES (?,?)";
			log.debug("开始同步号源类型数据");
			long startTime = System.currentTimeMillis();
			this.getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement pstmt, int i) throws SQLException {
					pstmt.setString(1, typeList.get(i).getTypeCode());
					pstmt.setString(2, typeList.get(i).getTypeName());
					log.debug("第" + (i + 1) + "条号源类型数据同步完成");
				}

				@Override
				public int getBatchSize() {
					return typeList.size();
				}
			});
			long endTime = System.currentTimeMillis();
			log.debug("本次同步号源类型数据,共" + typeList.size() + "条,耗时：" + (endTime - startTime) + "毫秒");
			synChroRecordPO.setSpendTime((endTime - startTime));
			synChroRecordPO.setRecordCount(typeList.size());
			synChroRecordPO.setSynchroResult("SUCCESS");
		} catch (Exception e) {
			synChroRecordPO.setSynchroResult("FAILED");
			synChroRecordPO.setRecordCount(0);
			log.error("同步号源类型数据异常", e);
		} finally {
			synchroRecord.recordSynchroLog(synChroRecordPO, this.getJdbcTemplate());
		}

	}

	/*
	 * (non-javadoc) <p>Title: delAllOrderType</p> <p>Description: </p>
	 * 
	 * @see com.catic.mobilehos.dao.AppRegOrderTypeDao#delAllOrderType()
	 */
	@Override
	public int delAllOrderType() {
		try {
			String sql = "delete from app_reg_order_type";
			return this.getJdbcTemplate().update(sql);
		} catch (Exception e) {
			log.error("删除本地号源类型列表异常", e);
			return 0;
		}

	}
}
