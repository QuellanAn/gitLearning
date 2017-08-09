$(document).ready(function() {
	
	loadPayType();
	loadAccount();
	

	
	/*//单选按钮点击事件
	$("input[name='checkType']").click(function(){
		value= $(":radio:checked").val();
		if(value==1){
			document.getElementById('pattern').disabled=true;
			document.getElementById("pattern").options[0].selected=true;
		}else{
			document.getElementById('pattern').disabled=false;
		}
	
	});*/
//查询点击事件
	$("#act_query").click(function(e){
		var startdate=$('input[name=startDate]').val();
		var enddate=$('input[name=endDate]').val();
		var checkStatus=$('#checkStatus').val();
	//	var pattern=$('#pattern').val();
		var account=$('#account').val();
		var status=$('#status').val();
		datalist_ctrl.loaddatalist("unpaid/checkBillAction_findAll?startDate="+startdate+"&endDate="+enddate+"&accountCode="+account+"&checkStatus="+checkStatus+"&status="+status);
	});
//导出
	$("#export").click(function(e){
		var startdate=$('#hid_startdate').val();
		var enddate=$('#hid_enddate').val();
		var billStatus=$('#hid_billStatus').val();
//		window.location.href="unpaid/checkBillAction_exportExcel?startdate="+startdate+"&enddate="+enddate+"&billStatus="+billStatus;
		window.location.href="unpaid/checkBillAction_exportCheckBill?startdate="+startdate+"&enddate="+enddate+"&billStatus="+billStatus;
	});
	
	$('input[name=startDate]').datepicker({ dateFormat: 'yy-mm-dd' });
	$('input[name=endDate]').datepicker({ dateFormat: 'yy-mm-dd' });	
	var beginDate = getDayAdd((-1) * (2 - 1));
	$('input[name=startDate]').val(beginDate);
	$('input[name=endDate]').val(beginDate);
	var startdate=$('input[name=startDate]').val();
	var enddate=$('input[name=endDate]').val();
	datalist_ctrl.loaddatalist("unpaid/checkBillAction_findAll?startDate="+startdate+"&endDate="+enddate);
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
	topage: function(url){
		this.loaddatalist(url);
	},	
};

//点击待确认
function toConfirm(id,page,startDate,endDte,checkStatus,accountCode,status,billDate){
	$.post("unpaid/checkBillAction_existUnhandled",{
		accountCode:accountCode,
		createDate:billDate
	}, function(data) {
			var  obj=eval(data);	
			var str="";
			if(obj.code==0){
			   str="在对账列表中还存在未处理的异常订单!\n";
			}
			if(confirm(str+"你确定要“确认”此账单的当前状态吗？")){
			$.post("unpaid/checkBillAction_confirm",{
				id:id,
				startDate:startDate,
				endDte:endDte,
				accountCode:accountCode,
				checkStatus:checkStatus,
				pageNo:page,
				status:status
			}, function(data) {
					var cnt = $('#div_cnt_table');
					cnt.empty();
					cnt.append(data);			
			});
		}	
	});
}

function closeDialog(id) {
	$("#" + id).dialog("close");
}

function findDetails(createDate,accountCode){
	window.location.href="unpaid/checkBillAction_toFindDetails?createDate="+createDate+"&accountCode="+accountCode;
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




function exports() {
	window.location.href="checkImportQuery/import_main.jsp";
}


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

/**
 * 重新对账
 */
var arr;
var ids="";
function recheck(){
	arr=document.getElementsByName("ids");
	for(var i=0;i<arr.length;i++){
		if(arr[i].checked==true){
			ids+=arr[i].value+",";
		}
	}
	if(ids!=""){
		$.post("unpaid/checkBillAction_recheck",{
			ids:ids
		},function(data){
			if(data.code==0){//成功
				alert("重新对账成功！");
				window.location.href="check/check_main.jsp";
			}else{
				alert("重新对账失败！");	
			}
		});
		ids="";
	}else{
		alert("未选择");
	}
	
}


