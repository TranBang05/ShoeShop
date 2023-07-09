package Controller;

import DAO.DAO;
import Model.Category;
import Model.Products;
import Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.List;

@WebServlet(name = "getlistuser", value = "/getlistuser")

public class getlistuser extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //b1: get data from da
        DAO dao = new DAO();


        List<User> listU = dao.getAllUser();


        //b2: set data to jsp
        request.setAttribute("listU", listU);

        request.getRequestDispatcher("account.jsp").forward(request, response);

        //404 -> url
        //500 -> jsp properties
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}