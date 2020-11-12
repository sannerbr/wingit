package learn.wingit.models;

import javax.validation.constraints.NotBlank;

public enum Size {
    SMALL(1, "Small"),
    MEDIUM(2, "Medium"),
    LARGE(3, "Large");


    private int sizeId;
    private String size;

    Size(int sizeId, String size) {
        this.sizeId = sizeId;
        this.size = size;
    }

    public static Size getSizeById(int id) {
        for (Size s : Size.values()) {
            if (s.sizeId == id) {
                return s;
            }
        }
        return null;
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
