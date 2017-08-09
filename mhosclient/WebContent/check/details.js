$(document).ready(function() {
	var createDate=$("#createDate").val();	
	var accountCode=$("#accountCode").val();	
	datalist_ctrl.loaddatalist("unpaid/checkBillAction_findDetails?createDate="+createDate+"&accountCode="+accountCode);
	$("#act_query").click(function(e){
		var billStatus=$('#billStatus').val();
		var createDate=$("#createDate").val();	
		var accountCode=$("#accountCode").val();	
		var handle=$('#handle').val();
		datalist_ctrl.loaddatalist("unpaid/checkBillAction_findDetails?createDate="+createDate+"&status="+handle+"&billStatus="+billStatus+"&accountCode="+accountCode);
	});	
});

function refresh_totalcount(){
	$("#totalcount").html($("#hid_totalcount").val());
}


var datalist_ctrl = {
		
	loaddatalist:function(url){
			var cnt = $('#div_cnt_table');
			$.get(url,
			function(data, status){
				if (data && data.msg){
					alert(data.msg);
				}else if (data){
					cnt.empty();
					cnt.append(data);
				}
			});
		},
	topage:function(pageNo,createDate,handle,billStatus,accountCode){
		
		var cnt = $('#div_cnt_table');
		$.post('unpaid/checkBillAction_findDetails',{
			pageNo:pageNo,
			createDate:createDate,
			status:handle,
			billStatus:billStatus,
			accountCode:accountCode
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

//全选/取消全选
function dosubmit(name,id){
	var names=document.getElementById(id);
	
	if(names.checked==true){
		var names=document.getElementsByName(name);
		var len=names.length;
		if(len>0){
			for(var i=0;i<len;i++){
				names[i].checked=true;
			}
		}
	}else{
		var names=document.getElementsByName(name);
		var len=names.length;
		if(len>0){
			for(var i=0;i<len;i++){
				names[i].checked=false;
			}
		}
	}
}


var arr;
var ids="";
function checkById(){
	arr=document.getElementsByName("check.ids");
	for(var i=0;i<arr.length;i++){
		if(arr[i].checked==true){
			ids+=arr[i].value+",";
		}
	}
}

//批量校验
function batchCheck(){
	checkById();
	var pageNo=document.getElementById("pageNo").value;
	var hid_pattern=document.getElementById("hid_pattern").value;
	var hid_createDate=document.getElementById("hid_createDate").value;
	var hid_billStatus=document.getElementById("hid_billStatus").value;
	if(ids.length==0){
		alert("请至少选择一项！");
	}else{
		if(confirm("您确定要批量校验吗？")){
			verify(ids,pageNo,hid_pattern,hid_createDate,hid_billStatus);
		}
	}
	ids="";
}

//导出
function exportExcel(){
	var billStatus=$('#hid_billStatus').val();
	var pattern=$('#hid_pattern').val();
	var createDate=$('#hid_createDate').val();
	
//	window.location.href="unpaid/checkBillAction_exportDetailExcel?pattern="+pattern+"&billStatus="+billStatus+"&createDate="+createDate;
	window.location.href="unpaid/checkBillAction_exportCheckBillDetail?pattern="+pattern+"&billStatus="+billStatus+"&createDate="+createDate;
}


//校验
//function verify(tradeNo,page,pattern,createDate,billStatus,billDatailsId,payStatus,accountCode){
//	var cnt = $('#div_cnt_table');
//	var billStatus=$('#hid_billStatus').val();
//	var pattern=$('#hid_pattern').val();
//	LoadingToast("正在校验");
//	$.post('unpaid/checkBillAction_verify',{
//		pageNo:page,
//		createDate:createDate,
//		pattern:pattern,
//		billStatus:billStatus,
//		tradeNos:tradeNo,
//		id:billDatailsId,
//		payStatus:payStatus,
//		accountCode:accountCode
//	},
//		function(data, status){
//		    HiddenToast();
//			$("#node").html(data);
//			$("#node").dialog({
//	    		modal: true,
//	    		resizable:false,
//	    		title: "对账异常处理", 
//				width: 700,
//				height:450
//			});
//		}, "html");
//}
/**
 * 校验
 */
function verify(tradeNo,page,pattern,billDate,billStatus,billDatailsId,status,accountCode){
	LoadingToast("正在校验");
	$.post('unpaid/checkBillAction_verify',{
		pageNo:page,
		createDate:billDate,
		pattern:pattern,
		billStatus:billStatus,
		tradeNos:tradeNo,
		id:billDatailsId,
		status:status,
		accountCode:accountCode
	},
		function(data, status){
		    HiddenToast();
			$("#node").html(data);
			$("#node").dialog({
	    		modal: true,
	    		resizable:false,
	    		title: "对账异常处理", 
				width: 700,
				height:450
			});
		}, "html");
}


function findDetails(id){
//	window.location.href="unpaid/checkBillAction_findCheckDetails?outTradeNo="+outTradeNo;
	window.location.href="unpaid/checkBillAction_findCheckDetails?id="+id;
}
function findRefundDetails(id){

//	showTips('正在加载，请稍等', 200,5);
//	window.location.href="unpaid/checkBillAction_findCheckRefund?outTradeNo="+outTradeNo;
	window.location.href="unpaid/checkBillAction_findCheckRefund?id="+id;
}
