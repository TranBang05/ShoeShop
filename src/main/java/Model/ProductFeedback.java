/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import DAL.DBContext;

import java.sql.*;

/**
 *
 * @author ADMIN
 */
public class ProductFeedback {

    private int feedback_id;

    private String title;
    private String content;
    private int author_id;
    private Timestamp date_posted;
    private int product_id;
    private int star;
    private String authorName;

    public ProductFeedback() {
        connect ();
    }

    public ProductFeedback(int feedback_id, String title, String content ,int author_id, Timestamp date_posted, int product_id, int star, String authorName) {
        this.feedback_id = feedback_id;
        this.title = title;
        this.content = content;
        this.author_id = author_id;
        this.date_posted = date_posted;
        this.product_id = product_id;
        this.star = star;
        this.authorName = authorName;
        connect ();
    }

    public ProductFeedback(String title,String content, int author_id, Timestamp date_posted, int product_id, int star) {
        this.title = title;
        this.content = content;
        this.author_id = author_id;
        this.date_posted = date_posted;
        this.product_id = product_id;
        this.star = star;
        connect ();

    }

    public int getFeelback_id() {
        return feedback_id;
    }

    public void setFeelback_id(int feelback_id) {
        this.feedback_id = feelback_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public Timestamp getDate_posted() {
        return date_posted;
    }

    public void setDate_posted(Timestamp date_posted) {
        this.date_posted = date_posted;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "ProductFeedback{" + "feelback_id=" + feedback_id + ", title=" + title + ", content=" + content + ", author_id=" + author_id + ", date_posted=" + date_posted + ", product_id=" + product_id + ", star=" + star + ", authorName=" + authorName + '}';
    }

    Connection cnn;
    Statement stm;
    PreparedStatement pstm;
    ResultSet rs;

    private void connect() {
        try {
            cnn = (new DBContext ()).connection;
            if (cnn != null) {
                System.out.println("Connect success");
            }
        } catch (Exception e) {
            System.out.println("Connection error: " + e.getMessage());
        }
    }


    public int countProductFeedbacks() {
        // Kết nối đến cơ sở dữ liệu và thực hiện truy vấn để đếm số lượng đánh giá
        // Ví dụ, giả sử bạn có sử dụng DBContext và Connection cnn, bạn có thể thực hiện như sau:
        int count = 0;
        try {
            String query = "SELECT COUNT(*) AS feedback_count FROM product_feedbacks;";
            Statement stm = cnn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            if (rs.next()) {
                count = rs.getInt("feedback_count");
            }
        } catch (SQLException e) {
            System.out.println("countProductFeedbacks: " + e.getMessage());
        }
        return count;
    }

    public static void main(String[] args) {
        ProductFeedback productFeedback = new ProductFeedback();
        int feedbackCount = productFeedback.countProductFeedbacks();
        System.out.println("Total Product Feedbacks: " + feedbackCount);
    }


}