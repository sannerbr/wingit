package learn.wingit.controllers;

import learn.wingit.models.Role;
import learn.wingit.models.Size;
import learn.wingit.models.Type;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EnumController {

    @GetMapping("/types")
    public Type[] getTypes() {
        return Type.values();
    }

    @GetMapping("/sizes")
    public Size[] getSizes() {
        return Size.values();
    }

    @GetMapping("/roles")
    public Role[] getRoles() {
        return Role.values();
    }
}
