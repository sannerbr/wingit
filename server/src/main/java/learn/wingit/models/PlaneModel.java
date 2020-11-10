package learn.wingit.models;

import javax.validation.constraints.NotBlank;

public class PlaneModel {
    private int model_id;
    @NotBlank(message = "Model needs a name")
    private String name;

    public PlaneModel(int model_id, @NotBlank(message = "Model needs a name") String name) {
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
