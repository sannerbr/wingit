package learn.wingit.models;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PlaneModel {
    private int model_id;
    @NotBlank(message = "Model needs a name")
    private String name;
    @NotNull(message = "Manufacturer is required")
    private Manufacturer manufacturer;
}
