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
<title>添加角色</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="resources/css/main.css" />
<link rel="stylesheet" type="text/css" href="resources/css/introduction-edit.css" />
<link rel="stylesheet" href="resources/css/zTreeStyle/zTreeStyle.css" />
<script type="text/javascript" src="resources/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="resources/js/common.js"></script>
<script type="text/javascript" src="../getKey.js"></script>
<script type="text/javascript" src="role/modifyRole.js"></script>
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
				<li class="tab-crt"><font color="red">*</font>角色管理</li>
			</ul>
			<div class="f-fl left-list-wrap">
				<div id="div_cnt_table">
					<s:form id="myform" action="role_modify" method="post">
					<s:hidden name="role.roleId" id="id"/>
					<s:hidden name="role.ids" id="ids" />
					<table id="tab-cnt" class="list-table">
						<tbody id="tb_cnt_body"  >
							<tr>
								<td align="center"><font color="red">*</font>角色名称</td>
								<td align="center"><s:textfield id="name" name="role.name" cssClass="fm-text f-fl" style="width:250px;" /></td>
							</tr>
							<%-- <tr>
								<td align="center">角色类别</td>
								<td align="left"><s:radio id="category" name="role.category" list="#{'1':'超级管理员','3':'服务商管理员','4':'银行管理员'}" cssStyle="width:30px;"  value="role.category"/></td>
							</tr> --%>
							<tr>
								<td align="center"><font color="red">*</font>角色描述</td>
								<td align="center"><s:textarea id="depict" name="role.depict"  cssClass="fm-text introduction-textarea1 f-fl" style="width:250px;"/></td>
							</tr>
							<tr>
								<td align="center"><font color="red">*</font>分配权限</td>
								<td align="center">
									<div
										style="float: left; height: 250px; width: 260px; border: 1px solid #cdcccc; overflow: auto;">
										<ul id="ztree" class="ztree"></ul>
									</div>
								</td>
							</tr>
							<tr>
								<td align="center" colspan="2"><input id="button" type="submit" value="提交" class="normal-btn"></td>
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