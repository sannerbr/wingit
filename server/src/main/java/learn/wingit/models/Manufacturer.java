package learn.wingit.models;

import lombok.Data;

import javax.validation.constraints.NotBlank;



@Data
public class Manufacturer {
    private int manufacturer_id;
    @NotBlank(message = "Name must be provided")
    private String name;

}
