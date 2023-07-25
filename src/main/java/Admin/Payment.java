package Admin;


import Model.Payments;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

@WebServlet(name = "Ordercontroller", value = "/payments")
public class Payment extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {



        Payments p = new Payments();
        int total = p.calculateTotalAmount();

        int total_year = p.calculateTotalAmount (2023);
        System.out.println ("totalamoutyear:"+total_year);

        String year = req.getParameter("year");
        Map<Integer, Integer> monthlyOrders = p.calculateTotalAmountByYear(2023);
        Map<String, Integer> dailyOrders = p.calculateTotalByDay(2023, 12); // Thay đổi ngày và tháng tùy theo nhu cầu

        for (Map.Entry<Integer, Integer> entry : monthlyOrders.entrySet()) {
            int month = entry.getKey();
            int totalOrders = entry.getValue();
            System.out.println("Month " + month + ": " + totalOrders + " orders");
        }

        for (Map.Entry<String, Integer> entry : dailyOrders.entrySet()) {
            String day = entry.getKey();
            int totalOrders = entry.getValue();
            System.out.println("Day " + day + ": " + totalOrders + " orders");
        }






        req.setAttribute("total_year", total_year);
        req.setAttribute("total", total);
        req.setAttribute("monthlyOrders", monthlyOrders);
        req.setAttribute("dailyOrders", dailyOrders);

        req.getRequestDispatcher("AdminStatics.jsp").forward(req, resp);

        //
    }





    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve the list of orders

        processRequest (req,resp);

    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve the list of orders
        Payments p = new Payments();
        int total = p.calculateTotalAmount();

        Map<String, Integer> dailyOrders = p.calculateTotalByDay(2023, 7); // Thay đổi ngày và tháng tùy theo nhu cầu



        for (Map.Entry<String, Integer> entry : dailyOrders.entrySet()) {
            String day = entry.getKey();
            int totalOrders = entry.getValue();
            System.out.println("Day " + day + ": " + totalOrders + " orders");
        }



        req.setAttribute("dailyOrders", dailyOrders); // Thêm dữ liệu theo ngày vào thuộc tính req.setAttribute()

        req.getRequestDispatcher("AdminStatics.jsp").forward(req, resp);

    }



}