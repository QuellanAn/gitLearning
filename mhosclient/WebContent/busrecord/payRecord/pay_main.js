$(document).ready(function() {
   
	datalist_ctrl.loaddatalist();
	loadinfocats();
	loadPayType();
	loadBody();
	loadPayStatus();
	loadAccount();
	loadPayScene();
	$("#act_query").click(function(e){
		datalist_ctrl.loaddatalist();
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
		
	loaddatalist:function(){
			var cnt = $('#div_cnt_table');	
			var payType=$('#payType').val();
			var body=$('#body').val();
			var startdate=$('input[name=startdate]').val();	
			var enddate=$('input[name=enddate]').val();
			var status=$('#status').val();
			var pattern=$('#pattern').val();
			var districtId=$('#districtId').val();
			var cardNo=$('#cardNo').val();
			var patientname=$('#patientname').val();
			
			var out_trade_no=$('#out_trade_no').val();
			var orderCode=$('#orderCode').val();
			var hisTransId=$('#hisTransId').val();
			var payScene=$('#paySence').val();
			
			$.post('unpaid/payOrderAction_findAllPayOrRecharge',{
				payType:payType,
				startdate:startdate,
				enddate:enddate,
				status:status,
				pattern:pattern,
				districtId:districtId,
				out_trade_no:out_trade_no,
				hisTransId:hisTransId,
				orderCode:orderCode,
				body:body,
				patientname:patientname,
				cardNo:cardNo,
				payScene:payScene,
				redirectType:"1"
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
	loaddatalist1:function(pageNo,payType,startdate,enddate,status,
			pattern,districtId,out_trade_no,hisTransId,orderCode,body,cardNo,patientName,payScene){
		var cnt = $('#div_cnt_table');
		/*
		 * alert(url); $.get(url, function(data, status){ if (data && data.msg){
		 * alert(data.msg); }else if (data){ cnt.empty(); cnt.append(data); }
		 * });
		 */
		var cardNo=$('#cardNo').val();
		//var payScene=$('#payScene').val();
		var patientName=$('#patientname').val();
		
		$.post('unpaid/payOrderAction_findAllPayOrRecharge',{
			payType:payType,
			startdate:startdate,
			enddate:enddate,
			status:status,
			pattern:pattern,
			districtId:districtId,
			out_trade_no:out_trade_no,
			hisTransId:hisTransId,
			orderCode:orderCode,
			body:body,
			cardNo:cardNo,
			patientname:patientName,
			pageNo:pageNo,
			payScene:payScene,
			redirectType:"1"
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


// 显示缴费明细
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


function refund(tradeNo,amount){
	
	/* window.location.href="payorder/refund.jsp"; */
	window.location.href="unpaid/refundAction_toRefund?tradeNo="+tradeNo+"&amount="+amount;
}

function toRefund(orderId,pattern,outTradeNo){		
	window.location.href="unpaid/payOrderAction_findDetails?flag=1&orderId="+orderId;
}

function toSelect(orderId){		
	window.location.href="unpaid/payOrderAction_findPayOrRechargeDetails?orderId="+orderId+"&redirectType=1";
}

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
 * 加载缴费项目
 */
function loadBody(){
	var url = 'unpaid/payDictionaryAction_findAll?dictionaryCode='+"payment_project";
	var body = $('#body');
	body.empty();
	body.append("<option value=''>全部</option>");
	$.get(url , function(data, status){
		var arr1 = eval(data);
        var arr=eval("("+arr1.data+")");
		for (var i in arr){
			var o;
			if(arr[i].codeNo != "3"){//排除就诊卡充值（3）
				o = $('<option></option>').val(arr[i].codeName).html(arr[i].codeName);
			}
			body.append(o.get());
		}
	});
};

/**
 * 加载支付状态
 */
function loadPayStatus(){
	var url = 'unpaid/payDictionaryAction_findAll?dictionaryCode='+"transaction_status";
	var payStatus = $('#status');
	payStatus.empty();
	payStatus.append("<option value=''>全部</option>");
	$.get(url , function(data, status){
		var arr1 = eval(data);
        var arr=eval("("+arr1.data+")");
		for (var i in arr){
			var o = $('<option></option>').val(arr[i].codeNo).html(arr[i].codeName);
			payStatus.append(o.get());
		}
	});
};

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
 * 加载场景
 */
function loadPayScene(){
	var url = 'unpaid/paySceneAction_findAllJson';
	var payScene = $('#paySence');
	payScene.empty();
	payScene.append("<option value=''>全部</option>");
	$.get(url , function(data, status){
		var jarr1 = eval(data);
        var jarr=eval("("+jarr1.data+")");
		for (var i in jarr){
			var o = $('<option></option>').val(jarr[i].SCode).html(jarr[i].SName);
			payScene.append(o.get());
		}
	});
};



/**
 * 导出交易订单
 */
function exportPayOrder(){
	var payType=$('#payType').val();
	var body=$('#body').val();
	var startdate=$('input[name=startdate]').val();	
	var enddate=$('input[name=enddate]').val();
	var status=$('#status').val();
	var pattern=$('#pattern').val();
	var districtId=$('#districtId').val();
	
	var url = "unpaid/payOrderAction_exportPayOrder?1=1";
	url += "&payType=" + payType;
	url += "&body=" + body;
	url += "&startdate=" + startdate;
	url += "&enddate=" + enddate;
	url += "&status=" + status;
	url += "&pattern=" + pattern;
	url += "&districtId=" + districtId;
	
	document.location.href = url;
}