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
<title>对账单导入查询</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="resources/css/smoothness/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="resources/css/index.css" />
<script charset="utf-8" src="resources/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery-ui.js"></script>
<script type="text/javascript"
	src="resources/js/jquery/jquery.ui.datepicker-zh-CN.js"></script>
<script type="text/javascript" src="../getKey.js"></script>
<script charset="utf-8" src="../checkImportQuery/import_main.js"></script>
</head>
<input type="hidden" value="${param.key}" id="key1" />
<s:hidden value="%{#session.key}" id="key" />
<body>
	<div class="cnt-wrap">
		<s:include value="../menus.jsp"></s:include>
		<div class="right-cnt-wrap f-fl">
			<ul class="tab-wrap bg">
				<li class="tab-crt">对账单导入</li>
			</ul>
			<form  id="checkForm" onsubmit="return false;">
				<input type="hidden" id="type" name="type" />
				<table height="100%">
					<tr>
						<td valign="middle" align="left" width="120px;">&nbsp;&nbsp;&nbsp;&nbsp;对账单来源:</td>
						<td valign="middle" align="left"><input type="radio"
							name="source" value="HIS" checked="checked" />&nbsp;HIS</td>
					</tr>
					<tr>
						<td valign="middle" align="left" width="120px;">&nbsp;&nbsp;&nbsp;&nbsp;对账单名称:</td>
						<td valign="middle" align="left"><input type="text" size="20"
							name="name" id="name" style="height: 28px; line-height: 28px;">
						</td>
					</tr>
					<tr>
						<td valign="middle" align="left" width="120px;">&nbsp;&nbsp;&nbsp;&nbsp;对账单日期:</td>
						<td valign="middle" align="left"><input name="createTime"
							id="createTime" type="text" size="20"
							style="height: 28px; line-height: 28px;" />
						</td>
					</tr>
					<tr>
						<td valign="middle" align="left" width="120px;">&nbsp;&nbsp;&nbsp;&nbsp;对账单文件:</td>
						<td valign="middle" align="left"><input name="upload"
							id="upload" type="file" accept=".txt,.xls"
							style="height: 28px; line-height: 28px; width:180px;" />
						</td>
					</tr>
					<tr>
						<td colspan="2" valign="middle" align="center"><button onclick="importExcle()" id="act_query" class="save-btn"
							style="width: 90px;margin-top: 30px;">确认提交</button></td>
					</tr>
				</table>
			</form>
			<br />
			<p style="margin-left: 17px;color: #999999">注：<a style="margin-left: 10px;" href="#" onclick="exportMoban();return false;">下载模板</a></p>
			<p style="margin-left: 17px;color: #999999">1、上传的对账单文件支持txt格式和csv格式，一份文件最大支持记载10,000条记录。</p>
			<p style="margin-left: 17px;color: #999999">2、请提前按照模版文件txt文件示例、csv文件示例的格式编辑批量退款文件。</p>
		</div>
		<div class="f-clear"></div>
		<div class="f-clear"></div>
	</div>
</body>
</html>