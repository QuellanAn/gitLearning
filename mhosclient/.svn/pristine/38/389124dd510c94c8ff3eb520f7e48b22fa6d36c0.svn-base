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
<title>手机支付对账</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="resources/css/smoothness/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="resources/css/index.css" />
<script charset="utf-8" src="resources/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery.ui.datepicker-zh-CN.js"></script>
<script charset="utf-8" src="resources/js/common.js"></script>
<script type="text/javascript" src="busrecord/paycheck/pay_check_main.js"></script>
</head>
<!-- <s:if
	test="#session.userName==null ||!#session.authority.contains('ROLE_HOSINFO')">
	<script type="text/javascript">
		location.href = $('base').attr('href')+"login.jsp";
	</script>
</s:if> -->
<body>
	<div class="cnt-wrap">
		<s:include value="../../menus.jsp"></s:include>

		<div class="right-cnt-wrap f-fl">
			<ul class="tab-wrap bg">
				<li class="tab-crt"><a href="busrecord/appreg/app_reg_main.jsp">手机支付对账记录</a></li>
			</ul>
			<div class="tab-cnt">
				<div class="f-fl left-list-wrap">
					<div class="left-list-hd">
						<span class="f-fl">&nbsp;手机支付对账记录(<span id="totalcount"></span>)
						<span class="f-fl">所查询订单总金额(<span id="totalmoney"></span>)
						</span>
					</div>
					<div class="left-list-hd" style="height: 80px;">
						<form id="form_query">
							<table height="100%" style="margin-top: -3px;">
								<tr>
								<td valign="middle" align="right">预约号：</td>
									<td valign="middle">
									<input type="text" id="orderid" name="orderid" size="10"  style="height: 28px; line-height: 28px;">
									</td>
									 <td valign="middle" align="right">就诊人：</td>
									<td valign="middle">
									<input type="text" id="patientname" name="patientname" size="10"  style="height: 28px; line-height: 28px;">
									</td>
									<td valign="middle" align="right">缴费号：</td>
									<td valign="middle">
									<input type="text" id="payid" name="payid" size="10"  style="height: 28px; line-height: 28px;">
									</td>
									<td valign="middle" align="right">微信订单号：</td>
									<td valign="middle">
									<input type="text" id="wechatid" name="wechatid" size="10"  style="height: 28px; line-height: 28px;">
									</td>
									
									</tr>
									<tr>
									
									<!-- <td valign="middle" align="right">退款号：</td>
									<td valign="middle">
									<input type="text" id="patientname" name="patientname" size="10"  style="height: 28px; line-height: 28px;">
									</td>
									
									 -->
									
									
									</tr>
									<tr>
									<td valign="middle" align="right">交易时间:</td>
									<td valign="middle" align="right"><input
										name="startdate" type="text" size="10"
										style="height: 28px; line-height: 28px;" /></td>
									<td valign="middle" align="right">-</td>
									<td valign="middle" align="right"><input
										name="enddate" type="text" size="10"
										style="height: 28px; line-height: 28px;" /></td>
									
									
									<td valign="middle" align="right">交易类型：</td>
									<td valign="middle" align="right"><select id="tradetype"
										name="tradetype" style="height: 28px; padding: 4px 8px;">
											<option value="0">全部</option>
											<option value="1">挂号费</option>
											<option value="2">就诊费</option>
											<option value="3">药品费</option>
									</select></td>
									<td valign="middle" align="right">交易状态：</td>
									<td valign="middle" align="right"><select id="status"
										name="status" style="height: 28px; padding: 4px 8px;">
											<option value="0">全部</option>
											<option value="1">未支付</option>
											<option value="2">已支付</option>
											<option value="3">已退款</option>
									</select></td>
									
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
	</div>
	<div id="showRegInfo"></div>
	<div id="showPatients"></div>
</body>
</html>