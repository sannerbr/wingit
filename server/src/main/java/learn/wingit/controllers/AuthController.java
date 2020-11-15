package learn.wingit.controllers;


import learn.wingit.security.JwtConverter;
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

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:8081"})
public class AuthController {


    private final AuthenticationManager authenticationManager;
    private final JwtConverter converter;

    public AuthController(AuthenticationManager authenticationManager, JwtConverter converter) {
        this.authenticationManager =  authenticationManager;
        this.converter = converter;
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
}
