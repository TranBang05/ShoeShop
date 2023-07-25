/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.DAO;
import Model.Feedback;
import Model.User;
import jakarta.mail.Session;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class FeedbackToStaffController extends HttpServlet {

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
            out.println("<title>Servlet FeedbackToStaffController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FeedbackToStaffController at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        User acc = (User) session.getAttribute("username");
        System.out.println("GO TO FEEDFBACK");
        List<Feedback> feedbacks = dao.getAllFeedback();

        feedbacks.forEach(System.out::println);
        request.setAttribute("acc", acc);
        request.setAttribute("feedbacks", feedbacks);
        request.getRequestDispatcher("contact.jsp").forward(request, response);
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
        String action = request.getServletPath();

        switch (action) {
            case "/submit-feedback":
                submitFeedback(request, response);
                break;
            default:
                response.sendRedirect("home");
        }
    }

    private void submitFeedback(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form data
        HttpSession session = request.getSession();
        User acc = (User) session.getAttribute("username");
        String staffId = request.getParameter("staff");
        String email = request.getParameter("email");
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        // Create Feedback object
        Feedback feedback = new Feedback();
        feedback.setUserid(acc.getId()); // Set the user ID
        feedback.setStaff_id(Integer.parseInt(staffId)); // Set the staff ID
        System.out.println(staffId);
        feedback.setTitle(title);
        feedback.setContent(content);

        // Insert the feedback into the database
        DAO dao = new DAO();
        if (acc != null) {

            boolean isAdded = dao.insertFeedback(feedback);
            if (isAdded) {
                response.sendRedirect("success");
            } else {
                response.sendRedirect("error");

            }
        } else {
            response.sendRedirect("error");
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
