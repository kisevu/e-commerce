package com.amedakevinkisevu.post.service;

import com.amedakevinkisevu.post.DTO.PostRequest;
import com.amedakevinkisevu.post.common.User;
import com.amedakevinkisevu.post.entity.Post;
import com.amedakevinkisevu.post.feign.UserFeignCall;
import com.amedakevinkisevu.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserFeignCall userFeignCall;
    private final User user;

    public String setId(){
        return UUID.randomUUID().toString()
                .replace("-", "")
                .substring(0, 12);
    }
    public Post upload(PostRequest postRequest, String userId) {
        User result = userFeignCall.getUser(userId).getBody();
        assert result != null;
        Post post = Post.builder()
                .postId(setId())
                .caption(postRequest.getCaption())
                .userId(result.getUserId())
                .build();
        return postRepository.save(post);
    }
}

