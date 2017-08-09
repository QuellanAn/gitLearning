<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<link id="skin" rel="stylesheet" href="resources/jBox/Skins2/Blue/jbox.css" />
<script charset="utf-8" src="resources/js/jquery/jquery-1.7.2.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="resources/jBox/i18n/jquery.jBox-zh-CN.js"></script>
  <head>
  	<script type="text/javascript">
  		$(document).ready(function() {
  			$.jBox.tip("因您长时间未操作，请重新登录!","info");
  		});
  	</script>
  </head>
  <body>
  	<s:include value="login.jsp"></s:include>
  </body>
</html>
