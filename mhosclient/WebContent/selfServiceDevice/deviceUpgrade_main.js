var setting = {
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
			$("#upgradeType").val(treeNode.value);
			$("#act_query").click();
		}
	}
};

$(document).ready(function() {
	$('#beginDate').datepicker({ dateFormat: 'yy-mm-dd' });
	$('#endDate').datepicker({ dateFormat: 'yy-mm-dd' });
	// 加载设备类型
	loadFacilityType();
	loadPage($("#hid_pageNo").val());

	$("#act_query").click(function(e) {
		var pageNo=$("#hid_pageNo").val();
		loadPage(pageNo);
		return false;
	});

	var zNodes = [ { name: "升级类型", open: true, children: [ { name: "主程序升级",value:"0" }, { name: "守护程序升级",value:"1" }] } ];
	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
});

/**
 * 加载分页数据
 * @param pageNo 页码
 */
function loadPage(pageNo){
	var paras = $("#form_query").serialize();
	var url = "selfService/selfServiceUpgradeAction_findAllInfo?pageNo="+pageNo+"&" + paras;
	datalist_ctrl.loaddatalist(url);
}

function refresh_unpubcount() {
	$("#totalcount").html($("#hid_totalcount").val());
	$("#treeDemoDiv").height($("#tb_cnt").height());
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

/**
 * 上传升级包
 */
function uploadPackage(){
	var html = "<form style='padding:25px;font-size:13px;color:#333;' id='uploadForm' action='selfService/selfServiceUpgradeAction_uploadPkg' method='post' enctype='multipart/form-data'>"
	html += "<div style=''><span class='color-red'>*</span><label class='labelClass'>升级类型：</label><select id='upgradeTypeSelect' name='po.upgradeType' class='inputClass'></select><span class='gapSpan'></span><span class='color-red'>*</span><label class='labelClass'>终端类型：</label><select id='facilityTypeSelect' name='po.facilityType' class='inputClass'></select></div>";
	html += "<div style='margin-top:20px;'><span class='color-red'>*</span><label class='labelClass'>版本名称：</label><input id='versionNameInput' name='po.versionName' type='text' class='inputClass'/><span class='gapSpan'></span><span class='color-red'>*</span><label class='labelClass'>版本号：</label><input id='versionCodeInput' name='po.versionCode' type='text' class='inputClass'/></div>";
	html += "<div style='margin-top:20px;'><span class='color-red' style='visibility:hidden;'>*</span><label class='labelClass'>更新内容：</label><textarea id='upgradeContent' name='po.content' style='width:478px;height:70px;'></textarea></div>";
	html += "<div style='margin-top:20px;'><span class='color-red'>*</span><label class='labelClass'>上传升级包：</label><input id='upgradePkg' name='upgradePkg' type='file'/><input id='forceUpgrade' style='margin-left:25px;' type='checkbox'/><label for='forceUpgrade'>是否强制更新</label><input id='activate' style='margin-left:25px;' type='checkbox'/><label for='activate'>是否同时激活</label></div>";
	html += "<input id='fUpgrade' type='hidden' name='po.forceUpgrade' value=''>";
	html += "<input id='aStatus' type='hidden' name='po.activationStatus' value=''>";
	html += "</form>";
    var submit = function (v, h, f) {
    	// 升级类型
    	var upgradeType = h.find("#upgradeTypeSelect").val();
    	// 终端类型
    	var facilityType = h.find("#facilityTypeSelect").val();
    	// 版本名称
    	var versionName = h.find("#versionNameInput").val();
    	// 版本号
    	var versionCode = $.trim(h.find("#versionCodeInput").val());
    	// 升级内容
    	var upgradeContent = h.find("#upgradeContent").val();
    	if(upgradeType == ''){
    		jBox.tip("请选择升级类型", 'error');
    		return false;
    	}
    	if(facilityType == ''){
    		jBox.tip("请选择终端类型", 'error');
    		return false;
    	}
    	if(versionName == ''){
    		jBox.tip("请填写版本名称", 'error');
    		return false;
    	}
    	if(versionCode == ''){
    		jBox.tip("请填写版本号", 'error');
    		return false;
    	}
//    	if(upgradeContent == ''){
//    		jBox.tip("请填写更新内容", 'error');
//    		return false;
//    	}
    	var forceUpgrade = '0';// 默认不是强制更新
    	if ($('#forceUpgrade').attr('checked')) {
    		forceUpgrade = '1';// 需要强制更新
    	}
    	var activate = '0';// 默认未激活
    	if ($('#activate').attr('checked')) {
    		activate = '1';// 需要强制更新
    	}
    	h.find("#fUpgrade").val(forceUpgrade);
    	h.find("#aStatus").val(activate);
    	
    	var reg = /^[0-9]*[1-9][0-9]*$/;     
        var r = versionCode.match(reg);     
        if(r==null){
        	jBox.tip("版本号必须为正整数", 'error');
    		return false;
        }
        h.find("#versionCodeInput").val($.trim(h.find("#versionCodeInput").val()));
    	var filePath = h.find("#upgradePkg").val();
    	if(filePath == ''){
    		jBox.tip("请选择上传的升级包", 'error');
    		return false;
    	}
//    	var pos = filePath.lastIndexOf(".");
// 	    var fileType = filePath.substring(pos + 1);
// 	    if(fileType != 'exe'){
// 	    	jBox.tip("请选择exe文件！", 'error');
// 			return false;
// 	    }
    	$.jBox.tip("正在上传，请稍等...", 'loading');
    	$("#uploadForm").form("submit",{
 			success:function(msg){
 				$.jBox.closeTip();
 				var result=eval('('+msg+')');
 				if(result.end == "ok"){
 					$.jBox.success(result.data, '上传成功', { closed: function () {
 						$("#act_query").click();
 					} });
 				}else{
 					$.jBox.error(result.data, '上传失败');
 				}
 			}
 		});

        return true;
    };

    jBox(html, { title: "上传升级包", width:630,height:330, buttons: { '上传': true }, submit: submit, loaded: function (h) {
    	// 升级类型选项
    	h.find("#upgradeTypeSelect").append("<option value='0'>主程序升级</option>");
    	h.find("#upgradeTypeSelect").append("<option value='1'>守护程序升级</option>");
    	// 终端类型选项
    	var url = 'unpaid/payDictionaryAction_findAll?dictionaryCode='+"facility_type";
    	var body = $('#facilityTypeSelect');
    	body.empty();
    	$.get(url , function(data, status){
    		var arr1 = eval(data);
            var arr=eval("("+arr1.data+")");
    		for (var i in arr){
    			var o = $('<option></option>').val(arr[i].codeNo).html(arr[i].codeName);
    			body.append(o.get());
    		}
    	});
    } });
}

/**
 * 全选
 */
function selectAll() {  
    if ($("#selectAll").attr("checked")) {  
        $("input[name='xzsj']").attr("checked", true);  
    } else {  
        $("input[name='xzsj']").attr("checked", false);  
    }  
} 

/**
 * 激活
 */
function activate(){
	var xzsj=new Array();
	$('input[name="xzsj"]:checked').each(function(){ 
		 xzsj.push($(this).val()); 
	}); 
	if(xzsj.length != 0){
		$.ajax({
			type : "post",
			url : "selfService/selfServiceUpgradeAction_checkActivate",
			data:{"xzsj":xzsj.toString()},
			dataType : "json",
			async : false,
			success : function(data) {
				// 需要激活的个数
				var needActivateCount = data.result;
				if(needActivateCount > 0){
					var submit = function (v, h, f) {
					    if (v == 'ok') {
				    		$.post("selfService/selfServiceUpgradeAction_activateVersions",
							{"xzsj":xzsj.toString()},
							function success(data,status){
								if(data.result){
									jBox.tip("激活成功", 'info');
									window.setTimeout(function() {
										$("#act_query").click();
									}, 500);
								}else{
									jBox.tip("激活失败，请重新尝试", 'info');
								}
							});
					    }else if (v == 'cancel') {
//					    	jBox.tip("取消", 'info');
					    }

					    return true; //close
					};
					$.jBox.confirm("确定激活选择的设备版本("+needActivateCount+"个需要激活)吗？", "提示", submit)
				}else{
					jBox.tip("勾选的设备版本没有需要激活的", 'info');
				}
			},error : function(errorMsg) {
				jBox.tip("请求失败，请重新尝试", 'info');
			}
		});
	}else{
		jBox.tip("请勾选需要激活的设备版本", 'info');
	}
}
