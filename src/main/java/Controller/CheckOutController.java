package Controller;

import DAO.DAO;
import Model.Order;
import Model.User;
import Model.cart;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "CheckOutController", value = "/checkout")
public class CheckOutController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //b1: get data from da
        try {
            PrintWriter out = response.getWriter();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            ArrayList<cart> cart_list = (ArrayList<cart>) request.getSession().getAttribute("cart-list");

            User username = (User) request.getSession().getAttribute("username");




            if (cart_list != null && username != null) {
                for (cart c : cart_list) {

                    System.out.println(c);
                    Order order = new Order();
                    order.setId(c.getId());
                    order.setUid(username.getId());
                    order.setPrice(c.getPrice());
                    order.setQunatity(c.getQuantity());
                    order.setDate(formatter.format(date));

                    DAO orderDao = new DAO();
                    boolean result = orderDao.insertOrder(order);
                    if (!result) break;


                }

                cart_list.clear();

                response.sendRedirect("order.jsp");
            } else {
                if (username == null) {
                    response.sendRedirect("Login.jsp");
                } else {
                    response.sendRedirect("ShopCart.jsp");
                }
            }
        } catch (Exception e) {
            // Handle other exceptions
            e.printStackTrace();
        }
    }

    //404 -> url
    //500 -> jsp properties


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}