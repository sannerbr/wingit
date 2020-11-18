package learn.wingit.models;

import learn.wingit.validation.QuantityOrderedIsAvailable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
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

@Data
@AllArgsConstructor
@NoArgsConstructor
@QuantityOrderedIsAvailable
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
}
