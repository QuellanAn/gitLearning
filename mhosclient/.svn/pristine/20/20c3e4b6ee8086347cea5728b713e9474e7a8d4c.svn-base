$(document).ready(function() {
	var key = $("#key1").val();
	if(key == ""){
		key = $("#key").val();
	}
		
	$('.menu-item-cnt').attr("style", "display:none;");
	$('.menu-item-cnt:eq(' + key + ')').attr("style", "display:block");
	
	saveKey(key);
	
});

function saveKey(key){
	$.post(
		'manager_saveKey',
		{keywords : key},
		function(data, status){
		if (data){
		}
	});
}
