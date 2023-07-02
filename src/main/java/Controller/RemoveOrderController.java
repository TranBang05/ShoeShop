package Controller;

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

import DAO.DAO;

@WebServlet(name = "RemoveOrderController", value = "/remove")
public class RemoveOrderController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try(PrintWriter out = response.getWriter()) {
            String id = request.getParameter("id");
            if(id != null) {
                DAO orderDao = new DAO();
                orderDao.cancelOrder(Integer.parseInt(id));
            }
            response.sendRedirect("order.jsp");
        }  catch (Exception e) {
            // Xử lý ClassNotFoundException ở đây
            e.printStackTrace();
        }
        //b1: get data from da

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