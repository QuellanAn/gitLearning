$(document).ready(function() {
	datalist_ctrl.loaddatalist();
	$("#act_query").click(function(e){	
		var cnt = $('#div_cnt_table');	
		var out_trade_no=$('#out_trade_no').val();
		var orderCode=$('#orderCode').val();
		var hisTransId=$('#hisTransId').val();
		if(out_trade_no.length==0&&orderCode.length==0&&hisTransId.length==0){
			alert("请输入查询条件");	
			return;
		}
		datalist_ctrl.loaddatalist();
	});
});

function refresh_totalcount(){
	$("#totalcount").html($("#hid_totalcount").val());
}


var datalist_ctrl = {
		
	loaddatalist:function(){
			var cnt = $('#div_cnt_table');	
			var out_trade_no=$('#out_trade_no').val();
			var orderCode=$('#orderCode').val();
			var hisTransId=$('#hisTransId').val();
			
			if(out_trade_no.length==0&&orderCode.length==0&&hisTransId.length==0){
				return;
			}
			
			$.post('unpaid/payOrderAction_findAll',{
				out_trade_no:out_trade_no,
				orderCode:orderCode,
				hisTransId:hisTransId,
				flag:1
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
/*	loaddatalist1:function(url){
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
	},*/
	loaddatalist1:function(pageNo,payType,startdate,enddate,status,pattern,districtId,out_trade_no,hisTransId,orderCode,body){
		var cnt = $('#div_cnt_table');
		$.post('unpaid/payOrderAction_findAll',{
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
			pageNo:pageNo
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

function toRefund(orderId,pattern,outTradeNo){		
	window.location.href="unpaid/payOrderAction_findDetails?flag=1&orderId="+orderId;
}	

function toSelect(orderId){		
	window.location.href="unpaid/payOrderAction_findDetails?orderId="+orderId;
}
function refund(tradeNo,amount,pattern){
	LoadingToast("正在校验，请稍等....");
	$.post('unpaid/outOrderQueryAction_findOrderJson',{
		tradeType:0,
		outTradeNo:tradeNo,
		pattern:pattern
			},
      	function(data){
		HiddenToast();
		var obj=eval(data);
		if (obj.code==0){
		var obj1=eval("("+obj.data+")");
		if(obj1.payStatus.indexOf("支付成功") >= 0 ){  
			if(confirm("是否已确认该订单在HIS的状态，符合退款条件")){
				window.location.href="unpaid/refundAction_toRefund?tradeNo="+tradeNo+"&amount="+amount;	
			}
		}else{
		    alert("该订单"+obj1.payStatus); 
		} 
		}else if (obj.code==1){
			alert("验证失败，无法退款");
		}
	});
}