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
<title>添加院长开讲</title>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="resources/css/smoothness/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="resources/css/introduction-edit.css" />
<script charset="utf-8" src="kindeditor/kindeditor-min.js"></script>
<script charset="utf-8" src="kindeditor/lang/zh_CN.js"></script>
<script charset="utf-8" src="resources/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery-ui.js"></script>
<script type="text/javascript"
	src="resources/js/jquery/jquery.ui.datepicker-zh-CN.js"></script>
<script type="text/javascript" src="resources/js/common.js"></script>
<script charset="utf-8" src="enterprize/deanlecturer/lecturer_add.js"></script>
</head>
<script>
	var editor;
	KindEditor.ready(function(K) {
		editor = K.create('textarea[name="info.content"]', {
			resizeType : 0,
			minWidth : '200px',
			height : '350px',
			allowPreviewEmoticons : false,
			allowImageUpload : true,
			afterBlur : function() {
				this.sync();
			},
			items : [ 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor',
					'bold', 'italic', 'underline', 'removeformat', '|',
					'justifyleft', 'justifycenter', 'justifyright',
					'insertorderedlist', 'insertunorderedlist', '|',
					'emoticons', 'image', 'link' ]
		});
	});
</script>
<body>
	<div class="cnt-wrap">
		<div class="right-cnt-wrap f-fl">
			<ul class="tab-wrap bg">
				<li class="tab-crt">添加院长开讲</li>
			</ul>

			<div class="tab-cnt" style="overflow: auto;">

			<s:form method="post" action="enterprize/lecturer_addOrUpdate"  id="formadd" cssClass="introduction-fm">
					<ul>
						<li><span class="introduction-label f-fl" style="width: 130px;"> <span
								class="color-red">*</span>开讲院长：</span>
								<s:textfield
								id="author" name="info.author"
								cssClass="fm-text f-fl" style="width:150px;" />
								<span style="margin-left: 25px;" class="color-red">*</span>开讲时间：</span>
								<input name="starttime" id="starttime" type="text" value="${info.createtime.substring(0,10) }"
								style="width: 130px; height: 28px; line-height: 28px;" />
							<div class="f-clear"></div></li>

						<li><span class="introduction-label f-fl" style="width: 130px;"> <span
								class="color-red">*</span>开讲标题：</span> 
								<s:textfield
								id="title" name="info.title"
								Class="fm-text f-fl" style="width:400px;" class="fm-text f-fl"/>
							<div class="f-clear"></div></li>

						<li><span class="introduction-label f-fl" style="width: 130px;"> <span
								class="color-red">*</span>开讲内容：</span> <s:textarea id="content" name="info.content" cssClass="edit-textarea article-textarea" cssStyle="height: 300px;width:420px;"/>
							<div class="f-clear"></div></li>
						<li><span class="introduction-label f-fl" style="width: 130px;"> 
							<div class="f-clear"></div></li>
					</ul>
					<center>
						<input id="saveTer" type="submit"  value="提交"
							class="normal-btn" onclick="" />
					</center>
					<br>
			</s:form>
			</div>
		</div>
		<div class="f-clear"></div>
	</div>
</body>

</html>