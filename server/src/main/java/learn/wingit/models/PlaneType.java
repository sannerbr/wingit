package learn.wingit.models;

public class PlaneType {
    private int type_id;
    private String name;

    public PlaneType(int type_id, String name) {
        this.type_id = type_id;
        this.name = name;
    }

    public PlaneType() {

    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
