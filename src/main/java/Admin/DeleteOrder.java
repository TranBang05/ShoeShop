package Admin;










import Model.Order;
import Model.Orders;
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

import jakarta.servlet.http.HttpSession;

@WebServlet(name = "DeleteOrder", value = "/deleteorders")
public class DeleteOrder extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int index=0;
        Orders o = new Orders();
        List<Orders> orders;

        String id = req.getParameter("id");
        int convertedId = Integer.parseInt(id);
        o.deleteOrder(convertedId);


        index=o.getNumberPage ();
        req.setAttribute ( "count",index );

        if (req.getParameter("index") != null) {
            index = Integer.parseInt(req.getParameter("index"));
        } else {
            index = 1;
        }
        orders = o.getPaging(index);


        req.setAttribute("listO", orders);
        req.getRequestDispatcher("AdminOrder.jsp").forward(req, resp);


    }





    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve the list of orders

        processRequest (req,resp);

    }


}


