<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<head>
<base href="<%=basePath%>/" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加、修改信息</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/smoothness/jquery-ui.css" />
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
<script charset="utf-8"
	src="${pageContext.request.contextPath}/hosinfomng/employee_update.js"></script>
<script>
	//var infoid = "<%=request.getAttribute("employeeId")%>";
	var editor;
	KindEditor.ready(function(K) {
		editor = K.create('textarea[name="content"]', {
			uploadJson : 'hosinfomng/uploadHosPubInfoHtmlImg?infoid=' + infoid,
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
		<s:include value="../menus.jsp"></s:include>
		<div class="right-cnt-wrap f-fl">
			<ul class="tab-wrap bg">
				<li class="tab-crt"><a href="employee/manage_employee.jsp">员工信息管理</a>
				</li>
				<li><a href="employee/employee_add.jsp">添加新员工</a>
				</li>
				<li><a href="employee/employee_manage.jsp">设置</a>
				</li>
			</ul>

			<form action="employeesAction/updateEmployeeSubmit" method="post"
				id="form_add">
				<div class="btn-wrap">
					<div class="f-fl info-wrap">
						<span class="icon arrow-icon f-ib"></span>
				<table>
					<tr>
						<td><span class="color-red">*</span>员工ID&nbsp;&nbsp;：</td>
						<td>
						<div class="edit-label">	
							<div class="edit-label">
								<s:property value="employeePO.employeeId"/>
								<input type="hidden" name="employeePO.employeeId" value="<s:property value="employeePO.employeeId"/>">
							</div> 
						</div>
						</td>
					</tr>
					<tr>
						<td>员工姓名：</td>
						<td>
						<div class="edit-label">
							<input type="text" name="employeePO.employeeName"
							value="<s:property value="employeePO.employeeName"/>">
						</div>
						</td>
					</tr>
					<tr>
						<td>员工密码：</td>
						<td>
							<div class="edit-label">
							<input type="password" name="employeePO.password"
							value="<s:property value="employeePO.password"/>">
							</div>
						</td>
					</tr>
					<tr>
						<td>员工性别：</td>
						<td>
							<div class="edit-label">
								<select name="employeePO.sex" style="width:100px;" cssClass="edit-select f-fl">
									<option value="<s:property value="employeePO.sex"/>"><s:property value="employeePO.sex"/></option>
									<option value="男">男</option>
									<option value="女">女</option>
								</select>
							</div>
							<div class="f-clear"></div>
						</td>
					</tr>
					<tr>
						<td>员工部门：</td>
						<td>
							<div class="edit-label">
								<select name="employeePO.department" style="width:100px;" cssClass="edit-select f-fl">
									<option value="<s:property value="employeePO.department"/>"><s:property value="employeePO.department"/></option>
									<option value="打印机">打印机</option>
									<option value="智能自助">智能自助</option>
									<option value="物联网">物联网</option>
									<option value="数据平台">数据平台</option>
									<option value="软件部">软件部</option>
								</select>
							</div>
							<div class="f-clear"></div>
						</td>
					</tr>
					<tr>
						<td>员工工资：</td>
						<td><input type="text" name="employeePO.salary"
							value="<s:property value="employeePO.salary"/>">
						</td>
					</tr>			
				</table>
				<div class="btn-wrap">					
					<s:submit value="提交" style="margin-left:70px;"
						cssClass="edit-btn save-btn f-fl"></s:submit>
				</div>
				</div>
				</div>
			</form>

		</div>
		<div class="f-clear"></div>
	</div>
</body>
</html>