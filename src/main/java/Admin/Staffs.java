package Admin;

import Model.Staff;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "staffcontroller", value = "/staff")
public class Staffs extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Staff u = new Staff ();
        List<Staff> userList = u.getAllStaffMembers();

        req.setAttribute("total", userList);
        req.getRequestDispatcher("AdminStaff.jsp").forward(req, resp);
        // resp.sendRedirect("AdminCustomer.jsp");

    }





    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve the list of orders

        processRequest (req,resp);

    }


}