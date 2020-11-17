package learn.wingit.controllers;

import learn.wingit.domain.PlaneService;
import learn.wingit.domain.Result;
import learn.wingit.models.Plane;
import learn.wingit.models.Type;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/hidden/all")
    public List<Plane> findAllHidden() {
        return service.findAllHidden();
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


    @PostMapping
    public ResponseEntity<Object> add(@RequestBody(required = false) Plane plane) {
        Result<Plane> result = service.add(plane);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/id/{planeId}")
    public ResponseEntity<Object> update(@PathVariable int planeId, @RequestBody Plane plane) {
        if (planeId != plane.getPlane_id()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Plane> result = service.update(plane);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }

    @DeleteMapping("/id/{planeId}")
    public ResponseEntity<Object> delete(@PathVariable int planeId) {
        if (service.delete(planeId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
