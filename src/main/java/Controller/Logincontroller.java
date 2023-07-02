package Controller;


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
        //nhan thong tin
        String username = req.getParameter ( "username" );
        String password = req.getParameter ( "password" );

        String err = "";
        //su ly thong tin
        User u = new User ( username, password );

        User user = u.userLogin(username, password);



        if (user!=null) {
            req.getSession().setAttribute("username", user);


            resp.sendRedirect("home");

        } else {
            err = "Account exit";
            req.setAttribute ( "err", err );
            req.getRequestDispatcher ( "Login.jsp" ).forward ( req, resp );
        }

    }
}