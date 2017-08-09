/**   
 * @Title: AppRegOrderExceptionDaoImpl.java 
 * @Package com.catic.mobilehos.dao.jdbc 
 * @Description: TODO
 * @author htWang   
 * @date 2014-5-19 下午5:10:45 
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

import com.catic.mobilehos.po.AppRegExceptionPO;
import com.catic.mobilehos.po.SynChroRecordPO;

/**
 * @author WANG
 * 
 */
public class IAppRegExceptionDaoImpl extends JdbcDaoSupport implements IAppRegExceptionDao {

	private Log log = LogFactory.getLog(this.getClass());

	/*
	 * (non-javadoc) <p>Title: addOrderExceptFromHIS</p> <p>Description: </p>
	 * 
	 * @param apoeList
	 * 
	 * @see
	 * com.catic.mobilehos.dao.AppRegOrderExceptionDao#addOrderExceptFromHIS
	 * (java.util.List)
	 */
	@Override
	public void addOrderExceptFromHIS(final List<AppRegExceptionPO> apoeList) {

		SynchroRecord synchroRecord = new SynchroRecord();
		SynChroRecordPO synChroRecordPO = new SynChroRecordPO();
		synChroRecordPO.setSynchroTable("app_reg_exception");

		try {
			// String nativeSql = "select * from app_reg_exception";
			// final List<AppRegExceptionPO> exceptList =
			// this.getJdbcTemplate().query(nativeSql, new AppRegExceptionPO());
			// boolean isSynchro = false; // 是否进行同步
			// for (int j = 0; j < apoeList.size(); j++) {
			// if (exceptList.get(j).getExceptId() !=
			// apoeList.get(j).getExceptId()) {
			// isSynchro = true; // 如果有新的数据 就把新的数据同步过来
			// }
			// }
			// if (isSynchro) {
			String sql = "INSERT INTO app_reg_exception (order_id, exception_type, exception_time,excep_id) VALUES (?,?,?,?)";
			log.debug("开始同步...预约异常信息");
			long startTime = System.currentTimeMillis();
			this.getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement pstmt, int i) throws SQLException {

					pstmt.setString(1, apoeList.get(i).getOrderId());
					pstmt.setString(2, apoeList.get(i).getExceptionType());
					pstmt.setTimestamp(3, apoeList.get(i).getExceptionTime());
					pstmt.setInt(4, apoeList.get(i).getExceptId());
					log.debug("第" + (i + 1) + "条预约异常信息同步完成");
					
					
					updateOrderStatus(apoeList.get(i).getOrderId(), apoeList.get(i).getExceptionType());//更新挂号单的状态
					
					setUserStatus(apoeList.get(i).getOrderId(), apoeList.get(i).getExceptionType(), 7, 3, 7);
				}

				@Override
				public int getBatchSize() {
					return apoeList.size();
				}
			});
			long endTime = System.currentTimeMillis();
			log.debug("本次同步预约异常信息数据,成功：" + apoeList.size() + "条,耗时：" + (endTime - startTime) + "毫秒");
			synChroRecordPO.setSpendTime((endTime - startTime));
			synChroRecordPO.setRecordCount(apoeList.size());
			synChroRecordPO.setSynchroResult("SUCCESS");
			// }
		} catch (Exception e) {
			synChroRecordPO.setSynchroResult("FAILED");
			synChroRecordPO.setRecordCount(0);
			log.error("同步预约异常信息发生异常", e);
		} finally {
			synchroRecord.recordSynchroLog(synChroRecordPO, this.getJdbcTemplate());
		}
	}

	@Override
	public long findLastException() {
		try {

			String sql = "select max(excep_id) from app_reg_exception";
			return getJdbcTemplate().queryForObject(sql, Long.class);
		} catch (NullPointerException e) {
			return 0;
		}
	}

	/**
	 * @param orderid, 挂号单的id号
	 * @param excType, 异常的类型,类型分类见文档
	 * @param limitDays, 检测,在之前指定的天数之内
	 * @param times, 出现此挂号异常的次数
	 * @param blackDays, 则将此用户拉入黑名单多少天
	 * 
	 * return 是否执行成功
	 */
	@Override
	public int setUserStatus(String orderid, String excType, int limitDays, int times, int blackDays) {
		//根据挂号单号,查出 userid
		String sql = "SELECT user_id FROM app_reg_orders WHERE app_reg_order_id = ?";
		List<String> userIds = getJdbcTemplate().queryForList(sql, String.class, orderid);
		if(userIds != null && !userIds.isEmpty()) {
			String userId = userIds.get(0);
			
			//查询此用户, 在之前的{limitDasys}天内,关于{excType}这个异常出现的次数(比如,在过去的7天内,'延期'出现的次数.)
			sql = "SELECT COUNT(0) FROM app_reg_orders WHERE user_id = ? AND datediff(NOW(),create_date) <= ? AND `status` LIKE ?";
			int count = getJdbcTemplate().queryForObject(sql, Integer.class, userId, limitDays, "%" + excType + "%");
			
			//比如数据库查出,其过去7天内, '延期' 的5次, 但我们的规则是一周最多只能出现{times}次
			if(count >= times) {
				//则我们将其拉入黑名单内,黑名单的期限为 ,从现在算起共{blackDays}天
				sql = "UPDATE users SET black_time = DATE_ADD(NOW(),INTERVAL ? DAY) WHERE user_id = ?";
				getJdbcTemplate().update(sql, blackDays, userId);
			}
		}
		return 0;
	}

	@Override
	public int updateOrderStatus(String orderid, String excType) {
		String sql = "UPDATE app_reg_orders SET `status` = CONCAT(`status`, ?) WHERE app_reg_order_id = ?";
		return getJdbcTemplate().update(sql, excType, orderid);
	}
}
