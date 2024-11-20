package com.pfa5.demo.Controller;

import com.pfa5.demo.Entities.User;
import com.pfa5.demo.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    AuthService authService;
    @GetMapping("/u")
    public String user(){
        return "je suis dans user controller";
    }
    @GetMapping("/me")
    public Object authenticatedUser() {
        return authService.getCurrentUser();
    }
}
