package com.catic.mobilehos.service.synchronize;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.catic.mobilehos.dao.synchronize.IAppRegPeriodDao;
import com.catic.mobilehos.po.AppRegPeriodPO;
import com.catic.mobilehos.utils.GetServiceImplPort;

public class AppRegPeriodServiceImpl implements AppRegPeriodService {

	private Log log = LogFactory.getLog(this.getClass());

	private IAppRegPeriodDao iAppRegPeriodDao;

	@Override
	public void saveAppRegPeriod() {
		try {
			List<AppRegPeriodPO> periodsList = new ArrayList<AppRegPeriodPO>();
			String appRegPeriods = (new GetServiceImplPort()).getPort().getAppRegPeriod();
			JSONArray jsonArray = JSONArray.fromObject(appRegPeriods);
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				AppRegPeriodPO period = new AppRegPeriodPO();
				period.setPeriodId(jsonObject.getInt("period_id"));
				period.setPeriodType(jsonObject.getString("period_type"));
				period.setStartTime(Time.valueOf(jsonObject.getString("start_time")));
				period.setEndTime(Time.valueOf(jsonObject.getString("end_time")));
				periodsList.add(period);
			}
			iAppRegPeriodDao.delAllAppRegPeriod();
			iAppRegPeriodDao.addAppRegPeriodFromHis(periodsList);
		} catch (Exception e) {
			log.error(null, e);
		}
	}

	public void setiAppRegPeriodDao(IAppRegPeriodDao iAppRegPeriodDao) {
		this.iAppRegPeriodDao = iAppRegPeriodDao;
	}
}
