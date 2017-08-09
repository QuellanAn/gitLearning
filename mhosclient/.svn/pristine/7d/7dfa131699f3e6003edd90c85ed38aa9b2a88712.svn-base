<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<table id="tb_cnt" class="list-table">
	<thead>
		<tr>
			<th>科室</th>
			<th>医生</th>
			<th>已提交</th>
			<th>已取消</th>
			<th>已停诊</th>
			<th>已取号</th>
			<th>已爽约</th>
			<th>已逾期</th>
			<th>小计</th>
		</tr>
	</thead>
	<tbody id="tb_cnt_body">
		<%
			int dep_011 = 0, dep_021 = 0, dep_031 = 0, dep_041 = 0, dep_051 = 0, dep_061 = 0, dep_count = 0;
			List<Map<String, Object>> data = (List<Map<String, Object>>) request.getAttribute("data");
			Map<String, Object> row = null;
			for (int i = 0; i < data.size(); i++) {
				row = data.get(i);
		%>
		<tr>
			<%
				if(!row.get("doctor_id").equals("countRow")) {
			%>
			<td align="center" class="dep_<%=row.get("department_id")%>"><%=row.get("department_name")%></td>
			<td align="center"><%=row.get("doctor_name")%></td>
			<%
										dep_011 += Integer.parseInt(row.get("011").toString());
										dep_021 += Integer.parseInt(row.get("021").toString());
										dep_031 += Integer.parseInt(row.get("031").toString());
										dep_041 += Integer.parseInt(row.get("041").toString());
										dep_051 += Integer.parseInt(row.get("051").toString());
										dep_061 += Integer.parseInt(row.get("061").toString());
										dep_count += Integer.parseInt(row.get("count").toString());
									} else {
			%>
			<td align="center" class="dep_<%=row.get("department_id")%>"><%=row.get("department_name")%></td>
			<td align="center"><%=row.get("doctor_name")%></td>
			<%
				}
			%>
			<td align="center"><%=row.get("011")%></td>
			<td align="center"><%=row.get("021")%></td>
			<td align="center"><%=row.get("031")%></td>
			<td align="center"><%=row.get("041")%></td>
			<td align="center"><%=row.get("051")%></td>
			<td align="center"><%=row.get("061")%></td>
			<td align="center"><%=row.get("count")%></td>
		</tr>
		<%
			}
		%>
		<tr style="color: red; font-weight: bold;">
			<td align="center" colspan="2" style="font-size: 20px;">总计</td>
			<td align="center"><%=dep_011%></td>
			<td align="center"><%=dep_021%></td>
			<td align="center"><%=dep_031%></td>
			<td align="center"><%=dep_041%></td>
			<td align="center"><%=dep_051%></td>
			<td align="center"><%=dep_061%></td>
			<td align="center" style="font-size: 20px;"><%=dep_count%></td>
		</tr>
	</tbody>
</table>
<script>
	$(function() {
		var chart4tbl = $("#tb_cnt");
		var tds;
		while ((tds = $(chart4tbl).find("td[class^='dep_']")).length > 0) {
			var thisClass = $(tds[0]).attr("class");
			var thisTds = $(chart4tbl).find("." + thisClass);
			$(thisTds).eq(0).removeClass(thisClass);
			$(thisTds).eq(0).attr("rowspan", thisTds.length);
			$(thisTds).last().parent().css({
				"color" : "#FF5151"
				//"font-weight" : "bold"
			});
			$(thisTds).slice(1).remove();
		}
	});
</script>