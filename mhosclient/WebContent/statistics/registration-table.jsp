<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>/" />
<title></title>
<link rel="stylesheet" href="resources/css/smoothness/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="resources/css/index.css" />
<script charset="utf-8" src="resources/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery.ui.datepicker-zh-CN.js"></script>
<script type="text/javascript" src="resources/js/highcharts/highcharts.js"></script>
<script type="text/javascript" src="resources/js/highcharts/exporting.js"></script>
<!-- <s:if test="#session.userName==null">
	<script type="text/javascript">
		location.href = $('base').attr('href') + "login.jsp";
	</script>
</s:if> -->
</head>
<body>
	<div class="cnt-wrap">
		<s:include value="../menus.jsp"></s:include>
		<div class="right-cnt-wrap f-fl">
			<ul class="tab-wrap bg">
				<li><a href="${pageContext.request.contextPath}/statistics/registration.jsp">预约挂号统计</a></li>
			<%-- 	<li><a href="${pageContext.request.contextPath}/statistics/registration-exception.jsp">挂号违约记录</a></li> --%>
				<li class="tab-crt"><a>挂号总统计表</a></li>
			</ul>
			<div class="tab-cnt">
				<div class="f-fl left-list-wrap">
					<div class="left-list-hd">
						<table height="100%" style="margin-top: -3px;">
							<tr>
								<td valign="middle" align="right">科室:</td>
								<td valign="middle" align="right"><select id="department" name="department" onchange="chooseDoctor(this)">
										<option value="all">所有科室</option>
										<s:iterator id="department" value="#request.data.departments">
											<option value="${department.department_id }">${department.department_name }</option>
										</s:iterator>
								</select></td>
								<td valign="middle">医生</td>
								<td valign="middle" align="right"><select id="doctor" name="doctor">
										<option value="all" class="all_doctor">所有医生</option>
										<s:iterator id="doctor" value="#request.data.doctors">
											<option value="${doctor.doctor_id }" class="dep_${doctor.department_id }">${doctor.doctor_name }</option>
										</s:iterator>
								</select></td>
								<td valign="middle" align="right">统计日期:</td>
								<td valign="middle" align="right"><input name="startdate" id="startdate" type="text" size="10" style="height: 28px; line-height: 28px;" readonly="readonly" /></td>
								<td valign="middle">-</td>
								<td valign="middle" align="right"><input name="enddate" id="enddate" type="text" size="10" style="height: 28px; line-height: 28px;" readonly="readonly" /></td>
								<td valign="middle" align="right"><button id="act_query" class="save-btn" onclick="addNewSeries()">查询</button></td>
							</tr>
						</table>
					</div>
					<div id="result" style="width: 99%;"></div>
				</div>
				<div class="f-clear"></div>
			</div>
		</div>
		<div class="f-clear"></div>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		//显示配置菜单
		$(".menu-item-cnt").attr("style", "display:none;");
		$(".menu-item-cnt:eq(2)").attr("style", "display:block");

		var dates = $("#startdate,#enddate");
		dates.datepicker({
			changeMonth : true,
			changeYear : true,
			onSelect : function(selectedDate) {
				var option = this.id == "startdate" ? "minDate" : "maxDate";
				dates.not(this).datepicker("option", option, selectedDate);
			}
		});
	});

	function chooseDoctor(department) {
		var id = $(department).val();
		if (id == "all") {
			$("#doctor option").show();
		} else {
			$("#doctor :not(.dep_" + id + ")").hide();
			$(".all_doctor").show();
			$("#doctor .dep_" + id).show();
		}
		$("#doctor").val("all");
	}

	function addNewSeries() {
		var start = $("#startdate").val();
		var end = $("#enddate").val();
		if ($.trim(start).length < 1 || $.trim(end).length < 1) {
			alert("请选择起止时间");
		}
		var department = $("#department").val(), doctor = $("#doctor").val();
		$.post("statistics/getChartTableData", {dep: department, doc: doctor, start : start, end : end}, function(result) {
			$("#result").html(result);
		}, "html");
	}
</script>
</html>
