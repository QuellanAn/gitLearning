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
<base href="${pageContext.request.contextPath}/" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>评价详情</title>
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
		<s:include value="../../menus.jsp">
			</a>
		</s:include>
		<div class="right-cnt-wrap f-fl">
			<ul class="tab-wrap bg">
				<li class="tab-crt">
					<a href="busrecord/evaluate/eva_main.jsp">问卷详情 </a>
				</li>
			</ul>
			<div class="tab-cnt" style="width: 1040px;">
				<s:form cssClass="introduction-fm">
					<ul>
						<li id="questions">
							<c:forEach items="${data.questions }"
								var="q" varStatus="status">
								<div class="question_div"
									style="border: 1px solid rgb(192, 192, 192); margin-top: 20px; display: block;margin-right: 20px;">
									<span>${status.index + 1}. </span> <span>${q.subject }</span> <br>
									
									<c:choose>
										<c:when test="${q.type == 0 || q.type == 1 }">
											<div class="an1">
										</c:when>
										<c:otherwise>
											<div class="an1" style="display: none">
										</c:otherwise>
									</c:choose>
									<c:forEach items="${q.options }" var="o" varStatus="status">
										&nbsp; ${status.index + 1}.
										<c:choose>
											<c:when test="${fn:contains(q.suggest, status.index) }">
											
												<input type="radio" checked="checked"  readonly="true"/>
												
											</c:when>
											<c:otherwise>
												<input type="radio"  readonly="true" disabled="disabled"/>
											</c:otherwise>
										</c:choose>
										<span>${o }</span>

										

										<c:if test="${status.index == 2 }">
											<br>
										</c:if>
									</c:forEach>
									<c:forEach var="i" begin="${fn:length(q.options) }" end="5"
										step="1">
										<%-- <c:if test="${i == 5 && fn:length(q.options) != 5 }"><br></c:if> --%>
										<c:if test="${i == 3 && fn:length(q.options) != 3 }">
											<br>
										</c:if>
										<c:if test="${i == 6 && fn:length(q.options) != 6 }">
											<br>
										</c:if>
										<c:if test="${i == 9 && fn:length(q.options) != 9 }">
											<br>
										</c:if>
										<%-- 选项 ${i+1 }：<input type="radio" /><input type="text" style="width: 150px; height: 25px"> --%>
									</c:forEach>
								</div>
								<c:choose>
									<c:when test="${q.type == 0 || q.type == 1 }">
										<textarea class="an2" rows="" cols="" readonly="readonly"
											style='width: 680px; height: 50px; display: none'>表达题答案由用户填写.</textarea>
									</c:when>
									<c:otherwise>
										<textarea class="an2" rows="" cols="" readonly="readonly"
											style='width: 1000px; height: 50px;'>${q.suggest }</textarea>
									</c:otherwise>
								</c:choose>
			</div>
			</c:forEach>
			</li>
			</ul>
			<br> <br />
			</s:form>
		</div>
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