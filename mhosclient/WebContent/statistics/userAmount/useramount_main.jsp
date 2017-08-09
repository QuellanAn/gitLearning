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
<title>用户量统计</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="resources/css/smoothness/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="resources/css/index.css" />
<script charset="utf-8" src="resources/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="../getKey.js"></script>
<script type="text/javascript" src="resources/js/common.js"></script>
<script charset="utf-8" src="statistics/userAmount/useramount_main.js"></script>
<style type="text/css">
ul.nav-tabs{
	width: 130px;
	height: 35px;
	margin: 20px auto;
	margin-bottom: 10px;
	border: 1px solid #44AD86;
}

ul.nav-tabs li{
	float: left;
	width: 50%;
	height: 35px;
	text-align: center;
	vertical-align: middle;
	padding: 0;
	color: #44AD86;
}

ul.nav-tabs li:hover{
	cursor: pointer;
}

ul.nav-tabs li.active{
	background-color: #44AD86;
	color: #fff;
}

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

span.radioSelection{
	margin-right: 15px;
}
</style>
</head>
<input type="hidden" value="${param.key}" id="key1" />
<s:hidden value="%{#session.key}" id="key" />
<body>
	<div class="cnt-wrap">
		<input type="hidden" value="2" id="userType"/>
		<s:include value="../../menus.jsp"></s:include>
		<div class="right-cnt-wrap f-fl">
			<ul class="tab-wrap bg">
				<li class="tab-crt">用户增量统计</li>
				<li><a href="statistics/userAmount/patientamount_main.jsp">就诊人增量统计</a></li>
			</ul>
			<div class="tab-cnt">
				<div class="f-fl left-list-wrap">
					<div class="left-list-hd" style="height: 95px;">
						<div style="float: left;">
							<form id="form_query">
								<table height="100%" style="margin-top: 3px;">
									<tr>
										<%-- <td valign="middle" align="right">用户类型:</td>
										<td valign="middle" style="padding-left: 30px;" width="120px;" colspan="5">
											<span class="radioSelection">
												<input type="radio" name="userType" value="2" id="mobileUser" checked="checked"/>
												<label for="mobileUser">移动端用户</label>
											</span>
											<span class="radioSelection">
												<input type="radio" name="userType" value="0" id="onlineUser" />
												<label for="onlineUser">在线建卡用户</label>
											</span>
											<span class="radioSelection">
												<input type="radio" name="userType" value="1" id="oldUser" />
												<label for="oldUser">新绑定老患者</label>
											</span>
										</td>
										<td>用户来源：<select name="userSource" id="userSource"
										    style="width: 130px;height: 28px;">
											</select>
										</td> --%>
										<td valign="middle" align="right">用户来源:</td>
										<td valign="middle" style="padding-left: 10px;" width="120px;" colspan="5">
											<select name="userSource" id="userSource" style="width: 130px;height: 28px;">
											</select>
										</td>
									</tr>
									<tr>
										<td>注册日期:</td>
										<td valign="middle" style="padding-left: 10px;" align="left" colspan="6">
											<input id="beginDate" name="sq_startdate" type="text" size="10" 
												style="height: 28px; line-height: 28px; width:120px;" readonly="readonly"/>
											<span>-</span>
											<input id="endDate" name="sq_enddate" type="text" size="10" 
												style="height: 28px; line-height: 28px;width:120px;" readonly="readonly"/>
											<span style="margin-left: 20px;">
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
										<td width="120px"></td>
										<td width="300px"></td>
									</tr>
								</table>
							</form>
						</div>
					</div>
					<div class="divSum">
						<span class="num">0</span><br>
						<span class="text">累计增加用户量</span>
					</div>
					<ul class="nav nav-tabs">
						<li id="increment" class="active">增量</li>
						<li id="allCount">总人数</li>
					</ul>
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
