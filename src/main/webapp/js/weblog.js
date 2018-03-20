/**
 * Created by LYL on 2018/03/17.
 */



angular.module('App', ['ui.bootstrap']).controller('controller_index', [ '$scope', '$http', '$uibModal',
    function ($scope, $http, $uibModal) {

        $scope.start = function () {
            webSocket.send('hello');
            return false;
        }

        var webSocket = new WebSocket('ws://localhost:8080/websocket');
        var countchart = echarts.init(document.getElementById('count'));
        var sumchart = echarts.init(document.getElementById('sum'));
        //产生异常
        webSocket.onerror = function (event) {
            alert(event.data);
        };
        //已经建立连接
        webSocket.onopen = function (event) {
        };
        //收到服务器消息，使用event.data提取
        webSocket.onmessage = function (event) {
            var sd = JSON.parse(event.data);
            webcount(sd);
            webSum(sd.titleSum);
        };

        function webcount(json) {
            var option = {
                title: {
                    text: '搜狗新闻热点实时统计',
                    subtext: '作者：刘彦伶'
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    }
                },
                legend: {
                    data: ['浏览量']
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: {
                    type: 'value',
                    boundaryGap: [0, 0.01]
                },
                yAxis: {
                    type: 'category',
                    data: json.titleName
                },
                series: [
                    {
                        name: '浏览量',
                        type: 'bar',
                        data: json.titleCount
                    },

                ]
            };
            countchart.setOption(option);
        }


        function webSum(data) {

            var option = {
                backgroundColor: '#fbfbfb',//背景色
                title: {
                    text: '搜狗新闻话题总量统计',
                    subtext: '作者：刘彦伶'
                },


                tooltip: {
                    formatter: "{a} <br/>{b} : {c}%"
                },
                toolbox: {
                    feature: {
                        restore: {},
                        saveAsImage: {}
                    }
                },
                series: [
                    {
                        name: '业务指标',
                        type: 'gauge',
                        max: 50000,
                        detail: {formatter: '{value}个话题'},
                        data: [{value: 50, name: '话题曝光量'}]
                    }
                ]
            };

            option.series[0].data[0].value = data;
            sumchart.setOption(option, true);
        }

        $scope.select = function () {
            $http({
                method: 'POST',
                url: '/weblogController/webcount',
            }).success(function (response) {
                console.log(response);
                console.log("asa")

                $uibModal.open({
                            templateUrl: 'total.html',
                            controller: function($scope, $http, $uibModalInstance){
                                $scope.totalpeople = function () {
                                    $scope.people = response.result.data.peoplesum;
                                }
                                $scope.totalname = function () {
                                    $scope.name = response.result.data.namesum;
                                }
                                $scope.cancel= function() {
                                    $uibModalInstance.dismiss();
                                }
                            },
                            resolve: {
                                sum: function () {
                                    return response.result.data;
                                }
                            }

                        }
                    );

                }
            ).error(
                function () {
                    alert("出错了");
                })
        }

    }]);


