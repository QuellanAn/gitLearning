<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>新增医护人员</title>
<base href="${pageContext.request.contextPath}/" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/global.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/introduction-edit.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/common.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/manager/serUser/serUser_add.js"></script>
</head>
<body>
	<div class="cnt-wrap">
		<s:include value="../../menu.jsp"></s:include>
		<div class="right-cnt-wrap f-fl">
			<ul class="tab-wrap bg">
				<li class="tab-crt"><a href="${pageContext.request.contextPath}/manager/serUser/serUser.jsp">医护人员管理</a></li>

			</ul>
			<div class="tab-cnt" style="overflow: auto;">
				<s:form id="serUserAddForm" action="manager/serUser/addOpenFireUser" method="post"
					theme="simple" cssClass="introduction-fm">
					<s:hidden id="groupName" name="groupName" value="doctor"></s:hidden>
					<ul>
						<li><span class="introduction-label f-fl"><span
								class="color-red">*</span>用户名:</span> <s:textfield id="username"
								name="username" cssClass="fm-text f-fl" />
							<div class="f-clear"></div>
							<p class="introduction-pmt">账号名应由6－12位数字、字母或下划线组成！</p></li>

						<li><span class="introduction-label f-fl">名称:</span> <s:textfield
								id="name" name="name" cssClass="fm-text f-fl" maxLength="20" />
						</li>

						<li><span class="introduction-label f-fl"><span
								class="color-red">*</span>密码:</span> <s:password id="encryptedPassword"
								name="encryptedPassword" cssClass="fm-text f-fl" maxLength="20" />
							<div class="f-clear"></div>
							<p class="introduction-pmt">密码应由6－12位数字、字母或下划线组成！</p></li>

						<li><span class="introduction-label f-fl"><span
								class="color-red">*</span>确认密码:</span> <s:password
								id="reencryptedPassword" maxLength="20" cssClass="fm-text f-fl" /></li>

						<li><span class="introduction-label f-fl">email:</span> <s:textfield
								id="email" name="email" cssClass="fm-text f-fl" /></li>

					</ul>
					<br />
					<center>
						<input type="submit" id="addButton" value="提交" class="normal-btn">
					</center>
				</s:form>
			</div>
		</div>
		<div class="f-clear"></div>
	</div>
</body>
</html>