package com.catic.mobilehos.pay.biz.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;

import com.catic.mobilehos.pay.biz.BaseBiz;
import com.catic.mobilehos.pay.biz.IHisCheckBiz;
import com.catic.mobilehos.pay.entity.HisCheck;
import com.catic.mobilehos.pay.util.ExcleUtils;
import com.catic.mobilehos.utils.ExportExcel;

@Service("hisCheckBiz")
public class HisCheckBizImpl extends BaseBiz implements IHisCheckBiz{

	@Override
	public int save(HisCheck ci) throws Exception {
		return hisCheckDao.save(ci);
	}

	@Override
	public Map<String,Object> readXls(Workbook workBook,String uploadFileName,HttpServletRequest request,HttpServletResponse response) {
		Map<String,Object> map=new HashMap<String,Object>();
		Integer num=0;
		Integer lineNum=0;
		Integer failNum=0;
		try {
			List<HisCheck> listHc=new ArrayList<HisCheck>();
			HisCheck hc=null;
			for(int numSheet=0;numSheet<workBook.getNumberOfSheets();numSheet++){
				Sheet sheet=workBook.getSheetAt(numSheet);
				if(sheet==null){
					continue;
				}
				for(int rowNum=8;rowNum<=sheet.getLastRowNum();rowNum++){
					Row row=sheet.getRow(rowNum);
					if(row!=null){
						lineNum++;
						hc=new HisCheck();
						Cell orderType=row.getCell(0);
						Cell orderNum=row.getCell(1);
						Cell othersNum=row.getCell(2);
						Cell hisNum=row.getCell(3);
						Cell orderMoney=row.getCell(4);
						Cell orderStatus=row.getCell(5);
						Cell orderTime=row.getCell(6);
						if(Integer.parseInt(ExcleUtils.getValue(orderType))==0){
							Cell body=row.getCell(7);
							hc.setBody(ExcleUtils.getValue(body));
						}else{
							Cell refundType=row.getCell(8);
							hc.setRefundType(Integer.parseInt(ExcleUtils.getValue(refundType)));
						}
						hc.setOrderType(Integer.parseInt(ExcleUtils.getValue(orderType)));
						hc.setOrderNum(ExcleUtils.getValue(orderNum));
						hc.setOthersNum(ExcleUtils.getValue(othersNum));
						hc.setHisNum(ExcleUtils.getValue(hisNum));
						hc.setOrderMoney(Integer.parseInt(ExcleUtils.getValue(orderMoney)));
						hc.setOrderStatus(Integer.parseInt(ExcleUtils.getValue(orderStatus)));
						hc.setOrderTime(ExcleUtils.getValue(orderTime));
						int count = save(hc);
						if(count>0){
							num++;
							continue;
						}else{
							failNum++;
							listHc.add(hc);
							continue;
						}
						
					}
				}
			}
			if(listHc.size()>0){
				String[] headers={"订单类型", "订单单号", "第三方流水号", "HIS流水号", "订单金额", "订单状态",
						"订单时间", "缴费项目(交易单填写)", "退款类型(退款单填写)"};
				List<List<Object>> listVal=changeHisCheckList(listHc);
				ExportExcel.excelOut(headers, listVal, "", request, response);
				map.put("noFileName", "(未受理账单)"+uploadFileName);
				map.put("noFilePath", ServletActionContext.getServletContext().getRealPath("notAcceptedUpload"));
				ExcleUtils.excelOut(headers, listVal, map.get("noFileName").toString(), map.get("noFilePath").toString());
			}
			map.put("submitNum", lineNum);
			map.put("successNum",num);
			map.put("failNum",failNum);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return map;
	}
	
	@SuppressWarnings("unused")
	private List<List<Object>> changeHisCheckList(List<HisCheck> listHc){
		List<List<Object>> listVal=new ArrayList<List<Object>>();
		for (HisCheck hisCheck : listHc) {
			List<Object> listOb=new ArrayList<Object>();
			listOb.add(hisCheck.getOrderType());
			listOb.add(hisCheck.getOrderNum());
			listOb.add(hisCheck.getOthersNum());
			listOb.add(hisCheck.getHisNum());
			listOb.add(hisCheck.getOrderMoney());
			listOb.add(hisCheck.getOrderStatus());
			listOb.add(hisCheck.getBody());
			listOb.add(hisCheck.getRefundType());
			listOb.add(hisCheck.getOrderTime());
			listVal.add(listOb);
		}
		return listVal;
	}

}
