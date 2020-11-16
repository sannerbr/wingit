package learn.wingit.domain;

import learn.wingit.data.UserRepository;
import learn.wingit.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private enum ValidationMode {
        ADD, UPDATE
    }

    @Autowired
    private Validator validator;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(int userId) {
        return userRepository.findById(userId);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Result<User> add(User user) {
        Result<User> result = validate(user, ValidationMode.ADD);
        if(result.isSuccess()) {
            userRepository.addUser(user);
            result.setPayload(user);
        }
        return result;
    }

    public Result<User> update(User user) {
        Result<User> result = validate(user, ValidationMode.UPDATE);
        if(result.isSuccess()) {
            if(userRepository.updateUser(user)) {
                result.setPayload(user);
            } else {
                String msg = String.format("User id: %s is not found!", user.getUserId());
                result.addMessage(msg, ResultType.NOT_FOUND);
            }
        }
        return result;
    }

    public boolean delete(int userId) {
       return userRepository.deleteUser(userId);
    }

    private Result<User> validate(User user, UserService.ValidationMode validationMode) {
        Result<User> result = new Result<>();

        if(user == null) {
            result.addMessage("User cannot be null", ResultType.INVALID);
        } else if(validationMode == UserService.ValidationMode.ADD && user.getUserId() > 0) {
            result.addMessage("User ID cannot be set for user", ResultType.INVALID);
        } else if(validationMode == UserService.ValidationMode.UPDATE && user.getUserId() <= 0) {
            result.addMessage("User ID is required", ResultType.INVALID);
        }

        if(result.isSuccess()) {
            Set<ConstraintViolation<User>> violations = validator.validate(user);

            if(!violations.isEmpty()) {
                for(ConstraintViolation<User> violation : violations) {
                    result.addMessage(violation.getMessage(), ResultType.INVALID);
                }
            }
        }
        return result;
    }

}
