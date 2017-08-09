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
				<li class="tab-crt"><a>会员增量统计</a></li>
			</ul>
			<div class="tab-cnt">
				<div class="f-fl left-list-wrap">
					<div class="left-list-hd">
						<span class="f-fl">会员总增量</span>
					</div>
					<div class="left-list-hd">
						<table height="100%" style="margin-top: -3px;">
							<tr>
								<td valign="middle" align="right">统计日期:</td>
								<td valign="middle" align="right"><input name="startdate" id="startdate" type="text" size="10" style="height: 28px; line-height: 28px;" readonly="readonly" /></td>
								<td valign="middle">-</td>
								<td valign="middle" align="right"><input name="enddate" id="enddate" type="text" size="10" style="height: 28px; line-height: 28px;" readonly="readonly" /></td>
								<td valign="middle" align="right"><button id="act_query" class="save-btn" onclick="addNewSeries()">查询</button></td>
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
		//显示配置菜单
		var dates = $("#startdate,#enddate");
		dates.datepicker({
			changeMonth: true,
			changeYear: true,
			onSelect : function(selectedDate) {
				var option = this.id == "startdate" ? "minDate" : "maxDate";
				dates.not(this).datepicker("option", option, selectedDate);
			}
		});
		chart = $("#container").highcharts({
			chart : {
				type : "line"
			},
			lang : {
				printChart : "打印图表",
				downloadPNG : "导出 PNG 图片",
				downloadJPEG : "导出 JPEG 图片",
				downloadPDF : "导出 PDF文档",
				downloadSVG : "导出 SVG 矢量图",
				contextButtonTitle : "导出图表"
			},
			title : {
				text : "银医通用户增量统计"
			},
			subtitle : {
				text : "增量趋势图"
			},
			xAxis : {
				type : "datetime",
				labels : {
					formatter : function() {
						return Highcharts.dateFormat("%m-%d", this.value);
					}
				}
			},
			yAxis : {
				title : {
					text : "用户量(人)"
				},
				min : 0
			},
			tooltip : {
				formatter : function() {
					return "<b>" + this.series.name + "</b><br/>" + Highcharts.dateFormat("%Y-%m-%d", this.x) + ": 新增 " + this.y + " 人";
				}
			},
			credits : {
				enabled : false
			},
			series : []
		}).highcharts();
		init();
	});
	function addNewSeries() {
		var start = $("#startdate").val()+" 00:00:00";
		var end = $("#enddate").val()+" 23:59:59";
		if ($.trim(start).length < 1 || $.trim(end).length < 1) {
			alert("请选择起止时间");
		}
		chart.showLoading("加载中...");
		while (chart.series.length && chart.series.length > 0) {
			chart.series[0].remove(false);
		}
		$.post("statistics/getMemberSeries", {start: start, end: end}, function(data) {
			data = eval(data);
			var count = data.pop();
			$("#totalcount").html(count);
			for (var i = 0; i < data.length; i++) {
				chart.addSeries(data[i], false);
			}
			chart.hideLoading();
			chart.redraw();
		});
	}
	function init() {
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
		chart.showLoading("加载中...");
		while (chart.series.length && chart.series.length > 0) {
			chart.series[0].remove(false);
		}
		$.post("statistics/getMemberSeries", {start: start+" 00:00:00", end: end+" 23:59:59"}, function(data) {
			data = eval(data);
			var count = data.pop();
			$("#totalcount").html(count);
			for (var i = 0; i < data.length; i++) {
				chart.addSeries(data[i], false);
			}
			chart.hideLoading();
			chart.redraw();
		});
	};
</script>
</html>
