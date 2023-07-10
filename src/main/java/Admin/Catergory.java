package Admin;







import Model.Categories;
import Model.Products;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.util.List;
import Model.Orders;

@WebServlet(name = "Catergorycontrollers", value = "/catergorys")
public class Catergory extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Categories c = new Categories();
        List<Products> categories = c.getTop3OrderedProducts();
        req.setAttribute("categories", categories);

        // Print the categories before forwarding
        System.out.println("Top 3 Ordered Products:");
        for (Products product : categories) {
            System.out.println("Product ID: " + product.getId());
            System.out.println("Name: " + product.getName());
            System.out.println("Description: " + product.getDescription());
            System.out.println("Price: " + product.getPrice());
            System.out.println("Image: " + product.getImage());
            System.out.println("Category ID: " + product.getCategory().getCategory_id());
            System.out.println("Category Name: " + product.getCategory().getName());
            System.out.println("----------------------------------------");
        }

        req.getRequestDispatcher("Admin.jsp").forward(req, resp);

    }





    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve the list of orders

        processRequest (req,resp);

    }


}

