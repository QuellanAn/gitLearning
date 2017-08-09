<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
<title>医生管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css"
	href="resources/css/smoothness/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="resources/css/index.css" />
<link rel="stylesheet" href="resources/css/zTreeStyle/zTreeStyle.css"
	type="text/css">
<script type="text/javascript" src="resources/js/jquery/jquery-1.6.4.js"></script>
<script type="text/javascript"
	src="resources/js/jquery/jquery.ztree.core.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="../getKey.js"></script>
<script charset="utf-8" src="config/doctor/doctor_cfg.js"></script>
<script type="text/javascript">
	function operate(id,ope,num){
		$.post("config/doctor/moveByOperate", {
		sortNum : id,
		operate : ope,
		num : num
		}, function(data, status) {
			data = eval('(' + data + ')');
			if (data.result == 0) {
				$("#act_query").click();
			} else {
				$("#act_query").click();
			}
		});
	}
	
	 function selectAll() {  
        if ($("#selectAll").attr("checked")) {  
            $("input[name='xzsj']").attr("checked", true);  
        } else {  
            $("input[name='xzsj']").attr("checked", false);  
        }  
    } 
</script>
</head>
<!-- <s:if
	test="#session.userName==null ||!#session.authority.contains('ROLE_DEPARTMENT')">
	<script type="text/javascript">
		location.href = $('base').attr('href')+"login.jsp";
	</script>
</s:if> -->
<input type="hidden" value="${param.key}" id="key1"/>
<s:hidden value="%{#session.key}" id="key"/>
<body>
	<div class="cnt-wrap">
		<s:include value="../../menus.jsp"></s:include>
		<div class="right-cnt-wrap f-fl">
			<ul class="tab-wrap bg">
				<li class="tab-crt">医生管理</li>
			</ul>
			<div class="tab-cnt">
				<div class="f-fl left-list-wrap">
					<div class="left-list-hd">
						<span class="f-fl">医生管理(<span id="totalcount"></span>) </span>
					</div>
					<div class="left-list-hd" style="height: 80px;">
						<form id="form_query" onsubmit="return false;">
							<table height="100%" style="margin-top: -3px;">
								<tr>
									<td valign="middle" align="right">姓名：</td>
									<td valign="middle"><input id="name" name="name"
										type="text"
										style="height: 24px; line-height: 24px;width:120px;" /></td>
									<!-- <td valign="middle">科室:</td>
									<td valign="middle"><select id="departmentId" name="departmentId"
										type="text" style="height: 28px; line-height: 28px;" /></td> -->
									<td valign="middle" align="right">职位:</td>
									<td valign="middle"><select id="job" name="job"
										type="text"
										style="height: 28px; line-height: 28px;width:120px;">

									</select></td>
									<td valign="middle">专家:</td>
									<td valign="middle"><select id="isExpert" name="isExpert"
										type="text"
										style="height: 28px; line-height: 28px;width:120px;">
											<option value="">全部</option>
											<option value="1">是</option>
											<option value="0">否</option>
									</select></td>
									<td valign="middle"
										style="padding-left: 55px;padding-right: 25px;">
										<button id="act_query" class="save-btn">查询</button>
									</td>
									<td valign="middle">
										<button onclick="javascript:act_save();" class="save-btn">
											添加</button>
									</td>
								</tr>
								<tr>
									<td valign="middle">医生状态:</td>
									<td valign="middle"><select id="status" name="status"
										type="text"
										style="height: 28px; line-height: 28px;width:120px;">
											<option value="0">全部</option>
											<option value="1">正常</option>
											<option value="2">冻结</option>
									</select></td>
									<td valign="middle">资料状态:</td>
									<td valign="middle"><select id="integrity"
										name="integrity" type="text"
										style="height: 28px; line-height: 28px;width:120px;">
											<option value="">全部</option>
											<option value="完整">完整</option>
											<option value="缺失">缺失</option>
									</select></td>
									<td></td>
									<td></td>
									<td style="padding-left: 55px;padding-right: 25px;"><input
										type="file" id="importDoctor" name="importDoctor"
										onchange="excleDoctor(this);" style="display:none" />
										<button onclick="$('input[id=importDoctor]').click();"
											class="save-btn">导入</button>
									</td>
									<td>
										<button onclick="exportDoctor()" class="save-btn">导出</button>
									</td>
								</tr>
							</table>
						</form>
					</div>
					<div
						style="float: left;width: 215px;height:800px;text-align:left;border:1px solid #CCCCCC">
						<ul id="treeDemo" class="ztree" style="overflow-y:auto;max-height:800px;"></ul>
					</div>
					<div id="div_cnt_table" style="float: right;"></div>
				</div>
				<div class="f-clear"></div>
			</div>
		</div>
		<div class="f-clear"></div>
		<div id="node"></div>
	</div>

</body>
</html>