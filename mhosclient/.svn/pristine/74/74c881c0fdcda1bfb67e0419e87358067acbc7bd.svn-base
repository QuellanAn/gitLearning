<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="resources/css/index.css" />
	
	<script type="text/javascript">
$("#saveDepartment").click(function(e){
		
		var form_id=$("#form_id").val();
		var form_departmentName=$("#form_departmentName").val();
		var form_departmentId=$("#form_departmentId").val();
		var form_parentId=$("#form_parentId").val();
		var form_departmentAddr=$("#form_departmentAddr").val();
		var form_introduction=$("#form_introduction").val();
		if($.trim(form_departmentName).length==0 ||$.trim(form_departmentId).length==0){
		alert("请输出完整的信息");
		return false;
		}
		alert(form_introduction);
		var paras = {id:form_id,
		             departmentName:form_departmentName,
		             departmentId:form_departmentId,
		             parentId:form_parentId,
		             departmentAddr:form_departmentAddr,
		             introduction:form_introduction,
		             phone:form_phone
		            };
	    $.post("config/department/saveDepartment",
			paras,
			function(data, status){
			
				if (data){
			     data=eval("("+data+")");
					alert(data.msg);
				}else{
					alert("操作成功！");
					$("#node").dialog("close");
					$("#act_query").click();
				}
			});
		
	});
var editor;
  editor =  KindEditor.create('textarea[id="form_introduction"]', {
		        resizeType : 0,
				minWidth : '400px',
				height : '500px',
				allowPreviewEmoticons : false,
				allowImageUpload : false,
				//同步值
				afterBlur : function() {
					this.sync();
				},
				items : [ 'fontname', 'fontsize', '|', 'forecolor',
						'hilitecolor', 'bold', 'italic', 'underline',
						'removeformat', '|', 'justifyleft', 'justifycenter',
						'justifyright', 'insertorderedlist',
						'insertunorderedlist' ]
		}); 
	  editor.html($("#form_introduction").val());
</script>
	
<div id="div_cnt_table">
    <form  method="post" action="" onsubmit="return false;" id="uploadAppVersionForm">
	<table id="tab-cnt"  style="border-collapse:separate; border-spacing:4px;width: 100%;">
	
		<tbody id="tb_cnt_body">
		
			<tr>
				<td align="center" width="30%"  >
					<p ><font color="red">*</font>科室名称：</p>
				</td>
				 <td align="center" width="70%">
				    <s:hidden id="form_id"  name="departmentVO.id" cssClass="fm-text1 f-fl" />
					<s:textfield id="form_departmentName" name="departmentVO.departmentName" cssClass="fm-text1 f-fl" />
				</td> 
			</tr>
			<tr>
			    
				<td align="center" width="30%">
					<p ><font color="red">*</font>科室编号:</p>
				</td>
				<td align="center" width="70%">
				<s:textfield id="form_departmentId" name="departmentVO.departmentId" Class="fm-text1 f-fl" />
				</td>
			</tr>
			<tr>
		<td align="center" width="30%">
					<p ><font color="red">*</font>上级科室:</p>
				</td>
			<td align="center" width="70%">
			<s:select id="form_parentId" name="departmentVO.parentId" list="%{departmentVOList}" listKey="%{departmentId}" listValue="%{departmentName}" headerKey="0" headerValue="无" cssClass="fm-text f-fl" style="width:200px;" />
			</td>
		</tr>
		<tr>
		<td align="center" width="30%">
					<p ><font color="red">&nbsp;&nbsp;</font>联系方式:</p>
				</td>
			<td align="center" width="70%">
				<s:textfield id="form_phone" name="departmentVO.phone" Class="fm-text1 f-fl" />
				</td>
		</tr>
			</tbody>
	</table>
		
		
			<table id="tab-cnt"  style="border-collapse:separate; border-spacing:4px;width: 100%;">
	
		<tbody id="tb_cnt_body">
	     <tr>
		<td align="center" width="30%">
					<p ><font color="red"></font>科室位置:</p>
				</td>
			<td align="center" width="70%">
				<s:textfield id="form_departmentAddr" name="departmentVO.departmentAddr" Class="fm-text1 f-fl" style="width:300px;" />
			</td>
			</tr>	
	<tr>		
	<td align="center" width="30%">
					<p ><font color="red"></font>科室介绍:</p>
				</td>
			<td align="center" width="70%">
				<s:textarea rows="12" id="form_introduction" name="departmentVO.introduction" Class="fm-text1 f-fl" style="width:300px;" />
			</td>
			</tr>			
			</tbody>
	</table>
	
			<table id="tab-cnt"  style="border-collapse:separate; border-spacing:4px;width: 100%;">
	
		<tbody id="tb_cnt_body">
			<tr>
				<td align="center" colspan="8">
					<input id="saveDepartment" type="button" value="提交" class="normal-btn" onclick="" />
				</td>
			</tr>
		
		</tbody>
	
	</table>
	
</form>
</div>