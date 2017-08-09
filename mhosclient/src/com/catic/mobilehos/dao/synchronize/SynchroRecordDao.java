/**   
 * @Title: SynchroRecordDao.java 
 * @Package com.catic.mobilehos.dao.synchronize 
 * @Description: TODO
 * @author htWang   
 * @date 2014-6-9 下午3:58:52 
 * 
 */ 
package com.catic.mobilehos.dao.synchronize;

import com.catic.mobilehos.po.SynChroRecordPO;

/**
 * @author WANG
 *
 */
public interface SynchroRecordDao {
	void recordSynchroLog(final SynChroRecordPO synChroRecordPO);
}
