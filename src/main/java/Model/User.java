package Model;

import DAL.DBContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String address;
    private String phone_number;
    private String code;
    private int role;
    private int status;



    public User() {
        connect();
    }

    public User(String username)
    {
        this.username = username;
        connect();
    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        connect();
    }

    public User(String username, String password, String email, String code) {

        this.username = username;
        this.password = password;
        this.email = email;
        this.code=code;
        connect();
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Connection getCnn() {
        return cnn;
    }

    public void setCnn(Connection cnn) {
        this.cnn = cnn;
    }

    public Statement getStm() {
        return stm;
    }

    public void setStm(Statement stm) {
        this.stm = stm;
    }

    public PreparedStatement getPstm() {
        return pstm;
    }

    public void setPstm(PreparedStatement pstm) {
        this.pstm = pstm;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    public User(int id, String username, String password, String email, String address, String phone_number, int role, int status, Connection cnn, Statement stm, PreparedStatement pstm, ResultSet rs) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.phone_number = phone_number;
        this.role = role;
        this.status = status;
        this.cnn = cnn;
        this.stm = stm;
        this.pstm = pstm;
        this.rs = rs;
    }





    public User(String username, String password, String email) {

        this.username = username;
        this.password = password;
        this.email = email;
        connect();
    }


    public User(String username, String password, String email,int role) {

        this.username = username;
        this.password = password;
        this.email = email;
        this.role=role;
        connect();
    }

    public User(String username, String password, int role) {

        this.username = username;
        this.password = password;
        this.role = role;
        connect();
    }




    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public User userLogin(String username, String password) {
        User user = null;

        try {
            String strSelect = "SELECT * FROM Users "
                    + "WHERE username=? "
                    + "AND password=?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, username);
            pstm.setString(2, password);
            rs = pstm.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt("userid"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));

            }
        } catch (Exception e) {
            System.out.println("checkUser: " + e.getMessage());
        }

        return user;
    }


    public boolean checkAccount() {
        try {
            String strSelect="select * from Users "
                    + "where username=? ";
            pstm=cnn.prepareStatement(strSelect);
            pstm.setString(1, username);
            rs=pstm.executeQuery();
            while(rs.next()){
                return true;
            }
        } catch (Exception e) {
            System.out.println("checkAccount"+e.getMessage());
        }
        return false;
    }

    public void addUser() {
        try {
            String stradd = "INSERT INTO Users(username, password, email,role)"
                    + "VALUES (?,?,?,?)";

            pstm = cnn.prepareStatement(stradd);
            pstm.setString(1, username);
            pstm.setString(2, password);
            pstm.setString(3, email);
            pstm.setInt ( 4,role );
            pstm.execute();

        } catch (Exception e) {
            System.out.println("ADD: "+e.getMessage());
        }
    }

    public User getUserByAccount2(String acc) {
        User u = null;
        try {
            String strSelect = "SELECT * FROM Users "
                    + "WHERE username = '" + acc + "'";
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                String username = rs.getString(2);
                String password = rs.getString(3);
                String email = rs.getString(4);

                return u = new User(username, password, email);
            }
        } catch (Exception e) {
            System.out.println("getUserByAccount: " + e.getMessage());
        }

        return u;
    }


    public int getUserByRole(String acc,String pass) {
        int role=0;
        try {
            String strSelect = "SELECT * FROM Users "
                    + "WHERE username = '" + acc + "' AND password = '" + pass + "'";

            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {

                role = rs.getInt (7);

            }
        } catch (Exception e) {
            System.out.println("getUserByAccount: " + e.getMessage());
        }

        return role;
    }



    public void updatePassword(User u) {
        try {
            String strUpdate = "UPDATE Users SET password=? WHERE username=?";

            pstm = cnn.prepareStatement(strUpdate);
            pstm.setString(1, u.getPassword());
            pstm.setString(2, u.getUsername());
            pstm.execute();
        } catch (Exception e) {
            System.out.println("updatePassword: " + e.getMessage());
        }
    }

    public List<User> getListUser() {
        List<User> userList = new ArrayList<>();

        try {
            String strSelect = "SELECT * FROM Users";
            stm = cnn.createStatement();
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                int role = rs.getInt("role");

                User user = new User(username, password, email, role);
                userList.add(user);
            }
        } catch (Exception e) {
            System.out.println("getListUser: " + e.getMessage());
        }

        return userList;
    }
}