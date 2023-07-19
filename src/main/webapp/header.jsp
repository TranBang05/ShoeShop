<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="Model.User" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="Model.Products" %>
<%@ page import="java.util.List" %>
<%@page isELIgnored="false" %>


<div class="navigation">
    <ul>
        <li>
            <a href="#">
                        <span class="icon">
                            <ion-icon name="map-outline"></ion-icon>
                        </span>
                <span class="title">Staff Dashboard</span>
            </a>
        </li>

        <li>
            <a href="DashBoard.jsp">
                        <span class="icon">
                            <ion-icon name="home-outline"></ion-icon>
                        </span>
                <span class="title">Home</span>
            </a>
        </li>

        <li>
            <a href="product-manage">
                        <span class="icon">
                            <ion-icon name="server-outline"></ion-icon>
                        </span>
                <span class="title">Products</span>
            </a>
        </li>

        <li>
            <a href="user-manage">
                        <span class="icon">
                            <ion-icon name="trending-down-outline"></ion-icon>
                        </span>
                <span class="title">User Manage</span>
            </a>
        </li>
        <li>
            <a href="user-feedback-manage">
                        <span class="icon">
                            <ion-icon name="trending-down-outline"></ion-icon>
                        </span>
                <span class="title">Feedback Manage</span>
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