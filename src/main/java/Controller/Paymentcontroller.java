package Controller;

import DAO.DAO;
import Model.Order;
import Model.User;
import Model.cart;
import Model.Payment;
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
import java.util.List;

@WebServlet(name = "Paymentcontroller", value = "/payment")
public class Paymentcontroller extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //b1: get data from da
        try(PrintWriter out = response.getWriter()){
            User username = (User) request.getSession().getAttribute("username");
            List<Order> orders = null;


                DAO orderDao  = new DAO();
                orders = orderDao.userOrders(username.getId());
                request.setAttribute("order-list", orders);

            double price = Double.parseDouble(request.getParameter("price"));
            if(orders != null && username!=null) {
                for(Order c:orders) {
                    System.out.println(c);
                    Payment p= new Payment();
                    p.setOrderId(c.getOrderId());
                    p.setAmount(price);
                    DAO payment = new DAO();
                    boolean result = payment.insertPayment(p);
                    if(!result) break;
                }
                orders.clear();
                response.sendRedirect("paymentsuccess.jsp");
            }else {
                if (username == null) {
                    response.sendRedirect("Login.jsp");
                } else {
                    response.sendRedirect("ShopCart.jsp");
                }
            }
        } catch (Exception e) {
            // Xử lý ClassNotFoundException ở đây
            e.printStackTrace();
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}