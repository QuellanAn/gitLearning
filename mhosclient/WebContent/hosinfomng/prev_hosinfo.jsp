<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%   
String path = request.getContextPath();   
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";   
%>   

<html>
<head>
<base href="<%=basePath%>/" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title></title>

<style>
	#div_prev{ width:300px; height:500px; border:1px solid #000000;overflow-y:auto}   
	#subject{font-weight:bold;}
	#div_prev img {width:100%;}
</style>

<script charset="utf-8" src="resources/js/jquery/jquery-1.10.2.js"></script>
<script charset="utf-8" src="hosinfomng/prev_hosinfo.js"></script>



<script type="text/javascript">


</script>



</head>


<body>
	<div>
		<div id="div_prev">
		<p>
			<s:label name="subject" id="subject"></s:label>
		</p>
		<p>
			<label>作者：</label>
			<s:label name="author" id="author"></s:label>
			<label id="pub_time"></label>
		</p>
		<div id="div_content">
				<s:hidden name="content" id="content"></s:hidden>
		</div>
		
	</div>
	
	<button id="btn_edit">编辑</button>
	
	</div>
	
	
	
	
	
	

</body>





	
</html>