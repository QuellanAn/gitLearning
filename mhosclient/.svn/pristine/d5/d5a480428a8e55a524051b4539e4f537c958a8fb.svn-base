/**   
 * @Title: AppRegOrderTypeServiceImpl.java 
 * @Package com.catic.mobilehos.service 
 * @Description: TODO
 * @author htWang   
 * @date 2014-5-19 下午3:42:36 
 * 
 */
package com.catic.mobilehos.service.synchronize;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.catic.mobilehos.dao.synchronize.IAppRegOrderTypeDao;
import com.catic.mobilehos.po.RegOrderTypePO;
import com.catic.mobilehos.utils.GetServiceImplPort;

/**
 * @author WANG
 * 
 */
public class AppRegOrderTypeServiceImpl implements AppRegOrderTypeService {

	private Log log = LogFactory.getLog(this.getClass());

	private IAppRegOrderTypeDao appRegOrderTypeDao;

	/*
	 * (non-javadoc) <p>Title: saveOrderTypeFromHis</p> <p>Description: </p>
	 * 
	 * @see
	 * com.catic.mobilehos.service.AppRegOrderTypeService#saveOrderTypeFromHis()
	 */
	@Override
	public void saveOrderTypeFromHis() {
		try {
			List<RegOrderTypePO> typeList = new ArrayList<RegOrderTypePO>();
			String orderTypes = (new GetServiceImplPort()).getPort().getAppRegOrderType();
			JSONArray jsonArray = JSONArray.fromObject(orderTypes);
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				RegOrderTypePO orderType = new RegOrderTypePO();
				orderType.setTypeCode(jsonObject.getString("type_code"));
				orderType.setTypeName(jsonObject.getString("type_name"));
				typeList.add(orderType);
			}
			appRegOrderTypeDao.delAllOrderType();
			appRegOrderTypeDao.addOrderTypeFromHis(typeList);
		} catch (Exception e) {
			log.error(null, e);
		}
	}

	/**
	 * @param appRegOrderTypeDao
	 *            the appRegOrderTypeDao to set
	 */
	public void setAppRegOrderTypeDao(IAppRegOrderTypeDao appRegOrderTypeDao) {
		this.appRegOrderTypeDao = appRegOrderTypeDao;
	}

}
