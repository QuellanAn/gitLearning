$(document).ready(function() {
	loaddepart();
	loaddoctor(0)
	var paras = $("#form_query").serialize();
	datalist_ctrl.loaddatalist("busrecord/getEvaluateList?page=1&" +encodeURI(paras));
	
	$("#depart_name").change(function(e){
		loaddoctor($(this).val());
	});

	
	$("#act_query").click(function(e){
		var paras = $("#form_query").serialize();
		var url = "busrecord/getEvaluateList?page=1&" +encodeURI(paras);
		datalist_ctrl.loaddatalist(url);
		return false;
	});
	
	$('input[name=startdate]').datepicker({ dateFormat: 'yy-mm-dd' });
	$('input[name=enddate]').datepicker({ dateFormat: 'yy-mm-dd' });
});




function loaddepart(){
	var url ="busrecord/getDepartmentNameJson";
	var sel =$('#depart_name');
	$.get(url , function(data, status){
		var jarr = eval(data);
		for (var i in jarr){
			var o = $('<option></option>').val(jarr[i].departmentId).html(jarr[i].departmentName);
			sel.append(o.get());
		}
	});
}

function loaddoctor(type){
//	alert(type);
	var url = "busrecord/getDoctorNameJson?departmentId=" + type;
	var sel = $('#doctor_name');
	sel.empty();
	if (!type=="" || type == 0){
		sel.append("<option value=''>全部</option>");
	}
	$.get(url , function(data, status){
		var jarr = eval(data);
		for (var i in jarr){
			var o = $('<option></option>').val(jarr[i].doctorId).html(jarr[i].doctorName);
			sel.append(o.get());
		}
	});
}
function showQuesstionnaire(QuestionnaireId,AppRegOrderId){
	//...
	$.post("busrecord/showQuestionnaire", {
		"questionnaireId" : QuestionnaireId,
		"appRegOrderId" : AppRegOrderId,
	},  function(data) {
		alert(data);
	}, "json");


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

