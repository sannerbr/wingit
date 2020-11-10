package learn.wingit.models;

public class Size {
    private int sizeId;
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
