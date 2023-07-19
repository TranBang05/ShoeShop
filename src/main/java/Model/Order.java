package Model;

public class Order extends Products {

    private int orderId;
    private int uid;
    private int qunatity;
    private String date;

    private int staffId;
    private String state;
    private double total_cost;

    public Order() {
    }

    public Order(int orderId, int uid,  int staffId,String state, double total_cost,  String date ) {
        this.orderId = orderId;
        this.uid = uid;
        this.staffId = staffId;
        this.state = state;
        this.total_cost = total_cost;
        this.date = date;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(double total_cost) {
        this.total_cost = total_cost;
    }

    public Order(int orderId, int uid, int qunatity, String date) {
        super();
        this.orderId = orderId;
        this.uid = uid;
        this.qunatity = qunatity;
        this.date = date;
    }

    public Order(int uid, int qunatity, String date) {
        super();
        this.uid = uid;
        this.qunatity = qunatity;
        this.date = date;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getQunatity() {
        return qunatity;
    }

    public void setQunatity(int qunatity) {
        this.qunatity = qunatity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Order{" + "orderId=" + orderId + ", uid=" + uid + ", qunatity=" + qunatity + ", date=" + date + ", staffId=" + staffId + ", state=" + state + ", total_cost=" + total_cost + '}';
    }

}
