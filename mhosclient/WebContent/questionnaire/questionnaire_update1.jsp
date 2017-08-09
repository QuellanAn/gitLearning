<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改调查问卷</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css"
	href="resources/css/global.css" />
<link rel="stylesheet" type="text/css"
	href="resources/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="resources/css/introduction-edit.css" />
<script type="text/javascript"
	src="resources/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript"
	src="resources/js/common.js"></script>
<script type="text/javascript" src="questionnaire/questionnaire-add.js"></script>
<script type="text/javascript" src="./getKey.js"></script>
</head>
<!-- <s:if
	test="#session.userName==null ||!#session.authority.contains('ROLE_HOSINFO')">
	<script type="text/javascript">
		location.href = $('base').attr('href') + "login.jsp";
	</script>
</s:if> -->
<input type="hidden" value="${param.key}" id="key1"/>
<s:hidden value="%{#session.key}" id="key"/>
<body>
	<div class="cnt-wrap">
		<s:include value="../menus.jsp"> 
				<a href="questionnaire/questionnaire"></a></s:include>
		<div class="right-cnt-wrap f-fl">
			<ul class="tab-wrap bg">
				<li class="tab-crt">
				<a href="questionnaire/questionnaire">调查问卷管理 </a>
				</li>
			</ul>
			<div class="tab-cnt" style="width: 1000px;" id="wj">
				<s:form id="updateQuestionnaireForm"
					action="updateQuestionnaireAction" method="post" theme="simple"
					cssClass="introduction-fm">
					<ul>
						<%-- <input id="questionsLength" type="" value="${fn:length(data.questions)}" /> --%>
						<li><span class="introduction-label f-fl" style="margin-left: -37px;"><span
								class="color-red">*</span>标题：</span> 
								<input type="text" id="title" value="${data.title }"
								class="fm-text f-fl" style="margin-left: 37px;"/>
						</li>

						<li><span class="introduction-label f-fl" style="margin-left: -20px;"><span
								class="color-red">*</span>副标题：</span> 
								<textarea rows="" cols="" style="margin: 0px; width: 605px; height: 77px;margin-left: 20px;" id="sub_title" class="fm-text " >${data.sub_title }</textarea>
						</li>
						<li><span class="introduction-label f-fl" style="margin-left: -10px;">问卷状态：</span> <select
								id="status" value="${data.status }" style="width:200px;height:30px;margin-left: 10px;" >
								<c:if test="${data.status == 1 }">
									<option value="1" selected="selected">有效</option>
									<option value="0">无效</option>
								</c:if>
								<c:if test="${data.status == 0 }">
									<option value="1">有效</option>
									<option value="0" selected="selected">无效</option>
								</c:if>
								</select>
						</li>
						<input type="hidden" name="questionsLength" id="questionsLength" value="${fn:length(data.questions)}" /> 
						<li id="questions">
						<c:forEach items="${data.questions }" var="q" varStatus="status">
							<div class="question_div" style="border: 1px solid rgb(192, 192, 192); margin-top: 20px; display: block;margin-right : 20px;height: 100px;">
								<span class="introduction-label f-fl" style="width:92px;">问题类型：</span> 
								<select id="sel_type" style="width: 120px;height:27px;" onchange="addAnswers(this.value, this)">
									<option value="0" <c:if test="${q.type == 0 }">selected="selected"</c:if>>单项选择题</option>
									<option value="1" <c:if test="${q.type == 1 }">selected="selected"</c:if>>多项选择题</option>
									<option value="2" <c:if test="${q.type == 2 }">selected="selected"</c:if>>表达题</option>
								</select> 
								<input id="inp_subject" style="width: 615px; height: 25px" value="${q.subject }"> 
								<input type="hidden" value="删除" class="normal-btn" style="text-align: center; padding: 0; height: 30px;width: 50px;" onclick="deleteQuestion(this)"> <br>
									<c:choose>
										<c:when test="${q.type == 0 || q.type == 1 }">
											<div class="an1" style="margin-left: 25px;">
										</c:when>
										<c:otherwise>
											<div class="an1" style="display: none;margin-left: 25px;">
										</c:otherwise>
									</c:choose>
									<c:forEach items="${q.options }" var="o" varStatus="status">
										选项 ${status.index + 1}：
										<input type="text" style="width: 196px; height: 25px" value="${o }">
										<c:if test="${status.index == 2 }"><br></c:if>
									</c:forEach>
									<c:forEach var="i" begin="${fn:length(q.options) }" end="5" step="1"> 
										<c:if test="${i == 3 && fn:length(q.options) != 3 }"><br></c:if>
										<%-- <c:if test="${i == 6 && fn:length(q.options) != 6 }"><br></c:if>
										<c:if test="${i == 9 && fn:length(q.options) != 9 }"><br></c:if> --%>
										
										&nbsp;选项 ${i+1 }：<input type="text" style="width: 196px; height: 25px">
									</c:forEach>
								</div>
								<c:choose>
										<c:when test="${q.type == 0 || q.type == 1 }">
											<textarea class="an2" rows="" cols="" readonly="readonly" style='width: 819px; height: 50px; display: none;margin-left: 13px;'>表达题答案由用户填写.</textarea>
										</c:when>
										<c:otherwise>
											<textarea class="an2" rows="" cols="" readonly="readonly" style='width: 819px; height: 50px;margin-left: 13px;'>表达题答案由用户填写.</textarea>
										</c:otherwise>
									</c:choose>
							</div>
						</c:forEach>
						</li>
					</ul>
					<!-- <br>
					<br /> -->
					<%-- <center>
						<input type="button" value="添加问题" class="normal-btn"
							onclick="addQuestions()" style="margin-right: 20px;">
						<input
							type="button" value="提交" class="normal-btn"
							onclick="submitQuestionnaire()" />
					</center> --%>
				</s:form>
			</div>
			<%-- <center>
				<input type="button" value="添加问题" class="normal-btn"
					onclick="addQuestions()" style="margin-right: 20px;">
				<input
					type="button" value="提交" class="normal-btn"
					onclick="submitQuestionnaire()" />
			</center> --%>
		</div>
		<div class="f-clear"></div>
	</div>
	<div id="div_add_win_temp1" class="question_div"
		style="display: none; border: 1px solid #C0C0C0; margin-top: 20px;margin-right : 20px;height: 100px;">
		<span class="introduction-label f-fl" style="width:92px;">问题类型：</span> <select
			id="sel_type" style="width: 120px;height:27px;"
			onchange="addAnswers(this.value, this)">
			<option value="0">单项选择题</option>
			<option value="1">多项选择题</option>
			<option value="2">表达题</option>
		</select>
			<input id="inp_subject" style="width: 615px; height: 25px" />
			<input type="button" value="删除" class="normal-btn"
			style="text-align: center; padding: 0; height: 30px;width: 50px;"
			onclick="deleteQuestion(this)" /> <br>
		<div class="an1" style="margin-left: 25px;">
			&nbsp;&nbsp;选项1：<input type="text" style="width: 203px; height: 25px" /> 
			选项2：<input type="text" style="width: 203px; height: 25px" /> 
			选项3：<input type="text" style="width: 203px; height: 25px" /><br> 
			&nbsp;&nbsp;选项4：<input type="text" style="width: 203px; height: 25px" /> 
			选项5：<input type="text" style="width: 203px; height: 25px" /> 
			选项6：<input type="text" style="width: 203px; height: 25px" /> 
		</div>
		<textarea class="an2" rows="" cols="" readonly="readonly"
			style="width: 819px; height: 50px; display: none;margin-left: 13px;">表达题答案由用户填写.</textarea>
	</div>
</body>
</html>
<script>
$(document).ready(function(){	
	var wjheight;
	if($("#questionsLength").val() != "0"){
		var questionsLength = parseInt($("#questionsLength").val());
		//var questionsheight = (questionsLength)*200+"px";
		wjheight = (questionsLength)*125+200+"px";
		//$("#questions").css({"height":questionsheight});
	}else{
		wjheight = 200+"px";
	}
	//alert("  "+wjheight+"   "+questionsLength);
	$("#wj").css({"height":wjheight});
});
function addQuestions() {
	var addheight = $("#div_add_win_temp1").outerHeight(true)-55;
	var wjheight = $("#wj").outerHeight(true);
	var height = wjheight+addheight+"px";
	$("#wj").css({"height":height});
	var q = $("#div_add_win_temp1").clone().show();
	$(q).removeAttr("id");
	$("#questions").append(q);
};
function submitQuestionnaire() {
	if(!postData()) {
		return;
	}
	var questionnaire = new Object();
	questionnaire.id = ${data.id };
	questionnaire.title = $("#title").val();
	questionnaire.sub_title = $("#sub_title").val();
	questionnaire.status = $("#status").val();
	var questionsArray = [];
	var questions = $("#questions").find(".question_div");
	for(var i = 0; i < questions.length; i++) {
		var q = new Object();
		q.type = $(questions[i]).find("#sel_type").val();
		q.subject = $(questions[i]).find("#inp_subject").val();
		var options = [];
		var optionsArray = $(questions[i]).find(".an1 input");
		for(var k = 0; k < optionsArray.length; k++) {
			var option = $(optionsArray[k]).val();
			if($.trim(option) != '') {
				options.push($.trim(option));
			} else {
				break;
			}
		}
		q.options = options;
		questionsArray.push(q);
	}
		questionnaire.questions =JSON.stringify(questionsArray);
			 //alert(JSON.stringify(questionnaire));
	
	$.ajax({
			url: "questionnaire/updateQuestionnaireAtion",
			type: "POST",
			data: {questionnaire: JSON.stringify(questionnaire)},
			dataType: "json",
			success: function(data){
				alert(data);
			}
		});
		confirm("提交成功");
		window.history.back(-1);
}

	
	//${data.title}
	
// 	var data = ${data };
// 	$("#title").val(data["title"]);
// 	$("#sub_title").val(data["sub_title"]);
// 	$("#status").val(data["status"]);
</script>