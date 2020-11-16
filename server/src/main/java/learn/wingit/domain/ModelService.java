package learn.wingit.domain;

import learn.wingit.data.ModelRepository;
import learn.wingit.models.PlaneModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class ModelService {

    private enum ValidationMode {
        ADD, UPDATE
    }

    private final ModelRepository repository;

    public ModelService(ModelRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private Validator validator;

    public List<PlaneModel> findAll() {
        return repository.findAll();
    }

    public PlaneModel findById(int modelId) {return repository.findById(modelId);}

    public Result<PlaneModel> add(PlaneModel model){
        Result<PlaneModel> result = validate(model, ValidationMode.ADD);

        if (result.isSuccess()) {
            model = repository.add(model);
            result.setPayload(model);
            return result;
        }
        return result;
    }

    public Result<PlaneModel> update(PlaneModel model) {
        Result<PlaneModel> result = validate(model, ValidationMode.UPDATE);

        if (result.isSuccess()) {
            if (!repository.update(model)) {
                result.addMessage(String.format("Model Id: %s, not found", model.getModel_id()), ResultType.NOT_FOUND);
                return result;
            }
        }
        return result;
    }

    //Alec and I decided to leave out deletion due to complications

    public Result<PlaneModel> validate(PlaneModel model, ValidationMode mode) {
        Result<PlaneModel> result = new Result<>();

        if (model == null) {
            result.addMessage("Model cannot be null", ResultType.INVALID);
            return result;
        }
        if (mode == ValidationMode.ADD && model.getModel_id() != 0) {
            result.addMessage("Model Id cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }
        if (mode == ValidationMode.UPDATE && model.getModel_id() <= 0) {
            result.addMessage("Model Id must be set for `update` operation", ResultType.INVALID);
            return result;
        }
        if (result.isSuccess()) {
            Set<ConstraintViolation<PlaneModel>> violations = validator.validate(model);
            if (!violations.isEmpty()) {
                for (ConstraintViolation<PlaneModel> v : violations) {
                    result.addMessage(v.getMessage(), ResultType.INVALID);
                }
            }
        }
        return result;
    }
}
