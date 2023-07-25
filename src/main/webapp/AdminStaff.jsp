<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Model.Orders" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.User" %>
<%@ page import="Model.Staff" %>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Responsive Admin Dashboard | Korsat X Parmaga</title>
  <!-- ======= Styles ====== -->
  <link rel="stylesheet" href="assets/css/style01.css">
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
        <a href="payments">
                    <span class="icon">
                        <ion-icon name="bar-chart-outline"></ion-icon>
                    </span>
          <span class="title">Quản lí thống kê</span>
        </a>
      </li>




      <li>
        <a href="logout">
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


    <div class="details">
      <div class="recentOrders">
        <div class="cardHeader">
          <a href="AddStaff.jsp" class="btn">Add Staff</a>
        </div>

        <% List<Staff> staffList = (List<Staff>) request.getAttribute("total");
          if (staffList != null) { %>
        <table>
          <thead>
          <tr>
            <th>Staff ID</th>
            <th>Username</th>
            <th>Password</th>
            <th>Email</th>
            <th>Address</th>
            <th>Phone Number</th>
            <th>Status</th>
          </tr>
          </thead>
          <tbody>
          <% for (Staff staff : staffList) { %>
          <tr>
            <td><%= staff.getId() %></td>
            <td><%= staff.getUsername() %></td>
            <td><%= staff.getPassword() %></td>
            <td><%= staff.getEmail() %></td>
            <td><%= staff.getAddress() %></td>
            <td><%= staff.getPhone_number() %></td>
            <td><%= (staff.getRole() == 1) ? "Đã kích hoạt" : "Chưa kích hoạt" %></td>
          </tr>
          <% } %>
          </tbody>
        </table>
        <% } %>


      </div>
    </div>
  </div>
</div>

<!-- =========== Scripts =========  -->
<script src="assets/js/main.js"></script>

<!-- ====== ionicons ======= -->
<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>

</html>