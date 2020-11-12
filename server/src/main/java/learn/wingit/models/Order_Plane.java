package learn.wingit.models;

import javax.validation.constraints.Min;

public class Order_Plane {
    @Min(value = 1, message = "Order ID must be greater than or equal to {value}")
    private int orderId;
    @Min(value = 1, message = "Plane ID must be greater than or equal to {value}")
    private int plane_Id;
    @Min(value = 1, message = "Number ordered must be greater than or equal to {value}")
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
