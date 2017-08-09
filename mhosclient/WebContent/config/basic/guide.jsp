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
<title>就医指南</title>
<base href="<%=basePath%>/" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="resources/css/index.css" />
<script charset="utf-8" src="kindeditor/kindeditor-min.js"></script>
<script charset="utf-8" src="kindeditor/lang/zh_CN.js"></script>
<script type="text/javascript"
	src="resources/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="resources/js/common.js"></script>
<script type="text/javascript" src="config/basic/guide.js"></script>
</head>
<!-- <s:if
	test="#session.userName==null ||!#session.authority.contains('ROLE_BASIC_CON')">
	<script type="text/javascript">
		location.href = $('base').attr('href')+"login.jsp";
	</script>
</s:if> -->
<body>
	<div class="cnt-wrap">
		<s:include value="../../menus.jsp"></s:include>
		<div class="right-cnt-wrap f-fl">
			<ul class="tab-wrap bg">
				<li><a href="config/basic/showHosIntroCfgPage">医院介绍</a></li>
				<li><a href="config/basic/res_notice">预约挂号须知</a></li>
				<li class="tab-crt"><a href="config/basic/guide">就医指南</a></li>
				<!-- <li><a href="config/basic/fun">操作指南</a></li>
				<li><a href="config/basic/faq">常见问题</a></li> -->
			</ul>
			<div class="tab-cnt">
				<div class="f-fl left-list-wrap">
					<div class="left-list-hd">
						<span class="f-fl" id="title_guide">挂号须知</span>
					</div>
					<div id="div_cnt_table">
						<s:hidden id="action" value="save" />
						<s:form id="addGuideForm" method="post" theme="simple">
							<s:hidden id="cfg_type" name="cfg_type" value="1" />
							<s:hidden id="cat" name="cat" value="1" />
							<s:hidden id="cat_name" name="cat_name" value="挂号须知" />
							<s:hidden id="htmlFileName" name="htmlFileName"
								value="getregnotice.html" />
							<textarea name="content" id="content" rows="25" cols="80"></textarea>
							<br />
							<center>
								<input id="guide_submit" value="保存" type="submit"
									class="normal-btn" />
							</center>
						</s:form>
					</div>
				</div>
				<div id="div_info_cat" class="f-fl right-list-wrap"
					style="min-height: 395px;">
					<div class="right-list-hd" style="padding-left: 15px;cursor: default;">
						<strong>就医须知分类</strong>
					</div>
					<div class="right-list-item" style="padding-left: 20px;cursor: pointer;"
						id="reg_guide">挂号须知</div>
					<div class="right-list-item" style="padding-left: 20px;cursor: pointer;"
						id="doc_duide">就诊须知</div>
					<div class="right-list-item" style="padding-left: 20px;cursor: pointer;"
						id="med_guide">取药须知</div>
					<div class="right-list-item" style="padding-left: 20px;cursor: pointer;"
						id="exit_guide">退药须知</div>
					<div class="right-list-item" style="padding-left: 20px;cursor: pointer;"
						id="hos_guide">住院须知</div>
					<div class="f-clear"></div>
				</div>
				<div class="f-clear"></div>
			</div>
		</div>
		<div class="f-clear"></div>
	</div>
</body>
</html>