/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.DAO;
import Model.Blog;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 *
 * @author ADMIN
 */
public class AddPostBlogController extends HttpServlet {

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
            out.println("<title>Servlet AddPostBlogController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddPostBlogController at " + request.getContextPath() + "</h1>");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Go to add post");
        User user = (User) request.getSession().getAttribute("username");
//        System.out.println(user.getId());
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String image = request.getParameter("image");

        //get current date-time 
        Timestamp datePosted = new Timestamp(System.currentTimeMillis());

        DAO dao = new DAO();
      
        // check if user  do not login --> cant post 
        if (user != null) {
            if (!title.isEmpty() && !content.isEmpty() && !image.isEmpty()) {
                Blog blog = new Blog(title, content, user.getId(), datePosted, image);
                dao.addPost(blog);
                String messageSuccess = "Post success";
                response.sendRedirect("blog?messageSuccess=" + messageSuccess);
            } else {
               String FaildMessage = "Post Faild";
                response.sendRedirect("blog?FaildMessage=" + FaildMessage);
            }
        } else {
            request.setAttribute("message", "You must login first!");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
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
