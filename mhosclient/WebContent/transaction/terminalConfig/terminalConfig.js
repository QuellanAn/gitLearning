$(document).ready(function() {
	
	loadinfocats();
	loadPayScene();
	$.jBox.tip("数据加载中...", 'loading');
	datalist_ctrl.loaddatalist("unpaid/payTerAction_findAllInfo?pageNo="+$("#hid_pageNo").val());
	$("#act_query").click(function(e){
		var paras = $("#form_query").serialize();
		var pageNo=$("#hid_pageNo").val();
		var url = "unpaid/payTerAction_findAllInfo?pageNo="+pageNo+"&" + paras;
		datalist_ctrl.loaddatalist(url);
		return false;
	});
	
});

function refresh_totalcount(){
	$("#totalcount").html($("#hid_totalcount").val());
}

var datalist_ctrl = {
		loaddatalist:function(url){
				var cnt = $('#div_cnt_table');
				$.get(url , function(data, status){
					if (data && data.msg){
						jBox.alert(data.msg,'error');
					}else if (data){
						
						window.setTimeout(function () { 
							if(data!=null){
								cnt.empty();
								cnt.append(data);
								$.jBox.tip('数据加载完成。', 'success');
							}
							}, 2000);
					}
				});
			},
		topage: function(url){
			this.loaddatalist(url);
		}
	};

function updateTerminal(id){
	document.location.href="unpaid/payTerAction_toUpdate?id="+id;
};

function deleteTerminal(id){
	/*if (confirm("你确定删除该信息吗？")){
		$.post("unpaid/payTerAction_delete",
				{id:id},
				function success(data,status){
					if (status = 'success'){
						var cnt = $('#div_cnt_table');
						cnt.empty();
						cnt.append(data);
					}else{
						//alert('删除失败！');
						showTips('删除失败！',250,2);
						
					}
				});
	}*/
	var submit = function (v, h, f) {
	    if (v == 'ok') {
	    	$.post("unpaid/payTerAction_delete",
					{id:id},
					function success(data,status){
						//$.jBox.tip("正在删除数据...", 'loading');
						if (status = 'success'){
							var cnt = $('#div_cnt_table');
							 cnt.empty();
							 cnt.html(data);
//							 window.setTimeout(function () { 
//								 var cnt = $('#div_cnt_table');
//								 cnt.empty();
//								 cnt.append(data);
//								 $.jBox.tip('删除成功。', 'success'); 
//								 }, 2000);
							
						}else{
//							showTips('删除失败！',250,2);
							window.setTimeout(function () { 
								$.jBox.tip('删除成功。', 'error'); }, 2000);
						}
					});
	    }
	    else if (v == 'cancel') {
	    	jBox.tip("取消", 'info');
	    }

	    return true; //close
	};
	$.jBox.confirm("确定要删除数据吗？", "提示", submit)
};

function toAdd() {
	document.location.href="unpaid/payTerAction_toSave";
}

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