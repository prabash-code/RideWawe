package edu.icet.controller;

import edu.icet.model.dto.response.UserResponse;
import edu.icet.model.entity.UserEntity;
import edu.icet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminController {
    @Autowired
    UserService userService;

    @GetMapping
    public List<UserEntity> getAllUsers(){
        return userService.getAllUsers();

    }

    @GetMapping("/email/{email}")
    public UserEntity findUserByEmail(@PathVariable String email){
        return userService.findUserByEmail(email);
    }
    @GetMapping("/{id}")
    public UserEntity findUserById(@PathVariable Long id){
        return userService.findUserById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUserById(id);
    }




}
