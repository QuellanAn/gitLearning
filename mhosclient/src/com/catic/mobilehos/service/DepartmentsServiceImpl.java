package com.catic.mobilehos.service;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.dao.DataAccessException;


import com.catic.mobilehos.dao.DepartmentDAO;
import com.catic.mobilehos.pay.util.ExcleUtils;
import com.catic.mobilehos.po.DepartmentPO;
import com.catic.mobilehos.service.vo.DepartmentVO;
import com.catic.mobilehos.service.vo.Page;
import com.catic.mobilehos.service.vo.VOPOConverter;

public class DepartmentsServiceImpl implements DepartmentsService {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	private DepartmentDAO departmentDAO;
	
	public DepartmentDAO getDepartmentDAO() {
		return departmentDAO;
	}

	public void setDepartmentDAO(DepartmentDAO departmentDAO) {
		this.departmentDAO = departmentDAO;
	}
/**
 * YJJ
 * 分页查询科室
 */
	@Override
	public Page<DepartmentVO> queryDepartmentsListByParas(String name,
			String location, Integer status, String department_id, int page, int pageSize) {
		int totalRecord = this.departmentDAO.countDepartments(name, location, status, department_id);
		Page<DepartmentVO> p = new Page<DepartmentVO>(totalRecord, pageSize, page);
		List<DepartmentPO> dlist = departmentDAO.findDepartmentsByParas(name, location, status, department_id, p.getOffset(), p.getPageSize());
		VOPOConverter<DepartmentVO, DepartmentPO> cvt 
			= new VOPOConverter<DepartmentVO, DepartmentPO>(DepartmentVO.class, DepartmentPO.class);
		List<DepartmentVO> vlist = cvt.fromPOList(dlist);
		p.setCurPageData(vlist);
		return p;
	}

	@Override
	public ServiceResult saveDepartmentAddr(String did, String addr) {
		try{
			departmentDAO.updateDepartmentAddr(did, addr);
			return ServiceResult.getSucInstance();
		}catch(DataAccessException dacEx){
			ServiceResult sr = ServiceResult.getFailedInstance(""
					, "系统异常，更新科室失败！");
			return sr;
		}catch(Exception ex){
			log.error(null, ex);
			ServiceResult sr = ServiceResult.getFailedInstance(""
					, "参数错误，更新科室失败！");
			return sr;
		}
	}

	/* (non-javadoc) 
	 * <p>Title: findAllDeptPO</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.catic.mobilehos.service.DepartmentsService#findAllDeptPO() 
	*/ 
	@Override
	public List<DepartmentVO> findAllDeptVO() {
		
		List<DepartmentPO> list =departmentDAO.findAllDeptPO();
		
		if(null == list || list.isEmpty()){
			return new ArrayList<DepartmentVO>();
		}
		VOPOConverter<DepartmentVO, DepartmentPO> converter = new VOPOConverter<DepartmentVO, DepartmentPO>(
				DepartmentVO.class, DepartmentPO.class);
		List<DepartmentVO> deptVOs = converter.fromPOList(list);
		return deptVOs;
	}

	@Override
	public DepartmentVO findDepVOById(String id) {
		DepartmentPO departmentPO=departmentDAO.findDepVOById(id);
		//查询不到结果
		if(departmentPO==null){
			return null;
		}else{
			VOPOConverter<DepartmentVO, DepartmentPO> converter = new VOPOConverter<DepartmentVO, DepartmentPO>(
					DepartmentVO.class, DepartmentPO.class);
			DepartmentVO departmentVO=converter.poToVO(departmentPO);
			return departmentVO;
		}
		
	}

	@Override
	public DepartmentVO findDepVOByCode(String department_id) {
		DepartmentPO departmentPO=departmentDAO.findDepVOByCode(department_id);
		//查询不到结果
		if(departmentPO==null){
			return null;
		}else{
			VOPOConverter<DepartmentVO, DepartmentPO> converter = new VOPOConverter<DepartmentVO, DepartmentPO>(
					DepartmentVO.class, DepartmentPO.class);
			DepartmentVO departmentVO=converter.poToVO(departmentPO);
			return departmentVO;
		}
	}
/**
 * 
 * 查找一级科室
 */
	@Override
	public List<DepartmentVO> findOneGradeDeptVO() {
List<DepartmentPO> list =departmentDAO.findOneGradeDeptVO();
		
		if(null == list || list.isEmpty()){
			return new ArrayList<DepartmentVO>();
		}
		VOPOConverter<DepartmentVO, DepartmentPO> converter = new VOPOConverter<DepartmentVO, DepartmentPO>(
				DepartmentVO.class, DepartmentPO.class);
		List<DepartmentVO> deptVOs = converter.fromPOList(list);
		return deptVOs;
	}

@Override
public ServiceResult saveOrUpdaeDepartment(DepartmentVO d) {
	try{
		departmentDAO.saveOrUpdaeDepartment(d);
		return ServiceResult.getSucInstance();
	}catch(DataAccessException dacEx){
		ServiceResult sr = ServiceResult.getFailedInstance(""
				, "系统异常，更新科室失败！");
		return sr;
	}catch(Exception ex){
		log.error(null, ex);
		ServiceResult sr = ServiceResult.getFailedInstance(""
				, "参数错误，更新科室失败！");
		return sr;
	}
}

@Override
public boolean deleteDepartment(String id) {
	
	return departmentDAO.deleteDepartment(id);
}

@Override
public List<DepartmentVO> readXls(Workbook workBook) {
	try {
			DepartmentVO departVO=null;
			for(int numSheet=0;numSheet<=workBook.getNumberOfSheets();numSheet++){
				Sheet sheet=workBook.getSheetAt(numSheet);
				if(sheet==null){
					continue;
				}
				for(int rowNum=1;rowNum<=sheet.getLastRowNum();rowNum++){
					Row row=sheet.getRow(rowNum);
					if(row!=null){
						departVO=new DepartmentVO();
						Cell dName=row.getCell(0);
						Cell dId=row.getCell(1);
						Cell pId=row.getCell(2);
						Cell dStatus=row.getCell(3);
						Cell dPhone=row.getCell(4);
						Cell dAddr=row.getCell(5);
						Cell dNo=row.getCell(6);
						Cell dInfo=row.getCell(7);
						Cell appStatus=row.getCell(8);
						departVO.setDepartmentName(ExcleUtils.getValue(dName));
						departVO.setDepartmentId(ExcleUtils.getValue(dId));
						departVO.setDepartment_status(Integer.parseInt(ExcleUtils.getValue(dStatus)));
						departVO.setDisplay_no(ExcleUtils.getValue(dNo));
						departVO.setParentId(ExcleUtils.getValue(pId));
						departVO.setPhone(ExcleUtils.getValue(dPhone));
						departVO.setDepartmentAddr(ExcleUtils.getValue(dAddr));
						departVO.setIntroduction(ExcleUtils.getValue(dInfo));
						departVO.setApp_reg_status(Integer.parseInt(ExcleUtils.getValue(appStatus)));
						saveOrUpdaeDepartment(departVO);
					}
				}
			}
	} catch (Exception e) {
		log.error(e);
	}
	return null;
}

}
