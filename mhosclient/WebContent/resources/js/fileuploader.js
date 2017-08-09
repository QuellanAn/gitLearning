/**
 * 模拟ajax无刷新文件上传
 */
var fileUploader = function(config) {

    var ifr = null,
        defConfig = {
    		action:null,
            submit_button:null, //提交按钮
            file:null,
            complete: function(response) {}, //上传成功后回调
            beforeUpLoad: function() {}, //点击提交未上传时回调
            afterUpLoad: function() {} //点击提交上传后回调
        };

    //静态变量
    var IFRAME_NAME = 'file_upload_iframe';

    //配置
    config = $.extend(defConfig, config);
    
    var upload_fun = function(e){
        e.preventDefault();

        //点击提交前触发事件, 函数返回false可阻止提交表单，用于file为空判断
        if (config.beforeUpLoad.call(this) === false) {
            return;
        }

        //生成一个隐藏iframe，并设置form的target为该iframe，模拟ajax效果
        ifr = $('<iframe name="'+ IFRAME_NAME +'" id="'+ IFRAME_NAME +'" style="display:none;"></iframe>');
        
        ifr.appendTo($('body'));
        this.form.action = config.action;
        this.form.target = IFRAME_NAME; //target目标设为ifr

        //上传完毕iframe onload事件
        ifr.load(function(){
            var response = this.contentWindow.document.body.innerHTML;

            config.complete.call(this, response);
            ifr.remove();
            ifr = null; //清除引用
        });
        this.form.submit(); //提交表单

        //点击提交后触发事件
        config.afterUpLoad.call(this);
    };
    

    //绑定submit事件
    if (config.submit_button){
    	config.submit_button.bind('click', upload_fun);
    }else if (config.file){
    	config.file.bind('change', upload_fun);
    }
    

};