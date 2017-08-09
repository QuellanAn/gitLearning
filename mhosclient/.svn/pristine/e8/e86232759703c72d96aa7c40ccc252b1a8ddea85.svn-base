var setting = {
	async : {
		contentType : "application/json",
		enable : true,
		dataType : "json",
		type : "post",
		url : "config/department/findAllDeptmentTree",
		dataFilter : null
	},
	data : {
		simpleData : {
			enable : true,
			idKey : "id",
			pIdKey : "pId",
			rootPId : 0
		// 根节点
		}
	},
	view : {
		selectedMulti : false,
		fontCss : {
			'color' : 'black'
		}
	},
	callback : {// 回调函数
		onClick : function(event, treeId, treeNode) {// 点击事件的触发函数
			$.ajax({
				type : 'post',
				url : 'config/doctor/searchDoctors?page=1&departmentId='
						+ treeNode.value,
				dataType : "text",
				success : function(data) {
					var cnt = $('#div_cnt_table');
					cnt.empty();
					cnt.append(data);
				},
				error : function(msg) {
					alert(" 数据加载失败！" + msg);
				}
			});
		}
	}
};

$(document).ready(function() {
	// 初始化科室选择框
	initDepartment();
	datalist_ctrl.loaddatalist("config/doctor/searchDoctors?page=1");

	$("#act_query").click(function(e) {
		var paras = $("#form_query").serialize();
		var url = "config/doctor/searchDoctors?page=1&" + paras;
		datalist_ctrl.loaddatalist(url);
		return false;
	});

	jobInit();
	$.fn.zTree.init($("#treeDemo"), setting);

});

function refresh_unpubcount() {
	$("#totalcount").html($("#hid_totalcount").val());
}

var datalist_ctrl = {

	loaddatalist : function(url) {
		var cnt = $('#div_cnt_table');
		$.post(url, function(data, status) {
			if (data && data.msg) {
				alert(data.msg);
			} else if (data) {
				cnt.empty();
				cnt.append(data);
			}
		});
	},

	loaddatalist_post : function(url, paras) {
		var cnt = $('#div_cnt_table');
		$.post(url, paras, function(data, status) {
			if (data && data.msg) {
				alert(data.msg);
			} else if (data) {
				cnt.empty();
				cnt.append(data);
			}
		});
	},
	topage : function(url) {
		this.loaddatalist(url);
	}
};

function act_edit(doctorId) {

	/*$.post('config/doctor/toSaveOrUpdate', {
		doctorId : doctorId
	}, function(data, status) {

		$("#node").empty();
		$("#node").html(data);
		$("#node").dialog({
			modal : true,
			resizable : false,
			title : "编辑医生",
			width : 600,
			height : 550
		});
	}, "html");*/
	document.location.href="config/doctor/toSaveOrUpdate?doctorId="+doctorId;
};

function act_save() {
	/*$.post('config/doctor/toSaveOrUpdate', {
		doctorId : null
	}, function(data, status) {
		$("#node").empty();
		$("#node").html(data);
		$("#node").dialog({
			modal : true,
			resizable : false,
			title : "添加医生",
			width : 600,
			height : 550
		});
	}, "html");*/
	document.location.href="config/doctor/toSaveOrUpdate";
};

function initDepartment() {
	var sel = $('#departmentId');
	sel.empty();
	sel.append("<option value=''>全部</option>");
	$.post("config/department/findAllDeptment", function(data, status) {
		var obj = eval('(' + data + ')');
		var jarr = obj.depts;
		for ( var i in jarr) {
			var o = $('<option></option>').val(jarr[i].departmentId).html(
					jarr[i].departmentName);
			sel.append(o.get());
		}
	});

};
// 初始化职称下拉框
function jobInit() {

	var jobSel = $('#job');
	jobSel.empty();
	jobSel.append("<option value=''>全部</option>");
	$.post("config/doctor/findJobList", function(data, status) {
		var obj = eval('(' + data + ')');
		var jarr = obj.jobList;
		for ( var i in jarr) {
			var o = $('<option></option>').val(jarr[i].id)
					.html(jarr[i].jobName);
			jobSel.append(o.get());
		}
	});
};
function deleteDoctor(id, name) {

	if (confirm("你确定删除名为 " + name + " 的医生吗？")) {
		$.post("config/doctor/delDoctor", {
			doctorId : id
		}, function(data, status) {
			data = eval('(' + data + ')');
			if (data.result == 0) {
				alert("删除成功");
				$("#act_query").click();
			} else {
				alert("删除失败");
			}
		});

	}
};

function excleDoctor(input){
	var fileName = input.value;
		if(fileName.length > 1 && fileName ) {       
        var ldot = fileName.lastIndexOf(".");
        var type = fileName.substring(ldot + 1);
        if(type=="xls" || type=="xlsx") {
        	$("#type").val(type);
            var form=document.getElementById("form_query");
			form.method = 'post';
				form.action = '';
				form.submit();  
        }else{
        	alert("文件格式不准确！");
        	input.outerHTML=input.outerHTML.replace(/(value=\").+\"/i,"$1\"")
        }       
	}
};

function exportDoctor() {
	var xzsj=new Array();
	$('input[name="xzsj"]:checked').each(function(){ 
		 xzsj.push($(this).val()); 
	}); 
	if(xzsj!=0){
		document.location.href="config/doctor/exportDoctorByExcle?xzsj="+xzsj;
	}else{
		alert("请勾选信息!");
	}
};
