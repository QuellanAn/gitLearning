<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>客户端版本发布</title>
<base href="${pageContext.request.contextPath}/"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/global.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/introduction-edit.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/config/version/cli_ver_edit.js"></script>
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
				<li class="tab-crt">客户端版本发布</li>
			</ul>
			<div class="tab-cnt" style="overflow: auto;">
				<s:form id="cliVerAddForm" action="updateCliVersionAction"
					method="post" theme="simple" cssClass="introduction-fm">
					<s:hidden name="cliVersionPO.ID" value="%{cliVersionPO.ID}" />
					<ul>
						<li><span class="introduction-label f-fl">客户端类别:</span> <s:select
								id="ver_type" name="cliVersionPO.ver_type"
								list="#{'大众版':'大众版','医护版':'医护版'}"
								value="%{cliVersionPO.ver_type}"
								cssStyle="height:30px;width:150px;" /></li>
						<li><span class="introduction-label f-fl">客户端平台:</span> <s:select
								id="ver_cat" name="cliVersionPO.ver_cat"
								list="#{'Android':'Android','iOS':'iOS'}"
								value="%{cliVersionPO.ver_cat}"
								cssStyle="height:30px;width:150px;" /></li>
						<li><span class="introduction-label f-fl"><span
								class="color-red">*</span>版本号: </span> <s:textfield id="versionNo"
								name="cliVersionPO.versionNo" maxlength="30"
								cssClass="fm-text f-fl" value="%{cliVersionPO.versionNo }" />
							<div class="f-clear"></div>
							<p class="introduction-pmt">请直接输入数字和点号的版本号，例如：1.0.1，版本号请采用三层级别</p></li>
						<li><span class="introduction-label f-fl">强制更新:</span> <s:select
								id="update_version" name="cliVersionPO.update_version"
								list="#{}" cssStyle="height:30px;width:150px;" />
							<div class="f-clear"></div>
							<p class="introduction-pmt">以下，包含选定版本</p></li>
						<li><span class="introduction-label f-fl"><span
								class="color-red">*</span>更新内容:</span> <s:textarea id="content"
								name="cliVersionPO.content" value="%{cliVersionPO.content}"
								cssClass="fm-text introduction-textarea2 f-fl" /></li>
						<li><span class="introduction-label f-fl">安装包:</span>
							<p class="introduction-pmt">
								<s:property value="%{cliVersionPO.apkName}" />
							</p></li>
					</ul>
					<center><input type="submit" value="修改" class="normal-btn" ></center>
				</s:form>
			</div>
		</div>
		<div class="f-clear"></div>
	</div>
</body>
</html>