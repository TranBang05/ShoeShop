package Controller;

import Model.Products;
import Model.cart;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "WishListController", value = "/wishlist")
public class WishListController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
//        	out.print("add to cart servlet");

            ArrayList<Products> wishList = new ArrayList<>();
            int id = Integer.parseInt(request.getParameter("id"));
            Products cm = new Products();
            cm.setId(id);

            HttpSession session = request.getSession();
            ArrayList<Products> Wish_list = (ArrayList<Products>) session.getAttribute("wishlist");
            if (Wish_list == null) {
                wishList.add(cm);
                session.setAttribute("wishlist", wishList);
                response.sendRedirect("shop");
            } else {
                wishList = Wish_list;

                boolean exist = false;
                for (Products p : Wish_list) {
                    if (p.getId() == id) {
                        exist = true;
                        out.println("<h3 style='color:crimson; text-align: center'>Item Already in Cart. <a href='Wishlist.jsp'>GO to Cart Page</a></h3>");
                    }
                }

                if (!exist) {
                    wishList.add(cm);
                    response.sendRedirect("shop");
                }
            }
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