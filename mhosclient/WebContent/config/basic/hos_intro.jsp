<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*, com.catic.mobilehos.service.vo.*"%>
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
<base href="<%=basePath%>/" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>医院介绍</title>
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="resources/css/introduction.css" />
<script charset="utf-8" src="resources/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="../getKey.js"></script>
<script charset="utf-8" src="config/basic/hos_intro.js"></script>
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
				<li class="tab-crt"><a href="config/basic/showHosIntroCfgPage">医院介绍</a></li>
				<!-- <li><a href="config/basic/res_notice">预约挂号须知</a></li> -->
				<!-- <li><a href="config/basic/guide">就医指南</a></li> -->
				<!-- <li><a href="config/basic/fun">操作指南</a></li> -->
				<!-- <li><a href="config/basic/faq">常见问题</a></li> -->
			</ul>
			<div class="tab-cnt" style="overflow: auto; height: 1800px;">
				<p class="introduct-p">
					<span>医院名称：</span><span><s:text
							name="hosIntroVO.hospitalName"></s:text></span>
				</p>
				<p class="introduct-p">
					<span>医院级别：</span><span><s:text name="hosIntroVO.level"></s:text></span>
				</p>
				<div class="img-wrap">
					<div class="f-fl img-label">医院图片：</div>
					<div class="hospital-img f-fl">
						<img alt="" src="config/basic/getHosImage" width="290px"
							height="160px" alt="医院图片" title="医院图片">
					</div>
					<div class="f-clear"></div>
				</div>
				<p class="introduct-p">
					<span>医院地址：</span><span><s:text name="hosIntroVO.address"></s:text></span>
				</p>
				<p class="introduct-p">
					<span>官方网址：</span><a href="<s:text name="hosIntroVO.website"/>"
						target="_blank"><s:text name="hosIntroVO.website"></s:text></a>
				</p>
				<p class="introduct-p" style="width: 280px;">
						<span style="float: left;">联系方式：</span>
						<span style="float: right;"><s:text
							name="hosIntroVO.phone"></s:text></span>
					<!-- <s:text
							name="hosIntroVO.contactsDesc"></s:text></span> -->
				</p>
				<p class="introduct-p">
					<span>特色科室：</span><span><s:text
							name="hosIntroVO.featureDepartment"></s:text></span>
				</p>
				<p class="introduct-p">
					<span class="f-fl">医院介绍：</span><br /> <span
						class="text-indent f-fl"><s:text
							name="hosIntroVO.introduction"></s:text></span>
				</p>

				<p class="introduct-p">
					<span>乘车线路：</span> <span><s:text  name="hosIntroVO.busLine"></s:text></span>
				</p>
				<%-- <p class="introduct-p">
					<span>GPS经度：</span> <span><s:text
							name="hosIntroVO.longitude"></s:text></span>
				</p>
				<p class="introduct-p">
					<span>GPS纬度：</span> <span><s:text name="hosIntroVO.latitude"></s:text></span>
				</p>
				<p class="introduct-p">
					<span>GPS地点：</span> <span><s:text name="hosIntroVO.gpsPlace"></s:text></span>
				</p> --%>
			</div>
			<center>
				<button
					onclick="javascript:window.location.href='config/basic/showHosIntroCfgEditPage';"
					class="normal-btn">编辑</button>
			</center>
		</div>
		<div class="f-clear"></div>
	</div>
</body>
</html>