package learn.wingit.domain;


import learn.wingit.data.PlaneRepository;
import learn.wingit.models.Plane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class PlaneService {

    private enum ValidationMode {
        ADD, UPDATE
    }

    private final PlaneRepository repository;

    @Autowired
    private Validator validator;

    public PlaneService(PlaneRepository repository) {
        this.repository = repository;
    }

    public List<Plane> findAll() {
        return repository.findAll();
    }

    public List<Plane> findAllForUser() {
        return repository.findAllForUser();
    }

    public List<Plane> findByType(int type_id) {
        return repository.findByType(type_id);
    }

    public Plane findById(int planeId) {
        return repository.findById(planeId);
    }

    public Result<Plane> add(Plane plane) {
        Result<Plane> result = validate(plane, ValidationMode.ADD);

        if (result.isSuccess()) {
            plane = repository.add(plane);
            result.setPayload(plane);
            return result;
        }
        return result;
    }

    public Result<Plane> update(Plane plane) {
        Result<Plane> result = validate(plane, ValidationMode.UPDATE);

        if (result.isSuccess()) {
            if (!repository.update(plane)) {
                String msg = String.format("Plane Id: %s, not found", plane.getPlane_id());
                result.addMessage(msg, ResultType.NOT_FOUND);
            }
        }
        return result;
    }

    public boolean delete(int planeId) {
        return repository.delete(planeId);
    }

    private Result<Plane> validate(Plane plane, ValidationMode mode) {
        Result<Plane> result = new Result<>();
        if (plane == null) {
            result.addMessage("plane cannot be null", ResultType.INVALID);
            return result;
        }
        if (mode == ValidationMode.ADD && plane.getPlane_id() != 0) {
            result.addMessage("planeId cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }
        if (mode == ValidationMode.UPDATE && plane.getPlane_id() <= 0) {
            result.addMessage("planeId must be set for `update` operation", ResultType.INVALID);
            return result;
        }
        if (result.isSuccess()) {
            Set<ConstraintViolation<Plane>> violations = validator.validate(plane);
            if (!violations.isEmpty()) {
                for (ConstraintViolation<Plane> v : violations) {
                    result.addMessage(v.getMessage(), ResultType.INVALID);
                }
            }
        }
        return result;
    }
}
