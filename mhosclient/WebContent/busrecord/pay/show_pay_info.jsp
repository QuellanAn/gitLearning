<%@page import="com.catic.mobilehos.po.DocBillsPO"%>
<%@page import="com.catic.mobilehos.po.PatientPO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*, com.catic.mobilehos.service.vo.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<head>
<base href="<%=basePath%>/" />
<title>缴费记录</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="resources/css/smoothness/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="resources/css/index.css" />
<script charset="utf-8" src="resources/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery-ui.js"></script>
<script type="text/javascript"
	src="resources/js/jquery/jquery.ui.datepicker-zh-CN.js"></script>
<script charset="utf-8" src="busrecord/pay/pay_main.js"></script>
</head>
<!-- <s:if
	test="#session.userName==null ||!#session.authority.contains('ROLE_HOSINFO')">
	<script type="text/javascript">
		location.href = $('base').attr('href')+"login.jsp";
	</script>
</s:if> -->
<body>
	<div class="cnt-wrap">
		<s:include value="../../menus.jsp"></s:include>

		<div class="right-cnt-wrap f-fl">
			<ul class="tab-wrap bg">
				<li class="tab-crt"><a href="busrecord/pay/pay_main.jsp">缴费详情</a></li>
			</ul>
			<div class="tab-cnt">
				<div class="patients" style="padding-left : 50px;">
					<table class="list-table" style="width: 1000px">
						<thead>
							<tr>
								<th align="center" width="15%">缴费单号</th>
								<th align="center" width="15%">就诊人</th>
								<th align="center" width="20%">缴费项目</th>
								<th align="center" width="30%">缴费金额</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator var="docBillDetailPO" value="docBillDetailPO">
								<tr>
									<td align="center" valign="middle"><s:property value="#docBillDetailPO.curDocSN" /></td>
									<td align="center" valign="middle"><s:property value="#docBillDetailPO.patientName" /></td>
									<td align="center" valign="middle"><s:property value="#docBillDetailPO.billItemName" /></td>
									<td align="center" valign="middle"><s:property value="#docBillDetailPO.itemAmount" /></td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
			</div>
		</div>

		<div class="f-clear"></div>
	</div>
		<div id="showPayInfo"></div>
		<div id="showPatients"></div>
</body>
</html>




