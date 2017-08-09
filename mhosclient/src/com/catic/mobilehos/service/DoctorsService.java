package com.catic.mobilehos.service;

import java.util.List;

import com.catic.mobilehos.po.DoctorPO;
import com.catic.mobilehos.service.vo.DoctorVO;
import com.catic.mobilehos.service.vo.Page;

/**
 * 医生相关的服务
 * 
 * @author xieweipeng
 *
 */
public interface DoctorsService {
	
	/**
	 * 通过名称关键字查找医生
	 * @param name 医生姓名的一个或多个字符
	 * @return
	 */
	Page<DoctorVO> getDoctorsListByName(String name,int page, int pageSize);
	
	/**
	 * 获取医生的详细信息
	 * @param docId 医生ID
	 * @return
	 */
	DoctorVO getDoctorDetail(String docId);
	
	/**
	 * 
	 * @Title: updateDoctorPO 修改单个医生信息
	 * @Description: TODO 
	 * @return void    
	 * @throws
	 */
	void updateDoctorPO(DoctorPO doctor,String docId);
	
	/**
	 * 
	 * @Title: deleteDoctor 
	 * @Description: TODO 根据医生的ID删除单个医生对象
	 * @param doctorId 医生ID   
	 * @return boolean 标识是否删除成功
	 * @throws
	 */
	boolean deleteDoctor(String doctorId);
	
	/**
	 * 
	 * 描述：根据条件查询医生列表
	 * @author yjj
	 * @date 2016-11-23
	 */
	Page<DoctorVO> getDoctorsList(DoctorVO doctor,int page, int pageSize);
	ServiceResult saveDoctor(DoctorPO doctorPO);
	/**
	 * 
	 * 描述：修改或者添加医生  type=0：添加，type=1更新
	 * @author yjj
	 * @date 2016-11-23
	 */
	ServiceResult saveOrUpdateDoctor(DoctorPO doctorPO,int type);
	
	/**
	 * 根据医生的code，获取医生信息
	 * @param code
	 * @return
	 */
	DoctorVO getDoctorDetailByCode(String code);
	
	DoctorPO findByUpDown(String sortNum,String operate);
	
	void updateIdByOperate(final String newId,final String oldId);
	
	List<DoctorPO> findByExcle();
	
	/**
	 * 根据科室id查询所有医生信息
	 * @return 科室id对应所有医生信息
	 */
	List<DoctorPO> getDoctorByDepartmentId(DoctorPO po);
}
