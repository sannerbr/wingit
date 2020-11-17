package learn.wingit.domain;

import learn.wingit.data.UserRepository;
import learn.wingit.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userModel = userRepository.findByUsername(username);

        if (userModel == null) {
            throw new UsernameNotFoundException(username + " not found.");
        }

        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + userModel.getRole().getRole()));

        return new org.springframework.security.core.userdetails.User(username, userModel.getPassword(), authorities);
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

    public Result<User> add(User user) {
        Result<User> result = validate(user, ValidationMode.ADD);
        if(result.isSuccess()) {
            user.setPassword(encoder.encode(user.getPassword()));
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
