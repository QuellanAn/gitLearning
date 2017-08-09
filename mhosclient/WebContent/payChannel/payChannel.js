$(document).ready(function() {
	loadinfocats();
	loadPayScene();
	 loadPayType();
	 loadAccount();
	datalist_ctrl.loaddatalist("","","","","","");
	$("#act_query").click(function(e){
	/*	var paras = $("#form_query").serialize();
		var pageNo=$("#hid_pageNo").val();
		var url = "unpaid/payTerAction_findAllInfo?"+ paras;*/
		var yqID=$("#district").val();
		var sCode=$('#payScene').val();
		var ptCode=$('#payType').val();
		var accountCode=$('#account').val();
		var cStatus=$('#cStatus').val();
		datalist_ctrl.loaddatalist("",yqID,sCode,ptCode,accountCode,cStatus);
	});
	
});

function refresh_totalcount(){
	$("#totalcount").html($("#hid_totalcount").val());
}

var datalist_ctrl = {
		loaddatalist:function(pageNo,yqID,sCode,ptCode,accountCode,cStatus){
				var cnt = $('#div_cnt_table');
				$.post("unpaid/payChannelAction_findAll" ,{
					yqID:yqID,
					sCode:sCode,
					ptCode:ptCode,
					accountCode:accountCode,
					cStatus:cStatus,
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
		topage: function(url){
			this.loaddatalist(url);
		}
	};


function toAdd() {
	document.location.href="unpaid/payChannelAction_toAdd";
}
function toModify(cID) {
	document.location.href="unpaid/payChannelAction_toModify?cID="+cID;
}
/**
 * 加载院区
 */
function loadinfocats(){
	var url = "unpaid/allYqJson";
	var dis = $('#district');
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
 * 加载场景
 */
function loadPayScene(){
	var url = 'unpaid/paySceneAction_findAllJson';
	var payScene = $('#payScene');
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
 * 加载支付方式
 */
function loadPayType(){
	var url = "unpaid/payTypeAction_findAll";
	var dis = $('#payType');
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
