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
<title>问题与帮助编辑</title>
<base href="<%=basePath%>/" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="resources/css/smoothness/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="resources/css/introduction-edit.css" />
<script charset="utf-8" src="kindeditor/kindeditor-min.js"></script>
<script charset="utf-8" src="kindeditor/lang/zh_CN.js"></script>
<script type="text/javascript"
	src="resources/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="resources/js/common.js"></script>
<script charset="utf-8" src="config/wechat/add_edit_helpanswer.js"></script>
<script type="text/javascript" src="../getKey.js"></script>
</head>
<!-- <s:if
	test="#session.userName==null ||!#session.authority.contains('ROLE_BASIC_CON')">
	<script type="text/javascript">
		location.href = $('base').attr('href') + "login.jsp";
	</script>
</s:if> -->
<input type="hidden" value="${param.key}" id="key1"/>
<s:hidden value="%{#session.key}" id="key"/>
<body>
	<div class="cnt-wrap">
		<s:include value="../../menus.jsp"></s:include>
		<div class="right-cnt-wrap f-fl">
			<ul class="tab-wrap bg">
				<li><a href="config/wechat/basecontent_cfg.jsp">就医指南</a></li>
				<li><a href="config/basic/res_notice">挂号须知</a></li>
				<li class="tab-crt"><a href="config/basic/help.jsp">问题与帮助</a></li>
			</ul>

			<div class="tab-cnt" style="overflow: auto;height: 450px;">

			<s:form method="post" id="saveHelpForm" action="config/basic/saveHelp"
					cssClass="introduction-fm">
					<s:hidden id="helpId" name="helpId"></s:hidden>
					<ul>
						<li><span class="introduction-label f-fl"> <span
								class="color-red">*</span>问题： </span> <s:textfield
								id="question" name="question"
								cssClass="fm-text f-fl" style="width:550px;" />
							<div class="f-clear"></div></li>

						<li><span class="introduction-label f-fl"><span
								class="color-red">*</span>答案： </span> <s:textarea
								id="answer" name="answer"
								cssClass="fm-text introduction-textarea2 f-fl"></s:textarea>
							<div class="f-clear"></div></li>

					</ul>

					<br />
					<center>
						<input id="saveHelp" type="button" value="提交"
							class="normal-btn" onclick="" />
					</center>

			</s:form>
			</div>

		</div>
		<div class="f-clear"></div>
	</div>
</body>



<script type="text/javascript">
	$("#saveHelp").click(function(e) {
		var question = $("#question").val();
		var answer = $("#answer").val();
		if ($.trim(question).length == 0
				|| $.trim(answer).length == 0) {
			alert("请输入完整的信息");
			return false;
		}
		
		var f = this.form;
			f.action = 'config/basic/AddUpdateHelp';
		f.target = '_self';
		f.submit();
	});
	var editor;
	editor = KindEditor.create('textarea[id="answer"]', {
		resizeType : 0,
		width : '557px',
		minWidth : '300px',
		height : '260px',
		allowPreviewEmoticons : false,
		allowImageUpload : false,
		//同步值
		afterBlur : function() {
			this.sync();
		},
		items : [ 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor',
				'bold', 'italic', 'underline', 'removeformat', '|',
				'justifyleft', 'justifycenter', 'justifyright',
				'insertorderedlist', 'insertunorderedlist' ]
	});
</script>
</html>