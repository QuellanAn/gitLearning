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
<title>当日挂号记录</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="resources/css/smoothness/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="resources/css/index.css" />
<script charset="utf-8" src="resources/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery.ui.datepicker-zh-CN.js"></script>
<script charset="utf-8" src="resources/js/common.js"></script>
<script type="text/javascript" src="busrecord/appreg/regist_main.js"></script>
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
				<li class="tab-crt"><a href="busrecord/appreg/regist_main.jsp">当日挂号记录</a></li>
			</ul>
			<div class="tab-cnt">
				<div class="f-fl left-list-wrap">
					<div class="left-list-hd">
						<span class="f-fl">当日挂号记录列表(<span id="totalcount"></span>)
						</span>
					</div>
					<div class="left-list-hd" style="height: 80px;">
					<div style="height: 100%;">
						<form id="form_query">
							<table height="100%" style="margin-top: -2px;">
								<tr>
								 <td valign="middle" align="center">就诊卡号：</td>
									<td valign="middle">
									<input type="text" name="cardNumber" size="10"  id="cardNumber" style="height: 28px; line-height: 28px; width:90px; ">
									</td>
									 <td valign="middle" align="left">就诊人：</td>
									<td valign="middle">
									<input type="text" id="patientname" name="patientname" size="10"  style="height: 28px; line-height: 28px;">
									</td>
									<td valign="middle" align="left">挂号途径：</td>
										<td valign="middle" align="left"><select id="regSource" name="regSource"
										    style="width: 98px;height: 28px;">
										</select></td>
									<td valign="middle" align="center">挂号单号：</td>
									<td valign="middle">
										<input type="text" id="orderid" name="orderid" size="10"  style="height: 28px; line-height: 28px;width: 96px;">
									</td>
									<td valign="middle" align="left">挂号时间：</td>
									<td valign="middle" align="center"><input
										name="startCreateTime" type="text" size="10"
										style="height: 28px; line-height: 28px;width: 95px;margin-left: -20px;" />&nbsp;-</td>
									<td valign="middle" align="center"><input
										name="endCreateTime" type="text" size="10"
										style="height: 28px; line-height: 28px;width: 95px;" /></td>
								</tr>
								<tr>
									<td valign="middle">挂号科室：</td>
									<td valign="middle" >
										<%-- <select id="departmentCode" name="departmentCode" type="text" style="width: 95px;height: 28px; line-height: 28px; " /> --%>
										<input type="text" id="departmentName" name="departmentName" size="10"  style="height: 28px; line-height: 28px;width: 90px;">
									</td>
									<td valign="middle" align="left">挂号医生：</td>
									<td valign="middle">
										<input type="text" id="doctorName" name="doctorName" size="10"  style="height: 28px; line-height: 28px;">
									</td>
									<td valign="middle" align="center">挂号状态：</td>
									<td valign="middle" align="center">
										<select id="status" name="status"
											    style="width: 98px;height: 28px;">
										</select>
									</td>
									<td valign="middle" align="center">缴费状态：</td>
									<td valign="middle" align="center">
										<select id="paid" name="paid"
										    style="width: 98px;height: 28px;">
										</select>
									</td>
									<td valign="middle" align="right" style="padding-left: 20px;"><button id="act_query"
										class="save-btn">查询</button></td>
								</tr>
								<!-- <tr>
									<td valign="middle" align="right">就诊日期：</td>
									<td valign="middle" align="right"><input name="startdate"
										type="text" size="10" style="height: 28px; line-height: 28px;" /></td>
									<td valign="middle" align="center">-</td>
									<td valign="middle" align="right"><input name="enddate"
										type="text" size="10" style="height: 28px; line-height: 28px;" /></td>
									<td valign="middle" align="right"><button id="act_query"
											class="save-btn">查询</button></td>
								</tr> -->
							</table>
						</form>
						</div>
						<!-- <div style="width: 20%;float: left;margin-left: 3px;">
							<div style="padding-top: 45px;">
								<button id="act_query" class="save-btn">查询</button>
							</div>
						</div> -->
					</div>
					<div id="div_cnt_table"></div>

				</div>
				<div class="f-clear"></div>
			</div>
		</div>

		<div class="f-clear"></div>
	</div>
	<div id="showRegInfo"></div>
	<div id="showPatients"></div>
</body>
</html>