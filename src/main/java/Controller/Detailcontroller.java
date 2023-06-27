package Controller;

import DAO.DAO;
import Model.Category;
import Model.Products;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.List;

@WebServlet(name = "Detailcontroller", value = "/detail")
public class Detailcontroller extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //b1: get data from da
        String id=request.getParameter("pid");
        String cateID=request.getParameter("cid");
        DAO dao=new DAO();

        Products p=dao.getProductByID(id);

        List<Products> list = dao.getAllProduct();
        List<Category> listC = dao.getAllCategory();
        Products last = dao.getLast();
        request.setAttribute("list", list);
        request.setAttribute("listC", listC);
        request.setAttribute("p", last);
        request.setAttribute("detail", p);
        request.getRequestDispatcher("Detail.jsp").forward(request, response);

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