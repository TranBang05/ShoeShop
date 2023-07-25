package Model;

import DAL.DBContext;

import java.sql.*;


public class Admin {
    public int admin_id;
    int user_id;
    int staff_id;
    private String username;

    private  String email;
    private String password;

    public Admin() {
        connect();
    }
    // Getter for admin_id
    public int getAdminId() {
        return admin_id;
    }

    // Setter for admin_id
    public void setAdminId(int admin_id) {
        this.admin_id = admin_id;
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

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    // Getter and Setter methods for staff_id
    public int getStaffId() {
        return staff_id;
    }

    public void setStaffId(int staff_id) {
        this.staff_id = staff_id;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
                admin.setAdminId(rs.getInt("admin_id"));
                admin.setUsername(rs.getString("username"));
                admin.setPassword(rs.getString("password"));
                admin.setEmail(rs.getString("email"));
                // Cập nhật các thuộc tính khác của đối tượng Admin tại đây (nếu có)
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

    public Admin searchAdminByEmail(String email) {
        Admin admin = null;
        try {
            String strSelect = "SELECT * FROM admins "
                    + "WHERE email=?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, email);
            rs = pstm.executeQuery();

            if (rs.next()) {
                admin = new Admin();
                admin.setAdminId (rs.getInt("admin_id"));
                admin.setUsername(rs.getString("username"));
                admin.setPassword(rs.getString("password"));
                admin.setEmail(rs.getString("email"));
                // Set other properties of the admin object if needed
            }
        } catch (Exception e) {
            System.out.println("searchAdminByEmail: " + e.getMessage());
        }

        return admin;
    }


    public static void main(String[] args) {
        String emailToSearch = "nguyentien020102@gmail.com"; // Thay đổi email này bằng email cần tìm kiếm

        Admin admin = new Admin();
        Admin searchedAdmin = admin.searchAdminByEmail(emailToSearch);

        if (searchedAdmin != null) {
            System.out.println("Admin found!");
            System.out.println("Admin ID: " + searchedAdmin.getAdminId());
            System.out.println("Username: " + searchedAdmin.getUsername());
            System.out.println("Password: " + searchedAdmin.getPassword());
            System.out.println("Email: " + searchedAdmin.getEmail());
            // Hiển thị các thuộc tính khác của admin nếu cần
        } else {
            System.out.println("Admin with email " + emailToSearch + " not found.");
        }
    }
}