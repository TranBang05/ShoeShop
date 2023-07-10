package Admin;

import Model.User;
import OTPSendMail.SendMail;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import Model.Order;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "Userscontroller", value = "/customers")
public class Users extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User u = new User();
        List<User> userList = u.getListUser1();

        req.setAttribute("userList", userList);
        req.getRequestDispatcher("AdminCustomer.jsp").forward(req, resp);
        // resp.sendRedirect("AdminCustomer.jsp");

    }





    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve the list of orders

        processRequest (req,resp);

    }


}