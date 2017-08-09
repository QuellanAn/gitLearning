/**   
 * @Title: IAppRegExceptionServiceImpl.java 
 * @Package com.catic.mobilehos.service.synchronize 
 * @Description: TODO
 * @author htWang   
 * @date 2014-5-20 上午10:09:15 
 * 
 */
package com.catic.mobilehos.service.synchronize;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.catic.mobilehos.dao.synchronize.IAppRegExceptionDao;
import com.catic.mobilehos.po.AppRegExceptionPO;
import com.catic.mobilehos.utils.GetServiceImplPort;

/**
 * @author WANG
 * 
 */
public class IAppRegExceptionServiceImpl implements IAppRegExceptionService {

	private Log log = LogFactory.getLog(this.getClass());

	private IAppRegExceptionDao iAppRegExceptionDao;

	@Override
	public void saveAppRegExcetion() {
		try {
			long last = iAppRegExceptionDao.findLastException();
			String exceptArray = (new GetServiceImplPort()).getPort().getHisAppRegException(String.valueOf(last));
			JSONArray jsonArray = JSONArray.fromObject(exceptArray);
			List<AppRegExceptionPO> exceptList = new ArrayList<AppRegExceptionPO>();
			for (int i = 0; i < jsonArray.size(); i++) {
				AppRegExceptionPO except = new AppRegExceptionPO();
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				except.setExceptId(jsonObject.getInt("excep_id"));
				except.setOrderId(jsonObject.getString("reg_order_id"));
				
				if(jsonObject.has("exception_time")) {
					except.setExceptionTime(Timestamp.valueOf(jsonObject.getString("exception_time")));
				}
				if(jsonObject.has("exception_type")) {
					except.setExceptionType(jsonObject.getString("exception_type"));
				}
				
				exceptList.add(except);
			}
			iAppRegExceptionDao.addOrderExceptFromHIS(exceptList);
		} catch (Exception e) {
			log.error("业务层执行同步预约异常信息异常：", e);
		}
	}

	public void setiAppRegExceptionDao(IAppRegExceptionDao iAppRegExceptionDao) {
		this.iAppRegExceptionDao = iAppRegExceptionDao;
	}

}
