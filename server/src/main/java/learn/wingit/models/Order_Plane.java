package learn.wingit.models;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class Order_Plane {
    @Min(value = 1, message = "Order ID must be greater than or equal to {value}")
    private int orderId;
    @Min(value = 1, message = "Plane ID must be greater than or equal to {value}")
    private int plane_Id;
    @Min(value = 1, message = "Number ordered must be greater than or equal to {value}")
    private int number_ordered;
    private Order order;
    private Plane plane;
}
