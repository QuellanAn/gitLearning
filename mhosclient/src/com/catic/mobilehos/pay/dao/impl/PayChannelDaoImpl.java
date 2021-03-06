package com.catic.mobilehos.pay.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.catic.mobilehos.pay.dao.BaseDao;
import com.catic.mobilehos.pay.dao.IPayChannelDao;
import com.catic.mobilehos.pay.entity.PayChannel;
import com.catic.mobilehos.utils.Page;

@Repository("payChannelDao")
public class PayChannelDaoImpl extends BaseDao implements IPayChannelDao {

	@Override
	public Boolean save(PayChannel payChannel) throws Exception {
		String sql = "INSERT INTO pay_channel(cID,cName,yqCode,sCode,ptCode,accountCode,remark,cStatus,creator,createTime)  VALUES(?,?,?,?,?,?,?,?,?,NOW())";
		List<Object> params = new ArrayList<Object>();
		params.add(payChannel.getCID());
		params.add(payChannel.getCName());
		params.add(payChannel.getYqCode());
		params.add(payChannel.getSCode());
		params.add(payChannel.getPtCode());
		params.add(payChannel.getAccountCode());
		params.add(payChannel.getRemark().trim());
		params.add(payChannel.getCStatus());
		params.add(payChannel.getCreator());
		int count = jdbc.update(sql, params.toArray());
		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<PayChannel> findAll(PayChannel payChannel, Page page)
			throws Exception {
		String sql = "";
		if (page == null) {
			sql = "SELECT count(0) AS count FROM pay_channel pc LEFT JOIN pay_yqinfo py ON pc.yqCode=py.yqCode LEFT JOIN pay_scene ps ON pc.sCode=ps.sCode LEFT JOIN pay_paytype pt ON pc.ptCode=pt.ptCode LEFT JOIN pay_account pa ON pc.accountCode=pa.accountCode WHERE 1=1";
		} else {
			sql = "SELECT pc.*,py.`name` AS yqName,ps.sName,pa.accountName,pt.ptName FROM pay_channel pc LEFT JOIN pay_yqinfo py ON pc.yqCode=py.yqCode LEFT JOIN pay_scene ps ON pc.sCode=ps.sCode LEFT JOIN pay_paytype pt ON pc.ptCode=pt.ptCode LEFT JOIN pay_account pa ON pc.accountCode=pa.accountCode WHERE 1=1";
		}
		StringBuffer sb = new StringBuffer(sql);
		List<Object> params = new ArrayList<Object>();
		if (StringUtils.isNotBlank(payChannel.getYqCode())) {
			sb.append(" AND pc.yqCode=?");
			params.add(payChannel.getYqCode());
		}
		if (StringUtils.isNotBlank(payChannel.getSCode())) {
			sb.append(" AND pc.sCode=?");
			params.add(payChannel.getSCode());
		}
		if (StringUtils.isNotBlank(payChannel.getPtCode())) {
			sb.append(" AND pc.ptCode=?");
			params.add(payChannel.getPtCode());
		}
		if (StringUtils.isNotBlank(payChannel.getAccountCode())) {
			sb.append(" AND pc.accountCode=?");
			params.add(payChannel.getAccountCode());
		}
		if (StringUtils.isNotBlank(payChannel.getCStatus())) {
			sb.append(" AND pc.cStatus=?");
			params.add(payChannel.getCStatus());
		}
		if (page != null) {
			sb.append(" ORDER BY pc.createTime DESC");
			sb.append(" LIMIT ?,?");
			params.add(page.getFirstIndex());
			params.add(page.getPageSize());
		}
		List<PayChannel> list = jdbc.query(sb.toString(), params.toArray(),
				new BeanPropertyRowMapper(PayChannel.class));
		return list;
	}

	/**
	 * 通过编码查询
	 * 
	 * @param code
	 * @return
	 */
	public PayChannel findByID(String cID)throws Exception  {
		String sql = "SELECT * FROM pay_channel WHERE cID=?";
		List<Object> params = new ArrayList<Object>();
		params.add(cID);
		List<PayChannel> list = jdbc.query(sql, params.toArray(),
				new BeanPropertyRowMapper(PayChannel.class));
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;

	}

	/**
	 * 修改
	 * @param payChannel
	 * @return
	 */
	public boolean modify(PayChannel payChannel)throws Exception {
		String sql = "UPDATE pay_channel SET cID=?";
		StringBuilder sb=new StringBuilder(sql);
		List<Object> params=new ArrayList<Object>();
		params.add(payChannel.getCID());
		if (StringUtils.isNotBlank(payChannel.getYqCode())) {
             sb.append(",yqCode=?");
             params.add(payChannel.getYqCode());
		}
		if (StringUtils.isNotBlank(payChannel.getSCode())) {
			 sb.append(",sCode=?");
             params.add(payChannel.getSCode());
		}
		if (StringUtils.isNotBlank(payChannel.getPtCode())) {
			 sb.append(",ptCode=?");
             params.add(payChannel.getPtCode());
		}
		if (StringUtils.isNotBlank(payChannel.getAccountCode())) {
			 sb.append(",accountCode=?");
             params.add(payChannel.getAccountCode());
		}
		if (StringUtils.isNotBlank(payChannel.getCStatus())) {
			 sb.append(",cStatus=?");
             params.add(payChannel.getCStatus());
		}
		if (StringUtils.isNotBlank(payChannel.getRemark())) {
			 sb.append(",remark=?");
            params.add(payChannel.getRemark().trim());
		}
		if(StringUtils.isNotBlank(payChannel.getCName())){
			sb.append(",cName=?");
			params.add(payChannel.getCName().trim());
		}
		sb.append("WHERE cID=?");
		params.add(payChannel.getCID());
		int count=jdbc.update(sb.toString(),params.toArray());
		if(count>0){
			return true;
		}
		return false;

	}
}
