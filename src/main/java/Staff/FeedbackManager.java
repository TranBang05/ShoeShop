/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Staff;

import DAO.DAO;
import Model.Feedback;
import Model.Staff;

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
public class FeedbackManager extends HttpServlet {

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
            out.println("<title>Servlet FeedbackManager</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FeedbackManager at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }


    @Override

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao = new DAO();
        HttpSession session = request.getSession();
        Staff staff = (Staff) session.getAttribute("staff");
        String pageParam = request.getParameter("page");
        String searchParam = request.getParameter("search");
        int page = 1; // Default to the first page
        int pageSize = 6; // Set the desired page size
//        String categoryParam = request.getParameter("category");
        if (pageParam != null && !pageParam.isEmpty()) {
            // Parse the page parameter to an integer
            page = Integer.parseInt(pageParam);
        }
        List<Feedback> feedbacks = dao.getAllFeedbacksForManage(searchParam, staff.getId());
        feedbacks.forEach(System.out::println);
        List<Feedback> usersList = dao.PagingFeedback(feedbacks, page, pageSize);
        usersList.forEach(System.out::println);
        request.setAttribute("searchParam", searchParam);

        request.setAttribute("listP", usersList);
        request.setAttribute("totalPages", feedbacks.size() % pageSize == 0 ? (feedbacks.size() / pageSize) : (feedbacks.size() / pageSize + 1));
        request.setAttribute("currentPage", page);
        request.getRequestDispatcher("FeedbackManager.jsp").forward(request, response);
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
        processRequest(request, response);
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
