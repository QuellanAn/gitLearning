$(document).ready(function() {
	//datalist_ctrl.loaddatalist();
	//loadinfocats(0);
	datalist_ctrl.loaddatalist("employeesAction/getEmployeeList?page=1");
	
/*	$("#infotype").change(function(e){
		loadinfocats($(this).val());
	});
	*/
	$("#act_query").click(function(e){
		var paras = $("#form_query").serialize();
		var url = "employeesAction/getEmployeeList?page=1&" + paras;
		datalist_ctrl.loaddatalist(url);
		return false;
	});
	function refresh_totalcount(){
		$("#totalcount").html($("#hid_totalcount").val());
		alert($("#hid_totalcount").val());
	}
});

/*function loadinfocats(){
	//var url = "employeesAction/getEmployeeList?sex=" + sex+"&department="+department;
	var url = "employeesAction/getEmployeeList";
	var sel = $('#sex');
	sel.empty();
	if (!sex=="" || sex == 0){
		sel.append("<option value=''>全部</option>");
	}
	$.get(url , function(data, status){
		var jarr = eval(data);
		for (var i in jarr){
			var o = $('<option></option>').val(jarr[i].infoCatCode).html(jarr[i].infoCatName);
			sel.append(o.get());
		
	});
}*/

	function loadinfocats(sex){
		var url = "employeesAction/getEmployeeList?sex=" + sex;
		var sel = $('#sex');
		/*sel.empty();
		if (!sex=="" || sex == 0){
			sel.append("<option value=''>全部</option>");
		}*/
		$.get(url , function(data, status){
			var jarr = eval(data);
			for (var i in jarr){
				var o = $('<option></option>').val(jarr[i].infoCatCode).html(jarr[i].infoCatName);
				sel.append(o.get());
			}
		});
	}	
			
function deletehosinfo(e, p){
	if (confirm("你确认要删除该条记录吗？")){
		
		$.post("employeesAction/deleteNewEmployee",
				{selectOrderNumber:e, page:p},
				function success(data,status){
					if (status = 'success'){
						alert(status)
						var cnt = $('#div_cnt_table');
						cnt.empty();
						cnt.append(data);
					}else{
						//alert('删除失败！');
						showTips('删除失败！',250,2);						
					}
				});
		//datalist_ctrl.loaddatalist("employeesAction/getEmployeeList?page=1");
	}
}

/*var datalist_ctrl = {
		
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
	};*/
/**
 * 加载分页数据
 * @param pageNo 页码
 */
/*function loadPage(pageNo){
	var paras = $("#form_query").serialize();
	var url = "employeesAction/getEmployeeList?pageNo="+pageNo+"&" + paras;
	datalist_ctrl.loaddatalist(url);
}*/


var datalist_ctrl = {
	loaddatalist : function(url) {
		var cnt = $('#div_cnt_table');
		$.get(url, function(data, status) {
			if (data && data.msg) {
				jBox.alert(data.msg, 'error');
			} else if (data) {
				window.setTimeout(function() {
					if (data != null) {
						cnt.empty();
						cnt.append(data);
						$.jBox.tip('数据加载完成。', 'success');
					}
				}, 500);
			}
		});
	},
	topage : function(url) {
		this.loaddatalist(url);
	}
};
