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
<script type="text/javascript" src="../getKey.js"></script>
<!-- <s:if test="#session.userName==null">
	<script type="text/javascript">
		location.href = $('base').attr('href') + "login.jsp";
	</script>
</s:if> -->
</head>
<input type="hidden" value="${param.key}" id="key1"/>
<s:hidden value="%{#session.key}" id="key"/>
<body>
	<div class="cnt-wrap">
		<s:include value="../menus.jsp"></s:include>
		<div class="right-cnt-wrap f-fl">
			<ul class="tab-wrap bg">
				<li class="tab-crt"><a>服务评价统计</a></li>
			</ul>
			<div class="tab-cnt">
				<div class="f-fl left-list-wrap">
					<div class="left-list-hd">
						<span class="f-fl">服务评价总量(<span id="totalcount">0</span>)
						</span>
					</div>
					<div class="left-list-hd">
						<table height="100%" style="margin-top: -3px;">
							<tr>
							    <td valign="middle" align="right">选择调查问卷：</td>
								<td valign="middle" align="right"><select id="questionnaire_id"
										name="questionnaireid"
										style="width: 180px; height: 28px; padding: 4px 8px;">
											<option value="0">请选择调查问卷</option>
									</select></td>
								<td valign="middle" align="right"></td>
								<td valign="middle" align="right"></td>
								
								
								<td valign="middle" align="right">评价日期:</td>
								<td valign="middle" align="right"><input name="startdate" id="startdate" type="text" size="10" style="height: 28px; line-height: 28px;" readonly="readonly" /></td>
								<td valign="middle">-</td>
								<td valign="middle" align="right"><input name="enddate" id="enddate" type="text" size="10" style="height: 28px; line-height: 28px;" readonly="readonly" /></td>
								<td valign="middle" align="right"><button id="act_query" class="save-btn" onclick="search()">查询</button></td>
<!-- 								 <td valign="middle" align="right"><a href="statistics/getEvaluateStatistics?qnId=${questionnaire_id }"  class="save-btn">查询</a></td> -->
							</tr>
						</table>
					</div>
					<div id="container" style="width: 99%; height:500px"></div>
				</div>
				<div class="f-clear"></div>
			</div>
		</div>
		<div class="f-clear"></div>
	</div>
	
	
</body>
<script type="text/javascript">
	var chart;
	$(document).ready(function() {
		loadevaluate();
		
		var dates = $("#startdate,#enddate");
		dates.datepicker({
			changeMonth: true,
			changeYear: true,
			onSelect : function(selectedDate) {
				var option = this.id == "startdate" ? "minDate" : "maxDate";
				dates.not(this).datepicker("option", option, selectedDate);
			}
		});
	});
	
	function loadevaluate(){
		var url ="statistics/getTitleJson";
		var sel =$('#questionnaire_id');
		
		$.get(url , function(data, status){
			var jarr = eval(data);
			for (var i in jarr){
				var o = $('<option></option>').val(jarr[i].questionnaireId).html(jarr[i].title);
				sel.append(o.get());
			}
		});
	}
	
	
	function search() {
		
		var start = $("#startdate").val();
		var end = $("#enddate").val();
		var qnId =$("#questionnaire_id").val();
		
		if (qnId ==0){
			alert("请选择调查问卷");}
		else{
		if ($.trim(start).length < 1 || $.trim(end).length < 1) {
			alert("请选择起止时间");
		    }
		else{
		//alert(start);
		document.location.href="statistics/getEvaluateStatistics?qnId=" + qnId+"&start=" + start+"&end=" + end;
		}}
	}
</script>
</html>
