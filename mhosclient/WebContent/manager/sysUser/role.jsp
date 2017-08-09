<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>角色管理</title>
<base href="${pageContext.request.contextPath}/"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/global.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/index.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/manager/sysUser/role.js"></script>
</head>
<s:if
	test="#session.userName==null ||!#session.authority.contains('ROLE_SYS_USER')">
	<script type="text/javascript">
		location.href = $('base').attr('href')+"login.jsp";
	</script>
</s:if>
<body>
<div class="cnt-wrap">
		<s:include value="../../menu.jsp"></s:include>
		<div class="right-cnt-wrap f-fl">
			<ul class="tab-wrap bg">
				<li><a href="manager/sysUser/showSysUsers">系统用户管理</a></li>
				<li class="tab-crt">角色管理</li>
			</ul>
			<div class="tab-cnt">
				<div class="f-fl left-list-wrap">
					<div class="left-list-hd">
					<div style="float: left; left: 10px; top: 0px;">
					<span class="f-fl">角色管理(<span id="totalcount"></span>)
						</span>
					</div>
						<div style="float: right; margin-top: 5px; margin-right: 14px;">
							<input id="addRoleButton" type="button" value="新增角色" class="save-btn"
								style="width: 90px; height: 28px;"
								onclick="location.href = '${pageContext.request.contextPath}/manager/sysUser/role_add.jsp';">
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