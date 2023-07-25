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

    .container1 {
      max-width: 900px;
      margin: 0 auto;
      padding: 40px;

    }

    .form1 {
      margin-bottom: 20px;
    }

    .form1 h1 {
      margin-bottom: 20px;
      text-align: center;
    }

    .input-control {
      margin-bottom: 15px;
    }

    .input-control label {
      display: block;
      font-weight: bold;
      margin-bottom: 5px;
    }

    .input-control input {
      width: 100%;
      padding: 10px;
      border: 1px solid #ccc;
      border-radius: 5px;
    }

    .error {
      color: red;
      font-size: 14px;
      margin-top: 5px;
    }

    button {
      display: block;
      width: 100%;
      padding: 10px;
      background-color: #007bff;
      color: #fff;
      border: none;
      border-radius: 5px;
      cursor: pointer;
    }

    button:hover {
      background-color: #0056b3;
    }

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
          <span class="title">Quản lí tài khoản</span>
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
        <a href="staff">
                    <span class="icon">
                        <ion-icon name="people-outline"></ion-icon>
                    </span>
          <span class="title">Quản lí Nhân Viên</span>
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
    <div class="topbar">
      <div class="toggle">
        <ion-icon name="menu-outline"></ion-icon>
      </div>




    </div>

    <!-- ======================= Cards ================== -->


    <!-- ================ Order Details List ================= -->
    <div class="details">
      <div class="recentOrders">

        <div class="container1">
          <div class="form1">

            <form action="addStaff" method="post">
              <h1>Add Staff</h1>
              <div class="input-control">
                <label for="username">Username</label>
                <input type="text" id="username" name="username">
                <div class="error"></div>
              </div>
              <div class="input-control">
                <label for="email">Email</label>
                <input type="text" id="email" name="email">
                <div class="error"></div>
              </div>

              <div class="input-control">
                <label for="address">Address</label>
                <input type="text" id="address" name="address">
                <div class="error"></div>
              </div>
              <div class="input-control">
                <label for="phone">Phone</label>
                <input type="text" id="phone" name="phone">
                <div class="error"></div>
              </div>
              <div class="input-control">
                <label for="password">Password</label>
                <input type="password" id="password" name="password">
                <div class="error"></div>
              </div>

              <div class="input-control">
                <label for="repass">Re-enter Password</label>
                <input type="password" id="repass" name="repass">
                <div class="error"></div>
              </div>
              <button type="submit">Add Staff</button>
              <div class="mb-3">
                <%-- Lấy giá trị của biến mess từ request --%>
                <% String mess = (String) request.getAttribute("mess"); %>
                <%-- Kiểm tra và hiển thị nội dung của biến mess nếu nó tồn tại và không rỗng --%>
                <% if (mess != null && !mess.isEmpty()) { %>
                <p><%= mess %></p>
                <% } %>
              </div>

            </form>
          </div>
        </div>



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