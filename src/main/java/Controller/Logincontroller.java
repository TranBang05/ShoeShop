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

        boolean check = u.checkUser ();

        if (check) {
            //luu thong tin login vao session
            int role = u.getUserByRole ( username, password );

            switch (role) {
                case 1:
                    HttpSession session = req.getSession ();
                    session.setAttribute ( "username", u );
                    req.setAttribute ( "username", username );
                    resp.sendRedirect("home");
                    break;


                case 2:
                    req.getRequestDispatcher ( "Admin.jsp" ).forward ( req, resp );

                    break;

                default:
                    System.out.println ( "Lựa chọn không hợp lệ" );
                    break;
            }


        } else {
            err = "Account exit";
            req.setAttribute ( "err", err );
            req.getRequestDispatcher ( "Login.jsp" ).forward ( req, resp );
        }

    }
}