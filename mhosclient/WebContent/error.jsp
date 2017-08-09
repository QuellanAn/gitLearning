<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<head>
<base href="<%=basePath%>/" />
<title>系统异常</title>
<meta http-equiv="Content-Type" content="text/html; charset=UFT-8" />
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<style type="text/css">
.div_error_tips {
	float: left;
	height: 600px;
	width: 400px;
	left: 35%;
	top: 20px;
	border: 1px solid #cdcccc;
	position: absolute;
	text-align: center;
}

img {
	margin-top: 100px;
}

a {
	color: blue;
	cursor: pointer;
}
</style>
</head>
<body>

	<div class="div_error_tips">
		<img title="系统出错，请与管理员联系" src="resources/images/no_data.png" /> <br />
		<br /> <a onclick="history.go(-1);">返回一页</a> <br /> <b>系统出错，请与管理员联系.</b>
	</div>

</body>
</html>