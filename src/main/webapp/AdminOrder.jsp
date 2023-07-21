<%@ page contentType="text/html; charset=UTF-8" %>
<% Object orderInfo = null; %>
<!DOCTYPE html>
<html lang="en">
<%@ page import="Model.Orders" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Products" %>
<%@ page import="com.google.gson.Gson" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css"></script>
<head>
    <style>


        .searchForm {
            display: flex;
            align-items: center;
        }

        .formGroup {
            margin-right: 10px;
        }

        label {
            margin-right: 5px;
        }

        button {
            margin-left: 10px;
        }

        .pagination {
            display: flex;
            justify-content: center;
            align-items: center;
            margin-top: 20px;
        }

        .pagination a {
            display: inline-block;
            padding: 8px 16px;
            margin: 0 5px;
            text-decoration: none;
            color: #000;
            border: 1px solid #ddd;
            border-radius: 4px;
        }

        .pagination a.active {
            background-color: #007bff;
            color: #fff;
            border: 1px solid #007bff;
        }

        .status.delivered {
            background-color: #a3e6b9; /* Set the background color for "Delivered" state */
        }

        .status.return {
            background-color: #fddfdf; /* Set the background color for "Return" state */
        }

        .status.inProgress {
            background-color: #ffe5b4; /* Set the background color for "In Progress" state */
        }

        .status.pending {
            background-color: #f5f5f5; /* Set the background color for "Pending" state */
        }

        .btne {
            padding: 8px 16px;
            border: none;
            text-decoration: none;
            font-size: 14px;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            background-color: #007bff;
            color: #fff;
            margin-right: 5px;
            border: 1px solid #007bff; /* Đường viền cho nút "Edit" */
        }

        .btnd{

            padding: 8px 16px;
            border: none;
            text-decoration: none;
            font-size: 14px;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            background-color: #e82e3e;
            color: #fff;
            margin-right: 5px;
            border: 1px solid #007bff; /* Đường viền cho nút "Edit" */

        }



        .btn:hover {
            background-color: #0056b3;
        }





    </style>
    <meta charset="UTF-8">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Responsive Admin Dashboard | Korsat X Parmaga</title>
    <!-- ======= Styles ====== -->
    <link rel="stylesheet" href="assets/css/style01.css">

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Responsive Admin Dashboard | Korsat X Parmaga</title>
    <!-- ======= Styles ====== -->
    <link rel="stylesheet" href="assets/css/style01.css">
    <title>Chart.js Example - Area Chart</title>
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
                <a href="pages">
        <span class="icon">
            <ion-icon name="basket-outline"></ion-icon>
        </span>
                    <span class="title">Quản lí đơn hàng</span>
                </a>
            </li>

            <li>
                <a href="payments">
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
        <div class="topbar">
            <div class="toggle">
                <ion-icon name="menu-outline"></ion-icon>
            </div>



            <div class="user">
                <img src="assets/imgs/customer01.jpg" alt="">
            </div>
        </div>

        <!-- ======================= Cards ================== -->


        <!-- ================ Order Details List ================= -->
        <div class="details">
            <div class="recentOrders">
                <div class="cardHeader">
                    <div class="searchForm">
                        <form id="searchForm" action="pages" method="get">
                            <!-- Hidden input field to hold the order information -->
                            <div class="formGroup">
                                <input type="hidden" id="orderInfo" name="orderInfo" value='<%= new Gson().toJson(orderInfo) %>'>
                                <label for="orderDate">Order Date:</label>
                                <input type="date" id="orderDate" name="orderDate">
                            </div>
                            <div class="formGroup">
                                Sate:
                                <select name="state">
                                    <option value="">All States</option>
                                    <option value="inProgress">In Progress</option>
                                    <option value="pending">Pending</option>
                                    <option value="Return">Return</option>
                                </select>
                            </div>
                            <button type="submit" class="customButton">Search</button>
                        </form>
                    </div>

                </div>


                <table>
                    <thead>
                    <tr>
                        <td>User Name</td>
                        <td>Product Name</td>
                        <td>State</td>
                        <td>Total</td>
                        <td>Date</td>
                        <td>Action</td>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        List<Orders> orders = (List<Orders>) request.getAttribute("listO");
                        List<Orders> searchOrders = (List<Orders>) request.getAttribute("searchorders");

                        List<Orders> displayOrders = (orders != null) ? orders : searchOrders;

                        if (displayOrders != null && !displayOrders.isEmpty()) {
                            for (Orders order : displayOrders) {
                    %>
                    <tr>
                        <td><%= order.getUserName() %></td>
                        <td><%= order.getProductName() %></td>
                        <td class="status <%= order.getState().toLowerCase() %>"><%= order.getState() %></td>
                        <td><%= order.getTotal() %></td>
                        <td><%= order.getOrderDate() %></td>
                        <td>

                            <a href="deleteorders?id=<%= order.getOrder_id() %>" class="btnd" onclick="return confirm('Bạn có chắc chắn muốn xóa không?')">Delete</a>
                        </td>
                    </tr>
                    <script>
                        // Example JavaScript code to populate the order information before submitting the form
                        var orderInfo = {
                            orderId: "<%= order.getProductName() %>",
                            state: "<%= order.getState() %>",
                            total: "<%= order.getTotal() %>",
                            orderDate: "<%= order.getOrderDate() %>"
                        };


                        document.getElementById("orderInfo").value = JSON.stringify(orderInfo);
                    </script>
                    <%
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="5">No orders found.</td>
                    </tr>
                    <%
                        }
                    %>
                    </tbody>
                </table>

            </div>
        </div>





        <div class="pagination">
            <%
                Integer count = (Integer) request.getAttribute("count");
                if (count != null) {
                    for (int i = 1; i <= count; i++) {
                        if (i == 1) {
            %>
            <a href="pages?index=<%=i%>" class="active"><%= i %></a>
            <%
            } else {
            %>
            <a href="pages?index=<%=i%>"><%= i %></a>
            <%
                        }
                    }
                }
            %>
        </div>
    </div>
</div>

<script src="assets/js/main.js"></script>

<!-- ====== ionicons ======= -->
<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>

<!-- =========== Scripts =========  -->

</body>

</html>