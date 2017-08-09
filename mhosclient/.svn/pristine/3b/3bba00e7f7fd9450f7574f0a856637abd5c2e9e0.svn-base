/**   
 * @Title: RoomServiceImpl.java 
 * @Package com.catic.mobilehos.service 
 * @Description: TODO
 * @author htWang   
 * @date 2014-5-19 下午4:58:29 
 * 
 */
package com.catic.mobilehos.service.synchronize;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.json.JSONArray;

import com.catic.mobilehos.dao.synchronize.IRoomDao;
import com.catic.mobilehos.po.RoomPO;
import com.catic.mobilehos.utils.GetServiceImplPort;

/**
 * @author WANG
 * 
 */
public class RoomServiceImpl implements RoomService {

	private Log log = LogFactory.getLog(this.getClass());

	private IRoomDao roomDao;

	/*
	 * (non-javadoc) <p>Title: saveRoomsFromHIS</p> <p>Description: </p>
	 * 
	 * @see com.catic.mobilehos.service.RoomService#saveRoomsFromHIS()
	 */
	@Override
	public void saveRoomsFromHIS() {
		try {
			List<RoomPO> roomsList = new ArrayList<RoomPO>();
			String hisRooms = (new GetServiceImplPort()).getPort().getRooms();
			JSONArray roomArray = JSONArray.fromObject(hisRooms);
			for (int i = 0; i < roomArray.size(); i++) {
				RoomPO roomPO = new RoomPO();
				roomPO.setRoomId(roomArray.getJSONObject(i).getString("room_id"));
				roomPO.setRoomName(roomArray.getJSONObject(i).getString("room_name"));
				roomPO.setDepartmentId(roomArray.getJSONObject(i).getString("department_id"));
				roomPO.setRoomAddr(roomArray.getJSONObject(i).getString("room_addr"));
				roomsList.add(roomPO);
			}
			roomDao.delAllRooms();
			roomDao.addRoomsFromHIS(roomsList);
		} catch (Exception e) {
			log.error(e);
		}
	}

	/**
	 * @param roomDao
	 *            the roomDao to set
	 */
	public void setRoomDao(IRoomDao roomDao) {
		this.roomDao = roomDao;
	}

}
