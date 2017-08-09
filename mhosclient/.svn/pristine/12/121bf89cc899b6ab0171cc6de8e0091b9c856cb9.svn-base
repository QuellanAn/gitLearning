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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>服务评价统计</title>
<link rel="stylesheet" href="resources/css/smoothness/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="resources/css/index.css" />
<script charset="utf-8" src="resources/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery-ui.js"></script>
<script type="text/javascript"
	src="resources/js/jquery/jquery.ui.datepicker-zh-CN.js"></script>
</head>
<!-- <s:if
	test="#session.userName==null ||!#session.authority.contains('ROLE_HOSINFO')">
	<script type="text/javascript">
		location.href = $('base').attr('href')+"login.jsp";
	</script>
</s:if> -->
<body>
	<div class="cnt-wrap">
	<s:include value="../menus.jsp"></s:include>

		<div class="right-cnt-wrap f-fl">
			<ul class="tab-wrap bg">
				<li class="tab-crt"><a href="${pageContext.request.contextPath}/statistics/evaluateHos.jsp">服务评价统计</a></li>
			</ul>
			<div class="tab-cnt">
				<div class="f-fl left-list-wrap">
					<div class="left-list-hd">
						<span class="f-fl">服务评价统计<%-- (<span id="totalcount">0</span>) --%>
						</span>
					</div>
					<div class="left-list-hd">
						<form id="form_query">
							<table height="100%" style="margin-top: -3px;">
								<tr>
									<td valign="middle" align="right"></td>
									
									<td valign="middle">科室:</td>
									<td valign="middle"><select id="departmentCode" name="departmentCode"
										type="text" style="height: 28px; line-height: 28px;" /></td>
									
								<!-- 	<td valign="middle" align="right">主治医生：</td>
									<td valign="middle">
									<input type="text" id="doctorName" name="doctorName" size="10"  style="height: 28px; line-height: 28px;">
									</td> -->
									
									<td valign="middle" align="right">评价日期:</td>
									<td valign="middle" align="right"><input name="startdate" id="startdate"
										type="text" size="10" style="height: 28px; line-height: 28px;" /></td>
									<td valign="middle">-</td>
									<td valign="middle" align="right"><input name="enddate" id="enddate"
										type="text" size="10" style="height: 28px; line-height: 28px;" /></td>
									
									<td valign="middle" align="right"><button id="act_query"
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

<script type="text/javascript">
	$(document).ready(function() {
	//显示配置菜单
	$('.menu-item-cnt').attr("style", "display:none;");
	$('.menu-item-cnt:eq(2)').attr("style", "display:block");
	
	initDepartment();
	init();
	$("#act_query").click(function(e){
		var paras = $("#form_query").serialize();
		var url = "statistics/getEvaluateHosStatistics?page=1&" +encodeURI(paras);
		loadData(url);
		return false;
	});
	$('input[name=startdate]').datepicker({ dateFormat: 'yy-mm-dd' });
	$('input[name=enddate]').datepicker({ dateFormat: 'yy-mm-dd' });
	});
	
	
	function loadData(url){
	var cnt = $('#div_cnt_table');
			$.get(url , function(data, status){
				if (data && data.msg){
					alert(data.msg);
				}else if (data){
					cnt.empty();
					cnt.append(data);
				}
			});
	};
	
	function initDepartment(){
		var sel = $('#departmentCode');
		sel.empty();
		sel.append("<option value=''>全部</option>");
		$.post("config/department/findAllDeptment" , function(data, status){
			var obj=eval('('+data+')');
			var jarr = obj.depts;
			for (var i in jarr){
				var o = $('<option></option>').val(jarr[i].departmentId).html(jarr[i].departmentName);
				sel.append(o.get());
			}
		});
		
	};
	function init(){
		var now = new Date();
		var year = now.getFullYear();
		var month = now.getMonth() + 1;
		var end = year+"-"+month+"-"+now.getDate();
		
		var date = new Date(now.getTime() - 7 * 24 * 3600 * 1000);
		var year1 = date.getFullYear();
		var month1 = date.getMonth() + 1;
		var start = year1+"-"+month1+"-"+date.getDate();
		$("#startdate").val(start);
		$("#enddate").val(end);
		var url = "statistics/getEvaluateHosStatistics?page=1&startSQLDate=" +start+" 00:00:00"+"&endSQLDate="+end+" 23:59:59";
		loadData(url);
		return false;
	};
	function refresh_totalcount() {
		$("#totalcount").html($("#hid_totalcount").val());
	};
</script>

</html>