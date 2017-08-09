/**   
 * @Title: IRoomQueueSnServiceImpl.java 
 * @Package com.catic.mobilehos.service.synchronize 
 * @Description: TODO
 * @author htWang   
 * @date 2014-5-23 下午3:15:18 
 * 
 */ 
package com.catic.mobilehos.service.synchronize;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.catic.mobilehos.dao.synchronize.IRoomQueueSnDao;
import com.catic.mobilehos.po.RoomQueueSnPO;
import com.catic.mobilehos.utils.GetServiceImplPort;

/**
 * @author WANG
 *
 */
public class IRoomQueueSnServiceImpl implements IRoomQueueSnService{
	
	private Log log = LogFactory.getLog(this.getClass());
	
	private IRoomQueueSnDao roomQueueSnDao;
	
	@Override
	public void saveRoomQueueSn() {
		try {
			List<RoomQueueSnPO> roomQueueSnList = new ArrayList<RoomQueueSnPO>();
			String hisRooQueues = new GetServiceImplPort().getPort().getHisRoomQueueSn();
			JSONArray roomQueueArray = JSONArray.fromObject(hisRooQueues);
			for (int i = 0; i < roomQueueArray.size(); i++) {
				RoomQueueSnPO roomQueuePO = new RoomQueueSnPO();
				roomQueuePO.setRoomId(roomQueueArray.getJSONObject(i).getString("room_id"));
				roomQueuePO.setDepartmentId(roomQueueArray.getJSONObject(i).getString("department_id"));
				roomQueuePO.setDoctorId(roomQueueArray.getJSONObject(i).getString("doctor_id"));
				roomQueuePO.setPatientOrderId(roomQueueArray.getJSONObject(i).getString("patient_order_id"));
				roomQueuePO.setQueueSn(roomQueueArray.getJSONObject(i).getInt("queue_sn"));
				roomQueueSnList.add(roomQueuePO);
			}
			roomQueueSnDao.deleteRoomQueueSn();
			roomQueueSnDao.addRoomQueueSnFromHIS(roomQueueSnList);
		} catch (Exception e) {
			log.error(null,e);
		}
	}

	/**
	 * @param roomQueueSnDao the roomQueueSnDao to set
	 */
	public void setRoomQueueSnDao(IRoomQueueSnDao roomQueueSnDao) {
		this.roomQueueSnDao = roomQueueSnDao;
	}

}
