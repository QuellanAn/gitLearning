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
		
		var form_doctorId=$("#form_doctorId").val();
		var form_doctorName=$("#form_doctorName").val();
		var form_sex=$("#form_sex").val();
		var form_job=$("#form_job").val();
		var form_departmentId=$("#form_departmentId").val();
		var form_isExpert=$("#form_isExpert").val();
		var form_speciality=$("#form_speciality").val();
		var form_introduction=$("#form_introduction").val();
		var form_code=$("#form_code").val();
		var form_sortNumber=$("#form_sortNumber").val();
		if($.trim(form_doctorName).length==0
		||$.trim(form_departmentId).length==0
		||$.trim(form_introduction).length==0
		||$.trim(form_code).length==0
		||$.trim(form_sortNumber).length==0
		){
		alert("请输入完整的信息");
		return false;
		}
				
	var form = new FormData(document.getElementById("saveDoctorForm"));
	 $.ajax({
                url:"config/doctor/saveOrUpdateDoctor",
                type:"post",
                data:form,
                processData:false,
                contentType:false,
                success:function(data){
                   if (data){
			     data=eval("("+data+")");
					alert(data.msg);
				}else{
					alert("操作成功！");
					$("#node").dialog("close");
					$("#currentPage").click();
				}
                },
                error:function(e){
                    alert("错误！！");
        
                }
            });        				
			
		
	});
$("#file").change(function(){
	var objUrl = getObjectURL(this.files[0]) ;
	console.log("objUrl = "+objUrl) ;
	if (objUrl) {
		$("#img").attr("src", objUrl) ;
	}
}) ;	

function getObjectURL(file) {
var url = null ; 
if (window.createObjectURL!=undefined) {
	url = window.createObjectURL(file) ;
} else if (window.URL!=undefined) {
	url = window.URL.createObjectURL(file) ;
} else if (window.webkitURL!=undefined) {
	url = window.webkitURL.createObjectURL(file) ;
}
return url ;
};

</script>
<div id="div_cnt_table">
    <form  method="post" action="" onsubmit="return false;" id="saveDoctorForm">
	<table id="tab-cnt"  style="border-collapse:separate; border-spacing:4px;width: 100%;">
	
		<tbody id="tb_cnt_body">
		
			<tr>
				<td align="center" width="20%"  >
					<p><font color="red">*</font>姓名:</p>
				</td>
				 <td align="center" width="30%">
				    <s:hidden id="form_doctorId"  name="doctorVO.doctorId" cssClass="fm-text1 f-fl" />
				     <s:hidden id="form_photo"  name="doctorVO.photo" cssClass="fm-text1 f-fl" />
					<s:textfield id="form_doctorName" name="doctorVO.doctorName"  cssClass="fm-text f-fl" />
				</td> 
				
				<td align="center" width="20%"  >
					<p ><font color="red">*</font>性别:</p>
				</td>
				 <td align="center" width="30%">
				  <s:select id="form_sex" name="doctorVO.sex" list="#{'0':'男','1':'女'}" cssClass="fm-text f-fl" style="width:150px;" ></s:select>
		
				</td> 
			</tr>
			<tr>
			    <td align="center" width="20%">
					<p ><font color="red">*</font>职位:</p>
				</td>
				<td align="center" width="30%">
				
				  <s:select id="form_job" name="doctorVO.job" list="%{jobList}" listKey="%{id}" listValue="%{jobName}" cssClass="fm-text f-fl" style="width:210px;"></s:select>
				</td>
				<td align="center" width="20%">
					<p ><font color="red">*</font>科室:</p>
				</td>
				<td align="center" width="30%">
				<s:select id="form_departmentId" name="doctorVO.departmentId" headerKey="0" headerValue="%{doctorVO.deptName}" list="%{deptList}" listKey="%{departmentId}" listValue="%{departmentName}" cssClass="fm-text f-fl" style="width:150px;" />
				</td>
			</tr>
			<tr>
			<td align="center" width="20%"><p ><font color="red">*</font>专家:</p></td>
			<td align="center" width="30%">
			<s:select id="form_isExpert" name="doctorVO.isExpert" list="#{'0':'否','1':'是' }"  cssClass="fm-text f-fl" style="width:210px;" /></td>
			<td align="center" width="20%"><p ><font color="red">*</font>工号:</p></td>
			<td align="center" width="30%">
			<s:textfield id="form_code" name="doctorVO.code"  cssClass="fm-text f-fl" style="width:137px;" />
			</td>
			</tr>
					
			
			</tbody>
	</table>
	
		<table id="tab-cnt"  style="border-collapse:separate; border-spacing:4px;width: 100%;">
	
		<tbody id="tb_cnt_body">
	
	<tr>
				<td align="center" width="30%"  >
					<p ><font color="red">*</font>展示排序号:</p>
				</td>
				 <td align="center" width="20%">
				   <s:if test='doctorVO.doctorId==null || doctorVO.doctorId==""'>
				   
				   <s:textfield id="form_sortNumber" value="99999" maxlength="5"  name="doctorVO.sortNumber"  cssClass="fm-text f-fl"  onkeyup="value=value.replace(/[^\d]/g,'');"/>
				   </s:if>
					<s:else>
					<s:textfield id="form_sortNumber"  maxlength="5"  name="doctorVO.sortNumber"  cssClass="fm-text f-fl"  onkeyup="value=value.replace(/[^\d]/g,'');"/>
				</s:else>
				</td> 
				
				<td align="center" width="30%"><p ><font color="red">*</font>状态:</p></td>
				<td align="center" width="20%">
				<s:select id="form_status" name="doctorVO.status" list="#{'1':'正常','2':'冻结' }"  cssClass="fm-text f-fl" style="width:130px;" /></td>
				</td>
			</tr>
	</tbody>
	</table>
				<table id="tab-cnt"  style="border-collapse:separate; border-spacing:4px;width: 100%;">
	
		<tbody id="tb_cnt_body">
		<tr>
		<td align="center" width="20%">
					<p ><font color="red"></font>照片:</p>
				</td>
			<td align="center" width="40%"  ><img src="${doctorVO.photo}" id="img" height="120" width="90"></td>	
			<td align="center" width="40%">
			<s:file id="file" name="file"  ></s:file>
			</td>
			</tr>			
			</tbody>
	</table>
			<table id="tab-cnt"  style="border-collapse:separate; border-spacing:4px;width: 100%;">
	
		<tbody id="tb_cnt_body">
		<tr>
		<td align="center" width="10%">
					<p ><font color="red"></font>专长:</p>
				</td>
			<td align="center" width="90%">
			<s:textarea rows="3" id="form_speciality" name="doctorVO.speciality" Class="fm-text1 f-fl" style="width:400px;" />
			</td>
			</tr>
			
		<tr>
		<td align="center" width="10%">
					<p ><font color="red">*</font>介绍:</p>
				</td>
			<td align="center" width="90%">
			<s:textarea id="form_introduction" name="doctorVO.introduction" Class="fm-text1 f-fl" style="width:400px;height:200px;" />
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