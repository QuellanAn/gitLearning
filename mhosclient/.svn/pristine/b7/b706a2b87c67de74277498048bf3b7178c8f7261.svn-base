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
<title>交易统计</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="resources/css/smoothness/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="resources/css/index.css" />
<script charset="utf-8" src="resources/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery.ui.datepicker-zh-CN.js"></script>
<script type="text/javascript" src="../getKey.js"></script>
<script type="text/javascript" src="resources/js/common.js"></script>
<script charset="utf-8" src="../statisticalAnalysis/tjfx_main.js"></script>
<style type="text/css">
span.gap{
	margin-left: 10px;
	margin-right: 10px;
	color: #4FB18C;
}

a.selectDay{
	color: #4FB18C;
	text-decoration: none;
}
</style>
</head>
<input type="hidden" value="${param.key}" id="key1" />
<s:hidden value="%{#session.key}" id="key" />
<body>
	<div class="cnt-wrap">
		<s:include value="../menus.jsp"></s:include>
		<div class="right-cnt-wrap f-fl">
			<ul class="tab-wrap bg">
				<li><a href="../statisticalAnalysis/trendAnalysis.jsp">交易趋势分析</a></li>
				<li class="tab-crt">交易占比分析</li>
			</ul>
			<div class="tab-cnt">
				<div class="f-fl left-list-wrap">
					<div class="left-list-hd" style="height: 120px;">
						<div style="float: left;">
							<form id="form_query">
							<input type="hidden" name="ajaxType" value="json"/>
								<table height="100%" style="margin-top: -3px;">
									<tr>
										<td valign="middle" align="left">分析类型:</td>
										<td valign="middle" align="left"><select name="xiangmu" id="xiangmu"
										    style="width: 115px;height: 28px;">
											<option value="1">总金额</option>
											<option value="2">总笔数</option>
										</select>
										<label>分析项目:</<label>
										<span><select name="po.analysisType" id="leibi"
										    style="width: 115px;height: 28px;">
											<option value="1">院区</option>
											<option value="2">资金账户</option>
											<option value="3">支付方式</option>
											<option value="4">支付场景</option>
											<option value="5">缴费项目</option>
										</select></span></label>
									</tr>
									<tr>
										<td valign="middle" align="right">交易时间:</td>
										<td valign="middle" align="left"><input id="startTime" name="startTime"
											type="text" size="13"
											style="height: 28px; line-height: 28px;" /> - 
										<input id="endTime" name="endTime"
											type="text" size="13"
											style="height: 28px; line-height: 28px;" />
										
										<span style="margin-left: 20px;">
												<a class="selectDay" href="javascript:void(0)" onclick="selectTime(1)">今天</a><span class="gap">|</span> 
												<a class="selectDay" href="javascript:void(0)" onclick="selectTime(7)">最近7天</a><span class="gap">|</span> 
												<a class="selectDay" href="javascript:void(0)" onclick="selectTime(15)">最近15天</a><span class="gap">|</span>
												<a class="selectDay" href="javascript:void(0)" onclick="selectTime(30)">最近30天</a>
										</span>
										<td valign="msiddle"><button id="act_query"
												class="save-btn">查询</button></td>
									</tr>
								</table>
							</form>
						</div>
					</div>
					<div
						style="margin-left:200px;margin-top:50px; height: 360px;width: 530px;float: left;"
						id="Section1" align="center"></div>
				</div>
				<div class="f-clear"></div>
			</div>
		</div>

		<div class="f-clear"></div>
	</div>
</body>
<script src="resources/js/echarts.min.js" type="text/javascript"></script>
</html>
