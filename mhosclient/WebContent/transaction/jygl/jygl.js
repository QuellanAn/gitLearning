$(document).ready(function() {
	var myChart = echarts.init(document.getElementById('Section1'));
	myChart.showLoading({text : '正在努力请求数据,请稍等!' }); 
	var myChartBi = echarts.init(document.getElementById('Section2'));
	var myChartJun = echarts.init(document.getElementById('Section3'));
	
	$.ajax({
		type : "post",
		url : "unpaid/selectStatistics_selectBytime",
		dataType : "json",
		data:{"ajaxType":"json"},
		async : false,
		success : function(data) {
			if(eval(data).json!=null){
				return false;
			}
			var po = eval(data.po);
			var rows = eval(data.all);
			var arr = [];
			var arrY = [];
			//调用函数获取值，转换成数组模式  
			for ( var i = 0; i < rows.length; i++) {
				arr.push(rows[i].name);
				arrY.push(rows[i].val);
			}
			option = {
				title : {
					text : '订单总金额',
					left : 'center'
				},
				tooltip : {
					trigger : 'item',
				},
				toolbox : {
					show : true,
					feature : {
						magicType : {show: true, type: ['line', 'bar']},
						restore : {}
					}
				},
				xAxis : {
					type : 'category',
					boundaryGap: false,
					splitLine : {
						show : false
					},
					axisLabel : {
                        textStyle: {
                            color: '#44B549'
                        }
                    },
					axisLine:{  
                        lineStyle:{  
                            color:'#CCCCCC',  
                            width:1,
                        }  
                    },
					data : arr
				},
				grid : {
					left : '3%',
					right : '4%',
					bottom : '3%',
					containLabel : true
				},
				yAxis : {
					axisLabel : {
                        textStyle: {
                            color: '#000000'
                        }
                    },
					axisLine:{  
                        lineStyle:{  
                            color:'#ffffff',  
                            width:1,
                        }  
                    }
				},
				series : [ {
					name : '订单总金额',
					type : 'line',
					itemStyle : {  
                        normal : {  
                        	color:'#44B549',  
                            lineStyle:{  
                                color:'#44B549'  
                            }  
                        }  
                    },  
					data : arrY
				} ]
			};
			myChart.setOption(option);
			var totalMoney=$("#totalMoney");
//			if(po.upDown==" 上升"){
//				$("#zongeBefore").val(po.totalMoney+"<br/>"+"对比前7天"+'<span style="color:green;"> ↑ '+po.contrast+'</span>');
//			}else if(po.upDown=="下降"){
//				$("#zongeBefore").val(po.totalMoney+"<br/>"+"对比前7天"+'<span style="color:red;"> ↓ '+po.contrast+'</span>');
//			}
			totalMoney.append(po.totalMoney+"<br/>"+"对比前7天"+'<span> ↑ '+po.contrast+'</span>');
			myChart.hideLoading();
		},error : function(errorMsg) {
	         alert("图表请求数据失败!");
	         myChart.hideLoading();
		}
	});
	
	$.ajax({
		type : "post",
		url : "unpaid/selectStatistics_selectTotalCount",
		dataType : "json",
		data:{"ajaxType":"json"},
		async : false,
		success : function(data) {
			if(eval(data).json!=null){
				return false;
			}
			var po = eval(data.po);
			var rows = eval(data.all);
			var arr = [];
			var arrY = [];
			//调用函数获取值，转换成数组模式  
			for ( var i = 0; i < rows.length; i++) {
				arr.push(rows[i].name);
				arrY.push(rows[i].val);
			}
			option = {
				title : {
					text : '订单总笔数',
					left : 'center'
				},
				tooltip : {
					trigger : 'item',
				},
				toolbox : {
					show : true,
					feature : {
						magicType : {show: true, type: ['line', 'bar']},
						restore : {}
					}
				},
				xAxis : {
					type : 'category',
					boundaryGap: false,
					splitLine : {
						show : false
					},
					axisLabel : {
                        textStyle: {
                            color: '#44B549'
                        }
                    },
					axisLine:{  
                        lineStyle:{  
                            color:'#CCCCCC',  
                            width:1,
                        }  
                    },
					data : arr
				},
				grid : {
					left : '3%',
					right : '4%',
					bottom : '3%',
					containLabel : true
				},
				yAxis : {
					axisLabel : {
                        textStyle: {
                            color: '#000000'
                        }
                    },
					axisLine:{  
                        lineStyle:{  
                            color:'#ffffff',  
                            width:1,
                        }  
                    }
				},
				series : [ {
					name : '订单总笔数',
					type : 'line',
					itemStyle : {  
                        normal : {  
                        	color:'#44B549',  
                            lineStyle:{  
                                color:'#44B549'  
                            }  
                        }  
                    },  
					data : arrY
				} ]
			};
			myChartBi.setOption(option);
			var totalCount=$("#totalCount");
//			if(po.upDown==" 上升"){
				$("#zongbiBefore").val(po.totalCount+"<br/>"+"对比前7天"+'<span> ↑ '+po.contrast+'</span>');
//			}else if(po.upDown=="下降"){
//				$("#zongbiBefore").val(po.totalMoney+"<br/>"+"对比前7天"+'<span style="color:red;"> ↓ '+po.contrast+'</span>');
//			}
			totalCount.append($("#zongbiBefore").val());
		},error : function(errorMsg) {
	         alert("图表请求数据失败!");
	         myChart.hideLoading();
		}
	});
	
	$.ajax({
		type : "post",
		url : "unpaid/selectStatistics_selectAvgMoney",
		dataType : "json",
		data:{"ajaxType":"json"},
		async : false,
		success : function(data) {
			if(eval(data).json!=null){
				window.setTimeout(function () { window.top.location.href = "session.jsp"; }, 100);
				return false;
			}
			var po = eval(data.po);
			var rows = eval(data.all);
			var arr = [];
			var arrY = [];
			//调用函数获取值，转换成数组模式  
			for ( var i = 0; i < rows.length; i++) {
				arr.push(rows[i].name);
				arrY.push(rows[i].val);
			}
			option = {
				title : {
					text : '人均金额',
					left : 'center'
				},
				tooltip : {
					trigger : 'item',
				},
				toolbox : {
					show : true,
					feature : {
						magicType : {show: true, type: ['line', 'bar']},
						restore : {}
					}
				},
				xAxis : {
					type : 'category',
					boundaryGap: false,
					splitLine : {
						show : false
					},
					axisLabel : {
                        textStyle: {
                            color: '#44B549'
                        }
                    },
					axisLine:{  
                        lineStyle:{  
                            color:'#CCCCCC',  
                            width:1,
                        }  
                    },
					data : arr
				},
				grid : {
					left : '3%',
					right : '4%',
					bottom : '3%',
					containLabel : true
				},
				yAxis : {
					axisLabel : {
                        textStyle: {
                            color: '#000000'
                        }
                    },
					axisLine:{  
                        lineStyle:{  
                            color:'#ffffff',  
                            width:1,
                        }  
                    }
				},
				series : [ {
					name : '人均金额',
					type : 'line',
					itemStyle : {  
                        normal : {  
                        	color:'#44B549',  
                            lineStyle:{  
                                color:'#44B549'  
                            }  
                        }  
                    },  
					data : arrY
				} ]
			};
			myChartJun.setOption(option);
			var avgMoney=$("#avgMoney");
//			if(po.upDown==" 上升"){
				$("#juneBefore").val(po.avgMoney+"<br/>"+"对比前7天"+'<span> ↑ '+po.contrast+'</span>');
//			}else if(po.upDown=="下降"){
//				$("#juneBefore").val(po.totalMoney+"<br/>"+"对比前7天"+'<span style="color:red;"> ↓ '+po.contrast+'</span>');
//			}
			avgMoney.append($("#juneBefore").val());
		},error : function(errorMsg) {
	         alert("图表请求数据失败!");
	         myChart.hideLoading();
		}
	});
	
});


//function totalMoney(){
//	$("#totalCount").html($("#zongbiBefore").val());
//	$("#totalMoney").html($("#zongeAfter").val());
//	$("#avgMoney").html($("#juneBefore").val());
//};
//function totalCount(){
//	$("#totalCount").html($("#zongbiAfter").val());
//	$("#totalMoney").html($("#zongeBefore").val());
//	$("#avgMoney").html($("#juneBefore").val());
//};
//function avgMoney(){
//	$("#totalCount").html($("#zongbiBefore").val());
//	$("#totalMoney").html($("#zongeBefore").val());
//	$("#avgMoney").html($("#juneAfter").val());
//};
