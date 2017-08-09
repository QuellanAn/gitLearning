<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>系统登录</title>
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" /> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/login.css" />
<link id="skin" rel="stylesheet" href="resources/jBox/Skins2/Blue/jbox.css" />
<script charset="utf-8" src="resources/js/jquery/jquery-1.7.2.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="resources/jBox/i18n/jquery.jBox-zh-CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/login.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery.md5.js"></script>
<script type="text/javascript">
if (window.top!=window.self){
        window.top.location="login.jsp";
    }
</script>
<style type="text/css">
	input:-webkit-autofill { 
	-webkit-box-shadow: 0 0 0px 1000px #FBFBFB inset; 
	}
</style>
</head>
<body class="f-oh">
	<div class="login-bg">
		<img src="resources/images/login-bg.jpg" alt="系统登录" />
		<div class="login-fm-wrap">
			<img src="resources/images/login-logo.png" alt="掌上医院|登录"
				title="登录--掌上医院" />
			<form id="loginForm" class="login-fm" action="" name="" method="post">
				<div class="fm-txt-wrap">
					<div class="fm-txt fm-txt-up">
						<span class="fm-label f-fl"> 账号 </span> <input id="userName"
							name="userName" type="text" class="fm-text f-fl"
							placeholder="请输入您的账号" value=""/>
					</div>
					<div class="fm-txt fm-txt-down">
						<span class="fm-label f-fl"> 密码 </span> <input id="password"
							name="password" type="password" class="fm-text f-fl"
							placeholder="请输入您的密码" value=""/>
					</div>
				</div>
				<input type="submit" value="登录" class="fm-submit" />
				<font color="red"><s:fielderror /></font>
			</form>
		</div>
	</div>
</body>
</html>