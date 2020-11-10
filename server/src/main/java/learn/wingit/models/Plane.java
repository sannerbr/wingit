package learn.wingit.models;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Plane {
    private int plane_id;
    @NotNull(message = "Manufacturer ID is required")
    private int manufacturer_model_id;
    @NotNull(message = "Size ID required")
    private int size_id;
    @NotNull(message = "Type ID is required")
    private int type_id;
    @Min(value = 0, message = "Price is required and must be greater than {value}")
    BigDecimal price;
    @Min(value = 0, message = "Quantity must be greater than {value}")
    private int quantity;
    @Min(value = 0, message = "Seating capacity must be more than {value}")
    private int seating_capacity;
    @Min(value = 0, message = "Height must be greater than {value}")
    private int height;
    @Min(value = 0, message = "Length must be greater than {value}")
    private int length;
    @Min(value = 0,message = "Wingspan must be greater than {value}")
    private int wingspan;
    @Min(value = 0, message = "Range must be larger than {value}")
    private int range;
    @NotBlank(message = "Description is required.")
    private String description;
    List<Order> orders = new ArrayList<>();

    public Plane() {

    }

    public Plane(int plane_id, @NotNull(message = "Manufacturer ID is required") int manufacturer_model_id,
                 @NotNull(message = "Size ID required") int size_id, @NotNull(message = "Type ID is required") int type_id,
                 @Min(value = 0, message = "Price is required and must be greater than {value}") BigDecimal price,
                 @Min(value = 0, message = "Quantity must be greater than {value}") int quantity, @Min(value = 0, message = "Seating capacity must be more than {value}") int seating_capacity,
                 @Min(value = 0, message = "Height must be greater than {value}") int height, @Min(value = 0, message = "Length must be greater than {value}") int length,
                 @Min(value = 0, message = "Wingspan must be greater than {value}") int wingspan, @Min(value = 0, message = "Range must be larger than {value}") int range,
                 @NotBlank(message = "Description is required.") String description, List<Order> orders) {
        this.plane_id = plane_id;
        this.manufacturer_model_id = manufacturer_model_id;
        this.size_id = size_id;
        this.type_id = type_id;
        this.price = price;
        this.quantity = quantity;
        this.seating_capacity = seating_capacity;
        this.height = height;
        this.length = length;
        this.wingspan = wingspan;
        this.range = range;
        this.description = description;
        this.orders = orders;
    }

    public int getPlane_id() {
        return plane_id;
    }

    public void setPlane_id(int plane_id) {
        this.plane_id = plane_id;
    }

    public int getManufacturer_model_id() {
        return manufacturer_model_id;
    }

    public void setManufacturer_model_id(int manufacturer_model_id) {
        this.manufacturer_model_id = manufacturer_model_id;
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

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plane plane = (Plane) o;
        return plane_id == plane.plane_id &&
                manufacturer_model_id == plane.manufacturer_model_id &&
                size_id == plane.size_id &&
                type_id == plane.type_id &&
                quantity == plane.quantity &&
                seating_capacity == plane.seating_capacity &&
                height == plane.height &&
                length == plane.length &&
                wingspan == plane.wingspan &&
                range == plane.range &&
                price.equals(plane.price) &&
                description.equals(plane.description) &&
                orders.equals(plane.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plane_id, manufacturer_model_id, size_id, type_id, price, quantity, seating_capacity, height, length, wingspan, range, description, orders);
    }
}
