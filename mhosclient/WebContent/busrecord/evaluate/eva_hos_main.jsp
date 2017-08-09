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
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>服务评价记录</title>
<link rel="stylesheet" href="resources/css/smoothness/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="resources/css/index.css" />
<script charset="utf-8" src="resources/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery-ui.js"></script>
<script type="text/javascript"
	src="resources/js/jquery/jquery.ui.datepicker-zh-CN.js"></script>
<script charset="utf-8" src="busrecord/evaluate/eva_hos_main.js"></script>
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
				<li class="tab-crt"><a href="busrecord/evaluate/eva_hos_main.jsp">服务评价记录</a></li>
			</ul>
			<div class="tab-cnt">
				<div class="f-fl left-list-wrap">
					<div class="left-list-hd">
						<span class="f-fl">服务评价记录列表(<span id="totalcount"></span>)
						</span>
					</div>
					<div class="left-list-hd" style="height: 80px;">
						<form id="form_query">
							<table height="100%" style="margin-top: -2px;">
								<tr>
								 <td valign="middle" align="right">就诊卡号：</td>
									<td valign="middle">
									<input type="text" name="cardNumber" size="10"  id="cardNumber" style="height: 28px; line-height: 28px; widows: ">
									</td>
									<td valign="middle" align="left">就诊人：</td>
									<td valign="middle">
									<input type="text" id="patientname" name="patientname" size="10"  style="height: 28px; line-height: 28px;width:90px;">
									</td>
									<td valign="middle" align="left">就诊科室：</td>
									<td valign="middle">
										<input type="text" id="departmentName" name="departmentName" size="10"  style="height: 28px; line-height: 28px;width:90px;">
									</td>
									<td valign="middle" align="right">评价时间：</td>
									<td valign="middle" align="right"><input name="startdate"
										type="text" size="10" style="height: 28px; line-height: 28px;width:90px;" />&nbsp;-</td>
									<td valign="middle" align="right"><input name="enddate"
										type="text" size="10" style="height: 28px; line-height: 28px;width:90px;" /></td>
								</tr>
								<tr>
									<td valign="middle" align="center">就诊医生：</td>
									<td valign="middle">
										<input type="text" id="doctorName" name="doctorName" size="10"  style="height: 28px; line-height: 28px;">
									</td>
									<td valign="middle">总体评价：</td>
									<td valign="middle" align="left"><select id="overallEvaluation" name="overallEvaluation"
										    style="width: 93px;height: 28px;">
										</select></td>
									<td valign="middle" align="left">评价途径：</td>
										<td valign="middle" align="left"><select id="evaSource" name="evaSource"
										    style="width: 92px;height: 28px;">
									</select></td>						
									<td valign="middle" align="right" style="padding-left: 20px;"><button id="act_query"
											class="save-btn">查询</button></td>
								</tr>
								
							</table>
						</form>
					</div>
					<div id="div_cnt_table"></div>
				</div>
				<div class="f-clear"></div>
			</div>
		</div>
		<div class="f-clear"></div>
		<div id="node"></div>
	</div>
</body>
</html>