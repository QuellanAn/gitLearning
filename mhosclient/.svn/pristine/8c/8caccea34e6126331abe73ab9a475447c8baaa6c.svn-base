<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*, com.catic.mobilehos.service.vo.*"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%   
String path = request.getContextPath();   
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
HosPubInfoVO v = (HosPubInfoVO)request.getAttribute("hosPubInfoVO");

%>   

<html>
<head>
<base href="<%=basePath%>/" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title></title>
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="resources/css/index.css" />
<style>
	#div_prev{ 	
		width: 757px;
	height: 430px;
	margin-left:10px;
	border: 1px solid #cdcccc;
	overflow-y: auto;
	padding: 20px;
	}   
	#subject{font-weight:bold;}
	#div_prev img {width:100%;}
</style>

<script charset="utf-8" src="resources/js/jquery/jquery-1.10.2.js"></script>
<script charset="utf-8" src="hosinfomng/prev_pub_hosinfo.js"></script>



<script type="text/javascript">


</script>



</head>


<body>
		<div class="cnt-wrap">
		<s:include value="../menus.jsp"></s:include>

		<div class="right-cnt-wrap f-fl" style="min-height: 500px;">
			<ul class="tab-wrap bg">
				<li><a href="hosinfomng/hos_pub_info_main.jsp">新发布</a></li>
				<li><a
					href="hosinfomng/hos_pub_info_main_sp.jsp">发布审批</a></li>
				<li class="tab-crt"><a href="hosinfomng/hos_pub_info_main_pub.jsp">已发布</a></li>
			</ul>
			<div class="tab-cnt">
		<s:hidden name="infoid" id="infoid"></s:hidden>
		<%@ include file="prev_content.jsp" %>
		<center><button id="act_cancel" <%=v.isCanceled()?"disabled":""%> class="normal-btn">取消</button></center>
	
	</div>
		</div>
		<div class="f-clear"></div>
	</div>

</body>





	
</html>