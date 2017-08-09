<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>掌上医院</title>
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" /> 
</head>
<!-- 判断用户是否登录，如果用户没有登录，则跳转到登录界面 -->
<s:if
	test="#session.userName==null">
	<script type="text/javascript">
		location.href = "login.jsp";
	</script>
</s:if>

<!--将index.jsp页面分成上下两大部分，第一部分的高度为70px?，作为首页的导航栏。下面一部分又分为两部分，
	第一部分用迭代器显示保存在session中的数据。第二部分应该是现实分页的功能？不太清楚
 -->
<frameset rows="70,*" frameborder="no" framespacing="0">
	<frame src="top.jsp" name="topFrame" noresize="noresize"/>
	<frameset cols="210,*">
	<frame src="menudata.jsp" name="menulist"/>
		<s:iterator value="#session.menu" var="menu" begin="0" end="0">
		<s:iterator value="#menu.submenus" var="submenu" begin="0" end="0">
	<frame src="<s:property value="#submenu.url.substring(3)"/><s:property value="#constants"/>" name="mainframe"/>
		</s:iterator>
	</s:iterator>
	</frameset>
	<noframes>
		<body>您的浏览器无法处理框架！
		</body>
	</noframes>
</frameset>
</html>