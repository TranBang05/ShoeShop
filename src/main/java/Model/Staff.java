/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import DAL.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author ADMIN
 */
public class Staff {

    private int id;
    private String username;
    private String password;

    private String email;
    private String address;
    private String phone_number;
    private int role;

    public Staff(int id, String username, String password, String email, String address, String phone_number, int role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.phone_number = phone_number;
        this.role = role;
    }

    public Staff() {
    }
    public Staff(String username, String password) {
        this.username = username;
        this.password = password;
        connect();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
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
    public Staff staffLogin(String username, String password) {
        Staff staff = null;

        try {
            String strSelect = "SELECT * FROM Staff "
                    + "WHERE username=? "
                    + "AND password=?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, username);
            pstm.setString(2, password);
            rs = pstm.executeQuery();
            while (rs.next()) {
                staff = new Staff();
                staff.setId(rs.getInt("staff_id"));
                staff.setUsername(rs.getString("username"));
                staff.setEmail(rs.getString("email"));

            }
        } catch (Exception e) {
            System.out.println("checkStaff: " + e.getMessage());
        }

        return staff;
    }

}
