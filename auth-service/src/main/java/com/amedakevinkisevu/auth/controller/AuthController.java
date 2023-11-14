package com.amedakevinkisevu.auth.controller;

import com.amedakevinkisevu.auth.DTO.AuthRequest;
import com.amedakevinkisevu.auth.entity.UserCredentials;
import com.amedakevinkisevu.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public String registerUser(@RequestBody  UserCredentials user){
        return authService.saveUser(user);
    }

   @PostMapping("/token")
    public String getToken(@RequestBody AuthRequest request){
        //only validate registered users...
        Authentication authenticate
        = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),
                request.getPassword()));
        if(authenticate.isAuthenticated()){
            return authService.generateToken(request.getUsername());
        }else{
            throw new RuntimeException("Invalid access");
        }
    }
    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token){
        authService.validateToken(token);
        return "Token is valid";
    }
}
