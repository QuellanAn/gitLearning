$(document).ready(function() {
	datalist_ctrl.loaddatalist("busrecord/getPayList?page=1");
	
	$("#act_query").click(function(e){
		var paras = $("#form_query").serialize();
		var url = "busrecord/getPayList?page=1&" + encodeURI(paras);
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
function showPatients(patientId, docBillSn){
	document.location.href="busrecord/appreg/showPayPatient?patientId="+patientId+"&docBillSn="+docBillSn;
	
	/*$.post("busrecord/appreg/showPayPatient",{
		"patientId" : patientId,
		"docBillSn":docBillSn
	}, function(data) {
		$("#showPatients").html(data);
		$("#showPatients").dialog({
		    width: 850,
		    height: 400
		}).dialog("open");
	}, "html");*/
}

//显示缴费明细
function showPayInfo(docBillSn){
	document.location.href="busrecord/pay/showPayInfo?docBillSn="+docBillSn;
	
	/*$.post("busrecord/pay/showPayInfo",{
		"docBillSn" : docBillSn
	}, function(data) {
		$("#showPatients").html(data);
		$("#showPatients").dialog({
		    width: 850,
		    height: 400
		}).dialog("open");
	}, "html");*/
}



function closeDialog(id) {
	$("#" + id).dialog("close");
}
