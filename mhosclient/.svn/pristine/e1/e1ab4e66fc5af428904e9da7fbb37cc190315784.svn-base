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
<title>充值记录</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="resources/css/smoothness/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="resources/css/index.css" />
<script charset="utf-8" src="resources/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery-ui.js"></script>
<script type="text/javascript"
	src="resources/js/jquery/jquery.ui.datepicker-zh-CN.js"></script>
<script charset="utf-8" src="trade/trade_main.js"></script>
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
				<li class="tab-crt"><a href="trade/trade_main.jsp">充值记录</a></li>
			</ul>
			<div class="tab-cnt" id="tb_cnt">
				<div class="f-fl left-list-wrap">
					<div class="left-list-hd">
						<span class="f-fl">充值记录列表(<span id="totalcount"></span>)
						</span>
					</div>
					<div class="left-list-hd" style="height: 40px;">
						<form id="form_query">
							<table height="100%" style="margin-top: 0px;">
								<tr>
								 	<td valign="middle" align="right">就诊卡号：</td>
									<td valign="middle">
									<input type="text" id="cardNo" name="cardNo" size="10"  style="height: 28px; line-height: 28px;">
									</td>
									<!--  <td valign="middle" align="right">商户订单号：</td>
									<td valign="middle">
									<input type="text" name="tradeNo" size="10"  id="tradeNo" style="height: 28px; line-height: 28px; widows: ">
									</td> -->
									<td valign="middle" align="right">就诊人：</td>
									<td valign="middle">
									<input type="text" id="patientname" name="patientname" size="10"  style="height: 28px; line-height: 28px;">
									</td>
									<td valign="middle" align="right">充值时间：</td>
									<td valign="middle" align="right"><input name="startdate"
										type="text" size="10" style="height: 28px; line-height: 28px;" /></td>
									<td valign="middle"  align="center">-</td>
									<td valign="middle" align="right"><input name="enddate"
										type="text" size="10" style="height: 28px; line-height: 28px;" /></td>
									<td valign="middle" align="center" style="padding-left: 20px;"><button id="act_query"
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
		<div id="showPayInfo"></div>
		<div id="showPatients"></div>
</body>
</html>