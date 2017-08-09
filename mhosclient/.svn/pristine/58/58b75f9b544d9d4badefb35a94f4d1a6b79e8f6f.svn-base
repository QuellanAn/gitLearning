package com.catic.mobilehos.service.synchronize;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.catic.mobilehos.dao.synchronize.TimetableDao;
import com.catic.mobilehos.po.TimetablePO;
import com.catic.mobilehos.utils.GetServiceImplPort;

public class ITimeTableServiceImpl implements ITimeTableService {

	private Log log = LogFactory.getLog(this.getClass());

	private TimetableDao iTimeTableDao;

	@Override
	public void saveTimetableFromHIS() {
		try {
			List<TimetablePO> timeList = new ArrayList<TimetablePO>();
			long lastTimeTable = iTimeTableDao.findLastTimetable();
			String hisRooms = (new GetServiceImplPort()).getPort().getTimetable(String.valueOf(lastTimeTable));
			JSONArray timeArray = JSONArray.fromObject(hisRooms);
			for (int i = 0; i < timeArray.size(); i++) {
				TimetablePO timePO = new TimetablePO();
				JSONObject jo = timeArray.getJSONObject(i);

				timePO.setTimeId(jo.getInt("time_id"));
				timePO.setServiceDate(Date.valueOf(jo.getString("service_date")));
				timePO.setDoctorId(jo.getString("doctor_id"));
				timePO.setDepartmentId(jo.getString("department_id"));

				if (jo.has("order_type")) {
					timePO.setOrderType(jo.getString("order_type"));
				}
				if (jo.has("remainder")) {
					timePO.setRemainder(jo.getInt("remainder"));
				}
				if (jo.has("charge")) {
					timePO.setCharge(jo.getDouble("charge"));
				}
				if (jo.has("room_id")) {
					timePO.setRoomId(jo.getString("room_id"));
				}
				if (jo.has("time_state")) {
					timePO.setTimeState(jo.getInt("time_state"));
				}
				if (jo.has("periodId")) {
					timePO.setPeriodId(jo.getInt("periodId"));
				}
				if (jo.has("total_count")) {
					timePO.setTotalCount(jo.getInt("total_count"));
				}
				timeList.add(timePO);
			}
			// timeTableDao.delAllTimeTables();
			iTimeTableDao.addTimetable(timeList);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
	}

	public void setiTimeTableDao(TimetableDao iTimeTableDao) {
		this.iTimeTableDao = iTimeTableDao;
	}
}
