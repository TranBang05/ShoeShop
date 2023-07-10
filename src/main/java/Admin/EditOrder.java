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
import Model.Orders;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "EditOrder", value = "/editOrderServlet")
public class EditOrder extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String id = req.getParameter("Id");
        String state = req.getParameter("state");
        String totalStr = req.getParameter("total");
        double total = Double.parseDouble(totalStr);
        String orderDate = req.getParameter("orderDate");

        System.out.println (id);
        System.out.println (state);
        System.out.println (total);
        System.out.println (orderDate);
        Orders o = new Orders();
        //  o.editOrder ( id,state,total,orderDate );


    }





    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve the list of orders

        processRequest (req,resp);

    }


}

