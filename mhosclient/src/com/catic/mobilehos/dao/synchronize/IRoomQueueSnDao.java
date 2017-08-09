/**   
 * @Title: IRoomQueueSnDao.java 
 * @Package com.catic.mobilehos.dao.synchronize 
 * @Description: TODO
 * @author htWang   
 * @date 2014-5-23 下午2:16:14 
 * 
 */ 
package com.catic.mobilehos.dao.synchronize;

import java.util.List;

import com.catic.mobilehos.po.RoomQueueSnPO;

/**
 * @author WANG
 *
 */
public interface IRoomQueueSnDao {
	void deleteRoomQueueSn();
	void addRoomQueueSnFromHIS(List<RoomQueueSnPO> roomQueueSnList);
}
