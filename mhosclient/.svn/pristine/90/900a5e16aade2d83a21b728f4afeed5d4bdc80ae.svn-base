$(document).ready(function() {

	$("#btn_submit").click(function(e){
		var type=$("#type").val();
		var title=$("#title").val();
		var content=$("#content").val();
		if($.trim(type).length==0
		||$.trim(title).length==0
		||$.trim(content).length==0
		){
		alert("请输入完整的信息");
		return false;
		}
	});

	$("#file").change(function(){
		var objUrl = getObjectURL(this.files[0]) ;
		console.log("objUrl = "+objUrl) ;
		if (objUrl) {
			$("#img_prev").attr("src", objUrl) ;
		}
	}) ;

	function getObjectURL(file) {
		var url = null ; 
		if (window.createObjectURL!=undefined) {
			url = window.createObjectURL(file) ;
		} else if (window.URL!=undefined) {
			url = window.URL.createObjectURL(file) ;
		} else if (window.webkitURL!=undefined) {
			url = window.webkitURL.createObjectURL(file) ;
		}
		return url ;
	};
});
