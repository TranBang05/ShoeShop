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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import Model.Orders;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "PageOrder", value = "/pages")

public class Page extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get the order information from the request
        int index=0;
        String orderInfo = req.getParameter("orderInfo");


        String state = req.getParameter("state");
        //    System.out.println (state);
        String orderDate = req.getParameter("orderDate");

        Orders o = new Orders();
        List<Orders> orders;

        if (state != null && !state.isEmpty()) {

            // Search by staghte
            orders = o.searchOrdersByState(state);

        } else if (orderDate != null && !orderDate.isEmpty()) {
            // Search by date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date orderDate1;
            try {
                orderDate1 = dateFormat.parse(orderDate);
                System.out.println (orderDate1);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            orders = o.searchOrdersByDate(orderDate1);
        } else {
            index=o.getNumberPage ();
            req.setAttribute ( "count",index );

            if (req.getParameter("index") != null) {
                index = Integer.parseInt(req.getParameter("index"));
            } else {
                index = 1;
            }
            orders = o.getPaging(index);
        }



        req.setAttribute("listO", orders);


        req.getRequestDispatcher("AdminOrder.jsp").forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
}





