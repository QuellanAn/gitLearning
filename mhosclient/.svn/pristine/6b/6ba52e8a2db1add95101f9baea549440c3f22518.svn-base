$(document).ready(function() {
	
datalist_ctrl.loaddatalist("","","","");
//查询点击事件
	$("#act_query").click(function(e){
	//	var source=$('#source').val();
		var operator=$('#operator').val();
		var billDate=$('input[name=billDate]').val();
		datalist_ctrl.loaddatalist("","",billDate,operator);
	});
	$('input[name=billDate]').datepicker({ dateFormat: 'yy-mm-dd' });
	
});

function refresh_totalcount(){
	$("#totalcount").html($("#hid_totalcount").val());
}

var datalist_ctrl = {
		
	loaddatalist:function(pageNo,source,billDate,operator){
			var cnt = $('#div_cnt_table');
			$.post("unpaid/checkImportAction_findAll",{
				pageNo:pageNo,
				source:source,
				billDate:billDate,
				operator:operator,
			},
			function(data, status){
				if (data && data.msg){
					alert(data.msg);
				}else if (data){
					cnt.empty();
					cnt.append(data);
				}
			});
		}
};

function closeDialog(id) {
	$("#" + id).dialog("close");
}

function refresh_totalcount(){
	$("#totalcount").html($("#hid_totalcount").val());
}

function findDetails(iid){
	window.location.href="unpaid/checkImportAction_findDetails?iid="+iid;
}

