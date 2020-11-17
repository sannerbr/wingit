package learn.wingit.controllers;

import learn.wingit.domain.Result;
import learn.wingit.domain.UserService;
import learn.wingit.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:8081"})
@RequestMapping("/api/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<User> findAll() {
        return service.findAll();
    }

    @GetMapping("/id/{userId}")
    public User findById(@PathVariable int userId) {
        return service.findById(userId);
    }

//    @GetMapping("/username/{userName}")
//    public User findByUsername(@PathVariable String userName) {
//        return service.findByUsername(userName);
//    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody User user) {
        Result<User> result = service.add(user);
        if(result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/id/{userId}")
    public ResponseEntity<Object> update(@PathVariable int userId, @RequestBody User user) {
        if(user.getUserId() != userId) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        user.setPassword(service.findById(userId).getPassword());
        Result<User> result = service.update(user);

        if(result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }

    @DeleteMapping("/id/{userId}")
    public ResponseEntity<Object> delete(@PathVariable int userId) {
        boolean result = service.delete(userId);
        if(result == true) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
