$(document).ready(function() {
	$('input[name=sq_startdate]').datepicker({ dateFormat: 'yy-mm-dd' });
	$('input[name=sq_enddate]').datepicker({ dateFormat: 'yy-mm-dd' });
	loadUserSource();
	$("a.selectTime:eq(0)").click();
	
	$("#act_query").click(function(){
		updateChart();
	});
});

/**
 * 更新显示的图表
 */
function updateChart(){
	var beginDate = $("#beginDate").val();
	var endDate = $("#endDate").val();
	var myChart = echarts.init(document.getElementById('Section1'));
	var title = "充值来源占比分析图";
	myChart.showLoading({
        text: '读取数据中...' // loading，是在读取数据的时候显示
    });
	$.ajax({
		type : "post",
		url : "rechargeStatisticsAction_getCountBySource",
		data:{"startTime":beginDate, "endTime":endDate,"ajaxType":"json"},
		dataType : "json",
		async : true,
		success : function(data) {
			if(eval(data).json!=null){
				window.setTimeout(function () { window.top.location.href = "session.jsp"; }, 100);
				return false;
			}
			var num = eval(data.num);
			$("div.divSum span.num").text(num);
			var rows = eval(data.list);
			var arr = [];
			var arrY = [];
			//调用函数获取值，转换成数组模式  
			for ( var i = 0; i < rows.length; i++) {
				arr.push(rows[i].rechargeSource);
				arrY.push(rows[i].count);
			}
			option = {
				title : {
					text : title,
					x:'center',
					y:'bottom'
				},
				tooltip : {
					trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)"
				},
				toolbox : {
					show : true,
					feature : {
						restore : {show: true},
			            saveAsImage : {show: true}
					}
				},
				series : [ {
					name:'充值来源占比',
		            type:'pie',
		            radius : [0, 150],
		            center : ['50%', 200],
		            /*roseType : 'area',*/
		            itemStyle:{ 
                        normal:{
                            label:{ 
                               show: true, 
                               formatter: '{b} : {c} ({d}%)' 
                            }, 
                            labelLine :{show:true}
                        } 
                    },
		            data:(function(){  
                        var res = [];  
                        var len = arr.length;
                        if(len > 0){
                        	while (len--) {
                        		res.push({
                        			name: arr[len],  
                        			value: arrY[len]
                        		});  
                        	}
                        }else{// 时间区间内的统计数据为0
                        	res.push({
                    			name: "全部",  
                    			value: 0
                    		});
                        }
                        return res;  
                    })()
				} ]
			};
			myChart.setOption(option);
			myChart.hideLoading();
		},error : function(errorMsg) {
	         alert("图表请求数据失败!");
	         myChart.hideLoading();
		}
	});
}

/**
 * 加载用户来源列表
 */
function loadUserSource(){
	var url = 'unpaid/payDictionaryAction_findAll?dictionaryCode='+"business_source";
	var body = $('#userSource');
	body.empty();
	body.append("<option value=''>全部</option>");
	$.get(url , function(data, status){
		var code = eval(data.code);
		if(code == 0){
			var arr1 = eval(data);
			var arr=eval("("+arr1.data+")");
			for (var i in arr){
				var o = $('<option></option>').val(arr[i].codeNo).html(arr[i].codeName);
				body.append(o.get());
			}
		}
	});
}

/**
 * 点击“最近7天”等设置时间区间
 */
function setSelectTime(days){
	var beginDate = getDayAdd((-1) * (days - 1));
	var endDate = getTodayDate();
	$("#beginDate").val(beginDate);
	$("#endDate").val(endDate);
	
	updateChart();
}

/**
 * 跳转到科室变化趋势页面
 */
function toDepartmentChange(){
	var beginDate = $("#beginDate").val();
	var endDate = $("#endDate").val();
	window.location.href="appointmentStatisticsAction_toDepartmentChange?beginDate=" + beginDate + "&endDate=" + endDate + "&chartType=1&type=0"; 
}