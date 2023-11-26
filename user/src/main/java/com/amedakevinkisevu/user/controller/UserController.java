package com.amedakevinkisevu.user.controller;

import com.amedakevinkisevu.user.DTO.UserRequest;
import com.amedakevinkisevu.user.entity.User;
import com.amedakevinkisevu.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserRequest userRequest){
        return new ResponseEntity<>(userService.createUser(userRequest),HttpStatus.OK);
    }
    @GetMapping("/user")
    public ResponseEntity<?> getUser(@RequestParam String userId){
        return new ResponseEntity<>(userService.getUser(userId),HttpStatus.OK);
    }
}
