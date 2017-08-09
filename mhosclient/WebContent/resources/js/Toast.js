//自定义弹框  

function TextToast(msg,duration){ 
	var check = document.getElementById("mDiv");
	if (check) {
		document.body.removeChild(check); 
	}
	var mDiv = document.createElement('div'); 
    duration=isNaN(duration)?3000:duration;  
    mDiv.innerHTML = msg;
    mDiv.id = "mDiv";
    mDiv.style.cssText="width:60%; min-width:150px;background:#000; opacity:0.5; height:60px; color:#fff; line-height:60px; text-align:center; border-radius:5px; position:fixed; top:40%; left:20%; z-index:999999; font-size: 17px;";  
    document.body.appendChild(mDiv);  
    setTimeout(function() {  
        var d = 0.5;  
        mDiv.style.webkitTransition = '-webkit-transform ' + d + 's ease-in, opacity ' + d + 's ease-in';  
        mDiv.style.opacity = '0';  
        setTimeout(function() { document.body.removeChild(document.getElementById("mDiv")); }, d * 1000);  
    }, duration);  
} 

function LoadingToast(msg){
	var check = document.getElementById("mDiv");
	if (check) {
		document.body.removeChild(check); 
	}
	var mDiv = document.createElement('div'); 
    var loaderImg = document.createElement('img');
    mDiv.style.cssText="width:20%; min-width:30px;background:#000; opacity:0.5; height:60px; color:#fff; line-height:59px; text-align:center; border-radius:5px; position:fixed; top:40%; left:50%; z-index:999999;";  
    document.body.appendChild(mDiv);  
    mDiv.id = "mDiv";
    var mydiv = document.getElementById('mDiv'); //获得dom对象
    loaderImg.style.cssText = "width: 20px;height: 20px;display: inline-block;vertical-align:middle;";
    loaderImg.src = "resources/images/wait.gif";
    mydiv.appendChild(loaderImg);
    var title = document.createElement('p');
    title.style.cssText = "background-color:rgba(0,0,0,0);margin: 0;padding: 0;font-size: 17px;display: inline-block;padding-left: 6px;vertical-align:middle;";
    title.innerText = msg;
    mydiv.appendChild(title);
} 
function LoadingToast2(msg){
	var check = document.getElementById("mDiv");
	if (check) {
		document.body.removeChild(check); 
	}
	var mDiv = document.createElement('div'); 
    var loaderImg = document.createElement('img');
    mDiv.style.cssText="width:60%; min-width:150px;background:#000; opacity:0.5; height:60px; color:#fff; line-height:59px; text-align:center; border-radius:5px; position:fixed; top:40%; left:20%; z-index:999999;";  
    document.body.appendChild(mDiv);  
    mDiv.id = "mDiv";
    var mydiv = document.getElementById('mDiv'); //获得dom对象
    loaderImg.style.cssText = "width: 20px;height: 20px;display: inline-block;vertical-align:middle;";
    loaderImg.src = "images/wait.gif";
    mydiv.appendChild(loaderImg);
    var title = document.createElement('p');
    title.style.cssText = "background-color:rgba(0,0,0,0);margin: 0;padding: 0;font-size: 17px;display: inline-block;padding-left: 6px;vertical-align:middle;";
    title.innerText = msg;
    mydiv.appendChild(title);
} 
function HiddenToast() {
	var d = 0.1;  
    mDiv.style.webkitTransition = '-webkit-transform ' + d + 's ease-in, opacity ' + d + 's ease-in';  
    mDiv.style.opacity = '0';  
    setTimeout(function() { document.body.removeChild(document.getElementById("mDiv"));  }, d * 1000);
}

