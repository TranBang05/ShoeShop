<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Management</title>
    <!-- ======= Styles ====== -->
    <link rel="stylesheet" href="staff/assets/css/style.css">
    <link rel="stylesheet" href="staff/assets/css/productStyle.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"></script>

</head>       <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/toastify-js/src/toastify.min.css" />
<script src="https://cdn.jsdelivr.net/npm/toastify-js"></script>
<link
        href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,300;0,400;0,600;0,700;0,800;1,400;1,500&display=swap"
        rel="stylesheet">
<style>   .details select {
        width: 100%;
        padding: 8px;
        border: 1px solid #ccc;
        border-radius: 4px;
        margin-bottom: 10px;
    }

    .details select option {
        padding: 4px;
    }
    .details form {
        width: 800px;
        margin: 0 auto;
    }

    .details label {
        display: block;
        margin-bottom: 5px;
        font-weight: bold;
    }

    .details input[type="text"],
    .details textarea,
    .details input[type="number"] {
        width: 100%;
        padding: 15px;
        border: 1px solid #ccc;
        border-radius: 4px;
        margin-bottom: 10px;
    }

    .details input[type="button"] {
        background-color: gray;
        color: white;
        padding: 10px 16px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }
    .details input[type="submit"] {
        background-color: #4CAF50;
        color: white;
        padding: 10px 16px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }

    .details input[type="submit"]:hover {
        background-color: #45a049;
    }
    </style>
</head>
<body>

<div class="container">
    <jsp:include page="header.jsp"></jsp:include>


    <div class="main">

        <h1 style=" text-align: center">Product Details </h1>

        <div class="details">

            <form action="updateProduct" method="POST">
                <input type="hidden" name="id" value="${product.id}" />

                <label>Name:</label>
                <input type="text" name="name" value="${product.name}" /><br/>

                <label >Description:</label>
                <textarea name="description">${product.description}</textarea><br/>

                <label >Price:</label>
                <input type="number" name="price" value="${product.price}" step="0.01" /><br/>

                <label >Image:</label>
                <input type="text" name="image" value="${product.image}" /><br/>

                <label >Discount(%)</label>
                <input type="number" name="discount" value="${product.discount*100 }" /><br/>

                <!--<label for="title">Title:</label>-->
                <!--<input type="text" name="title" value="${product.title}" /><br/>-->

                <label>Category</label>
                <select name="cateId">
                    <c:forEach var="cat" items="${category}">
                        <option value="${cat.cid}" ${cat.cid == product.cateId ? 'selected' : ''}>${cat.cname}</option>
                    </c:forEach>
                </select><br/>
                <input type="submit" value="Update" />
                <input type="button" onclick="goBack()" value="Back" />

            </form>
        </div>
    </div>
</div>


</body>
<script>
    function goBack() {
        window.history.back();
    }
</script>
<script src="assets/js/main.js"></script>
<!-- ====== ionicons ======= -->
<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10.16.0/dist/sweetalert2.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@10.16.0/dist/sweetalert2.min.css">
</html>