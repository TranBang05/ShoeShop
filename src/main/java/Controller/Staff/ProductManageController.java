/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Staff;

import DAO.DAO;
import Model.Category;
import Model.Products;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ProductManageController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProductManageController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductManageController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao = new DAO();
        String pageParam = request.getParameter("page");
        String searchParam = request.getParameter("search");     
        int page = 1; // Default to the first page
        int pageSize = 6; // Set the desired page size
        String categoryParam = request.getParameter("category");
//        String categoryParam = request.getParameter("category");
        if (pageParam != null && !pageParam.isEmpty()) {
            // Parse the page parameter to an integer
            page = Integer.parseInt(pageParam);
        }
        List<Products> products = dao.getAllProductForManage(searchParam, categoryParam);
//        products.forEach(System.out::println);
        List<Products> productList = dao.Paging(products, page, pageSize);
        List<Category> category = dao.getAllCategory();
         request.setAttribute("searchParam", searchParam);
        request.setAttribute("listP", productList);
        request.setAttribute("totalPages", products.size() % pageSize == 0 ? (products.size() / pageSize) : (products.size() / pageSize + 1));
        request.setAttribute("currentPage", page);
        request.setAttribute("category", category);
        request.getRequestDispatcher("/staff/productManage.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
