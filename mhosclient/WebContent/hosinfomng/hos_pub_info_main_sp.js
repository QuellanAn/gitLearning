$(document).ready(function() {
	loadinfocats(0);
	datalist_ctrl.loaddatalist("hosinfomng/getWaitAppHosPubInfoList?page=1");
	
	$("#infotype").change(function(e){
		loadinfocats($(this).val());
	});
	
	$("#act_query").click(function(e){
		var paras = $("#form_query").serialize();
		var url = "hosinfomng/getWaitAppHosPubInfoList?page=1&" + paras;
		datalist_ctrl.loaddatalist(url);
		return false;
	});
	
	$("input[name=startexpdate]").datepicker({ dateFormat: 'yy-mm-dd' });
	$("input[name=endexpdate]").datepicker({ dateFormat: 'yy-mm-dd' });
	
	initDate();
});

function initDate(){
	var date=new Date();
	var yyyy=date.getFullYear();
	var mm=date.getMonth()+1;
	var day=date.getDate();
	date = yyyy+"-"+mm+"-"+day
	$("input[name=startexpdate]").val(date);
	$("input[name=endexpdate]").val(date);
};

function refresh_unpubcount(){
	$("#totalcount").html($("#hid_totalcount").val());
}

function loadinfocats(type){
	var url = "hosinfomng/getinfocats?infotype=" + type;
	var sel = $('#infocat');
	sel.empty();
	if (!type=="" || type == 0){
		sel.append("<option value=''>全部</option>");
	}
	$.get(url , function(data, status){
		var jarr = eval(data);
		for (var i in jarr){
			var o = $('<option></option>').val(jarr[i].infoCatCode).html(jarr[i].infoCatName);
			sel.append(o.get());
		}
	});
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






