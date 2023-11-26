package com.amedakevinkisevu.post.controller;

import com.amedakevinkisevu.post.DTO.PostRequest;
import com.amedakevinkisevu.post.entity.Post;
import com.amedakevinkisevu.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {
    private final PostService postService;
    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestBody PostRequest postRequest, @RequestParam String userId){
        return new ResponseEntity<>(postService.upload(postRequest,userId),HttpStatus.OK);
    }
}
