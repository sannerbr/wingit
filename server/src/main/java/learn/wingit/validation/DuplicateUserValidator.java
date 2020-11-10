package learn.wingit.validation;

import learn.wingit.models.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;

public class DuplicateUserValidator implements ConstraintValidator<NoDuplicateUser, User> {

    //TODO need the userRepository to check for duplicates

    @Override
    public void initialize(NoDuplicateUser constraintAnnotation) {

    }

    @Override
    public boolean isValid(User user, ConstraintValidatorContext constraintValidatorContext) {
        if (user == null){
            return true;
        }

        return false;
    }
}
