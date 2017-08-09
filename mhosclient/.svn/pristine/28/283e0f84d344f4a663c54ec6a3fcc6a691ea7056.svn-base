<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>掌上医院后台管理系统</title>
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" /> 
</head>
<s:if
	test="#session.userName==null">
	<script type="text/javascript">
		location.href = "login.jsp";
	</script>
</s:if>
<s:set value="'?key=0'" var="constants" />
<frameset rows="70,*" frameborder="no" framespacing="0">
	<frame src="top.jsp" name="topFrame" noresize="noresize" scrolling="no"/>
	<s:iterator value="#session.menu" var="menu" begin="0" end="0">
		<s:iterator value="#menu.submenus" var="submenu" begin="0" end="0">
			<frame src="<s:property value="#submenu.url.substring(3)"/><s:property value="#constants"/>" name="content" />
		</s:iterator>
	</s:iterator>
	<noframes>
		<body>您的浏览器无法处理框架！
		</body>
	</noframes>
</frameset>
</html>