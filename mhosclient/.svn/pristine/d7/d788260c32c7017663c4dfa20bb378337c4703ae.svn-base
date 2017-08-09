<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>医护人员管理</title>
<base href="${pageContext.request.contextPath}/" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/smoothness/jquery-ui.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/global.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/index.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery/jquery-ui.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery/jquery.ui.datepicker-zh-CN.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/manager/serUser/serUser.js"></script>
</head>

<body>
	<div class="cnt-wrap">
		<s:include value="../../menu.jsp"></s:include>
		<div class="right-cnt-wrap f-fl">
			<ul class="tab-wrap bg">
				<li class="tab-crt">医护人员管理</li>
			</ul>
			<div class="tab-cnt">
				<div class="f-fl left-list-wrap">
					<div class="left-list-hd">医护人员管理(<span id="totalcount"></span>)
					</div>
					<div class="left-list-hd">
					<div style="float: left; left: 10px; top: 0px;">
							<s:form id="searchSerUserForm" action="searchSerUser" theme="simple">
							<s:hidden id="groupName" name="groupName" value="doctor"></s:hidden>
							<s:hidden name="baseUrl" value="manager/serUser/getOpenfireUsers"></s:hidden>
								<table height="100%" style="margin-top: -3px;">
									<tr>
										<td valign="middle">用户名 :</td>
										<td valign="middle"><s:textfield id="username"
												name="username" size="20"
												cssStyle="height: 28px; line-height: 28px;" /></td>
										<td valign="middle"><input id="" type="submit"
											class="save-btn" value="查询" /></td>
									</tr>
								</table>
							</s:form>
						</div>
						<div style="float: right; margin-top: 3px; margin-right: 14px;">
							<input id="addSysUserButton" type="button" class="save-btn"
								style="width: 90px; height: 28px;" value="新增医护人员"
								onclick="location.href = '${pageContext.request.contextPath}/manager/serUser/serUser_add.jsp';">
						</div>
					</div>
					<div id="div_cnt_table"></div>
				</div>
				<div class="f-clear"></div>
			</div>
		</div>
		<div class="f-clear"></div>
	</div>
</body>
</html>