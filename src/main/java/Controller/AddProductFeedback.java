/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.DAO;
import Model.ProductFeedback;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

/**
 *
 * @author ADMIN
 */
public class AddProductFeedback extends HttpServlet {

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
            out.println("<title>Servlet AddProductFeedback</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddProductFeedback at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the feedback content, product ID, and star rating from the request parameters
        User user = (User) request.getSession().getAttribute("username");
        String feedbackContent = request.getParameter("content");
        String feedbackTitle = request.getParameter("title");
        System.out.println("fb content =====" + feedbackContent);
        System.out.println("fb feedbackTitle     " + feedbackTitle);
        int starRating = Integer.parseInt(request.getParameter("rating"));
        System.out.println("fb starRating   " + starRating);
        int productId = Integer.parseInt(request.getParameter("productId"));
        System.out.println("fb productId    " + productId);
        Timestamp datePosted = new Timestamp(System.currentTimeMillis());
        // Create a new ProductFeedback object
        ProductFeedback feedback = new ProductFeedback(feedbackTitle, feedbackContent, user.getId(), datePosted, productId, starRating);

        // Save the feedback to the database using your DAO or data access layer
        DAO dao = new DAO();
        if (user != null) {
            if (!feedbackContent.isEmpty() && !feedbackTitle.isEmpty()) {
                dao.addProductFeedback(feedback);
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                // You can send a success response if needed
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        }
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
