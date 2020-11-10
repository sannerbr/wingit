package learn.wingit.models;

public class Manufacturer {
    private int manufacturer_id;
    private String name;

    public Manufacturer(int manufacturer_id, String name) {
        this.manufacturer_id = manufacturer_id;
        this.name = name;
    }

    public Manufacturer() {
    }

    public int getManufacturer_id() {
        return manufacturer_id;
    }

    public void setManufacturer_id(int manufacturer_id) {
        this.manufacturer_id = manufacturer_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
