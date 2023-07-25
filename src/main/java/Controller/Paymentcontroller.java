package Controller;

import DAO.DAO;
import Model.Order;
import Model.Payments;
import Model.User;

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
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();

            User username = (User) request.getSession().getAttribute("username");
            List<Order> orders = null;


                DAO orderDao  = new DAO();
                orders = orderDao.userOrders(username.getId());
                request.setAttribute("order-list", orders);

            double price = Double.parseDouble(request.getParameter("price"));
            String phone= request.getParameter("phone");
            String address=request.getParameter("address");
            String note=request.getParameter("note");
            System.out.println(phone);
            if(orders != null && username!=null) {
                for(Order c:orders) {
                    System.out.println(c);
                    Payments p= new Payments();
                    p.setOrderId(c.getOrderId());
                    p.setAmount(price);
                    p.setPhone(phone);
                    p.setAddress(address);
                    p.setNote(note);
                    p.setDate(formatter.format(date));
                    DAO payment = new DAO();
                    boolean result = payment.insertPayment(p);
                    if(!result) break;
                }
                orders.clear();
                request.getSession().removeAttribute("order-list");
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