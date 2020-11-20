package learn.wingit.domain;

import learn.wingit.data.ManufacturerRepository;
import learn.wingit.models.Manufacturer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class ManufacturerService {

    private enum ValidationMode {
        ADD, UPDATE
    }

    private final ManufacturerRepository repository;

    public ManufacturerService(ManufacturerRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private Validator validator;

    public List<Manufacturer> findAll() {
        return repository.findAll();
    }

    public Manufacturer findById(int manufacturerId) {return repository.findById(manufacturerId);}

    public Result<Manufacturer> add(Manufacturer manufacturer){
        Result<Manufacturer> result = validate(manufacturer, ValidationMode.ADD);

        if (result.isSuccess()) {
            manufacturer = repository.add(manufacturer);
            result.setPayload(manufacturer);
            return result;
        }
        return result;
    }

    public Result<Manufacturer> update(Manufacturer manufacturer) {
        Result<Manufacturer> result = validate(manufacturer, ValidationMode.UPDATE);

        if (result.isSuccess()) {
            if (!repository.update(manufacturer)) {
                result.addMessage(String.format("Manufacturer Id: %s, not found",
                        manufacturer.getManufacturer_id()), ResultType.NOT_FOUND);
                return result;
            }
        }
        return result;
    }

    public Result<Manufacturer> validate(Manufacturer manufacturer, ValidationMode mode) {
        Result<Manufacturer> result = new Result<>();

        if (manufacturer == null) {
            result.addMessage("Manufacturer cannot be null", ResultType.INVALID);
            return result;
        }
        if (mode == ValidationMode.ADD && manufacturer.getManufacturer_id() != 0) {
            result.addMessage("Manufacturer Id cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }
        if (mode == ValidationMode.UPDATE && manufacturer.getManufacturer_id() <= 0) {
            result.addMessage("Manufacturer Id must be set for `update` operation", ResultType.INVALID);
            return result;
        }
        if (result.isSuccess()) {
            Set<ConstraintViolation<Manufacturer>> violations = validator.validate(manufacturer);
            if (!violations.isEmpty()) {
                for (ConstraintViolation<Manufacturer> v : violations) {
                    result.addMessage(v.getMessage(), ResultType.INVALID);
                }
            }
        }
        return result;
    }
}
