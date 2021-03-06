$(document).ready(function() {
	// 加载院区
	loadinfocats();
	// 加载设备类型
	loadFacilityType();
	$.jBox.tip("数据加载中...", 'loading');
	loadPage($("#hid_pageNo").val());
	$("#act_query").click(function(e){
		$.jBox.tip("数据加载中...", 'loading');
		var pageNo=$("#hid_pageNo").val();
		loadPage(pageNo);
		return false;
	});
	
});

/**
 * 加载分页数据
 * @param pageNo 页码
 */
function loadPage(pageNo){
	var paras = $("#form_query").serialize();
	var url = "selfService/selfServiceManageAction_findAllInfo?pageNo="+pageNo+"&" + paras;
	datalist_ctrl.loaddatalist(url);
}

function refresh_totalcount(){
	$("#totalcount").html($("#hid_totalcount").val());
}

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

/**
 * 跳转到添加页面
 */
function toAdd() {
	document.location.href="selfService/selfServiceManageAction_toSave";
}

/**
 * 跳转到更新页面
 * @param id
 */
function toUpdate(id){
	document.location.href="selfService/selfServiceManageAction_toUpdate?id="+id;
}

/**
 * 设备导入
 */
function toImport(){
	var html = "<form id='uploadForm' action='selfService/selfServiceManageAction_importExcel' method='post' enctype='multipart/form-data'>"
	html += "<div style='padding:10px;'>导入模板：<a href='selfService/selfServiceManageAction_downloadTemplate'>下载模板</a></div>";
	html += "<div style='padding:10px;padding-top:0;'>导入文件：<input name='upload' id='upload' type='file' accept='.xls' style='height: 28px; line-height: 28px; width:180px;' /></div>";
	html += "<div style='padding:10px;padding-top:0;'>导入说明：<span style='color:red;'>带*为必填项</span></div>";
	html += "</form>";
    var submit = function (v, h, f) {
    	var filePath = h.find("#upload").val();
    	if(filePath == ''){
    		jBox.tip("请选择上传的文件", 'error');
    		return false;
    	}
    	var pos = filePath.lastIndexOf(".");
 	    var fileType = filePath.substring(pos + 1);
 	    if(fileType != 'xls'){
 	    	jBox.tip("请选择Excel文件！", 'error');
 			return false;
 	    }
    	$.jBox.tip("正在处理，请稍等...", 'loading');
    	$("#uploadForm").form("submit",{
 			success:function(msg){
 				$.jBox.closeTip();
 				var result=eval('('+msg+')');
 				if(result.end == "ok"){
 					$.jBox.success(result.data, '导入成功', { closed: function () {
 						$("#act_query").click();
 					} });
 				}else{
 					$.jBox.error(result.data, '导入失败');
 				}
 			}
 		});

        return true;
    };

    jBox(html, { title: "导入", submit: submit, loaded: function (h) {} });
}

/**
 * 删除终端
 * @param id
 */
function deleteTerminal(id){
	var submit = function (v, h, f) {
	    if (v == 'ok') {
	    	$.post("selfService/selfServiceManageAction_delete",
				{id:id},
				function success(data,status){
					if (status = 'success'){
						var cnt = $('#div_cnt_table');
						 cnt.empty();
						 cnt.html(data);
					}else{
						window.setTimeout(function () { 
							$.jBox.tip('删除成功。', 'error'); 
						}, 500);
					}
				});
	    }else if (v == 'cancel') {
	    	jBox.tip("取消", 'info');
	    }

	    return true; //close
	};
	$.jBox.confirm("确定要删除数据吗？", "提示", submit)
};

/**
 * 加载院区选项
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
 * 加载设备类型
 */
function loadFacilityType(){
	var url = 'unpaid/payDictionaryAction_findAll?dictionaryCode='+"facility_type";
	var body = $('#facilityType');
	body.empty();
	body.append("<option value=''>全部</option>");
	$.get(url , function(data, status){
		var arr1 = eval(data);
        var arr=eval("("+arr1.data+")");
		for (var i in arr){
			var o = $('<option></option>').val(arr[i].codeNo).html(arr[i].codeName);
			body.append(o.get());
		}
	});
};