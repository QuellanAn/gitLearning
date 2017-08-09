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
<link href="resources/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="resources/css/smoothness/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="resources/css/index.css" />
<link rel="stylesheet" type="text/css" href="transaction/jygl/jygl.css" />
<script charset="utf-8" src="resources/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery-ui.js"></script>
<script type="text/javascript"
	src="resources/js/jquery/jquery.ui.datepicker-zh-CN.js"></script>
<script src="resources/js/jquery/bootstrap.min.js"
	type="text/javascript"></script>
<script type="text/javascript" src="../getKey.js"></script>
<script type="text/javascript" src="resources/js/common.js"></script>
<script charset="utf-8" src="../statisticalAnalysis/trendAnalysis.js"></script>
<style type="text/css">
span.gap{
	margin-left: 10px;
	margin-right: 10px;
	color: #4FB18C;
}

a.selectDay, a.selectMonth{
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
				<li class="tab-crt">趋势占比分析</li>
				<li><a href="../statisticalAnalysis/tjfx_main.jsp">数据占比分析</a></li>
			</ul>
			<div class="tab-cnt">
				<div class="f-fl left-list-wrap">
					<div class="left-list-hd" style="height: 160px;">
						<div style="float: left;">
							<form id="form_query">
								<input type="hidden" name="ajaxType" value="json"/>
								<input type="hidden" name="export" id="export" value="">
								<table height="100%" style="margin-top: -3px;">
									<tr>
										<td valign="middle" align="left">订单类型:</td>
										<td valign="middle" style="padding-left: 30px;" width="90px;">
											<input type="radio" name="status" value="1"  checked="checked"
											 />全部</td>
										<td valign="middle" width="75px;"><input type="radio"
											name="status" value="2"/>交易</td>
										<td valign="middle"><input type="radio"
											name="status" value="3" />退款</td>
									</tr>
									<tr>
										<td valign="middle" align="left">院区:</td>
										<td valign="middle" align="left"><select name="districtId"
										    style="width: 115px;height: 28px;">
											<option value="">全部</option>
											<option value="46">南区</option>
											<option value="53">北区</option>
										</select></td>
										
 										<td valign="middle" align="left"  id="jiaofei">缴费项目:</td>
										<td valign="middle" align="left"><select name="body" id="body"
										    style="width: 115px;height: 28px;">
										</select></td>
									</tr>
									<tr>
										<td valign="middle" align="left">支付方式:</td>
										<td valign="middle" align="left"><select  id="pattern" name="pattern"
										    style="width: 115px;height: 28px;">
										</select></td>
										<td valign="middle" align="left">支付场景:</td>
										<td valign="middle" align="left"><select name="payType" id="paySence"
										    style="width: 115px;height: 28px; padding-right: 5px">
										</select>
										资金账户:<select 
											type="text" id="account" name="account"
											style="height: 28px; line-height: 28px;width:115px;margin-left: 5px;">
										</select></td>
									</tr>
									<tr>
										<td valign="middle" align="left">订单日期:</td>
										<td valign="middle" align="center" width="60px;"><input
											type="radio" name="timeType" value="1" checked="checked"/>按日统计
										</td>
										<td valign="middle" align="center" width="60px;"><input
											type="radio" name="timeType" value="2" />按月统计</td>
										<td valign="middle" align="left"><input name="startdate" type="text"
											style="height: 28px; line-height: 28px;width: 110px;" />
											<input name="startmonth" type="text"
												style="height: 28px; line-height: 28px; width:110px;display:none;"/>
											<span>-</span>
											<input name="enddate" type="text"
											style="height: 28px; line-height: 28px;width: 110px;" />
											<input name="endmonth" type="text"
												style="height: 28px; line-height: 28px; width:110px;display:none;"/>
											<span id="daySelect" style="margin-left: 20px;;">
												<a class="selectDay" href="javascript:void(0)" onclick="selectTime(7)">最近7天</a><span class="gap">|</span> 
												<a class="selectDay" href="javascript:void(0)" onclick="selectTime(15)">最近15天</a><span class="gap">|</span>
												<a class="selectDay" href="javascript:void(0)" onclick="selectTime(30)">最近30天</a>
											</span>
											<span id="monthSelect" style="margin-left: 20px;display:none;color: green;">
												<a class="selectMonth" href="javascript:void(0)" onclick="selectMonth(3)">最近3个月</a><span class="gap">|</span> 
												<a class="selectMonth" href="javascript:void(0)" onclick="selectMonth(6)">最近6个月</a><span class="gap">|</span>
												<a class="selectMonth" href="javascript:void(0)" onclick="selectMonth(12)">最近1年</a>
											</span>
										</td>
										<td valign="msiddle"><button id="act_query" style="margin-left: 10px;width: 50px;"
												class="save-btn">查询</button></td>
									</tr>
								</table>
							</form>
						</div>
					</div>
					<div id="zeFont" style="color: #999999;">注：实际总收入=交易总金额-退款总金额；实际总笔数=交易总笔数+退款总笔数；人均金额=实际总收入/实际总笔数。</div>
					<div id="zbFont" style="color: #999999;display: none;">注：人均交易金额=交易总金额/交易总笔数。</div>
					<div id="rjFont" style="color: #999999;display: none;">注：人均退款金额=退款总金额/退款总笔数。</div>
									<div class="tab" role="tabpanel"
										style="width: 1030px;margin-left: 30px;">
										<ul class="nav nav-tabs" role="tablist">
											<li style="width: 33%" role="presentation" class="active"><a
												href="#Section1" aria-controls="home" role="tab"
												id="totalMoney" data-toggle="tab"
												style="height: 60px;text-align: center;"></a></li>
											<li style="width: 33%" role="presentation"><a
												href="#Section2" aria-controls="profile" role="tab"
												id="totalCount" data-toggle="tab"
												style="height: 60px;text-align: center;"></a></li>
											<li style="width: 34%" role="presentation"><a
												href="#Section3" aria-controls="messages" role="tab"
												id="avgMoney" data-toggle="tab"
												style="height: 60px;text-align: center;"></a></li>
										</ul>
										<div class="tab-content tabs">
											<div role="tabpanel" class="tab-pane fade in active"
												style="height: 410px;width: 1000px;" id="Section1"></div>
											<div role="tabpanel" class="tab-pane fade"
												style="height: 410px;width: 1000px;" id="Section2"></div>
											<div role="tabpanel" class="tab-pane fade"
												style="height: 410px;width: 1000px;" id="Section3"></div>
										</div>
									</div>
						<div align="right"><input type="button" class="save-btn" style="margin:5px 20px 5px 0px;width: 90px;" 
						value="下载EXCLE文件" onclick="exportCheckValue();"/></div>
						<div id="div_cnt_table"></div>
					</div>
				<div class="f-clear"></div>
			</div>
		</div>

		<div class="f-clear"></div>
	</div>
</body>
<script src="resources/js/echarts.min.js" type="text/javascript"></script>
</html>
