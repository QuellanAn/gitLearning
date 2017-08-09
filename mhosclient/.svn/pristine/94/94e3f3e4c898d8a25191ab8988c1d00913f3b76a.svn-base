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
				url : 'config/department/getDepartments?page=1&department_id='
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
	datalist_ctrl.loaddatalist("config/department/getDepartments?page=1");
	
	$("#act_query").click(function(e){
		var paras = $("#form_query").serialize();
		var url = "config/department/getDepartments?page=1&" + paras;
		datalist_ctrl.loaddatalist(url);
		return false;
	});
	
});

function refresh_unpubcount(){
	$("#totalcount").html($("#hid_totalcount").val());
}


var datalist_ctrl = {
		
	loaddatalist:function(url){
		var cnt = $('#div_cnt_table');
		$.post(url , function(data, status){
			if (data && data.msg){
				alert(data.msg);
			}else if (data){
				cnt.empty();
				cnt.append(data);
				$.fn.zTree.init($("#treeDemo"), setting);
			}
		});
	},

		
	loaddatalist_post:function(url, paras){
			var cnt = $('#div_cnt_table');
			$.post(url , paras, function(data, status){
				if (data && data.msg){
					alert(data.msg);
				}else if (data){
					cnt.empty();
					cnt.append(data);
					$.fn.zTree.init($("#treeDemo"), setting);
				}
			});
		},
	topage: function(url){
		this.loaddatalist(url);
	}
};

function act_edit(id){
/*	var a_edit = $("#act_edit_"+id)
	var a_save = $("#act_save_"+id);
	var txt = $("#txt_addr_"+id);
	var inp = $("#inp_addr_"+id);
	$(".act_save").hide();
	$(".act_edit").show();
	$(".txt_addr").show();
	a_edit.hide();
	a_save.show();
	$("[name=addr]").hide();
	txt.hide();
	inp.show();
	return false;*/
	
	/*	$.post('config/department/toUpdate',
         {id:id},
		function(data, status) { 
        
        	 $("#node").empty();
			$("#node").html(data);
			$("#node").dialog({
				modal : true,
				resizable : false,
				title : "编辑科室",
				width : 600,
				height : 600
			});
		}, "html");*/
	
	document.location.href="config/department/toUpdate?id="+id;

	
};

function act_save(){
	/*$.post('config/department/toUpdate',
			 {id:null},
			function(data, status) {  
	        	 $("#node").empty();
				$("#node").html(data);
				$("#node").dialog({
					modal : true,
					resizable : false,
					title : "添加科室",
					width : 600,
					height : 600
				});
			}, "html");*/
	document.location.href="config/department/toUpdate";
};

function submit_save(did, address, txt){
	var paras = {id:did,location:address};
	$.post("config/department/saveDepartment",
			paras,
			function(data, status){
				if (data && data.msg){
					alert(data.msg);
				}else{
					txt.html(address);
				}
			});
};


function deleteDepartment(id,name){
	
	if (confirm("你确定删除名为 "+name+" 的科室吗？")){
		$.post("config/department/delDepartment",
				{id:id},
				function(data, status){
					data=eval('('+data+')');
					if(data.result==0){
						alert("删除成功");
						$("#act_query").click();
					}else{
						alert("删除失败");
					}
		      });
		
	}
};

function exportDepart() {
	document.location.href="config/department/exportExcle";
};

function fileChange(input){
	var fileName = input.value;
		if(fileName.length > 1 && fileName ) {       
        var ldot = fileName.lastIndexOf(".");
        var type = fileName.substring(ldot + 1);
        if(type=="xls" || type=="xlsx") {
        	$("#type").val(type);
            var form=document.getElementById("form_query");
			form.method = 'post';
			form.action = 'config/department/importExcle';
			form.submit();  
        }else{
        	alert("文件格式不准确！");
        	input.outerHTML=input.outerHTML.replace(/(value=\").+\"/i,"$1\"")
        }       
	}
	
};


