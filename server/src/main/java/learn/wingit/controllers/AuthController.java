package learn.wingit.controllers;


import learn.wingit.domain.UserService;
import learn.wingit.models.Role;
import learn.wingit.security.JwtConverter;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:8081"})
public class AuthController {


    private final AuthenticationManager authenticationManager;
    private final JwtConverter converter;
    private final UserService service;

    public AuthController(AuthenticationManager authenticationManager, JwtConverter converter, UserService service) {
        this.authenticationManager =  authenticationManager;
        this.converter = converter;
        this.service = service;
    }

    @PostMapping("/api/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody Map<String, String> credentials) {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(credentials.get("username"), credentials.get("password"));


        try {
            Authentication authentication = authenticationManager.authenticate(authToken);

            if(authentication.isAuthenticated()) {
                User user = (User) authentication.getPrincipal();
                String jwtToken = converter.getTokenFromUser(user);
                HashMap<String, String> map = new HashMap<>();
                map.put("jwt_token", jwtToken);
                return new ResponseEntity<>(map, HttpStatus.OK);
            }
        } catch (AuthenticationException ex) {
            System.out.println(ex);
        }

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PostMapping("/api/create-account")
    public ResponseEntity<?> createAccount(@RequestBody learn.wingit.models.User appUser) {
        try {
            appUser.setRole(Role.USER);
            service.add(appUser);
        } catch (ValidationException ex) {
//            ValidationErrorResult validationErrorResult = new ValidationErrorResult();
//            validationErrorResult.addMessage(ex.getMessage());
//            return new ResponseEntity<>(validationErrorResult, HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(List.of(ex.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (DuplicateKeyException ex) {
//            ValidationErrorResult validationErrorResult = new ValidationErrorResult();
//            validationErrorResult.addMessage("The provided username already exists");
//            return new ResponseEntity<>(validationErrorResult, HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(List.of("The provided username already exists"), HttpStatus.BAD_REQUEST);
        }

        HashMap<String, String> map = new HashMap<>();
        map.put("appUserId", String.valueOf(appUser.getUserId()));

        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }
}
