package DAO;

import DAL.DBContext;
import Model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAO {



    Connection cnn;
    Statement stm;//thuc thi cau lenh sql
    PreparedStatement pstm;
    ResultSet rs;//luu tru du lieu vaxu li
    //PreparedStatement pstm; //thuc thi cau lenh spl
    private void connect() {
        try {
            cnn=(new DBContext()).connection;
            if(cnn!=null){
                System.out.println("Connect success");
            }
        } catch (Exception e) {
        }
    }

    public List<Products> getAllProduct() {
        List<Products> list = new ArrayList<>();
        String query = "select * from products";
        try {
            cnn=(new DBContext()).connection;
            pstm = cnn.prepareStatement(query);
            rs = pstm.executeQuery();

            while (rs.next()) {
                list.add(new Products(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(7)));
            }
        } catch (Exception e) {
        }
        return list;
    }





    public List<cart> getCartProducts(ArrayList<cart> cartList) {
        List<cart> book = new ArrayList<>();
        try {
            if (cartList.size() > 0) {
                for (cart item : cartList) {
                     String query = "select * from products where product_id=?";
                    cnn=(new DBContext()).connection;
                    pstm = cnn.prepareStatement(query);
                    pstm.setInt(1, item.getId());
                    rs = pstm.executeQuery();
                    while (rs.next()) {
                        cart row = new cart();
                        row.setId(rs.getInt("product_id"));
                        row.setName(rs.getString("name"));
                        row.setDescription(rs.getString("description"));
                        row.setImage(rs.getString("image"));
                        row.setPrice(rs.getDouble("price") * item.getQuantity());
                        row.setQuantity(item.getQuantity());
                        book.add(row);
                    }

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return book;
    }





    public List<Products> getWishListProducts(ArrayList<Products> WishList) {
        List<Products> p = new ArrayList<>();
        try {
            if (WishList.size() > 0) {
                for (Products item : WishList) {
                    String query = "select * from products where product_id=?";
                    cnn=(new DBContext()).connection;
                    pstm = cnn.prepareStatement(query);
                    pstm.setInt(1, item.getId());
                    rs = pstm.executeQuery();
                    while (rs.next()) {
                        cart row = new cart();
                        row.setId(rs.getInt("product_id"));
                        row.setName(rs.getString("name"));
                        row.setDescription(rs.getString("description"));
                        row.setImage(rs.getString("image"));
                        row.setPrice(rs.getDouble("price"));
                        p.add(row);
                    }

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return p;
    }


    public List<Products> SearchByName(String name) {
        List<Products> data = new ArrayList<>();
        String strSelect = "  select * from products where name like ?";
        try {
            // String strSelect = "select * from Student where name like '?'";

            cnn=(new DBContext()).connection;
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1,"%"+name+"%");
            rs = pstm.executeQuery();

            while (rs.next()) {
                data.add(new Products(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(7)));
            }

        } catch (Exception e) {
            System.out.println("getListSearch:" + e.getMessage());
        }
        return data;
    }





    public List<Products> getProductByCID(String cid) {
        List<Products> list = new ArrayList<>();
        String query = "select * from products where category_id=?";
        try {
            cnn=(new DBContext()).connection;
            pstm = cnn.prepareStatement(query);
            pstm.setString(1,cid);
            rs = pstm.executeQuery();

            while (rs.next()) {
                list.add(new Products(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(7)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Products getProductByID(String id) {

        String query = "select * from products where product_id=?";
        try {
            cnn=(new DBContext()).connection;
            pstm = cnn.prepareStatement(query);
            pstm.setString(1,id);
            rs = pstm.executeQuery();

            while (rs.next()) {
               return new Products(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(7));
            }
        } catch (Exception e) {
        }
        return null;
    }


    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>();
        String query = "select * from categories";
        try {
            cnn=(new DBContext()).connection;
            pstm = cnn.prepareStatement(query);

            rs = pstm.executeQuery();

            while (rs.next()) {
                list.add(new Category(rs.getInt(1),
                        rs.getString(2)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Products getLast() {
        String query = "select top 1 * from products\n"
                + "order by id desc";
        try {
            cnn=(new DBContext()).connection;
            pstm = cnn.prepareStatement(query);

            rs = pstm.executeQuery();
            while(rs.next()){
                return new Products(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(7));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public boolean insertOrder(Order model) {
        boolean result = false;
        String query = "insert into orders ( userid, product_id, price, quantity, date_placed) values(?,?,?,?,?)";
        try {
            cnn=(new DBContext()).connection;
            pstm = cnn.prepareStatement(query);

            pstm.setInt(1, model.getUid());
            pstm.setInt(2, model.getId());
            pstm.setDouble(3, model.getPrice());
            pstm.setInt(4, model.getQunatity());
            pstm.setString(5, model.getDate());

            pstm.executeUpdate();
            result = true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }







    public boolean insertPayment(Payment model) {
        boolean result = false;
        String query = "insert into payments (order_id, amount) values(?,?)";
        try {
            cnn=(new DBContext()).connection;
            pstm = cnn.prepareStatement(query);

            pstm.setInt(1,model.getOrderId());
            pstm.setDouble(2, model.getAmount());

            pstm.executeUpdate();
            result = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }




    public Products getSingleProduct(int id) {
        Products row = null;
        String query = "select * from products where product_id=? ";
        try {

            cnn=(new DBContext()).connection;
            pstm = cnn.prepareStatement(query);



            pstm.setInt(1, id);
            rs = pstm.executeQuery();

            while (rs.next()) {
                row = new Products();
                row.setId(rs.getInt("product_id"));
                row.setName(rs.getString("name"));
                row.setPrice(rs.getDouble("price"));
                row.setImage(rs.getString("image"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return row;
    }


    public List<Order> userOrders(int id) {
        List<Order> list = new ArrayList<>();
        String query = "select * from orders where userid=? order by orders.order_id desc";
        try {

            cnn=(new DBContext()).connection;
            pstm = cnn.prepareStatement(query);

            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                DAO productDao = new DAO();
                int pId = rs.getInt("product_id");
                Products product = productDao.getSingleProduct(pId);
                order.setOrderId(rs.getInt("order_id"));
                order.setId(pId);
                order.setName(product.getName());
                order.setPrice(product.getPrice()*rs.getInt("quantity"));
                order.setQunatity(rs.getInt("quantity"));
                order.setDate(rs.getString("date_placed"));
                list.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return list;
    }

    public List<Order> Orders(int id) {
        List<Order> list = new ArrayList<>();
        String query = "select * from orders where order_id=? ";
        try {

            cnn=(new DBContext()).connection;
            pstm = cnn.prepareStatement(query);

            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("order_id"));

                order.setDate(rs.getString("date_placed"));
                list.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return list;
    }


    public void cancelOrder(int id) {
        String query = "DELETE FROM orders WHERE order_id = ?";
        try {
            cnn = (new DBContext()).connection;
            pstm = cnn.prepareStatement(query);
            pstm.setInt(1, id);
            int rowsAffected = pstm.executeUpdate();

            // Optionally, you can check the number of affected rows
            if (rowsAffected > 0) {
                System.out.println("Order canceled successfully.");
            } else {
                System.out.println("No order found with the specified ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print(e.getMessage());
        }
    }

    public double getTotalCartPrice(ArrayList<cart> cartList) {
        double sum = 0;
        try {
            if (cartList.size() > 0) {
                for (cart item : cartList) {
                     String query = "select price from products where product_id=?";

                    cnn=(new DBContext()).connection;
                    pstm = cnn.prepareStatement(query);
                    pstm.setInt(1, item.getId());
                    rs = pstm.executeQuery();


                    while (rs.next()) {
                        sum+=rs.getDouble("price")*item.getQuantity();
                    }

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return sum;
    }



    public static void main(String[] args) {
        DAO dao = new DAO();
        List<Products> list = dao.getAllProduct();
        List<Category> listC = dao.getAllCategory();


        for (Category o : listC) {
            System.out.println(o);
        }
    }


}
