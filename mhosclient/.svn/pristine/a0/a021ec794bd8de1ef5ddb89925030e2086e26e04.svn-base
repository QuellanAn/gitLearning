$(document).ready(function() {

	datalist_ctrl.loaddatalist($("#hid_pageNo").val());
	$("#act_query").click(function(e){
		datalist_ctrl.loaddatalist();
	});
					
});

function refresh_totalcount(){
	$("#totalcount").html($("#hid_totalcount").val());
}


var datalist_ctrl = {
		
	loaddatalist:function(pageNo){
			var cnt = $('#div_cnt_table');	
		
			$.get('unpaid/yQConfigAction_findAll?pageNo='+pageNo,
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

//删除
function toDelete(yqCode,pageNo){
	
	if(confirm("你确定要删除吗？")){	
	var cnt = $('#div_cnt_table');	
	
	$.post('unpaid/yQConfigAction_delete',{
		yqCode:yqCode,
		pageNo:pageNo
	},
		function(data, status){
			if (data && data.msg){
				alert(data.msg);
			}else if (data){
				alert("删除成功！");
				cnt.empty();
				cnt.append(data);
			}
		});
	}
}

//删除
function toModifyYQ(yqCode,pageNo){
	
	var cnt = $('#div_cnt_table');	
	
	$.post('unpaid/yQConfigAction_modifyYQ',{
		yqCode:yqCode,
		pageNo:pageNo
	},
		function(data, status){
			if (data && data.msg){
				alert(data.msg);
			}else if (data){
				alert("删除成功！");
				cnt.empty();
				cnt.append(data);
			}
		});
}


function refund(){
	
	window.location.href="payorder/refund.jsp";
}

//跳转到添加界面
function toAddYQ(){		
	document.location.href="unpaid/yQConfigAction_toSave";
}

//跳转到编辑界面
function toModifyYQ(yqCode,pageNo){		
	window.location.href="unpaid/yQConfigAction_toModify?yqCode="+yqCode+"&pageNo="+pageNo;
}