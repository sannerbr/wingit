package learn.wingit.controllers;

import learn.wingit.domain.PlaneService;
import learn.wingit.models.Plane;
import learn.wingit.models.Type;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:8081"})
@RequestMapping("/api/planes")
public class PlaneController {

    private final PlaneService service;

    public PlaneController(PlaneService service) {
        this.service = service;
    }

    @GetMapping
    public List<Plane> findAll() {
        return service.findAll();
    }

    @GetMapping("/id/{planeId}")
    public Plane findById(@PathVariable int planeId) {
        return service.findById(planeId);
    }

    @GetMapping("/{planeType}")
    public List<Plane> findByType(@PathVariable String planeType) {
        Type type = Type.getTypeByName(planeType);
        if (type == null){
            return new ArrayList<>();
        }

        return service.findByType(type.getId());
    }
}
