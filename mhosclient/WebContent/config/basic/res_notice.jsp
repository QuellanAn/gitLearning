<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<title>挂号须知</title>
<base href="<%=basePath%>/" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="resources/css/index.css" />
<link rel="stylesheet" type="text/css" href="resources/css/index.css" />
<script charset="utf-8" src="kindeditor/kindeditor-min.js"></script>
<script charset="utf-8" src="kindeditor/lang/zh_CN.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="resources/js/common.js"></script>
<script type="text/javascript" src="../getKey.js"></script>
<script type="text/javascript" src="config/basic/regnotice.js"></script>
</head>
<!-- <s:if
	test="#session.userName==null ||!#session.authority.contains('ROLE_BASIC_CON')">
	<script type="text/javascript">
		location.href = $('base').attr('href')+"login.jsp";
	</script>
</s:if> -->
<input type="hidden" value="${param.key}" id="key1"/>
<s:hidden value="%{#session.key}" id="key"/>
<body>
	<div class="cnt-wrap">
		<s:include value="../../menus.jsp"></s:include>
		<div class="right-cnt-wrap f-fl">
			<ul class="tab-wrap bg">
				<li><a href="../config/wechat/basecontent_cfg.jsp">就医指南</a></li>
				<li class="tab-crt"><a href="config/basic/res_notice">挂号须知</a></li>
				<li><a href="../config/basic/help.jsp">问题与帮助</a></li>
				<!-- <li><a href="config/basic/guide">就医指南</a></li> -->
				<!-- <li><a href="config/basic/fun">操作指南</a></li> -->
				
			</ul>

			<div class="tab-cnt">
				<div class="f-fl left-list-wrap">
					<div class="left-list-hd">
						<span class="f-fl" id="title_guide">预约挂号须知</span>
					</div>
					<div id="div_cnt_table" style="margin-left: 250px;">
						<s:form id="addResNoticeForm" method="post" theme="simple">
							<s:hidden id="action" value="save" />
							<s:hidden id="noticeId" name="noticeId" value="1" />
							<textarea name="notice_content" id="notice_content" rows="25" cols="80"></textarea>
							<br />
							<center>
								<input id="resNotice_submit" value="保存" type="submit"
									class="normal-btn" />
							</center>
						</s:form>
					</div>
				</div>
				<div id="div_info_cat" class="f-fl right-list-wrap"
					style="min-height: 350px;margin-top: -433px;float: left;">
						<div class="right-list-hd" style="padding-left: 15px;cursor: default;">
							<strong>挂号须知分类</strong>
						</div>
						<div class="right-list-item" style="padding-left: 20px;cursor: pointer; background-color: #0079ff; "
							id="res_yygh">预约挂号须知</div>
						<div class="right-list-item" style="padding-left: 20px;cursor: pointer;"
							id="res_ddgh">当日挂号须知</div>
						<div class="f-clear"></div>
				</div>
				<div class="f-clear"></div>
			</div>
		</div>
		<div class="f-clear"></div>
	</div>
</body>
</html>