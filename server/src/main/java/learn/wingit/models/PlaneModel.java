package learn.wingit.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PlaneModel {
    private int model_id;
    @NotBlank(message = "Model needs a name")
    private String name;
    @Min(value = 1, message = "Model must have a manufacturer ID greater than 1")
    private int manufacturer_id;

    public PlaneModel(int model_id, @NotBlank(message = "Model needs a name") String name,
                      @Min(value = 1, message = "Model must have a manufacturer ID greater than 1") int manufacturer_id) {
        this.model_id = model_id;
        this.name = name;
        this.manufacturer_id = manufacturer_id;
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

    public int getManufacturer_id() {
        return manufacturer_id;
    }

    public void setManufacturer_id(int manufacturer_id) {
        this.manufacturer_id = manufacturer_id;
    }
}
