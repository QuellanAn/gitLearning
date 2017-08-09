$(document).ready(function() {
	
	$('input[name=starttime]').datepicker({ dateFormat: 'yy-mm-dd' });

	$("#btn_submit").click(function(e){
		var type=$("#type").val();
		var title=$("#title").val();
		var content=$("#content").val();
		alert(type+title+content)
		if($.trim(type).length==0
		||$.trim(title).length==0
		||$.trim(content).length==0
		){
		alert("请输入完整的信息");
		return false;
		}
	});
});
