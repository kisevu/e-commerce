package com.amedakevinkisevu.post.common;

import lombok.*;
import org.springframework.stereotype.Component;
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private String userId;
    private String name;
}

