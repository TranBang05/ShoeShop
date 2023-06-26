package DAO;

import DAL.DBContext;
import Model.Products;
import Model.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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

    public static void main(String[] args) {
        DAO dao = new DAO();
        List<Products> list = dao.getAllProduct();
        List<Category> listC = dao.getAllCategory();

        for (Category o : listC) {
            System.out.println(o);
        }
    }
}
