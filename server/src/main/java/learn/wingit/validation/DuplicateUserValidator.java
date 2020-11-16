package learn.wingit.validation;

import learn.wingit.data.UserRepository;
import learn.wingit.domain.UserService;
import learn.wingit.models.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;

public class DuplicateUserValidator implements ConstraintValidator<NoDuplicateUser, User> {

    //TODO need the userRepository to check for duplicates
    @Autowired
    UserService service;

    @Override
    public void initialize(NoDuplicateUser constraintAnnotation) {
        //
    }

    @Override
    public boolean isValid(User user, ConstraintValidatorContext constraintValidatorContext) {
        if (user == null) {
            return true;
        }

        boolean result = service.findAll().stream().anyMatch(user1 -> user1.getUsername().equalsIgnoreCase(user.getUsername()));
        if(result == true) {
            return false;
        }
        return true;
    }
}
