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
<title>第三方账单下载</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="resources/css/smoothness/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="resources/css/index.css" />
<s:include value="../publicJs.jsp"></s:include>
<script type="text/javascript"
	src="resources/js/jquery/jquery.ui.datepicker-zh-CN.js"></script>
<script charset="utf-8" src="../checkImportQuery/export_main.js"></script>
</head>
<input type="hidden" value="${param.key}" id="key1" />
<s:hidden value="%{#session.key}" id="key" />
<body>
	<div class="cnt-wrap" style="height: 530px;">
		<s:include value="../menus.jsp"></s:include>
		<div class="right-cnt-wrap f-fl">
			<ul class="tab-wrap bg">
				<li><a href="../check/check_main.jsp">多方对账</a>
				</li>
				<li><a href="../checkRecord/checkRecord_main.jsp">对账处理记录</a>
				</li>
				<li><a href="../checkImportQuery/ImportQuery_main.jsp">对账单导入查询</a>
				</li>
				<li class="tab-crt">第三方账单下载</li>
			</ul>
			<form id="checkForm" onsubmit="return false;">
				<input type="hidden" id="type" name="type" />
				<table height="100%">
					<tr>
						<td valign="middle" align="left"
							style="padding-left: 40px;padding-right: 20px;" width="120px;">&nbsp;&nbsp;&nbsp;&nbsp;第三方支付平台:</td>
						<td valign="middle" align="left"><select id="name"
							name="name" style="height: 28px; line-height: 28px;width: 150px;">
								<option value="1">微信</option>
								<option value="2">支付宝</option>
						</select></td>
					</tr>
					<tr>
						<td valign="middle" align="left"
							style="padding-left: 40px;padding-right: 20px;" width="120px;">&nbsp;&nbsp;&nbsp;&nbsp;对账单日期:</td>
						<td valign="middle" align="left"><input name="createTime"
							id="createTime" type="text"
							style="height: 28px; line-height: 28px;width: 150px;" /></td>
					</tr>
					<tr>
						<td colspan="2" valign="middle" align="center"><button
								onclick="exportFile()" id="act_query" class="save-btn"
								style="width: 90px;margin-top: 30px;">确认下载</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="f-clear"></div>
		<div class="f-clear"></div>
	</div>
</body>
</html>