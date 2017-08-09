$(document).ready(function() {
	datalist_ctrl.loaddatalist();
	
	cat_menu_ctrl.loadcatmenu();
	$('#btn_add_pub').click(function(){
		window.location.href = 'hosinfomng/showaddhosinfopage';
	});
	
});

function loadInfo() {
	$.jBox.messager("欢迎登陆后台管理系统", "提示", 2000, {
	    width: 350,
	    icon: 'info',
	    showType: 'show',
	    buttons: { '确认': true },
	    submit: function (v, h, f) {
	        return true;
	    }		
	});
};


function refresh_unpubcount(){
	$("#totalcount").html($("#hid_totalcount").val());
}

function deletehosinfo(e,c, p){
	if (confirm("你确认要删除该条记录吗？")){
		$.post("hosinfomng/deletehosinfo",
				{infoid:e,infocatcode: c, page:p},
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
	}
}

var datalist_ctrl = {
		
	loaddatalist:function(type, code, page){
			var url = "hosinfomng/getunapprovedhospubinfolist";
			
			var existsfirst = false;
			if (type){
				existsfirst = true;
				url += "?infotype=" + type
			}
			if (code){
				if (existsfirst){
					url += "&infocatcode=" + code;
				}else{
					url += "?infocatcode=" + code;
					existsfirst = true;
				}
			}
			if (page){
				if (existsfirst){
					url += "&page=" + page;
				}else{
					url += "?page=" + page;
				}
			}
			
			var cnt = $('#div_cnt_table');
			$.get(url , function(data, status){
				cnt.empty();
				cnt.append(data);
			});
		},

	topage: function(type, code, page){
		this.loaddatalist(type, code, page);
	}
};

var cat_menu_ctrl = {
		
	editmode: false,
	
	loadcatmenu:function(){
		var catMenuUrl = "hosinfomng/getunapprovedcatmenu";
		var catMenuCnt = $('#div_info_cat');
		$.get(catMenuUrl, function(data, status){
			catMenuCnt.empty();
			catMenuCnt.append(data);
		});
	},
	
	cat_onmouseover: function(code){
		if (this.editmode){
			return;
		}
		
		var cat = $("#cat_" + code);
		var inp = $("#inp_" + code);
		var edit = $("#edit_" + code);
		var del = $("#del_" + code);
		var save = $("#save_" + code);
		var cancel = $("#cancel_" + code);
		
		cat.show();
		edit.show();
		del.show();
		
		inp.hide();
		save.hide();
		cancel.hide();
	},
	
	cat_onmouseout: function(code){
		if (this.editmode){
			return;
		}
		
		var cat = $("#cat_" + code);
		var inp = $("#inp_" + code);
		var edit = $("#edit_" + code);
		var del = $("#del_" + code);
		var save = $("#save_" + code);
		var cancel = $("#cancel_" + code);
		
		cat.show();
		
		inp.hide();
		edit.hide();
		del.hide();
		save.hide();
		cancel.hide();
	},
	
	cat_edit_onclick: function(code, name){
		this.editmode = true;
		var cat = $("#cat_" + code);
		var inp = $("#inp_" + code);
		var edit = $("#edit_" + code);
		var del = $("#del_" + code);
		var save = $("#save_" + code);
		var cancel = $("#cancel_" + code);
		
		inp.val(name);
		
		inp.show();
		inp.focus();
		save.show();
		cancel.show();
		
		cat.hide();
		edit.hide();
		del.hide();
		
	},
	
	cat_cancel_onclick:function(code){
		this.editmode = false;
		var cat = $("#cat_" + code);
		var inp = $("#inp_" + code);
		var edit = $("#edit_" + code);
		var del = $("#del_" + code);
		var save = $("#save_" + code);
		var cancel = $("#cancel_" + code);
		
		cat.show();
		
		inp.hide();
		edit.hide();
		del.hide();
		save.hide();
		cancel.hide();
	},
	
	cat_save_onclick:function(code, count){
		var cat = $("#cat_" + code);
		var inp = $("#inp_" + code);
		var edit = $("#edit_" + code);
		var del = $("#del_" + code);
		var save = $("#save_" + code);
		var cancel = $("#cancel_" + code);
		
		if(inp.val().length>4){
			showTips("类别名长度应1－4位",250,2);
			return ;
		}
		
		$.post("hosinfomng/editcatname",
				{infocatcode:code, infocatname:inp.val()},
				function success(data,status){
					if (data){
						data = $.parseJSON(data);
						showTips(data.msg,250,2);
						//alert(data.msg);
					}else{
						cat_menu_ctrl.loadcatmenu();
					}
				});
		
		this.editmode = false;
		
		cat.show();
		
		inp.hide();
		edit.hide();
		del.hide();
		save.hide();
		cancel.hide();
	},
	
	cat_del_onclick:function(code){
		if (!confirm("确认要删除该类别吗？")){
			return;
		}
		
		var cat = $("#cat_" + code);
		var inp = $("#inp_" + code);
		var edit = $("#edit_" + code);
		var del = $("#del_" + code);
		var save = $("#save_" + code);
		var cancel = $("#cancel_" + code);
		
		$.post("hosinfomng/deletecat",
				{infocatcode:code},
				function success(data,status){
					if (data){
						data = $.parseJSON(data);
						showTips(data.msg,250,2);
						//alert(data.msg);
					}else{
						cat_menu_ctrl.loadcatmenu();

					}
				});
		
		cat.show();
		
		inp.hide();
		edit.hide();
		del.hide();
		save.hide();
		cancel.hide();
	},
	
	cat_add_onclick:function(){
		var sel_type = $("#sel_type");
		var cat_name = $("#inp_catname");
		if (cat_name.val().length <= 0){
			//alert("请输入类别名！");
			showTips("请输入类别名！",250,2);
			return;
		}else if(cat_name.val().length>4){
			showTips("类别名长度应1－4位！",250,2);
			return;
		}
		$.post("hosinfomng/addcat",
				{infotype:sel_type.val(), infocatname:cat_name.val()},
				function success(data,status){
					if (data){
						data = $.parseJSON(data);
						showTips(data.msg,250,2);
						//alert(data.msg);
					}else{
						cat_menu_ctrl.loadcatmenu();
					}
				});
	},
	
	show_addwin:function(){
		$("#div_add_win").show();
	}


};




