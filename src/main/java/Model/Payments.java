package Model;
import DAL.DBContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import java.util.Date;
import java.time.LocalDate;
import java.time.YearMonth;

public class Payments {
    int id;
    int orderId;
    String payment;
    double amount;
    String date;
    String phone;
    String address;
    String note;






    public Payments(int id, int orderId, String payment, double amount, String date) {
        this.id = id;
        this.orderId = orderId;
        this.payment = payment;
        this.amount = amount;
        this.date = date;
        connect ();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Payments() {
        connect ();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public int calculateTotalAmount(int year) {
        int totalAmount = 0;

        try {
            String query = "SELECT SUM(amount) AS totalAmount FROM payments WHERE YEAR(date_paid) = ?";
            try (PreparedStatement pstm = cnn.prepareStatement(query)) {
                pstm.setInt(1, year);
                try (ResultSet rs = pstm.executeQuery()) {
                    if (rs.next()) {
                        totalAmount = rs.getInt("totalAmount");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("calculateTotalAmount: " + e.getMessage());
        }

        return totalAmount;
    }

    public int calculateTotalAmount() {
        int totalAmount = 0;

        try {
            String query = "SELECT SUM(amount) AS totalAmount FROM payments";
            try (PreparedStatement pstm = cnn.prepareStatement(query);
                 ResultSet rs = pstm.executeQuery()) {

                if (rs.next()) {
                    totalAmount = rs.getInt("totalAmount");
                }
            }
        } catch (Exception e) {
            System.out.println("calculateTotalAmount: " + e.getMessage());
        }

        return totalAmount;
    }









    public Map<Integer, Integer> calculateTotalAmountByYear(int year) {
        Map<Integer, Integer> monthlyAmounts = new HashMap<>();

        try {
            for (int month = 1; month <= 12; month++) {
                YearMonth yearMonthObject = YearMonth.of(year, month);
                int daysInMonth = yearMonthObject.lengthOfMonth();
                LocalDate startDate = LocalDate.of(year, month, 1);
                LocalDate endDate = LocalDate.of(year, month, daysInMonth);

                String query = "SELECT SUM(amount) AS totalAmount FROM payments WHERE date_paid >= ? AND date_paid <= ?";
                PreparedStatement pstm = cnn.prepareStatement(query);
                pstm.setDate(1, java.sql.Date.valueOf(startDate));
                pstm.setDate(2, java.sql.Date.valueOf(endDate));
                ResultSet rs = pstm.executeQuery();

                if (rs.next()) {
                    int totalAmount = rs.getInt("totalAmount");
                    monthlyAmounts.put(month, totalAmount);
                }

                rs.close();
                pstm.close();
            }
        } catch (Exception e) {
            System.out.println("calculateTotalAmountByYear: " + e.getMessage());
        }

        return monthlyAmounts;
    }




    public Map<String, Integer> calculateTotalByDay(int year, int month) {
        Map<String, Integer> totalByDay = new HashMap<>();

        try {
            YearMonth yearMonthObject = YearMonth.of(year, month);
            int daysInMonth = yearMonthObject.lengthOfMonth();

            for (int day = 1; day <= daysInMonth; day++) {
                String dayString = String.format("%02d", day);
                String query = "SELECT SUM(amount) AS totalAmount FROM payments WHERE DAY(date_paid) = ? AND MONTH(date_paid) = ? AND YEAR(date_paid) = ?";
                PreparedStatement pstm = cnn.prepareStatement(query);
                pstm.setInt(1, day);
                pstm.setInt(2, month);
                pstm.setInt(3, year);
                ResultSet rs = pstm.executeQuery();

                if (rs.next()) {
                    int totalAmount = rs.getInt("totalAmount");
                    totalByDay.put(dayString, totalAmount);
                }

                rs.close();
                pstm.close();
            }
        } catch (Exception e) {
            System.out.println("calculateTotalByDay: " + e.getMessage());
        }

        return totalByDay;
    }









}