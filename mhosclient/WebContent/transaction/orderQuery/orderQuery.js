$(document).ready(function() {	
	
	loadPayType();
	
	//单选按钮点击事件
	$("input[name='tradeType']").click(function(){
		value= $(":radio[name='tradeType']:checked").val();
		if(value==1){
			document.getElementById('tr_refund').style.display="block";
		}else{
			document.getElementById('tr_refund').style.display="none";
			document.getElementById("tr_trade").style.display="block";
		}
	
	});
	$("#act_query").click(function(e){
		LoadingToast("正在查询");
		var pattern=$("#pattern").val();
		var tradeType=$("input[name='tradeType']:checked").val();
		var outTradeNo=$('#outTradeNo').val();
		var transactionId=$('#transactionId').val();
		var refundNo=$('#refundNo').val();
		var refundId=$('#refundId').val();
		if(tradeType=="0"){
			if(outTradeNo.length==0&&transactionId.length==0){
				HiddenToast();
				alert("请输入查询条件");
				return;
			}
			datalist_ctrl.loaddatalist(pattern, tradeType, outTradeNo, transactionId,"", "");
		}else if(tradeType=="1"){
			if(refundNo.length==0&&refundId.length==0&&outTradeNo.length==0&&transactionId.length==0){
				HiddenToast();
				alert("请输入查询条件");
				return;
			}
			datalist_ctrl.loaddatalist(pattern, tradeType,outTradeNo,transactionId, refundNo, refundId);
		}
	
	});
	
	$("#act_query1").click(function(e){
		LoadingToast("正在查询");
		var pattern=$("#pattern").val();
		var tradeType=$("input[name='tradeType']:checked").val();
		var refundNo=$('#refundNo').val();
		var refundId=$('#refundId').val();
		if(refundNo.length==0&&refundId.length==0){
			HiddenToast();
			alert("请输入查询条件");
			return;
		}
		datalist_ctrl.loaddatalist(pattern, tradeType, "", "", refundNo, refundId);
	});
	
});
function refresh_totalcount(){
	$("#totalcount").html($("#hid_totalcount").val());
}

var datalist_ctrl = {
		
	loaddatalist:function(pattern,tradeType,outTradeNo,transactionId,refundNo,refundId){
			var cnt = $('#div_cnt_table');	
			$.post('unpaid/outOrderQueryAction_findOrder',{
				pattern:pattern,
				tradeType:tradeType,
				outTradeNo:outTradeNo,
				transactionId:transactionId,
				refundNo:refundNo,
				refundId:refundId
			},
		      	function(data, status){
				HiddenToast();
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
	loaddatalist1:function(url){
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
		
		
};
function refund(){
	
	window.location.href="payorder/refund.jsp";
}

/**
 * 加载支付方式
 */
function loadPayType(){
	var url = "unpaid/payTypeAction_findAll";
	var dis = $('#pattern');
	dis.empty();
	$.get(url , function(data, status){
		var jarr1 = eval(data);
        var jarr=eval("("+jarr1.data+")");
		for (var i in jarr){
			var o = $('<option></option>').val(jarr[i].ptCode).html(jarr[i].ptName);
			dis.append(o.get());
		}
	});
}