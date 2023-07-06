package Controller;

import DAO.DAO;
import Model.Category;
import Model.ProductFeedback;
import Model.Products;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "Detailcontroller", value = "/detail")
public class Detailcontroller extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        System.out.println("Go to blog details");
        //b1: get data from da
        String id = request.getParameter("pid");
        String cateID = request.getParameter("cid");
        DAO dao = new DAO();

        int page = 1;
        int pageSize = 5;

        String pageParam = request.getParameter("page");
        String pageSizeParam = request.getParameter("pageSize");

        if (pageParam != null && !pageParam.isEmpty()) {
            page = Integer.parseInt(pageParam);
        }

        if (pageSizeParam != null && !pageSizeParam.isEmpty()) {
            pageSize = Integer.parseInt(pageSizeParam);
        }

        Products p = dao.getProductByID(id);
        List<Products> list = dao.getAllProduct();
        List<Category> listC = dao.getAllCategory();
        Products last = dao.getLast();
        int product_id = Integer.parseInt(id);
        int totalCount = dao.countProductFeedback(product_id);
//        System.out.println("count " + totalCount);
        int totalPages = (int) Math.ceil((double) totalCount / pageSize);
        List<ProductFeedback> productFeedbacks = dao.getAllProductFeedbackByProductID(product_id, page, pageSize);
//        System.out.println("PageTT " + totalPages);
//        productFeedbacks.forEach(System.out::println);

        // caculate star
        double avgStarRating = 0.0;
        if (totalCount > 0) {
            int sumStarRating = 0;
            for (ProductFeedback feedback : productFeedbacks) {
                sumStarRating += feedback.getStar();
            }
            avgStarRating = (double) sumStarRating / totalCount;
            request.setAttribute("avgStarRating", avgStarRating);
        }
        request.setAttribute("list", list);
        request.setAttribute("listC", listC);
        request.setAttribute("p", last);
        request.setAttribute("detail", p);
        request.setAttribute("product_id", product_id);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("productFeedback", productFeedbacks);
        request.getRequestDispatcher("Detail.jsp").forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
