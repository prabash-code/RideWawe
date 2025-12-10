package edu.icet.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
public class LoginController {
    @GetMapping("/name")
    public String getName(HttpServletRequest request){
        return "welcome !!"+request.getSession().getId();

    }

}
