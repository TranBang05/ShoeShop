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
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ADMIN
 */
public class Staff {

    private int id;
    private int admin_id;
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
        connect ();
    }


    public Staff(String username, String phone_number, String password, String pass, int role, int id) {

        this.username = username;
        this.password = password;

        this.phone_number = phone_number;
        this.role = role;
        this.id = id;
        connect ();
    }

    public Staff(String username, String password, String email, String address, String phone_number) {
        // this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.phone_number = phone_number;

        connect ();
    }

    public Staff() {
        connect ();
    }

    public Staff(String username, String password) {
        this.username = username;
        this.password = password;
        connect ();
    }

    public Staff(String fullname, String pass, String user, String address, String phone, int id) {
        this.username = fullname;
        this.password = pass;
        this.email = user;
        this.address = address;
        this.phone_number = phone;
        this.id = id;
        connect ();


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
            cnn = (new DBContext ()).connection;
            if (cnn != null) {
                System.out.println ( "Connect success" );
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
            pstm = cnn.prepareStatement ( strSelect );
            pstm.setString ( 1, username );
            pstm.setString ( 2, password );
            rs = pstm.executeQuery ();
            while (rs.next ()) {
                staff = new Staff ();
                staff.setPhone_number ( rs.getString ( "phone_number" ) );
                staff.setAddress ( rs.getString ( "address" ) );
                staff.setPassword (rs.getString ( "password" ));
                staff.setId ( rs.getInt ( "staff_id" ) );
                staff.setUsername ( rs.getString ( "username" ) );
                staff.setEmail ( rs.getString ( "email" ) );
                staff.setRole ( rs.getInt ( "role" ) );
            }
        } catch (Exception e) {
            System.out.println ( "checkStaff: " + e.getMessage () );
        }

        return staff;
    }


    public List<Staff> getAllStaffMembers() {
        List<Staff> staffList = new ArrayList<> ();

        try {
            String strSelect = "SELECT * FROM Staff";
            pstm = cnn.prepareStatement ( strSelect );
            rs = pstm.executeQuery ();
            while (rs.next ()) {
                int id = rs.getInt ( "staff_id" );
                String username = rs.getString ( "username" );
                String password = rs.getString ( "password" );
                String email = rs.getString ( "email" );
                String address = rs.getString ( "address" );
                String phone_number = rs.getString ( "phone_number" );
                int role = rs.getInt ( "role" );

                Staff staff = new Staff ( id, username, password, email, address, phone_number, role );
                staffList.add ( staff );
            }
        } catch (Exception e) {
            System.out.println ( "getAllStaffMembers: " + e.getMessage () );
        }

        return staffList;
    }

    public boolean isSecurePassword(String password) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        Pattern pattern = Pattern.compile ( regex );
        Matcher matcher = pattern.matcher ( password );
        return matcher.matches ();
    }

    public Staff searchByEmail(String email) {
        String sql = "SELECT * FROM Staff WHERE email = ?";

        try {
            PreparedStatement pre = cnn.prepareStatement ( sql );
            pre.setString ( 1, email );
            ResultSet rs = pre.executeQuery ();
            while (rs.next ()) {
                int id = rs.getInt ( "staff_id" );
                String username = rs.getString ( "username" );
                String password = rs.getString ( "password" );
                String address = rs.getString ( "address" );
                String phone_number = rs.getString ( "phone_number" );
                int role = rs.getInt ( "role" );

                Staff staff = new Staff ( id, username, password, email, address, phone_number, role );
                return staff;
            }
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return null;
    }


    public void addStaffMember(Staff newStaff) {
        try {
            String strInsert = "INSERT INTO Staff (username, password, email, address, phone_number, role) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            pstm = cnn.prepareStatement ( strInsert );
            pstm.setString ( 1, newStaff.getUsername () );
            pstm.setString ( 2, newStaff.getPassword () );
            pstm.setString ( 3, newStaff.getEmail () );
            pstm.setString ( 4, newStaff.getAddress () );
            pstm.setString ( 5, newStaff.getPhone_number () );
            pstm.setInt ( 6, newStaff.getRole () );

            pstm.executeUpdate ();
            System.out.println ( "Staff member added successfully." );
        } catch (Exception e) {
            System.out.println ( "addStaffMember: " + e.getMessage () );
        }

    }

    public int updateStaffByPre(Staff staff) {
        int n = 0;
        String sql = "UPDATE Staff " +
                "SET username = ?, " +
                "password = ?, " +
                "email = ?, " +
                "address = ?, " +
                "phone_number = ?, " +
                "role = ? " +
                "WHERE staff_id = ?;";

        try {
            PreparedStatement pre = cnn.prepareStatement ( sql );
            pre.setString ( 1, staff.getUsername () );
            pre.setString ( 2, staff.getPassword () );
            pre.setString ( 3, staff.getEmail () );
            pre.setString ( 4, staff.getAddress () );
            pre.setString ( 5, staff.getPhone_number () );
            pre.setInt ( 6, staff.getRole () );
            pre.setInt ( 7, staff.getId () );

            n = pre.executeUpdate ();
        } catch (Exception e) {
            System.out.println ( "updateStaffByPre: " + e.getMessage () );
        }

        return n;
    }


    public void insertStaff(Staff newStaff, int adminId) {
        try {
            String strInsert = "INSERT INTO Staff (username, password, email, address, phone_number, role, admin_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            pstm = cnn.prepareStatement ( strInsert );
            pstm.setString ( 1, newStaff.getUsername () );
            pstm.setString ( 2, newStaff.getPassword () );
            pstm.setString ( 3, newStaff.getEmail () );
            pstm.setString ( 4, newStaff.getAddress () );
            pstm.setString ( 5, newStaff.getPhone_number () );
            pstm.setInt ( 6, newStaff.getRole () );
            pstm.setInt ( 7, adminId ); // Set the admin_id for the new staff

            pstm.executeUpdate ();
            System.out.println ( "Staff member added successfully." );
        } catch (Exception e) {
            System.out.println ( "insertStaff: " + e.getMessage () );
        }
    }


    public static void main(String[] args) {
        // Create a sample Staff object with the updated data
        Staff staff = new Staff ();
        staff.setId ( 3); // Assuming staff_id 1 is the one you want to update
        staff.setUsername ( "newUsername" );
        staff.setPassword ( "newPassword" );
        staff.setEmail ( "newEmail@example.com" );
        staff.setAddress ( "New Address" );
        staff.setPhone_number ( "1234567890" );
        staff.setRole ( 1); // Assuming role 2 represents the updated role value

        // Test the updateStaffByPre method
        int result = staff.updateStaffByPre ( staff );
        if (result > 0) {
            System.out.println ( "Staff update successful!" );

            // Retrieve and display the updated staff information

        }

    }
}
