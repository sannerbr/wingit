package learn.wingit.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

public class Manufacturer {
    private int manufacturer_id;
    @NotBlank(message = "Name must be provided")
    private String name;
    List<PlaneModel> models = new ArrayList<>();

    public Manufacturer(int manufacturer_id, @NotBlank(message = "Name must be provided") String name, List<PlaneModel> models) {
        this.manufacturer_id = manufacturer_id;
        this.name = name;
        this.models = models;
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

    public List<PlaneModel> getModels() {
        return models;
    }

    public void setModels(List<PlaneModel> models) {
        this.models = models;
    }
}
