$(document).ready(function() {
	$('input[name=sq_startdate]').datepicker({ dateFormat: 'yy-mm-dd' });
	$('input[name=sq_enddate]').datepicker({ dateFormat: 'yy-mm-dd' });
	$("a.selectTime:eq(0)").click();
	
	$("#act_query").click(function(){
		updateChart();
	});
	
	/*$('input[name=countType]').change(function(){
		var countType = $(this).val();
		if(countType == "department"){// 按科室
			$("#userSource").removeAttr("disabled");
		}else{// 按来源
			$("#userSource").val('');
			$("#userSource").attr("disabled","disabled"); 
		}
	});*/
});

/**
 * 更新显示的图表
 */
function updateChart(){
	var beginDate = $("#beginDate").val();
	var endDate = $("#endDate").val();
	var myChart = echarts.init(document.getElementById('Section1'));
	var title = "各科室综合好评占比分析图";
	var tipTitle = "科室综合好评占比";
	var numText = "综合好评总数";
	myChart.showLoading({
        text: '读取数据中...' // loading，是在读取数据的时候显示
    });
	$.ajax({
		type : "post",
		url : "serviceEvaluationStatisticsAction_getCommentCountByDepartMent",
		data:{"beginDate":beginDate, "endDate":endDate,"ajaxType":"json"},
		dataType : "json",
		async : true,
		success : function(data) {
			if(eval(data).json!=null){
				window.setTimeout(function () { window.top.location.href = "session.jsp"; }, 100);
				return false;
			}
			var num = eval(data.sum);
			$("div.divSum span.num").text(num);
			$("div.divSum span.text").text(numText);
			var rows = eval(data.list);
			var arr = [];
			var arrY = [];
			//调用函数获取值，转换成数组模式  
			for ( var i = 0; i < rows.length; i++) {
				arr.push(rows[i].departmentName);
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
					name:tipTitle,
		            type:'pie',
		            radius : [0, 140],
		            center : ['50%', 220],
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