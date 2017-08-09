<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>新增角色</title>
<base href="${pageContext.request.contextPath}/" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/global.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/introduction-edit.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/zTreeStyle/zTreeStyle.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/common.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/manager/sysUser/role_add.js"></script>
<style type="text/css">
.ztree li {
	min-height: 5px;
}
</style>
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
				<li class="tab-crt"><a href="manager/sysUser/showRoles">角色管理</a></li>
			</ul>
			<div class="tab-cnt" style="overflow: auto;">
				<s:form id="roleAddForm" action="saveRole" method="post"
					theme="simple" cssClass="introduction-fm">
					<ul>
						<li><span class="introduction-label f-fl"><span
								class="color-red">*</span>角色:</span> <s:textfield id="roleName"
								name="rolesVO.roleName" cssClass="fm-text f-fl" /></li>

						<li><span class="introduction-label f-fl"><span
								class="color-red">*</span>包含权限:</span>
							<div
								style="float: left; height: 250px; width: 250px; border: 1px solid #cdcccc; overflow: auto;">
								<ul id="ztree" class="ztree"></ul>
							</div>
							<div class="f-clear"></div></li>

						<li><span class="introduction-label f-fl">备注:</span> <s:textarea
								id="remark" name="rolesVO.remark"
								cssClass="fm-text introduction-textarea1 f-fl" /></li>

					</ul>
					<br />
					<center>
						<input id="addButton" type="button" value="提交" class="normal-btn">
					</center>
					<s:hidden id="au_ids" name="au_ids" />
				</s:form>
			</div>
		</div>
		<div class="f-clear"></div>
	</div>
</body>
</html>