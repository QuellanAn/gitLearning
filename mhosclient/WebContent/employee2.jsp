<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>" />
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="resources/css/index.css" />
<script charset="utf-8" src="resources/js/jquery/jquery-1.10.2.js"></script>
<script charset="utf-8" src="resources/js/common.js"></script>
<div class="menu f-fl">
	<div class="menu-item">
		<div class="menu-item-hd open-icon icon">
			<ul class="menu-item-cnt " style="display: block;">员工信息管理						
				<li ><a href="employee/employee_add.jsp" target="mainframe" >增加新员工</a></li>
				<li ><a href="employee/employee_main.jsp" target="mainframe" >员工信息管理</a></li>
				<li >aa</li>
			</ul>
		</div>
	</div>
	<div class="f-clear"></div>
</div>
