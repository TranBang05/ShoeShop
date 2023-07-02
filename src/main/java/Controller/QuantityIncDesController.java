package Controller;

import DAO.DAO;
import Model.Category;
import Model.Products;
import Model.cart;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "QuantityIncDesController", value = "/quantityincdes")
public class QuantityIncDesController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //b1: get data from da
        try (PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");
            int id = Integer.parseInt(request.getParameter("id"));
            ArrayList<cart> cart_list = (ArrayList<cart>) request.getSession().getAttribute("cart-list");

            if (action != null && id >= 1) {
                if (action.equals("inc")) {
                    for (cart c : cart_list) {
                        if (c.getId() == id) {
                            int quantity = c.getQuantity();
                            quantity++;
                            c.setQuantity(quantity);
                            response.sendRedirect("ShopCart.jsp");
                        }
                    }
                }

                if (action.equals("dec")) {
                    for (cart c : cart_list) {
                        if (c.getId() == id && c.getQuantity() > 1) {
                            int quantity = c.getQuantity();
                            quantity--;
                            c.setQuantity(quantity);
                            break;
                        }
                    }
                    response.sendRedirect("ShopCart.jsp");
                }
            } else {
                response.sendRedirect("ShopCart.jsp");
            }
        }
        //404 -> url
        //500 -> jsp properties
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