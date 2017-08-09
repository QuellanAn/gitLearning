$(document).ready(function() {
	
	datalist_ctrl.loaddatalist("unpaid/paySceneAction_findAllInfo?pageNo="+$("#hid_pageNo").val());
	
});

function refresh_totalcount(){
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

function modify(sCode){
	document.location.href="unpaid/paySceneAction_toUpdate?code="+sCode;
};
