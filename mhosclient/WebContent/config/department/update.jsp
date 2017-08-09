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
<title>科室编辑</title>
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
<script type="text/javascript" src="../getKey.js"></script>
<script type="text/javascript" src="resources/js/common.js"></script>
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
				<li class="tab-crt">科室介绍管理</li>
			</ul>

			<div class="tab-cnt" style="overflow: auto;">

			<s:form method="post" action="config/department/saveDepartment"
					id="saveDepartmentForm" cssClass="introduction-fm">
					<ul>
						<li><span class="introduction-label f-fl"> <span
								class="color-red">*</span>科室名称： </span> <s:hidden id="form_id"
								name="departmentVO.id" cssClass="fm-text1 f-fl" /> <s:textfield
								id="form_departmentName" name="departmentVO.departmentName"
								cssClass="fm-text f-fl" style="width:550px;" />
							<div class="f-clear"></div></li>

						<li><span class="introduction-label f-fl"> <span
								class="color-red">*</span>科室编号： </span> <s:textfield
								id="form_departmentId" name="departmentVO.departmentId"
								Class="fm-text f-fl" style="width:550px;" class="fm-text f-fl"/>
							<div class="f-clear"></div></li>

						<li><span class="introduction-label f-fl"> <span
								class="color-red">*</span>上级科室： </span> <s:select id="form_parentId"
								name="departmentVO.parentId" list="%{departmentVOList}"
								listKey="%{departmentId}" listValue="%{departmentName}"
								headerKey="0" headerValue="无" cssClass="fm-text f-fl"
								style="width:240px;" />
								<span class="introduction-label f-fl" style="margin-left: 25px;"><span class="color-red">*</span>科室状态： </span> <s:select id="department_status"
								name="departmentVO.department_status" list="#{1:'正常',2:'冻结'}"
								listKey="key" listValue="value" cssClass="fm-text f-fl" style="width:205px;" />
							<div class="f-clear"></div></li>

						<li><span class="introduction-label f-fl"> 联系方式： </span> <s:textfield
								id="form_phone" name="departmentVO.phone" Class="fm-text f-fl" style="width:550px;" class="fm-text f-fl"/>
							<div class="f-clear"></div></li>

						<li><span class="introduction-label f-fl"> 科室位置： </span> <s:textfield
								id="form_departmentAddr" name="departmentVO.departmentAddr"
								Class="fm-text f-fl" style="width:550px;" class="fm-text f-fl"/>
							<div class="f-clear"></div></li>

						<li><span class="introduction-label f-fl"> <span
								class="color-red">*</span>科室排序： </span> <s:textfield
								id="form_display_no" name="departmentVO.display_no"
								Class="fm-text f-fl" style="width:550px;" class="fm-text f-fl"/>
							<div class="f-clear"></div></li>

						<li><span class="introduction-label f-fl"> 科室介绍： </span> <s:textarea
								id="form_introduction" name="departmentVO.introduction"
								cssClass="fm-text introduction-textarea2 f-fl"></s:textarea>


							<div class="f-clear"></div></li>

					</ul>

					<br />
					<center>
						<input id="saveDepartment" type="button" value="提交"
							class="normal-btn" onclick="" />
					</center>

			</s:form>



			</div>

		</div>
		<div class="f-clear"></div>
	</div>
</body>



<script type="text/javascript">
	$("#saveDepartment").click(
			function(e) {

				var form_id = $("#form_id").val();
				var form_departmentName = $("#form_departmentName").val();
				var form_departmentId = $("#form_departmentId").val();
				var form_parentId = $("#form_parentId").val();
				var form_departmentAddr = $("#form_departmentAddr").val();
				var form_introduction = $("#form_introduction").val();
				var form_display_no = $("#form_display_no").val();
				if ($.trim(form_departmentName).length == 0
						|| $.trim(form_departmentId).length == 0
						|| $.trim(form_display_no).length == 0) {
					alert("请输出完整的信息");
					return false;
				}

				$.post("config/department/validateCode", {
					id : form_id,
					departmentId : form_departmentId,
				}, function(data, status) {

					if (data) {
						data = eval("(" + data + ")");
						alert(data.msg);
						return false;
					} else {
						$("#saveDepartmentForm").submit();
					}
				});

			});
	var editor;
	editor = KindEditor.create('textarea[id="form_introduction"]', {
		resizeType : 0,
		width : '557px',
		minWidth : '300px',
		height : '350px',
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