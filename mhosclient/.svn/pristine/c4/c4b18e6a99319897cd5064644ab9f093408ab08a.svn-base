package com.catic.mobilehos.dao;

import java.util.List;

import com.catic.mobilehos.po.DoctorPO;
import com.catic.mobilehos.service.vo.DoctorVO;

public interface DoctorDAO {
	
	/**
	 * @Title: findDoctorsByParas 
	 * @Description: TODO 分页显示所有医生信息
	 * @param @param name 医生名称
	 * @param @param offset
	 * @param @param length
	 * @return List<DoctorPO>    
	 * @throws
	 */
	List<DoctorPO> findDoctorsByParas(String name,int offset,int length);
	
	/**
	 * 
	 * @Title: getDoctorCount 
	 * @Description: TODO 获取总记录数
	 * @param @param name 医生姓名
	 * @return int    
	 * @throws
	 */
	int getDoctorCount(String name);
	
	/**
	 * 
	 * @Title: getDoctorPO 
	 * @Description: TODO 根据医生ID获取单个医生对象
	 * @param docId 医生ID
	 * @return DoctorPO    
	 * @throws
	 */
	DoctorPO getDoctorPO(String docId);
	
	/**
	 * @Title: deleteDoctor 
	 * @Description: TODO 删除某个ID的医生
	 * @param @param doctorId 医生ID
	 * @return boolean    
	 * @throws
	 */
	boolean deleteDoctor(String doctorId);
	
	/**
	 * 
	 * @Title: updateDoctor 
	 * @Description: TODO 修改某个医生的信息
	 * @param  doctor
	 * @param  doctorId 要修改的医生的ID
	 * @throws
	 */
	void updateDoctor(DoctorPO doctor,String doctorId);
	
	/**
	 * 
	 * @Title: saveDoctor 
	 * @Description: TODO 添加医护人员
	 * @param doctorPO
	 * @return void   
	 * @throws
	 */
	void saveDoctor(DoctorPO doctorPO);
	
	/**
	 * 
	 * @Title: saveDoctorDepartment 
	 * @Description: TODO 在医生和科室的中间表插入一条数据
	 * @param @param doctorPO 
	 * @return void    
	 * @throws
	 */
	void saveDoctorDepartment(DoctorPO doctorPO);
	
	/**
	 * 
	 * @Title: updateDoctorDepartment 
	 * @Description: TODO 修改医生所在科室时 医生和科室的中间表也应一起修改
	 * @param @param doctorPO    
	 * @return void    
	 * @throws
	 */
	void updateDoctorDepartment(DoctorPO doctorPO);
	/**
	 * 
	 * 描述：获取医生记录数
	 * @author yjj
	 * @date 2016-11-23
	 */
	int getDoctorCount(DoctorVO doctor);
	/**
	 * 
	 * 描述：获取医生列表
	 * @author yjj
	 * @date 2016-11-23
	 */
	List<DoctorPO> findDoctorsByParas(DoctorVO doctor,int offset,int length);
	
	List<DoctorPO> findByExcle();
	/**
	 * 
	 * 描述：修改或者保存医生数据，type=0:添加,type=1修改
	 * @author yjj
	 * @date 2016-11-23
	 */
	void saveOrUpdateDoctor(DoctorPO doctorPO,int type);
	
	/**
	 * 根据医生的code，获取医生信息
	 * @param code
	 * @return
	 */
	DoctorPO getDoctorDetailByCode(String code);
	
	DoctorPO findByUpDown(String sortNum,String operate);

	void updateIdByOperate(String newId, String oldId);
	
	/**
	 * 根据科室id查询所有医生信息
	 * @return 科室id对应所有医生信息
	 */
	List<DoctorPO> getDoctorByDepartmentId(DoctorPO po);
}
