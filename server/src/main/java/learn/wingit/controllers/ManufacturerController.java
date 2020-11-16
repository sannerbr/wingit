package learn.wingit.controllers;

import learn.wingit.domain.ManufacturerService;
import learn.wingit.domain.Result;
import learn.wingit.models.Manufacturer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:8081"})
@RequestMapping("/api/manufacturers")
public class ManufacturerController {

    private final ManufacturerService service;


    public ManufacturerController(ManufacturerService service) {
        this.service = service;
    }

    @GetMapping
    public List<Manufacturer> findAll() {return service.findAll();}

    @GetMapping("/{manufacturerId}")
    public Manufacturer findById(@PathVariable int manufacturerId) {
        return service.findById(manufacturerId);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody(required = false) Manufacturer manufacturer) {
        Result<Manufacturer> result = service.add(manufacturer);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{manufacturerId}")
    public ResponseEntity<Object> update(@PathVariable int manufacturerId, @RequestBody Manufacturer manufacturer) {
        if (manufacturer.getManufacturer_id() != manufacturerId) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Manufacturer> result = service.update(manufacturer);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }
}
