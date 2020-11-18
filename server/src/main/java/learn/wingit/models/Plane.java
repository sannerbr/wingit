package learn.wingit.models;

import learn.wingit.validation.NoDuplicatePlane;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoDuplicatePlane(message = "This plane already exists")
public class Plane {
    private int plane_id;
    @NotNull(message = "Model is required")
    private PlaneModel model;
    @NotNull(message = "Size is required")
    private Size size;
    @NotNull(message = "Type is required")
    private Type type;
    @DecimalMin(value = "0.00", message = "Price must be greater than or equal to {value}", inclusive = true)
    @NotNull(message = "Price is required")
    BigDecimal price;
    @Min(value = 0, message = "Quantity must be greater than {value}")
    private int quantity;
    @Min(value = 0, message = "Seating capacity must be more than {value}")
    private int seating_capacity;
    @Min(value = 0, message = "Height must be greater than {value}")
    private int height;
    @Min(value = 0, message = "Length must be greater than {value}")
    private int length;
    @Min(value = 0, message = "Wingspan must be greater than {value}")
    private int wingspan;
    @NotNull(message = "Hidden value must be true or false")
    private boolean isHidden;
    @Min(value = 0, message = "Range must be larger than {value}")
    private int range;
    @NotBlank(message = "Description is required.")
    private String description;

}
