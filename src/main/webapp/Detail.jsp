<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="Model.User" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="Model.Products" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.cart" %>
<%@ page import="java.util.ArrayList" %>
<%@page isELIgnored="false" %>
<%@ page import="DAO.DAO" %>





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
        <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/toastify-js/src/toastify.min.css">
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
        if (username != null) {
            request.setAttribute("username", username);
        }

        ArrayList<cart> cart_list = (ArrayList<cart>) session.getAttribute("cart-list");
        List<cart> cartProduct = null;
        if (cart_list != null) {
            DAO dao = new DAO(); // Khởi tạo đối tượng DAO
            try {
                cartProduct = dao.getCartProducts(cart_list);
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
                                                        <li class="account"><i class="fa fa-user"></i><a href="logout">Xin chào ${sessionScope.username.username}</a></li>
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
                <!--== Start Page Header Area Wrapper ==-->
                <div class="page-header-area" data-bg-img="https://images5.alphacoders.com/594/594404.jpg?fbclid=IwAR2S-ndjiHJQdCYAb6mKHwJjH4jF6HiBzUL7NK7qRCk2UYTWbP4moM_JUkA">
                    <div class="container pt--0 pb--0">
                        <div class="row">
                            <div class="col-12">
                                <div class="page-header-content">
                                    <h2 class="title" data-aos="fade-down" data-aos-duration="1000">Product Details</h2>
                                    <nav class="breadcrumb-area" data-aos="fade-down" data-aos-duration="1200">
                                        <ul class="breadcrumb">
                                            <li><a href="index.html">Home</a></li>
                                            <li class="breadcrumb-sep">//</li>
                                            <li>Product Details</li>
                                        </ul>
                                    </nav>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--== End Page Header Area Wrapper ==-->

                <!--== Start Product Single Area Wrapper ==-->
                <section class="product-area product-single-area">
                    <div class="container">

                        <div class="row">
                            <div class="col-12">
                                <div class="product-single-item">
                                    <div class="row">
                                        <div class="col-xl-6">

                                            <!--== Start Product Thumbnail Area ==-->
                                            <div class="product-single-thumb">
                                                <div class="swiper-container single-product-thumb single-product-thumb-slider">
                                                    <div class="swiper-wrapper">
                                                        <div class="swiper-slide">
                                                            <a class="lightbox-image" data-fancybox="gallery" href="assets/img/shop/product-single/1.webp">
                                                                <img src="${detail.image}" width="570" height="541" alt="Image-HasTech">
                                                            </a>
                                                        </div>
                                                        <div class="swiper-slide">
                                                            <a class="lightbox-image" data-fancybox="gallery" href="assets/img/shop/product-single/2.webp">
                                                                <img src="assets/img/shop/product-single/2.webp" width="570" height="541" alt="Image-HasTech">
                                                            </a>
                                                        </div>
                                                        <div class="swiper-slide">
                                                            <a class="lightbox-image" data-fancybox="gallery" href="assets/img/shop/product-single/3.webp">
                                                                <img src="assets/img/shop/product-single/3.webp" width="570" height="541" alt="Image-HasTech">
                                                            </a>
                                                        </div>
                                                        <div class="swiper-slide">
                                                            <a class="lightbox-image" data-fancybox="gallery" href="assets/img/shop/product-single/4.webp">
                                                                <img src="assets/img/shop/product-single/4.webp" width="570" height="541" alt="Image-HasTech">
                                                            </a>
                                                        </div>
                                                        <div class="swiper-slide">
                                                            <a class="lightbox-image" data-fancybox="gallery" href="assets/img/shop/product-single/5.webp">
                                                                <img src="assets/img/shop/product-single/5.webp" width="570" height="541" alt="Image-HasTech">
                                                            </a>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="swiper-container single-product-nav single-product-nav-slider">

                                                </div>
                                            </div>
                                            <!--== End Product Thumbnail Area ==-->
                                        </div>

                                        <div class="col-xl-6">
                                            <!--== Start Product Info Area ==-->

                                            <div class="product-single-info">
                                                <h3 class="main-title">${detail.name}</h3>
                                                <div class="prices">
                                                    <span class="price">${detail.price}</span>
                                                </div>
                                                <div class="rating-box-wrap">
                                                    <div class="rating-box">
                                                        <%-- Display the average star rating dynamically --%>
                                                        <c:forEach begin="1" end="${avgStarRating}" var="i">
                                                            <i class="fa fa-star"></i>
                                                        </c:forEach>
                                                        <%-- Display remaining empty stars based on the maximum rating --%>
                                                        <c:forEach begin="${avgStarRating + 1}" end="5" var="i">
                                                            <i class="fa fa-star-o"></i>
                                                        </c:forEach>
                                                    </div>
                                                    <div class="review-status">
                                                        <%-- Display the total customer reviews dynamically --%>
                                                        <a href="javascript:void(0)">${totalCount} Customer Review</a>
                                                    </div>
                                                </div>
                                                <p>${detail.description}</p>

                                                <div class="product-size">
                                                    <h6 class="title">Size</h6>
                                                    <ul class="size-list">
                                                        <li>S</li>
                                                        <li class="active">M</li>
                                                        <li>L</li>
                                                        <li>XL</li>
                                                    </ul>
                                                </div>

                                                <div class="product-quick-action">
                                                    <div class="qty-wrap">
                                                        <div class="pro-qty">
                                                            <input type="text" title="Quantity" value="1">
                                                        </div>
                                                    </div>

                                                    <a class="btn-theme" href="addtocart?id=${detail.id}">Add to Cart</a>

                                                </div>
                                                <div class="product-wishlist-compare">
                                                    <a href="wishlist?id=${detail.id}" ><i class="pe-7s-like"></i>Add to Wishlist</a>

                                                </div>


                                            </div>

                                            <!--== End Product Info Area ==-->
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>



                        <div class="row">
                            <div class="col-12">
                                <div class="product-review-tabs-content">
                                    <ul class="nav product-tab-nav" id="ReviewTab" role="tablist">
                                        <li role="presentation">
                                            <a class="active" id="information-tab" data-bs-toggle="pill" href="#information" role="tab" aria-controls="information" aria-selected="true">Information</a>
                                        </li>
                                        <li role="presentation">
                                            <a id="description-tab" data-bs-toggle="pill" href="#description" role="tab" aria-controls="description" aria-selected="false">Description</a>
                                        </li>
                                        <li role="presentation">
                                            <a id="reviews-tab" data-bs-toggle="pill" href="#reviews" role="tab" aria-controls="reviews" aria-selected="false">Reviews <span>(${totalCount})</span></a>
                                        </li>
                                    </ul>
                                    <div class="tab-content product-tab-content" id="ReviewTabContent">
                                        <div class="tab-pane fade show active" id="information" role="tabpanel" aria-labelledby="information-tab">
                                            <div class="product-information">
                                                <p>${detail.description}</p>
                                            </div>
                                        </div>
                                        <div class="tab-pane fade" id="description" role="tabpanel" aria-labelledby="description-tab">
                                            <div class="product-description">
                                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim adlo minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in tun tuni reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserun mollit anim id est laborum. Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rel aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur.</p>
                                            </div>
                                        </div>
                                        <div class="tab-pane fade" id="reviews" role="tabpanel" aria-labelledby="reviews-tab">
                                            <div class="product-review-content">
                                                <div class="review-content-header">
                                                    <h3>Customer Reviews</h3>
                                                    <div class="review-info">
                                                        <ul class="review-rating">
                                                            <c:forEach begin="1" end="${avgStarRating}">
                                                                <li class="fa fa-star"></li>
                                                                </c:forEach>
                                                                <c:forEach begin="${avgStarRating + 1}" end="5">
                                                                <li class="fa fa-star-o"></li>
                                                                </c:forEach>
                                                        </ul>
                                                        <span class="review-caption">Based on ${totalCount} reviews</span>
                                                        <span class="review-write-btn">Write a review</span>
                                                    </div>
                                                </div>

                                                <!--== Start Reviews Form Item ==-->
                                                <div class="reviews-form-area">
                                                    <h4 class="title">Write a review</h4>
                                                    <div class="reviews-form-content">
                                                        <form id="feedbackForm" action="#" method="POST">
                                                            <div class="row">
                                                                <div class="col-md-12">
                                                                    <div class="form-group" >
                                                                        <span class="title">Rating</span>
                                                                        <ul class="review-rating">
                                                                            <li class="fa fa-star-o" data-star="1"></li>
                                                                            <li class="fa fa-star-o" data-star="2"></li>
                                                                            <li class="fa fa-star-o" data-star="3"></li>
                                                                            <li class="fa fa-star-o" data-star="4"></li>
                                                                            <li class="fa fa-star-o" data-star="5"></li>
                                                                        </ul>
                                                                        <input type="hidden" id="feedbackRating" name="rating" value="0" required="">
                                                                    </div>
                                                                </div>
                                                                <div class="col-md-12">
                                                                    <div class="form-group">
                                                                        <label for="feedbackTitle">Review Title</label>
                                                                        <input id="feedbackTitle" class="form-control" type="text" placeholder="Give your review a title" required="">
                                                                        <input type="hidden" id="productId" name="productId" value="${product_id}">
                                                                    </div>
                                                                </div>
                                                                <div class="col-md-12">
                                                                    <div class="form-group">
                                                                        <label for="feedbackContent">Body of Review (1500)</label>
                                                                        <textarea id="feedbackContent" class="form-control" placeholder="Write your comments here" required=""></textarea>
                                                                    </div>
                                                                </div>
                                                                <div class="col-md-12">
                                                                    <div class="form-submit-btn">
                                                                        <button id="addFeedbackButton" type="submit" class="btn-submit">Post comment</button>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>

                                                <!--== End Reviews Form Item ==-->

                                                <div class="reviews-content-body">
                                                    <c:forEach items="${productFeedback}" var="feedback">
                                                        <div class="review-item">
                                                            <ul class="review-rating">
                                                                <c:forEach begin="1" end="${feedback.star}">
                                                                    <li class="fa fa-star"></li>
                                                                    </c:forEach>
                                                                    <c:forEach begin="${feedback.star + 1}" end="5">
                                                                    <li class="fa fa-star-o"></li>
                                                                    </c:forEach>
                                                            </ul>
                                                            <h3 class="title"> <b>${feedback.title}</b></h3>
                                                            <h5 class="sub-title"><span>${feedback.authorName}</span> no <span>${feedback.date_posted}</span></h5>
                                                            <p>${feedback.content}</p>
                                                            <a href="#/">Report as Inappropriate</a>
                                                        </div>
                                                    </c:forEach>
                                                </div>

                                                <!--== Start Reviews Pagination Item ==-->
                                                <div class="col-12">
                                                    <div class="pagination-items">
                                                        <ul class="pagination d-inline-flex">
                                                            <c:if test="${currentPage > 1}">
                                                                <li class="page-item">
                                                                    <a class="page-link" href="?pid=${product_id}&amp;page=${currentPage - 1}&amp;pageSize=${pageSize}">&laquo; </a>
                                                                </li>
                                                            </c:if>

                                                            <c:forEach begin="1" end="${totalPages}" var="pageNumber">
                                                                <c:choose>
                                                                    <c:when test="${pageNumber == currentPage}">
                                                                        <li class="page-item active">
                                                                            <span class="page-link">${pageNumber}</span>
                                                                        </li>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <li class="page-item">
                                                                            <a class="page-link" href="?pid=${product_id}&amp;page=${pageNumber}&amp;pageSize=${pageSize}">${pageNumber}</a>
                                                                        </li>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </c:forEach>

                                                            <c:if test="${currentPage < totalPages}">
                                                                <li class="page-item">
                                                                    <a class="page-link" href="?pid=${product_id}&amp;page=${currentPage + 1}&amp;pageSize=${pageSize}"> &raquo;</a>
                                                                </li>
                                                            </c:if>
                                                        </ul>
                                                    </div>
                                                </div>
                                                <!--== End Reviews Pagination Item ==-->
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
                <!--== End Product Single Area Wrapper ==-->

                <!--== Start Product Area Wrapper ==-->
                <section class="product-area product-best-seller-area">
                    <div class="container pt--0">
                        <div class="row">
                            <div class="col-12">
                                <div class="section-title text-center">
                                    <h3 class="title">Những sảm phẩm tương tự</h3>
                                    <div class="desc">
                                        <p>Có rất nhiều mẫu của tương tự sản phẩm của bạn lựa chọn</p>
                                    </div>
                                </div>
                            </div>
                        </div>


                        <div class="row">

                            <div class="col-12">

                                <div class="product-slider-wrap">
                                    <div class="swiper-container product-slider-col4-container">
                                        <div class="swiper-wrapper">
                                            <c:forEach var="p" items="${list}">
                                                <div class="swiper-slide">
                                                    <!--== Start Product Item ==-->

                                                    <div class="product-item">
                                                        <div class="inner-content">
                                                            <div class="product-thumb">
                                                                <a href="detail?pid=${p.id}">
                                                                    <img src="${p.image}" width="270" height="274" alt="Image-HasTech">
                                                                </a>

                                                                <div class="product-action">
                                                                    <a class="btn-product-wishlist" href="shop-wishlist.html"><i class="fa fa-heart"></i></a>

                                                                </div>
                                                                <a class="banner-link-overlay" href="shop.html"></a>
                                                            </div>
                                                            <div class="product-info">

                                                                <h4 class="title"><a href="detail?pid=${p.id}">${p.name}</a></h4>
                                                                <div class="prices">
                                                                    <span class="price">${p.price}</span>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <!--== End prPduct Item ==-->
                                                </div>
                                            </c:forEach>


                                        </div>
                                    </div>
                                    <!--== Add Swiper Arrows ==-->
                                    <div class="product-swiper-btn-wrap">
                                        <div class="product-swiper-btn-prev">
                                            <i class="fa fa-arrow-left"></i>
                                        </div>
                                        <div class="product-swiper-btn-next">
                                            <i class="fa fa-arrow-right"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
                <!--== End Product Area Wrapper ==-->
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
                                        <p class="desc">Lorem ipsum dolor sit amet consl adipisi elit, sed do eiusmod templ incididunt ut labore</p>
                                        <div class="social-icons">
                                            <a href="https://www.facebook.com/" target="_blank" rel="noopener"><i class="fa fa-facebook"></i></a>
                                            <a href="https://dribbble.com/" target="_blank" rel="noopener"><i class="fa fa-dribbble"></i></a>
                                            <a href="https://www.pinterest.com/" target="_blank" rel="noopener"><i class="fa fa-pinterest-p"></i></a>
                                            <a href="https://twitter.com/" target="_blank" rel="noopener"><i class="fa fa-twitter"></i></a>
                                        </div>
                                    </div>
                                </div>
                                <!--== End widget Item ==-->
                            </div>
                            <div class="col-md-6 col-lg-3">
                                <!--== Start widget Item ==-->
                                <div class="widget-item widget-services-item">
                                    <h4 class="widget-title">Services</h4>
                                    <h4 class="widget-collapsed-title collapsed" data-bs-toggle="collapse" data-bs-target="#widgetId-1">Services</h4>
                                    <div id="widgetId-1" class="collapse widget-collapse-body">
                                        <div class="collapse-body">
                                            <div class="widget-menu-wrap">
                                                <ul class="nav-menu">
                                                    <li><a href="contact.html">Home monitoring</a></li>
                                                    <li><a href="contact.html">Air Filters</a></li>
                                                    <li><a href="contact.html">Professional installation</a></li>
                                                    <li><a href="contact.html">Smart Buildings</a></li>
                                                    <li><a href="contact.html">For contractors</a></li>
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
                                    <h4 class="widget-title">My Account</h4>
                                    <h4 class="widget-collapsed-title collapsed" data-bs-toggle="collapse" data-bs-target="#widgetId-2">My Account</h4>
                                    <div id="widgetId-2" class="collapse widget-collapse-body">
                                        <div class="collapse-body">
                                            <div class="widget-menu-wrap">
                                                <ul class="nav-menu">
                                                    <li><a href="account-login.html">My Account</a></li>
                                                    <li><a href="contact.html">Contact</a></li>
                                                    <li><a href="shop-cart.html">Shopping cart</a></li>
                                                    <li><a href="shop.html">Shop</a></li>
                                                    <li><a href="account-login.html">Services Login</a></li>
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
                                    <h4 class="widget-title">Contact Info</h4>
                                    <h4 class="widget-collapsed-title collapsed" data-bs-toggle="collapse" data-bs-target="#widgetId-3">Contact Info</h4>
                                    <div id="widgetId-3" class="collapse widget-collapse-body">
                                        <div class="collapse-body">
                                            <div class="widget-contact-wrap">
                                                <ul>
                                                    <li><span>Address:</span> Your address goes here.</li>
                                                    <li><span>Phone//fax:</span> <a href="tel://0123456789">0123456789</a></li>
                                                    <li><span>Email:</span> <a href="mailto://demo@example.com">demo@example.com</a></li>
                                                    <li><a target="_blank" href="https://www.hasthemes.com">www.example.com</a></li>
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
                            <div class="col-md-7 col-lg-6">
                                <p class="copyright">© 2021 Shome. Made with <i class="fa fa-heart"></i> by <a target="_blank" href="https://themeforest.net/user/codecarnival/portfolio">Codecarnival.</a></p>
                            </div>
                            <div class="col-md-5 col-lg-6">
                                <div class="payment">
                                    <a href="account-login.html"><img src="assets/img/photos/payment-card.webp" width="192" height="21" alt="Payment Logo"></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--== End Footer Bottom ==-->
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
            <div class="aside-cart-wrapper offcanvas offcanvas-end" tabindex="-1" id="AsideOffcanvasCart" aria-labelledby="offcanvasRightLabel">
                <div class="offcanvas-header">
                    <h1 id="offcanvasRightLabel"></h1>
                    <button class="btn-aside-cart-close" data-bs-dismiss="offcanvas" aria-label="Close">Shopping Cart <i class="fa fa-chevron-right"></i></button>
                </div>
                <div class="offcanvas-body">
                    <ul class="aside-cart-product-list">
                        <li class="product-list-item">
                            <a href="#/" class="remove">×</a>
                            <a href="single-product.html">
                                <img src="assets/img/shop/product-mini/1.webp" width="90" height="110" alt="Image-HasTech">
                                <span class="product-title">Leather Mens Slipper</span>
                            </a>
                            <span class="product-price">1 × £69.99</span>
                        </li>
                        <li class="product-list-item">
                            <a href="#/" class="remove">×</a>
                            <a href="single-product.html">
                                <img src="assets/img/shop/product-mini/2.webp" width="90" height="110" alt="Image-HasTech">
                                <span class="product-title">Quickiin Mens shoes</span>
                            </a>
                            <span class="product-price">1 × £20.00</span>
                        </li>
                    </ul>
                    <p class="cart-total"><span>Subtotal:</span><span class="amount">£89.99</span></p>
                    <a class="btn-theme" data-margin-bottom="10" href="shop-cart.html">View cart</a>
                    <a class="btn-theme" href="shop-checkout.html">Checkout</a>
                    <a class="d-block text-end lh-1" href="shop-checkout.html"><img src="assets/img/photos/paypal.webp" width="133" height="26" alt="Has-image"></a>
                </div>
            </div>
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
            <div class="off-canvas-wrapper offcanvas offcanvas-start" tabindex="-1" id="AsideOffcanvasMenu" aria-labelledby="offcanvasExampleLabel">
                <div class="offcanvas-header">
                    <h1 id="offcanvasExampleLabel"></h1>
                    <button class="btn-menu-close" data-bs-dismiss="offcanvas" aria-label="Close">menu <i class="fa fa-chevron-left"></i></button>
                </div>
                <div class="offcanvas-body">
                    <div class="info-items">
                        <ul>
                            <li class="number"><a href="tel://0123456789"><i class="fa fa-phone"></i>+00 123 456 789</a></li>
                            <li class="email"><a href="mailto://demo@example.com"><i class="fa fa-envelope"></i>demo@example.com</a></li>
                            <li class="account"><a href="account-login.html"><i class="fa fa-user"></i>Account</a></li>
                        </ul>
                    </div>
                    <!-- Mobile Menu Start -->
                    <div class="mobile-menu-items">
                        <ul class="nav-menu">
                            <li><a href="#">Home</a>
                                <ul class="sub-menu">
                                    <li><a href="index.html">Home One</a></li>
                                    <li><a href="index-two.html">Home Two</a></li>
                                </ul>
                            </li>
                            <li><a href="about-us.html">About</a></li>
                            <li><a href="#">Pages</a>
                                <ul class="sub-menu">
                                    <li><a href="account.html">Account</a></li>
                                    <li><a href="account-login.html">Login</a></li>
                                    <li><a href="account-register.html">Register</a></li>
                                    <li><a href="page-not-found.html">Page Not Found</a></li>
                                </ul>
                            </li>
                            <li><a href="#">Shop</a>
                                <ul class="sub-menu">
                                    <li><a href="#">Shop Layout</a>
                                        <ul class="sub-menu">
                                            <li><a href="shop-three-columns.html">Shop 3 Column</a></li>
                                            <li><a href="shop-four-columns.html">Shop 4 Column</a></li>
                                            <li><a href="shop.html">Shop Left Sidebar</a></li>
                                            <li><a href="shop-right-sidebar.html">Shop Right Sidebar</a></li>
                                        </ul>
                                    </li>
                                    <li><a href="#">Single Product</a>
                                        <ul class="sub-menu">
                                            <li><a href="single-normal-product.html">Single Product Normal</a></li>
                                            <li><a href="single-product.html">Single Product Variable</a></li>
                                            <li><a href="single-group-product.html">Single Product Group</a></li>
                                            <li><a href="single-affiliate-product.html">Single Product Affiliate</a></li>
                                        </ul>
                                    </li>
                                    <li><a href="#">Others Pages</a>
                                        <ul class="sub-menu">
                                            <li><a href="shop-cart.html">Shopping Cart</a></li>
                                            <li><a href="shop-checkout.html">Checkout</a></li>
                                            <li><a href="shop-wishlist.html">Wishlist</a></li>
                                            <li><a href="shop-compare.html">Compare</a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </li>
                            <li><a href="#">Blog</a>
                                <ul class="sub-menu">
                                    <li><a href="#">Blog Layout</a>
                                        <ul class="sub-menu">
                                            <li><a href="blog.html">Blog Grid</a></li>
                                            <li><a href="blog-left-sidebar.html">Blog Left Sidebar</a></li>
                                            <li><a href="blog-right-sidebar.html">Blog Right Sidebar</a></li>
                                        </ul>
                                    </li>
                                    <li><a href="#">Single Blog</a>
                                        <ul class="sub-menu">
                                            <li><a href="blog-details-no-sidebar.html">Blog Details</a></li>
                                            <li><a href="blog-details-left-sidebar.html">Blog Details Left Sidebar</a></li>
                                            <li><a href="blog-details.html">Blog Details Right Sidebar</a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </li>
                            <li><a href="contact.html">Contact</a></li>
                        </ul>
                    </div>
                    <!-- Mobile Menu End -->
                </div>
            </div>
            <!--== End Side Menu ==-->
        </div>

        <!--=======================Javascript============================-->

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
    <script>
        $(document).ready(function () {
            // Add event listener to the star rating
            $('.review-rating li').click(function () {
                var selectedStar = $(this).data('star');

                // Update the selected star styling
                $(this).prevAll('li').removeClass('fa-star-o').addClass('fa-star');
                $(this).removeClass('fa-star-o').addClass('fa-star');
                $(this).nextAll('li').removeClass('fa-star').addClass('fa-star-o');

                // Set the selected star value to the hidden input field
                $('#feedbackRating').val(selectedStar);
            });

            // Add event listener to the feedback form submission
            $('#feedbackForm').submit(function (event) {
                event.preventDefault();

                // Retrieve form values
                var rating = $('#feedbackRating').val();
                var title = $('#feedbackTitle').val();
                var content = $('#feedbackContent').val();
                var productId = $('#productId').val();

                // Disable the submit button
                $('#addFeedbackButton').prop('disabled', true);

                // AJAX request
                $.ajax({
                    url: 'add-product-feedback', // Update with the actual server endpoint
                    type: 'POST',
                    data: {
                        rating: rating,
                        title: title,
                        content: content,
                        productId: productId // Add the productId parameter
                    },
                    success: function (response) {
                        // Reset form values
                        $('.review-rating li').removeClass('fa-star').addClass('fa-star-o');
                        $('#feedbackRating').val('0');
                        $('#feedbackTitle').val('');
                        $('#feedbackContent').val('');

                        // Enable the submit button
                        $('#addFeedbackButton').prop('disabled', false);

                        // Show success message
                        Toastify({
                            text: "Feedback submitted successfully!",
                            duration: 2000, // Duration in milliseconds
                            gravity: "top", // Position: top, topLeft, topCenter, topRight, center, centerLeft, centerRight, bottom, bottomLeft, bottomCenter, bottomRight
                            position: "left", // Position: left, right, center
                            close: false, // Show a close button
                            style: {
                                background: "green"
                            }
                        }).showToast();
                        setTimeout(function () {
                            var urlParams = new URLSearchParams(window.location.search);
                            urlParams.set('page', 1);
                            window.location.href = window.location.pathname + '?' + urlParams.toString();
                        }, 2000);
                    },
                    error: function (xhr, status, error) {
                        // Enable the submit button
                        $('#addFeedbackButton').prop('disabled', false);

                        if (xhr.status === 401) {
                            // User must log in
                            Toastify({
                                text: "You must log in first!",
                                duration: 2000,
                                gravity: "top",
                                position: "left",
                                close: false,
                                style: {
                                    background: "red"
                                }
                            }).showToast();
                        } else {
                            // Show error message
                            Toastify({
                                text: "Error submitting feedback",
                                duration: 2000,
                                gravity: "top",
                                position: "left",
                                close: false,
                                style: {
                                    background: "red"
                                }
                            }).showToast();
                        }
                    }
                });
            });
        });
        $(document).ready(function () {
            // Check if the page contains the "reviews" parameter in the URL
            var urlParams = new URLSearchParams(window.location.search);
            var pageParam = urlParams.get('page');
            // If the "reviews" parameter is present or the page is 1, activate the "Reviews" tab
            if (pageParam && pageParam >= 1) {
                $('#reviews-tab').tab('show');
            }

            // Add event listener to the pagination links
            $('.pagination-items .page-link').click(function () {
                var targetTab = $(this).attr('href');
                // If the target tab is the "Reviews" tab, activate it
                if (targetTab === '#reviews') {
                    $('#reviews-tab').tab('show');
                }
            });
        });




    </script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/toastify-js"></script>
</html>