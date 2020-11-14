package learn.wingit.models;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


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


    //HAS MANY IMAGES (List<Image>)

    public Plane() {

    }

    public int getPlane_id() {
        return plane_id;
    }

    public void setPlane_id(int plane_id) {
        this.plane_id = plane_id;
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

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    public PlaneModel getModel() {
        return model;
    }

    public void setModel(PlaneModel model) {
        this.model = model;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
