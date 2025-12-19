package edu.icet.controller;

import edu.icet.model.dto.request.RegistrationRequest;
import edu.icet.model.dto.request.LoginRequest;
import edu.icet.model.dto.response.UserResponse;
import edu.icet.service.impl.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-control")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")


public class AuthController {
    @Autowired
    private AuthService service;

    @PostMapping("/register")
    public UserResponse register(@RequestBody RegistrationRequest user){
         return service.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest user){
        return service.verify(user);

    }
}
