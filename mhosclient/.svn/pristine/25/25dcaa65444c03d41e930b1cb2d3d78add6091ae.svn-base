package com.catic.mobilehos.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;

import net.sf.json.JSONObject;

import com.catic.mobilehos.ErrorCode;
import com.catic.mobilehos.dao.CliVersionDAO;
import com.catic.mobilehos.po.CliVersionPO;
import com.catic.mobilehos.service.vo.CliVersionVO;
import com.catic.mobilehos.service.vo.Page;
import com.catic.mobilehos.service.vo.VOPOConverter;

/**
 * 客户端版本发布
 * 
 * @author linchunda
 * 
 */
public class CliVersionServiceImpl implements CliVersionService {
	private Log log = LogFactory.getLog(this.getClass());
	private CliVersionDAO cliVersionDAO;

	public CliVersionDAO getCliVersionDAO() {
		return cliVersionDAO;
	}

	public void setCliVersionDAO(CliVersionDAO cliVersionDAO) {
		this.cliVersionDAO = cliVersionDAO;
	}

	@Override
	public ServiceResult checkVersion(String ver_cat, String ver_type) {
		try {
			final CliVersionPO cliVersionPO = cliVersionDAO.checkVersion(
					ver_cat, ver_type);
			return new ServiceResult() {
				@Override
				public boolean isSuccess() {
					return false;
				}

				@Override
				public JSONObject getJSON() {
					JSONObject json = new JSONObject();
					json.put("versionNo", cliVersionPO.getVersionNo());
					json.put("apkName", cliVersionPO.getApkName());
					json.put("update_version", cliVersionPO.getUpdate_version());
					json.put("ID", cliVersionPO.getID());
					
					return json;
				}
			};
		} catch (DataAccessException dacEx) {
			ServiceResult sr = ServiceResult.getFailedInstance(
					ErrorCode.LS_BUS_EXEC_ERR, "系统异常，客户端版本检查失败！");
			return sr;
		} catch (Exception ex) {
			log.error("客户端版本检查出错:", ex);
			ServiceResult sr = ServiceResult.getFailedInstance(
					ErrorCode.LS_ILLEGAL_BUS_ARGUMENT, "参数错误，客户端版本检查失败！");
			return sr;
		}

	}

	@Override
	public CliVersionPO getCliVersionPOById(int ID) {
		return cliVersionDAO.getCliVersionPOById(ID);
	}

	@Override
	public List<String> getVersionNo(String ver_cat, String ver_type) {
		return cliVersionDAO.getVersionNo(ver_cat, ver_type);
	}

	@Override
	public Page<CliVersionVO> findCliVersionPOByByParas(String ver_cat,
			int curPage, int pageSize) {
		int totalRecord = cliVersionDAO.getCountCliVersionPO(ver_cat);
		Page<CliVersionVO> page = new Page<CliVersionVO>(totalRecord, pageSize,
				curPage);
		List<CliVersionPO> list = cliVersionDAO.findCliVersionPOByParas(
				ver_cat, page.getOffset(), page.getPageSize());
		VOPOConverter<CliVersionVO, CliVersionPO> cvt = new VOPOConverter<CliVersionVO, CliVersionPO>(
				CliVersionVO.class, CliVersionPO.class);
		List<CliVersionVO> vlist = cvt.fromPOList(list);
		page.setCurPageData(vlist);
		return page;
	}

	@Override
	public boolean saveCliVersionPO(CliVersionPO cliVersionPO) {
		return cliVersionDAO.saveCliVersionPO(cliVersionPO);
	}

	@Override
	public boolean updateCliVersionPO(CliVersionPO cliVersionPO, int ID) {
		return cliVersionDAO.updateCliVersionPO(cliVersionPO, ID);
	}

	@Override
	public boolean deleteCliVersionPO(int ID) {
		return cliVersionDAO.deleteCliVersionPO(ID);
	}

}
