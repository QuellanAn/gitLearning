<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>/" />
<base href="${pageContext.request.contextPath}/" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>问题统计详情</title>
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
</head>
<!-- <s:if
	test="#session.userName==null ||!#session.authority.contains('ROLE_HOSINFO')">
	<script type="text/javascript">
		location.href = $('base').attr('href') + "login.jsp";
	</script>
</s:if> -->
<body>
	<div class="cnt-wrap">
		<s:include value="../menus.jsp">
			</a>
		</s:include>
		<div class="right-cnt-wrap f-fl">
			<ul class="tab-wrap bg">
				<li class="tab-crt">问题统计详情 </a></li>
			</ul>
			<!-- <div class="tab-cnt" style="overflow: auto;"> -->
			<table id="tb_cnt" class="list-table" style="margin-top: 5px;" border="1"  width="100%">
			
				<thead>
				<c:forEach items="${data.questions }" var="q" varStatus="status">
					<c:if test="${q.type == 0 || q.type == 1 }">
					<tr>
						<td style="margin-right: 5px;" colspan="100">${q.id }.${q.subject }</td>
					</tr>
					
					<tr>	
							<td>选择项：</td>
							<c:forEach items="${q.options }" var="o" varStatus="status">
							<td width="20%">${status.index + 1}.${o }</td>
							</c:forEach>
											
					</tr>
					<tr>
							<td>统计数量：</td>
							<c:set var="answer" value="${q.answers }"></c:set>
							<c:forEach items="${q.options }" var="o" varStatus="status">
							<td width="20%">${answer[status.index] }</td>
							</c:forEach>
							
					</tr>
					<tr >
						<td colspan="100"> &nbsp;</td>
					</tr>
					</c:if>
					</c:forEach>
				</thead>		
			</table>	
						
						
						
	
	</div>
	<div class="f-clear"></div>
	</div>
	<div id="div_add_win_temp1" class="question_div"
		style="display: none; border: 1px solid #C0C0C0; margin-top: 20px;">
		<span class="introduction-label f-fl">问题类型:</span> <select
			id="sel_type" style="width: 120px;height:27px;"
			onchange="addAnswers(this.value, this)">
			<option value="0">单项选择题</option>
			<option value="1">多项选择题</option>
			<option value="2">表达题</option>
		</select> <br>
		<div class="an1">
			选项1：<input type="text" readonly="readonly"
				style="width: 170px; height: 25px" /> 选项2：<input type="text"
				readonly="readonly" style="width: 170px; height: 25px" /> 选项3：<input
				type="text" style="width: 150px; height: 25px" /><br> 选项4：<input
				type="text" style="width: 150px; height: 25px" /> 选项5：<input
				type="text" style="width: 150px; height: 25px" /> 选项6：<input
				type="text" style="width: 150px; height: 25px" /><br> 选项7：<input
				type="text" style="width: 150px; height: 25px" /> 选项8：<input
				type="text" style="width: 150px; height: 25px" /> 选项9：<input
				type="text" style="width: 150px; height: 25px" /> <br> 选项10：<input
				type="text" style="width: 150px; height: 25px" />
		</div>
		<textarea class="an2" rows="" cols="" readonly="true"
			style="width: 680px; height: 50px; display: none">表达题答案由用户填写.</textarea>
	</div>
</body>
</html>
<script>
	

	
</script>