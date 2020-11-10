package learn.wingit.models;

public class PlaneModel {
    private int model_id;
    private String name;

    public PlaneModel(int model_id, String name) {
        this.model_id = model_id;
        this.name = name;
    }

    public PlaneModel() {

    }

    public int getModel_id() {
        return model_id;
    }

    public void setModel_id(int model_id) {
        this.model_id = model_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
