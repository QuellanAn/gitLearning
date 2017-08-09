$(document).ready(function() {
	loadAccount();
	showCont();
	
	$("#act_querybill").click(function(e){
		var paras = $("#form_query").serialize();
		datalist_ctrl.loaddatalist("unpaid/checkRecordAction_findAll?"+paras);
	});
	
	$("input[name=recordType]").change(function() {
		showCont();
	});
	
	$('input[name=startdate]').datepicker({ dateFormat: 'yy-mm-dd' });
	$('input[name=enddate]').datepicker({ dateFormat: 'yy-mm-dd' });
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
		loaddatalist1:function(pageNo,recordType,transactionNum,orderCode,hisTransId,startdate,enddate,account){
			var cnt = $('#div_cnt_table');
			$.post("unpaid/checkRecordAction_findAll",{
				pageNo:pageNo,
		        recordType:recordType,
				transactionNum:transactionNum,
				orderCode:orderCode,
				hisTransId:hisTransId,
				startdate:startdate,
				enddate:enddate,
				account:account
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
	topage: function(url){
		this.loaddatalist(url);
	},	
};

function showCont() {
	var val=$('input:radio[name="recordType"]:checked').val();
	if(val==0){
		$("#TdH").hide();
		$("#TdNum").hide();
		$("#danTd").hide();
		$("#riJieTd").show()
		$("input[name='recordType']").eq(0).attr("checked","checked");
		$("input[name='recordType']").eq(1).removeAttr("checked");
	}
	if(val==1){
		$("#TdH").show();
		$("#TdNum").show();
		$("#danTd").show();
		$("#riJieTd").hide();
		$("input[name='recordType']").eq(1).attr("checked","checked");
		$("input[name='recordType']").eq(0).removeAttr("checked");
	}
	datalist_ctrl.loaddatalist("unpaid/checkRecordAction_findAll?recordType="+val);
	document.getElementById('form_query').reset();
}

function findInfo(oId) {
	window.location.href="unpaid/checkRecordAction_findInfoByOId?transactionNum="+oId;
}

function findDetails(outTradeNo){
	showTips('正在加载，请稍后', 200,5);
	window.location.href="unpaid/checkBillAction_findCheckDetails?outTradeNo="+outTradeNo;
	
}
function findRefundDetails(outTradeNo){
	showTips('正在加载，请稍后', 200,5);
	window.location.href="unpaid/checkBillAction_findCheckRefund?outTradeNo="+outTradeNo;
}

/**
 * 加载资金账户
 */
function loadAccount(){
	var url = 'unpaid/accountConfigAction_findAllJson';
	var account = $('#account');
	account.empty();
	account.append("<option value=''>全部</option>");
	$.get(url , function(data, status){
		var jarr1 = eval(data);
        var jarr=eval("("+jarr1.data+")");
		for (var i in jarr){
			var o = $('<option></option>').val(jarr[i].accountCode).html(jarr[i].accountName);
			account.append(o.get());
		}
	});
};