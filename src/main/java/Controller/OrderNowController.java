package Controller;

import DAO.DAO;
import Model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "OrderNowController", value = "/ordernow")
public class OrderNowController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //b1: get data from da
        System.out.println("go add orrdr " );
        try (PrintWriter out = response.getWriter()) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();

            User username = (User) request.getSession().getAttribute("username");

            if (username != null) {
                System.out.println( username.getId() );
                String productId = request.getParameter("id");
                double price = Double.parseDouble(request.getParameter("price"));
                System.out.println(price +"nnnnn");
                int productQuantity = Integer.parseInt(request.getParameter("quantity"));
                if (productQuantity <= 0) {
                    productQuantity = 1;
                }
                Order orderModel = new Order();
                orderModel.setId(Integer.parseInt(productId));
                orderModel.setUid(username.getId());
                orderModel.setPrice(price);
                orderModel.setQunatity(productQuantity);
                orderModel.setDate(formatter.format(date));

                DAO orderDao = new DAO();
                boolean result = orderDao.insertOrder(orderModel);
                if (result) {
                    ArrayList<cart> cart_list = (ArrayList<cart>) request.getSession().getAttribute("cart-list");
                    if (cart_list != null) {
                        for (cart c : cart_list) {
                            System.out.println(c);
                            if (c.getId() == Integer.parseInt(productId)) {
                                cart_list.remove(cart_list.indexOf(c));
                                break;
                            }
                        }
                    }

                    response.sendRedirect("order.jsp");
                } else {
                    out.println("order failed");
                }
            } else {
                response.sendRedirect("Login.jsp");
            }

        } catch (Exception e) {
            // Xử lý ClassNotFoundException ở đây
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