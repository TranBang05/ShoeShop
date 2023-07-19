


<%@page contentType="text/html" pageEncoding="x-windows-949"%>
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
        rel="stylesheet">\
<style>
  .btn-add-blog {
    margin-right: 100px;
    display: inline-block;
    padding: 10px 20px;
    background-color: #4CAF50;
    color: white;
    border: none;
    border-radius: 4px;
    font-size: 16px;
    cursor: pointer;
  }

  .btn-add-blog:hover {
    background-color: #45a049;
  }
</style>
<body>
<!-- =============== Navigation ================ -->
<div class="container">
  <jsp:include page="header.jsp"></jsp:include>

  <!-- ========================= Main ==================== -->
  <div class="main">
    <div class="topbar">

      <form>
        <div class="form-container">
          <label>
            <input type="text" name="search" placeholder="Search here" value="${searchParam}"><!-- comment -->
            <input type="hidden" name="page" value="${currentPage}">

          </label>






        </div>


        <!-- Add other form fields and buttons as needed -->

      </form>


    </div>

    <!-- ======================= Cards ================== -->


    <!-- ================ Order Details List ================= -->
    <div class="details">
      <div class="recentOrders">
        <div class="cardHeader">
          <h2>Product Management</h2>

        </div>
        <% String status = request.getParameter("status"); %>

        <!-- Add the following code after the form -->
        <% if ("success".equals(status)) { %>
        <p class="status delivered">Update success</p>
        <% } else if ("failed".equals(status)) { %>
        <p class="status return">Update failed</p>
        <% }%>
        <% if ("addSuccess".equals(status)) { %>
        <p class="status delivered">Add success</p>
        <% } else if ("addFailed".equals(status)) { %>
        <p class="status return">Update failed</p>
        <% }%>
        <table>
          <thead>
          <tr>
            <td>Username</td>
            <td>Title</td>
            <td>Content</td>
            <td>Date</td>

          </tr>
          </thead>

          <tbody>
          <c:if test="${empty listP}">
            <tr>
              <td colspan="5" style="text-align: center; color: red"><b>No product found!</b></td>
            </tr>
          </c:if>
          <c:if test="${not empty listP}">
            <c:forEach items="${listP}" var="p">
              <tr>
                <td>${p.userid}</td>
                <td>$${p.title}</td>
                <td>$${p.content}</td>
                <td>$${p.date_posted}</td>



              </tr>
            </c:forEach>
          </c:if>

          </tbody>
        </table>
        <%--paging--%>
        <nav class="mt-3" aria-label="Page navigation example">
          <ul class="pagination">
            <!-- Disable the "Previous" link if on the first page -->
            <c:if test="${currentPage > 1}">
              <li class="page-item">
                <a class="page-link" href="?category=${param.category}&amp;search=${param.search}&amp;page=${currentPage - 1}">Previous</a>
              </li>
            </c:if>

            <!-- Display the page numbers as links -->
            <c:forEach var="pageNum" begin="1" end="${totalPages}">
              <li class="page-item <c:if test='${pageNum == currentPage}'>active</c:if>">
                <a class="page-link" href="?category=${param.category}&amp;search=${param.search}&amp;page=${pageNum}">${pageNum}</a>
              </li>
            </c:forEach>

            <!-- Disable the "Next" link if on the last page -->
            <c:if test="${currentPage < totalPages}">
              <li class="page-item">
                <a class="page-link" href="?category=${param.category}&amp;search=${param.search}&amp;page=${currentPage + 1}">Next</a>
              </li>
            </c:if>
          </ul>
        </nav>
      </div>


      <!-- ================= New Customers ================ -->


    </div>
  </div>
</div>

<!-- =========== Scripts =========  -->

<script>

  var modal = document.getElementById("editModel");


  var editModelButton = document.querySelector(".btn-add-blog");


  var closeSpan = document.getElementsByClassName("close")[0];


  editModelButton.onclick = function () {
    modal.style.display = "block";
  };


  closeSpan.onclick = function () {
    modal.style.display = "none";
  };

  // When the user clicks anywhere outside of the modal, close it
  window.onclick = function (event) {
    if (event.target == modal) {
      modal.style.display = "none";
    }
  };

  function confirmDelete(button) {
    Swal.fire({
      title: 'Confirmation',
      text: 'Are you sure you want to delete this product?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes',
      cancelButtonText: 'Cancel'
    }).then((result) => {
      if (result.isConfirmed) {
        deletePost(button);
      }
    });
  }

  function deletePost(button) {
    var postId = button.getAttribute("data-product-id");

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/staff/delete-product?pid=" + encodeURIComponent(postId), true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function () {
      if (xhr.readyState === 4) {
        if (xhr.status === 200) {
          // Product deleted successfully
          Swal.fire({
            title: 'Success',
            text: xhr.responseText,
            icon: 'success'
          }).then(function () {
            window.location.reload();
          });
        } else {
          // Failed to delete the product
          Swal.fire({
            title: 'Error',
            text: xhr.responseText,
            icon: 'error'
          });
        }
      }
    };
    xhr.send("pid=" + encodeURIComponent(postId));
  }


</script>
<script src="assets/js/main.js"></script>
<!-- ====== ionicons ======= -->
<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10.16.0/dist/sweetalert2.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@10.16.0/dist/sweetalert2.min.css">

</body>
</html>