/**   
 * @Title: TimetableDao.java 
 * @Package com.catic.mobilehos.dao.synchronize 
 * @Description: TODO
 * @author htWang   
 * @date 2014-6-3 上午10:07:25 
 * 
 */ 
package com.catic.mobilehos.dao.synchronize;

import java.util.List;

import com.catic.mobilehos.po.TimetablePO;

/**
 * @author WANG
 *
 */
public interface TimetableDao {
	
	void addTimetable(List<TimetablePO> timetableList);
	
	long findLastTimetable();
	
	void deleTimetable();
}
