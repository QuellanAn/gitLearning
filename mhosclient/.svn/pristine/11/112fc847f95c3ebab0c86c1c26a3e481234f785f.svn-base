<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>/" />
<title>添加系统用户</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="resources/css/smoothness/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="resources/css/main.css" />
<link rel="stylesheet" type="text/css" href="resources/css/introduction-edit.css" />
<script type="text/javascript" src="resources/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="resources/js/common.js"></script>
<script type="text/javascript" src="jquery.md5.js"></script>
<script type="text/javascript" src="managers/saveOrModifyManager.js"></script>
<script type="text/javascript" src="../getKey.js"></script>
<style type="text/css">
.ztree li {
	min-height: 5px;
}
</style>
</head>
<input type="hidden" value="${param.key}" id="key1"/>
<s:hidden value="%{#session.key}" id="key"/>
<body>
	<div class="cnt-wrap">
		<s:include value="../menus.jsp"></s:include>
		<div class="right-cnt-wrap f-fl">
			<ul class="tab-wrap bg">
				<li class="tab-crt">系统用户管理</li>
			</ul>
			<div class="f-fl left-list-wrap">
				<div id="div_cnt_table">
					<s:form id="myform" action="manager_save" method="post">
					<table id="tab-cnt" class="list-table">
						<tbody id="tb_cnt_body"  >
							<tr>
								<td align="center"><font color="red">*</font>账号</td>
								<td align="center"><s:textfield id="userName" name="manager.userName" cssClass="fm-text f-fl" style="width:235px;" /></td>
								<td align="center"><font color="red">*</font>密码</td>
								<td align="center">
									<s:password id="pwd" cssClass="fm-text f-fl" style="width:235px;" />
									<s:hidden id="password" name="manager.password" />
								</td>
							</tr>
							<tr>
								<td align="center"><font color="red">*</font>姓名</td>
								<td align="center"><s:textfield id="realName" name="manager.realName" cssClass="fm-text f-fl" style="width:235px;" /></td>
								<td align="center"><font color="red">*</font>性别</td>
								<td align="center"><s:select id="sex" name="manager.sex" list="#{'0':'男','1':'女'}" cssClass="fm-text f-fl" style="width:250px;" /></td>
							</tr>
							<tr>
								<td align="center"><font color="red">*</font>手机号码</td>
								<td align="center"><s:textfield id="tel" name="manager.tel" cssClass="fm-text f-fl" style="width:235px;" /></td>
								<td align="center"><font color="red">*</font>分配角色</td>
								<td align="left"><select id="roleId" name="roleId"
										style="width: 250px; height: 28px; padding: 4px 8px;" onchange="jueshe()"> </select>
								</td>
								
							</tr>
							<tr>
								<td align="center" colspan="4"><input id="button" type="submit" value="提交" class="normal-btn"></td>
							</tr>
						</tbody>
					</table>
					</s:form>
				</div>
				<div class="f-clear"></div>
			</div>
		</div>
		<div class="f-clear"></div>
	</div>
</body>
</html>