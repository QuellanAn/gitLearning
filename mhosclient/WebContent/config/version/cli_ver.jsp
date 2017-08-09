<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>版本发布</title>
<base href="${pageContext.request.contextPath}/" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/global.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/index.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/common.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/config/version/cli_ver.js"></script>
</head>
<!-- <s:if
	test="#session.userName==null ||!#session.authority.contains('ROLE_CLIENT_VER')">
	<script type="text/javascript">
		location.href = $('base').attr('href')+"login.jsp";
	</script>
</s:if> -->
<body>
	<div class="cnt-wrap">
		<s:include value="../../menus.jsp"></s:include>
		<div class="right-cnt-wrap f-fl">
			<ul class="tab-wrap bg">
				<li class="tab-crt">版本管理</li>
			</ul>
			<div class="tab-cnt">
				<div class="f-fl left-list-wrap">
					<div class="left-list-hd">
						<span class="f-fl">版本管理(<span id="totalcount"></span>)
						</span>
					</div>
					<div class="left-list-hd">
						<div style="float: left; left: 10px; top: 0px;">
							<s:form id="cliVersionForm" action="getCliVersions"
								theme="simple">
								<table height="100%" style="margin-top: -3px;">
									<tr>
										<td valign="middle">客户端平台 :</td>
										<td valign="middle"><s:select
												cssStyle="width:120px;height: 28px; padding: 4px 8px;"
												id="ver_cat" name="cliVersionPO.ver_cat"
												list="#{'':'全部','Android':'Android','iOS':'iOS' }"></s:select>
										</td>
									</tr>
								</table>
							</s:form>
						</div>

						<div style="float: right; margin-top: 5px; margin-right: 14px;">
							<input id="addbutton" type="button" value="发布新版本"
								class="save-btn" style="width: 100px; height: 28px;">
						</div>
					</div>
					<div id="div_cnt_table"></div>
				</div>
				<div class="f-clear"></div>
			</div>
		</div>
		<div class="f-clear"></div>
	</div>
</html>