package Controller;

import Model.cart;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "RemoveCartController", value = "/removecart")
public class RemoveCartController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //b1: get data from da
        try (PrintWriter out = response.getWriter()) {
            String bookId = request.getParameter("id");
            if (bookId != null) {
                ArrayList<cart> cart_list = (ArrayList<cart>) request.getSession().getAttribute("cart-list");
                if (cart_list != null) {
                    for (cart c : cart_list) {
                        if (c.getId() == Integer.parseInt(bookId)) {
                            cart_list.remove(cart_list.indexOf(c));
                            break;
                        }
                    }
                }
                response.sendRedirect("ShopCart.jsp");

            } else {
                response.sendRedirect("ShopCart.jsp");
            }

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