package learn.wingit.controllers;

import learn.wingit.domain.ModelService;
import learn.wingit.domain.Result;
import learn.wingit.models.PlaneModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:8081"})
@RequestMapping("/api/models")
public class ModelController {

    private final ModelService service;


    public ModelController(ModelService service) {
        this.service = service;
    }
/*

    @GetMapping
    public List<PlaneModel> findAll() {return service.findAll();}

    @GetMapping("/{modelId}")
    public PlaneModel findById(@PathVariable int modelId) {
        return service.findById(modelId);
    }
*/

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody(required = false) PlaneModel model) {
        Result<PlaneModel> result = service.add(model);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

   /* @PutMapping("/{modelId}")
    public ResponseEntity<Object> update(@PathVariable int modelId, @RequestBody PlaneModel model) {
        if (model.getModel_id() != modelId) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<PlaneModel> result = service.update(model);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }*/
}
