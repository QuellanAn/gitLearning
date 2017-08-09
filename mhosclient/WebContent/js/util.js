function dosubmit(name,id){
	var names=document.getElementById(id);
  	if(names.checked==true){
  		checkedAll(name);
  	}else{
		checkedNo(name);
	}
}
//全选
function checkedAll(name){
	var names=document.getElementsByName(name);
  	var len=names.length;
  	if(len>0){
   		for(var i=0;i<len;i++){
   			names[i].checked=true;
   		}
	}
}

//全不选
function checkedNo(name){
	var names = document.getElementsByName(name);
	var len=names.length;
	if(len>0){
	    for(var i=0;i<len;i++){
	    	names[i].checked=false;
	    }
	}
}
//获取id,name与循环的那么相同
function getChickIds(name){
	var id = "";
	 var array = document.getElementsByName(name);
	 //alert("kkk"+array.length);
	 if(array.length > 0){
		 for(var i=0;i<array.length;i++){
				if(array[i].checked==true){
					id += array[i].value +",";
				}
		 }
	 }
    if(id!=null&&id!=""){
    	id=id.substring(0,id.length-1);
    }else{
    	alert("请选择一个任务");
    }
   return id;
}