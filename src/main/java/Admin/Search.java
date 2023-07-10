package Admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import Model.Orders;


@WebServlet(name = "searchcontroller", value = "/search1")
public class Search extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, ParseException {
    /*    String orderDate = req.getParameter("orderDate");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date orderDate1 = null;
        try {
            orderDate1 = dateFormat.parse(orderDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Orders o = new Orders();
        List<Orders> orders = o.searchOrdersByDate(orderDate1);

        if (!orders.isEmpty()) {
            int id = orders.get(0).getOrder_id ();

            req.setAttribute("id", id);
        }

        req.setAttribute("orders", orders);

        req.getRequestDispatcher("AdminOrder.jsp").forward(req, resp);

     */
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
        } catch (ParseException e) {
            throw new ServletException(e);
        }
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchName = req.getParameter("name");

        User user = new User();
        List<User> userList = user.searchUsersByName(searchName);

        req.setAttribute("userList", userList);
        req.getRequestDispatcher("AdminCustomer.jsp").forward(req, resp);
    }
}