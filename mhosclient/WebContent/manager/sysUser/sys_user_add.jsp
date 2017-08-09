<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>新增系统用户</title>
<base href="${pageContext.request.contextPath}/" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
<script type="text/javascript"
	src="${pageContext.request.contextPath}/manager/sysUser/sys_user_add.js"></script>
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
				<li class="tab-crt"><a href="manager/sysUser/showSysUsers">系统用户管理</a></li>
				<!-- <li><a href="manager/sysUser/showRoles">角色管理</a></li> -->
			</ul>
			<div class="tab-cnt" style="overflow: auto;">
				<s:form id="sysUserAddForm" action="saveSysUser" method="post"
					theme="simple" cssClass="introduction-fm">
					<ul>
						<li><span class="introduction-label f-fl"><span
								class="color-red">*</span>姓名:</span> <s:textfield id="realName"
								name="sysUsersVO.realName" cssClass="fm-text f-fl" /></li>


						<li><span class="introduction-label f-fl">性别:</span> <s:radio
								id="sex" name="sysUsersVO.sex" list="#{'男':'男','女':'女'}"
								value="'男'"></s:radio></li>

						<li><span class="introduction-label f-fl"><span
								class="color-red">*</span>账号名:</span> <s:textfield id="userName"
								name="sysUsersVO.userName" cssClass="fm-text f-fl"
								maxLength="20" />
							<div class="f-clear"></div>
							<p class="introduction-pmt">账号名应由6－12位数字、字母或下划线组成！</p></li>


						<li><span class="introduction-label f-fl"><span
								class="color-red">*</span>密码:</span> <s:password id="password"
								name="sysUsersVO.password" cssClass="fm-text f-fl"
								maxLength="20" />
							<div class="f-clear"></div>
							<p class="introduction-pmt">密码应由6－12位数字、字母或下划线组成！</p></li>

						<li><span class="introduction-label f-fl"><span
								class="color-red">*</span>确认密码:</span> <s:password id="repassword"
								maxLength="20" cssClass="fm-text f-fl" /></li>

						<li><span class="introduction-label f-fl">联系方式:</span> <s:textfield
								id="tel" name="sysUsersVO.tel" cssClass="fm-text f-fl" /></li>

						<li><span class="introduction-label f-fl">所属科室:</span> <s:textfield
								id="department" name="sysUsersVO.department"
								cssClass="fm-text f-fl" /></li>

						<li><span class="introduction-label f-fl"><span
								class="color-red">*</span>所属角色:</span>

							<div id="roleDiv"
								style="float: left; border: 1px solid #cdcccc; width: 200px; height: 200px; padding-left: 10px; overflow: auto;"></div>
							<div class="f-clear"></div></li>

						<li><span class="introduction-label f-fl">备注:</span> <s:textarea
								id="remark" name="sysUsersVO.remark"
								cssClass="fm-text introduction-textarea1 f-fl" /></li>

						<li><span class="introduction-label f-fl">用户状态:</span> <s:select
								id="status" name="sysUsersVO.status" list="#{'0':'有效','1':'无效'}"
								cssStyle="width:200px;height:30px;" /></li>
					</ul>
					<br />
					<center>
						<input type="submit" value="提交" class="normal-btn">
					</center>
				</s:form>
			</div>
		</div>
		<div class="f-clear"></div>
	</div>
</body>
</html>