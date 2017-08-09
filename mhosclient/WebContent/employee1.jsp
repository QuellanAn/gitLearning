<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<meta charset="utf-8" />
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>掌上医院</title>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link rel="stylesheet" href="assets/materialize/css/materialize.min.css"
	media="screen,projection" />
<!-- Bootstrap Styles-->
<link href="assets/css/bootstrap.css" rel="stylesheet" />
<!-- FontAwesome Styles-->
<link href="assets/css/font-awesome.css" rel="stylesheet" />
<!-- Morris Chart Styles-->
<link href="assets/js/morris/morris-0.4.3.min.css" rel="stylesheet" />
<!-- Custom Styles-->
<link href="assets/css/custom-styles.css" rel="stylesheet" />
<!-- Google Fonts-->
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />
<link rel="stylesheet" href="assets/js/Lightweight-Chart/cssCharts.css">
</head>

<body>
	<div id="wrapper">
		<nav class="navbar navbar-default top-navbar" role="navigation">
		<div class="navbar-header">

			<a class="navbar-brand waves-effect waves-dark" href="index.html"><i
				class="large material-icons">insert_chart</i> <strong>CATIC</strong>
			</a>

			<div id="sideNav" href="">
				<i class="material-icons dp48">toc</i>
			</div>
			<div class="waves-effect " href="#"
				style="margin-left: 350px;color: black; font-size: xx-large; margin-bottom: 0;">
				<strong>掌上医院后台管理系统</strong>
			</div>
		</div>

		<ul class="nav navbar-top-links navbar-right">
			<li><a class="dropdown-button waves-effect waves-dark" href="#!"
				data-activates="dropdown1"><i class="fa fa-user fa-fw"></i> <b>用户</b>
					<i class="material-icons right">arrow_drop_down</i> </a></li>
		</ul>
		</nav>
		<ul id="dropdown1" class="dropdown-content">
			<li><a href="#"><i class="fa fa-user fa-fw"></i>个人中心</a></li>
			<li><a href="#"><i class="fa fa-gear fa-fw"></i> 设置</a></li>
			<li><a href="#"><i class="fa fa-sign-out fa-fw"></i> 退出</a></li>
		</ul>

		<!--/. NAV TOP  -->
		<nav class="navbar-default navbar-side" role="navigation">
		<div class="sidebar-collapse">
			<ul class="nav" id="main-menu">
				<li><a href="#" class="waves-effect waves-dark "><i
						class="fa fa-dashboard active open"></i> 员工信息管理<span
						class="fa arrow"></span> </a>
					<ul class="nav nav-second-level">
						<li><a href="#">新增员工信息</a></li>
						<li><a href="employee1.jsp" class="active">员工信息管理</a></li>
					</ul></li>
				<li><a href="#" class="waves-effect waves-dark"><i
						class="fa fa-sitemap"></i> 设置<span class="fa arrow"></span> </a>
					<ul class="nav nav-second-level">
						<li><a href="#">Second Level Link</a></li>
						<li><a href="#">Second Level Link</a></li>
						<li><a href="#">Second Level Link<span class="fa arrow"></span>
						</a>
							<ul class="nav nav-third-level">
								<li><a href="#">Third Level Link</a></li>
								<li><a href="#">Third Level Link</a></li>
								<li><a href="#">Third Level Link</a></li>

							</ul></li>
					</ul></li>

			</ul>

		</div>

		</nav>
		<!-- /. NAV SIDE  -->

		<div id="page-wrapper">
			<div class="header">
				<h1 class="page-header">C A T I C</h1>
				<ol class="breadcrumb">
					<li><a href="#">主页</a>
					</li>
					<li><a href="#">员工管理</a>
					</li>
					<li class="active">员工信息管理</li>
				</ol>

			</div>

			<div id="page-inner">
				<div class="col-md-12">
					<div class="card">
						<div class="card-content">
							<span class="card-title">查找员工信息</span>
						</div>
						<div class="card-action">
							<form action="employeesAction/getEmployeeList" method="post"
								id="form">
								<table>
									<tr>
										<td>姓名：</td>
										<td><input type="text" style="width: 80px;margin-left: -10px;"></td>
										<td >性别：</td>
										<td><select name="employeePO.sex" style="width:100px;display:inline-block;margin-left:-10px;">
												<option value="">请选择</option>
												<option value="男">男</option>
												<option value="女">女</option>
										</select></td>
										<td>部门：</td>
										<td><select name="employeePO.department"
											style="width:100px;display:inline-block;margin-left:-10px;">
												<option value="">请选择</option>
												<option value="打印机">打印机</option>
												<option value="智能自助">智能自助</option>
												<option value="物联网">物联网</option>
												<option value="数据平台">数据平台</option>
												<option value="软件公司">软件公司</option>
										</select>
										</td>
										<td><input type="submit" value="查询" style="margin: 10px;"
											class="waves-effect waves-light btn">
										</td>
										<td><input type="button" value="修改" style="margin: 10px;"
											id="updateEmployee" class="waves-effect waves-light btn">
										</td>
										<td><input type="submit" value="删除" style="margin: 10px;"
											id="deleteEmployee" class="waves-effect waves-light btn">
										</td>
									</tr>

								</table>
								
								<table class="table table-striped table-bordered table-hover" style="margin-top: 20px;">
                                    <thead>
                                        <tr>
										<td width="30px"></td>
										<td >员工Id</td>
										<td >员工姓名</td>
										<td >员工性别</td>
										<td >员工部门</td>
										<td >员工工资</td>
									</tr>
                                    </thead>
                                    <tbody>
                                       <s:iterator var="result" value="employeePOList">
										<tr>
											<td width="30px"><input type="radio"
												name="selectOrderNumber" class="radio display:inline-block;"
												value="<s:property value="#result.employeeId"/>"/></td>
											<td ><s:property value="#result.employeeId" />
											</td>
											<td ><s:property
													value="#result.employeeName" />
											</td>
											<td ><s:property value="#result.sex" />
											</td>
											<td ><s:property value="#result.department" />
											</td>
											<td ><s:property value="#result.salary" />
											</td>
										</tr>
									</s:iterator>
                                    </tbody>
                                </table>																
							</form>

						</div>
					</div>
				</div>


			</div>

		</div>

	</div>

	<script src="assets/js/jquery-1.10.2.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/materialize/js/materialize.min.js"></script>
	<script src="assets/js/jquery.metisMenu.js"></script>
	<script src="assets/js/morris/raphael-2.1.0.min.js"></script>
	<script src="assets/js/morris/morris.js"></script>
	<script src="assets/js/easypiechart.js"></script>
	<script src="assets/js/easypiechart-data.js"></script>
	<script src="assets/js/Lightweight-Chart/jquery.chart.js"></script>
	<script src="assets/js/custom-scripts.js"></script>

</body>

</html>