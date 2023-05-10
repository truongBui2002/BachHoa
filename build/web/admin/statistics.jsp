<%-- 
    Document   : statistics
    Created on : Mar 10, 2023, 7:11:19 PM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/stylestt.css"/>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <title>Statistics</title>
        <script>
            //Xóa đi canvas cũ
            function deleteChart() {
                var oldcanv = document.getElementById('myChart');
                var index = document.getElementById("indexChart");
                index.removeChild(oldcanv);
                var canv = document.createElement('canvas');
                canv.id = 'myChart';
                index.appendChild(canv);
            }
            function createChart() {
                deleteChart();
                //Kiểm tra trạng thái xem đã bấm chưa
                var index;
                var cln = document.getElementsByName("stc");
                for (var i = 0; i < cln.length; i++) {
                    if (cln[i].checked) {
                        index = cln[i].value;
                    }
                }
                //ban đầu khi chạy web thì user chưa ấn nên index trả về null
                //Thiết lập giá trị mặc định khi chạy web lần đầu là 1
                var cIndex;
                if (index === null) {
                    cIndex = 1;
                } else {
                    cIndex = index;
                }

                //Mở kết nối tới servlet và dùng hàm send() để gửi request
                var xhr = new XMLHttpRequest();
                xhr.open('GET', 'chart?index=' + cIndex);
                xhr.onload = function () {
                    if (xhr.status === 200) {
                        //chuỗi nhận được từ servlet
                        var resText = xhr.responseText;
                        var twoArr = resText.split("/");

                        //dùng JSON để chuyển đổi sang mảng
                        var labels = JSON.parse(twoArr[0]);
                        var values = JSON.parse(twoArr[1]);
                        var type = twoArr[2];
                        var ctx = document.getElementById('myChart').getContext('2d');
                        //Vẽ biểu đồ cột(bar), miền(line)
                        var myChart = new Chart(ctx, {
                            type: 'line',
                            data: {
                                labels: labels,
                                datasets: [{
                                        label: type,
                                        data: values,
                                        backgroundColor: 'rgba(54, 162, 235, 0.2)',
                                        borderColor: 'rgba(54, 162, 235, 1)',
                                        borderWidth: 1,
                                        fill: true
                                    }]
                            },
                            options: {
                                scales: {
                                    yAxes: [{
                                            ticks: {
                                                beginAtZero: true
                                            }
                                        }]
                                },
                                plugins: {
                                    filler: {
                                        propagate: true
                                    }
                                }
                            }
                        });
                    } else {
                        console.log('Lỗi khi lấy dữ liệu từ Servlet');
                    }
                };
                xhr.send();
            }
        </script>
        <!-- style css -->
        <style>
            body {
                font-weight: bold;
                line-height: 20px;
            }
            .form-control-lg {
                font-size: 23px !important;
                text-align: center;
                padding: 12px;
                margin: 10px;
                color: rgba(0,0,0, 0.6);

            }
            .containert {
                font-family: 'Roboto', sans-serif;
            }
            .st1 {
                padding: 14px 24px;
                text-align: center;
                color: rgba(0,0,0,0.7);
            }
            .p2 label {
                border: 2px solid rgba(0,0,0,0.6);
            }
            .widget .p2:hover {
                box-shadow: 0 0 0px;
                border: 0px #ffffff;
                background-color: transparent;

            }
            input[type="radio"]{
                visibility: hidden;
                content: '';
                height: 0;
                width: 0;
            }

        </style>
    </head>
    <body onload="createChart()">
        <jsp:include page="headerd.jsp"/>

        <div class="d-flex justify-content-center">
            <div class="containert">
                <div class="row-left">
                    <div class="sidebar">
                        <div class="widget">
                            <div class="widget p1">
                                <!--                                <label style="font-size: 1.5rem">Filter</label>-->
                                <label class="st h2 st1" style="font-weight: 700">Classification</label>
                            </div>
                            <div class="widget p2">
                                <label class="st form-control-lg block-chart">
                                    <input onclick="createChart()" name="stc" value="1" checked type="radio"/> Top potential customers
                                </label>
                                <label class="st form-control-lg block-chart" >
                                    <input onclick="createChart()" name="stc" value="2" type="radio"/> Top selling production
                                </label>
                                <label class="st form-control-lg block-chart" >
                                    <input onclick="createChart()" name="stc" value="3" type="radio"/> Top customers who rarely shop
                                </label>
                                <label class="st form-control-lg block-chart" >
                                    <input onclick="createChart()" name="stc" value="4" type="radio"/> Top products with few purchases
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row-right" id="indexChart">
                    <canvas id="myChart"></canvas>
                </div>
            </div>
        </div>
    </body>
</html>
