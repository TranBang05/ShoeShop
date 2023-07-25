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
            <ion-icon name="person-circle-outline"></ion-icon>
        </span>
                    <span class="title">Admin</span>
                </a>
            </li>

            <li>
                <a href="statics">
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
                <a href="staff">
                    <span class="icon">
                        <ion-icon name="people-outline"></ion-icon>
                    </span>
                    <span class="title">Quản lí Nhân Viên</span>
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
                <a href="login.jsp">
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
        <!-- ================ Order Details List ================= -->
        <div class="details">

            <div class="recentOrders">
                <h3>Theo tháng</h3>
                <canvas id="myChart" class="chart"></canvas>

            </div>


            <!-- ================= New Customers ================ -->
            <div>
                <h3>Total Amount in Year:</h3>
                <br>
                <h4><%= request.getAttribute("total_year") %></h4>

            </div>

        </div>


        <div class="details">


        </div>
    </div>
</div>


<script>
    // Lấy đối tượng form và nút s

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