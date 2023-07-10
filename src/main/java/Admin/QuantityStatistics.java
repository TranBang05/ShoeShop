package Admin;

import Model.Payments;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "staticscontroller", value = "/statics")
public class QuantityStatistics extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Payments p = new Payments();
        int total = p.calculateTotalAmount();
        req.setAttribute("total", total);
        req.getRequestDispatcher("Admin.jsp").forward(req, resp);
    }






    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve the list of orders

        processRequest (req,resp);

    }


}