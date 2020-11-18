package learn.wingit.validation;

import learn.wingit.data.PlaneJdbcTemplateRepository;
import learn.wingit.models.Order;
import learn.wingit.models.Plane;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

public class QuantityOrderedValidator implements ConstraintValidator<QuantityOrderedIsAvailable, Order> {
    @Autowired
    PlaneJdbcTemplateRepository planeRepository;

    @Override
    public void initialize(QuantityOrderedIsAvailable constraintAnnotation) {

    }

    @Override
    public boolean isValid(Order order, ConstraintValidatorContext constraintValidatorContext) {
        if(order ==  null) {
            return true;
        }
        for(Plane p : order.getPlanes()) {
          if(planeRepository.findById(p.getPlane_id()).getQuantity() < p.getQuantity()) {
              return false;
          }
        }
        return true;
    }
}
