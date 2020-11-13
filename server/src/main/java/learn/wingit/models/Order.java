package learn.wingit.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {
    private int orderId;
    @NotNull(message = "Must provide a user ID")
    @Min(value = 1, message = "User ID must be greater than or equal to {value}")
    private int userId;
    @NotNull(message = "Order Date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "Order Date cannot be in the future")
    private LocalDate orderDate;
    @DecimalMin(value = "0.00", message = "Total cost must be greater than or equal to {value}", inclusive = true)
    @NotNull(message = "Total cost is required")
    private BigDecimal totalCost;
    private List<Plane> planes = new ArrayList<>();

    public Order() {
    }

    public Order(int orderId, int userId, LocalDate orderDate, BigDecimal totalCost, List<Plane> planes) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderDate = orderDate;
        this.totalCost = totalCost;
        this.planes = planes;
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

    public List<Plane> getPlanes() {
        return planes;
    }

    public void setPlanes(List<Plane> planes) {
        this.planes = planes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId &&
                userId == order.userId &&
                Objects.equals(orderDate, order.orderDate) &&
                Objects.equals(totalCost, order.totalCost) &&
                Objects.equals(planes, order.planes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, userId, orderDate, totalCost, planes);
    }
}
