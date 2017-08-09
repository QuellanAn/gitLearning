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
<title>就诊缴费记录</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="resources/css/smoothness/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="resources/css/index.css" />
<script charset="utf-8" src="resources/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery-ui.js"></script>
<script type="text/javascript"
	src="resources/js/jquery/jquery.ui.datepicker-zh-CN.js"></script>
<script type="text/javascript" src="../getKey.js"></script>
<script charset="utf-8" src="transFlow/pay_main.js"></script>
</head>
<!-- <s:if
	test="#session.userName==null">
	<script type="text/javascript">
		location.href = $('base').attr('href')+"login.jsp";
	</script>
</s:if> -->
<input type="hidden" value="${param.key}" id="key1"/>
<s:hidden value="%{#session.key}" id="key"/>
<body>
	<div class="cnt-wrap">
		<s:include value="../menus.jsp"></s:include>
		<div class="right-cnt-wrap f-fl">
			<ul class="tab-wrap bg">
				<li><a href="transFlow/payorder_main.jsp">交易流水查询</a></li>
				<li class="tab-crt">按订单号查询</li>
			</ul>
			<div class="tab-cnt">
				<div class="f-fl left-list-wrap">
					<div class="left-list-hd">
						<span class="f-fl">订单(<span id="totalcount"></span>)
						</span>
					</div>
					<div class="left-list-hd" style="height: 80px;" margin:0px;">
							<table height="100%" style="margin-top: -3px;">
								<tr>
								    <td valign="middle" align="right">交易单号:</td>
									<td valign="middle">
									<input type="text"  size="27"  id="out_trade_no" style="height: 28px; line-height: 28px;">
									</td>
									 <td valign="middle" align="right">第三方交易流水号:</td>
									<td valign="middle">
									<input type="text" id="orderCode"  size="27"  style="height: 28px; line-height: 28px;">
									</td>
									<td valign="middle" align="right">HIS交易流水号:</td>
									<td valign="middle">
									<input type="text"  size="27"  id="hisTransId" style="height: 28px; line-height: 28px; ">
									</td>
								</tr>
								<tr>
								    <td valign="middle" align="right">退款单号:</td>
									<td valign="middle">
									<input type="text"  size="27"  id="refundOrder" style="height: 28px; line-height: 28px;">
									</td>
									 <td valign="middle" align="right">第三方退款流水号:</td>
									 <td valign="middle">
									<input type="text"  size="27"  id="otherOrder" style="height: 28px; line-height: 28px;">
									</td>
									<td valign="middle" align="right"><button id="act_query"
											class="save-btn">查询</button></td>
								</tr>
							</table>
					</div>
					<div id="div_cnt_table"></div>

				</div>
				<div class="f-clear"></div>
			</div>
		</div>

		<div class="f-clear"></div>
	</div>
		<div id="showPayInfo"></div>
		<div id="showPatients"></div>
</body>
</html>