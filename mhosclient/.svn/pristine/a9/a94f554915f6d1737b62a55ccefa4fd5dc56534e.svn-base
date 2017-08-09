$(document).ready(function() {         
	loadBody();
	
	datalist_ctrl.loaddatalist("busrecord/getPatientsList?page=1&type=0&type1=0");//type用于区分是建档(0)还是绑卡(1)，type1是区分跳转在线建档记录(0)还是就诊人管理(1)
	
	$("#act_query").click(function(e){
		var paras = $("#form_query").serialize();
		var url = "busrecord/getPatientsList?page=1&type=0&type1=0&" + encodeURI(paras);
		datalist_ctrl.loaddatalist(url);
		return false;
	});
	
	$('input[name=startdate]').datepicker({ dateFormat: 'yy-mm-dd' });
	$('input[name=enddate]').datepicker({ dateFormat: 'yy-mm-dd' });
	$('input[name=startCreateTime]').datepicker({ dateFormat: 'yy-mm-dd' });
	$('input[name=endCreateTime]').datepicker({ dateFormat: 'yy-mm-dd' });
	
});

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
//显示就诊人
function showPatients(patientId){
	document.location.href="busrecord/appreg/showPatient?patientId="+patientId+"&type=0";
}

function closeDialog(id) {
	$("#" + id).dialog("close");
}

/**
 * 加载建档途径项目
 */
function loadBody(){
	var url = 'unpaid/payDictionaryAction_findAll?dictionaryCode='+"business_source";
	var body = $('#patientSource');
	body.empty();
	body.append("<option value=''>全部</option>");
	$.get(url , function(data, status){
		var arr1 = eval(data);
        var arr=eval("("+arr1.data+")");
		for (var i in arr){
			var o = $('<option></option>').val(arr[i].codeNo).html(arr[i].codeName);
			body.append(o.get());
		}
	});
};
