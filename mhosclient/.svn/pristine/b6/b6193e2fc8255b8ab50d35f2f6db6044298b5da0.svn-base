$(document).ready(function() {
	//initDepartment();
	loadBody();
	loadEvaluate();
	var paras = $("#form_query").serialize();
	datalist_ctrl.loaddatalist("busrecord/getEvaluateHosList?page=1&" +encodeURI(paras));
	
	$("#depart_name").change(function(e){
		loaddoctor($(this).val());
	});

	
	$("#act_query").click(function(e){
		var paras = $("#form_query").serialize();
		var url = "busrecord/getEvaluateHosList?page=1&" +encodeURI(paras);
		datalist_ctrl.loaddatalist(url);
		return false;
	});
	
	$('input[name=startdate]').datepicker({ dateFormat: 'yy-mm-dd' });
	$('input[name=enddate]').datepicker({ dateFormat: 'yy-mm-dd' });
});






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

function initDepartment(){
	var sel = $('#departmentCode');
	sel.empty();
	sel.append("<option value=''>全部</option>");
	$.post("config/department/findAllDeptment" , function(data, status){
		var obj=eval('('+data+')');
		var jarr = obj.depts;
		for (var i in jarr){
			var o = $('<option></option>').val(jarr[i].departmentId).html(jarr[i].departmentName);
			sel.append(o.get());
		}
	});
	
};

function findDetail(serviceEvaluationId){
	document.location.href="busrecord/findEvaluateHosDetail?serviceEvaluationId="+serviceEvaluationId;
	
	/*$.post('busrecord/findEvaluateHosDetail',
     {serviceEvaluationId:serviceEvaluationId},
	function(data, status) { 
    
    	 $("#node").empty();
		$("#node").html(data);
		$("#node").dialog({
			modal : true,
			resizable : false,
			title : "查看评价",
			width : 600,
			height : 550
		});
	}, "html");*/
};

/**
 * 加载评价途径项目
 */
function loadBody(){
	var url = 'unpaid/payDictionaryAction_findAll?dictionaryCode='+"business_source";
	var body = $('#evaSource');
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
/**
 * 加载总体评价
 */
function loadEvaluate(){
	var url = 'unpaid/payDictionaryAction_findAll?dictionaryCode='+"service_evaluation";
	var body = $('#overallEvaluation');
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