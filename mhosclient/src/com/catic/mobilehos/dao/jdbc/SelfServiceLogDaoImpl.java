package com.catic.mobilehos.dao.jdbc;  

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.catic.mobilehos.dao.SelfServiceLogDao;
import com.catic.mobilehos.po.SelfServiceLogPO;
import com.catic.mobilehos.utils.Page;

/**  
 * 类说明:自助终端日志dao接口实现
 * @author 朱伟权
 * 创建时间: 2017-6-27 上午11:04:45
 */
public class SelfServiceLogDaoImpl extends JdbcDaoSupport implements SelfServiceLogDao {

	@Override
	public List<SelfServiceLogPO> findAll(Page page, SelfServiceLogPO po) {
		StringBuffer sql;
		if(page == null){
			sql = new StringBuffer(" SELECT COUNT(0) AS count ");
		}else{
			sql = new StringBuffer("select s.id,s.log_type logType,s.facility_id facilityId,yq.name district," +
					" p.put_address putAddress,s.log_filename logFileName,s.log_path logPath,s.update_time updateTime,p.facility_type facilityType ");
		}
		sql.append(" from selfservice_log s inner join pay_terminal p on s.facility_id=p.facility_id LEFT JOIN pay_yqinfo yq on p.district=yq.yqId where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if(po != null){
			if(StringUtils.isNotBlank(po.getLogType())){
				sql.append(" AND s.log_type=? ");
				params.add(po.getLogType());
			}
			if(StringUtils.isNotBlank(po.getFacilityId())){
				sql.append(" and s.facility_id like ? ");
				params.add("%" + po.getFacilityId() + "%");
			}
			if(StringUtils.isNotBlank(po.getDistrict())){
				sql.append(" AND p.district=? ");
				params.add(po.getDistrict());
			}
			if(StringUtils.isNotBlank(po.getFacilityType())){
				sql.append(" AND p.facility_type=? ");
				params.add(po.getFacilityType());
			}
			if(StringUtils.isNotBlank(po.getBeginDate())){
				sql.append(" AND s.update_time>=?");
				params.add(po.getBeginDate()+" 00:00:00");
			}
			if(StringUtils.isNotBlank(po.getEndDate())){
				sql.append(" AND s.update_time<=?");
				params.add(po.getEndDate()+" 23:59:59");
			}
		}
		sql.append(" order by s.update_time desc ");
		if(page != null){
			sql.append(" LIMIT ?,?");
			params.add(page.getFirstIndex());
			params.add(page.getPageSize());
		}
		return getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<SelfServiceLogPO>(SelfServiceLogPO.class));
	}
	
	@Override
	public boolean save(SelfServiceLogPO po) {
		String sql = "INSERT INTO selfservice_log(log_type,log_status,facility_id,log_filename,log_path,update_time) " +
				" values(?,?,?,?,?,?)";
		return getJdbcTemplate().update(sql,po.getLogType(), "0"
				  , po.getFacilityId(), po.getLogFileName(), po.getLogPath()
				  , po.getUpdateTime()) > 0;
	}

}
 