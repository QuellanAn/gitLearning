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
				<!-- <li class="tab-crt"><a>挂号违约记录</a></li> -->
				<li><a href="${pageContext.request.contextPath}/statistics/getChartTableView">挂号总统计表</a></li>
			</ul>
			<div class="tab-cnt">
				<div class="f-fl left-list-wrap">
					<div class="left-list-hd">
						<span class="f-fl">统计总量(<span id="totalcount">0</span>)
						</span>
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
		$(".menu-item-cnt").attr("style", "display:none;");
		$(".menu-item-cnt:eq(1)").attr("style", "display:block");

		var dates = $("#startdate,#enddate");
		dates.datepicker({
			changeMonth : true,
			changeYear: true,
			onSelect : function(selectedDate) {
				var option = this.id == "startdate" ? "minDate" : "maxDate";
				dates.not(this).datepicker("option", option, selectedDate);
			}
		});

		// Build the chart
		chart = $("#container").highcharts({
			chart : {
				plotBackgroundColor : null,
				plotBorderWidth : null,
				plotShadow : false
			},
			title : {
				text : "预约挂号异常统计表"
			},
			lang : {
				printChart : "打印图表",
				downloadPNG : "导出 PNG 图片",
				downloadJPEG : "导出 JPEG 图片",
				downloadPDF : "导出 PDF文档",
				downloadSVG : "导出 SVG 矢量图",
				contextButtonTitle : "导出图表"
			},
			tooltip : {
				pointFormat : "{series.name}: <b>{point.y}</b><br>占比: <b>{point.percentage:.2f}% </b>"
			},
			plotOptions : {
				pie : {
					allowPointSelect : true,
					cursor : "pointer",
					dataLabels : {
						enabled : false
					},
					showInLegend : true
				}
			},
			credits : {
				enabled : false
			},
// 			series : [ {
// 				type : "pie",
// 				name : "人次",
// 				data : [ [ "正常 的挂号", 45 ], [ "爽约 的挂号", 26 ], [ "逾期 的挂号", 12 ] ]
// 			} ]
			series: []
		}).highcharts();
	});

	function addNewSeries() {
		var start = $("#startdate").val();
		var end = $("#enddate").val();
		if ($.trim(start).length < 1 || $.trim(end).length < 1) {
			alert("请选择起止时间");
		}
		chart.showLoading("加载中...");
		while (chart.series.length && chart.series.length > 0) {
			chart.series[0].remove(false);
		}
		$.post("statistics/getRegistrationException", {start : start, end : end}, function(result) {
			result = eval(result);
			var count = result.pop();
			$("#totalcount").html(count);
			var series = {
				type : "pie",
				name : "人次",
				data : result
			};
			chart.addSeries(series, false);
			chart.hideLoading();
			chart.redraw();
		});
	}
</script>
</html>
