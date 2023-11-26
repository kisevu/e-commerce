package com.amedakevinkisevu.user.service;

import com.amedakevinkisevu.user.DTO.UserRequest;
import com.amedakevinkisevu.user.entity.User;
import com.amedakevinkisevu.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public String setId(){
        return UUID.randomUUID().toString()
                .replace("-", "")
                .substring(0, 12);
    }
    public User createUser(UserRequest userRequest) {
        User user = User.builder()
                .userId(setId())
                .name(userRequest.getName())
                .build();
        return userRepository.save(user);
    }
    public User getUser(String userId) {
        return userRepository.findById(userId).orElseThrow(null);
    }
}
