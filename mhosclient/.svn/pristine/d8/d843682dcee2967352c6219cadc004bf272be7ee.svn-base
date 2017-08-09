<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%   
String path = request.getContextPath();   
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";   
%>   

<head>
<base href="<%=basePath%>/" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>发布资讯－添加、修改资讯</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/smoothness/jquery-ui.css"/>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/global.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/edit.css" />
<script charset="utf-8"
	src="${pageContext.request.contextPath}/kindeditor/kindeditor-min.js"></script>
<script charset="utf-8"
	src="${pageContext.request.contextPath}/kindeditor/lang/zh_CN.js"></script>
<script charset="utf-8"
	src="${pageContext.request.contextPath}/resources/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery/jquery-ui.js"></script>
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery/jquery.ui.datepicker-zh-CN.js"></script>
<script charset="utf-8"
	src="${pageContext.request.contextPath}/resources/js/common.js"></script>
<script charset="utf-8"
	src="${pageContext.request.contextPath}/resources/js/jquery/jquery.cookie.js"></script>
<script charset="utf-8"
	src="${pageContext.request.contextPath}/resources/js/fileuploader.js"></script>
<script charset="utf-8" src="enterprize/hosinfo/add_edit_hosinfo.js"></script>
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
</head>

<body>
	<div class="cnt-wrap">
		<s:include value="../../menus.jsp"></s:include>
		<div class="right-cnt-wrap f-fl">
			<ul class="tab-wrap bg">
				<li class="tab-crt">新发布</li>
			</ul>
			<s:form action="enterprize/hosinfo_addOrUpdate" method="post"
				enctype="multipart/form-data" theme="simple"
				id="form_add">
				<s:hidden name="updateaction" id="updateaction"></s:hidden>
				<s:hidden id="id" name="info.id"></s:hidden>
				<div class="tab-cnt">
					<div class="f-fl info-wrap">
						<div class="img-wrap">
							<div class="img-alt">封面图片</div>
							<img id="img_prev" height="158" width="288" alt="" src="enterprize/hosinfo_getimg?img=${info.image }" />
						</div>
					</div>
					<div class="f-fr edit-wrap" style="height: 600px;">
						<span class="icon arrow-icon f-ib"></span>
						<ul class="edit-fm">
							<li class="add-zIndex">
								<div class="edit-label"><span class="color-red">*</span>内容分类：</div>
								<s:select id="type"
									cssClass="edit-select f-fl" name="info.type"
									list="#{0:'院内咨询',1:'业内新闻'}" listKey="key" listValue="value" 
									headerKey="" headerValue="请选择">
								</s:select>
								<div class="f-clear"></div>
							</li>
							<li>
								<div class="edit-label">
									<span class="color-red">*</span>标题：
								</div> <s:textfield name="info.title" id="title" cssClass="fm-text" maxlength="45"></s:textfield>
								<s:fielderror fieldName="title"></s:fielderror>
							</li>
							<li>
								<div class="edit-label">
									<span>图片：</span><span class="pmt f-fr">图片建议尺寸：360像素*200像素</span>
									<div class="f-clear"></div>
								</div> <s:file id="file" name="files"  ></s:file>
							</li>
							<li>
								<div class="edit-label">
									<span class="color-red">*</span>正文：
								</div>
								<div class="article-edit" style="height: 350px;">
									<s:textarea id="content" name="info.content" cssClass="edit-textarea article-textarea" cssStyle="height: 350px;"/>
								</div>
							</li>
						</ul>
					</div>
					<div class="f-clear"></div>
				</div>
				<div class="btn-wrap">
					<s:submit value="预览" id="btn_prev"
						cssClass="edit-btn preview-btn f-fl"></s:submit>
					<s:submit value="提交" id="btn_submit" cssClass="edit-btn save-btn f-fl"></s:submit>
				</div>
			</s:form>
		</div>
		<div class="f-clear"></div>
	</div>
</body>
</html>