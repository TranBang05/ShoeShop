package DAO;

import DAL.DBContext;

import Model.*;

import Model.Order;
import Model.User;
import Model.Products;
import Model.Blog;
import Model.BlogComment;
import Model.Category;
import Model.ProductFeedback;
import Model.cart;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAO {

    Connection cnn;
    Statement stm;// thuc thi cau lenh sql
    PreparedStatement pstm;
    ResultSet rs;// luu tru du lieu vaxu li
    // PreparedStatement pstm; //thuc thi cau lenh spl

    private void connect() {
        try {
            cnn = (new DBContext()).connection;
            if (cnn != null) {
                System.out.println("Connect success");
            }
        } catch (Exception e) {
        }
    }

    public List<Products> getAllProduct() {
        List<Products> list = new ArrayList<>();
        String query = "SELECT *, CASE WHEN discount IS NOT NULL THEN price - (price * discount) ELSE price END AS discounted_price FROM products";
        try {
            cnn = (new DBContext()).connection;
            pstm = cnn.prepareStatement(query);
            rs = pstm.executeQuery();

            while (rs.next()) {
                list.add( new Products(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5), rs.getString(7),
                        rs.getDouble(8),     rs.getDouble(9)));
            }
        } catch (Exception e) {
        }
        return list;
    }



    public List<Blog> getAllBlogs() {
        List<Blog> list = new ArrayList<>();
        String query = "   select b.blog_id,b.title, b.content, b.author_id,b.date_posted, b.image, u.username from blogs  b\r\n"
                + "  join users u\r\n"
                + "  on b.author_id = u.userid\r\n"
                + "  order by b.date_posted desc";
        try {
            cnn = (new DBContext()).connection;
            pstm = cnn.prepareStatement(query);
            rs = pstm.executeQuery();

            while (rs.next()) {
                list.add(new Blog(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getTimestamp(5),
                        rs.getString(6), rs.getString(7)));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return list;
    }

    public boolean deleteProduct(String productId) {
        boolean success = false;
        String query = "DELETE FROM Products WHERE product_id = ?";

        try {
            cnn = new DBContext().connection;
            pstm = cnn.prepareStatement(query);
            pstm.setString(1, productId);

            int rowsDeleted = pstm.executeUpdate();
            success = (rowsDeleted > 0);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            // Close connections and resources
            // ...
        }

        return success;
    }


    public void mapParams(PreparedStatement ps, List<Object> args) throws SQLException {
        int i = 1;
        for (Object arg : args) {
            if (arg instanceof Date) {
                ps.setTimestamp(i++, new Timestamp(((Date) arg).getTime()));
            } else if (arg instanceof Integer) {
                ps.setInt(i++, (Integer) arg);
            } else if (arg instanceof Long) {
                ps.setLong(i++, (Long) arg);
            } else if (arg instanceof Double) {
                ps.setDouble(i++, (Double) arg);
            } else if (arg instanceof Float) {
                ps.setFloat(i++, (Float) arg);
            } else {
                ps.setString(i++, (String) arg);
            }
        }
    }



    public List<Products> getAllProductForManage(String searchParam, String categoryParam) {
        List<Object> list = new ArrayList<>();
        List<Products> products = new ArrayList<>();

        try {
            StringBuilder query = new StringBuilder();
            query.append("select p.product_id, p.name, p.description, p.price, p.image, p.category_id, c.name\n")
                    .append("from products p join Categories c\n")
                    .append("on p.category_id = c.category_id ");

            if (searchParam != null && !searchParam.trim().isEmpty()) {
                query.append("and p.name LIKE ? ");
                list.add("%" + searchParam + "%");
            }
            if (categoryParam != null && !categoryParam.trim().isEmpty()) {
                query.append("AND p.category_id = ? ");
                list.add(Integer.parseInt(categoryParam));
            }
            cnn = (new DBContext()).connection;
            pstm = cnn.prepareStatement(query.toString());
            mapParams(pstm, list);
            rs = pstm.executeQuery();
            while (rs.next()) {
                products.add(new Products(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return products;
    }


    public List<User> getAllUsersForManage(String searchParam, String statusParam) {
        List<Object> list = new ArrayList<>();
        List<User> users = new ArrayList<>();

        try {
            StringBuilder query = new StringBuilder();
            query.append("SELECT *\n"
                    + "FROM Users u\n"
                    + "WHERE u.userid > 1");

            if (searchParam != null && !searchParam.trim().isEmpty()) {
                query.append(" AND (u.username LIKE ? OR u.email LIKE ?)");
                list.add("%" + searchParam + "%");
                list.add("%" + searchParam + "%");
            }
            if (statusParam != null && !statusParam.trim().isEmpty()) {
                query.append(" and status = ? ");
                list.add(Integer.parseInt(statusParam));
            }
            cnn = (new DBContext()).connection;
            pstm = cnn.prepareStatement(query.toString());
            mapParams(pstm, list);
            rs = pstm.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("userid"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setAddress(rs.getString("address"));
                user.setPhone_number(rs.getString("phone_number"));
                user.setStatus(rs.getInt("status"));
                users.add(user);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return users;
    }

    public List<Products> Paging(List<Products> list, int pageParam, int size) {
        if (list.isEmpty()) {
            return list;
        }
        return list.subList((pageParam - 1) * size, size * pageParam >= list.size() ? list.size() : size * pageParam);
    }

    public List<User> PagingUser(List<User> list, int pageParam, int size) {
        if (list.isEmpty()) {
            return list;
        }
        return list.subList((pageParam - 1) * size, size * pageParam >= list.size() ? list.size() : size * pageParam);
    }

    public boolean updateProduct(String id, String name, String description, String price, String image,
                                 String cateId) {
        boolean success = false;
        String query = "UPDATE Products SET name=?, description=?, price=?, image=?, category_id=? WHERE product_id=?";

        try {
            cnn = new DBContext().connection;
            pstm = cnn.prepareStatement(query);
            pstm.setString(1, name);
            pstm.setString(2, description);
            pstm.setString(3, price);
            pstm.setString(4, image);
            pstm.setString(5, cateId);
            pstm.setString(6, id);

            int rowsUpdated = pstm.executeUpdate();
            success = (rowsUpdated > 0);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            // Close connections and resources
            // ...
        }

        return success;
    }

    public Products getProductByIDForManage(String id) {

        String query = "select * from products where product_id=?";
        try {
            cnn = (new DBContext()).connection;
            pstm = cnn.prepareStatement(query);
            pstm.setString(1, id);
            rs = pstm.executeQuery();

            while (rs.next()) {
                return new Products(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
                        rs.getString(6));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }


    public List<BlogComment> getAllBlogCommentByBlogID(int blogId, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        List<BlogComment> list = new ArrayList<>();
        String query = "SELECT bc.comment_id, bc.content, bc.author_id, bc.date_posted, bc.blog_id, u.username\n"
                + "FROM Blog_Comments bc\n"
                + "JOIN users u ON bc.author_id = u.userid\n"
                + "WHERE bc.blog_id = ?\n"
                + "ORDER BY bc.date_posted DESC\n"
                + "LIMIT ? OFFSET ?";
        try {
            cnn = (new DBContext()).connection;
            pstm = cnn.prepareStatement(query);
            pstm.setInt(1, blogId);
            pstm.setInt(2, pageSize);
            pstm.setInt(3, offset);
            rs = pstm.executeQuery();

            while (rs.next()) {
                list.add(new BlogComment(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getTimestamp(4), rs.getInt(5),
                        rs.getString(6)));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return list;
    }

    public List<ProductFeedback> getAllProductFeedbackByProductID(int product_id, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        List<ProductFeedback> list = new ArrayList<>();
        String query = "SELECT pf.feelback_id ,pf.title, pf.content, pf.author_id, \n"
                + "pf.date_posted, pf.product_id, pf.star, u.username\n"
                + "                FROM Product_feedbacks pf\n"
                + "                JOIN users u ON pf.author_id = u.userid\n"
                + "                WHERE pf.product_id = ?\n"
                + "                ORDER BY pf.date_posted DESC\n"
                + "                LIMIT ? OFFSET ?";
        try {
            cnn = (new DBContext()).connection;
            pstm = cnn.prepareStatement(query);
            pstm.setInt(1, product_id);
            pstm.setInt(2, pageSize);
            pstm.setInt(3, offset);
            rs = pstm.executeQuery();

            while (rs.next()) {
                list.add(new ProductFeedback(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getTimestamp(5), rs.getInt(6), rs.getInt(7),
                        rs.getString(8)));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return list;
    }

    public void addComment(BlogComment comment) {
        String query = "INSERT INTO blog_comments ( content,author_id , date_posted, blog_id) VALUES (?, ?, ?, ?)";

        try {
            cnn = (new DBContext()).connection;
            pstm = cnn.prepareStatement(query);

            // Set the parameter values for the query
            pstm.setString(1, comment.getContent());
            pstm.setInt(2, comment.getAuthor_id());
            pstm.setTimestamp(3, comment.getDate_posted());
            pstm.setInt(4, comment.getBlog_id());

            // Execute the query
            pstm.executeUpdate();

            System.out.println("Comment added successfully.");
        } catch (Exception e) {
            System.err.println("Error adding comment: " + e);
        } finally {
            // Close resources
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (cnn != null) {
                    cnn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e);
            }
        }
    }

    public int countComments(int blog_id) {
        String query = "SELECT COUNT(*) AS count FROM blog_comments WHERE blog_id = ?";
        int count = 0;
        try {
            cnn = (new DBContext()).connection;
            pstm = cnn.prepareStatement(query);

            // Set the parameter values for the query
            pstm.setInt(1, blog_id);

            // Execute the query
            rs = pstm.executeQuery(); // Assign the result set to 'rs'

            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (Exception e) {
            System.err.println("countComments" + e);
        }
        return count;
    }

    public int countProductFeedback(int blog_id) {
        String query = "SELECT COUNT(*) AS count FROM Product_feedbacks WHERE product_id = ?";
        int count = 0;
        try {
            cnn = (new DBContext()).connection;
            pstm = cnn.prepareStatement(query);

            // Set the parameter values for the query
            pstm.setInt(1, blog_id);

            // Execute the query
            rs = pstm.executeQuery(); // Assign the result set to 'rs'

            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (Exception e) {
            System.err.println("countComments" + e);
        }
        return count;
    }

    public void addPost(Blog blog) {
        String query = "INSERT INTO blogs (title, content, author_id, date_posted, image) VALUES (?, ?, ?, ?, ?)";

        try {
            cnn = (new DBContext()).connection;
            pstm = cnn.prepareStatement(query);

            // Set the parameter values for the query
            pstm.setString(1, blog.getTitle());
            pstm.setString(2, blog.getContent());
            pstm.setInt(3, blog.getAuthor_id());
            pstm.setTimestamp(4, blog.getDate_posted());
            pstm.setString(5, blog.getImage());

            // Execute the query
            pstm.executeUpdate();

            System.out.println("Blog post added successfully.");
        } catch (Exception e) {
            System.err.println("Error adding blog post: " + e);
        } finally {
            // Close resources
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (cnn != null) {
                    cnn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e);
            }
        }
    }

    public Blog getBlogByID(int id) {

        String query = " select b.blog_id,b.title, b.content, b.author_id,b.date_posted, b.image, u.username from blogs  b\r\n"
                + "  join users u\r\n"
                + "  on b.author_id = u.userid\r\n"
                + "	where blog_id=?";
        try {
            cnn = (new DBContext()).connection;
            pstm = cnn.prepareStatement(query);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();

            while (rs.next()) {
                return new Blog(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getTimestamp(5),
                        rs.getString(6), rs.getString(7));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }

    public void deletePost(int blogId) {
        String query = "DELETE FROM blogs WHERE blog_id = ?";

        try {
            cnn = (new DBContext()).connection;
            pstm = cnn.prepareStatement(query);

            // Set the parameter value for the query
            pstm.setInt(1, blogId);

            // Execute the query
            pstm.executeUpdate();

            System.out.println("Blog post deleted successfully.");
        } catch (Exception e) {
            System.err.println("Error deleting blog post: " + e);
        } finally {
            // Close resources
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (cnn != null) {
                    cnn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e);
            }
        }
    }

    public void addProductFeedback(ProductFeedback feedback) {
        String query = "INSERT INTO Product_feedbacks (title,content, author_id, date_posted, product_id, star) VALUES (?,?, ?, ?, ?, ?)";

        try {
            cnn = (new DBContext()).connection;
            pstm = cnn.prepareStatement(query);
            pstm.setString(1, feedback.getTitle());
            pstm.setString(2, feedback.getContent());
            pstm.setInt(3, feedback.getAuthor_id());
            pstm.setTimestamp(4, feedback.getDate_posted());
            pstm.setInt(5, feedback.getProduct_id());
            pstm.setInt(6, feedback.getStar());

            pstm.executeUpdate();
            System.out.println("Product feedback added successfully.");
        } catch (Exception e) {
            System.err.println("Error adding product feedback: " + e);
        } finally {
            // Close resources
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (cnn != null) {
                    cnn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e);
            }
        }
    }

    public List<cart> getCartProducts(ArrayList<cart> cartList) {
        List<cart> book = new ArrayList<>();
        try {
            if (cartList.size() > 0) {
                for (cart item : cartList) {
                    String query = "select * from products where product_id=?";
                    cnn = (new DBContext()).connection;
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
                    cnn = (new DBContext()).connection;
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

            cnn = (new DBContext()).connection;
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, "%" + name + "%");
            rs = pstm.executeQuery();

            while (rs.next()) {
                data.add(new Products(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
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
            cnn = (new DBContext()).connection;
            pstm = cnn.prepareStatement(query);
            pstm.setString(1, cid);
            rs = pstm.executeQuery();

            while (rs.next()) {
                list.add(new Products(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
                        rs.getString(7)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Products getProductByID(String id) {

        String query = "select * from products where product_id=?";
        try {
            cnn = (new DBContext()).connection;
            pstm = cnn.prepareStatement(query);
            pstm.setString(1, id);
            rs = pstm.executeQuery();

            while (rs.next()) {
                return new Products(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
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
            cnn = (new DBContext()).connection;
            pstm = cnn.prepareStatement(query);

            rs = pstm.executeQuery();

            while (rs.next()) {
                list.add(new Category(rs.getInt(1), rs.getString(2)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Products getLast() {
        String query = "select top 1 * from products\n" + "order by id desc";
        try {
            cnn = (new DBContext()).connection;
            pstm = cnn.prepareStatement(query);

            rs = pstm.executeQuery();
            while (rs.next()) {
                return new Products(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
                        rs.getString(7));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public boolean insertOrder(Order model) {
        boolean result = false;
        String query = "insert into orders ( userid, product_id, total, quantity, orderDate) values(?,?,?,?,?)";
        try {
            cnn = (new DBContext()).connection;
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


    public int countProducts() {
        String query = "SELECT COUNT(*) AS count FROM products";
        int count = 0;
        try {
            cnn = (new DBContext()).connection;
            pstm = cnn.prepareStatement(query);


            // Execute the query
            rs = pstm.executeQuery(); // Assign the result set to 'rs'

            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (Exception e) {
            System.err.println("countComments" + e);
        }
        return count;
    }







    public List<Products> getAllProducts(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        List<Products> list = new ArrayList<>();
        String query = "SELECT *, CASE WHEN discount IS NOT NULL THEN price - (price * discount) ELSE price END AS discounted_price" +
                "                FROM products \n" +
                "              \n" +
                "                ORDER BY product_id asc\n" +
                "                LIMIT ? OFFSET ?";
        try {
            cnn = (new DBContext()).connection;
            pstm = cnn.prepareStatement(query);
            pstm.setInt(1, pageSize);
            pstm.setInt(2, offset);
            rs = pstm.executeQuery();

            while (rs.next()) {
                list.add( new Products(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5), rs.getString(7),
                        rs.getDouble(8),     rs.getDouble(9)));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return list;
    }


    public boolean updateProduct(String id, String name, String description, String price, String image,
                                 String cateId, double discount) {
        boolean success = false;
        String query = "UPDATE Products SET name=?, description=?, price=?, image=?, category_id=?, discount =? WHERE product_id=?";

        try {
            cnn = new DBContext().connection;
            pstm = cnn.prepareStatement(query);
            pstm.setString(1, name);
            pstm.setString(2, description);
            pstm.setString(3, price);
            pstm.setString(4, image);
            pstm.setString(5, cateId);
            pstm.setDouble(6, discount);
            pstm.setString(7, id);

            int rowsUpdated = pstm.executeUpdate();
            success = (rowsUpdated > 0);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            // Close connections and resources
            // ...
        }

        return success;
    }





    public boolean insertPayment(Payments model) {
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

            cnn = (new DBContext()).connection;
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

            cnn = (new DBContext()).connection;
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
                order.setPrice(product.getPrice() * rs.getInt("quantity"));
                order.setQunatity(rs.getInt("quantity"));
                order.setDate(rs.getString("orderDate"));
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

                order.setDate(rs.getString("orderDate"));
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

                    cnn = (new DBContext()).connection;
                    pstm = cnn.prepareStatement(query);
                    pstm.setInt(1, item.getId());
                    rs = pstm.executeQuery();

                    while (rs.next()) {
                        sum += rs.getDouble("price") * item.getQuantity();
                    }

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return sum;
    }

    public boolean addProduct(Products product) {
        boolean success = false;
        String query = "INSERT INTO Products (name, description, price, image, category_id) VALUES (?, ?, ?, ?, ?)";

        try {

            cnn = (new DBContext()).connection;
            pstm = cnn.prepareStatement(query);

            pstm.setString(1, product.getName());
            pstm.setString(2, product.getDescription());
            pstm.setDouble(3, product.getPrice());
            pstm.setString(4, product.getImage());
            pstm.setInt(5, product.getCateId());

            int rowsInserted = pstm.executeUpdate();
            success = (rowsInserted > 0);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return success;
    }

    public boolean blockUser(String userId) {
        boolean success = false;
        String query = "UPDATE Users SET status = CASE WHEN status = 0 THEN 1 ELSE 0 END WHERE userid = ?";

        try {
            cnn = new DBContext().connection;
            pstm = cnn.prepareStatement(query);
            pstm.setString(1, userId);

            int rowsAffected = pstm.executeUpdate();
            success = (rowsAffected > 0);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            // Close connections and resources
            // ...
        }

        return success;
    }

    public User getUserByID(int id) {

        String query = "  select * from users where userid = ?";
        try {
            cnn = (new DBContext()).connection;
            pstm = cnn.prepareStatement(query);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();

            while (rs.next()) {
                return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }
    public List<Feedback> getAllFeedback() {
        List<Feedback> feedbackList = new ArrayList<>();
        String query = "          SELECT f.feedback_id, f.userid, f.staff_id, f.title, f.content, f.date_posted,s.username FROM feedback f JOIN staff s \n"
                + "						ON f.staff_id = s.staff_id";
        try {
            cnn = (new DBContext()).connection;
            pstm = cnn.prepareStatement(query);
            rs = pstm.executeQuery();

            while (rs.next()) {
                int feedbackId = rs.getInt("feedback_id");
                int userId = rs.getInt("userid");
                int staffId = rs.getInt("staff_id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                Timestamp datePosted = rs.getTimestamp("date_posted");
                String staffName = rs.getString("username");

                Feedback feedback = new Feedback(feedbackId, userId, staffId, title, content, datePosted, staffName);
                feedbackList.add(feedback);
            }
        } catch (Exception e) {
            System.err.println("Error retrieving all feedback: " + e);

        }
        return feedbackList;
    }

    public boolean insertFeedback(Feedback feedback) {
        String query = "INSERT INTO Feedback (userid, staff_id, title, content, date_posted) VALUES (?, ?, ?, ?, NOW())";
        try {
            cnn = (new DBContext()).connection;
            pstm = cnn.prepareStatement(query);
            pstm.setInt(1, feedback.getUserid());
            pstm.setInt(2, feedback.getStaff_id());
            pstm.setString(3, feedback.getTitle());
            pstm.setString(4, feedback.getContent());
            pstm.executeUpdate();
        } catch (Exception e) {
            System.err.println("Error inserting feedback: " + e);
            e.printStackTrace();
        }
        return  true;
    }

    public List<Order> getOrdersByUserID(int id) {
        List<Order> list = new ArrayList<>();
        String query = " select * from orders where userid  = ?";
        try {
            cnn = (new DBContext()).connection;
            pstm = cnn.prepareStatement(query);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();

            while (rs.next()) {
                list.add(new Order(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getDouble(5), rs.getString(6)));
            }
        } catch (Exception e) {
            System.err.println("loi " + e);
        }
        return list;
    }

    public boolean verifyUserPassword(int userId, String password) {
        boolean isPasswordCorrect = false;
        String query = "SELECT password FROM users WHERE userid  = ?";
        try {
            cnn = (new DBContext()).connection;
            pstm = cnn.prepareStatement(query);
            pstm.setInt(1, userId);
            rs = pstm.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");
                // Compare the stored password with the provided password
                isPasswordCorrect = storedPassword.equals(password);
            }
        } catch (Exception e) {
            System.err.println("Error verifying user password: " + e);
        } finally {
            // Close connections and resources
            // ...
        }
        return isPasswordCorrect;
    }

    public void updateUserPassword(int userId, String newPassword) {
        String query = "UPDATE users SET password = ? WHERE userid = ?";
        try {
            cnn = (new DBContext()).connection;
            pstm = cnn.prepareStatement(query);
            pstm.setString(1, newPassword);
            pstm.setInt(2, userId);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.err.println("Error updating user password: " + e);
        } finally {
            // Close connections and resources
            // ...
        }
    }

    public List<Feedback> getAllFeedbacksForManage(String searchParam, int staffId) {
        List<Object> list = new ArrayList<>();
        List<Feedback> feedbacks = new ArrayList<>();

        try {
            StringBuilder query = new StringBuilder();
            query.append(" select * from feedback where staff_id = ?");

            if (searchParam != null && !searchParam.trim().isEmpty()) {
                query.append(" AND (title LIKE ? OR content LIKE ?)");
                list.add("%" + searchParam + "%");
                list.add("%" + searchParam + "%");
            }
            list.add(staffId);
            cnn = (new DBContext()).connection;
            pstm = cnn.prepareStatement(query.toString());

            mapParams(pstm, list);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Feedback feedback = new Feedback();
                feedback.setFeedback_id(rs.getInt("feedback_id"));
                feedback.setUserid(rs.getInt("userid"));
                feedback.setStaff_id(rs.getInt("staff_id"));
                feedback.setTitle(rs.getString("title"));
                feedback.setContent(rs.getString("content"));
                feedback.setDate_posted(rs.getTimestamp("date_posted"));

                feedbacks.add(feedback);
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return feedbacks;
    }

    public List<Feedback> PagingFeedback(List<Feedback> list, int pageParam, int size) {
        if (list.isEmpty()) {
            return list;
        }
        return list.subList((pageParam - 1) * size, size * pageParam >= list.size() ? list.size() : size * pageParam);
    }

    public boolean updateUserAddressAndPhone(int userId, String address, String phoneNumber) {
        String query = "UPDATE users SET address = ?, phone_number = ? WHERE userid = ?";
        try {
            cnn = (new DBContext()).connection;
            pstm = cnn.prepareStatement(query);
            pstm.setString(1, address);
            pstm.setString(2, phoneNumber);
            pstm.setInt(3, userId);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.err.println("Error updating user address and phone: " + e);
        } finally {
            // Close connections and resources
            // ...
        }
        return true;
    }


    public static void main(String[] args) {
        DAO dao = new DAO();
        List<Products> list = dao.getAllProduct();
        List<Category> listC = dao.getAllCategory();
        List<Blog> listB = dao.getAllBlogs();
        Blog b = dao.getBlogByID(1);
        System.out.println(b);
//        List<BlogComment> blogComments = dao.getAllBlogCommentByBlogID(1);
//        for (BlogComment blogComment : blogComments) {
//            System.out.println(blogComment);
//        }
    }

}
