$(document).ready(function() {
	$("#icon").change(function(){
		var objUrl = getObjectURL(this.files[0]) ;
		console.log("objUrl = "+objUrl) ;
		if (objUrl) {
			$("#img").attr("src", objUrl) ;
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

function getIcon(){
	var iconV=$("#iconValue").value;
	alert(icon);
	$("#img").attr("src", icon) ;
}
