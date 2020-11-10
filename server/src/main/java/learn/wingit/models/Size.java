package learn.wingit.models;

import javax.validation.constraints.NotBlank;

public class Size {
    private int sizeId;
    @NotBlank(message = "Size is required.")
    private String size;

    public Size() {
    }

    public Size(int sizeId, String size) {
        this.sizeId = sizeId;
        this.size = size;
    }

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
