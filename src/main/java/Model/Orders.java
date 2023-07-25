package Model;

import DAL.DBContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Orders {
    private int order_id;
    private String productName;

    private String userName;
    private int userId;
    private String state;
    private int staff_id;
    private double total;
    private Date orderDate;
    private int product_id;
    private int quantity;

    // Constructors, getters, and setters go here

    Connection cnn;
    Statement stm;
    PreparedStatement pstm;
    ResultSet rs;

    public Orders(int orderId, int userId, String state, int staffId, double total, Date orderDate, int productId, int quantity) {
        this.order_id = orderId;
        this.userId = userId;
        this.state = state;
        this.staff_id = staffId;
        this.total = total;
        this.orderDate = orderDate;
        this.product_id = productId;
        this.quantity = quantity;
        connect();
    }


    public Orders() {
        connect();
    }

    // Getter và Setter cho thuộc tính "order_id"
    public int getOrder_id() {
        return order_id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setUserName(String UserName) {
        this.userName = UserName;
    }

    public String getUserName() {
        return userName;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    // Getter và Setter cho thuộc tính "userId"
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    // Getter và Setter cho thuộc tính "state"
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    // Getter và Setter cho thuộc tính "staff_id"
    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    // Getter và Setter cho thuộc tính "total"
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    // Getter và Setter cho thuộc tính "orderDate"
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    // Getter và Setter cho thuộc tính "product_id"
    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    // Getter và Setter cho thuộc tính "quantity"
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    private void connect() {
        try {
            cnn = (new DBContext()).connection;
            if (cnn != null) {
                System.out.println("Connect success");
            }
        } catch (Exception e) {
            System.out.println("connect: " + e.getMessage());
        }
    }

    public List<Orders> getListOrders() {
        List<Orders> orderList = new ArrayList<>();

        try {
            String strSelect = "SELECT o.*, p.name FROM orders o JOIN products p ON o.product_id = p.product_id";
            Statement stm = cnn.createStatement();
            ResultSet rs = stm.executeQuery(strSelect);

            while (rs.next()) {
                int orderId = rs.getInt("order_id");
                int userId = rs.getInt("userid");
                String state = rs.getString("state");
                int staffId = rs.getInt("staff_id");
                double total = rs.getDouble("total_cost");
                Date orderDate = rs.getDate("date_placed");
                int productId = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");
                String productName = rs.getString("name");

                Orders order = new Orders(orderId, userId, state, staffId, total, orderDate, productId, quantity);
                order.setProductName(productName); // Assuming you have a setter method for the product name in the Orders class
                orderList.add(order);
            }

            rs.close();
            stm.close();
        } catch (Exception e) {
            System.out.println("getListOrders: " + e.getMessage());
        }

        return orderList;
    }


    public List<Orders> searchOrdersByState(String state) {
        List<Orders> orderList = new ArrayList<>();

        try {
            String strSelect = "SELECT * FROM orders WHERE state = ?";
            PreparedStatement pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, state);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                int orderId = rs.getInt("order_id");
                int userId = rs.getInt("userid");
                String orderState = rs.getString("state");
                int staffId = rs.getInt("staff_id");
                double total = rs.getDouble("total");
                Date orderDate = rs.getDate("orderDate");
                int productId = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");

                Orders order = new Orders(orderId, userId, orderState, staffId, total, orderDate, productId, quantity);
                orderList.add(order);
            }

            rs.close();
            pstm.close();
        } catch (Exception e) {
            System.out.println("searchOrdersByState: " + e.getMessage());
        }

        return orderList;
    }

    public void deleteOrder(int orderId) {
        try {
            String sql = "DELETE FROM orders WHERE order_id = ?";
            PreparedStatement pstm = cnn.prepareStatement(sql);
            pstm.setInt(1, orderId);

            int rowsAffected = pstm.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Order deleted successfully.");
            } else {
                System.out.println("Failed to delete the order.");
            }

            pstm.close();
        } catch (Exception e) {
            System.out.println("deleteOrder: " + e.getMessage());
        }
    }

    public void editOrder(int orderId, int userId, String state, int staffId, double total, Date orderDate, int productId, int quantity) {
        try {
            String sql = "UPDATE orders SET user_id = ?, state = ?, staff_id = ?, total_cost = ?, date_placed = ?, product_id = ?, quantity = ? WHERE order_id = ?";
            PreparedStatement pstm = cnn.prepareStatement(sql);
            pstm.setInt(1, userId);
            pstm.setString(2, state);
            pstm.setInt(3, staffId);
            pstm.setDouble(4, total);
            pstm.setDate(5, new java.sql.Date(orderDate.getTime()));
            pstm.setInt(6, productId);
            pstm.setInt(7, quantity);
            pstm.setInt(8, orderId);

            int rowsAffected = pstm.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Order with ID " + orderId + " updated successfully.");
            } else {
                System.out.println("Failed to update the order with ID " + orderId + ".");
            }

            pstm.close();
        } catch (Exception e) {
            System.out.println("editOrder: " + e.getMessage());
        }
    }



    public List<Orders> searchOrdersByDate(Date orderDate) {
        List<Orders> orderList = new ArrayList<>();

        try {
            String strSelect = "SELECT o.*, p.name, u.username " +
                    "FROM orders o " +
                    "JOIN Products p ON o.product_id = p.product_id " +
                    "JOIN Users u ON o.userid = u.userid " +
                    "WHERE o.orderDate = ?";

            PreparedStatement pstm = cnn.prepareStatement(strSelect);
            pstm.setDate(1, new java.sql.Date(orderDate.getTime()));
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                int orderId = rs.getInt("o.order_id");
                int userId = rs.getInt("o.userid");
                String state = rs.getString("o.state");
                int staffId = rs.getInt("o.staff_id");
                double total = rs.getDouble("o.total");
                Date date = rs.getDate("o.orderDate");
                int productId = rs.getInt("o.product_id");
                int quantity = rs.getInt("o.quantity");
                String productName = rs.getString("p.name");
                String username = rs.getString("u.username");

                Orders order = new Orders(orderId, userId, state, staffId, total, date, productId, quantity);
                order.setProductName(productName);
                order.setUserName(username);
                orderList.add(order);
            }

            rs.close();
            pstm.close();
        } catch (Exception e) {
            System.out.println("searchOrdersByDate: " + e.getMessage());
        }

        return orderList;
    }

    public int getNumberPage() {
        int total = 0;
        try {
            String query = "SELECT COUNT(*) FROM orders";
            pstm = cnn.prepareStatement(query);
            rs = pstm.executeQuery();
            while (rs.next()) {
                total = rs.getInt(1);
                int countPage = 0;
                countPage = total / 2;
                if (total % 2 != 0) {
                    countPage++;
                }
                return countPage;
            }
        } catch (Exception e) {
            System.out.println("getNumberPage: " + e.getMessage());
        }
        return 0;
    }

    public List<Orders> getPaging(int index) {
        List<Orders> list = new ArrayList<>();

        try {
            int offset = (index - 1) * 2;  // Assuming 2 orders per page

            String query = "SELECT o.*, p.name, u.username " +
                    "FROM orders o " +
                    "JOIN Products p ON o.product_id = p.product_id " +
                    "JOIN Users u ON o.userid = u.userid " +
                    "ORDER BY u.username " +  // Changed ORDER BY to u.username
                    "LIMIT 2 OFFSET ?";
            PreparedStatement pstm = cnn.prepareStatement(query);
            pstm.setInt(1, offset);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                int orderId = rs.getInt("o.order_id");
                int userId = rs.getInt("o.userid");
                String state = rs.getString("o.state");
                int staffId = rs.getInt("o.staff_id");
                double total = rs.getDouble("o.total");
                Date orderDate = rs.getDate("o.orderDate");
                int productId = rs.getInt("o.product_id");
                int quantity = rs.getInt("o.quantity");
                String productName = rs.getString("p.name");
                String username = rs.getString("u.username");

                Orders order = new Orders(orderId, userId, state, staffId, total, orderDate, productId, quantity);
                order.setProductName(productName);
                order.setUserName(username);
                list.add(order);
            }

            rs.close();
            pstm.close();
        } catch (Exception e) {
            System.out.println("getPaging: " + e.getMessage());
        }

        return list;
    }


}