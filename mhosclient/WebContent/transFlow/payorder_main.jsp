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
<title>交易流水</title>
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
				<li class="tab-crt">交易流水查询</li>
				<!-- <li><a href="transFlow/payorderbyid_main.jsp">按订单号查询</a></li> -->
			</ul>
			<div class="tab-cnt">
				<div class="f-fl left-list-wrap">
					<div class="left-list-hd">
						<span class="f-fl">订单(<span id="totalcount"></span>)
						</span>
					</div>
					<div class="left-list-hd" style="height: 80px; margin:0px;">
							<table height="100%" style="margin-top: -3px;">
								<tr>
									<td valign="middle" align="right">交易类型:</td>
										<td valign="middle" align="left"><select id="jyType"
										    style="width: 120px;height: 28px;">
											<option value="">全部</option>
											<option value="1">退款</option>
											<option value="0">支付</option>
									</select></td>
									<td valign="middle" align="right">院区:</td>
									<td valign="middle"><select id="districtId" name="districtId"
										type="text"
										style="height: 28px; line-height: 28px;width:120px;">

									</select></td>
									<td valign="middle" align="right">资金账户:</td>
										<td valign="middle" align="left"><select id="account"
										    style="width: 120px;height: 28px;">
									</select></td>
								</tr>
								<tr>
									<td valign="middle" align="right">支付场景:</td>
										<td valign="middle" align="left"><select id="paySence"
										    style="width: 120px;height: 28px;">
									</select></td>
									<td valign="middle" align="right">支付方式:</td>
										<td valign="middle" align="left"><select id="pattern"
										    style="width: 120px;height: 28px;">
									</select></td>
									<td valign="middle" align="right">交易更新时间:</td>
								     <td valign="middle" align="left"><input name="startdate"
										type="text" size="15" style="height: 28px; line-height: 28px;" /></td>
									<td valign="middle">-</td>
									<td valign="middle" align="left"><input name="enddate"
										type="text" size="15" style="height: 28px; line-height: 28px;" /></td>
									<td style="padding-left: 20px;" valign="middle" align="right"><button id="act_query"
										id="act_query"	class="save-btn">查询</button></td>
									<!-- <td valign="middle" align="center"><button id="" style="width: 90px;"
											class="save-btn"  onclick="exportTransflow()">导出交易流水</button></td> -->
									</tr>
								<tr>
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