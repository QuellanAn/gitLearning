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
	src="resources/js/jquery/jquery-1.7.2.js"></script>
<script type="text/javascript"
	src="resources/js/common.js"></script>
<script type="text/javascript" src="questionnaire/questionnaire_new.js"></script>
<script type="text/javascript" src="./getKey.js"></script>
<link id="skin" rel="stylesheet" href="resources/jBox/Skins2/Blue/jbox.css" />
<script type="text/javascript" src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="resources/jBox/i18n/jquery.jBox-zh-CN.js"></script>
<style type="text/css">
.block_table { 
	display: table;
}
.table_vertical {
   	display: table-cell;
   	vertical-align: middle;
}
</style>
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
		<div class="right-cnt-wrap f-fl" style="position: relative;">
			<ul class="tab-wrap bg">
				<li class="tab-crt">
				<a href="questionnaire/questionnaire">调查问卷管理 </a>
				</li>

			</ul>
			<div class="tab-cnt" style="width: 1000px;max-height: 800px;overflow-y: auto;padding-bottom: 20px;" id="wj">

				<s:form id="updateQuestionnaireForm"
					action="updateQuestionnaireAction" method="post" theme="simple"
					cssClass="introduction-fm">
					<ul>
						<input type="hidden" name="questionsLength" id="questionsLength" value="${fn:length(data.questions)}" /> 
						<div style="width: 975px;height: 32px;">
							<div class="f-fl" style="display: inline-block;width: 100px;height: 32px;line-heigh: 32px;text-align: right;">
								<span><span style="color: red;">*</span>标题：</span>
							</div>
							<input type="text" id="title" value="${data.title }" class="fm-text f-fl" style="height: 30px;"/>
						</div>
						<div style="width: 975px;height: 98px;margin-top: 5px;">
							<div class="f-fl" style="display: inline-block;width: 100px;height: 32px;line-heigh: 32px;text-align: right;">
								<span><span style="color: red;">*</span>副标题：</span>
							</div>
							<textarea rows="" cols="" style="display: inline-block;margin: 0px; width: 605px; height: 77px;resize : none;" id="sub_title" class="fm-text f-fl" >${data.sub_title }</textarea>
						</div>
						<div style="width: 975px;height: 32px;margin-top: 5px;">
							<div class="f-fl" style="display: inline-block;width: 100px;height: 32px;line-heigh: 32px;text-align: right;">
								<span>问卷状态：</span>
							</div>
							<select id="status" value="${data.status }" class="f-fl" style="width:200px;height:30px;" >
								<c:if test="${data.status == 1 }">
									<option value="1" selected="selected">有效</option>
									<option value="0">无效</option>
								</c:if>
								<c:if test="${data.status == 0 }">
									<option value="1">有效</option>
									<option value="0" selected="selected">无效</option>
								</c:if>

								</select>

						</div>
						<div id="questions">
							<input id="question_obj_input" type="hidden" value='${data.questions}'>
							
						</div>

					</ul>
				</s:form>
			</div>
			<center>
				<input type="button" value="添加问题" class="normal-btn"
					onclick="addQuestions()" style="margin-right: 20px;">
				<input
					type="button" value="提交" class="normal-btn"
					onclick="submitQuestionnaire()" />
			</center>
		</div>
		<div class="f-clear"></div>
	</div>
</body>
<script type="text/javascript">
function submitQuestionnaire() {
	if(!checkQuestionnaire()) {
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
		var optionsArray = $(questions[i]).find(".solo_option_div input");
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
</script>
</html>