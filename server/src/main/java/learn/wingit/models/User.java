package learn.wingit.models;

import learn.wingit.validation.NoDuplicateUser;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoDuplicateUser(message = "This username is already taken")
public class User {
    private int userId;

    @NotBlank(message = "Username is required.")
    private String username;

    @NotBlank(message = "Password is required.")
    private String password;

    @NotBlank(message = "Email is required.")
    private String email;

    @NotBlank(message = "Phone number is required.")
    private String phone;

    @NotBlank(message = "Address is required.")
    private String address;

    private String company;
    private List<Order> orders = new ArrayList<>();
    @NotNull(message = "Role is required")
    private Role role;

}
