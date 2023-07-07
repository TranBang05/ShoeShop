package Controller;

import Model.Staff;
import Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "Logincontroller", value = "/login")
public class Logincontroller extends HttpServlet {

    @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String username = req.getParameter("username");
    String password = req.getParameter("password");

    String err = "";
    User u = new User(username, password);
    Staff s = new Staff(username, password);

    User user = u.userLogin(username, password);
    Staff staff = s.staffLogin(username, password);

    if (user != null) {
        req.getSession().setAttribute("username", user);
        resp.sendRedirect("home");
    } else if (staff != null) {
        req.getSession().setAttribute("staff", staff);
        resp.sendRedirect("/staff/DashBoard.jsp");
    } else {
        err = "Invalid credentials";
        req.setAttribute("err", err);
        req.getRequestDispatcher("Login.jsp").forward(req, resp);
    }
}

}
