<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>系统用户管理</title>
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
	src="${pageContext.request.contextPath}/manager/sysUser/sysUser.js"></script>
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
				<li class="tab-crt">系统用户管理</li>
				<!--  <li><a href="manager/sysUser/showRoles">角色管理</a></li> -->
			</ul>
			<div class="tab-cnt">
				<div class="f-fl left-list-wrap">
					<div class="left-list-hd">
						<span class="f-fl">系统用户(<span id="totalcount"></span>)
						</span>
					</div>
					<div class="left-list-hd">
						<div style="float: left; left: 10px; top: 0px;">
							<s:form id="searchSysUserForm" action="getSysUsers"
								theme="simple">
								<table height="100%" style="margin-top: -3px;">
									<tr>
										<td valign="middle">姓名：</td>
										<td valign="middle"><s:textfield id="realName"
												name="realName" size="10"
												cssStyle="height: 28px; line-height: 28px;" /></td>
										<td valign="middle">账号状态：</td>
										<td valign="middle"><s:select
												cssStyle="width:90px;height: 28px; padding: 4px 8px;"
												id="status" name="status"
												list="#{'-1':'全部','0':'有效','1':'无效' }"></s:select></td>
										<td valign="middle">所属角色:</td>
										<td valign="middle"><s:select
												cssStyle="width:90px;height: 28px; padding: 4px 8px;"
												id="roleId" name="roleId" list="#{ }" headerKey="-1"
												headerValue="全部"></s:select></td>
										<td valign="middle"><input id="" type="submit" value="查询"
											class="save-btn"></td>
									</tr>
								</table>
							</s:form>
						</div>
						<div style="float: right; margin-top: 3px; margin-right: 14px;">
							<input id="addSysUserButton" type="button" class="save-btn"
								style="width: 90px; height: 28px;" value="新增用户"
								onclick="location.href = '${pageContext.request.contextPath}/manager/sysUser/sys_user_add.jsp';">
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