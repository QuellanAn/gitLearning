package com.catic.mobilehos.dao.jdbc;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.catic.mobilehos.dao.HosPubInfoDAO;
import com.catic.mobilehos.po.HosPubInfoPO;
import com.catic.mobilehos.po.HosPubInfoPO.StatByCatPO;
import com.catic.mobilehos.po.HosPubInfoPO.StatByTypePO;

public class HosPubInfoDAOImpl extends JdbcDaoSupport implements HosPubInfoDAO {
	
	private Log log = LogFactory.getLog(this.getClass());


	@Override
	public void addHosPubInfo(final HosPubInfoPO info) {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "INSERT INTO hos_pub_info(info_id, subject, concise"
						 + ", pub_date, update_date, image"
						 + ", html, html_content, create_date, browse_count"
						 + ", info_cat_code, status, is_main, exp_pub_date"
						 + ", editor) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			jt.update(sql, info.getHosInfoId()
					,info.getSubject()
					,info.getConcise()
					,info.getPubDate()
					,info.getUpdateDate()
					,info.getImage()
					,info.getHtml()
					,info.getHtmlContent()
					,info.getCreateDate()
					,info.getBrowseCount()
					,info.getInfoCatCode()
					,info.getStatus()
					,info.getIsMain()
					,info.getExpPubDate()
					,info.getEditor());
				    
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}
	}
	
	
	@Override
	public void updateHosPubInfo(final HosPubInfoPO info) {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "update hos_pub_info set subject = ?"
					      + ", concise = ?, update_date=?"
					      + ", html = ?, html_content = ?, is_main=?, info_cat_code = ?, exp_pub_date=?";
			if (info.getImage() != null){
				sql = sql + ", image = ? where info_id = ?";
				jt.update(sql,info.getSubject()
						  , info.getConcise()
						  , info.getUpdateDate()
						  , info.getHtml()
						  , info.getHtmlContent()
						  , info.getIsMain()
						  , info.getInfoCatCode()
						  , info.getExpPubDate()
						  , info.getImage()
						  , info.getHosInfoId());
			}else{
				sql = sql + " where info_id = ?";
				jt.update(sql,info.getSubject()
						  , info.getConcise()
						  , info.getUpdateDate()
						  , info.getHtml()
						  , info.getHtmlContent()
						  , info.getIsMain()
						  , info.getInfoCatCode()
						  , info.getExpPubDate()
						  , info.getHosInfoId());
			}
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}

	}
	
	@Override
	public void updateHosPubInfoStatus(String infoid, int status, String approver){
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			if (status == HosPubInfoPO.STATUS_APPROVED){
				String sql = "update hos_pub_info set status = ?, approve_date = ?, approver=? where info_id = ?"; 
				jt.update(sql, status,new Timestamp((new Date()).getTime()), approver, infoid);
			}else if (status == HosPubInfoPO.STATUS_UNAPPROVED){
				String sql = "update hos_pub_info set status = ? , approver = ? where info_id = ?"; 
				jt.update(sql, status, approver, infoid);
			}else{
				String sql = "update hos_pub_info set status = ? where info_id = ?"; 
				jt.update(sql, status, infoid);
			}
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}
	}
	

	
	@Override
	public void deleteHosPubInfo(String infoid) {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "delete from hos_pub_info where info_id = ?";
			jt.update(sql, infoid);
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}

	}


	@Override
	public List<HosPubInfoPO> findLastPubHosInfo(int infoType, int num) {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "select * from hos_pub_info hpi, info_cat ic "
					+ " where hpi.info_cat_code = ic.info_cat_code "
					+ " and ic.info_type = ?"
					+ " and hpi.status = ? and hpi.exp_pub_date <= ?"
					+ " order by hpi.pub_date desc limit ?";
			List<HosPubInfoPO> lst = jt.query(sql, new HosPubInfoPO(),infoType
					, HosPubInfoPO.STATUS_APPROVED, new Date(), num);
			return lst;
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}
	}



	@Override
	public List<HosPubInfoPO> findNewHosInfoByTime(int infoType, Date time, int num) {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "select * from hos_pub_info hpi, info_cat ic "
					+ " where hpi.info_cat_code = ic.info_cat_code and ic.info_type = ? and hpi.pub_date > ?"
					+ " and hpi.status = ? and hpi.exp_pub_date <= ? order by hpi.pub_date desc limit ?";
			List<HosPubInfoPO> lst = jt.query(sql, new HosPubInfoPO()
			, infoType,  new Timestamp(time.getTime())
			, HosPubInfoPO.STATUS_APPROVED, new Date(), num);
			return lst;
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}
	}


	@Override
	public List<HosPubInfoPO> findOldHosInfoByTime(int infoType, Date time, int num) {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "select * from hos_pub_info hpi, info_cat ic"
					+ " where hpi.info_cat_code = ic.info_cat_code"
					+ " and ic.info_type = ? and hpi.pub_date < ? "
					+ " and hpi.status = ? and hpi.exp_pub_date <= ? "
					+ " order by hpi.pub_date desc limit ?";
			List<HosPubInfoPO> lst = jt.query(sql, new HosPubInfoPO()
				, infoType, new Timestamp(time.getTime())
				, HosPubInfoPO.STATUS_APPROVED, new Date()
			    , num);
			return lst;
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}
	}


	@Override
	public HosPubInfoPO getHosInfo(String id) {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "select * from hos_pub_info hpi, info_cat ic where hpi.info_cat_code = ic.info_cat_code and hpi.info_id = ?";
			HosPubInfoPO info = jt.queryForObject(sql, new HosPubInfoPO(), id);
			return info;
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}
	}

	@Override
	public void increaseBrowseCount(String id) {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "update hos_pub_info set browse_count = browse_count + 1 where info_id = ?";
			jt.update(sql, id);
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}
	}
	
	
	@Override
	public List<HosPubInfoPO> findUnApprovedHosInfo(int infoType,
			String infoCatCode, int offset, int length) {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			if (StringUtils.isNotBlank(infoCatCode)){
				String sql = "select hpi.*, ic.info_type, ic.info_cat_name from hos_pub_info hpi, info_cat ic where hpi.info_cat_code = ic.info_cat_code and hpi.status <= ? and hpi.info_cat_code = ?"
						+ " order by update_date desc limit ?,?";
				List<HosPubInfoPO> lst = jt.query(sql, new HosPubInfoPO(), HosPubInfoPO.STATUS_UNAPPROVED, infoCatCode, offset, length);
				return lst;
			}else if (infoType > 0){
				String sql = "select hpi.*, ic.info_type, ic.info_cat_name from hos_pub_info hpi, info_cat ic where hpi.info_cat_code = ic.info_cat_code and hpi.status <= ? and ic.info_type = ?"
						+ " order by update_date desc limit ?,?";
				List<HosPubInfoPO> lst = jt.query(sql, new HosPubInfoPO(), HosPubInfoPO.STATUS_UNAPPROVED, infoType, offset, length);
				return lst;
			}else{
				String sql = "select hpi.*, ic.info_type, ic.info_cat_name from hos_pub_info hpi, info_cat ic where hpi.info_cat_code = ic.info_cat_code and hpi.status <= ? "
						+ " order by update_date desc limit ?,?";
				List<HosPubInfoPO> lst = jt.query(sql, new HosPubInfoPO(), HosPubInfoPO.STATUS_UNAPPROVED, offset, length);
				return lst;
			}
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}
	}
	
	@Override
	public List<HosPubInfoPO> findUnApprovedHosInfoByParas(Integer infoType,
			String infoCatCode, java.sql.Date startExpDate,
			java.sql.Date endExpDate,String editor, int offset, int length) {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			List<Object> lstArgs = new ArrayList<Object>();
			String sql = "select hpi.*, ic.info_type, ic.info_cat_name from hos_pub_info hpi, info_cat ic where hpi.info_cat_code = ic.info_cat_code and hpi.status <= ? ";
			lstArgs.add(HosPubInfoPO.STATUS_UNAPPROVED);
			sql = addParasToSQLS(sql, infoType, infoCatCode, startExpDate, endExpDate,editor,null, lstArgs);
			sql += " order by hpi.update_date desc limit ?,?";
			lstArgs.add(offset);
			lstArgs.add(length);
			List<HosPubInfoPO> lst = jt.query(sql, lstArgs.toArray(), new HosPubInfoPO());
			return lst;
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}
	}
	
	
	
	
	@Override
	public List<HosPubInfoPO> findApprovedHosInfoByParas(Integer infoType,
			String infoCatCode, Integer isMain, java.sql.Date startExpDate,
			java.sql.Date endExpDate,String editor,String approver, int offset, int length) {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			List<Object> lstArgs = new ArrayList<Object>();
			String sql = "select hpi.*, ic.info_type, ic.info_cat_name from hos_pub_info hpi, info_cat ic where hpi.info_cat_code = ic.info_cat_code and (hpi.status = ? or hpi.status=?) ";
			lstArgs.add(HosPubInfoPO.STATUS_APPROVED);
			lstArgs.add(HosPubInfoPO.STATUS_CANCEL);
			sql = addParasToSQLS(sql, infoType, infoCatCode, startExpDate, endExpDate,editor,approver, lstArgs);
			if (isMain == 1 || isMain == 0){
				sql += " and hpi.is_main = ?";
				lstArgs.add(isMain);
			}
			sql += " order by hpi.update_date desc limit ?,?";
			lstArgs.add(offset);
			lstArgs.add(length);
			//System.out.println("sql语句"+sql);
			List<HosPubInfoPO> lst = jt.query(sql, lstArgs.toArray(), new HosPubInfoPO());
			return lst;
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}
	}


//	private String addParasToSQL(
//				String sql,
//				Integer infoType,
//				String infoCatCode, 
//				java.sql.Date startExpDate,
//				java.sql.Date endExpDate,
//				List<Object> lstArgs){
//		
//		if (infoType != null && infoType > 0){
//			sql += " and ic.info_type = ?";
//			lstArgs.add(infoType);
//		}
//		if (StringUtils.isNotBlank(infoCatCode)){
//			sql += " and ic.info_cat_code = ?";
//			lstArgs.add(infoCatCode);
//		}
//		if (startExpDate != null){
//			sql += " and hpi.exp_pub_date >= ?";
//			lstArgs.add(startExpDate);
//		}
//		if (endExpDate != null){
//			sql += " and hpi.exp_pub_date <= ?";
//			lstArgs.add(endExpDate);
//		}
//		return sql;
//
//	}
	
	private String addParasToSQLS(
				String sql,
				Integer infoType,
				String infoCatCode, 
				java.sql.Date startExpDate,
				java.sql.Date endExpDate,
				String editor,String approver,
				List<Object> lstArgs){
		
		if (infoType != null && infoType > 0){
			sql += " and ic.info_type = ?";
			lstArgs.add(infoType);
		}
		if (StringUtils.isNotBlank(infoCatCode)){
			sql += " and ic.info_cat_code = ?";
			lstArgs.add(infoCatCode);
		}
		if (startExpDate != null){
			sql += " and hpi.exp_pub_date >= ?";
			lstArgs.add(startExpDate);
		}
		if (endExpDate != null){
			sql += " and hpi.exp_pub_date <= ?";
			lstArgs.add(endExpDate);
		}
		if (StringUtils.isNotBlank(editor)){
			sql += " and hpi.editor like ?";
			lstArgs.add("%"+editor+"%");
		}
		if (StringUtils.isNotBlank(approver)){
			sql += " and hpi.approver like ?";
			lstArgs.add("%"+approver+"%");
		}
		return sql;
	
	}
	
	
	@Override
	public int countUnApprovedHosInfoByParas(Integer infoType,
			String infoCatCode, java.sql.Date startExpDate,
			java.sql.Date endExpDate,String editor) {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			List<Object> lstArgs = new ArrayList<Object>();
			String sql = "select count(*) from hos_pub_info hpi, info_cat ic where hpi.info_cat_code = ic.info_cat_code and hpi.status <= ? ";
			lstArgs.add(HosPubInfoPO.STATUS_UNAPPROVED);
			sql = addParasToSQLS(sql, infoType, infoCatCode, startExpDate, endExpDate,editor,null, lstArgs);
			int count = jt.queryForObject(sql, lstArgs.toArray(), Integer.class);
			return count;
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}
	}
	
//	/
	@Override
	public int countApprovedHosInfoByParas(Integer infoType,
			String infoCatCode, Integer isMain, java.sql.Date startExpDate,
			java.sql.Date endExpDate,String editor,String approver) {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			List<Object> lstArgs = new ArrayList<Object>();
			String sql = "select count(*) from hos_pub_info hpi, info_cat ic where hpi.info_cat_code = ic.info_cat_code and (hpi.status= ? or hpi.status=?) ";
			lstArgs.add(HosPubInfoPO.STATUS_APPROVED);
			lstArgs.add(HosPubInfoPO.STATUS_CANCEL);
			sql = addParasToSQLS(sql, infoType, infoCatCode, startExpDate, endExpDate,editor,approver, lstArgs);
			if (isMain == 1 || isMain == 0){
				sql += " and hpi.is_main = ?";
				lstArgs.add(isMain);
			}
			int count = jt.queryForObject(sql, lstArgs.toArray(), Integer.class);
			return count;
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}
	}


	@Override
	public int countUnApprovedHosInfo(int infoType, String infoCatCode) {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			if (infoType <=0 && StringUtils.isBlank(infoCatCode)){
				String sql = "select count(*) from hos_pub_info hp where status <= ?";
				return jt.queryForObject(sql, Integer.class, HosPubInfoPO.STATUS_UNAPPROVED);
			}else if (StringUtils.isBlank(infoCatCode)){
				String sql = "select count(*) from hos_pub_info hp, info_cat i where hp.info_cat_code = i.info_cat_code and i.info_type=? and hp.status <= ?";
				return jt.queryForObject(sql, Integer.class, infoType, HosPubInfoPO.STATUS_UNAPPROVED);
			}else{
				String sql = "select count(*) from hos_pub_info where status <= ? and info_cat_code = ?";
				return jt.queryForObject(sql, Integer.class, HosPubInfoPO.STATUS_UNAPPROVED, infoCatCode);
			}
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}
	}
	

	@Override
	public int countHosInfoByCatCode(String infoCatCode) {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "select count(*) from hos_pub_info where info_cat_code = ?";
			return jt.queryForObject(sql, Integer.class, infoCatCode);
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}
	}


	@Override
	public List<StatByTypePO> statUnApprovedInfoByType() {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			
			String sql = "select i.info_type, h.info_cat_code, count(*) stat_count "
				     + " from hos_pub_info h, info_cat i "
				     + " where h.info_cat_code = i.info_cat_code "
				     + " and h.status <= ? group by i.info_type ";
			
			List<StatByTypePO> lst = jt.query(sql, new StatByTypePO()
				, HosPubInfoPO.STATUS_UNAPPROVED);
			
			return lst;
		}catch(DataAccessException ex){
			log.error(null,ex);
			throw ex;
		}
	}


	private List<StatByCatPO> statInfoByCat(int status){
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "select ic.info_cat_code, ic.info_cat_name, ic.info_type"
					+ ", hpi.stat_count from info_cat ic left join "
					+ "(select info_cat_code, status, count(*) stat_count "
					+ "from hos_pub_info where status <= ? group by info_cat_code) hpi "
					+ "on ic.info_cat_code=hpi.info_cat_code";
			List<StatByCatPO> lst = jt.query(sql, new StatByCatPO(), status);
			return lst;
		}catch(DataAccessException ex){
			log.error(null, ex);
			throw ex;
		}
	}

	@Override
	public List<StatByCatPO> statUnApprovedInfoByCat() {
		return statInfoByCat(HosPubInfoPO.STATUS_UNAPPROVED);
	}

}
