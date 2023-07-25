package Model;

import DAL.DBContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Categories {
    private int category_id;
    private String name;

    public Categories() {
        connect();
    }

    public Categories(String name) {
        this.name = name;
        connect();
    }

    public Categories(int category_id, String name) {
        this.category_id = category_id;
        this.name = name;
        connect();
    }

    public int getCategory_id() {
        return category_id;
    }

    public String getName() {
        return name;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    Connection cnn;
    Statement stm;
    PreparedStatement pstm;
    ResultSet rs;

    private void connect() {
        try {
            cnn = (new DBContext()).connection;
            if (cnn != null) {
                System.out.println("Connect success");
            }
        } catch (Exception e) {
            System.out.println("Connection error: " + e.getMessage());
        }
    }

    public List<Products> getTop3OrderedProducts() {
        List<Products> productsList = new ArrayList<>();

        try {
            String query = "SELECT p.product_id, p.name, p.description, p.price, p.image, c.category_id, c.name AS category_name, COUNT(oi.item_id) AS order_count " +
                    "FROM Products p " +
                    "JOIN Categories c ON p.category_id = c.category_id " +
                    "JOIN Order_Items oi ON p.product_id = oi.product_id " +
                    "GROUP BY p.product_id, p.name, p.description, p.price, p.image, c.category_id, c.name " +
                    "ORDER BY order_count DESC " +
                    "LIMIT 3"; // Retrieve only the top 3 products

            pstm = cnn.prepareStatement(query);
            rs = pstm.executeQuery();

            while (rs.next()) {
                int product_id = rs.getInt("product_id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                String image = rs.getString("image");
                int category_id = rs.getInt("category_id");
                String category_name = rs.getString("category_name");

                Categories category = new Categories(category_id, category_name);
                Products product = new Products(product_id, name, description, price, image, category);
                productsList.add(product);
            }
        } catch (Exception e) {
            System.out.println("getTop3OrderedProducts: " + e.getMessage());
        }

        return productsList;
    }


    public List<Categories> getCategories() {
        List<Categories> categoriesList = new ArrayList<>();

        try {
            String query = "SELECT * FROM Categories";
            stm = cnn.createStatement();
            rs = stm.executeQuery(query);

            while (rs.next()) {
                int category_id = rs.getInt("category_id");
                String name = rs.getString("name");

                Categories category = new Categories(category_id, name);
                categoriesList.add(category);
            }
        } catch (Exception e) {
            System.out.println("getCategories: " + e.getMessage());
        }

        return categoriesList;
    }

    public int getTotalCategories() {
        List<Categories> categoriesList = getCategories();
        return categoriesList.size();
    }



    public static void main(String[] args) {
        Categories categories = new Categories();
        int totalCategories = categories.getTotalCategories();
        System.out.println("Total Categories: " + totalCategories);
    }
}
