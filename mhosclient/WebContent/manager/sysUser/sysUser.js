$(document).ready(
		function() {
			//显示管理菜单
			$('.menu-item-cnt').attr("style", "display:none;");
			$('.menu-item-cnt:eq(4)').attr("style", "display:block");
			
			datalist_ctrl.loaddatalist("manager/sysUser/getSysUsers?page=1");
			
			$.post('manager/sysUser/findAllRoles',function(data){
				if(data.result=='1'){
					var roles = data.roles;
					$.each(roles,function(i){
						$('#roleId').append("<option value=\""+roles[i].roleId+"\">"+roles[i].roleName+"</option>");
					});
				}
			},'json');

			// 查找
			$('#searchSysUserForm').submit(
					function() {
						datalist_ctrl.loaddatalist_post(
								'manager/sysUser/getSysUsers', $(this).serialize());
						return false;
					});
		});

function refresh_unpubcount() {
	$("#totalcount").html($("#hid_totalcount").val());
}

var datalist_ctrl = {

	loaddatalist : function(url) {
		var cnt = $('#div_cnt_table');
		$.post(url, function(data, status) {
			cnt.empty();
			cnt.append(data);
			$('.list-table tbody tr td:nth-child(7)').each(function(i){
				var $td = $(this);
				var tdValue = $td.text().replace(/\s/g,"");
				if(tdValue.length>7){
					$td.attr("title",tdValue);
					$td.html(tdValue.substring(0,7)+'...');
				}
			});
		}, 'html');
	},

	loaddatalist_post : function(url, paras) {
		var cnt = $('#div_cnt_table');
		$.post(url, paras, function(data, status) {
			cnt.empty();
			cnt.append(data);
			$('.list-table tbody tr td:nth-child(7)').each(function(i){
				var $td = $(this);
				var tdValue = $td.text().replace(/\s/g,"");
				if(tdValue.length>7){
					$td.attr("title",tdValue);
					$td.html(tdValue.substring(0,7)+'...');
				}
			});
		}, 'html');
	},
	topage : function(url) {
		this.loaddatalist(url);
	},
	// 编辑
	updateSysUser : function(span) {
		$(span).parents().submit();
	},
	// 删除
	deleteSysUser : function(span, userId) {
		if (confirm("您确定要删除该条记录吗")) {
			$.post("manager/sysUser/deleteSysUser", {
				"userId" : userId
			}, function(data) {
				if (data.result == '0') {
					$('#totalcount').text($('#totalcount').text() - 1);
					$(span).parent().parent().remove();
				}
			}, 'json');
		}// end if
	}

};
