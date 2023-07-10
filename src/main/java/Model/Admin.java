package Model;

import DAL.DBContext;

import java.sql.*;


public class Admin {
    private String username;
    private String password;

    public Admin() {
        connect();
    }

    public String getUsername() {
        return username;
    }

    // Setter cho thuộc tính username
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter cho thuộc tính password
    public String getPassword() {
        return password;
    }

    // Setter cho thuộc tính password
    public void setPassword(String password) {
        this.password = password;
    }


    Connection cnn;
    Statement stm;
    PreparedStatement pstm;
    ResultSet rs;
    // Phương thức đăng nhập


    public Admin adminLogin(String username, String password) {
        Admin admin = null;

        try {
            String strSelect = "SELECT * FROM admins "
                    + "WHERE username=? "
                    + "AND password=?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, username);
            pstm.setString(2, password);
            rs = pstm.executeQuery();

            if (rs.next()) {
                admin = new Admin();
                admin.setUsername(rs.getString("username"));
                admin.setPassword(rs.getString("password"));
                // Cập nhật các thuộc tính khác của đối tượng Admin tại đây
            }
        } catch (Exception e) {
            System.out.println("adminLogin: " + e.getMessage());
        }

        return admin;
    }



    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
        connect();
    }


    private void connect() {
        try {
            cnn = (new DBContext()).connection;
            if (cnn != null) {
                System.out.println("Connect success");
            }
        } catch (Exception e) {
            System.out.println("Connect error: " + e.getMessage());
        }
    }


}

