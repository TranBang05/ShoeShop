<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="Model.User" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="Model.Products" %>
<%@ page import="java.util.List" %>
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

    User username = (User) request.getSession().getAttribute("username");
    if(username!=null){
        request.setAttribute("username",username);
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
                                        <%
                                            if(username!=null){%>

                                        <ul>
                                            <li class="account"><i class="fa fa-user"></i><a href="logout">LogOut</a></li>
                                        </ul>
                                        <%}else{%>
                                        <ul>
                                            <li class="account"><i class="fa fa-user"></i><a href="logout">Đăng Nhập</a></li>
                                        </ul>

                                        <%}
                                        %>

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
                                    <form class="header-searchbox">
                                        <input type="search" class="form-control" placeholder="Tìm Kiếm">
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
                                        <a class="shopping-wishlist-btn" href="shop-wishlist.html">
                                            <i class="pe-7s-like icon"></i>
                                        </a>
                                    </div>
                                    <div class="shopping-cart">
                                        <button class="shopping-cart-btn" type="button" data-bs-toggle="offcanvas" data-bs-target="#AsideOffcanvasCart" aria-controls="offcanvasRightLabel">
                                            <i class="pe-7s-shopbag icon"></i>
                                            <sup class="shop-count">02</sup>
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

                                    <li><a href="Home.jsp"><span>Trang Chủ</span></a>

                                    </li>

                                    <li ><a href="Shop.jsp"><span>Sản Phẩm</span></a>

                                    </li>
                                    <li><a href="blog-details-no-sidebar.html"><span>Blog</span></a>
                                    </li>
                                    <li><a href="contact.html"><span>Liên Hệ</span></a></li>
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
        <!--== Start Hero Area Wrapper ==-->
        <section class="home-slider-area">
            <div class="swiper-container home-slider-container default-slider-container">
                <div class="swiper-wrapper home-slider-wrapper slider-default">
                    <div class="swiper-slide">
                        <div class="slider-content-area" >



                            <div class="container">
                                <img src="https://sneakerdaily.vn/wp-content/uploads/2023/05/6.png.webp" width="100%" height="10%" >
                                <div class="slider-container">
                                    <div class="row justify-content-between align-items-center">
                                        <div class="col-sm-6 col-md-5">
                                            <div class="slider-content">
                                                <div class="content">

                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>

                </div>

                <!--== Add Swiper Arrows ==-->
                <div class="swiper-btn-wrap">
                    <div class="swiper-btn-prev">
                        <i class="pe-7s-angle-left"></i>
                    </div>
                    <div class="swiper-btn-next">
                        <i class="pe-7s-angle-right"></i>
                    </div>
                </div>
            </div>
        </section>
        <!--== End Hero Area Wrapper ==-->

        <!--== Start Product Collection Area Wrapper ==-->
        <section class="product-area product-collection-area">

            <div class="container">
                <div class="row">
                    <div class="col-lg-4 col-md-6">
                        <!--== Start Product Collection Item ==-->
                        <div class="product-collection">
                            <div class="inner-content">
                                <div class="product-collection-content">
                                    <div class="content">
                                        <h3 class="title" ><a href="shop.html">Giày Bóng Đá</a></h3>
                                    </div>
                                </div>
                                <div class="product-collection-thumb" data-bg-img="https://pos.nvncdn.net/b0b717-26181/pc/cateCT/20200117_8kqN0qRHydP4MLm4J63tG97f.jpg"></div>
                                <a class="banner-link-overlay" href="shop.html"></a>
                            </div>
                        </div>
                        <!--== End Product Collection Item ==-->
                    </div>
                    <div class="col-lg-4 col-md-6">
                        <!--== Start Product Collection Item ==-->
                        <div class="product-collection">
                            <div class="inner-content">
                                <div class="product-collection-content">
                                    <div class="content">
                                        <h3 class="title"><a href="shop.html">Giày bóng rổ
                                        </a></h3>

                                    </div>
                                </div>
                                <div class="product-collection-thumb" data-bg-img="https://pos.nvncdn.net/80cfbf-41716/ps/20230610_z5zDh3SKhL.jpeg"></div>
                                <a class="banner-link-overlay" href="shop.html"></a>
                            </div>
                        </div>
                        <!--== End Product Collection Item ==-->
                    </div>
                    <div class="col-lg-4 col-md-6">
                        <!--== Start Product Collection Item ==-->
                        <div class="product-collection">
                            <div class="inner-content">
                                <div class="product-collection-content">
                                    <div class="content">
                                        <h3 class="title"><a href="shop.html">Giày Trượt Ván</a></h3>

                                    </div>
                                </div>
                                <div class="product-collection-thumb" data-bg-img="https://mcdn.coolmate.me/image/August2021/00.jpg"></div>
                                <a class="banner-link-overlay" href="shop.html"></a>
                            </div>
                        </div>
                        <!--== End Product Collection Item ==-->
                    </div>
                </div>
            </div>
        </section>


        <section class="product-area product-default-area">
            <div class="container pt--0">
                <div class="row">
                    <c:forEach var="p" items="${list}">
                        <div class="col-12 col-md-6 col-lg-3">
                            <div class="product-item">
                                <div class="inner-content">
                                    <div class="product-thumb">
                                        <img src="${p.image}"  width="270px" height="274px" >
                                        <div class="product-action">
                                            <a class="btn-product-wishlist" href="shop-wishlist.html"><i class="fa fa-heart"></i></a>
                                        </div>
                                        <a class="banner-link-overlay" href="shop.html"></a>
                                    </div>
                                    <div class="product-info">
                                        <div class="category">
                                            <ul>
                                                <li>${p.name}</li>
                                            </ul>
                                        </div>
                                        <div class="prices">
                                            <span class="price">${p.price}</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </section>



    </main>








</div>

<!--=== jQuery Modernizr Min Js ===-->
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