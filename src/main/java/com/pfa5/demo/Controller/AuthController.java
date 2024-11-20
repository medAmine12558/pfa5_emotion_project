package com.pfa5.demo.Controller;

import com.pfa5.demo.DTO.UserLoginRequest;
import com.pfa5.demo.DTO.UserSignupRequest;
import com.pfa5.demo.Entities.User;
import com.pfa5.demo.Service.AuthService;
import com.pfa5.demo.Service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.pfa5.demo.Configs.LoginResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    JwtService jwtService;
    @Autowired
     AuthService authenticationService;
    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody UserSignupRequest user) {
        User registeredUser=authenticationService.signup(user);
        return ResponseEntity.ok(registeredUser);
    }
    @GetMapping("/a")
    public String hello(){
        return "hello";
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody UserLoginRequest user) {
        try{
            User authenticatedUser=authenticationService.login(user);
            String jwtToken = jwtService.generateToken(authenticatedUser);
            LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());
            return ResponseEntity.ok(loginResponse);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    @GetMapping("/me")
    public ResponseEntity<Boolean> me(){
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Boolean currentUser = authentication.isAuthenticated();
            return ResponseEntity.ok(currentUser);
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7); // Extraire le token
            jwtService.blacklistToken(token); // Ajouter le token à la liste noire
            return ResponseEntity.ok("Logout successful");
        } else {
            return ResponseEntity.status(401).body("Unauthorized");
        }
    }
}
