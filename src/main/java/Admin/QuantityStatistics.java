package Admin;

import Model.Categories;
import Model.Payments;
import Model.ProductFeedback;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "staticscontroller", value = "/statics")
public class QuantityStatistics extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Categories c = new Categories ();
        int totalcatergory=c.getTotalCategories ();


        ProductFeedback p1 = new ProductFeedback ();
        int totalproductfeedback =p1.countProductFeedbacks ();

        Payments p = new Payments();
        int total = p.calculateTotalAmount();





        req.setAttribute ( "totalproductfeedback",totalproductfeedback );
        req.setAttribute("totalcatergory", totalcatergory);
        req.setAttribute("total", total);

        req.getRequestDispatcher("Admin.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve the list of orders

        processRequest (req,resp);

    }

}
