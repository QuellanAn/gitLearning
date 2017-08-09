<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>发布资讯－新发布</title>
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="resources/css/index.css" />
<link id="skin" rel="stylesheet" href="resources/jBox/Skins2/Blue/jbox.css" />
<script charset="utf-8" src="resources/js/jquery/jquery-1.7.2.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="resources/jBox/i18n/jquery.jBox-zh-CN.js"></script>
<script charset="utf-8" src="resources/js/common.js"></script>
<script charset="utf-8" src="hosinfomng/hos_pub_info_main.js"></script>
</head>
<s:if test="#session.result!=null">
	<script type="text/javascript">
		$(document).ready(function() {
			<% session.removeAttribute("result"); %>
			loadInfo();
		});
	</script>
</s:if>
<!-- <s:if
	test="#session.userName==null ||!#session.authority.contains('ROLE_HOSINFO')">
	<script type="text/javascript">
		location.href = "../login.jsp";
	</script>
</s:if> -->
<body>
	<div class="cnt-wrap">
		<s:include value="../menus.jsp"></s:include>

		<div class="right-cnt-wrap f-fl">
			<ul class="tab-wrap bg">
				<li class="tab-crt"><a href="hosinfomng/hos_pub_info_main.jsp">新发布</a></li>
				<li><a href="hosinfomng/hos_pub_info_main_sp.jsp">发布审批</a></li>
				<li><a href="hosinfomng/hos_pub_info_main_pub.jsp">已发布</a></li>
			</ul>
			<div class="tab-cnt">
				<div class="f-fl left-list-wrap">
					<div class="left-list-hd">
						<span class="f-fl">未发布列表（<span id="totalcount"></span>）
						</span> <a href="javascript:void(0);" id="btn_add_pub" class="new-link f-fr">发布新内容</a>
					</div>
					<div id="div_cnt_table"></div>
					
				</div>
				<!-- <div id="div_info_cat" class="f-fl right-list-wrap"></div>  -->
				<div class="f-clear"></div>
			</div>
		</div>
		<div class="f-clear"></div>
	</div>
</body>
</html>