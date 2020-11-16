package learn.wingit.validation;

import learn.wingit.domain.PlaneService;
import learn.wingit.models.Plane;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DuplicatePlaneValidator implements ConstraintValidator<NoDuplicatePlane, Plane> {
    @Autowired
    PlaneService service;

    @Override
    public void initialize(NoDuplicatePlane constraintAnnotation) {
        //
    }

    @Override
    public boolean isValid(Plane plane, ConstraintValidatorContext constraintValidatorContext) {
        if(plane == null) {
            return true;
        }

        boolean result = service.findAll().stream().anyMatch(p -> (plane.getModel().getManufacturer().getName().equalsIgnoreCase(p.getModel().getManufacturer().getName()) &&
                plane.getModel().getName().equalsIgnoreCase(p.getModel().getName()) && plane.getSize().getSize().equalsIgnoreCase(p.getSize().getSize()) &&
                plane.getType().getName().equalsIgnoreCase(p.getType().getName()) && plane.getPlane_id() != p.getPlane_id()));

        if(result == true) {
            return false;
        }
        return true;
    }
}
