$(document).ready(function() {            
	datalist_ctrl.loaddatalist("busrecord/getPatientsUserList?page=1&type1=1");//type用于区分是建档(0)还是绑卡(1)，type1是区分跳转在线建档记录(0)还是就诊人管理(1)
	
	$("#act_query").click(function(e){
		var paras = $("#form_query").serialize();
		var url = "busrecord/getPatientsUserList?page=1&type1=1&" + encodeURI(paras);
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
function showPatientUser(patientId){
	document.location.href="busrecord/appreg/showPatientUser?patientId="+patientId;
};

function closeDialog(id) {
	$("#" + id).dialog("close");
};
function updateStatus(){
	var status = $("#status").val();
	var pid = $("#pid").val();
	document.location.href="busrecord/appreg/updatePatientOrUser?type=0&status="+status+"&patientId="+pid;//0表示对patient的操作，1表示对微信账户操作
	//alert(status);
};
function updateAllStatus(){
	var patientId = getChickIds("patientId");
	//alert(patientId);
	/*if(patientId == ""){
		return;
	}else{
		$.post(
				"busrecord/appreg/updateAllPatientOrUser",
				{
					type:"0",
					patientId:patientId
				},
				function(data) {
					//alert(data);
				}
			);
	}*/
	//window.location.href="busrecord/appreg/updateAllPatientOrUser?type=0&patientId="+patientId;
	
	document.location.href="busrecord/appreg/updateAllPatientOrUser?type=0&patientId="+patientId;
	return false;
}