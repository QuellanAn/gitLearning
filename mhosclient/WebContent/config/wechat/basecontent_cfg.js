$(document).ready(function() {
	datalist_ctrl.loaddatalist("config/basic/getBaseContentList?page=1");
	
	$("#act_query").click(function(e){
		var paras = $("#form_query").serialize();
		var url = "config/basic/getBaseContentList?page=1&" + paras;
		datalist_ctrl.loaddatalist(url);
		return false;
	});
	
	$("input[name=startdate]").datepicker({ dateFormat: 'yy-mm-dd' });
	$("input[name=enddate]").datepicker({ dateFormat: 'yy-mm-dd' });
	
});

function refresh_unpubcount(){
	$("#totalcount").html($("#hid_totalcount").val());
}

var datalist_ctrl = {
		
		loaddatalist:function(url){
				var cnt = $('#div_cnt_table');
				$.get(url , function(data, status){
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

function updateBase(cat){
	document.location.href="config/basic/toUpdateBase?cat="+cat;
};

function deleteBaseCon(cat){
	if (confirm("你确定删除该信息吗？")){
		$.post("config/basic/deleteBaseContent",
				{cat:cat},
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
	}
};



