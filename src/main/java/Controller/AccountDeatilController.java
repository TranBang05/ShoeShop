
package Controller;

import DAO.DAO;
import Model.Order;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;


public class AccountDeatilController extends HttpServlet {

    String errMess = "";
    String successMess = "";

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
            out.println("<title>Servlet AccountDeatilController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AccountDeatilController at " + request.getContextPath() + "</h1>");
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
        System.out.println("GO TO ACCOUNT DETAILS");
        HttpSession session = request.getSession();
        User a = (User) session.getAttribute("username");

        DAO dao = new DAO();
        if (a != null) {
            User userDetail = dao.getUserByID(a.getId());
            List<Order> order = dao.getOrdersByUserID(a.getId());
            System.out.println(order);
            request.setAttribute("user", userDetail);
            request.setAttribute("order", order);

            request.getRequestDispatcher("account.jsp").forward(request, response);
        } else {
            response.sendRedirect("home");
        }

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
            case "/edit-address":
                updateAddress(request, response);
                break;
            case "/change-pass":
                changePassword(request, response);
                break;
            default:
                response.sendRedirect("home");
        }

    }

    private void updateAddress(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("username");

        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phone_number");

        DAO dao = new DAO();
        boolean isUpdate = dao.updateUserAddressAndPhone(user.getId(), address, phoneNumber);
        if (isUpdate) {
            successMess = "Address and phone number updated successfully.";
            session.setAttribute("successMess", successMess);
            session.removeAttribute("errMess");

            response.sendRedirect("account-detail");
        } else {
            errMess = "Failed to update address and phone number.";
            session.setAttribute("errMess", errMess);
            session.removeAttribute("successMess");
            response.sendRedirect("account-detail");
        }
    }

    private void changePassword(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("username");
        session.removeAttribute("errMess");
        session.removeAttribute("successMess");
        String currentPassword = request.getParameter("current-pwd");
        String newPassword = request.getParameter("new-pwd");
        String confirmNewPassword = request.getParameter("confirm-pwd");

        DAO dao = new DAO();
        if (dao.verifyUserPassword(user.getId(), currentPassword)) {
            if (newPassword.equals(confirmNewPassword)) {
                dao.updateUserPassword(user.getId(), newPassword);
                successMess = "Password changed successfully.";
                session.setAttribute("successMess", successMess);
                session.removeAttribute("errMess");

                response.sendRedirect("account-detail");
            } else {
                errMess = "New passwords don't match.";
                session.setAttribute("errMess", errMess);

                session.removeAttribute("successMess");
                response.sendRedirect("account-detail");
            }
        } else {
            errMess = "Invalid current password.";
            session.setAttribute("errMess", errMess);

            session.removeAttribute("successMess");
            response.sendRedirect("account-detail");
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
