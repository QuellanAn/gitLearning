$(document).ready(function() {
	datalist_ctrl.loaddatalist();
	loadPayType();
	loadinfocats();
	loadAccount();
	$("#act_query").click(function(e){
		datalist_ctrl.loaddatalist();
	});
		
	$('input[name=sq_startdate]').datepicker({ dateFormat: 'yy-mm-dd' });
	$('input[name=sq_enddate]').datepicker({ dateFormat: 'yy-mm-dd' });
	$('input[name=tk_startdate]').datepicker({ dateFormat: 'yy-mm-dd' });
	$('input[name=tk_enddate]').datepicker({ dateFormat: 'yy-mm-dd' });
	
});

function refresh_totalcount(){
	$("#totalcount").html($("#hid_totalcount").val());
}


var datalist_ctrl = {
		
	loaddatalist:function(){
			var cnt = $('#div_cnt_table');	
			var tradeNo=$('#tradeNo').val();
			var operatorName=$('#operatorName').val();
			var refundNo=$('#refundNo').val();
			var pattern=$('#pattern').val();
			var refundStatus=$('#refundStatus').val();
			var refundType=$('#refundType').val();
			var tk_startdate=$('input[name=tk_startdate]').val();
			var tk_enddate=$('input[name=tk_enddate]').val();
			var refundId=$('#refundId').val();
			var districtId=$('#districtId').val();
			var account=$('#account').val();
			$.post('unpaid/refundAction_findAll',{
				tradeNo:tradeNo,
				operatorName:operatorName,
				refundNo:refundNo,
				refundType:refundType,
				pattern:pattern,
			    tk_startdate:tk_startdate,
			    tk_enddate:tk_enddate,
			    refundStatus:refundStatus,
				refundId:refundId,
			    districtId:districtId,
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
	loaddatalist1:function(pageNo,tradeNo,refundNo,operatorName,pattern,refundType,sq_startdate,sq_enddate,tk_startdate,tk_enddate,refundStatus){
		var cnt = $('#div_cnt_table');
		$.post('unpaid/refundAction_findAll',{
			tradeNo:tradeNo,
			operatorName:operatorName,
			refundNo:refundNo,
			refundType:refundType,
			pattern:pattern,
			pageNo:pageNo,
			sq_startdate:sq_startdate,
		    sq_enddate:sq_enddate,
		    tk_startdate:tk_startdate,
		    tk_enddate:tk_enddate,
		    refundStatus:refundStatus
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
function findDetails(){
	$.post('unpaid/refundAction_findDetails',{
		tradeNo:tradeNo,
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

function showImg(orderId){
	$.post("unpaid/wXPayAction_findImg",{
		orderId:orderId
	}, function(data) {
		$("#showPatients").html(data);
		$("#showPatients").dialog({
		    width: 800,
		    height:600
		}).dialog("open");
	}, "html");
}



//显示缴费明细
function showPayInfo(billNum,name,money,attach){
	$.post("unpaid/wXPayAction_toPay",{
		billNum:billNum
	}, function(data) {
		$("#showPatients").html(data);
		$("#showPatients").dialog({
		    width: 800,
		    height:600
		}).dialog("open");
	}, "html");
}



function closeDialog(id) {
	$("#" + id).dialog("close");
}
function toSelect(orderId){		
	window.location.href="unpaid/payOrderAction_findDetails?orderId="+orderId;
}

/**
 * 加载支付方式
 */
function loadPayType(){
	var url = "unpaid/payTypeAction_findAll";
	var dis = $('#pattern');
	dis.empty();
	dis.append("<option value=''>全部</option>");
	$.get(url , function(data, status){
		var jarr1 = eval(data);
        var jarr=eval("("+jarr1.data+")");
		for (var i in jarr){
			var o = $('<option></option>').val(jarr[i].ptCode).html(jarr[i].ptName);
			dis.append(o.get());
		}
	});
}

/**
 * 加载院区
 */
function loadinfocats(){
	var url = "unpaid/allYqJson";
	var dis = $('#districtId');
	dis.empty();
	dis.append("<option value=''>全部</option>");
	$.get(url , function(data, status){
		var jarr = eval(data);
		for (var i in jarr){
			var o = $('<option></option>').val(jarr[i].yqId).html(jarr[i].name);
			dis.append(o.get());
		}
	});
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



/**
 * 导出退款订单
 */
function exportRefund(){
	var tradeNo=$('#tradeNo').val();
	var operatorName=$('#operatorName').val();
	var refundNo=$('#refundNo').val();
	var pattern=$('#pattern').val();
	var refundId=$('#refundId').val();
	var districtId=$('#districtId').val();
	var account=$('#account').val();
	var refundStatus=$('#refundStatus').val();
	var refundType=$('#refundType').val();
	var sq_startdate=$('input[name=sq_startdate]').val();
	var sq_enddate=$('input[name=sq_enddate]').val();
	var tk_startdate=$('input[name=tk_startdate]').val();
	var tk_enddate=$('input[name=tk_enddate]').val();
	
	var url = "unpaid/refundAction_exportRefund?1=1";
	url += "&tradeNo=" + tradeNo;
	url += "&operatorName=" + operatorName;
	url += "&refundNo=" + refundNo;
	url += "&pattern=" + pattern;
	url += "&refundStatus=" + refundStatus;
	url += "&refundType=" + refundType;
	url += "&tk_startdate=" + tk_startdate;
	url += "&tk_enddate=" + tk_enddate;
	url += "&refundId=" + refundId;
	url += "&districtId=" + districtId;
	url += "&account=" + account;
	
	document.location.href = url;
}