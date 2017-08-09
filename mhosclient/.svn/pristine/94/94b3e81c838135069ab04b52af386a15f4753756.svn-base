$(document).ready(function() {
	loadtypes();
	datalist_ctrl.loaddatalist("busrecord/getMessagesList?page=1");
	
	$("#act_query").click(function(e){
		var paras = $("#form_query").serialize();
		var url = "busrecord/getMessagesList?page=1&" + paras;
		datalist_ctrl.loaddatalist(url);
		return false;
	});
	
	$('input[name=startdate]').datepicker({ dateFormat: 'yy-mm-dd' });
	$('input[name=enddate]').datepicker({ dateFormat: 'yy-mm-dd' });
	
});


function loadtypes(){
	var url = "busrecord/getMsgBusTypeJson";
	var sel = $('#bustype');
	$.get(url , function(data, status){
		var jarr = eval(data);
		for (var i in jarr){
			var o = $('<option></option>').val(jarr[i].busTypeCode).html(jarr[i].busName);
			sel.append(o.get());
		}
	});
}


function refresh_totalcount(){
	$("#totalcount").html($("#hid_totalcount").val());
}

var datalist_ctrl = {
		
	loaddatalist:function(url){
			var cnt = $('#div_cnt_table');
			$.get(url , function(data, status){
				if (data && data.msg){
					alert(data.msg);
				}else if (data){
					cnt.empty();
					cnt.append(data);
				}
			});
		},
	topage: function(url){
		this.loaddatalist(url);
	}
};






