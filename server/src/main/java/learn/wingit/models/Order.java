package learn.wingit.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Order {
    private int orderId;
    @NotNull(message = "Must provide a user ID")
    private int userId;
    @NotNull(message = "Must provide a plane ID")
    private int planeId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "Order Date cannot be in the future")
    private LocalDate orderDate;
    @Min(value = 0, message = "Total cost must be greater than {value}")
    private BigDecimal totalCost;

    public Order() {
    }

    public Order(int orderId, int userId, int planeId, LocalDate orderDate, BigDecimal totalCost) {
        this.orderId = orderId;
        this.userId = userId;
        this.planeId = planeId;
        this.orderDate = orderDate;
        this.totalCost = totalCost;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPlaneId() {
        return planeId;
    }

    public void setPlaneId(int planeId) {
        this.planeId = planeId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId &&
                userId == order.userId &&
                planeId == order.planeId &&
                Objects.equals(orderDate, order.orderDate) &&
                Objects.equals(totalCost, order.totalCost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, userId, planeId, orderDate, totalCost);
    }
}
