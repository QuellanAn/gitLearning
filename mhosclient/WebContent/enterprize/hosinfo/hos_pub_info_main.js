$(document).ready(function() {
	datalist_ctrl.loaddatalist("enterprize/hosinfo_findAll?pageNo="+$("#hid_pageNo").val());
	
	$('#btn_add_pub').click(function(){
		window.location.href = 'enterprize/hosinfo/add_edit_hosinfo.jsp';
	});
	
	$("#act_query").click(function(e){
		var paras = $("#form_query").serialize();
		var pageNo=$("#hid_pageNo").val();
		var url = "enterprize/hosinfo_findAll?pageNo="+pageNo+"&" + paras;
		datalist_ctrl.loaddatalist(url);
		return false;
	});
	
	$('input[name=starttime]').datepicker({ dateFormat: 'yy-mm-dd' });
	$('input[name=endtime]').datepicker({ dateFormat: 'yy-mm-dd' });
});

function deletehosinfo(id){
	if (confirm("你确认要删除该条记录吗？")){
		$.post("enterprize/hosinfo_delete",
				{id:id},
				function success(data,status){
					if (status = 'success'){
						var cnt = $('#div_cnt_table');
						cnt.empty();
						cnt.append(data);
					}else{
						showTips('删除失败！',250,2);
					}
				});
	}
}

function updatehosinfo(id) {
	document.location.href="enterprize/hosinfo_toUpdate?id="+id;
}

function refresh_totalcount(){
	$("#totalcount").html($("#hid_totalcount").val());
}

var datalist_ctrl = {
		loaddatalist:function(url){
				var cnt = $('#div_cnt_table');
				$.get(url ,function(data, status){
					if (data && data.msg){
						alert(data.msg);
					}else if (data){
						cnt.empty();
						cnt.append(data);
					}
				});
			},
			loaddatalist1:function(pageNo){
				var cnt = $('#div_cnt_table');
				$.post('enterprize/hosinfo_findAll',{
					pageNo:pageNo
						},
			      	function(data, status){
					if (data && data.msg){
						alert(data.msg);
					}else if (data){
						cnt.empty();
						cnt.append(data);
					}
				});
			},

	};
