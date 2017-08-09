<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>" />
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="resources/css/index.css" />
<script charset="utf-8" src="resources/js/jquery/jquery-1.10.2.js"></script>
<script charset="utf-8" src="resources/js/common.js"></script>
<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.7.2.min.js "></script>
<script type="text/javascript">
$(document).ready(function() {
		var val = false;
		var number=0;			
		$('.radio').click(function(e) {
			val = true;
			number=e.target.value;
		});
		
		$('#updateEmployee').click(function(){
		if (val==false) {
				alert("请选择需要修改的员工");
				return false;
			} else {
				$('#form').attr("action", "employeesAction/updateNewEmployee");
				$('#form').submit();
			}
		});
		
		$('#deleteEmployee').click(function(){
		if (val==false) {
				alert("请选择需要删除的员工");
				return false;
			} else {
				if (!confirm("确定要删除该员工吗?")) {
					return false;
				}else{
					$('#form').attr("action", "employeesAction/deleteNewEmployee");
					$('#form').submit();
				}
				
			}
		});
		$('#deleteEmployee1').click(function(){		
			$.ajax({
   			url: "employeesAction/deleteNewEmployee?selectOrderNumber="+number,
   			/* data: number, */
   			success: function(data){
   				//window.location.href = "./selectEmployees";
   				//window.location.href ="./selectEmployees";
   				
   			}
 });
		});
	});
		
	
</script>
</head>
<body>
<div class="menu f-fl">
	<div class="menu-item">
		<div class="menu-item-hd open-icon icon">
			<ul class="menu-item-cnt" style="display: block;">员工信息管理
				<li class="f-ib">aa</li>
				<li class="f-ib">aa</li>
				<li class="f-ib">aa</li>
			</ul>
		</div>
	</div>
</div>

	<form action="employeesAction/getEmployeeList" method="post" id="form">
		<input type="submit" value="查询" style="margin: 10px;">
		<input type="button" value="修改" style="margin: 10px;" id="updateEmployee">
		<!-- <input type="submit" value="删除" style="margin: 10px;" id="deleteEmployee">	 -->
		<input type="button" value="删除" style="margin: 10px;" id="deleteEmployee1">
	<table border="1px solid" style="padding: 10px;border-collapse: collapse;">
		<tr>
			<td></td>
			<td width="80px">员工Id</td>
			<td width="80px">员工姓名</td>
			<td width="80px">员工性别</td>
			<td width="80px">员工部门</td>
			<td width="80px">员工工资</td>
		</tr>
		<s:iterator var="result" value="employeePOList" >
			<tr>
				<td width="20px">
					<input type="radio"name="selectOrderNumber" class="radio"
					value="<s:property value="#result.employeeId"/>" />
				</td>
				<td width="80px"><s:property value="#result.employeeId"/></td>
				<td width="80px"><s:property value="#result.employeeName"/></td>
				<td width="80px"><s:property value="#result.sex"/></td>
				<td width="80px"><s:property value="#result.department"/></td>
				<td width="80px"><s:property value="#result.salary"/></td>		
			</tr>
		</s:iterator>
		
	</table>
	</form>
	<br><br>
	<form action="employeesAction/selectEmployeesByName" method="post" id="form1">
		<input type="text" name="employeesName">
		<input type="submit" value="查询">
	
	<table border="1px solid" style="padding: 10px;border-collapse: collapse;">
		<tr>
			<td width="80px">员工Id</td>
			<td width="80px">员工姓名</td>
			<td width="80px">员工性别</td>
			<td width="80px">员工部门</td>
			<td width="80px">员工工资</td>			
		</tr>
		<s:iterator var="result" value="employeePOList1" >
			<tr>			
				<td width="80px"><s:property value="#result.employeeId"/></td>
				<td width="80px"><s:property value="#result.employeeName"/></td>
				<td width="80px"><s:property value="#result.sex"/></td>
				<td width="80px"><s:property value="#result.department"/></td>
				<td width="80px"><s:property value="#result.salary"/></td>
					
			</tr>
		</s:iterator>
		
	</table>
	</form>
	<br><br>
	addEmployee
	添加新的员工：
	<form action="employeesAction/addNewEmployee" method="post">
		<table>
			<tr>
			<td>员工姓名：</td>
			<td><input type="text" name="employeePO.employeeName"></td>
			</tr>
			<tr>
			<td>员工性别：</td>
			<td> <!-- <input type="text" name="employeePo.sex"> -->
				<%-- <%
					HashMap<String Object> map1=new HashMap<String,Object>();
					map1.put("", "请选择");
					map1.put("1", "男");
					map1.put("2", "女");
					session.setAttribute("situation", map1);
				%> <s:select list="#session.situation" 
				name="employeePO.sex">
				</s:select> --%>
				<select name="employeePO.sex"style="width:50px;">
					<option value="男">男</option>
					<option value="女">女</option>
				</select>				
			</td>
			</tr>
			<tr>
			<td>员工部门：</td>
			<td><!-- <input type="text" name="employeePO.department"> -->
				<select name="employeePO.department"style="width:100px;">
					<option value="打印机">打印机</option>
					<option value="智能自助">智能自助</option>
					<option value="物联网">物联网</option>
					<option value="数据平台">数据平台</option>
					<option value="软件公司">软件公司</option>
				</select>	
			</td>
			</tr>
			<tr>
			<td>员工工资：</td>
			<td><input type="text" name="employeePO.salary"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="添加"></td>
			</tr>
		</table>
	</form>
</body>

</html>