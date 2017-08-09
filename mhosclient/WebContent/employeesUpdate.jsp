<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
修改的员工信息：
	<form action="employeesAction/updateEmployeeSubmit" method="post">
		<table>
		<tr>
			<td>员工编号：</td>
			<td><input type="text" name="employeePO.employeeId" value="<s:property value="employeePO.employeeId"/>" readonly="readonly"></td>
			</tr>
			<tr>
			<td>员工姓名：</td>
			<td><input type="text" name="employeePO.employeeName" value="<s:property value="employeePO.employeeName"/>"></td>
			</tr>
			<tr>
			<td>员工密码：</td>
			<td><input type="password" name="employeePO.password" value="<s:property value="employeePO.password"/>"></td>
			</tr>
			<tr>
			<td>员工性别：</td>
			<td><input type="text" name="employeePO.sex" value="<s:property value="employeePO.sex"/>"></td>
			</tr>
			<tr>
			<td>员工部门：</td>
			<td>
			<%-- <select name="employeePO.department"style="width:100px;">
					<option value="<s:property value="employeePO.department"/>"><s:property value="employeePO.department"/></option>
					<option value="打印机">打印机</option>
					<option value="智能自助">智能自助</option>
					<option value="物联网">物联网</option>
					<option value="数据平台">数据平台</option>
					<option value="软件公司">软件公司</option>
				</select> --%>	
			<input type="text" name="employeePO.department" value="<s:property value="employeePO.department"/>">
			</td>
			</tr>
			<tr>
			<td>员工工资：</td>
			<td><input type="text" name="employeePO.salary" value="<s:property value="employeePO.salary"/>"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="修改"></td>
			</tr>
		</table>
	</form>
</body>
</html>