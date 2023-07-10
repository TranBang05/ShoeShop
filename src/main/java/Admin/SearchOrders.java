package Admin;

import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "searchcontroller1", value = "/searcho")
public class SearchOrders extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, ParseException {
   /*     resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        String orderDateStr1 = req.getParameter("orderDate1");

        System.out.println (orderDateStr1);
        String orderDateStr2 = req.getParameter("orderDate2");
        System.out.println (orderDateStr2);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date orderDate1 = dateFormat.parse(orderDateStr1);
        Date orderDate2 = dateFormat.parse(orderDateStr2);

        OrderItems o = new OrderItems();
        List<OrderItems> orders = o.searchDateRange(orderDate1, orderDate2);
        req.setAttribute("list0", orders);

        if (orders != null && !orders.isEmpty()) {
            for (OrderItems order : orders) {
                // Display information for each OrderItems object
                out.println("Item ID: " + order.getItemId());
                out.println("Product ID: " + order.getProductId());
                out.println("Quantity: " + order.getQuantity());
                out.println("Price: " + order.getPrice());
                out.println("Order ID: " + order.getOrderId());
                out.println("Total: " + order.getTotal());
                out.println("<br>");
            }
        } else {
            out.println("No orders found.");
        }
        */

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
        } catch (ParseException e) {
            throw new ServletException(e);
        }
        req.getRequestDispatcher("AdminStatics.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}