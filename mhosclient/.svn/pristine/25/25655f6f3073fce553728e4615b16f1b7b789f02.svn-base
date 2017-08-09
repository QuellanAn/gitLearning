package com.catic.mobilehos.pay.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.catic.mobilehos.pay.dao.BaseDao;
import com.catic.mobilehos.pay.dao.IPayTerminalDao;
import com.catic.mobilehos.pay.entity.PayTerminal;
import com.catic.mobilehos.utils.Page;
@Repository("payTerDao")
public class PayTerminalDaoImpl extends BaseDao implements IPayTerminalDao {
	private Log log = LogFactory.getLog(this.getClass());

	private String addParasToSQL(PayTerminal payTer,String sql,List<Object> lstArgs){
		if(payTer!=null){
			if (StringUtils.isNotBlank(payTer.getFacilityId())){
				sql += " and te.facility_id like ?";
				lstArgs.add("%"+payTer.getFacilityId()+"%");
			}
			/*if (StringUtils.isNotBlank(payTer.getCollectorId())){
				sql += " and te.collector_id like ?";
				lstArgs.add("%"+payTer.getCollectorId()+"%");
			}*/
			if (StringUtils.isNotBlank(payTer.getDistrict())){
				sql += " and te.district = ?";
				lstArgs.add(payTer.getDistrict());
			}
			if (StringUtils.isNotBlank(payTer.getPayScene())){
				sql += " and te.pay_scene = ?";
				lstArgs.add(payTer.getPayScene());
			}
			if (StringUtils.isNotBlank(payTer.getFacilityStatus())){
				sql += " and te.facility_status = ?";
				lstArgs.add(payTer.getFacilityStatus());
			}
			if (StringUtils.isNotBlank(payTer.getPutAddress())){
				sql += " and te.put_address like ?";
				lstArgs.add("%"+payTer.getPutAddress()+"%");
			}
			if (StringUtils.isNotBlank(payTer.getFacilityType())){// 设备类型
				sql += " and te.facility_type = ?";
				lstArgs.add(payTer.getFacilityType());
			}
			if(StringUtils.isNotBlank(payTer.getMaster_version_code())){
				if("1".equals(payTer.getIsLargerThan())){
					sql += " and te.master_version_code >= ?";
					lstArgs.add(payTer.getMaster_version_code());
				}else if("-1".equals(payTer.getIsLargerThan())){
					sql += " and (te.master_version_code is null || te.master_version_code='' || te.master_version_code < ? )";
					lstArgs.add(payTer.getMaster_version_code());
				}
			}
			if(StringUtils.isNotBlank(payTer.getDaemon_version_code())){
				if("1".equals(payTer.getIsLargerThan())){
					sql += " and te.daemon_version_code >= ?";
					lstArgs.add(payTer.getDaemon_version_code());
				}else if("-1".equals(payTer.getIsLargerThan())){
					sql += " and (te.daemon_version_code is null || te.daemon_version_code='' || te.daemon_version_code < ? )";
					lstArgs.add(payTer.getDaemon_version_code());
				}
			}
		}
		return sql;
	}

	@Override
	public Boolean insert(PayTerminal payTer) throws Exception {
		try{
			String sql = "INSERT INTO pay_terminal(facility_id,collector_id,facility_name,district," +
					"facility_status,put_address,create_time,pay_scene,remark,facility_type)values(?,?,?,?,?,?,?,?,?,?)";
			jdbc.update(sql,payTer.getFacilityId(), payTer.getCollectorId()
					  , payTer.getFacilityName(), payTer.getDistrict()
					  , payTer.getFacilityStatus(), payTer.getPutAddress()
					  , payTer.getCreateTime(), payTer.getPayScene()
					  ,payTer.getRemark(), payTer.getFacilityType());
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}
		return null;
	}

	@Override
	public List<PayTerminal> findAll(Page page, PayTerminal payTer) throws Exception {
		String sql;
		if(page==null){
			sql="SELECT COUNT(0) AS count FROM pay_terminal te WHERE te.facility_status!=4 ";
		}else{
			sql="select te.*,yq.`name` as name,ps.sName as sceneName " +
					"from pay_terminal te LEFT JOIN pay_yqinfo yq on te.district=yq.yqId LEFT JOIN pay_scene ps on te.pay_scene=ps.sCode WHERE te.facility_status!=4 ";
		}
		List<Object> param=new ArrayList<Object>();
		sql=addParasToSQL(payTer,sql,param);
		sql += " order by te.id asc ";
		if(page!=null){
			sql+=" LIMIT ?,?";
			param.add(page.getFirstIndex());
			param.add(page.getPageSize());
		}
		List<PayTerminal> list=jdbc.query(sql,param.toArray(),new BeanPropertyRowMapper(PayTerminal.class));
		return list;
	}

	@Override
	public Boolean delete(int id) throws Exception {
		try{
			String sql = "update pay_terminal set facility_status=4 where id=? ";
			jdbc.update(sql,id);
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}
		return null;
	}

	@Override
	public Boolean modify(PayTerminal payTer) throws Exception {
		try{
			/*String sql = "update pay_terminal set facility_id=?,collector_id=?,facility_name=?," +
					"district=?,facility_status=?,put_address=?," +
					"create_time=?,pay_scene=?,remark=?,facility_type=? where id=?";
			jdbc.update(sql,payTer.getFacilityId(), payTer.getCollectorId()
					  , payTer.getFacilityName(), payTer.getDistrict()
					  , payTer.getFacilityStatus()
					  , payTer.getPutAddress(), payTer.getCreateTime()
					  , payTer.getPayScene(), payTer.getRemark()
					  , payTer.getFacilityType(), payTer.getId());*/
			
			StringBuffer sql = new StringBuffer("update pay_terminal set facility_id=?,facility_name=?," +
					"district=?,facility_status=?,put_address=?," +
					"create_time=?,remark=? ");
			List<Object> params = new ArrayList<Object>();
			params.add(payTer.getFacilityId());
			params.add(payTer.getFacilityName());
			params.add(payTer.getDistrict());
			params.add(payTer.getFacilityStatus());
			params.add(payTer.getPutAddress());
			params.add(payTer.getCreateTime());
			params.add(payTer.getRemark());
			if(StringUtils.isNotBlank(payTer.getCollectorId())){
				sql.append(" ,collector_id=? ");
				params.add(payTer.getCollectorId());
			}
			if(StringUtils.isNotBlank(payTer.getPayScene())){
				sql.append(" ,pay_scene=? ");
				params.add(payTer.getPayScene());
			}
			if(StringUtils.isNotBlank(payTer.getFacilityType())){
				sql.append(" ,facility_type=? ");
				params.add(payTer.getFacilityType());
			}
			sql.append(" where id=?");
			params.add(payTer.getId());
			jdbc.update(sql.toString(), params.toArray());
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}
		return null;
	}

	@Override
	public PayTerminal findById(int id) throws Exception {
		try{
			PayTerminal payTer=new PayTerminal();
//			String sql = "select * from pay_terminal pt where id=? ";
			String sql = "select pt.*,yq.`name` name from pay_terminal pt LEFT JOIN pay_yqinfo yq on pt.district=yq.yqId where id=? ";
			payTer = jdbc.queryForObject(sql, new BeanPropertyRowMapper(PayTerminal.class),id);
			return payTer;
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}
	}
	
	/**
	 * 通过编号查询
	 * @param code
	 * @return
	 * @throws Exception
	 */
	@Override
	public PayTerminal findByCode(String code) throws Exception {
		try{
			String sql = "SELECT * FROM pay_terminal pt WHERE facility_status!=4 AND facility_id='"+code+"'";
			List<PayTerminal>  payTer= jdbc.query(sql, new BeanPropertyRowMapper(PayTerminal.class));
			if(payTer!=null&&payTer.size()>0){
				return payTer.get(0);
			}else{
				return null;
			}
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}
	}

	@Override
	public List<PayTerminal> findFacilityVersion(Page page, PayTerminal payTer)
			throws Exception {
		String sql;
		if(page==null){
			sql="SELECT COUNT(0) AS count FROM pay_terminal te WHERE te.facility_status!=4 ";
		}else{
			sql="select te.*,yq.`name` as name,sv1.version_name mainProgramVersion,sv2.version_name daemonVersion " +
					" from pay_terminal te LEFT JOIN pay_yqinfo yq on te.district=yq.yqId " +
					" LEFT JOIN selfservice_version sv1 ON sv1.facility_type=te.facility_type and te.master_version_code = sv1.version_code and sv1.upgrade_type='0' " +
					" LEFT JOIN selfservice_version sv2 ON sv2.facility_type=te.facility_type and te.daemon_version_code = sv2.version_code and sv2.upgrade_type='1' WHERE te.facility_status!=4 ";
		}
		List<Object> param=new ArrayList<Object>();
		sql=addParasToSQL(payTer,sql,param);
		sql += " order by te.id asc ";
		if(page!=null){
			sql+=" LIMIT ?,?";
			param.add(page.getFirstIndex());
			param.add(page.getPageSize());
		}
		List<PayTerminal> list=jdbc.query(sql,param.toArray(),new BeanPropertyRowMapper<PayTerminal>(PayTerminal.class));
		return list;
	}

	@Override
	public boolean updateVersioncode(PayTerminal payTer) {
		if(StringUtils.isNotBlank(payTer.getMaster_version_code()) || StringUtils.isNotBlank(payTer.getDaemon_version_code())){
			StringBuffer sql = new StringBuffer("update pay_terminal set id=?");
			List<Object> params = new ArrayList<Object>();
			params.add(payTer.getId());
			if(StringUtils.isNotBlank(payTer.getMaster_version_code())){
				sql.append(" ,master_version_code=? ");
				params.add(payTer.getMaster_version_code());
			}
			if(StringUtils.isNotBlank(payTer.getDaemon_version_code())){
				sql.append(" ,daemon_version_code=? ");
				params.add(payTer.getDaemon_version_code());
			}
			sql.append(" where id=? ");
			params.add(payTer.getId());
			return jdbc.update(sql.toString(), params.toArray()) > 0;
		}
		return false;
	}

}
