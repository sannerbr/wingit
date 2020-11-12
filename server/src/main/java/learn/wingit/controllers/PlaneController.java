package learn.wingit.controllers;

import learn.wingit.domain.PlaneService;
import learn.wingit.models.Plane;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000","http://127.0.0.1:8081"})
@RequestMapping("/planes")
public class PlaneController {

    private final PlaneService service;

    public PlaneController(PlaneService service) {
        this.service = service;
    }

    @GetMapping
    public List<Plane> findAll() {
        return service.findAll();
    }

    @GetMapping("/{planeId}")
    public Plane findById(@PathVariable int planeId) {
        return service.findById(planeId);
    }

    @GetMapping("/{typeId}")
    public List<Plane> findByType(@PathVariable int typeId) {
        return service.findByType(typeId);
    }
}
