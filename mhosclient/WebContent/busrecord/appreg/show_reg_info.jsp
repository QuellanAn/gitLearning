<%@page import="com.catic.mobilehos.po.UserPO"%>
<%@page import="com.catic.mobilehos.po.AppRegOrdersPO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="patients">
	<table class="list-table">
		<thead>
			<tr>
				<th align="center" width="10%">预约号</th>
				<th align="center" width="10%">预约时间</th>
				<th align="center" width="10%">就诊人</th>
				<th align="center" width="10%">预约科室</th>
				<th align="center" width="10%">预约医生</th>
				<th align="center" width="10%">就诊时间</th>
				<th align="center" width="9%">挂号费</th>
				<th align="center" width="9%">预约状态</th>
				<th align="center" width="9%">缴费状态</th>
				<th align="center" width="13%">预约途径</th>
			</tr>
		</thead>
		<tbody>
		
		 <s:iterator var="aroPO" value="aropo">
				<tr>
					<td align="center" valign="middle"><s:property value="#aroPO.appRegOrderNumber" /></td>
					<td align="center" valign="middle"><s:property value="#aroPO.createDate" /></td>
					<td align="center" valign="middle"><s:property value="#aroPO.patientName" /></td>
					<td align="center" valign="middle"><s:property value="#aroPO.departmentName" /></td>
					<td align="center" valign="middle"><s:property value="#aroPO.doctorName" /></td>
					<td align="center" valign="middle"><s:property value="#aroPO.doctorDate" /></td>
					<td align="center" valign="middle"><s:property value="#aroPO.payableFees" /></td>
					<td align="center" valign="middle"><s:if test="#aroPO.status=='011'">已预约</s:if><s:else>已取消</s:else></td>
					<td align="center" valign="middle"><s:if test="#aroPO.paid==0">未缴费</s:if><s:else>已缴费</s:else></td> 
					<%-- <td align="center" valign="middle"><s:property value="#aroPO.IMEI" /></td> --%>
					<td align="center" valign="middle"><s:if test="#aroPO.IMEI==1">微信预约</s:if><s:else>手机APP预约</s:else></td> 
				</tr>
			</s:iterator>
		</tbody>
	</table>
</div>