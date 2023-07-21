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


    ArrayList<cart> cart_list = (ArrayList<cart>) session.getAttribute("cart-list");
    List<cart> cartProduct = null;
    if (cart_list != null) {
        DAO dao = new DAO(); // Khởi tạo đối tượng DAO
        try {
            cartProduct = dao.getCartProducts(cart_list);
            double total = dao.getTotalCartPrice(cart_list);

            request.setAttribute("total", total);
            request.setAttribute("cart_list", cart_list);
        } catch (Exception e) {
            // Xử lý ngoại lệ nếu có
            e.printStackTrace();
        }
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
                                        <img class="logo-main" src="assets/img/logo.webp" width="131" height="34" alt="Logo" />
                                        <img class="logo-light" src="assets/img/logo-light.webp" width="131" height="34" alt="Logo" />
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
                                    <li><a href="index.html">Home</a></li>
                                    <li class="breadcrumb-sep">//</li>
                                    <li>Shopping Cart</li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--== End Page Header Area Wrapper ==-->

        <!--== Start Blog Area Wrapper ==-->
        <section class="shopping-cart-area">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="shopping-cart-form table-responsive">
                            <form action="ordernow" method="post">
                                <table class="table text-center">
                                    <thead>
                                    <tr>
                                        <th class="product-remove">&nbsp;</th>
                                        <th class="product-thumb">&nbsp;</th>
                                        <th class="product-name">Product</th>
                                        <th class="product-price">Price</th>
                                        <th class="product-quantity">Quantity</th>
                                        <th class="product-subtotal">Total</th>
                                        <th class="product-subtotal">Buy Now</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <%
                                        if (cart_list != null) {
                                            for (cart c : cartProduct) {
                                    %>
                                                <tr class="cart-product-item">
                                                    <input type="hidden" name="id" value="<%= c.getId()%>" class="form-input">
                                                    <input type="hidden" class="quantity" title="Quantity" name="price" value="<%=c.getPrice()%>">
                                        <td class="product-remove">
                                            <a href="removecart?id=<%=c.getId() %>"><i class="fa fa-trash-o"></i></a>
                                        </td>
                                        <td class="product-thumb">
                                            <a href="single-product.html">
                                                <img src="<%=c.getImage()%>" width="90" height="110" alt="Image-HasTech">
                                            </a>
                                        </td>
                                        <td class="product-name">
                                            <h4 class="title"><a href="single-product.html"><%=c.getName()%></a></h4>
                                        </td>
                                        <td class="product-price">
                                            <c:choose>
                                                <c:when test="c.getDiscount() != null && c.getDdiscount() != 0.0)">

                                                    <span class="price" name="price"><%= c.getDiscountPrice() %></span>

                                                </c:when>
                                                <c:otherwise>

                                                    <span class="price" name="price"><%= c.getPrice() %></span>

                                                </c:otherwise>
                                            </c:choose>

                                        </td>
                                        <td class="product-quantity">

                                            <div class="pro-qty">
                                                <a href="quantityincdes?action=inc&id=<%=c.getId()%>">
                                                    <input type="text" class="quantity" title="Quantity" name="quantity" value="<%=c.getQuantity()%>">
                                                </a>

                                            </div>

                                        </td>
                                        <td class="product-subtotal">
                                            <span class="price"><%=c.getPrice()%></span>
                                        </td>

                                                    <td class="product-subtotal">
                                                        <button type="submit" class="btn btn-primary btn-sm">Buy</button>
                                                    </td>
                                    </tr>

                                    <%
                                            }}%>
                                    </tbody>
                                </table>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="row row-gutter-50">

                    <div class="col-md-6 col-lg-4">
                        <div class="shipping-form-coupon">
                            <div class="section-title-cart">
                                <h5 class="title">Mã Giảm Giá</h5>
                                <div class="desc">
                                    <p>Nhập mã phiếu giảm giá của bạn</p>
                                </div>
                            </div>
                            <form action="#" method="post">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label for="couponCode" class="visually-hidden">Mã Giảm Giá</label>
                                            <input type="text" id="couponCode" class="form-control" placeholder="Nhập Mã code..">
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <button type="submit" class="coupon-btn">Apply</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>

                    <div class="col-lg-6">
                        <div class="checkout-order-details-wrap">
                            <form action="checkout" method="post">
                                <div class="order-details-table-wrap table-responsive">



                                    <h2 class="title mb-25">Your order</h2>

                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th class="product-name">Product</th>
                                            <th class="product-total">Total</th>
                                        </tr>
                                        </thead>
                                        <tbody class="table-body">

                                            <%
                                    double total = 0.0; // Khởi tạo biến tổng ban đầu

                                     if (cart_list != null) {
                                            for (cart o : cartProduct) {
                                   %>
                                        <tr class="cart-item">
                                            <td class="product-name" name="name"><%= o.getName() %><span class="product-quantity">*</span></td>
                                            <td class="product-total" name="price"><%= o.getPrice() %></td>

                                        </tr>
                                            <%
                                           total += o.getPrice(); // Cộng giá mỗi món vào tổng
                                  }
                                     }
                                 %>

                                        <!-- ... -->

                                        <tfoot class="table-foot">
                                        <!-- ... -->
                                        <tr class="order-total">
                                            <th>Total</th>
                                            <td><%= total %></td>
                                        </tr>
                                        <input type="hidden" class="quantity" title="Quantity" name="price" value="<%=total%>">
                                        </tfoot>
                                    </table>

                                    <td >
                                        <button type="submit" class="btn-theme btn-flat">Đặt Hàng</button>
                                    </td>
                                </div>
                            </form>

                        </div>


                    </div>

                </div>
            </div>
        </section>
        <!--== End Blog Area Wrapper ==-->
    </main>

    <!--== Start Footer Area Wrapper ==-->
    <footer class="footer-area">
        <!--== Start Footer Main ==-->
        <div class="footer-main">
            <div class="container pt--0 pb--0">
                <div class="row">
                    <div class="col-md-6 col-lg-3">
                        <!--== Start widget Item ==-->
                        <div class="widget-item">
                            <div class="about-widget-wrap">
                                <div class="widget-logo-area">
                                    <a href="index.html">
                                        <img class="logo-main" src="assets/img/logo-light.webp" width="131" height="34" alt="Logo" />
                                    </a>
                                </div>
                                <p class="desc">Trang web bao gồm tất cả các đôi giày mới nhất trên thị trường</p>
                                <div class="social-icons">
                                    <a href="https://www.facebook.com/" target="_blank" rel="noopener"><i class="fa fa-facebook"></i></a>
                                    <a href="https://twitter.com/" target="_blank" rel="noopener"><i class="fa fa-twitter"></i></a>
                                </div>
                            </div>
                        </div>
                        <!--== End widget Item ==-->
                    </div>
                    <div class="col-md-6 col-lg-3">
                        <!--== Start widget Item ==-->
                        <div class="widget-item widget-services-item">
                            <h4 class="widget-title">Dịch Vụ</h4>
                            <h4 class="widget-collapsed-title collapsed" data-bs-toggle="collapse" data-bs-target="#widgetId-1">Dịch Vụ</h4>
                            <div id="widgetId-1" class="collapse widget-collapse-body">
                                <div class="collapse-body">
                                    <div class="widget-menu-wrap">
                                        <ul class="nav-menu">
                                            <li><a href="contact.html">Dịch vụ khách hàng</a></li>
                                            <li><a href="contact.html">Khuyến mãi và giảm giá</a></li>
                                            <li><a href="contact.html">Đổi/trả hàng</a></li>
                                            <li><a href="contact.html">Giao hàng</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--== End widget Item ==-->
                    </div>
                    <div class="col-md-6 col-lg-3">
                        <!--== Start widget Item ==-->
                        <div class="widget-item widget-account-item">
                            <h4 class="widget-title">Tài Khoản</h4>
                            <h4 class="widget-collapsed-title collapsed" data-bs-toggle="collapse" data-bs-target="#widgetId-2">Tài Khoản</h4>
                            <div id="widgetId-2" class="collapse widget-collapse-body">
                                <div class="collapse-body">
                                    <div class="widget-menu-wrap">
                                        <ul class="nav-menu">
                                            <li><a href="account-login.html">Tài Khoản</a></li>
                                            <li><a href="contact.html">Liên Hệ</a></li>
                                            <li><a href="shop-cart.html">Giỏ Hàng</a></li>
                                            <li><a href="shop">Sản Phẩm</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--== End widget Item ==-->
                    </div>
                    <div class="col-md-6 col-lg-3">
                        <!--== Start widget Item ==-->
                        <div class="widget-item">
                            <h4 class="widget-title">Thông Tin Liên Lạc</h4>
                            <h4 class="widget-collapsed-title collapsed" data-bs-toggle="collapse" data-bs-target="#widgetId-3">Thông Tin Liên Lạc</h4>
                            <div id="widgetId-3" class="collapse widget-collapse-body">
                                <div class="collapse-body">
                                    <div class="widget-contact-wrap">
                                        <ul>
                                            <li><span>Address:</span> Viet Nam</li>
                                            <li><span>Phone//fax:</span> <a href="tel://0123456789">0392156817</a></li>
                                            <li><span>Email:</span> <a href="mailto://demo@example.com">bang@example.com</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--== End widget Item ==-->
                    </div>
                </div>
            </div>
        </div>
        <!--== End Footer Main ==-->

        <!--== Start Footer Bottom ==-->
        <div class="footer-bottom">
            <div class="container pt--0 pb--0">
                <div class="row">

                    <div class="col-md-5 col-lg-6">
                        <div class="payment">
                            <a href="account-login.html"><img src="assets/img/photos/payment-card.webp" width="192" height="21" alt="Payment Logo"></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--== End Footer Bottom ==-->s
    </footer>

    <!--== End Footer Area Wrapper ==-->

    <!--== Scroll Top Button ==-->
    <div id="scroll-to-top" class="scroll-to-top"><span class="fa fa-angle-up"></span></div>

    <!--== Start Quick View Menu ==-->
    <aside class="product-quick-view-modal">
        <div class="product-quick-view-inner">
            <div class="product-quick-view-content">
                <button type="button" class="btn-close">
                    <span class="close-icon"><i class="fa fa-close"></i></span>
                </button>
                <div class="row align-items-center">
                    <div class="col-lg-6 col-md-6 col-12">
                        <div class="thumb">
                            <img src="assets/img/shop/product-single/1.webp" width="570" height="541" alt="Alan-Shop">
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-6 col-12">
                        <div class="content">
                            <h4 class="title">Space X Bag For Office</h4>
                            <div class="prices">
                                <del class="price-old">$85.00</del>
                                <span class="price">$70.00</span>
                            </div>
                            <p>Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia,</p>
                            <div class="quick-view-select">
                                <div class="quick-view-select-item">
                                    <label for="forSize" class="form-label">Size:</label>
                                    <select class="form-select" id="forSize" required>
                                        <option selected value="">s</option>
                                        <option>m</option>
                                        <option>l</option>
                                        <option>xl</option>
                                    </select>
                                </div>
                                <div class="quick-view-select-item">
                                    <label for="forColor" class="form-label">Color:</label>
                                    <select class="form-select" id="forColor" required>
                                        <option selected value="">red</option>
                                        <option>green</option>
                                        <option>blue</option>
                                        <option>yellow</option>
                                        <option>white</option>
                                    </select>
                                </div>
                            </div>
                            <div class="action-top">
                                <div class="pro-qty">
                                    <input type="text" id="quantity20" title="Quantity" value="1" />
                                </div>
                                <button class="btn btn-black">Add to cart</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="canvas-overlay"></div>
    </aside>
    <!--== End Quick View Menu ==-->

    <!--== Start Aside Cart Menu ==-->

    <!--== End Aside Cart Menu ==-->

    <!--== Start Aside Search Menu ==-->
    <aside class="aside-search-box-wrapper offcanvas offcanvas-top" tabindex="-1" id="AsideOffcanvasSearch" aria-labelledby="offcanvasTopLabel">
        <div class="offcanvas-header">
            <h5 class="d-none" id="offcanvasTopLabel">Aside Search</h5>
            <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"><i class="pe-7s-close"></i></button>
        </div>
        <div class="offcanvas-body">
            <div class="container pt--0 pb--0">
                <div class="search-box-form-wrap">
                    <div class="search-note">
                        <p>Start typing and press Enter to search</p>
                    </div>
                    <form action="#" method="post">
                        <div class="search-form position-relative">
                            <label for="search-input" class="visually-hidden">Search</label>
                            <input id="search-input" type="search" class="form-control" placeholder="Search entire store…">
                            <button class="search-button"><i class="fa fa-search"></i></button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </aside>
    <!--== End Aside Search Menu ==-->

    <!--== Start Side Menu ==-->

            <!-- Mobile Menu End -->
        </div>
    </div>
    <!--== End Side Menu ==-->
</div>

<!--=======================Javascript============================-->

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
</body>

</html>
