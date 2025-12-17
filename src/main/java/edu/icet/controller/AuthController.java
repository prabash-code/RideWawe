package edu.icet.controller;

import edu.icet.model.dto.request.UserRequest;
import edu.icet.model.dto.response.UserResponse;
import edu.icet.service.impl.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/user-control")


public class AuthController {
    @Autowired
    private AuthService service;

    @PostMapping("/register")
    public UserResponse register(@RequestBody UserRequest user){
         return service.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserRequest user){
        return service.verify(user);

    }
}
