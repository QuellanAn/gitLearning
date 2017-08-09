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
<title>预约挂号统计</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="resources/css/smoothness/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="resources/css/index.css" />
<script charset="utf-8" src="resources/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="../getKey.js"></script>
<script type="text/javascript" src="resources/js/common.js"></script>
<script charset="utf-8" src="statistics/appointment/appointment_proportion_main.js"></script>
<style type="text/css">
span.gap{
	margin-left: 10px;
	margin-right: 10px;
	color: #4FB18C;
}

a.selectTime{
	color: #4FB18C;
	text-decoration: none;
}

div.divSum{
	width: 130px;
	height: 60px;
	margin: 20px auto;
	text-align: center;
}

div.divSum span.num{
	font-size: xx-large;
	font-weight: bold;
	color: #272727;
}

div.divSum span.text{
	color: #999999;
	font-size: 16px;
}

div.divDepartmentCount{
	width: 90%;
	text-align: right;
	margin-left: 30px;
}

div.divDepartmentCount a{
	color: #63B897;
	font-size: 16px;
	font-weight: bold;
	text-decoration: none;
}

label.countType{
	margin-right: 10px;
}
</style>
</head>
<input type="hidden" value="${param.key}" id="key1" />
<s:hidden value="%{#session.key}" id="key" />
<body>
	<div class="cnt-wrap">
		<s:include value="../../menus.jsp"></s:include>
		<div class="right-cnt-wrap f-fl">
			<ul class="tab-wrap bg">
				<li><a href="statistics/appointment/appointment_main.jsp">挂号趋势统计</a></li>
				<li class="tab-crt">挂号占比统计</li>
				<li><a href="statistics/appointment/appointment_amount_main.jsp">挂号量统计</a></li>
			</ul>
			<div class="tab-cnt">
				<div class="f-fl left-list-wrap">
					<div class="left-list-hd" style="height: 95px;padding-top: 10px">
						<div style="float: left;">
							<form id="form_query">
								<table height="100%" style="margin-top: 3px;">
									<tr>
										<td>挂号类型:</td>
										<td valign="middle" style="padding-left: 10px;" align="left" colspan="5">
											<select id="regType" name="regType" type="text" style="width: 120px;height: 28px; line-height: 28px;">
												<option value="">全部</option>
												<option value="0">预约挂号</option>
												<option value="1">当日挂号</option>
											</select>
											<span style="margin-left: 20px;">
												<%-- <label style="margin-right: 10px;">挂号来源:</label>
												<select name="userSource" id="userSource" style="width: 120px;height: 28px;line-height: 28px;" /> --%>
												<input id="countDepartment" name="countType" type="radio" value="department" checked="checked"/><label class="countType" for="countDepartment">按科室统计</label>
											<input id="countSource" name="countType" type="radio" value="regSource"/><label class="countType" for="countSource">按来源统计</label>
											</span>
										</td>
										<%-- <td>
											<span style="margin-left: 20px;">
												<label style="margin-right: 10px;">选择科室:</label>
												<select id="departmentCode" name="departmentCode" style="width: 120px;height: 28px; line-height: 28px; "/>
											</span>
										</td> --%>
									</tr>
									<tr>
										<td>挂号日期:</td>
										<td valign="middle" style="padding-left: 10px;" align="left" colspan="6">
											<input id="beginDate" name="sq_startdate" type="text" size="10" 
												style="height: 28px; line-height: 28px; width:120px;" readonly="readonly"/>
											<span>-</span>
											<input id="endDate" name="sq_enddate" type="text" size="10" 
												style="height: 28px; line-height: 28px;width:120px;" readonly="readonly"/>
											<span id="daySelect" style="margin-left: 20px;">
												<a class="selectTime" href="javascript:void(0)" onclick="setSelectTime(7)">最近7天</a><span class="gap">|</span> 
												<a class="selectTime" href="javascript:void(0)" onclick="setSelectTime(15)">最近15天</a><span class="gap">|</span>
												<a class="selectTime" href="javascript:void(0)" onclick="setSelectTime(30)">最近30天</a>
											</span>
											<input type="button" id="act_query"
												class="save-btn" value="查询" style="margin-left: 30px;"/>
										</td>
									</tr>
									<tr>
										<td></td>
										<td width="120px"></td>
										<td width="40px"></td>
										<td width="40px"></td>
										<td width="40px"></td>
										<td width="95px"></td>
										<td width="500px"></td>
									</tr>
								</table>
							</form>
						</div>
					</div>
					<div class="divSum">
						<span class="num">0</span><br>
						<span class="text">挂号总人数</span>
					</div>
					<!-- <div class="divDepartmentCount">
						<a href="javascript:void(0)" onclick="toDepartmentChange()">查看科室变化趋势</a>
					</div> -->
					<div
						style="margin-left:30px;margin-top:10px;margin-bottom:50px; height: 450px;width: 90%;float: left;"
						id="Section1"></div>
				</div>
				<div class="f-clear"></div>
			</div>
		</div>

		<div class="f-clear"></div>
	</div>
</body>
<script src="resources/js/echarts.min.js" type="text/javascript"></script>
</html>
