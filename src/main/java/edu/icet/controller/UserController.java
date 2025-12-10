package edu.icet.controller;

import edu.icet.model.dto.User;
import edu.icet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping("/register")
    public User register(@RequestBody edu.icet.model.dto.User user){
         return service.register(user);
    }
    @PostMapping("/login")
    public String login(@RequestBody User user){
        return service.verify(user);

    }
}
