package learn.wingit.models;


import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class Plane {
    private int plane_id;
    @NotNull(message = "Model ID is required")
    @Min(value = 1, message = "Model ID must be greater than or equal to {value}")
    private int model_id;
    @NotNull(message = "Size ID required")
    @Min(value = 1, message = "Size ID must be greater than or equal to {value}")
    private int size_id;
    @NotNull(message = "Type ID is required")
    @Min(value = 1, message = "Type ID must be greater than or equal to {value}")
    private int type_id;
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

    private List<Order_Plane> orders = new ArrayList<>();

    //HAS MANY IMAGES (List<Image>)

    public Plane() {

    }

    public Plane(int plane_id, @NotNull(message = "Model ID is required") int model_id,
                 @NotNull(message = "Size ID required") int size_id,
                 @NotNull(message = "Type ID is required") int type_id,
                 @DecimalMin(value = "0.0", message = "Price is required and must be greater than {value}") BigDecimal price,
                 @Min(value = 0, message = "Quantity must be greater than {value}") int quantity,
                 @Min(value = 0, message = "Seating capacity must be more than {value}") int seating_capacity,
                 @Min(value = 0, message = "Height must be greater than {value}") int height,
                 @Min(value = 0, message = "Length must be greater than {value}") int length,
                 @Min(value = 0, message = "Wingspan must be greater than {value}") int wingspan, boolean isHidden,
                 @Min(value = 0, message = "Range must be larger than {value}") int range,
                 @NotBlank(message = "Description is required.") String description) {
        this.plane_id = plane_id;
        this.model_id = model_id;
        this.size_id = size_id;
        this.type_id = type_id;
        this.price = price;
        this.quantity = quantity;
        this.seating_capacity = seating_capacity;
        this.height = height;
        this.length = length;
        this.wingspan = wingspan;
        this.isHidden = isHidden;
        this.range = range;
        this.description = description;
    }

    public List<Order_Plane> getOrders() {
        return orders;
    }

    public void setOrders(List<Order_Plane> orders) {
        this.orders = orders;
    }

    public int getPlane_id() {
        return plane_id;
    }

    public void setPlane_id(int plane_id) {
        this.plane_id = plane_id;
    }

    public int getModel_id() {
        return model_id;
    }

    public void setModel_id(int model_id) {
        this.model_id = model_id;
    }

    public int getSize_id() {
        return size_id;
    }

    public void setSize_id(int size_id) {
        this.size_id = size_id;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSeating_capacity() {
        return seating_capacity;
    }

    public void setSeating_capacity(int seating_capacity) {
        this.seating_capacity = seating_capacity;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWingspan() {
        return wingspan;
    }

    public void setWingspan(int wingspan) {
        this.wingspan = wingspan;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getTotalPrice() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }
}
