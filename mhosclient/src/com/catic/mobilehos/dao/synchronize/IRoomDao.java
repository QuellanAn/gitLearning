/**   
 * @Title: RoomsDaoImpl.java 
 * @Package com.catic.mobilehos.dao 
 * @Description: TODO
 * @author htWang   
 * @date 2014-5-19 下午4:48:58 
 * 
 */ 
package com.catic.mobilehos.dao.synchronize;

import java.util.List;

import com.catic.mobilehos.po.RoomPO;

/**
 * @author WANG
 *
 */
public interface IRoomDao {
	void delAllRooms();
	void addRoomsFromHIS(List<RoomPO> roomsList);
}
