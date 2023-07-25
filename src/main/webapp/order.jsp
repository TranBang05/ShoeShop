<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="Model.User" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="Model.cart" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Products" %>
<%@ page import="static jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle.detail" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="DAO.DAO" %>
<%@ page import="DAL.DBContext" %>
<%@ page import="Model.Order" %>
<%@page isELIgnored="false" %>





<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<html>
<head>
    <title>TODO supply a title</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta name="description" content="Shome - Shoes eCommerce Website Template"/>
    <meta name="keywords" content="footwear, shoes, modern, shop, store, ecommerce, responsive, e-commerce"/>
    <meta name="author" content="codecarnival"/>

    <title>Shome - Shoes eCommerce Website Template</title>

    <!--== Favicon ==-->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico" type="image/x-icon" />

    <!--== Google Fonts ==-->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="${pageContext.request.contextPath}/https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,300;0,400;0,600;0,700;0,800;1,400;1,500&display=swap" rel="stylesheet">

    <!--== Bootstrap CSS ==-->
    <link href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" rel="stylesheet" />
    <!--== Font Awesome Min Icon CSS ==-->
    <link href="${pageContext.request.contextPath}/assets/css/font-awesome.min.css" rel="stylesheet" />
    <!--== Pe7 Stroke Icon CSS ==-->
    <link href="${pageContext.request.contextPath}/assets/css/pe-icon-7-stroke.css" rel="stylesheet" />
    <!--== Swiper CSS ==-->
    <link href="${pageContext.request.contextPath}/assets/css/swiper.min.css" rel="stylesheet" />
    <!--== Fancybox Min CSS ==-->
    <link href="${pageContext.request.contextPath}/assets/css/fancybox.min.css" rel="stylesheet" />
    <!--== Aos Min CSS ==-->
    <link href="${pageContext.request.contextPath}/assets/css/aos.min.css" rel="stylesheet" />

    <!--== Main Style CSS ==-->
    <link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet" />

    <!--[if lt IE 9]>
    <script src="${pageContext.request.contextPath}///oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="${pageContext.request.contextPath}///oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>


<%
    List<Order> orders = null;


    User username = (User) request.getSession().getAttribute("username");
    if(username!=null){
        request.setAttribute("username",username);
        DAO orderDao  = new DAO();
         orders = orderDao.userOrders(username.getId());
        request.setAttribute("order-list", orders);


    }else{
        response.sendRedirect("Login.jsp");
    }



%>




<body>
<!--wrapper start-->
<div class="wrapper">

    <!--== Start Header Wrapper ==-->
    <header class="main-header-wrapper position-relative">
        <div class="header-top">
            <div class="container pt--0 pb--0">
                <div class="row">
                    <div class="col-12">
                        <div class="header-top-align">
                            <div class="header-top-align-start">

                            </div>
                            <div class="header-top-align-end">
                                <div class="header-info-items">
                                    <div class="info-items">

                                        <c:if test="${sessionScope.username != null}">
                                            <ul>
                                                <li class="account"><i class="fa fa-user"></i><a href="account-detail">Xin chào ${sessionScope.username.username}</a></li>
                                                <li class="account"><i class="fa fa-user"></i><a href="logout">LogOut</a></li>
                                            </ul>
                                        </c:if>

                                        <c:if test="${sessionScope.username==null}">
                                            <ul>
                                                <li class="account"><i class="fa fa-user"></i><a href="Login.jsp">Đăng Nhập</a></li>
                                            </ul>

                                        </c:if>


                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="header-middle">
            <div class="container pt--0 pb--0">
                <div class="row align-items-center">
                    <div class="col-12">
                        <div class="header-middle-align">
                            <div class="header-middle-align-start">
                                <div class="header-logo-area">
                                    <a href="index.html">
                                        <img class="logo-main" src="https://png.pngtree.com/png-clipart/20190619/original/pngtree-sneakers-png-image_3989154.jpg" width="30%" height="30%"/>
                                        <img class="logo-light" src="https://png.pngtree.com/png-clipart/20190619/original/pngtree-sneakers-png-image_3989154.jpg" width="30%" height="30%"/>
                                    </a>
                                </div>
                            </div>
                            <div class="header-middle-align-center">
                                <div class="header-search-area">
                                    <form class="header-searchbox" action="search">
                                        <input type="search" class="form-control" name="txt" placeholder="Tìm Kiếm">
                                        <button class="btn-submit" type="submit"><i class="pe-7s-search"></i></button>
                                    </form>
                                </div>
                            </div>
                            <div class="header-middle-align-end">
                                <div class="header-action-area">
                                    <div class="shopping-search">
                                        <button class="shopping-search-btn" type="button" data-bs-toggle="offcanvas" data-bs-target="#AsideOffcanvasSearch" aria-controls="AsideOffcanvasSearch"><i class="pe-7s-search icon"></i></button>
                                    </div>
                                    <div class="shopping-wishlist">
                                        <a class="shopping-wishlist-btn" href="Wishlist.jsp">
                                            <i class="pe-7s-like icon"></i>
                                        </a>
                                    </div>
                                    <div class="shopping-cart">



                                        <button class="shopping-cart-btn" type="button" href="ShopCart.jsp">
                                            <a class="shopping-cart-btn" href="ShopCart.jsp">


                                                <i class="pe-7s-shopbag icon"></i>
                                                <sup class="shop-count">${cart_list.size()}</sup>
                                            </a>
                                        </button>

                                    </div>
                                    <button class="btn-menu" type="button" data-bs-toggle="offcanvas" data-bs-target="#AsideOffcanvasMenu" aria-controls="AsideOffcanvasMenu">
                                        <i class="pe-7s-menu"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="header-area header-default">
            <div class="container">
                <div class="row no-gutter align-items-center position-relative">
                    <div class="col-12">
                        <div class="header-align">
                            <div class="header-navigation-area position-relative">
                                <ul class="main-menu nav">

                                    <li><a href="home"><span>Trang Chủ</span></a>

                                    </li>

                                    <li ><a href="shop"><span>Sản Phẩm</span></a>

                                    </li>
                                    <li><a href="blog"><span>Blog</span></a>
                                    </li>
                                    <li><a href="feedback"><span>Liên Hệ</span></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </header>

    <!--== End Header Wrapper ==-->
    <main class="main-content">
        <!--== Start Page Header Area Wrapper ==-->
        <div class="page-header-area" data-bg-img="https://images5.alphacoders.com/594/594404.jpg?fbclid=IwAR2S-ndjiHJQdCYAb6mKHwJjH4jF6HiBzUL7NK7qRCk2UYTWbP4moM_JUkA">
            <div class="container pt--0 pb--0">
                <div class="row">
                    <div class="col-12">
                        <div class="page-header-content">
                            <h2 class="title" data-aos="fade-down" data-aos-duration="1000">Shopping Cart</h2>
                            <nav class="breadcrumb-area" data-aos="fade-down" data-aos-duration="1200">
                                <ul class="breadcrumb">
                                    <li><a href="home">Home</a></li>
                                    <li class="breadcrumb-sep">//</li>
                                    <li>Shopping Cart</li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="container">
            <div class="card-header my-3">Tất Cả</div>


            <table class="table table-light">
                <thead>
                <tr>
                    <th scope="col" type="date">Date</th>
                    <th scope="col">Name</th>
                    <th scope="col">Quantity</th>
                    <th scope="col">Price</th>
                    <th scope="col">Cancel</th>
                </tr>
                </thead>
                <tbody>

                <%
                    if(orders != null){
                        for(Order o:orders){%>
                <tr>
                    <td><%=o.getDate() %></td>
                    <td><%=o.getName() %></td>
                    <td><%=o.getQunatity() %></td>

                    <td><%=o.getTotal_cost() %></td>
                    <td><a class="btn btn-sm btn-danger" href="remove?id=<%=o.getOrderId()%>">Cancel Order</a></td>
                </tr>
                <%}
                }
                %>

                </tbody>
            </table>



        </div>
        <!--== End Page Header Area Wrapper ==-->

        <!--== Start Shopping Checkout Area Wrapper ==-->
        <section class="shopping-checkout-wrap">
            <div class="container">

                <div class="row">
                    </div>
                    <div class="col-lg-12">
                        <!--== Start Order Details Accordion ==-->
                        <div class="checkout-order-details-wrap">
                            <form action="payment" method="post">
                            <div class="order-details-table-wrap table-responsive">
                                <h2 class="title mb-25">Thông Tin Đặt Hàng</h2>
                                <div class="row">

                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label >Số Điện Thoại</label>
                                            <input  type="text"  class="form-control" name="phone">
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label  >Địa Chỉ Nhận Hàng</label>
                                            <input type="text"  class="form-control" name="address">
                                        </div>
                                    </div>


                                    <div class="col-md-12">
                                        <div class="form-group mb--0">
                                            <label >Ghi Chú</label>
                                            <textarea  class="form-control" placeholder=" "  name="note"></textarea>
                                        </div>
                                    </div>
                                </div>
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th class="product-name">Sản Phẩm</th>
                                        <th class="product-total">Tổng</th>
                                    </tr>
                                    </thead>
                                    <tbody class="table-body">

                                        <%
                                    double total = 0.0; // Khởi tạo biến tổng ban đầu

                                    if (orders != null) {
                                        for (Order o : orders) {
                                   %>
                                    <tr class="cart-item">
                                        <td class="product-name" name="name"><%= o.getName() %><span class="product-quantity">*<%=o.getQunatity()%></span></td>
                                        <td class="product-total" name="price"><%= o.getTotal_cost() %></td>
                                    </tr>
                                        <%
                                           total += o.getTotal_cost();
                                  }
                                     }
                                 %>

                                    <!-- ... -->

                                    <tfoot class="table-foot">

                                    <tr class="order-total">
                                        <th>Total</th>
                                        <td><%= total %></td>
                                    </tr>
                                    <input type="hidden" class="quantity" title="Quantity" name="price" value="<%=total%>">
                                    </tfoot>
                                </table>

                                <div class="shop-payment-method">


                                    <div class="agree-policy">
                                        <div class="custom-control custom-checkbox">
                                            <input type="checkbox" id="privacy" class="custom-control-input visually-hidden">
                                            <label>
                                                <input type="checkbox" id="agree" name="agree" required>
                                                Tôi đã đọc và chấp nhận các điều khoản và điều kiện của trang web.
                                            </label>
                                        </div>
                                    </div>
                                    <td >
                                        <button type="submit" class="btn-theme btn-flat">Đặt Hàng</button>
                                    </td>
                                </div>
                            </div>
                            </form>
                        </div>
                        <!--== End Order Details Accordion ==-->
                    </div>
                </div>
          
        </section>
        <!--== End Shopping Checkout Area Wrapper ==-->
    </main>


</div>


<script src="${pageContext.request.contextPath}/assets/js/modernizr.js"></script>
<!--=== jQuery Min Js ===-->
<script src="${pageContext.request.contextPath}/assets/js/jquery-main.js"></script>
<!--=== jQuery Migration Min Js ===-->
<script src="${pageContext.request.contextPath}/assets/js/jquery-migrate.js"></script>
<!--=== jQuery Popper Min Js ===-->
<script src="${pageContext.request.contextPath}/assets/js/popper.min.js"></script>
<!--=== jQuery Bootstrap Min Js ===-->
<script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
<!--=== jQuery Ui Min Js ===-->
<script src="${pageContext.request.contextPath}/assets/js/jquery-ui.min.js"></script>
<!--=== jQuery Swiper Min Js ===-->
<script src="${pageContext.request.contextPath}/assets/js/swiper.min.js"></script>
<!--=== jQuery Fancybox Min Js ===-->
<script src="${pageContext.request.contextPath}/assets/js/fancybox.min.js"></script>
<!--=== jQuery Waypoint Js ===-->
<script src="${pageContext.request.contextPath}/assets/js/waypoint.js"></script>
<!--=== jQuery Parallax Min Js ===-->
<script src="${pageContext.request.contextPath}/assets/js/parallax.min.js"></script>
<!--=== jQuery Aos Min Js ===-->
<script src="${pageContext.request.contextPath}/assets/js/aos.min.js"></script>

<!--=== jQuery Custom Js ===-->
<script src="${pageContext.request.contextPath}/assets/js/custom.js"></script>
<script>
    const agreeCheckbox = document.getElementById('agree');
    const registerBtn = document.getElementById('register-btn');

    agreeCheckbox.addEventListener('change', function () {
        if (this.checked) {
            registerBtn.removeAttribute('disabled');
        } else {
            registerBtn.setAttribute('disabled', true);
        }
    });
</script>
</body>
<style>

</style>

</html>
