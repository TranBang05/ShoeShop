<!DOCTYPE html>
<html lang="en">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Responsive Admin Dashboard | Korsat X Parmaga</title>
    <!-- ======= Styles ====== -->
    <link rel="stylesheet" href="assets/css/style01.css">
    <title>Chart.js Example - Area Chart</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        /* CSS styling goes here */
        .searchForm {
            margin: 20px;
        }

        .formGroup {
            margin-bottom: 10px;
        }

        .customButton {
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            border: none;
            cursor: pointer;
        }

        .orderList {
            margin: 20px;
        }

        .orderItem {
            margin-bottom: 10px;
        }


        .form1 {
            display: flex;
            justify-content: space-between;
            margin-right: 400px;
        }

        .form2 {
            margin-right: 100px; /* Khoảng cách giữa form2 và form3 */
        }
        #myChart {
            width: 100%;
            height: 100%;
        }

        /* Đảm bảo trang web và thẻ body chiếm toàn bộ màn hình */
        html,
        body {
            width: 100%;
            height: 100%;
            margin: 0;
            padding: 0;
            overflow: hidden;
        }


        .hidden {
            display: none;
        }
    </style>
</head>

<body>
<!-- =============== Navigation ================ -->
<div class="container">
    <div class="navigation">
        <ul>
            <li>
                <a href="#">
                        <span class="icon">
                            <ion-icon name="logo-apple"></ion-icon>
                        </span>
                    <span class="title">Brand Name</span>
                </a>
            </li>

            <li>
                <a href="payment">
                        <span class="icon">
                            <ion-icon name="home-outline"></ion-icon>
                        </span>
                    <span class="title">Dashboard</span>
                </a>
            </li>

            <li>
                <a href="customers">
                        <span class="icon">
                            <ion-icon name="people-outline"></ion-icon>
                        </span>
                    <span class="title">Quản lí khách hàng</span>
                </a>
            </li>

            <li>
                <a href="pages">
        <span class="icon">
            <ion-icon name="basket-outline"></ion-icon>
        </span>
                    <span class="title">Quản lí đơn hàng</span>
                </a>
            </li>


            <li>
                <a href="payment">
                    <span class="icon">
                        <ion-icon name="bar-chart-outline"></ion-icon>
                    </span>
                    <span class="title">Quản lí thống kê</span>
                </a>
            </li>



            <li>
                <a href="#">
                        <span class="icon">
                            <ion-icon name="log-out-outline"></ion-icon>
                        </span>
                    <span class="title">Sign Out</span>
                </a>
            </li>
        </ul>
    </div>


    <!-- ========================= Main ==================== -->
    <div class="main">
        <div class="topbar">
            <div class="toggle">
                <ion-icon name="menu-outline"></ion-icon>
            </div>



            <div class="user">
                <img src="assets/imgs/customer01.jpg" alt="">
            </div>
        </div>

        <!-- ======================= Cards ================== -->
        <div class="cardBox">

        </div>



        <!-- ================ Order Details List ================= -->
        <div class="details">

            <div class="recentOrders">
                <div class="form1">
                    <div class="form2">


                <form id="searchForm" action="payment" method="GET">

                    <input type="text" id="year" name="year" placeholder="Nhập năm..." required>
                    <button type="submit">Search</button>
                </form>
                    </div>
                    <div class="form3">
                        <button type="button" id="dateButton">Theo Ngày</button>
                        <button type="button" id="monthButton" style="display: none;">Theo Tháng</button>
                    </div>

                </div>
                <canvas id="myChart" class="chart"></canvas>








            </div>

            <!-- ================= New Customers ================ -->

        </div>
    </div>
</div>


<script>
    // Lấy đối tượng form và nút submit
    var chartCanvas = document.getElementById('myChart');
    var form = document.getElementById('searchForm');
    var submitButton = form.querySelector('button[type="submit"]');

    // Đăng ký sự kiện khi người dùng ấn nút "Tìm kiếm"
    submitButton.addEventListener('click', function(event) {
        event.preventDefault(); // Ngăn chặn hành vi mặc định của nút submit
        submitForm();
    });

    // Hàm để gửi form
    function submitForm() {
        form.submit(); // Gửi form
    }

    var dateButton = document.getElementById('dateButton');
    var monthButton = document.getElementById('monthButton');

    dateButton.addEventListener("click", function() {
        dateButton.style.display = "none";
        monthButton.style.display = "block";
        chartCanvas.classList.remove("hidden");
        initializeChart();
    });

    monthButton.addEventListener("click", function() {
        monthButton.style.display = "none";
        dateButton.style.display = "block";
        chartCanvas.classList.add("hidden");
        initializeChart1();
    });

    // Hàm để khởi tạo biểu đồ theo ngày
    function initializeChart() {
        runChart();
    }

    // Hàm để khởi tạo biểu đồ theo tháng
    function initializeChart1() {
        runChart1();
    }

    // Hàm để chạy biểu đồ theo ngày
    function runChart() {
        <%-- Thông kê theo ngày --%>
        var ctx = document.getElementById('myChart').getContext('2d');
        var chart = new Chart(ctx, {
            type: 'line',
            data: {
                labels: [
                    <% for (int i = 1; i <= 31; i++) { %>
                    '<%= i %>/01',
                    <% } %>
                ],
                datasets: [{
                    label: 'Orders',
                    data: [
                        <% Map<Integer, Integer> dailyOrders = (Map<Integer, Integer>) request.getAttribute("dailyOrders"); %>

                        <% for (int i = 1; i <= 31; i++) { %>
                        <%= dailyOrders.getOrDefault(i, 0) %>,
                        <% } %>
                    ],
                    backgroundColor: 'rgba(75, 192, 192, 0.2)',
                    borderColor: 'rgba(75, 192, 192, 1)',
                    borderWidth: 1,
                    fill: true
                }]
            },
            options: {
                responsive: true,
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });

    }



    <%--//thông kê theo tháng--%>
    <%--    var ctx = document.getElementById('myChart').getContext('2d');--%>
    <%--    var chart = new Chart(ctx, {--%>
    <%--        type: 'line',--%>
    <%--        data: {--%>
    <%--            labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],--%>
    <%--            datasets: [{--%>
    <%--                label: 'Orders',--%>
    <%--                data: [--%>
    <%--                    <% Map<Integer, Integer> monthlyOrders = (Map<Integer, Integer>) request.getAttribute("monthlyOrders"); %>--%>
    <%--                    <% for (int i = 1; i <= 12; i++) { %>--%>
    <%--                    <%= monthlyOrders.getOrDefault(i, 0) %>,--%>
    <%--                    <% } %>--%>
    <%--                ],--%>
    <%--                backgroundColor: 'rgba(75, 192, 192, 0.2)',--%>
    <%--                borderColor: 'rgba(75, 192, 192, 1)',--%>
    <%--                borderWidth: 1,--%>
    <%--                fill: true // Thêm thuộc tính fill: true để hiển thị miền dưới đường cong--%>
    <%--            }]--%>
    <%--        },--%>
    <%--        options: {--%>
    <%--            responsive: true,--%>
    <%--            scales: {--%>
    <%--                y: {--%>
    <%--                    beginAtZero: true--%>
    <%--                }--%>
    <%--            }--%>
    <%--        }--%>
    <%--    });--%>





    var ctx = document.getElementById('myChart').getContext('2d');
    var labels = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
    <% Map<Integer, Integer> monthlyOrders = (Map<Integer, Integer>) request.getAttribute("monthlyOrders"); %>
    var monthlyOrdersData = [
        <%= monthlyOrders.getOrDefault(1, 0) %>,
        <%= monthlyOrders.getOrDefault(2, 0) %>,
        <%= monthlyOrders.getOrDefault(3, 0) %>,
        <%= monthlyOrders.getOrDefault(4, 0) %>,
        <%= monthlyOrders.getOrDefault(5, 0) %>,
        <%= monthlyOrders.getOrDefault(6, 0) %>,
        <%= monthlyOrders.getOrDefault(7, 0) %>,
        <%= monthlyOrders.getOrDefault(8, 0) %>,
        <%= monthlyOrders.getOrDefault(9, 0) %>,
        <%= monthlyOrders.getOrDefault(10, 0) %>,
        <%= monthlyOrders.getOrDefault(11, 0) %>,
        <%= monthlyOrders.getOrDefault(12, 0) %>
    ];

    var chart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: labels,
            datasets: [{
                label: '$doller',
                data: monthlyOrdersData,
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderColor: 'rgba(75, 192, 192, 1)',
                borderWidth: 1
            }]
        },
        options: {
            responsive: true,
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });




</script>

<!-- =========== Scripts =========  -->
<script src="assets/js/main.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<!-- ====== ionicons ======= -->
<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
<script src="assets/js/main.js"></script>

<!-- ====== ionicons ======= -->
<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>

</html>
