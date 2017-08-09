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
				<li class="tab-crt"><a>预约统计</a></li>
				<%-- <li><a href="${pageContext.request.contextPath}/statistics/registration-exception.jsp">挂号违约记录</a></li> --%>
				<%-- <li><a href="${pageContext.request.contextPath}/statistics/getChartTableView">挂号总统计表</a></li> --%>
			</ul>
			<div class="tab-cnt">
				<div class="f-fl left-list-wrap">
					<div class="left-list-hd">
						<span class="f-fl">预约总量(<span id="totalcount">0</span>)
						</span>
					</div>
					<div class="left-list-hd">
						<table height="100%" style="margin-top: -3px;">
							<tr>
								<td valign="middle" align="right">统计日期:</td>
								<td valign="middle" align="right"><input name="startdate" id="startdate" type="text" size="10" style="height: 28px; line-height: 28px;" readonly="readonly" /></td>
								<td valign="middle">-</td>
								<td valign="middle" align="right"><input name="enddate" id="enddate" type="text" size="10" style="height: 28px; line-height: 28px;" readonly="readonly" /></td>
								<!-- <td valign="middle" align="right"><button id="act_query" class="save-btn" onclick="addNewSeries()">查询</button></td> -->
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
	var colors, name = "科室预约统计", categories = [], data = [], setChart, chart;

	$(document).ready(function() {
		colors = Highcharts.getOptions().colors;
		var dates = $("#startdate,#enddate");
		dates.datepicker({
			changeMonth : true,
			changeYear: true,
			onSelect : function(selectedDate) {
				var option = this.id == "startdate" ? "minDate" : "maxDate";
				dates.not(this).datepicker("option", option, selectedDate);
			}
		});

		chart = $("#container").highcharts({
			chart : {
				type : "column"
			},
// 			lang : {
// 				printChart : "打印图表",
// 				downloadPNG : "导出 PNG 图片",
// 				downloadJPEG : "导出 JPEG 图片",
// 				downloadPDF : "导出 PDF文档",
// 				downloadSVG : "导出 SVG 矢量图",
// 				contextButtonTitle : "导出图表"
// 			},
			title : {
				text : "预约统计表"
			},
			subtitle : {
				text : "点击科室列可查看详情,再次点击则返回."
			},
			xAxis : {
				categories : categories
			},
			yAxis : {
				title : {
					text : "预约数(人次)"
				},
				min : 0
			},
			plotOptions : {
				column : {
					cursor : "pointer",
					point : {
						events : {
							click : function() {
								var drilldown = this.drilldown;
								if (drilldown) { // drill down
									setChart(drilldown.name, drilldown.categories, drilldown.data, drilldown.color);
								} else { // restore
									setChart(name, categories, data);
								}
							}
						}
					},
					dataLabels : {
						enabled : true,
						//color : colors[0],
						style : {
							fontWeight : "bold"
						},
						formatter : function() {
							return "<font style='color: "+this.color+"'>" + this.y + "</font>";
						}
					}
				}
			},
			tooltip : {
				formatter : function() {
					var point = this.point, s = this.x + ": <b>" + this.y + "</b> 人次<br>";
					if (point.drilldown) {
						s += "点此查看 [" + point.category + "] 详情";
					} else {
						s += "点此返回上一页";
					}
					return s;
				}
			},
			series : [ {
				name : name,
				data : data,
				color : colors[0]
			} ],
			exporting : {
				enabled : false
			},
			credits : {
				enabled : false
			}
		}).highcharts(); // return chart

		setChart = function(name, categories, data, color) {
			chart.xAxis[0].setCategories(categories, false);
			chart.series[0].remove(false);
			chart.addSeries({
				name : name,
				data : data,
				color : color || colors[0]
			}, false);
			chart.hideLoading();
			chart.redraw();
		};
		init();
	});
	
	function addNewSeries() {
		var start = $("#startdate").val()+" 00:00:00";
		var end = $("#enddate").val()+" 23:59:59";
		if ($.trim(start).length < 1 || $.trim(end).length < 1) {
			alert("请选择起止时间");
		}
		chart.showLoading("加载中...");
		$.post("statistics/getRegistrationSeries", {start : start,end : end, type:"0"}, function(result) {
		result=result.replace("\n", "\\n").replace("\r", "\\r");
			result = eval(result);
			var count = result.pop();
			$("#totalcount").html(count);
			categories = result.pop();
			data = result;
			setChart(name, categories, data);
		});
	};
	function init() {
		var now = new Date();
		var year = now.getFullYear();
		var month = now.getMonth() + 1;
		var hour = now.getHours();
		var minute = now.getMinutes();
		var second = now.getSeconds();
		var end = year+"-"+month+"-"+now.getDate();
		
		var date = new Date(now.getTime() - 7 * 24 * 3600 * 1000);
		var year1 = date.getFullYear();
		var month1 = date.getMonth() + 1;
		var hour = date.getHours();
		var minute = date.getMinutes();
		var second = date.getSeconds();
		var start = year1+"-"+month1+"-"+date.getDate();
		$("#startdate").val(start);
		$("#enddate").val(end);
		chart.showLoading("加载中...");
		$.post("statistics/getRegistrationSeries", {start : start+" 00:00:00",end : end+" 23:59:59",type:"0"}, function(result) {
			result=result.replace("\n", "\\n").replace("\r", "\\r");
			result = eval(result);
			var count = result.pop();
			$("#totalcount").html(count);
			categories = result.pop();
			data = result;
			setChart(name, categories, data);
		});
	};
</script>
</html>