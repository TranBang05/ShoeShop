package Controller;

import DAO.DAO;
import Model.BlogComment;
import Model.Category;
import Model.Products;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.List;

@WebServlet(name = "shopcontroller", value = "/shop")
public class shopcontroller extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //b1: get data from da

        int page = 1;
        int pageSize = 6;

        String pageParam = request.getParameter("page");
        String pageSizeParam = request.getParameter("pageSize");

        if (pageParam != null && !pageParam.isEmpty()) {
            page = Integer.parseInt(pageParam);
        }

        if (pageSizeParam != null && !pageSizeParam.isEmpty()) {
            pageSize = Integer.parseInt(pageSizeParam);
        }

        DAO dao = new DAO();

//        List<Products> list = dao.getAllProduct();
        List<Category> listC = dao.getAllCategory();
        Products last = dao.getLast();

        int totalCount = dao.countProducts();
        System.out.println("count "+ totalCount);
        int totalPages = (int) Math.ceil((double) totalCount / pageSize);
        System.out.println("PageTT "+totalPages);
        List<Products> products = dao.getAllProducts(page, pageSize);




        //b2: set data to jsp
//        request.setAttribute("list", list);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("list", products);

        request.setAttribute("currentPage", page);
        request.setAttribute("listC", listC);
        request.setAttribute("p", last);
        request.getRequestDispatcher("Shop.jsp").forward(request, response);
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