$(document).ready(function() {
	findAllToJson();
	manager.findAll(null, "1");
		
	$("#roleId").change(function(e){
		manager.findAll($(this).val(),"1");
	});
	
	$("input[name=startexpdate]").datepicker({ dateFormat: 'yy-mm-dd' });
	$("input[name=endexpdate]").datepicker({ dateFormat: 'yy-mm-dd' });
	
});

function refresh_unpubcount(){
	$("#totalcount").html($("#hid_totalcount").val());
}

var manager = {
	findAll:function(keywords, pageNo){
		var cnt = $('#div_cnt_table');
		$.post(
			'manager_findAll',
			{keywords:keywords, pageNo:pageNo},
			function(data, status){
			if (data){
				cnt.empty();
				cnt.append(data);
			}
		});
	},
	pageing:function(pageNo){
		var cnt = $('#div_cnt_table');
		$.post(
			'manager_pageing',
			{pageNo:pageNo},
			function(data, status){
			if (data){
				cnt.empty();
				cnt.append(data);
			}
		});
	},
	deleteManager:function(id, pageNo){
		if (confirm("你确定删除吗?")) {
			var cnt = $('#div_cnt_table');
			$.get(
				'manager_modify?manager.status=1&manager.userId=' + id,
				{pageNo:pageNo},
				function(data, status){
				if (data){
					cnt.empty();
					cnt.append(data);
				}
			});
		}
	},
	saveManager:function(span) {
		$(span).parents().submit();
	},
	modifyManager:function(span) {
		$(span).parents().submit();
	},
};

function findAllToJson(){
	var sel = $('#roleId');
	sel.empty();
	sel.append("<option value=''>全部</option>");
	$.post("role_findAllToJson" , function(data, status){
		var jarr = eval(data);
		for (var i in jarr){
			var o = $('<option></option>').val(jarr[i].roleId).html(jarr[i].name);
			sel.append(o.get());
		}
	});
}


