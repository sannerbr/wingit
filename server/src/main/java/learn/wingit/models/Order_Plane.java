package learn.wingit.models;

public class Order_Plane {
    private int orderId;
    private int plane_Id;
    private int number_ordered;
    private Order order;
    private Plane plane;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getPlane_Id() {
        return plane_Id;
    }

    public void setPlane_Id(int plane_Id) {
        this.plane_Id = plane_Id;
    }

    public int getNumber_ordered() {
        return number_ordered;
    }

    public void setNumber_ordered(int number_ordered) {
        this.number_ordered = number_ordered;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }
}
