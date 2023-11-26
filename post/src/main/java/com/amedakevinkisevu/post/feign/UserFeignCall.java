package com.amedakevinkisevu.post.feign;

import com.amedakevinkisevu.post.common.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user")
public interface UserFeignCall {
    @PostMapping("/api/users/create")
    public ResponseEntity<User> createUser(@RequestBody User user);
    @GetMapping("api/users/user")
    public ResponseEntity<User> getUser(@RequestParam("userId")String userId);
}
